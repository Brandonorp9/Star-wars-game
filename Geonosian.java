
/**
 * Here we have our Genosian class which is a base type of enemy.
 * @author brand
 *
 */
public class Geonosian extends Enemy
{
    /**
     * we initialize the level and health of our base enemy
     * @param level the level we want our hero to be
     * @param maxHp the maxhp that we want our enemy to have
     */
    public Geonosian(int level,int maxHp)
    {
        super("Geonosian",level,maxHp);
    }

    /**
     * @param pass the Entity which we which to do damage to.
     * @return we are returning the damage we which to make.
     */
    public int attack(Entity e)
    {
        System.out.println("final attack");
        e.takeDamage(2);
        return 2;
    }
}