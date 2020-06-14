
import java.util.*;

/*
 * for (Item s:items)


			{
				System.out.println(number+". "+s.getName());
				number++;
			}
	This code is a for each loop being the Items the list and s the objects in that list.
	It will go through each object one by one.

 */

/**
 * This class is the blueprint for the hero created.
 */
import java.awt.Point;
public class Hero extends Entity implements Force {
    private ArrayList<Item> items;
    private Map map;
    private Point location;

    /**
     * @param n name of the hero
     * @param m the map of the game you are going to play in.
     */
    public Hero(String n, Map m) {
        super(n, 1, 100);
        map = m;
        location = m.findStart();
        items = new ArrayList<>();
    }

    /**
     * This function lets us attack an enemy.
     *
     * @param e the enemy that you are going to attack.
     * @return we return the damage that we want to deal to the enemy
     */
    public int attack(Entity e) {
        //if he has a holocron we ask what type of attack to do.


        Random rand = new Random();
        int damage = (rand.nextInt(2) + 1) * level;

        e.takeDamage(damage);
        return 5;

    }

    /**
     * we call the super display class.
     */
    public void display() {
        super.display();
    }

    /**
     * This function displays the items that the hero has.
     */
    public void displayItems() {
        System.out.println("Inventory:");
        int number = 1;
        for (Item s : items) {
            System.out.println(number + ". " + s.getName());
            number++;
        }
    }

    /**
     * This function lets us know the number of item our hero has.
     *
     * @return the number of items the heroes has
     */
    public int getNumItems() {
        return items.size();
    }

    /**
     * This function lets us pick up items and lets us know if we have succeeded.
     *
     * @param i It is the Item that we want to remove.
     * @return true if we picked it up or false if we didn't.
     */
    public boolean pickUpItem(Item i) {
        if (items.size() < 5) {
            items.add(i);
            return true;
        }
        return false;
    }

    /**
     * This function lets us remove items from the heros list.
     *
     * @param n the name of the item we want to remove.
     */
    public void removeItem(String n) {
        int index = 0;

        for (Item s : items) {
            if (s.getName().equals(n)) {
                index = items.indexOf(s);
                break;

            }
        }
        items.remove(index);
    }

    /**
     * This function lets us remove items from the heros list.
     *
     * @param index the items's number we want to remove
     */
    public void removeItem(int index) {
        items.remove(index - 1);
    }

    /**
     * This removes our fist armor in our list.
     */
    public String removeFirstArmorItem() {
        int index = 0;
        String armor = "";


        for (Item s : items) {
            if (s.getName().equals("Shield") || s.getName().equals("Helmet") || s.getName().equals("Chestplate")) {
                armor = s.getName();
                index = items.indexOf(s);
            }
        }
        items.remove(index);
        return armor;
    }

    /**
     * @return true if we have a med kit and false if we don't.
     */

    public boolean hasMedKit() {
        boolean contains = false;
        for (Item s : items) {

            if (s.getName().equals("Med Kit")) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * @return true if we have a Holocron and false if we don't.
     */
    public boolean hasHolocron() {
        boolean contains = false;
        for (Item s : items) {

            if (s.getName().equals("Holocron")) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * @return true if we have a key and false if we don't
     */
    public boolean hasKey() {
        boolean contains = false;
        for (Item s : items) {
            if (s.getName().equals("Key")) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * @return true if we have an Armor and false if we don't
     */
    public boolean hasArmor() {
        boolean contains = false;
        for (Item s : items) {
            if (s.getName().equals("Helmet") || s.getName().equals("Shield") || s.getName().equals("Chestplate")) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * @return the location point of the hero
     */
    public Point getLocation() {
        return location;
    }

    /**
     * this function lets the hero move north;
     *
     * @return the character stored in the location of where we are going
     */
    public char goNorth() {    // we reveal the map location and then we move.
        map.reveal(location);
        Point destination = new Point((int) location.getX(), (int) location.getY() - 1);
        location = destination;
        return map.getCharAtLoc(destination);

    }

    /**
     * this function lets the hero move north;
     *
     * @return the character stored in the location of where we are going
     */
    public char goSouth() {    // we reveal the map location and then we move.
        map.reveal(location);
        Point destination = new Point((int) location.getX(), (int) location.getY() + 1);
        location = destination;
        return map.getCharAtLoc(destination);

    }

    /**
     * this function lets the hero move north;
     *
     * @return the character stored in the location of where we are going
     */
    public char goEast() {    // we reveal the map location and then we move.
        map.reveal(location);
        Point destination = new Point((int) location.getX() + 1, (int) location.getY());
        location = destination;
        return map.getCharAtLoc(destination);
    }

    /**
     * this function lets the hero move north;
     *
     * @return the character stored in the location of where we are going
     */
    public char goWest() {    // we reveal the map location and then we move.
        map.reveal(location);
        Point destination = new Point((int) location.getX() - 1, (int) location.getY());
        location = destination;
        return map.getCharAtLoc(destination);
    }

    /**
     * we are defining a function that we implement from the force interface.
     *
     * @return we return a random number multiple of the level.
     */
    public int forcePush() {
        Random rand = new Random();
        return (rand.nextInt(3) + 1) * level;
    }

    /**
     * we are defining a function that we implement from the force interface.
     *
     * @return we return a random number multiple of the level.
     */
    public int forceChoke() {
        Random rand = new Random();
        return (rand.nextInt(3) + 1) * level;
    }

    /**
     * we are defining a function that we implement from the force interface.
     *
     * @return we return a random number multiple of the level.
     */
    public int forceSlam() {
        Random rand = new Random();
        return (rand.nextInt(3) + 1) * level;
    }

    public String getFirstItem() {
        if (items.size() > 0) {
            return "" + items.get(0).getName().charAt(0);
        }
        return "";
    }

    public String getSecondItem() {
        if (items.size() > 1) {
            return "" + items.get(1).getName().charAt(0);
        }
        return "";
    }

    public String getThirdItem() {
        if (items.size() > 2) {
            return "" + items.get(2).getName().charAt(0);
        }
        return "";
    }

    public String getFourthItem() {
        if (items.size() > 3) {
            return "" + items.get(3).getName().charAt(0);
        }
        return "";
    }

    public String getFifthItem() {
        if (items.size() > 4) {
            return "" + items.get(4).getName().charAt(0);
        }
        return "";
    }


}

