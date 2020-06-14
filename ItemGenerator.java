
import java.util.*;
import java.io.*;
/**
 * Class ItemGenerator-Creates items.
 * ItemGenerator is a singleton.
 */
public class ItemGenerator
{
    /**
     * we create the itemgenrator instance and set to null
     * we also have a list in order to get the items.
     */
    private static ItemGenerator instance=null;
    private ArrayList<Item> itemList;

    /**
     * This method reads the items string and adds the object created to a list.
     * @throws throws an exception if file not found.
     */
    private ItemGenerator()
    {
        itemList =new ArrayList<Item>();
        try
        {
            Scanner read =new Scanner(new File("Items.txt"));
            while(read.hasNext())
            {
                String text =read.nextLine();
                Item item_read=new Item(text);
                itemList.add(item_read);

            }
            read.close();
        }
        catch(FileNotFoundException fnf)
        {
            System.out.println("intem");
            System.out.println("File Not Found");
        }
    }

    public static ItemGenerator getInstance()
    {
        if(instance==null)
        {
            instance =new ItemGenerator();
        }
        return instance;
    }


    /**
     * this method creates a random item.
     * @return The item object created.
     */
    public Item generateItem()
    {	Random rand = new Random();
        /*creates an random number from zero to item list size
         * then i creates a random item with that number
         */
        int rand_number=rand.nextInt(itemList.size());
        return itemList.get(rand_number);
    }
}
