package Prototype2;

import java.awt.*;
import java.util.*;

/**
 *
 */
public class CatanDeck {

    private HexTile[] tiles;
    private String[] resources;
    private String[] buildOrder;
    private int[] tokenValues;
    private HashMap<Point, ArrayList<String>> pointMap;
    private ArrayList<EdgeCoord> edgeCoords;

    public CatanDeck(boolean classicRules){
        if(classicRules){
            buildOrder = new String[] {"AB", "CD", "DE","EF","FA","AB","AB","BC","CD","CD","DE","DE","EF","EF","FA","FA","AB","AB"};

            tiles = new HexTile[19];
            /*
            4 Forest
            4 Grain
            4 Pastures
            3 Mines
            3 Brick
            1 Desert
             */
            resources = new String[] {
                    "forest", "forest","forest","forest",
                    "grain", "grain","grain","grain",
                    "pasture","pasture","pasture","pasture",
                    "mine","mine","mine",
                    "brick","brick","brick",
                    "desert"
                    };
            //shuffle the terrain and update the token values
            findDesert(shuffleTerrain());
            pointMap = new HashMap<Point, ArrayList<String>>();
            edgeCoords = new ArrayList<EdgeCoord>();

        }

    }


    public String[] getBuildOrder() {
        return buildOrder;
    }

    public void setBuildOrder(String[] buildOrder) {
        this.buildOrder = buildOrder;
    }

    public int[] getTokenValues() {
        return tokenValues;
    }

    public void setTokenValues(int[] tokenValues) {
        this.tokenValues = tokenValues;
    }

    public HexTile[] getTiles() {
        return tiles;
    }

    public void setTiles(HexTile[] tiles) {
        this.tiles = tiles;
    }

    public String[] getResources() {
        return resources;
    }

    public void setResources(String[] resources) {
        this.resources = resources;
    }

    public HashMap<Point, ArrayList<String>> getPointMap() {
        return pointMap;
    }

    public void setPointMap(HashMap<Point, ArrayList<String>> pointMap) {
        this.pointMap = pointMap;
    }

    public ArrayList<EdgeCoord> getEdgeCoords() {
        return edgeCoords;
    }

    public void setEdgeCoords(ArrayList<EdgeCoord> edgeCoords) {
        this.edgeCoords = edgeCoords;
    }

    /**
     * A method for populating the coordinate hashmap
     * @param coord the v2 coordinate you wish to add
     * @param order the order/id of the hex that it belongs to.
     */
    public void addCoordinate(int[] coord, int order, char name){

        Point key = arrayToPoint(coord);
        if(pointMap.containsKey(key)){
            pointMap.get(key).add(String.valueOf(order) + name);
        } else{
            ArrayList<String> value = new ArrayList<String>();
            value.add(String.valueOf(order) + name);
            pointMap.put(key,value);
        }
    }

    /**
     * Adds all the coordinates from a single hex tile
     * into the coordinate hashmap
     * @param toAdd the hextile you wish to operate on
     */
    public void bulkAddCoord(HexTile toAdd){
        int id = toAdd.getOrder();
        addCoordinate(toAdd.getA(), id, 'A');
        addCoordinate(toAdd.getB(),id, 'B');
        addCoordinate(toAdd.getC(),id, 'C');
        addCoordinate(toAdd.getD(),id, 'D');
        addCoordinate(toAdd.getE(),id, 'E');
        addCoordinate(toAdd.getF(),id, 'F');
    }

    public void popEdgeTiles(){
        Iterator it = pointMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ArrayList<String> ids = (ArrayList<String>)pair.getValue();
            if(ids.size() == 1) {
                edgeCoords.add(new EdgeCoord((Point)pair.getKey(), ids.get(0)));
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        edgeCoords.sort(Comparator.<EdgeCoord>naturalOrder());
        for(int i = 0; i < edgeCoords.size(); i++){
        }
    }






    /**
     * Converts an int array of size 2 to a Point object
     * @param toConvert the array to convert
     * @return a new Point object.
     */
    public Point arrayToPoint(int[] toConvert) throws IllegalArgumentException{
        if(toConvert.length > 2){
            throw new IllegalArgumentException();
        }
        return new Point(toConvert[0],toConvert[1]);
    }


    /**
     * Converts a point object to an int array
     * @param toConvert the point object containing X & Y coords
     * @return an int array with the point coords.
     */
    public int[] pointToArray(Point toConvert){
        int[] result = new int[] {(int) toConvert.getX(), (int) toConvert.getY()};
        return result;
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
        for (int i = resources.length - 1; i > 0; i--)
        {
            int index = seed.nextInt(i + 1);
            String temp = resources[index];
            resources[index] = resources[i];
            resources[i] = temp;
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
        this.tokenValues = resultTokens;


    }

}
