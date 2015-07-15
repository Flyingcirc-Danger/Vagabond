package Prototype5;

import processing.core.PApplet;

/**
 * Created by Tom_Bryant on 7/15/15.
 * A class for static listner methods
 */
public class Listeners {


    /**
     * A method for checking a rectangular
     * space for a mouse click
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public static boolean overRect(int x, int y, int width, int height, PApplet parent) {
        if (parent.mouseX >= x && parent.mouseX <= x + width &&
                parent.mouseY >= y && parent.mouseY <= y + height) {
            return true;
        } else {
            return false;
        }
    }
}
