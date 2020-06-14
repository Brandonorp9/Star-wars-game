
/**
 * Item class- Representation of an item.
 * @author brandon
 */
public class Item
{
    String name;
    /**
     * @param n initializes the name of the item
     */
    public Item(String n)
    {
        name=n;
    }
    /**
     * @return the name of the item
     */
    public String getName()
    {
        return name;
    }
    /**
     * Description we modify the toString function in order to print out the item name;
     */
    public String toString()
    {
        return name;
    }

}
