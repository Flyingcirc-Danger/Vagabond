package Prototype3.Prototype3_2;

import processing.core.PApplet;

import java.awt.*;
import java.util.HashSet;

/**
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


    public HexSide(HexPoint start, HexPoint end, int id, PApplet canvas){
        this.parent = canvas;
        this.id = id;
        this.start = start;
        this.end = end;
        findMidPoint();
    }

    private void findMidPoint(){
        int tempX = (int)((this.start.getX() + this.end.getX())/2);
        int tempY = (int)((this.start.getY() + this.end.getY())/2);
        this.midPoint = new Point(tempX,tempY);
    }

    private void display(){

    }

}
