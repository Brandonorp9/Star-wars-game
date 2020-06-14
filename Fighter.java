
/**
 * we create the Fighter type that extends from enemy Decorator
 * @author brand
 *
 */
public class Fighter extends  EnemyDecorator
{
    /**
     * @param e we pass the enemy type that we are going to decorate
     */
    public Fighter(Enemy e)
    {
        super(e.getName(),e.getLevel(),e.getMaxHp()+1,e);
    }

    /**
     * @param heroe we pass the heroe that we are going to attack
     * Description we use attack damage to lower heroe health and we also return the amount to add it at the end.
     */
    public int attack(Entity heroe)
    {
        System.out.println("attack");
        heroe.takeDamage(2);

        return 2 +super.attack(heroe);
    }
    /**
     * we modify toString in order to print the fighters name;
     */
    public String toString()
    {
        return "Fighter"+this.name;
    }
}