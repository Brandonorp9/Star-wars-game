
import java.util.*;
/**
 *The class EnemyGenerator sets how to create a random enemy.
 * @author brandon
 */
public class EnemyGenerator
{


    private static EnemyGenerator instance =null;

    /**We read a list from the file then.
     * We create enemies to store them in the enemyList.
     */
    private EnemyGenerator()
    {

    }

    public static EnemyGenerator getInstance()
    {
        if(instance==null)
        {
            instance=new EnemyGenerator();
        }
        return instance;
    }

    /**
     * This function generates an enemy picked at random.
     * @param level	It determines the level of enemy and health.
     * @return Enemy we return the enemy that we have created.
     */
    public Enemy generateEnemy(int level)
    {
        Random rand=new Random();
        int chooseEnemy=rand.nextInt(4);

        int maxHealth=5;

        Enemy enemy=null;

        //we create a random enemy
        if(chooseEnemy==0)
        {
            enemy= new Dathomiri(level,level*maxHealth);
        }
        else if(chooseEnemy==1)
        {
            enemy=new Geonosian(level,level*maxHealth);
        }
        else if(chooseEnemy==2)
        {
            enemy=new Rodian(level,level*maxHealth);
        }
        else if(chooseEnemy == 3)
        {
            enemy=new Twilek(level,level*maxHealth);
        }

        //decorate enemy
        int specialEnemy=rand.nextInt(2);

        if(specialEnemy==0)
        {

            for(int i=level;i>1;i--)
            {
                enemy=new ForceUser(enemy);
            }
            return enemy;
        }

        for(int i=level;i>1;i--)
        {
            enemy=new Fighter(enemy);
        }
        return enemy;
    }
}