
/**
 * here we created the class Dathomiri which is a base enemy that extends enemy
 * @author brand
 *
 */
public class Dathomiri extends Enemy
{
    /**
     * @param level here we pass the level of the enemy
     * @param maxHp here we pass the maxHp of the enemy
     * Description we are initializing the base enemy Dathomiri.
     */
    public Dathomiri(int level,int maxHp)
    {

        super("Dathomimri",level,maxHp);
    }

    /**
     * @param e we are passing the Entity that we are going to deal damage to the Entity
     * @return we return the damage in order to sum the attacks.
     * Description here we are doing damage to entity and also returning the damage.
     */
    public int attack(Entity e)
    {
        System.out.println("final attack");
        e.takeDamage(2);
        return 2;
    }
}
