package Prototype4;

import processing.core.PApplet;

import java.awt.*;
import java.util.HashSet;

/**
 * Created by Tom_Bryant on 6/26/15.
 * A Hex point object. In vagabond you build
 * on hex points, so it's important for each point
 * to know their neigbors.
 */
public class HexPoint implements Comparable<HexPoint> {

    private Board parent;

    private Point coords;
    private HashSet<HexPoint> neigbors;
    private int id;
    private HashSet<HexSide> roads;

    private Point centerCoords;
    private boolean settled;
    private boolean city;
    //build status = 0: nobuild, 1:town, 2:city
    private int buildStatus;
    /**
     *
     * @param coords The coordinate of this point
     * @param centerCoords the centerCoordinate of the hex this belongs to
     * @param id the id of this hexpoint
     * @param parent the canvas to draw on
     */
    public HexPoint(Point coords, Point centerCoords, int id, Board parent){
        this.coords = coords;
        this.id = id;
        this.parent = parent;
        this.neigbors = new HashSet<HexPoint>();
        this.centerCoords = centerCoords;
        this.roads = new HashSet<HexSide>();
        this.buildStatus = 0;
    }

    /**
     * A method for displaying this point on the hex.
     */
    public void display(){
        //check if a structure is already built
        if(this.isSettled()){
          parent.fill(246,255,0);
        } else {
            parent.fill(255);
        }
        parent.stroke(0,0,0);
        parent.strokeWeight(1);
        if(!this.isEdge()) {
            parent.ellipse(coords.x, coords.y, 20, 20);
        } else{
            parent.rect(coords.x-10, coords.y-10,20,20);
        }
        parent.fill(0);
        parent.textSize(12);
        parent.text("" + id, coords.x - (parent.textWidth("id")/2), coords.y + 6);
    }

    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }

    public HashSet<HexPoint> getNeigbors() {
        return neigbors;
    }

    public void setNeigbors(HashSet<HexPoint> neigbors) {
        this.neigbors = neigbors;
    }

    public int getId() {
        return id;
    }

    public boolean isEdge() {
        if(neigbors.size() == 3){
            return false;
        }
        return true;
    }



    public double getX(){
        return this.coords.getX();
    }

    public double getY(){
        return this.coords.getY();
    }

    public Point getCenterCoords() {
        return centerCoords;
    }

    public PApplet getParent() {
        return parent;
    }

    public boolean isSettled() {
        if (this.buildStatus > 0){
            return true;
        }
        return false;
    }

    public void setSettled(boolean settled) {
        if(settled) {
            if (this.buildStatus < 2) {
                this.buildStatus = 1;
            }
        } else{
            this.buildStatus = 0;
        }
    }

    public HashSet<HexSide> getRoads() {
        return roads;
    }

    public void setRoads(HashSet<HexSide> roads) {
        this.roads = roads;
    }

    public boolean isCity() {
        if(this.buildStatus == 2){
            return true;
        }
        return false;
    }

    public void setCity(boolean city) {
        if(city){
            if(this.buildStatus > 0){
                this.buildStatus = 2;
            }
        }
    }

    @Override
    public String toString() {
        return "HexPoint{" +
                "id=" + id +
                ", settled=" + settled +
                ", coords=" + coords +
                '}';
    }

    /**
     * A method for mapping lines between all neighbors
     */
    public void mapNeigbors(){
        for(HexPoint pt : neigbors) {
            parent.stroke(255, 0, 0);
            parent.strokeWeight(5);
            parent.line(coords.x, coords.y, (float)pt.getX(),(float)pt.getY());
            parent.strokeWeight(1);
        }
    }

    /**
     * A method for checking to see if the mouse is over this
     * point.
     * @return
     */
    boolean overPoint() {
        float disX = coords.x - parent.mouseX;
        float disY = coords.y - parent.mouseY;
        if(parent.sqrt(parent.sq(disX) + parent.sq(disY)) < 20 ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(HexPoint o) {
        if(o.getId()< this.getId()){
            return 1;
        }
        if(o.getId() > this.getId()){
            return -1;
        }
        return 0;
    }

    /**
     * Draws a town icon centered on the point
     * Colors according to if it's a click (build) or hover
     * (test build)
     */
    public void drawTown(){
            if(buildStatus > 1){
                drawCity();
                return;
            }
            /*check if a point is settled, if your mouse is over the point, if it's valid to build
             and if the current tool selected is town building */
            if(this.isSettled() || (overPoint() && validBuild() && parent.currentTool == 1)) {
            int height = 20;
            int width = 20;

                if(!this.isSettled()){
                    parent.fill(255, 0, 0,80);
                } else {
                    parent.fill(1,87, 155);
                }
            parent.beginShape();
            parent.vertex(coords.x, coords.y - (height / 2));
            parent.vertex(coords.x - (width / 2), coords.y);
            parent.vertex(coords.x - ((width / 2) - (width / 8)), coords.y);
            parent.vertex(coords.x - ((width / 2) - (width / 8)), coords.y + (height / 2));
            parent.vertex(coords.x + ((width / 2) - (width / 8)), coords.y + (height / 2));
            parent.vertex(coords.x + ((width / 2) - (width / 8)), coords.y);
            parent.vertex(coords.x + (width / 2), coords.y);
            parent.endShape();
        }
    }


    public void drawCity(){
        if(this.isCity() || (overPoint() && validUpgrade() && parent.currentTool == 3)) {
                if (!this.isCity()) {
                    parent.fill(255, 0, 0, 80);
                } else {
                    parent.fill(1, 87, 155);
                }
                int tempX = coords.x - 10;
                int tempY = coords.y - 10;
                int height = 20;
                int width = 20;
                parent.beginShape();
                parent.vertex(tempX, (tempY + (height / 2)));
                parent.vertex(tempX, (tempY + height));
                parent.vertex(tempX + width, (tempY + height));
                parent.vertex(tempX + width, (tempY + (height / 2)));
                parent.vertex(tempX + width, (tempY + (height / 4)));
                parent.vertex(tempX + width - (width / 4), tempY);
                parent.vertex(tempX + (width / 2), (tempY + (height / 4)));
                parent.vertex(tempX + (width / 2), (tempY + (height / 2)));
                parent.endShape();
        }
    }

    /**
     * Check to see it this is a valid place to build
     * @return true if no neighbors are settled
     */
    public boolean validBuild() {
        for (HexPoint pt : neigbors) {
            if (pt.isSettled()) {
                return false;
            }
        }
        if (parent.model.settlementQuota >= 2) {
            int counter = roads.size();
            for (HexSide sd : roads) {
                if (sd.isBuilt()) {
                    return true;
                }
                counter--;
            }
            //if all sides contain no road
            if (counter == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean validUpgrade(){
        if(this.isSettled()){
            return true;
        } else{
            return false;
        }
    }
}

