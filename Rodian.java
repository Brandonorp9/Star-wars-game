
/**
 * we have the class Rodian which is a base enemy that we can decorate
 * @author brand
 *
 */
public class Rodian extends Enemy
{
    /**
     * Description here we have our initializer of the base enemy
     * @param level we pass the level we want our enemy to have
     * @param maxHp we also pass the maxHp that our enemy must have.
     */
    public Rodian(int level,int maxHp)
    {
        super("Rodian",level,maxHp);
    }

    /**
     *  @param e It is the entity that we want to attack
     *  @return here we return the damage we have done to the enemy
     */
    public int attack(Entity e)
    {
        System.out.println("final attack");
        e.takeDamage(2);
        return 2;
    }

}