package Prototype3;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Tom_Bryant on 6/26/15.
 * A class containing the board mapping data
 */
public class BoardData {

    private HashMap<Point, HexPoint> pointMap;


    public BoardData(){
        pointMap = new HashMap<Point, HexPoint>();
    }

    public HashMap<Point, HexPoint> getPointMap() {
        return pointMap;
    }

    public void setPointMap(HashMap<Point, HexPoint> pointMap) {
        this.pointMap = pointMap;
    }

    public int assignID(){
        return this.pointMap.size();
    }
}


