/**
* Represents a player's board.
* 
* @author Mark Kreitler
* @version 0.1
*/

import java.awt.*;
import java.util.*;

public class Board
{
    // Class variables.
    public static final int ROW_COUNT           = 10;
    public static final int COLUMN_COUNT        = 10;
    public static final int ROW_PIXEL_HEIGHT    = 20;
    public static final int COLUMN_PIXEL_WIDTH  = 20;
    private static final int SHIPS_PER_FLEET    = 5;
    private static final int TYPE_PEG_WHITE     = -2;
    private static final int TYPE_PEG_RED       = -3;

    // Instance variables.
    private Ship[] fleet;
    private int[][] gridCells;
    private int firstClickRow;
    private int firstClickCol;
    private int currentUserShip;
    private int[] shipType = {Ship.TYPE_AIRCRAFT_CARRIER,
                              Ship.TYPE_BATTLESHIP,
                              Ship.TYPE_CRUISER,
                              Ship.TYPE_SUBMARINE, 
                              Ship.TYPE_PT_BOAT};
    private int[] shipLength = {5, 4, 3, 3, 2};
    private int[] endBoxRow = {-1, -1, -1, -1};
    private int[] endBoxCol = {-1, -1, -1, -1};
    private StatusDisplay status;

    // Methods.
    /**
     * Constructor for objects of class Board
     */
    public Board(int statusX, int statusY, Color statusColor)
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

        // Initialize the first click row and column.
        this.firstClickRow = -1;
        this.firstClickCol = -1;

        // Initialize the user ship type.
        this.currentUserShip = 0;

        // Create status display managers.
        this.status = new StatusDisplay(statusX,
                                        statusY,
                                        fleet,
                                        statusColor);
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

        // Place the ship into the grid cells.
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
   
    public int getShipCount() {
        return this.currentUserShip;
    }
    
    /** Check target square to see if it contains a ship. */
    public boolean didHitShip(int row, int col) {
        return this.gridCells[row][col] >= 0;
    }

    /** Apply damage to the ship at square (row, col). */
    public Ship applyDamage(int row, int col) {
        int shipType = this.gridCells[row][col];
        Ship hitShip = this.fleet[shipType];
        hitShip.takeHit();
        
        return hitShip;
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

    /** Test to see if any undestroyed ship remains on the board. */
    public boolean isAnyShipLeft() {
        boolean bShipIsLeft = false;

        int i=0;
        while (i < fleet.length) {
            if (fleet[i] != null &&
                fleet[i].getHits() < fleet[i].getSize()) {

                bShipIsLeft = true;
                break;
            }
            i++;
        }

        return bShipIsLeft;
    }

    /**
     *  Record the row and column of the first setup mouse click.
     */
    public boolean setFirstRowColumn(int row, int col) {
        boolean bNeedPositionClick = false;
        
        if ((row >= 0) &&
            (row < ROW_COUNT) &&
            (col >= 0) &&
            (col < COLUMN_COUNT)) {
            this.firstClickRow = row;
            this.firstClickCol = col;
            
            // Figure out which of the four surrounding
            // end points are valid.
            int testLength = this.shipLength[this.currentUserShip] - 1;
            this.validateUpperEndPoint(0, row, col, testLength);
            this.validateRightEndPoint(1, row, col, testLength);
            this.validateLowerEndPoint(2, row, col, testLength);
            this.validateLeftEndPoint(3, row, col, testLength);
        }
        else {
            // Prevent the 'x' from drawing.
            this.firstClickRow = -1;
            this.firstClickCol = -1;
            bNeedPositionClick = true;
        }
        
        return bNeedPositionClick;
    }
    
    /**
     *  Record the row and column of the first setup mouse click.
     */
    public boolean setSecondRowColumn(int row, int col) {
        boolean bNeedPositionClick = false;
        
        if ((row >= 0) &&
            (row < ROW_COUNT) &&
            (col >= 0) &&
            (col < COLUMN_COUNT)) {
            
            // Start with the first box.
            boolean bPlacedShip = false;
            int i=0;
            while (i < this.endBoxRow.length) {
                // Check to see if the user clicked
                // on the current endpoint.
                if ((row == this.endBoxRow[i]) &&
                    (col == this.endBoxCol[i])) {
                    // Place the ship and move on
                    // to the next.
                    int shipType = this.shipType[this.currentUserShip];
                    int size = this.shipLength[this.currentUserShip];
                    Ship newShip = new Ship(shipType, i,
                                            this.firstClickRow,
                                            this.firstClickCol,
                                            size);
                    this.addShip(newShip);
                    this.currentUserShip++;
                    bPlacedShip = true;
                    break;
                }
                
                i++;
            }
            
            if (bPlacedShip == false) {
                // The user didn't click on a valid
                // end point, so now we have to
                // move the starting 'x'.
                this.setFirstRowColumn(row, col);
            }
            else {
                this.clearEndBoxes();
                bNeedPositionClick = true;
            }
        }
        
        return bNeedPositionClick;
    }

    /**
     *  Places the computer's ships on the board.
     */
    public void placeComputerShips() {
        long seed = System.currentTimeMillis();
        Random randomizer = new Random(seed);
        
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

        // Draw setup 'x'.
        if ((this.firstClickRow != -1) &&
            (this.firstClickCol != -1)) {
            x = startX + this.firstClickCol * COLUMN_PIXEL_WIDTH;
            y = startY + this.firstClickRow * ROW_PIXEL_HEIGHT;
            x = x + 2;
            y = y + 2;
            int dx = COLUMN_PIXEL_WIDTH - 4;
            int dy = ROW_PIXEL_HEIGHT - 4;
            gfx.setColor(Color.green);
            gfx.drawLine(x, y, x + dx, y + dy);
            gfx.drawLine(x + dx, y, x, y + dy);
        }
        
        // Draw the setup boxes.
        int i = 0;
        while (i < this.endBoxRow.length) {
            if ((this.endBoxRow[i] != -1) &&
                (this.endBoxCol[i] != -1)) {
                
                x = startX + this.endBoxCol[i] * COLUMN_PIXEL_WIDTH;
                y = startY + this.endBoxRow[i] * ROW_PIXEL_HEIGHT;
                gfx.setColor(Color.green);
                x = x + 2;
                y = y + 2;
                int dx = COLUMN_PIXEL_WIDTH - 3;
                int dy = ROW_PIXEL_HEIGHT - 3;
                gfx.setColor(Color.green);
                gfx.drawRect(x, y, dx, dy);
            }
            
            i++;
        }
    }
    
    /** Draw the status display. */
    public void drawStatus(Graphics gfx) {
        this.status.paint(gfx);
    }

    // Private Methods /////////////////////////////////////////////////////////
    private void clearEndBoxes() {
        int i=0;
        while (i < this.endBoxRow.length) {
            this.endBoxRow[i] = -1;
            this.endBoxCol[i] = -1;
            
            i++;
        }
    }
    
    /**
     *  Test to see if the we can place a ship placement
     *  endpoint in this position.
     */
    private void validateUpperEndPoint(int boxIndex, int row, int col, int size) {
        // Make sure the point falls on the board.
        int newRow = row - size;
        if (newRow >= 0) {
            // This point falls on the board.
            // Now check for collisions.
            boolean bCollision = false;
            int i = row;
            while (i >= newRow) {
                if (this.gridCells[i][col] != Ship.TYPE_NONE) {
                    bCollision = true;
                    break;
                }
                
                i--;
            }
            
            if (bCollision == false) {
                this.endBoxRow[boxIndex] = newRow;
                this.endBoxCol[boxIndex] = col;
            }
            else {
                // Prevent this box from drawing;
                this.endBoxRow[boxIndex] = -1;
                this.endBoxCol[boxIndex] = -1;
            }
        }
        else {
            // Prevent this box from drawing.
            this.endBoxRow[boxIndex] = -1;
            this.endBoxCol[boxIndex] = -1;
        }
    }
    
    private void validateRightEndPoint(int boxIndex, int row, int col, int size) {
        // Make sure the point falls on the board.
        int newCol = col + size;
        if (newCol < COLUMN_COUNT) {
            // This point falls on the board.
            // Now check for collisions.
            boolean bCollision = false;
            int i = col;
            while (i <= newCol) {
                if (this.gridCells[row][i] != Ship.TYPE_NONE) {
                    bCollision = true;
                    break;
                }
                
                i++;
            }
            
            if (bCollision == false) {
                this.endBoxRow[boxIndex] = row;
                this.endBoxCol[boxIndex] = newCol;
            }
            else {
                // Prevent this box from drawing;
                this.endBoxRow[boxIndex] = -1;
                this.endBoxCol[boxIndex] = -1;
            }
        }
        else {
            // Prevent this box from drawing.
            this.endBoxRow[boxIndex] = -1;
            this.endBoxCol[boxIndex] = -1;
        }
    }
    
    private void validateLowerEndPoint(int boxIndex, int row,
                                       int col, int size) {
        // Make sure the point falls on the board.
        int newRow = row + size;
        if (newRow < ROW_COUNT) {
            // This point falls on the board.
            // Now check for collisions.
            boolean bCollision = false;
            int i = row;
            while (i <= newRow) {
                if (this.gridCells[i][col] != Ship.TYPE_NONE) {
                    bCollision = true;
                    break;
                }
            
                i++;
            }
            
            if (bCollision == false) {
                this.endBoxRow[boxIndex] = newRow;
                this.endBoxCol[boxIndex] = col;
            }
            else {
                // Prevent this box from drawing;
                this.endBoxRow[boxIndex] = -1;
                this.endBoxCol[boxIndex] = -1;
            }
        }
        else {
            // Prevent this box from drawing.
            this.endBoxRow[boxIndex] = -1;
            this.endBoxCol[boxIndex] = -1;
        }
    }    
    
    private void validateLeftEndPoint(int boxIndex, int row,
                                      int col, int size) {
        // Make sure the point falls on the board.
        int newCol = col - size;
        if (newCol >= 0) {
            // This point falls on the board.
            // Now check for collisions.
            boolean bCollision = false;
            int i = col;
            while (i >= newCol) {
                if (this.gridCells[row][i] != Ship.TYPE_NONE) {
                    bCollision = true;
                    break;
                }

                i--;
            }

            if (bCollision == false) {
                this.endBoxRow[boxIndex] = row;
                this.endBoxCol[boxIndex] = newCol;
            }
            else {
                // Prevent this box from drawing.
                this.endBoxRow[boxIndex] = -1;
                this.endBoxCol[boxIndex] = -1;
            }
        }
        else {
            // Prevent this box from drawing.
            this.endBoxRow[boxIndex] = -1;
            this.endBoxCol[boxIndex] = -1;
        }
    }
    
    /** Check to see if the board contains a peg
     * at the location given by (row, col). */
     public boolean isUnpegged(int row, int col) {
        boolean bIsUnpegged = true;

        int squareContents = this.gridCells[row][col];
        if (squareContents == TYPE_PEG_WHITE ||
            squareContents == TYPE_PEG_RED) {
            bIsUnpegged = false;
        }

        return bIsUnpegged;
    }
    
}
