
/**
 * Displays the status of a player's ships.
 * 
 * @author Mark Kreitler
 * @version v0.1
 */
import java.awt.*;
public class StatusDisplay
{
    private static final int SHIP_DISPLAY_OFFSET_X  = 126;
    private static final int SHIP_DISPLAY_OFFSET_Y  = 2;
    private static final int SHIP_HEIGHT            = 18;
    private static final int SHIP_WIDTH             = 80;
    private static final int SHIP_HEALTH_OFFSET_Y   = 2;

    private int x;
    private int y;
    private Ship[] shipList;
    private Color playerColor;
    String[] shipNames = {
        "CARRIER",
        "BATTLESHIP",
        "CRUISER",
        "SUMBARINE",
        "PT BOAT"
    };
    Shape[] shipShapes = {
        new ShapeCarrier(),
        new ShapeBattleship(),
        new ShapeCruiser(),
        new ShapeSubmarine(),
        new ShapePTboat()
    };
    
    /** Constructor */
    public StatusDisplay(int x, int y, Ship[] ships, Color col) {
        this.x = x;
        this.y = y;
        this.shipList = ships;
        this.playerColor = col;
    }
    
    /** Draw the status display to the screen. */
    public void paint(Graphics gfx) {
        int shipIndex = 0;
        while (shipIndex < this.shipList.length) {
            /* Calculate current ship's health. */
            Ship ship = this.shipList[shipIndex];
            if (ship != null) {
                int damage = 100 * ship.getHits() / ship.getSize();

                /* Display health message. */
                String message = shipNames[ship.getType()] + ":";
                int messageX = this.x;
                int messageY = y + 25 * shipIndex;
                gfx.setColor(this.playerColor);
                gfx.drawString(message, messageX, messageY);
                
                if (damage < 100) {
                    /* Draw the ship box. */
                    int shipBottom = messageY + SHIP_DISPLAY_OFFSET_Y;
                    int shipTop = shipBottom - SHIP_HEIGHT;
                    int shipLeft = this.x + SHIP_DISPLAY_OFFSET_X;
                    int shipRight = shipLeft + SHIP_WIDTH;
                    this.shipShapes[shipIndex].draw(shipLeft, shipTop, Color.GRAY, gfx);
                
                    /* Draw the health bar. */
                    int healthBarX = shipLeft;
                    int healthBarY = shipBottom + SHIP_HEALTH_OFFSET_Y;
                    int healthWidth = SHIP_WIDTH * damage / 100;
                    Color healthColor = Color.BLACK;
                    if (damage > 0 && damage < 33) {
                        healthColor = Color.GREEN;
                    }
                    else if (damage >= 33 && damage < 67) {
                        healthColor = Color.YELLOW;
                    }
                    else if (damage >= 67) {
                        healthColor = Color.RED;
                    }
                
                    gfx.setColor(healthColor);
                    gfx.drawLine(healthBarX, healthBarY,
                                 healthBarX + healthWidth, healthBarY);
                }
            }

            // Next ship.
            shipIndex++;
        }
    }
}
