
/**
 * Here we the have class Twilek that is the base enemy .
 * @author brand
 *
 */
public class Twilek extends Enemy
{
    /**
     * @param level The level we want our hero to be
     * @param maxHp the maxhp that we want our enemy to have
     */
    public Twilek(int level,int maxHp)
    {
        super("Twilek",level,maxHp);
    }

    /**
     * @param we have our e Entity that we wish to attack
     * @ return we return the damage that we have done to the entity
     */
    public int attack(Entity e)
    {
        System.out.println("final attack");
        e.takeDamage(2);
        return 2;
    }
}
