/*
 * ShapeSubmarine.java
 *
 * Created on February 18, 2004, 5:09 PM
 */

/**
 *
 * @author  Administrator
 */
import java.awt.*;

public class ShapeSubmarine extends Shape {
    private int[][] submarinePoints = {
        {27, 9},
        {37, 9},
        {37, 6},
        {39, 6},
        {39, 2},
        {39, 6},
        {41, 6},
        {41, 4},
        {41, 6},
        {42, 6},
        {45, 9},
        {45, 10},
        {57, 10},
        {58, 11},
        {78, 11},
        {79, 12},
        {79, 13},
        {75, 17},
        {30, 17},
        {26, 13},
        {26, 10},
        {27, 9}
    };
    
    public ShapeSubmarine() {
        this.points = submarinePoints;
    }
}
