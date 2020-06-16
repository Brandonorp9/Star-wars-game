
/**
 * Entity class-characteristics and functionality of an entity.
 * @author brandon
 */
public abstract class Entity
{
    String name;
    int level;
    int maxHp;
    int hp;

    /** We intiailze the entity.
     * @param name	name of the entity
     * @param level	level of the entity
     * @param maxHp	the max health of the entity
     */
    public Entity(String name,int level,int maxHp)
    {
        this.name=name;
        this.level=level;
        this.maxHp=maxHp;
        this.hp=maxHp;
    }
    public Entity()
    {

    }

    /**
     * Each type of entity will develop their own attack
     * @param e enemy that you are going to attack
     */
    public abstract int attack(Entity e);
    String getName()
    {
        return name;
    }

    /**
     * This method lets us get the level of the object
     * @return	return an integer, which will be the level
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * This function lets us get the health of the object
     * @return returns the health of the entity
     */
    public int getHP()
    {
        return hp;
    }

    /**
     * lets us obtain the health of the object.
     * @return	returns the max health the entity can have
     */
    public int getMaxHp()
    {
        return maxHp;
    }

    /**
     * it increases the level of the entity by 1.
     */
    public void increaseLevel()
    {
        level=level+1;
    }

    /**
     * it increases the enity's health
     * @param h	the amount i want to increase the health by
     */
    public void heal(int h)
    {
        if (maxHp<hp+h)
        {
            hp=maxHp;
        }
        else
        {
            hp=hp+h;
        }
    }

    /**
     *It lets us reduce the health.
     * @param h	the amount of damage received
     */
    public void takeDamage(int h)
    {
        hp=hp-h;
        if (hp<0)
        {
            hp=0;
        }
    }

    /**
     *It increases the max health
     * @param h		the amount that max health should be increased by
     */
    public void increaseMaxHp(int h)
    {
        maxHp=maxHp+h;
    }

    /**
     *It decreases the max health
     * @param h	: the amount that the max health should be decreased
     */
    public void decreaseMaxHp(int h)
    {
        maxHp=maxHp-h;
    }

    /**
     * Lets us display information of the entity .
     */
    public void display()
    {
        System.out.println(name+"Lvl:"+level);
        System.out.println("HP:"+hp+"/"+maxHp);
    }

}