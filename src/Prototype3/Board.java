package Prototype3;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import processing.core.PApplet;

/**
 * Created by Tom_Bryant on 6/25/15.
 */
public class Board extends PApplet {

    public int SCREEN_HEIGHT;
    public int SCREEN_WIDTH;


    public void setup() {
        this.SCREEN_HEIGHT = 600 ;
        this.SCREEN_WIDTH = 800;
        size(SCREEN_WIDTH,SCREEN_HEIGHT);
        background(0, 188, 212);
        smooth(8);


    }

    public void draw() {
        //hex(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 50f);
        HexTile center = new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50);
        //center.display();
        HexTile one =  center.addAB();
        System.out.println("CENTER: " + center.getD().toString());
        System.out.println(one.getF().toString());
        System.out.println("CENTER: " + center.getC().toString());
        System.out.println(one.getA().toString());
        HexTile two =  center.addBC();
        HexTile three =  center.addCD();
        fill(255);
        one.display();
        //two.display();
        //three.display();
        fill(0);
        text("A", (int) one.getA().getX(), (int) one.getA().getY());
        text("B", (int)one.getB().getX(), (int)one.getB().getY());
        text("C", (int)one.getC().getX(), (int)one.getC().getY());
        text("D", (int)one.getD().getX(), (int)one.getD().getY());
        text("E", (int)one.getE().getX(), (int)one.getE().getY());
        text("F", (int)one.getF().getX(), (int)one.getF().getY());


    }


    public void hex(float cX, float cY, float radius){
        smooth(8);
        float sideToSide = (float)(Math.sqrt(3) * radius) / 2f;
        float canopyBase = (float) Math.sqrt((Math.pow(radius, 2)) - Math.pow(sideToSide,2));
        stroke(0,0,0,0);
        beginShape();
        vertex(cX, cY + radius);
        vertex(cX + sideToSide, cY +  (radius -canopyBase));
        vertex(cX + sideToSide, cY - (radius -canopyBase));
        vertex(cX, cY - radius);
        vertex(cX - sideToSide, cY - (radius -canopyBase));
        vertex(cX - sideToSide, cY +  (radius -canopyBase));
        endShape();

    }

    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "Prototype3.Board" });
    }
}
