import javax.swing.*;
/**
 * we have our window class in order to create a window
 * @author brand
 *
 */
public class Window extends JFrame
{
    /**
     * here we initialize the window by given its size title and other properties.
     */
    public Window()
    {
        setBounds(400,100,800,650);
        setTitle("game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel p=new Panel();
        setContentPane(p);
        Thread t=new Thread(p);
        t.start();
        setVisible(true);
    }
}
