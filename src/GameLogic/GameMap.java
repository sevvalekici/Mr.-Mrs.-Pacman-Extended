package GameLogic;

/** Game map of pacman
 * @author Ecem Ilgun
 * @version 1.8
 * @since 1.0
 */
public class GameMap {
    //Variables
    private static final int GRID_SIZE = 28;
    private int[][] foodMap, wallMap;
    public int[][] map1 = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,3,2,2,2,1,2,2,2,2,2,2,2,2,1,2,2,2,3,1},
            {1,2,1,1,2,1,1,2,1,1,1,1,1,2,1,2,1,1,2,1},
            {1,2,1,4,2,2,2,2,2,2,2,2,2,2,2,2,5,1,2,1},
            {1,2,1,2,1,1,2,1,1,2,1,1,1,1,1,1,2,1,2,1},
            {1,2,2,2,2,2,2,1,0,0,0,0,1,2,2,2,2,2,2,1},
            {1,2,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1},
            {1,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,4,1,2,1},
            {1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,2,1},
            {1,3,2,2,2,2,2,2,2,2,2,2,2,2,1,2,2,2,3,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
};

    //Constructor(s)
    /**
     * Creates the default pacman map
     */
    public GameMap() {
        
    }


    //Methods


}
