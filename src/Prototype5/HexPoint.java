package Prototype5;

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

    private Board parent; //the canvas to draw on

    private Point coords; //specific location
    private HashSet<HexPoint> neigbors; //neighbors within 1 side of this point
    private int id; //point ID for debugging and uniquness checking
    private HashSet<HexSide> roads; //roads that lead off of this point
    private HashSet<HexTile> borders;//tiles that this point lies on.

    private Point centerCoords; //the center of the hex on which this point occupies

    //build status = 0: nobuild, 1:town, 2:city
    private int buildStatus;

    //the ID of the player that has built on this point
    private int owner;

    private BoardData model;
    /**
     *
     * @param coords The coordinate of this point
     * @param centerCoords the centerCoordinate of the hex this belongs to
     * @param id the id of this hexpoint
     * @param parent the canvas to draw on
     */
    public HexPoint(Point coords, Point centerCoords, int id, Board parent, BoardData model){
        this.coords = coords;
        this.id = id;
        this.parent = parent;
        this.neigbors = new HashSet<HexPoint>();
        this.centerCoords = centerCoords;
        this.roads = new HashSet<HexSide>();
        this.borders = new HashSet<HexTile>();
        this.buildStatus = 0;
        this.owner = 0;
        this.model = model;
    }

    /**
     * Temporary empty constructor
     */
    public HexPoint(){

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
        parent.text("" + this.id, coords.x - (parent.textWidth("id")/2), coords.y + 6);
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

    public int getBuildStatus() {
        return buildStatus;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCenterCoords(Point centerCoords) {
        this.centerCoords = centerCoords;
    }

    public void setBuildStatus(int buildStatus) {
        this.buildStatus = buildStatus;
    }

    /**
     * Checks to see if a point is along an edge
     * Each point is on a hexagon so it follows
     * that if a point has 3 neighbor points, it
     * is not an edge
     * @return
     */
    public boolean isEdge() {
        if(neigbors.size() == 3){
            return false;
        }
        return true;
    }



    public int getX(){
        return (int)this.coords.getX();
    }

    public int getY(){
        return (int)this.coords.getY();
    }

    public Point getCenterCoords() {
        return centerCoords;
    }

    public PApplet getParent() {
        return parent;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int id){
        for(HexTile tile : borders){
            tile.addOwner(id);
        }
        this.owner = id;
    }


    public HashSet<HexTile> getBorders() {
        return borders;
    }

    public void setBorders(HashSet<HexTile> borders) {
        this.borders = borders;
    }

    public boolean isSettled() {
        if (this.buildStatus > 0){
            return true;
        }
        return false;
    }


    /**
     * Checks an id against this owner
     * (for building purposes)
     * @param id the id to check
     * @return true if it's a match
     */
    public boolean checkAgainstOwner(int id){
        if(id == owner){
            return true;
        }
        return false;
    }

    /**
     * Sets the build status of a tile
     * A town is 1
     * A city is 2
     * If true and not a city, buildstatus is 1, or town
     * else it's not settled.
     * @param settled
     */
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

    /**
     * Checks the buildstatus to determine
     * if a city has been built. Only a buildstatus of
     * 2 will return a true
     * @return
     */
    public boolean isCity() {
        if(this.buildStatus == 2){
            return true;
        }
        return false;
    }

    /**
     * Sets the city status of a point.
     * True = 2;
     * A point only becomes a city if it's buildstatus
     * is greater than 0;
     * @param city
     */
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
                ", settled=" + isSettled() +
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
            parent.textSize(10);
            parent.text("Bld St: " + this.buildStatus + "\nID: " + this.id, coords.x + 10, coords.y + 30);
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
            if(buildStatus > 1) {
                drawCity();
                return;
            }
            if(buildStatus == 1){


                int height = 20;
                int width = 20;
                int[] color = parent.model.getColor(owner);
                parent.fill(color[0], color[1],color[2]);
                parent.fill(255, 0, 0,80);
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
            /*check if a point is settled, if your mouse is over the point, if it's valid to build
             and if the current tool selected is town building */
            if(this.isSettled() || (overPoint() && validBuild() && parent.currentTool == 1)) {
            int height = 20;
            int width = 20;

                if(!this.isSettled()){
                    parent.fill(255, 0, 0,80);
                    parent.model.getMenus().getCard().displayTown();
                } else {
                    int[] color = parent.model.getColor(owner);
                    parent.fill(color[0], color[1],color[2]);

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

    /**
     * Draws a city icon, centered on the point
     */
    public void drawCity(){
        if(this.isCity() || (overPoint() && validUpgrade() && parent.currentTool == 3)) {
                if (!this.isCity()) {
                    parent.fill(255, 0, 0, 80);
                } else {
                    int[] color = parent.model.getColor(owner);
                    parent.fill(color[0], color[1],color[2]);
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

    /**
     * Checks to see if building a city here
     * is valid (a city can only be an upgrade
     * from a town)
     * @return
     */
    public boolean validUpgrade(){
        if(this.isSettled()){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Generates the XML for this specific point.
     * This will only be called once an action (like building)
     * is performed on this tile. It adds this points info
     * to the Manifest which once sent, will update the datastructure
     * on the connection & all clients
     */
    public void generateManifest(){
        BoardData model = parent.center.getModel();
        if(parent.model.getManifest().length() == 0){
            parent.model.initManifest();
        }
        parent.model.getManifest().append(ObjectParser.parseSinglePoint(model,this,false));
    }
}

