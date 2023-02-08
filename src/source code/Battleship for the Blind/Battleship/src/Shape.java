/*
 * Shape.java
 *
 * Created on February 18, 2004, 5:09 PM
 */

/**
 *
 * @author  Administrator
 */
import java.awt.*;

public class Shape {
    protected int[][] points;
    
    public void draw(int x, int y, Color color, Graphics gfx) {
        // Set the draw color.
        gfx.setColor(color);
        
        // Draw a line segment from the 0th point to the 1st point,
        // then the 1st point to the 2nd point,
        // then the 2nd to the 3rd, etc.
        for (int i=1; i<points.length; i++) {
            int pointX1 = x + points[i - 1][0];
            int pointY1 = y + points[i - 1][1];
            int pointX2 = x + points[i][0];
            int pointY2 = y + points[i][1];
            
            gfx.drawLine(pointX1, pointY1, pointX2, pointY2);
        }
    }
}
