package Prototype4;

import java.awt.*;
import java.util.ArrayList;
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
    private int displayMode;

    private ArrayList<HexCoast> coast;

    private String[] resourceTiles;

    private int[] tokens;

    public int settlementQuota;



    public BoardData() {
        this.displayMode = 0;
        pointMap = new HashMap<Point, HexPoint>();
        edges = new ArrayList<HexPoint>();
        sideMap = new HashMap<Point, HexSide>();
        buildOrder = new String[]{"NA", "AB", "CD", "DE", "EF", "FA", "AB", "AB", "BC", "CD", "CD", "DE", "DE", "EF", "EF", "FA", "FA", "AB", "AB"};
        hexDeck = new HexTile[19];
        this.coast = new ArrayList<HexCoast>();
        this.resourceTiles = new String[] {
                "forest", "forest","forest","forest",
                "grain", "grain","grain","grain",
                "pasture","pasture","pasture","pasture",
                "mine","mine","mine",
                "brick","brick","brick",
                "desert"
        };
        tokens = new int[19];
        findDesert(shuffleTerrain());
        settlementQuota = 0;
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

    public int getDisplayMode() {
        return displayMode;
    }

    public String[] getResourceTiles() {
        return resourceTiles;
    }

    public void setResourceTiles(String[] resourceTiles) {
        this.resourceTiles = resourceTiles;
    }

    public void setDisplayMode(int displayMode) {
        this.displayMode = displayMode;
    }

    public int[] getTokens() {
        return tokens;
    }

    public void setTokens(int[] tokens) {
        this.tokens = tokens;
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





    /**
     * Reads the build order, expands accordingly
     *
     * @param center
     */
    public void buildBoard(HexTile center) {
        hexDeck[0] = center;
        center.display();
        for (int i = 1; i < hexDeck.length; i++) {
            HexTile temp = hexDeck[i - 1].expand(buildOrder[i],resourceTiles[i],tokens[i]);
            hexDeck[i] = temp;
        }
        configureEdges();
    }

    /**
     * Randomly generates a board given a center tile
     * @param center
     */
    public void buildRandomBoard(HexTile center) {
        hexDeck[0] = center;
        for(int i = 1; i < 19; i++){
            hexDeck[i] = chooseSide(i-1,-1);

        }
        configureEdges();
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
                    || ((toBuild.getCenter().getX() > Prototype4.Board.SCREEN_WIDTH - (toBuild.getRadius() * 2))&& instructions[rS].equals("BC"))
                    || ((toBuild.getCenter().getY() > Prototype3.Prototype3_3.Board.SCREEN_HEIGHT - (toBuild.getRadius() * 2))&& instructions[rS].equals("CD"))
                    || ((toBuild.getCenter().getY() > Board.SCREEN_HEIGHT - (toBuild.getRadius() * 2))&& instructions[rS].equals("DE"))
                    || ((toBuild.getCenter().getY() < (toBuild.getRadius() * 2)) && instructions[rS].equals("FA"))
                    || ((toBuild.getCenter().getY() < (toBuild.getRadius() * 2)) && instructions[rS].equals("AB"))){
                return chooseSide(maxIndex, R);
            }

            return toBuild.expand(instructions[rS],resourceTiles[maxIndex+1],tokens[maxIndex+1]);
        }
    }




    /**
     * A method for redrawing the board.
     * OPTIONS:
     * 0 = no debug
     * 1 = point debug
     * 2 = display debug
     * 3 = resource debug
     * 4 = debug all
     */
    public void displayBoard(){
        int option = this.displayMode;
        for(int i = 0; i < coast.size(); i++){
            coast.get(i).shadow(2);
        }
        for(int i = 0; i < coast.size(); i++){
            coast.get(i).display();
        }
        for(int i =0; i< hexDeck.length; i++){
            hexDeck[i].display();
            hexDeck[i].checkPoints();
            hexDeck[i].checkSides();
            if(option == 1) {
                hexDeck[i].pointDebug();
                hexDeck[i].checkPoints();
            }
            if(option == 2) {
                hexDeck[i].sideDebug();
                hexDeck[i].checkSides();
            }
            if(option == 3){
                hexDeck[i].resourceDebug();
            }
            if(option == 4){
                hexDeck[i].pointDebug();
                hexDeck[i].sideDebug();
                hexDeck[i].checkPoints();
                hexDeck[i].checkSides();
            }

        }
    }

    public void checkSelected(int tool){
        if(tool == 1 || tool == 3) {
            for (int i = 0; i < hexDeck.length; i++) {
                hexDeck[i].checkSettledPoints();
            }
        }
        if(tool == 2){
            for(int i = 0; i < hexDeck.length; i++){
                hexDeck[i].checkBuiltRoads();
            }
        }
    }

    /**
     * Figures out which tiles are edge tiles
     * and populates the coast arrayList.
     */
    public void configureEdges(){
        for(int i = 0; i < hexDeck.length; i++){
            if(!hexDeck[i].landLocked()){
                HexTile ct = hexDeck[i];
                coast.add(new HexCoast(ct));
            }
        }
    }

    /**
     * A method for shuffling the deck of terrain
     * this helps generate the random map.
     * It returns the int index of the desert to
     * fix the tokens
     * adapted from:
     * http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
     */
    public int shuffleTerrain(){
        int desertIndex = 0;
        Random seed = new Random();
        for (int i = resourceTiles.length - 1; i > 0; i--)
        {
            int index = seed.nextInt(i + 1);
            String temp = resourceTiles[index];
            resourceTiles[index] = resourceTiles[i];
            resourceTiles[i] = temp;
            if(temp.equals("desert")){
                desertIndex = i;
            }
        }
        return desertIndex;

    }



    /**
     * A method for fixing the temp tokens array.
     * This method places the desert token (55) into
     * the list of tokens. This will not be rendered
     * by the front-end.
     * @param desertIndex the index where the desert is located.
     */
    public void findDesert(int desertIndex){
        int[] tempTokens = new int[] {11,3,6,5,4,9,10,8,4,11,12,9,10,8,3,6,2,5};
        int[] resultTokens = new int[19];
        int counter = 0;
        if(desertIndex == 0){
            resultTokens[0] = 55;
            for(int i=0; i <tempTokens.length;i++){
                resultTokens[i+1] = tempTokens[i];
            }
        } else {
            for (int i = 0; i < desertIndex; i++) {
                resultTokens[i] = tempTokens[i];
                counter = i;
            }
            resultTokens[counter + 1] = 55;
            for (int i = counter + 2; i < 19; i++) {
                resultTokens[i] = tempTokens[i - 1];
            }
        }
        this.tokens = resultTokens;

    }
}


