
/**
 * Represents the computer player in the game.
 * 
 * @author Mark Kreitler
 * @version 0.1
 */
// Include the package containing the Random object.
import java.util.*;

public class EnemyPlayer
{
    // Class Variables.
    private static final int AI_GRID_EMPTY      = 0;
    private static final int AI_GRID_TARGETED   = -99;

    // Instance Variables.
    private int targetRow;
    private int targetColumn;
    private Vector bag;
    private int[][] aiGrid;
    private int[] missWeights = {-2, -1};
    private int[] hitWeights = {3, 2, 1};

    // Methods.
    /**
     * Constructor for objects of class EnemyPlayer
     */
    public EnemyPlayer()
    {
        this.bag = new Vector();

        this.aiGrid = new int[Board.ROW_COUNT][Board.COLUMN_COUNT];
        this.initAIgrid();
    }

    /** Initialize the weights in the AI grid. */
    public void initAIgrid() {
        for (int i=0; i<Board.ROW_COUNT; i++) {
            for (int j=0; j<Board.COLUMN_COUNT; j++) {
                this.aiGrid[i][j] = AI_GRID_EMPTY;
            }
        }
    }

    /** Apply or undo the 3,2,1 pattern to the aiGrid. */
    public void applyHitWeights(int row, int col, boolean bUndo) {
        // First, fill the target square with -99.
        this.aiGrid[row][col] = AI_GRID_TARGETED;
        
        // Next, apply them upwards.
        for (int i=0; i<this.hitWeights.length; i++) {
            int r = row - 1 - i;
            if (r < 0) break;
            
            if (bUndo) {
                this.aiGrid[r][col] = this.aiGrid[r][col] - this.hitWeights[i];
            }
            else {
                this.aiGrid[r][col] = this.aiGrid[r][col] + this.hitWeights[i];
            }
        }
        
        // Next, apply them downwards.
        for (int i=0; i<this.hitWeights.length; i++) {
            int r = row + 1 + i;
            if (r >= Board.ROW_COUNT) break;
            
            if (bUndo) {
                this.aiGrid[r][col] = this.aiGrid[r][col] - this.hitWeights[i];
            }
            else {
                this.aiGrid[r][col] = this.aiGrid[r][col] + this.hitWeights[i];
            }
        }
        
        // Next, apply them to the right.
        for (int i=0; i<this.hitWeights.length; i++) {
            int c = col + 1 + i;
            if (c >= Board.COLUMN_COUNT) break;
            
            if (bUndo) {
                this.aiGrid[row][c] = this.aiGrid[row][c] - this.hitWeights[i];
            }
            else {
                this.aiGrid[row][c] = this.aiGrid[row][c] + this.hitWeights[i];
            }
        }
        
        // Finally, apply them to the left.
        for (int i=0; i<this.hitWeights.length; i++) {
            int c = col - 1 - i;
            if (c < 0) break;
            
            if (bUndo) {
                this.aiGrid[row][c] = this.aiGrid[row][c] - this.hitWeights[i];
            }
            else {
                this.aiGrid[row][c] = this.aiGrid[row][c] + this.hitWeights[i];
            }
        }
    }

    /** Apply the -2, -1 pattern to the aiGrid. */    
    public void applyMissWeights(int row, int col) {
        // First, fill the target square with -99.
        this.aiGrid[row][col] = AI_GRID_TARGETED;
        
        // Next, try to apply them upwards.
        for (int i=0; i<this.missWeights.length; i++) {
            int r = row - 1 - i;
            if (r < 0) break;
            
            this.aiGrid[r][col] = this.aiGrid[r][col] + this.missWeights[i];
        }
        
        // Next, apply them downwards.
        for (int i=0; i<this.missWeights.length; i++) {
            int r = row + 1 + i;
            if (r >= Board.ROW_COUNT) break;
            
            this.aiGrid[r][col] = this.aiGrid[r][col] + this.missWeights[i];
        }
        
        // Next, apply them to the right.
        for (int i=0; i<this.missWeights.length; i++) {
            int c = col + 1 + i;
            if (c >= Board.COLUMN_COUNT) break;
            
            this.aiGrid[row][c] = this.aiGrid[row][c] + this.missWeights[i];
        }
        
        // Finally, apply them to the left.
        for (int i=0; i<this.missWeights.length; i++) {
            int c = col - 1 - i;
            if (c < 0) break;
            
            this.aiGrid[row][c] = this.aiGrid[row][c] + this.missWeights[i];
        }
    }
    
    public void undoWeights(Ship ship) {
        int row = ship.getRow();
        int col = ship.getColumn();
        int size = ship.getSize();

        if (ship.getOrientation() == Ship.ORIENTATION_UP) {
            for (int i=0; i<size; i++) {
                this.applyHitWeights(row - i, col, true);
            }
        }
        else if (ship.getOrientation() == Ship.ORIENTATION_DOWN) {
            for (int i=0; i<size; i++) {
                this.applyHitWeights(row + i, col, true);
            }
        }
        else if (ship.getOrientation() == Ship.ORIENTATION_RIGHT) {
            for (int i=0; i<size; i++) {
                this.applyHitWeights(row, col + i, true);
            }
        }
        else if (ship.getOrientation() == Ship.ORIENTATION_LEFT) {
            for (int i=0; i<size; i++) {
                this.applyHitWeights(row, col - i, true);
            }
        }
    }

    /** Select a new target using weights based on prior hits and misses. */
    public void selectTarget() {
        /* Randomly pick targetRow and targetColumn. */
        long seed = System.currentTimeMillis();
        Random randomizer = new Random(seed);

        // Find the largest weight on the board.        
        int maxWeight = AI_GRID_TARGETED;
        for (int i=0; i<Board.ROW_COUNT; i++) {
            for (int j=0; j<Board.COLUMN_COUNT; j++) {
                if (this.aiGrid[i][j] > maxWeight) {
                    maxWeight = this.aiGrid[i][j];
                }
            }
        }

        // Create a Target for each square with the
        // maximum weight. Add these Targets to the
        // bag.
        for (int i=0; i<Board.ROW_COUNT; i++) {
            for (int j=0; j<Board.COLUMN_COUNT; j++) {
                if (this.aiGrid[i][j] == maxWeight) {
                    Target newTarget = new Target(i, j);
                    this.bag.addElement(newTarget);
                }
            }
        }

        // Pick a piece in the bag.
        int piecesInBag = this.bag.size();
        int piece = randomizer.nextInt(piecesInBag);
        Target target = (Target)this.bag.elementAt(piece);

        this.targetRow = target.row;
        this.targetColumn = target.column;

        // Empty the bag.
        this.bag.removeAllElements();
        
        // Encourage cleanup of old Targets.
        System.gc();
    }

    public int getTargetRow() {
        return this.targetRow;
    }

    public int getTargetColumn() {
        return this.targetColumn;
    }

    // Target Class =====================================================    
    private class Target {
        public int row;
        public int column;

        /** Constructor. */
        public Target(int row, int col) {
            this.row = row;
            this.column = col;
        }
    }
}
