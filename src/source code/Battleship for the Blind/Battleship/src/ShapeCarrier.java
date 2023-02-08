/*
 * ShapeCarrier.java
 *
 * Created on February 18, 2004, 5:09 PM
 */

/**
 *
 * @author  Administrator
 */
import java.awt.*;

public class ShapeCarrier extends Shape {
    private int[][] carrierPoints = {
        {0, 10},
        {49, 10},
        {49, 8},
        {55, 8},
        {55, 6},
        {52, 6},
        {52, 4},
        {56, 4},
        {56, 2},
        {55, 2},
        {55, 0},
        {59, 0},
        {59, 2},
        {58, 2},
        {58, 4},
        {62, 4},
        {62, 6},
        {58, 6},
        {59, 8},
        {66, 8},
        {66, 10},
        {79, 10},
        {79, 13},
        {77, 13},
        {73, 17},
        {6, 17},
        {2, 13},
        {0, 13},
        {0, 10}
    };
    
    public ShapeCarrier() {
        this.points = carrierPoints;
    }
}
