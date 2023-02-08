/*
 * ShapePTboat.java
 *
 * Created on February 18, 2004, 5:09 PM
 */

/**
 *
 * @author  Administrator
 */
import java.awt.*;

public class ShapePTboat extends Shape {
    private int[][] ptBoatPoints = {
        {45, 11},
        {51, 11},
        {53, 9},
        {54, 9},
        {56, 7},
        {57, 7},
        {57, 9},
        {58, 10},
        {58, 11},
        {59, 11},
        {59, 13},
        {73, 13},
        {73, 12},
        {79, 12},
        {79, 16},
        {78, 17},
        {51, 17},
        {45, 11}
    };
    
    public ShapePTboat() {
        this.points = ptBoatPoints;
    }
}
