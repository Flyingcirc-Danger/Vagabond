package Prototype3.Prototype3_3;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Tom_Bryant on 6/26/15.
 * A class containing the board mapping data
 */
public class BoardData {

    private HashMap<Point, HexPoint> pointMap;
    private ArrayList<HexPoint> edges;
    private String[] buildOrder;
    private HexTile[] hexDeck;

    private ArrayList<HexSide> sides;
    private HashMap<Point, HexSide> sideMap;


    public BoardData() {
        pointMap = new HashMap<Point, HexPoint>();
        edges = new ArrayList<HexPoint>();
        sideMap = new HashMap<Point, HexSide>();
        buildOrder = new String[]{"NA", "AB", "CD", "DE", "EF", "FA", "AB", "AB", "BC", "CD", "CD", "DE", "DE", "EF", "EF", "FA", "FA", "AB", "AB"};
        hexDeck = new HexTile[19];
    }

    public HashMap<Point, HexPoint> getPointMap() {
        return pointMap;
    }

    public void setPointMap(HashMap<Point, HexPoint> pointMap) {
        this.pointMap = pointMap;
    }

    public HashMap<Point, HexSide> getSideMap() {
        return sideMap;
    }

    public void setSideMap(HashMap<Point, HexSide> sideMap) {
        this.sideMap = sideMap;
    }

    public ArrayList<HexSide> getSides() {
        return sides;
    }

    public void setSides(ArrayList<HexSide> sides) {
        this.sides = sides;
    }

    /**
     * IDs are assigned to each point for
     * debugging purposes. They are assigned
     * based on the size of the current HashMap
     *
     * @return the ID of a new point.
     */
    public int assignPointID() {
        return this.pointMap.size();
    }

    /**
     * IDs are assigned to each side for
     * debugging purposes. They are assigned
     * based on the size of the current HashMap
     *
     * @return the ID of a new side.
     */
    public int assignSideID() {
        return this.sideMap.size();
    }


    public void makeEdge() {
        if (edges.size() == 0) {
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
        transform.x = (int) (edge.getX() + ((edge.getX() + edge.getCenterCoords().getX()) / 10));
        transform.y = (int) (edge.getY() + ((edge.getY() + edge.getCenterCoords().getY()) / 10));
        return new HexPoint(edge.getCoords(), edge.getCenterCoords(), edge.getId(), edge.getParent());
    }

    public void drawCoast(PApplet canvas) {
        makeEdge();
        canvas.fill(255, 243, 224);
        canvas.stroke(0, 0, 0);
        canvas.beginShape();
        for (HexPoint pt : edges) {
            canvas.vertex((float) pt.getX(), (float) pt.getY());
        }
        canvas.endShape();

        for (HexPoint pt : edges) {
            //pt.display();
        }
    }

    /**
     * Reads the build order, expands accordingly
     *
     * @param center
     */
    public void buildBoard(HexTile center) {
        hexDeck[0] = center;
        center.display();
        for (int i = 1; i < hexDeck.length; i++) {
            HexTile temp = hexDeck[i - 1].expand(buildOrder[i]);
            hexDeck[i] = temp;
        }
    }

    public void buildRandomBoard(HexTile center) {
        hexDeck[0] = center;
        for(int i = 1; i < 19; i++){
            hexDeck[i] = chooseSide(i-1,-1);

        }
    }


    /**
     * Chooses a side and tile to randomly expand
     * Takes into consideration whether there is a tile
     * already present
     * @param maxIndex the max index of the tiles already built
     * @param excludeTile a tile to exclude (if it's land locked)
     * @return the newly built tile
     */
    public HexTile chooseSide(int maxIndex, int excludeTile) {
        Random index = new Random();
        //chose a random int between 0 and the current index.
        int R = 0;
        if(maxIndex != 0){
             R = index.nextInt(maxIndex);
        }
        //deal with excluded index.
        if (R == excludeTile) {if (R == 0) {R = maxIndex;} else {R--; }}
        HexTile toBuild = hexDeck[R];
        //check to see if tile is landlocked.
        if (toBuild.landLocked()) {

            return chooseSide(maxIndex, R);
        }
        //Screen bounds checking

        else {
            //choose a random side
            String[] instructions = new String[]{"AB","BC","CD","DE","EF","FA"};
            Random side = new Random();
            int rS = index.nextInt(5);
            //while a neighbor exists, rotate the instructions
            while (toBuild.checkNeighbor(instructions[rS])) {
                if (rS == 0) {
                    rS = 5;
                } else {
                    rS--;
                }
            }
            if(((toBuild.getCenter().getX() < (toBuild.getRadius() * 2)) && instructions[rS].equals("EF"))
                    || ((toBuild.getCenter().getX() > Board.SCREEN_WIDTH - (toBuild.getRadius() * 2))&& instructions[rS].equals("BC"))
                    || ((toBuild.getCenter().getY() > Board.SCREEN_HEIGHT - (toBuild.getRadius() * 2))&& instructions[rS].equals("CD"))
                    || ((toBuild.getCenter().getY() > Board.SCREEN_HEIGHT - (toBuild.getRadius() * 2))&& instructions[rS].equals("DE"))
                    || ((toBuild.getCenter().getY() < (toBuild.getRadius() * 2)) && instructions[rS].equals("FA"))
                    || ((toBuild.getCenter().getY() < (toBuild.getRadius() * 2)) && instructions[rS].equals("AB"))){
                return chooseSide(maxIndex, R);
            }

            return toBuild.expand(instructions[rS]);
        }
    }


    /**
     * A method for redrawing the board.
     * OPTIONS:
     * 0 = no debug
     * 1 = point debug
     * 2 = display debug
     * 3 = debug all
     * @param option the display mode
     */
    public void displayBoard(int option){
        for(int i =0; i< hexDeck.length; i++){
            hexDeck[i].display();
            if(option == 1) {
                hexDeck[i].pointDebug();
                hexDeck[i].checkPoints();
            }
            if(option == 2) {
                hexDeck[i].sideDebug();
                hexDeck[i].checkSides();
            }
            if(option == 3){
                hexDeck[i].pointDebug();
                hexDeck[i].sideDebug();
                hexDeck[i].checkPoints();
                hexDeck[i].checkSides();
            }

        }
    }
}


