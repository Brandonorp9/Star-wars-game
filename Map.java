
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This is the blueprint for the map.
 * @author brandon
 */
public class Map {
    private static Map instance = null;
    private char[][] map;
    private boolean[][] revealed;

    /**
     * we create the map and reveal array.
     */
    private Map() {
        map = new char[5][5];
        revealed = new boolean[5][5];

    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;

    }


    /**
     * In this function we load the map and set reveal to false.
     *
     * @param mapNum It determines what map we are going to load.
     */
    public void loadMap(int mapNum) {
        int x = 0;
        int y = 0;
        try {
            Scanner read = new Scanner(new File("map" + mapNum + ".txt"));
            while (read.hasNext()) {
                char text = read.next().charAt(0);
                map[x][y] = text;
                x++;
                if (x > 4) {
                    x = 0;
                    y++;
                }
            }
            read.close();
        } catch (FileNotFoundException fnf) {
            System.out.println("map");
            System.out.println("File Not Found");
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                revealed[i][j] = false;
            }
        }

    }

    /**
     * @param p the position of the hero.
     * @return the character of the map in the position given.
     */
    public char getCharAtLoc(Point p) {
        return map[(int) p.getX()][(int) p.getY()];
    }

    /**
     * In this function we display the map according to reveal and the position of the hero.
     *
     * @param p the point of the hero in other to determine his space on the map.
     */
    public void displayMap(Point p) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == (int) p.getX() & i == (int) p.getY()) {
                    System.out.print("* ");
                } else if (revealed[i][j] == false) {
                    System.out.print("X ");
                } else if (revealed[i][j] == true) {
                    System.out.print(map[i][j] + " ");
                }

            }
            System.out.println();
        }

    }

    /**
     * @return the location of the letter s in the map.
     */
    public Point findStart() {
        Point pstart = new Point();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (map[i][j] == 's') {
                    pstart.setLocation(i, j);
                    break;
                }

            }
        }
        return pstart;
    }

    /**
     * This function lets us modify the real array to changed the mapped showed.
     *
     * @param p A location to turn the reveal array in the same position to true;
     */
    public void reveal(Point p) {
        revealed[(int) p.getX()][(int) p.getY()] = true;
    }

    /**
     * This function modifies the map
     *
     * @param p the location of the hero in order to change map character
     */
    public void removeCharAtLoc(Point p) {
        map[(int) p.getX()][(int) p.getY()] = 'n';
    }

    /**
     * @param x location of x in the map
     * @param y the location of y in the map
     * @return we return the character in the map.
     */
    public String getmap(int x, int y) {
        if (revealed[x][y] == false) {
            return "";
        }

        return "" + map[x][y];

    }


}