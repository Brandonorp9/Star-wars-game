
/**
 * Here we have the class enemyDecorator class which help us create the new type of enemy.
 * @author brand
 *
 */

public abstract class EnemyDecorator extends Enemy
{
    /**
     * This is the enemy parameter which we made private in
     */
    private  Enemy enemy;

    /**
     *
     * @param name
     * @param level
     * @param maxHp
     * @param e
     */
    public EnemyDecorator(String name,int level,int maxHp,Enemy e)
    {
        super(name,level,maxHp);
        enemy=e;
    }

    /**
     * @param hero we have the Entity type that we want to make damage.
     * @return we return the damage we have done to hero
     */
    public int  attack(Entity hero)
    {
        int a=enemy.attack(hero);
        return a;
    }

}