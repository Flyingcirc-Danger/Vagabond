import processing.core.*;

/**
 * Created by Tom_Bryant on 6/23/15.
 */
public class Board extends PApplet {

    public void setup() {
        size(500,500);
        background(0);
    }

    public void draw() {
        hex(50,250,250);
        if (mousePressed) {
            line(mouseX,mouseY,pmouseX,pmouseY);
        }
    }

    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "MyProcessingSketch" });
    }

    public void hex(int radius, int xCenter, int yCenter){
        beginShape();
        vertex(xCenter, yCenter +radius);
        vertex(xCenter + radius, yCenter + (radius)/2);
        vertex(xCenter + radius, yCenter - (radius)/2);
        vertex(xCenter, yCenter - radius);
        vertex(xCenter - radius, yCenter - (radius)/2);
        vertex(xCenter - radius, yCenter + (radius)/2);
        endShape(CLOSE);

    }
}

