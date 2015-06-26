package Prototype3.Prototype3_1;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by Tom_Bryant on 6/26/15.
 * A class containing the board mapping data
 */
public class BoardData {

    private HashMap<Point, HexPoint> pointMap;
    private ArrayList<HexPoint> edges;
    private String[] buildOrder;
    private HexTile[] hexDeck;


    public BoardData(){
        pointMap = new HashMap<Point, HexPoint>();
        edges = new ArrayList<HexPoint>();
        buildOrder = new String[] {"NA","AB", "CD", "DE","EF","FA","AB","AB","BC","CD","CD","DE","DE","EF","EF","FA","FA","AB","AB"};
        hexDeck = new HexTile[19];
    }

    public HashMap<Point, HexPoint> getPointMap() {
        return pointMap;
    }

    public void setPointMap(HashMap<Point, HexPoint> pointMap) {
        this.pointMap = pointMap;
    }

    /**
     * IDs are assigned to each point for
     * debugging purposes. They are assigned
     * based on the size of the current HashMap
     * @return the ID of a new point.
     */
    public int assignID(){
        return this.pointMap.size();
    }

    public void makeEdge(){
        if(edges.size() == 0) {
            for (Point key : pointMap.keySet()) {
                if (pointMap.get(key).isEdge()) {
                    edges.add((HexPoint) edgeToCoast(pointMap.get(key)));
                }
            }
            edges.sort(Comparator.<HexPoint>naturalOrder());
        }
    }

    public HexPoint edgeToCoast(HexPoint edge) {
        Point transform = new Point();
        transform.x = (int)(edge.getX() + ((edge.getX()+ edge.getCenterCoords().getX())/10));
        transform.y = (int)(edge.getY() + ((edge.getY()+edge.getCenterCoords().getY())/10));
        return new HexPoint(edge.getCoords(),edge.getCenterCoords(),edge.getId(), edge.getParent());
    }

    public void drawCoast(PApplet canvas){
        makeEdge();
        canvas.fill(255,243,224);
        canvas.stroke(0, 0, 0);
        canvas.beginShape();
        for(HexPoint pt : edges){
            canvas.vertex((float)pt.getX(),(float)pt.getY());
        }
        canvas.endShape();

        for (HexPoint pt : edges) {
            //pt.display();
        }
    }

    public void buildBoard(HexTile center){
        hexDeck[0] = center;
        center.display();
        for(int i = 1; i < hexDeck.length; i++){
            HexTile temp = hexDeck[i-1].expand(buildOrder[i]);
            hexDeck[i] = temp;
        }
    }

    public void displayBoard(){
       // drawCoast(hexDeck[0].getParent());
        for(int i =0; i< hexDeck.length; i++){
            hexDeck[i].display();
            hexDeck[i].pointDebug();
            hexDeck[i].checkPoints();
        }
    }
}


