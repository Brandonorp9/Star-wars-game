
import java.util.*;

/**
 * The class Enemy sets an enemy.
 * @author brandon
 */
public abstract class  Enemy extends Entity
{
    /**
     * the item that the enemy is going to hold
     */
    private Item item;
    /**
     * It initiliazes the enemy y calling the super from Entity.
     * @param n  name of the enemy.
     * @param l  The level of the enemy.
     * @param m	 The maximum health of the enemy.
     * @param i  The item that the enemy has.
     */
    public Enemy(String n, int l,int m)
    {
        super(n,l,m);
        item=ItemGenerator.getInstance().generateItem();

    }
    /**
     * It attacks another Object based on a random number and level.
     * @param e the object that you are attacking.
     * @ return we return the damage that we have made in order to sum the damage.
     */
    public int attack(Entity e)
    {
        Random rand = new Random();
        int damage=(rand.nextInt(3)+1)*level;
        System.out.println(getName()+" hits "+e.getName()+" with a damage of "+damage);
        //it uses the function from entity to do the damage
        e.takeDamage(2);
        return 2;
    }
    /**
     * This function returns the item object
     * @return enemy item
     */
    public Item getitem()
    {
        return item;
    }


}