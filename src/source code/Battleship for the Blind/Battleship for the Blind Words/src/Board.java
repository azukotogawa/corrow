import java.awt.*;
import java.util.*;

public class Board
{
    // Class variables.
    public static final int ROW_COUNT = 10;
    public static final int COLUMN_COUNT = 10;
    public static final int ROW_PIXEL_HEIGHT = 20;
    public static final int COLUMN_PIXEL_WIDTH = 20;
    private static final int SHIPS_PER_FLEET = 5;
    public static final int TYPE_PEG_WHITE     = -2;
    public static final int TYPE_PEG_RED       = -3;
    
    public static int rowY;
    public static int colX;

    // Instance variables.
    private RedPeg[] hitMarkers;
    private WhitePeg[] missMarkers;
    private Ship[] fleet;
    private int[][] gridCells;
    
    

    // Methods.
    /**
     * Constructor for objects of class Board
     */
    public Board()
    {
        // Initialize the arrays used to store ship info.
        this.fleet = new Ship[SHIPS_PER_FLEET];
        this.gridCells = new int[ROW_COUNT][COLUMN_COUNT];
        
        // Fill the grid cells with empty spaces.
        int i = 0;
        while (i < ROW_COUNT) {
            int j = 0;
            while (j < COLUMN_COUNT) {
                this.gridCells[i][j] = Ship.TYPE_NONE;
                j++;
            }
            i++;
        }
    }
   
    
    public boolean isUnpegged(int row, int col) {
        boolean bIsUnpegged = true;
     
        int squareContents = this.gridCells[row][col];
        if (squareContents == TYPE_PEG_WHITE ||
            squareContents == TYPE_PEG_RED) {
            bIsUnpegged = false;
        }
     
        return bIsUnpegged;
    }
    
    public boolean isShip(int row, int col) {
        boolean bIsShip = false;
     
        int squareContents = this.gridCells[row][col];
        if (squareContents != Ship.TYPE_NONE) {
            bIsShip = true;
        }
        if (squareContents == Ship.TYPE_NONE) {
            bIsShip = false;
        }
     
        return bIsShip;
    }
    
    public boolean isShipb(int row, int col) {
        boolean bIsShipb = false;
     
        int squareContents = this.gridCells[row][col];
        if (squareContents != Ship.TYPE_NONE) {
        	System.out.println("HELLO!!!");
            bIsShipb = true;
        }
        if (squareContents == Ship.TYPE_NONE) {
        	System.out.println("HELLO???");
            bIsShipb = false;
        }
     
        return bIsShipb;
    }
   
    /** Check target square to see if it contains a ship. */
    public boolean didHitShip(int row, int col) {
        return this.gridCells[row][col] >= 0;
    }
    
    /** Fill square with a peg. */
    public void putPegInSquare(int row, int col, boolean bRed) {
    	
    	if (bRed) {
            this.gridCells[row][col] = TYPE_PEG_RED;
        }
        else {
            this.gridCells[row][col] = TYPE_PEG_WHITE;
    }    
    }
    
    public void placeComputerShips() {
        long seed = System.currentTimeMillis();
        Random randomizer = new Random(seed);
            
        int [] shipType = {Ship.TYPE_AIRCRAFT_CARRIER,
                           Ship.TYPE_BATTLESHIP,
                           Ship.TYPE_CRUISER,
                           Ship.TYPE_SUBMARINE,
                           Ship.TYPE_PT_BOAT};
        int[] shipLength = {5, 4, 3, 3, 2};
            
        int i = 0;
        
        do {
            int row = rowY;
            int col = colX;
            int orientation;
                
            // Randomly generate a row, column, and
            // orientation.
            rowY = randomizer.nextInt(ROW_COUNT);
            colX = randomizer.nextInt(COLUMN_COUNT);
            
            orientation = randomizer.nextInt(4);
            
            
                
            // Check to see if the ship fits on the
            // board at the given row and column.
            boolean bFitsOnBoard = false;
            int testLength = shipLength[i] - 1;
                
            if (orientation == Ship.ORIENTATION_UP) {
                if (row >= testLength) {
                    bFitsOnBoard = true;
                }
            }
            else if (orientation == Ship.ORIENTATION_RIGHT) {
                if (COLUMN_COUNT - col > testLength) {
                    bFitsOnBoard = true;
                }
            }
            else if (orientation == Ship.ORIENTATION_DOWN) {
                if (ROW_COUNT - row > testLength) {
                    bFitsOnBoard = true;
                }
            }
            else if (orientation == Ship.ORIENTATION_LEFT) {
                if (col >= testLength) {
                    bFitsOnBoard = true;
                }
            }
                
            boolean bHitsOtherShips = false;
            // Check to see if the ship hits any
            // other ships on the board.

            if (bFitsOnBoard == true) {
                int j;
                if (orientation == Ship.ORIENTATION_UP) {
                    j = 0;
                    while (j < shipLength[i]) {
                        if (this.gridCells[row - j][col] !=
                            Ship.TYPE_NONE) {
                            bHitsOtherShips = true;
                            break;
                        }
                            
                        j++;
                    }
                }
                else if (orientation == Ship.ORIENTATION_RIGHT) {
                    j = 0;
                    while (j < shipLength[i]) {
                        if (this.gridCells[row][col + j] !=
                            Ship.TYPE_NONE) {
                            bHitsOtherShips = true;
                            break;
                        }
                            
                        j++;
                    }
                }
                else if (orientation == Ship.ORIENTATION_DOWN) {
                    j = 0;
                    while (j < shipLength[i]) {
                        if (this.gridCells[row + j][col] !=
                            Ship.TYPE_NONE) {
                            bHitsOtherShips = true;
                            break;
                        }
                            
                        j++;
                    }
                }
                else if (orientation == Ship.ORIENTATION_LEFT) {
                    j = 0;
                    while (j < shipLength[i]) {
                        if (this.gridCells[row][col - j] !=
                            Ship.TYPE_NONE) {
                            bHitsOtherShips = true;
                            break;
                        }
                            
                        j++;
                    }
                }
            }
                    
            if (bFitsOnBoard && !bHitsOtherShips) {
                // Place this ship on the board.
                Ship newShip = new Ship(shipType[i],
                                        orientation,
                                        row,
                                        col,
                                        shipLength[i]);
                this.addShip(newShip);
                    
                // Go on to the next ship.
                i++;
            }
                
        } while (i < SHIPS_PER_FLEET);
    }

    
    /**
     *  Add a ship to the grid.
     */
    public void addShip(Ship newShip) {
        int row = newShip.getRow();
        int col = newShip.getColumn();
        int orientation = newShip.getOrientation();
        int i = 0;
        
        // Add the ship to the fleet array.
        this.fleet[newShip.getType()] = newShip;
        
        if (orientation == Ship.ORIENTATION_UP) {
            while (i < newShip.getSize()) {
                this.gridCells[row - i][col] = newShip.getType();
                i++;
            }
        }
        else if (orientation == Ship.ORIENTATION_RIGHT) {
            while (i < newShip.getSize()) {
                this.gridCells[row][col + i] = newShip.getType();
                i++;
            }
        }
        else if (orientation == Ship.ORIENTATION_DOWN) {
            while (i < newShip.getSize()) {
                this.gridCells[row + i][col] = newShip.getType();
                i++;
            }
        }
        else {
            // Orientation must be LEFT.
            while (i < newShip.getSize()) {
                this.gridCells[row][col - i] = newShip.getType();
                i++;
            }
        }
    }

    /**
     *  Draws the ships to the grid.
     */
    /**
     * Draws the ships to the grid.
     **/
     public void drawShips(Graphics gfx, Color shipColor, int startX, int startY) {
        int row = 0;    // Start at the first row.
        do {
            
            int col = 0;// Start at the first column.
            do {

            // Is the cell empty?
            if (this.gridCells[row][col] != Ship.TYPE_NONE) {
                // No--the cell contains part of a ship or a peg.
                if (this.gridCells[row][col] == TYPE_PEG_WHITE) {
                    gfx.setColor(Color.white);
                }
                else if (this.gridCells[row][col] == TYPE_PEG_RED) {
                    gfx.setColor(Color.red);
                }
                else gfx.setColor(shipColor);
                    
                // Calculate the starting position of the cell.
                int x = startX + col * COLUMN_PIXEL_WIDTH;
                int y = startY + row * ROW_PIXEL_HEIGHT;

                // Draw in a box that's smaller than the cell.
                gfx.fillRect(x + 2, y + 2,
                            COLUMN_PIXEL_WIDTH - 3,
                            ROW_PIXEL_HEIGHT - 3);
            }
                
            col++;  // Move on to the next column.
        } while (col < COLUMN_COUNT); 
            
            row++;      // Move on to the next row.
     } while (row < ROW_COUNT);
  }
    /**
     * Draws a grid on the screen.
     */
    public void drawGrid(Graphics gfx, Color gridColor, int startX, int startY) {
        // Set the line color.
        gfx.setColor(gridColor);

        // Draw the horizontal lines.
        int lineCounter = 1;
        int x = startX;
        int y = startY;
        while (lineCounter <= 11) {
            gfx.drawLine(x, y, x + COLUMN_PIXEL_WIDTH * COLUMN_COUNT, y);
            y = y + ROW_PIXEL_HEIGHT;
            lineCounter++;
        }

        // Draw the vertical lines.
        lineCounter = 1;
        x = startX;
        y = startY;
        while (lineCounter <= 11) {
            gfx.drawLine(x, y, x, y + ROW_PIXEL_HEIGHT * ROW_COUNT);
            x = x + COLUMN_PIXEL_WIDTH;
            lineCounter++;
        }
    }
    
    
        public void placePlayerShips() {
            long seed = System.currentTimeMillis();
            Random randomizer = new Random(seed * 10000000 / 54);
            
            int [] shipType = {Ship.TYPE_AIRCRAFT_CARRIER,
                    		   Ship.TYPE_BATTLESHIP,
                    		   Ship.TYPE_CRUISER,
                    		   Ship.TYPE_SUBMARINE,
                    		   Ship.TYPE_PT_BOAT};
            int[] shipLength = {5, 4, 3, 3, 2};
            
            int i = 0;
            do {
                int row;
                int col;
                int orientation;
                
                // Randomly generate a row, column, and
                // orientation.
                row = randomizer.nextInt(ROW_COUNT);
                col = randomizer.nextInt(COLUMN_COUNT);
                orientation = randomizer.nextInt(4);
                    
                // Check to see if the ship fits on the
                // board at the given row and column.
                boolean bFitsOnBoard = false;
                int testLength = shipLength[i] - 1;
                    
                if (orientation == Ship.ORIENTATION_UP) {
                    if (row >= testLength) {
                        bFitsOnBoard = true;
                    }
                }
                else if (orientation == Ship.ORIENTATION_RIGHT) {
                    if (COLUMN_COUNT - col > testLength) {
                        bFitsOnBoard = true;
                    }
                }
                else if (orientation == Ship.ORIENTATION_DOWN) {
                    if (ROW_COUNT - row > testLength) {
                        bFitsOnBoard = true;
                    }
                }
                else if (orientation == Ship.ORIENTATION_LEFT) {
                    if (col >= testLength) {
                        bFitsOnBoard = true;
                    }
                }
                    
                boolean bHitsOtherShips = false;
                // Check to see if the ship hits any
                // other ships on the board.
        
                if (bFitsOnBoard == true) {
                    int j;
                    if (orientation == Ship.ORIENTATION_UP) {
                        j = 0;
                        while (j < shipLength[i]) {
                            if (this.gridCells[row - j][col] !=
                                Ship.TYPE_NONE) {
                                bHitsOtherShips = true;
                                break;
                            }
                                
                            j++;
                        }
                    }
                    else if (orientation == Ship.ORIENTATION_RIGHT) {
                        j = 0;
                        while (j < shipLength[i]) {
                            if (this.gridCells[row][col + j] !=
                                Ship.TYPE_NONE) {
                                bHitsOtherShips = true;
                                break;
                            }
                                
                            j++;
                        }
                    }
                    else if (orientation == Ship.ORIENTATION_DOWN) {
                        j = 0;
                        while (j < shipLength[i]) {
                            if (this.gridCells[row + j][col] !=
                                Ship.TYPE_NONE) {
                                bHitsOtherShips = true;
                                break;
                            }
                                
                            j++;
                        }
                    }
                    else if (orientation == Ship.ORIENTATION_LEFT) {
                        j = 0;
                        while (j < shipLength[i]) {
                            if (this.gridCells[row][col - j] !=
                                Ship.TYPE_NONE) {
                                bHitsOtherShips = true;
                                break;
                            }
                                
                            j++;
                        }
                    }
                }
                        
                if (bFitsOnBoard && !bHitsOtherShips) {
                    // Place this ship on the board.
                    Ship newShip = new Ship(shipType[i],
                                            orientation,
                                            row,
                                            col,
                                            shipLength[i]);
                    this.addShip(newShip);
                        
                    // Go on to the next ship.
                    i++;
                }
                    
            } while (i < SHIPS_PER_FLEET);
        }
        
        /**
         *  Draws the ships to the grid.
         */
        public void drawShips1(Graphics gfx, Color shipColor, int startX, int startY) {
            // Set the draw color.
            gfx.setColor(shipColor);
            
            int row = 0;    // Start at the first row.
            do {
                
                int col = 0;// Start at the first column.
                do {

                    // Is the cell empty?
                    if (this.gridCells[row][col] != Ship.TYPE_NONE) {
                        // No--the cell contains part of a ship.
                        
                        // Calculate the starting position of the cell.
                        int x = startX + col * COLUMN_PIXEL_WIDTH;
                        int y = startY + row * ROW_PIXEL_HEIGHT;

                        // Draw in a box that's smaller than the cell.
                        gfx.fillRect(x + 2, y + 2,
                                     COLUMN_PIXEL_WIDTH - 3,
                                     ROW_PIXEL_HEIGHT - 3);
                    }
                    
                    col++;  // Move on to the next column.
                } while (col < COLUMN_COUNT); 
                
                row++;      // Move on to the next row.
            } while (row < ROW_COUNT);
        }
}

