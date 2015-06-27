package Prototype3.Prototype3_2;

import processing.core.PApplet;

import java.awt.*;
import java.util.HashSet;

/**
 * An object representing a side of a hex.
 * Created by Tom_Bryant on 6/26/15.
 */
public class HexSide {

    private int id;
    private HexPoint start;
    private HexPoint end;
    private Point midPoint;
    private HashSet<HexSide> neighbors;
    private HashSet<HexTile> borders;
    private PApplet parent;



    public HexSide(HexPoint start, HexPoint end, Point midPoint, int id, PApplet canvas){
        this.parent = canvas;
        this.id = id;
        this.start = start;
        this.end = end;
        this.midPoint = midPoint;
        this.neighbors = new HashSet<HexSide>();
        this.borders = new HashSet<HexTile>();


    }



    public void display(){
        Point aStart = new Point(((int)start.getX() + midPoint.x)/2, (int)(start.getY() + midPoint.y)/2);
        Point bStart = new Point((int)(end.getX() + midPoint.x)/2, (int)(end.getY() + midPoint.y)/2);
        int slopeX = (int)(end.getX() - start.getX());
        slopeX = slopeX/10;
        int slopeY = (int)(end.getY() - start.getY());
        slopeY = slopeY/10;
        parent.stroke(0, 0, 0, 0);
        parent.fill(118,255,3);
        parent.beginShape();
        parent.vertex(bStart.x + slopeY, bStart.y + -slopeX);
        parent.vertex(aStart.x + slopeY, aStart.y + -slopeX);
        parent.vertex(aStart.x - slopeY, aStart.y + slopeX);
        parent.vertex(bStart.x - slopeY, bStart.y + slopeX);
        parent.endShape();
    }

    public HexPoint getStart() {
        return start;
    }

    public void setStart(HexPoint start) {
        this.start = start;
    }

    public HexPoint getEnd() {
        return end;
    }

    public void setEnd(HexPoint end) {
        this.end = end;
    }

    public Point getMidPoint() {
        return midPoint;
    }

    public void setMidPoint(Point midPoint) {
        this.midPoint = midPoint;
    }

    public HashSet<HexSide> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(HashSet<HexSide> neighbors) {
        this.neighbors = neighbors;
    }

    public HashSet<HexTile> getBorders() {
        return borders;
    }

    public void setBorders(HashSet<HexTile> borders) {
        this.borders = borders;
    }

    public PApplet getParent() {
        return parent;
    }

    public void setParent(PApplet parent) {
        this.parent = parent;
    }



    /**
     * A method for checking to see if the mouse is over this
     * point.
     * @return
     */
    boolean overSide() {
        float disX = midPoint.x - parent.mouseX;
        float disY = midPoint.y - parent.mouseY;
        if(parent.sqrt(parent.sq(disX) + parent.sq(disY)) < 20 ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method for mapping this side's neigbors.
     */
    public void mapNeighbors(){
        for(HexSide sd : neighbors){
            parent.stroke(255, 235, 59);
            parent.strokeWeight(5);
            parent.line(midPoint.x, midPoint.y, (float)sd.getMidPoint().x,(float)sd.getMidPoint().y);
            parent.strokeWeight(1);
        }
    }




    //    public void boxLine(Point a, Point b){
//        Point midPoint = new Point((a.x + b.x)/2, (a.y + b.y)/2);
//        Point aStart = new Point((a.x +midPoint.x)/2, (a.y + midPoint.y)/2);
//        Point bStart = new Point((b.x + midPoint.x)/2, (b.y + midPoint.y)/2);
//        int slopeX = b.x - a.x;
//        slopeX = slopeX/10;
//        int slopeY = b.y - a.y;
//        slopeY = slopeY/10;
//        stroke(0,0,0,0);
//        beginShape();
//        vertex(bStart.x +slopeY, bStart.y + -slopeX);
//        vertex(aStart.x +slopeY, aStart.y + -slopeX);
//        vertex(aStart.x -slopeY, aStart.y + slopeX);
//        vertex(bStart.x -slopeY, bStart.y + slopeX);
//        endShape();
//    }

}
