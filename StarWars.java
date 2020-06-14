public class StarWars
{

    public static void main (String [] args)
    {
        Map map=Map.getInstance();
        Hero heroe=new Hero("brandon",map);
        Enemy first=EnemyGenerator.getInstance().generateEnemy(5);
        System.out.println(first);
        System.out.println(first.getLevel());
        System.out.println(first.getitem());
        System.out.println(first.getMaxHp());
        System.out.println(first.attack(heroe));




        Window window=new Window();
    }

}
