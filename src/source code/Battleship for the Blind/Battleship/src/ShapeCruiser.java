/*
 * ShapeCruiser.java
 *
 * Created on February 18, 2004, 5:09 PM
 */

/**
 *
 * @author  Administrator
 */
import java.awt.*;

public class ShapeCruiser extends Shape {
    private int[][] cruiserPoints = {
        {26, 10},
        {33, 10},
        {33, 8},
        {29, 8},
        {36, 8},
        {37, 9},
        {37, 10},
        {40, 10},
        {41, 9},
        {42, 9},
        {46, 5},
        {45, 4},
        {46, 3},
        {48, 3},
        {49, 4},
        {48, 5},
        {49, 6},
        {49, 7},
        {51, 9},
        {51, 10},
        {60, 10},
        {60, 9},
        {61, 8},
        {61, 7},
        {62, 6},
        {62, 5},
        {63, 6},
        {63, 8},
        {65, 10},
        {68, 10},
        {68, 9},
        {69, 8},
        {75, 8},
        {72, 8},
        {72, 10},
        {79, 10},
        {72, 17},
        {33, 17},
        {26, 10}
    };
    
    public ShapeCruiser() {
        this.points = cruiserPoints;
    }
}
