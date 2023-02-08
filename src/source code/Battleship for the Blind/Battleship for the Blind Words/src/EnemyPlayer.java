
// Include the package containing the Random object.
import java.util.*;
public class EnemyPlayer
{
    // Instance Variables.
    public static int Row;
    public static int Col;
    private Vector bag;

    
    // Methods.
    /**
     * Constructor for objects of class EnemyPlayer
     */
    public EnemyPlayer() {

    }


	public static void EnemyPlayer() {
        long seed = System.currentTimeMillis();
        Random randomizer = new Random(seed + seed + seed);

        int row = Row;
        int col = Col;
        
        Row = randomizer.nextInt(Board.ROW_COUNT);
        Col = randomizer.nextInt(Board.COLUMN_COUNT);
        System.out.println(row + ":" + col);
    }
	}
    
        