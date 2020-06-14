
/**
 * This interface lets us implement the functions below in ForceEnemy and Hero.
 * @author brandon
 */
public interface Force {
    static final String FORCE_MENU="1.Force Push\n2.Force Choke\n3.Force Slam";
    /**
     * This function is in an interface that is why it is not define
     * @return it return a number that is need to determine the attack
     */
    public int forcePush();
    /**
     * This function is in an interface that is why it is not define
     * @return it return a number that is need to determine the attack
     */
    public int forceChoke();
    /**
     * This function is in an interface that is why it is not define
     * @return it return a number that is need to determine the attack
     */
    public int forceSlam();
}
