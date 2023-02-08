/**
* The basic framework for a battleship game.
* 
* @author Mark Kreitler
* @version 0.1
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class BattleshipApp extends JFrame
                           implements MouseListener, WindowListener
{
    private static final int GAME_STATE_INTRO = 0;
    private static final int GAME_STATE_SETUP = 1;
    private static final int GAME_STATE_PLAYING = 2;
    private static final int GAME_STATE_GAMEOVER = 3;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 500;
    private static final int TITLE_X = 260;
    private static final int TITLE_Y = 225;
    private static final int MOUSE_MSG_X = 228;
    private static final int MOUSE_MSG_Y = 275;
    private static final int RED_GRID_X = 5;
    private static final int RED_GRID_Y = 5;
    private static final int BLUE_GRID_X = 255;
    private static final int BLUE_GRID_Y = 5;
    
    private static final int MSG_WINDOW_DX = GAME_WIDTH;
    private static final int MSG_WINDOW_DY = 50;
    private static final int MSG_WINDOW_X = 0;
    private static final int MSG_WINDOW_Y = GAME_HEIGHT - MSG_WINDOW_DY;
    private static final int PLAYER_STATUS_X = RED_GRID_X;
    private static final int STATUS_Y = 250;
    private static final int COMPUTER_STATUS_X = BLUE_GRID_X;
    
    // Non-constant, static variables.
    private static String    SOUND_PATH = "";
    
    // instance variables
    private Board redBoard;
    private Board blueBoard;
    private EnemyPlayer enemyPlayer;
    private int gameState;
    private boolean bNeedPositionClick;
    private String userMessage = null;
    private Image backBuffer = null;
    private Clip soundRadio = null;
    private Clip soundExplosion = null;
    private Clip soundSplash = null;
    private Clip soundSonar = null;

    /**
     * Entry point for code execution.
     */
    public static void main(String[] args) {
        // Initialize the sound manager.
        SoundManager.INIT();
        
        try {
            SOUND_PATH = args[0];
        } catch(Exception e) {}
        
        new BattleshipApp();
    }

    /**
     * Constructor for objects of class BattleshipApp
     */
    public BattleshipApp()
    {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.show();
        this.addMouseListener(this);
        this.addWindowListener(this);
        this.gameState = GAME_STATE_INTRO;

        // Initialize the game boards.
        this.redBoard = new Board(PLAYER_STATUS_X, STATUS_Y, Color.red);;
        this.blueBoard = new Board(COMPUTER_STATUS_X, STATUS_Y, Color.blue);
        
        // Initialize the computer player.
        this.enemyPlayer = new EnemyPlayer();

        // Load sounds.
        this.soundRadio = SoundManager.LOAD(SOUND_PATH, "radio.au");
        this.soundSonar = SoundManager.LOAD(SOUND_PATH, "sonar.au");
        this.soundExplosion = SoundManager.LOAD(SOUND_PATH, "exp_big.au");
        this.soundSplash = SoundManager.LOAD(SOUND_PATH, "splash.au");

        // Set the application's title.
        this.setTitle("Battleship!");

        // Create a back buffer of the same size as the content pane.
        Container clientArea = this.getContentPane();
        int width = clientArea.getWidth();
        int height = clientArea.getHeight();
        this.backBuffer = this.createImage(width, height);
        
        // Draw the game now that we have a valid back buffer.
        this.paint(this.getGraphics());
        SoundManager.PLAY(this.soundSonar);
    }

    /**
     * Draws the game window.
     */
    public void paint(Graphics gfx) {
        if (this.backBuffer != null) {
            Graphics bbGfx = this.backBuffer.getGraphics();
            
            if (this.gameState == GAME_STATE_INTRO) {
                this.drawTitleScreen();
            }
            else if (this.gameState == GAME_STATE_SETUP) {
                this.drawGrids();
                this.drawStatus(bbGfx);
                this.drawMessage(bbGfx);
            }
            else if (this.gameState == GAME_STATE_PLAYING) {
                this.drawGrids();
                this.drawStatus(bbGfx);
                this.drawMessage(bbGfx);
            }
            else if (this.gameState == GAME_STATE_GAMEOVER) {
                this.drawGrids();
                this.drawStatus(bbGfx);
                this.drawMessage(bbGfx);
            }
            
            // Copy the contents of the back buffer into the content pane.
            Graphics contentPaneGfx = this.getContentPane().getGraphics();
            contentPaneGfx.drawImage(this.backBuffer, 0, 0, null);
        }
    }

    /**
     * Draws the 'Welcome to Battleship' screen.
     */
    private void drawTitleScreen() {
        // Get the Graphics object associated with the client area.
        Graphics gfx = this.backBuffer.getGraphics();

        // Get the size of the back buffer.
        int width = this.backBuffer.getWidth(null);
        int height = this.backBuffer.getHeight(null);

        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, width, height);

        gfx.setColor(Color.green);
        gfx.drawString("BATTLESHIP", TITLE_X, TITLE_Y);
        gfx.setColor(Color.gray);
        gfx.drawString("(click mouse to continue)", MOUSE_MSG_X, MOUSE_MSG_Y);
    }

    /** Draw status displays. */
    public void drawStatus(Graphics gfx) {
        this.redBoard.drawStatus(gfx);
        this.blueBoard.drawStatus(gfx);
    }
    
    /**
     * Draw the game grids.
     */
    private void drawGrids() {
        // Get the Graphics object associated with the client area.
        Graphics gfx = this.backBuffer.getGraphics();

        // Get the size of the back buffer.
        int width = this.backBuffer.getWidth(null);
        int height = this.backBuffer.getHeight(null);

        // Fill the background with black.
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, width, height);

        // Draw the two grids.
        this.redBoard.drawGrid(gfx, Color.red, RED_GRID_X, RED_GRID_Y);
        this.blueBoard.drawGrid(gfx, Color.blue, BLUE_GRID_X, BLUE_GRID_Y);
        
        // Draw the ships on the grids.
        this.redBoard.drawShips(gfx, Color.gray, RED_GRID_X, RED_GRID_Y);
        this.blueBoard.drawShips(gfx, Color.black, BLUE_GRID_X, BLUE_GRID_Y);
    }

    public void drawMessage(Graphics gfx) {
        int x = MSG_WINDOW_X;
        int y = MSG_WINDOW_Y;
        int dx = MSG_WINDOW_DX;
        int dy = MSG_WINDOW_DY;

        // Erase any old message.
        gfx.setColor(Color.black);
        gfx.drawRect(x, y, dx, dy);
        
        // Draw a rectangle the size of the window.
        gfx.setColor(Color.gray);
        gfx.drawRect(x, y, dx, dy);

        // Place the message inside the rectangle.
        if (this.userMessage != null) {
            gfx.drawString(this.userMessage, x + 2, y + 20);
        }
    }
    
    public void setMessage(String newMessage, boolean bDraw) {
        this.userMessage = newMessage;

        if (bDraw) {
            this.paint(this.getGraphics());
            SoundManager.PLAY(this.soundRadio);
        }
    }
    
    /**
     * MouseListener methods.
     */
    public void mouseClicked(MouseEvent event) {}

    public void mouseEntered(MouseEvent event) {}

    public void mouseExited(MouseEvent event) {}

    public void mousePressed(MouseEvent event) {}

    public void mouseReleased(MouseEvent event) {
        if (this.gameState == GAME_STATE_INTRO) {
            this.gameState = GAME_STATE_SETUP;
            this.blueBoard.placeComputerShips();
            this.repaint();
            this.initializeSetupState();
        }
        else if (this.gameState == GAME_STATE_SETUP) {
            if (this.bNeedPositionClick) {
                // Record the row and column of the first mouse click.
                int row = this.convertYtoRow(event.getY());
                int col = this.convertXtoCol(event.getX());
                this.bNeedPositionClick = 
                    this.redBoard.setFirstRowColumn(row, col);
                this.repaint();
            }
            else {
                // Record the row and column of the first mouse click.
                int row = this.convertYtoRow(event.getY());
                int col = this.convertXtoCol(event.getX());
                this.bNeedPositionClick = 
                    this.redBoard.setSecondRowColumn(row, col);
                this.repaint();
                
                if (this.redBoard.getShipCount() == 5) {
                    this.gameState = BattleshipApp.GAME_STATE_PLAYING;
                    this.setMessage("PLAY GAME!", true);
                }
            }
        }
        else if (this.gameState == GAME_STATE_PLAYING) {
            // Figure out where player clicked.
            int row = convertYtoEnemyRow(event.getY());
            int col = convertXtoEnemyCol(event.getX());

            // CHECK: did the player click on an empty blue square?
            if (this.isPlayerTargetValid(event.getX(), event.getY())) {
                /* If player hit a ship, apply damage. */
                if (this.blueBoard.didHitShip(row, col)) {
                    this.blueBoard.applyDamage(row, col);
                    this.blueBoard.putPegInSquare(row, col, true);
                    this.paint(this.getGraphics());
                    SoundManager.PLAY(this.soundExplosion);
                }
                else {
                    this.blueBoard.putPegInSquare(row, col, false);
                    this.paint(this.getGraphics());
                    SoundManager.PLAY(this.soundSplash);
                }

                /* CHECK: did player win? */
                if (!this.blueBoard.isAnyShipLeft()) {
                    this.setMessage("You win! Click the mouse to play again.", true);
                    this.gameState = GAME_STATE_GAMEOVER;
                }
                else {
                    this.enemyPlayer.selectTarget();
                    row = this.enemyPlayer.getTargetRow();
                    col = this.enemyPlayer.getTargetColumn();

                    if (this.redBoard.didHitShip(row, col)) {
                        Ship hitShip = this.redBoard.applyDamage(row, col);
                        this.redBoard.putPegInSquare(row, col, true);

                        // CHECK: Did we sink the player's ship?
                        if (hitShip.getHits() == hitShip.getSize())
                        {
                            // IF YES...undo the 3,2,1 patterns.
                            this.enemyPlayer.undoWeights(hitShip);
                        }
                        else {
                            // ...apply a new 3,2,1 pattern. 
                            this.enemyPlayer.applyHitWeights(row, col, false);
                        }
                        
                        this.paint(this.getGraphics());
                        SoundManager.PLAY(this.soundExplosion);
                    }
                    else {
                        this.redBoard.putPegInSquare(row, col, false);
                        this.enemyPlayer.applyMissWeights(row, col);
                        this.paint(this.getGraphics());
                        SoundManager.PLAY(this.soundSplash);
                    }

                    /* CHECK: did computer win? */
                    if (!this.redBoard.isAnyShipLeft()) {
                        this.setMessage("You lose... Click the mouse to play again.", true);
                        this.gameState = GAME_STATE_GAMEOVER;
                    }
                }
            }
            else {
                this.setMessage("Please click on an empty blue square.", true);
            }
        }
        else if (this.gameState == GAME_STATE_GAMEOVER) {
            // Initialize the game boards.
            this.redBoard = new Board(PLAYER_STATUS_X, STATUS_Y, Color.red);;
            this.blueBoard = new Board(COMPUTER_STATUS_X, STATUS_Y, Color.blue);
            this.enemyPlayer = new EnemyPlayer();
            System.gc();
            
            this.gameState = GAME_STATE_SETUP;
            this.blueBoard.placeComputerShips();
            this.repaint();
            this.initializeSetupState();
        }
    }

    // Private Methods /////////////////////////////////////////////////////////
    /**
     *  Get ready to set up the boards.
     */
    private void initializeSetupState() {
        this.bNeedPositionClick = true;
        
        // Display instructions.
        String instructions = "Click twice on the red grid to place a ship." +
                              "Green boxes mark valid endpoints for the ship.";
        this.setMessage(instructions, true);
    }
    
    /**
     *  Converts a mouse y-coordinate into a row on the red grid.
     */
    private int convertYtoRow(int pixelY) {
        Container clientArea = this.getContentPane();
        int borderSize = (this.getWidth() - clientArea.getWidth()) / 2;
        int titleSize = (this.getHeight() - clientArea.getHeight()) - borderSize;
        int row = (pixelY - titleSize - RED_GRID_Y) / Board.ROW_PIXEL_HEIGHT;

        return row;
    }

    /**
     *  Converts a mouse x-coordinate into a column on the red grid.
     */
    private int convertXtoCol(int pixelX) {
        Container clientArea = this.getContentPane();
        int borderSize = (this.getWidth() - clientArea.getWidth()) / 2;
        int column = (pixelX - borderSize - RED_GRID_X) / Board.COLUMN_PIXEL_WIDTH;

        return column;
    }    
    
    /**
     *  Converts a mouse y-coordinate into a row on the blue grid.
     */
    private int convertYtoEnemyRow(int pixelY) {
        Container clientArea = this.getContentPane();
        int borderSize = (this.getWidth() - clientArea.getWidth()) / 2;
        int titleSize = (this.getHeight() - clientArea.getHeight()) - borderSize;
        int row = (pixelY - titleSize - BLUE_GRID_Y) / Board.ROW_PIXEL_HEIGHT;

        return row;
    }

    /**
     *  Converts a mouse x-coordinate into a column on the blue grid.
     */
    private int convertXtoEnemyCol(int pixelX) {
        Container clientArea = this.getContentPane();
        int borderSize = (this.getWidth() - clientArea.getWidth()) / 2;
        int column = (pixelX - borderSize - BLUE_GRID_X) / Board.COLUMN_PIXEL_WIDTH;

        return column;
    }  
    
    /** Check space where player clicked.
     * * If it is on the board and empty, return 'true'. */
    private boolean isPlayerTargetValid(int mouseX, int mouseY) {
        boolean bIsValid = false;

        int row = convertYtoEnemyRow(mouseY);
        int col = convertXtoEnemyCol(mouseX);

        // Is this square on the board?
        if (row >= 0 && row < Board.ROW_COUNT &&
            col >= 0 && col < Board.COLUMN_COUNT) {
            // Is square empty?
            bIsValid = this.blueBoard.isUnpegged(row, col);
        }

        return bIsValid;
    }
    
    ////////////////////////////////////////////////////////////////////////
    // WindowListener Interface
    ////////////////////////////////////////////////////////////////////////
    public void windowActivated(WindowEvent event) {}
    public void windowClosed(WindowEvent event) {}
    public void windowClosing(WindowEvent event) {
        // Close the sound manager.
        SoundManager.SHUT_DOWN();
    
        this.dispose();
    }
    public void windowDeactivated(WindowEvent event) {}
    public void windowDeiconified(WindowEvent event) {}
    public void windowIconified(WindowEvent event) {}
    public void windowOpened(WindowEvent event) {}
}
