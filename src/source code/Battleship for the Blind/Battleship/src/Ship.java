/**
 * Represents a ship on the board.
 *
 * @author Mark Kreitler
 * @version 0.1
 */
public class Ship {
    // Class variables.
    public static final int TYPE_NONE = -1;
    public static final int TYPE_AIRCRAFT_CARRIER = 0;
    public static final int TYPE_BATTLESHIP = 1;
    public static final int TYPE_CRUISER = 2;
    public static final int TYPE_SUBMARINE = 3;
    public static final int TYPE_PT_BOAT = 4;
    public static final int ORIENTATION_UP = 0;
    public static final int ORIENTATION_RIGHT = 1;
    public static final int ORIENTATION_DOWN = 2;
    public static final int ORIENTATION_LEFT = 3;
    
    // Instance variables.
    private int type;
    private int orientation;
    private int row;
    private int column;
    private int size;
    private int hitsTaken;
    
    /**
     * Constructor for objects of class Ship
     */
    public Ship(int shipType, int dir, int row, int col, int length) {
        this.type = shipType;
        this.orientation = dir;
        this.row = row;
        this.column = col;
        this.size = length;
        this.hitsTaken = 0;
    }
    
    /**
     *  Returns the ship's type.
     */
    public int getType() {
        return this.type;
    }
    
    /**
     *  Returns the ship's orientation.
     */
    public int getOrientation() {
        return this.orientation;
    }
    
    /**
     *  Returns the ship's size (length).
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     *  Returns the ship's row.
     */
    public int getRow() {
        return this.row;
    }
    
    /**
     *  Returns the ship's column.
     */
    public int getColumn() {
        return this.column;
    }
    
    /**
     * Take damage from an enemy shot.
     */
    public void takeHit() {
        this.hitsTaken++;
    }
    
    /** Return the amount of damage. */
    public int getHits() {
        return this.hitsTaken;
    }
}
