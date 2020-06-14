import java.util.Random;

/**
 * We have the forceUser class that extends enemy decorator in order to modify an enemy.
 * @author brand
 *
 */
public class ForceUser extends  EnemyDecorator
{
    /**
     * @param e we have passed and enemy type  in order to pass it to enemy decorator.
     */
    public ForceUser(Enemy e)
    {
        super(e.getName(),e.getLevel(),e.getMaxHp()+2,e);
    }

    /**
     * @param the heroe is the entity that we are going to attack
     * @ return we return the damage that we have dealt to the hero
     */
    public int attack(Entity heroe)
    {
        System.out.println("force attack");
        Random rand=new Random();
        int chooseAttack=rand.nextInt(3);
        if(chooseAttack==0)
        {
            heroe.takeDamage(forcePush());
        }
        else if (chooseAttack==1)
        {
            heroe.takeDamage(forceChoke());
        }
        else
        {
            heroe.takeDamage(forceSlam());
        }


        return 3+ super.attack(heroe);
    }

    /**
     * here we modify the forcePush attack
     * @return we return the damage made
     */
    public int forcePush()
    {
        return 3;
    }
    /**
     * here we modify the forceChoke attack
     * @return we return the damage made
     */
    public int forceChoke()
    {
        return 3;
    }
    /**
     * here we modify the forceSlam attack
     * @return we return the damage made
     */
    public int forceSlam()
    {
        return 3;
    }

    /**
     * here we modify the toString method in order to get the enemy name
     */
    public String toString()
    {
        return "ForceUser"+this.name;
    }
}