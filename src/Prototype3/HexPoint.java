package Prototype3;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Tom_Bryant on 6/26/15.
 */
public class HexPoint {

    private PApplet parent;

    private Point coords;
    private HashSet<HexPoint> neigbors;
    private int id;
    private boolean edge;


    public HexPoint(Point coords, int id, PApplet parent){
        this.coords = coords;
        this.id = id;
        this.edge = true;
        this.parent = parent;
        this.neigbors = new HashSet<HexPoint>();
    }

    /**
     * A method for displaying this point on the hex.
     */
    public void display(){
        parent.fill(255);
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
        return edge;
    }

    public void setEdge(boolean edge) {
        this.edge = edge;
    }

    public double getX(){
        return this.coords.getX();
    }

    public double getY(){
        return this.coords.getY();
    }

    @Override
    public String toString() {
        return "HexPoint{" +
                "id=" + id +
                ", coords=" + coords +
                ", edge=" + edge +
                '}';
    }

    public void mapNeigbors(){
        for(HexPoint pt : neigbors) {
            parent.stroke(255, 0, 0);
            parent.strokeWeight(5);
            parent.line(coords.x, coords.y, (float)pt.getX(),(float)pt.getY());
        }
    }

    boolean overPoint() {
        int x = coords.x;
        int y = coords.y;
        int width = 10;
        int height = 20;
        if (parent.mouseX >= x && parent.mouseX <= x+width &&
                parent.mouseY >= y && parent.mouseY <= y+height) {
            return true;
        } else {
            return false;
        }
    }
}

