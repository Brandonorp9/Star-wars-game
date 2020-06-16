import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;
public class Panel extends JPanel implements KeyListener , Runnable,ActionListener,MouseListener
{
    private Hero hero;
    private Map map;
    private Enemy enemy;
    private Item item;

    // initial choices the hero will have
    private JButton fight,runaway,medKit;

    //button to choice the type of attack
    private JButton force,blaster;

    //button to choose the type of force attack
    private JButton forceShock,forceSlam,forcePush;

    //booleans that will determine the actions available
    private boolean nextLevel=false ,moved;
    private boolean fighting=false;
    private boolean removeItemMode=false;

    //labels written the GUI
    private JLabel roomText,infoText,item1,item2,item3,item4,item5,damage;

    //rectangles to erase an item if we have a full set
    private Rectangle item1Click=new Rectangle(565, 185, 55, 55);
    private Rectangle item2Click=new Rectangle(620, 185, 55, 55);
    private Rectangle item3Click= new Rectangle(675, 185, 55, 55);
    private Rectangle item4Click=new Rectangle(565, 240, 55, 55);
    private Rectangle item5Click=new Rectangle(620, 240, 55, 55);

       

    public Panel()
    {
        addMouseListener(this);
        this.setLayout(null);
        setBackground(Color.WHITE);
        addKeyListener(this);
        setFocusable(true);

        //we create the variables needed for the games
        map=Map.getInstance();
        map.loadMap(1);
        hero=new Hero("brandon",map);

        //we create the buttons
        forceSlam=new JButton("forceSlam");
        forceShock=new JButton("forceShock");
        forcePush=new JButton("forcePush");
        runaway=new JButton("runaway");
        fight=new JButton("fight");
        medKit=new JButton("Medkit");
        force=new JButton("Force");
        blaster=new JButton("blaster");

        //we locate the buttons in our GUI
        runaway.setBounds(555, 490, 100, 18);
        fight.setBounds(555, 520, 100, 18);
        medKit.setBounds(555,540,100,18);
        force.setBounds(555, 520, 100, 18);
        blaster.setBounds(555, 540, 100, 18);
        forceSlam.setBounds(555, 490, 100, 18);
        forcePush.setBounds(555, 520, 100, 18);
        forceShock.setBounds(555,530,100,18);


        blaster.addActionListener(this);
        runaway.addActionListener(this);
        fight.addActionListener(this);
        force.addActionListener(this);
        forceSlam.addActionListener(this);
        forcePush.addActionListener(this);
        forceShock.addActionListener(this);
        medKit.addActionListener(this);

        //we put all our buttons in our GUI
        this.add(force);
        this.add(blaster);
        this.add(forceSlam);
        this.add(forcePush);
        this.add(forceShock);
        this.add(runaway);
        this.add(fight);
        this.add(medKit);

        //we make some buttons invisible
        force.setVisible(false);
        blaster.setVisible(false);
        runaway.setVisible(false);
        fight.setVisible(false);
        forceSlam.setVisible(false);
        forcePush.setVisible(false);
        forceShock.setVisible(false);
        medKit.setVisible(false);

        //room information
        roomText=new JLabel("");
        infoText=new JLabel("");
        roomText.setBounds(600, 380, 300, 30);
        infoText.setBounds(560,400,500,100);
        this.add(roomText);
        this.add(infoText);

        damage=new JLabel("");
        damage.setBounds(600,470,300,30);
        damage.setVisible(false);
        this.add(damage);

        //here we fill all the items that the hero has
        item1=new JLabel("");
        item2=new JLabel("");
        item3=new JLabel("");
        item4=new JLabel("");
        item5=new JLabel("");

        item1.setBounds(565, 185, 55, 55);
        item2.setBounds(620, 185, 55, 55);
        item3.setBounds(675, 185, 55, 55);
        item4.setBounds(565, 240, 55, 55);
        item5.setBounds(620, 240, 55, 55);

        this.add(item1);
        this.add(item2);
        this.add(item3);
        this.add(item4);
        this.add(item5);

    }


    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);

        //we fill the items that the heroes has
        item1.setText(hero.getFirstItem());
        item2.setText(hero.getSecondItem());
        item3.setText(hero.getThirdItem());
        item4.setText(hero.getFourthItem());
        item5.setText(hero.getFifthItem());

        Point heroLocation=hero.getLocation();
        //here we draw everything in the GUI
        int mapStartX=30;
        int mapStartY=30;
        for(int j=0;j<5;j++)
        {
            for(int i=0;i<5;i++)
            {
                g.drawRect(mapStartX+i*100, mapStartY+j*100, 100, 100);
                if(heroLocation.getX()==i && heroLocation.getY()==j)
                {
                    g.drawString("H",mapStartX+50+i*100,mapStartY+50+j*100);
                }
                else
                {
                    g.drawString(map.getmap(i, j),mapStartX+50+i*100,mapStartY+50+j*100);
                }
            }
        }
        int characterX=550;
        int characterY=50;
        g.drawRect(characterX, characterY, 200, 300);
        g.drawString("Character informaiton", characterX+30 ,characterY+15 );
        g.drawLine(characterX, characterY+30, characterX+200, characterY+30);
        g.drawString("Name : "+hero.getName(), characterX+15, characterY+45);
        g.drawString("Level : "+hero.getLevel(), characterX+15, characterY+65);
        g.drawString("Hp : "+hero.getHP(), characterX+15, characterY+85);

        g.drawString("Inventory", characterX+15, characterY+110);
        g.drawRect(characterX+15, characterY+135, 55, 55);
        g.drawRect(characterX+70, characterY+135, 55, 55);
        g.drawRect(characterX+125, characterY+135, 55, 55);
        g.drawRect(characterX+15, characterY+190, 55, 55);
        g.drawRect(characterX+70, characterY+190, 55, 55);

        g.drawRect(characterX, characterY+320,200, 200);
        g.drawLine(characterX,characterY+370,characterX+200,characterY+370);

    }

    //we make a function in order to be able to eliminate an item by clicking a number
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==KeyEvent.VK_1 && removeItemMode)
        {
            hero.removeItem(1);
            hero.pickUpItem(item);
            fighting=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_2 && removeItemMode)
        {
            hero.removeItem(2);
            hero.pickUpItem(item);
            fighting=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_3 && removeItemMode)
        {
            hero.removeItem(3);
            hero.pickUpItem(item);
            fighting=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_4 && removeItemMode)
        {
            hero.removeItem(4);
            hero.pickUpItem(item);
            fighting=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_5 && removeItemMode)
        {
            hero.removeItem(5);
            hero.pickUpItem(item);
            fighting=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {

            if(fighting==false)
            {
                moveRight();
            }
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if(fighting==false)
            {
                moveLeft();
            }
        }
        if (e.getKeyCode()==KeyEvent.VK_DOWN)
        {
            if(fighting==false)
            {
                moveDown();
            }
        }
        if (e.getKeyCode()==KeyEvent.VK_UP)
        {
            if(fighting==false)
            {
                moveUp();
            }
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void run()
    {
        while(true)
        {
            while(!nextLevel)
            {
                repaint();
                try
                {
                    Thread.sleep(16);
                }
                catch(InterruptedException e)
                {
                    System.out.println("we got a problem");
                }
            }
            int mapLevel;
            hero.increaseLevel();
            if(hero.getLevel()<4)
            {
                mapLevel=hero.getLevel();
            }
            else
            {
                mapLevel=hero.getLevel()%3;
            }
            Map.getInstance().loadMap(mapLevel);
            nextLevel=false;
        }
    }

    public void moveUp()
    {
        char nextLocation='x';

        if(hero.getLocation().getY()>0)
        {
            moved=true;
            nextLocation=hero.goNorth();
        }
        if (nextLocation=='e')
        {
            enemy=EnemyGenerator.getInstance().generateEnemy(hero.getLevel());
            roomText.setText("Enemy Found");
            infoText.setText("Level "+enemy.getLevel()+" "+enemy.getName()+" HP: "+enemy.getHP()+"/"+enemy.getMaxHp());
            fighting=true;
            runaway.setVisible(true);
            fight.setVisible(true);
            if(hero.hasMedKit())
            {
                medKit.setVisible(true);
            }
        }
        else if(nextLocation=='f')
        {
            roomText.setText("Found door");
            infoText.setText("");
            foundDoor();
            runaway.setVisible(false);
            fight.setVisible(false);
        }
        else if(nextLocation=='i')
        {
            roomText.setText("You found an item");
            Map.getInstance().removeCharAtLoc(hero.getLocation());
            item=ItemGenerator.getInstance().generateItem();
            infoText.setText("You got an"+item);

            if(!hero.pickUpItem(item))
            {
                JOptionPane.showMessageDialog(this,"You have to remove an Item by pressing the number of the item or the item itself");
                removeItemMode=true;
                fighting=true;
            }
        }
        else
        {
            roomText.setText("Empty room");

            infoText.setText("");
            fighting=false;
            runaway.setVisible(false);
            fight.setVisible(false);
        }

    }

    public void moveRight()
    {
        char nextLocation='x';
        if(hero.getLocation().getX()<4)
        {
            moved=true;
            nextLocation=hero.goEast();
        }
        if (nextLocation=='e')
        {
            roomText.setText("Enemy Found");
            enemy=EnemyGenerator.getInstance().generateEnemy(hero.getLevel());
            infoText.setText("Level "+enemy.getLevel()+" "+enemy.getName()+" HP: "+enemy.getHP()+"/"+enemy.getMaxHp());
            fighting=true;
            runaway.setVisible(true);
            fight.setVisible(true);
            if(hero.hasMedKit())
            {
                medKit.setVisible(true);
            }

        }
        else if(nextLocation=='f')
        {
            roomText.setText("Found door");
            infoText.setText("");
            foundDoor();
            runaway.setVisible(false);
            fight.setVisible(false);
        }
        else if(nextLocation=='i')
        {
            roomText.setText("You found an item");
            Map.getInstance().removeCharAtLoc(hero.getLocation());
            item=ItemGenerator.getInstance().generateItem();
            infoText.setText("You got an"+item);

            if(!hero.pickUpItem(item))
            {
                JOptionPane.showMessageDialog(this,"You have to remove an Item by pressing the number of the item or the item itself");
                removeItemMode=true;
                fighting=true;
            }
        }
        else
        {
            roomText.setText("Empty room");

            infoText.setText("");
            fighting=false;
            runaway.setVisible(false);
            fight.setVisible(false);
        }

    }


    public void moveLeft()
    {
        char nextLocation='x';
        if(hero.getLocation().getX()>0)
        {
            moved=true;
            nextLocation=hero.goWest();
        }
        if (nextLocation=='e')
        {
            roomText.setText("Enemy Found");
            enemy=EnemyGenerator.getInstance().generateEnemy(hero.getLevel());
            infoText.setText("Level "+enemy.getLevel()+" "+enemy.getName()+" HP: "+enemy.getHP()+"/"+enemy.getMaxHp());
            fighting=true;
            runaway.setVisible(true);
            fight.setVisible(true);
            if(hero.hasMedKit())
            {
                medKit.setVisible(true);
            }
        }
        else if(nextLocation=='f')
        {
            roomText.setText("Found door");
            infoText.setText("");
            foundDoor();
            runaway.setVisible(false);
            fight.setVisible(false);
        }
        else if(nextLocation=='i')
        {
            roomText.setText("you found an item");
            Map.getInstance().removeCharAtLoc(hero.getLocation());
            Item item=ItemGenerator.getInstance().generateItem();
            infoText.setText("You got an"+item);

            if(!hero.pickUpItem(item))
            {
                JOptionPane.showMessageDialog(this,"You have to remove an Item by pressing the number of the item or the item itself");
                removeItemMode=true;
                fighting=true;
            }
        }
        else
        {
            roomText.setText("Empty room");

            infoText.setText("");
            fighting=false;
            runaway.setVisible(false);
            fight.setVisible(false);
        }

    }
    public void moveDown()
    {
        char nextLocation='x';
        if(hero.getLocation().getY()<4)
        {
            moved=true;
            nextLocation=hero.goSouth();
        }
        if (nextLocation=='e')
        {
            roomText.setText("Enemy Found");
            enemy=EnemyGenerator.getInstance().generateEnemy(hero.getLevel());
            infoText.setText("Level "+enemy.getLevel()+" "+enemy.getName()+" HP: "+enemy.getHP()+"/"+enemy.getMaxHp());
            fighting=true;
            runaway.setVisible(true);
            fight.setVisible(true);
            if(hero.hasMedKit())
            {
                medKit.setVisible(true);
            }
        }
        else if(nextLocation=='f')
        {
            roomText.setText("Found door");
            infoText.setText("");
            foundDoor();
            runaway.setVisible(false);
            fight.setVisible(false);
        }
        else if(nextLocation=='i')
        {
            roomText.setText("you found an item");
            Map.getInstance().removeCharAtLoc(hero.getLocation());
            Item item=ItemGenerator.getInstance().generateItem();
            infoText.setText("You got an"+item);

            if(!hero.pickUpItem(item))
            {
                JOptionPane.showMessageDialog(this,"You have to remove an Item by pressing the number of the item or the item itself");
                removeItemMode=true;
                fighting=true;
            }
        }
        else
        {
            roomText.setText("Empty room");

            infoText.setText("");
            fighting=false;
            runaway.setVisible(false);
            fight.setVisible(false);
        }

    }


    public void fight()
    {

        if(enemy.getHP()>0)
        {
            infoText.setText("Level "+enemy.getLevel()+" "+enemy.getName()+" HP: "+enemy.getHP()+"/"+enemy.getMaxHp());
            damage.setText("Enemy damage "+enemy.attack(hero));
            damage.setVisible(true);
            runaway.setVisible(true);
            fight.setVisible(true);
            if(hero.hasMedKit())
            {
                medKit.setVisible(true);
            }
            blaster.setVisible(false);

            if(hero.hasArmor())
            {
                hero.heal(1);
                hero.removeFirstArmorItem();
            }
        }
        else
        {
            damage.setVisible(false);
            blaster.setVisible(false);
            Map.getInstance().removeCharAtLoc(hero.getLocation());
            infoText.setText("You get an "+enemy.getitem());
            boolean gotItem=hero.pickUpItem(enemy.getitem());
            if (gotItem==false)
            {

                JOptionPane.showMessageDialog(this,"You have to remove an Item by pressing the number of the item or the item itself");
                removeItemMode=true;
                fighting=true;
            }

            forcePush.setVisible(false);
            forceSlam.setVisible(false);
            forceShock.setVisible(false);
            runaway.setVisible(false);
            fight.setVisible(false);
            medKit.setVisible(false);
            fighting=false;
        }

        if(hero.getHP()==0)
        {
            JOptionPane.showMessageDialog(this,"gameOver");
            System.exit(0);
        }



    }
    public void foundDoor()
    {
        if(hero.hasKey())
        {
            hero.removeItem("Key");
            nextLevel=true;
        }
        else
        {
            boolean keepAsking=true;
            if(hero.hasHolocron() && keepAsking)
            {
                int choice=JOptionPane.showConfirmDialog(this,"Do you want to use your holocron","question",JOptionPane.YES_NO_OPTION);
                if(choice==JOptionPane.OK_OPTION)
                {
                    hero.removeItem("Holocron");
                    Random rand=new Random();
                    int openDoor=rand.nextInt(2);
                    if(openDoor==0)
                    {
                        nextLevel=true;
                        keepAsking=false;
                    }
                }
            }
        }
    }

    public void actionPerformed(ActionEvent a)
    {



        if(a.getSource()==fight)
        {
            fight.setVisible(false);
            runaway.setVisible(false);
            if(hero.hasHolocron())
            {
                force.setVisible(true);
            }

            blaster.setVisible(true);

        }
        if(a.getSource()==blaster)
        {
            hero.attack(enemy);
            fight();
            force.setVisible(false);
            if(enemy.hp>0)
            {
                fight.setVisible(true);
            }
        }
        if(a.getSource()==force)
        {
            hero.removeItem("Holocron");
            force.setVisible(false);
            blaster.setVisible(false);
            forceSlam.setVisible(true);
            forceShock.setVisible(true);
            forcePush.setVisible(true);
            medKit.setVisible(false);
        }
        if(a.getSource()==forceSlam)
        {
            enemy.takeDamage(hero.forceSlam());
            fight();
            if(enemy.getHP()>0)
            {
                runaway.setVisible(true);
                fight.setVisible(true);
            }
            forceSlam.setVisible(false);
            forceShock.setVisible(false);
            forcePush.setVisible(false);

        }
        if(a.getSource()==forceShock)
        {
            enemy.takeDamage(hero.forceChoke());
            fight();
            if(enemy.getHP()>0)
            {
                runaway.setVisible(true);
                fight.setVisible(true);
            }
            forceSlam.setVisible(false);
            forceShock.setVisible(false);
            forcePush.setVisible(false);
        }
        if(a.getSource()==forcePush)
        {
            enemy.takeDamage(hero.forcePush());
            fight();
            if(enemy.getHP()>0)
            {
                runaway.setVisible(true);
                fight.setVisible(true);
            }
            forceSlam.setVisible(false);
            forceShock.setVisible(false);
            forcePush.setVisible(false);
        }

        if (a.getSource()==runaway)
        {
            fighting=false;
            moved=false;
            Random rand=new Random();
            while(!moved)
            {
                int chooseDirection=rand.nextInt(4);
                if (chooseDirection==0)
                {
                    moveUp();
                }
                else if(chooseDirection==1 )
                {
                    moveDown();
                }
                else if(chooseDirection==2)
                {
                    moveRight();
                }
                else if(chooseDirection==3)
                {
                    moveLeft();
                }
            }
        }
        if(a.getSource()==medKit)
        {
            System.out.println("usedmedkit");
            hero.removeItem("Med Kit");
            hero.heal(25);
            enemy.attack(hero);
            if(hero.hasMedKit())
            {
                medKit.setVisible(true);
            }
            else
            {
                medKit.setVisible(false);
            }
            runaway.setVisible(true);
            fight.setVisible(true);
        }

    }
    public void mouseClicked(MouseEvent e)
    {
        Point click =new Point(e.getX(),e.getY());
        if(item1Click.contains(click))
        {
            System.out.println("you clicked button");
            if(removeItemMode)
            {
                hero.removeItem(1);
                hero.pickUpItem(item);
                fighting=false;
            }
        }
        if(item2Click.contains(click))
        {
            if(removeItemMode)
            {
                hero.removeItem(2);
                hero.pickUpItem(item);
                fighting=false;
            }
        }
        if(item3Click.contains(click))
        {
            if(removeItemMode)
            {
                hero.removeItem(3);
                hero.pickUpItem(item);
                fighting=false;
            }

        }
        if(item4Click.contains(click))
        {
            if(removeItemMode)
            {
                hero.removeItem(4);
                hero.pickUpItem(item);
                fighting=false;
            }


        }
        if(item5Click.contains(click))
        {
            if(removeItemMode)
            {
                hero.removeItem(5);
                hero.pickUpItem(item);
                fighting=false;
            }

        }

    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
