package Prototype5;



import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tom_Bryant on 8/19/15.
 * Adds Harbors to the game
 * 1 x wool 2:1
 * 1 x Brick 2:1
 * 1 X Ore 2:1
 * 1 x Grain 2:1
 * 1 x Logs 2:1
 * 4 x generic 3:1
 */
public class HarborPiece {

    private Board parent;
    private String type;
    private ArrayList<HexPoint> neighbors;
    PImage harbor;
    private Point center;

    public HarborPiece(Board parent, Point center, String type, String direction, int sideToSide){
        if(type.equals("none")){
            PImage harbor = parent.loadImage("assets/harbors/Harbor.png");
        } else {
            PImage harbor = parent.loadImage("assets/harbors/" + type + "Harbor.png");
        }
        this.parent = parent;





       // parent.image(harbor, newCenter.x - harbor.width/2, newCenter.y - harbor.height/2 );

    }


    private void initCenter(String direction,Point oldCenter, int sideToSide){
        if(direction.equals("EF")){
            this.center = new Point(oldCenter.x - (sideToSide *2 ), oldCenter.y);
        }
        if(direction.equals("FA")){
            this.center = new Point(oldCenter.x - (2 * sideToSide), oldCenter.y - (2 *sideToSide));
        }

    }


}
