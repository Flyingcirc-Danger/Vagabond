package Prototype5;

import processing.core.PApplet;

import java.awt.*;

/**
 * Created by Tom_Bryant on 6/28/15.
 * A class for the coast border around tiles.
 */
public class HexCoast {

    //coords of the hex
    public Point A;
    public Point B;
    public Point C;
    public Point D;
    public Point E;
    public Point F;
    public Point center;

    //canvas
    private PApplet parent;

    public HexCoast(HexTile coastTile){
        this.center = coastTile.getCenter();
        initPoints(coastTile);
        this.parent = coastTile.getParent();

    }


    /**
     * Initializes the coast coordinates.
     * The radius of the coast hex will be 1.3 times
     * that of the regular hex that it borders.
     * @param coastTile
     */
    private void initPoints(HexTile coastTile){
        int sideToSide = decideRound((Math.sqrt(3) * (coastTile.getRadius() * 1.3) / 2));
        int canopyHeight = decideRound((coastTile.getRadius() * 1.3) - Math.sqrt((Math.pow((coastTile.getRadius() * 1.3), 2) - Math.pow(sideToSide, 2))));
        Point tempA = new Point();
        tempA.setLocation(center.getX(), center.getY() - (coastTile.getRadius() * 1.3));
        this.A = tempA;
        Point tempB = new Point();
        tempB.setLocation(center.getX() + sideToSide, center.getY() - canopyHeight);
        this.B = tempB;
        Point tempC = new Point();
        tempC.setLocation(center.getX() + sideToSide, center.getY() + canopyHeight);
        this.C = tempC;
        Point tempD = new Point();
        tempD.setLocation(center.getX(), center.getY() + (coastTile.getRadius() * 1.3));
        this.D = tempD;
        Point tempE = new Point();
        tempE.setLocation(center.getX() - sideToSide, center.getY() + canopyHeight);
        this.E = tempE;
        Point tempF = new Point();
        tempF.setLocation(center.getX() - sideToSide, center.getY() - canopyHeight);
        this.F = tempF;

    }




    /**
     * Draws the coasthex on the parent canvas
     */
    public void display(){
            parent.stroke(0,0,0,0);
            parent.smooth(8);
            parent.fill(255,243,224);
            parent.beginShape();
            parent.vertex(A.x, A.y);
            parent.vertex(B.x, B.y);
            parent.vertex(C.x, C.y);
            parent.vertex(D.x, D.y);
            parent.vertex(E.x, E.y);
            parent.vertex(F.x, F.y);
            parent.endShape();
    }

    /**
     * Draws the shadow of this tile with a given
     * offset.
     * @param offset
     */
    public void shadow(int offset){
            parent.stroke(0,0,0,0);
            parent.smooth(8);
            parent.fill(0,0,0,50);
            parent.beginShape();
            parent.vertex(A.x + offset, A.y + offset);
            parent.vertex(B.x + offset, B.y + offset);
            parent.vertex(C.x + offset, C.y + offset);
            parent.vertex(D.x + offset, D.y + offset);
            parent.vertex(E.x + offset, E.y + offset);
            parent.vertex(F.x + offset, F.y + offset);
            parent.endShape();

    }


    /**
     * Decides how to round a double
     * when converting to int
     * @param toRound the double to round
     * @return the rounded number
     */
    private int decideRound(double toRound){
        double result = ((toRound % (int) toRound));
        int firstDigit = Integer.parseInt(Double.toString(result).substring(0, 1));
        if(firstDigit >= 5){

            return (int) toRound + 1;
        } else {
            return (int) toRound;
        }
    }
}
