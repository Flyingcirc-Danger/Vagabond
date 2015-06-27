package Prototype3.Prototype3_2;

import processing.core.PApplet;

import java.awt.*;

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
        fill(0);
        text("Prototype 3.1", 20,20);

    }



    public void draw() {


        fill(255,0,0,0);

        fill(0);
        center.getModel().displayBoard();
        boxLine(center.getA().getCoords(), center.getB().getCoords());
        boxLine(center.getB().getCoords(), center.getC().getCoords());
        fill(0);



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


    public void boxLine(Point a, Point b){
        int slopeX = b.x - a.x;
        slopeX = slopeX/10;
        int slopeY = b.y - a.y;
        slopeY = slopeY/10;
        stroke(0,0,0,0);
        beginShape();
        vertex(b.x +slopeY, b.y + -slopeX);
        vertex(a.x +slopeY, a.y + -slopeX);
        vertex(a.x -slopeY, a.y + slopeX);
        vertex(b.x -slopeY, b.y + slopeX);
        endShape();
        //line(b.x + slopeY, b.y + -slopeX, b.x -slopeY, b.y + slopeX);
        //line(a.x +slopeY, a.y+ -slopeX,a.x -slopeY, a.y + slopeX);
        }



    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "Prototype3.Prototype3_2.Board" });
    }
}
