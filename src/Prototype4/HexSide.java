package Prototype4;

import processing.core.PApplet;

import java.awt.*;
import java.util.HashSet;

/**
 * An object representing a side of a hex.
 * Created by Tom_Bryant on 6/26/15.
 */
public class HexSide {

    private int id; //id for debugging and uniqueness purposes
    private HexPoint start; //start point of this side
    private HexPoint end; //end point of this side
    private Point midPoint; //middle point of this side
    private HashSet<HexSide> neighbors; //set of sides immediatley touching this side
    private HashSet<HexTile> borders; //set of tiles either side of this side (borders)
    private Board parent; //canvas to draw on

    private boolean built; //check to see if a road exists on this side.



    public HexSide(HexPoint start, HexPoint end, Point midPoint, int id, Board canvas){
        this.parent = canvas;
        this.id = id;
        this.start = start;
        this.end = end;
        this.midPoint = midPoint;
        this.neighbors = new HashSet<HexSide>();
        this.borders = new HashSet<Prototype4.HexTile>();
        this.built = false;
        this.start.getRoads().add(this);
        this.end.getRoads().add(this);


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

    /**
     * Draws a road centered on the side with 1/10 spacer either end.
     */
    public void drawRoad(){
        if(isBuilt() || (overSide() && validBuild() && parent.currentTool == 2)){
            Point aStart = new Point(((int) start.getX() + midPoint.x) / 2, (int) (start.getY() + midPoint.y) / 2);
            Point bStart = new Point((int) (end.getX() + midPoint.x) / 2, (int) (end.getY() + midPoint.y) / 2);
            int slopeX = (int) (end.getX() - start.getX());
            slopeX = slopeX / 15;
            int slopeY = (int) (end.getY() - start.getY());
            slopeY = slopeY / 15;
            if(!this.isBuilt()){
                parent.fill(255, 0, 0,80);
            } else{
                parent.fill(1,87, 155);
            }
            parent.stroke(0, 0, 0, 0);

            parent.beginShape();
            parent.vertex(bStart.x + slopeY, bStart.y + -slopeX);
            parent.vertex(aStart.x + slopeY, aStart.y + -slopeX);
            parent.vertex(aStart.x - slopeY, aStart.y + slopeX);
            parent.vertex(bStart.x - slopeY, bStart.y + slopeX);
            parent.endShape();
        }
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

    public HashSet<Prototype4.HexTile> getBorders() {
        return borders;
    }

    public void setBorders(HashSet<Prototype4.HexTile> borders) {
        this.borders = borders;
    }

    public PApplet getParent() {
        return parent;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }

    public boolean isBuilt() {
        return built;
    }

    public void setBuilt(boolean built) {
        this.built = built;
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

    /**
     * Checks to see if it is valid to
     * build a road on this side.
     * @return true if it is valid
     */
    public boolean validBuild(){
        if(start.isSettled() || end.isSettled()){
            return true;
        }
        for(HexSide ne : neighbors){
            if(ne.isBuilt()){
                return true;
            }
        }
        return false;
    }






}
