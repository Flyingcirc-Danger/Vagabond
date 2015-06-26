package Prototype3;

import com.sun.javafx.geom.Point2D;
import processing.core.PApplet;

import java.awt.*;

/**
 * Created by Tom_Bryant on 6/25/15.
 */
public class HexTile {

    private PApplet parent;

    //points
    private Point center;
    private Point A;
    private Point B;
    private Point C;
    private Point D;
    private Point E;
    private Point F;

    //measurements
    private int radius;
    private int sideToSide;
    private int canopyHeight;

    public HexTile(PApplet parent, double centerX, double centerY, int radius){
        this.parent = parent;
        this.center = new Point();
        this.center.setLocation(centerX, centerY);
        this.radius = radius;
        initPoints();

    }

    /**
     * Initializes the points based on the given
     * center coords and radius
     */
    private void initPoints(){
        this.sideToSide = decideRound((Math.sqrt(3) * this.radius) / 2);
        this.canopyHeight = decideRound(radius - Math.sqrt((Math.pow(radius, 2) - Math.pow(sideToSide, 2))));
        this.A = new Point();
        A.setLocation(center.getX(), center.getY() - radius);
        this.B = new Point();
        B.setLocation(center.getX() + sideToSide, center.getY() - canopyHeight);
        this.C = new Point();
        C.setLocation(center.getX() + sideToSide, center.getY() + canopyHeight);
        this.D = new Point();
        D.setLocation(center.getX(), center.getY() + radius);
        this.E = new Point();
        E.setLocation(center.getX() - sideToSide, center.getY() + canopyHeight);
        this.F = new Point();
        F.setLocation(center.getX() - sideToSide, center.getY() - canopyHeight);
    }

    /**
     * Draws the hex on the parent canvas
     */
    public void display(){
        parent.smooth(8);
        parent.stroke(0, 0, 0,20);
        parent.beginShape();
        parent.vertex((float) A.getX(),(float) A.getY());
        parent.vertex((float) B.getX(), (float) B.getY());
        parent.vertex((float) C.getX(), (float) C.getY());
        parent.vertex((float) D.getX(), (float) D.getY());
        parent.vertex((float) E.getX(), (float) E.getY());
        parent.vertex((float) F.getX(), (float) F.getY());
        parent.endShape();
    }

    /**
     * Adds a tile along the AB side
     * @return the new HexTile
     */
    public HexTile addAB(){
        return new HexTile(parent,B.getX(), A.getY() - radius - (A.getY() - B.getY() ), radius);
    }

    /**
     * Adds a tile along the BC side
     * @return the new HexTile
     */
    public HexTile addBC(){
        return new HexTile(parent,center.getX() + (sideToSide * 2),(center.getY()),radius);
    }

    /**
     * Adds a tile along the CD side
     * @return the new HexTile
     */
    public HexTile addCD(){
        return new HexTile(parent,C.getX(), D.getY() + radius - (D.getY() - C.getY() ), radius);
    }

    /**
     * Adds a tile along the DE side
     * @return the new HexTile
     */
    public HexTile addDE(){
        //return new HexTile(parent,E.getX(),(center.getY() + (radius + (radius - canopyHeight))),radius);
        return new HexTile(parent,E.getX(), D.getY() + radius - (D.getY() - E.getY() ), radius);
    }

    /**
     * Adds a tile along the EF side
     * @return the new HexTile
     */
    public HexTile addEF(){
        return new HexTile(parent,center.getX() - (sideToSide * 2),(center.getY()),radius);
    }

    /**
     * Adds a tile along the FA side
     * @return the new HexTile
     */
    public HexTile addFA(){
       // return new HexTile(parent,F.getX(),(center.getY() - (radius + (radius - canopyHeight))),radius);
        return new HexTile(parent,F.getX(), A.getY() - radius - (A.getY() - F.getY() ), radius);
    }

    //Getters & setters

    public void setA(Point a) {
        A = a;
    }

    public void setB(Point b) {
        B = b;
    }

    public void setC(Point c) {
        C = c;
    }

    public void setD(Point d) {
        D = d;
    }

    public void setE(Point e) {
        E = e;
    }

    public void setF(Point f) {
        F = f;
    }

    public Point getA() {
        return A;
    }

    public Point getB() {
        return B;
    }

    public Point getC() {
        return C;
    }

    public Point getD() {
        return D;
    }

    public Point getE() {
        return E;
    }

    public Point getF() {
        return F;
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

    /**
     * A toString method for only the coordinates
     * @return
     */
    public String coordsToString(){
        return "A: " + A.toString() +
                "\nB: " + B.toString() +
                "\nC: " + C.toString() +
                "\nD: " + D.toString() +
                "\nE: " + E.toString() +
                "\nF: " + F.toString();
    }
}
