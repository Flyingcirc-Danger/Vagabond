package Prototype3;

import processing.core.PApplet;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Tom_Bryant on 6/25/15.
 */
public class Board extends PApplet {

    public int SCREEN_HEIGHT;
    public int SCREEN_WIDTH;
    public HexTile center;



    public void setup() {

        this.SCREEN_HEIGHT = 600 ;
        this.SCREEN_WIDTH = 800;
        size(SCREEN_WIDTH,SCREEN_HEIGHT);
        background(0, 188, 212);
        smooth(8);
        this.center= new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, new BoardData());
        center.getModel().buildBoard(center);

    }



    public void draw() {
        //hex(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 50f);


        //center.getModel().drawCoast(this);
        fill(255,0,0,0);


        fill(0);
        center.getModel().displayBoard();


        loop();

//        text("A", (int) center.getA().getX(), (int) center.getA().getY());
//        text("B", (int) center.getB().getX(), (int) center.getB().getY());
//        text("C", (int) center.getC().getX(), (int) center.getC().getY());
//        text("D", (int)center.getD().getX(), (int) center.getD().getY());
//        text("E", (int)center.getE().getX(), (int)center.getE().getY());
//        text("F", (int) center.getF().getX(), (int) center.getF().getY());


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
