package Prototype4;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;

import java.awt.*;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.util.HashSet;


/**
 * Created by Tom_Bryant on 7/3/15.
 * A class for parsing objects into XML form
 */
public class ObjectParser {


    public static String parseModel(BoardData model){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        result.append("<model>");
        result.append("<identity>" + model.getIdentityToken() + "</identity>");
        result.append(parseMaps(model, false));
        result.append(parseTiles(model, false));
        result.append(parsePoints(model, false));
        result.append(parseSides(model, false));
        result.append("</model>");
        return result.toString();
    }

    /**
     * Generates an XML string of all tiles in a given model
     * @param model the model to operate on
     * @return the XML result string
     */
    public static String parseTiles(BoardData model, boolean independent){
        StringBuffer result = new StringBuffer();
        if(independent) {
            result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        }
        result.append("<tiles>");
        for(HexTile tile : model.getTileMap().values()) {
            result.append("<hextile>");
            //points
            result.append("<tilepoints>");
            result.append("<center>");
            result.append("<coordsX>" + tile.getCenter().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getCenter().y +  "</coordsY>");
            result.append("</center>");
            result.append("<A>");
            result.append(parseSinglePoint(model, tile.getA(),false));
            result.append("</A>");
            result.append("<B>");
            result.append(parseSinglePoint(model, tile.getB(),false));
            result.append("</B>");
            result.append("<C>");
            result.append(parseSinglePoint(model, tile.getC(),false));
            result.append("</C>");
            result.append("<D>");
            result.append(parseSinglePoint(model, tile.getD(),false));
            result.append("</D>");
            result.append("<E>");
            result.append(parseSinglePoint(model, tile.getE(),false));
            result.append("</E>");
            result.append("<F>");
            result.append(parseSinglePoint(model, tile.getF(),false));
            result.append("</F>");
            result.append("</tilepoints>");
            //sides
            result.append("<tilesides>");
            result.append("<AB>");
            result.append(parseSingleSide(model, tile.getAB(),false));
            result.append("</AB>");
            result.append("<BC>");
            result.append(parseSingleSide(model, tile.getBC(),false));
            result.append("</BC>");
            result.append("<CD>");
            result.append(parseSingleSide(model, tile.getCD(),false));
            result.append("</CD>");
            result.append("<DE>");
            result.append(parseSingleSide(model, tile.getDE(),false));
            result.append("</DE>");
            result.append("<EF>");
            result.append(parseSingleSide(model, tile.getEF(),false));
            result.append("</EF>");
            result.append("<FA>");
            result.append(parseSingleSide(model, tile.getFA(),false));
            result.append("</FA>");
            result.append("</tilesides>");
            result.append("<radius>" + tile.getRadius() + "</radius>");
            result.append("<sidetoside>" + tile.getSideToSide() + "</sidetoside>");
            result.append("<canopyheight>" + tile.getCanopyHeight() + "</canopyheight>");
            result.append("<resource>" + tile.getResource() + "</resource>");
            result.append("<value>" + tile.getValue() + "</value>");
            result.append("</hextile>");
        }
        result.append("</tiles>");
        return result.toString();
    }

    /**
     * Generates an XML string of a specific tile in a given model
     * @param model the model to operate on
     * @return the XML result string
     */
    public static String parseSingleTile(BoardData model, HexTile tile,boolean independent){
        StringBuffer result = new StringBuffer();
        if(independent) {
            result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        }
            result.append("<hextile>");
            //points
            result.append("<tilepoints>");
            result.append("<center>");
            result.append("<coordsX>" + tile.getCenter().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getCenter().y +  "</coordsY>");
            result.append("</center>");
            result.append("<A>");
            result.append("<coordsX>" + tile.getA().getX() +  "</coordsX>");
            result.append("<coordsY>" + tile.getB().getX() +  "</coordsY>");
            result.append("</A>");
            result.append("<B>");
            result.append("<coordsX>" + tile.getB().getX() +  "</coordsX>");
            result.append("<coordsY>" + tile.getB().getY() +  "</coordsY>");
            result.append("</B>");
            result.append("<C>");
            result.append("<coordsX>" + tile.getC().getX() +  "</coordsX>");
            result.append("<coordsY>" + tile.getC().getY() +  "</coordsY>");
            result.append("</C>");
            result.append("<D>");
            result.append("<coordsX>" + tile.getD().getX() +  "</coordsX>");
            result.append("<coordsY>" + tile.getD().getY() +  "</coordsY>");
            result.append("</D>");
            result.append("<E>");
            result.append("<coordsX>" + tile.getE().getX() +  "</coordsX>");
            result.append("<coordsY>" + tile.getE().getY() +  "</coordsY>");
            result.append("</E>");
            result.append("<F>");
            result.append("<coordsX>" + tile.getF().getX() +  "</coordsX>");
            result.append("<coordsY>" + tile.getF().getY() +  "</coordsY>");
            result.append("</F>");
            result.append("</tilepoints>");
            //sides
            result.append("<tilesides>");
            result.append("<AB>");
            result.append("<coordsX>" + tile.getAB().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getAB().getMidPoint().y +  "</coordsY>");
            result.append("</AB>");
            result.append("<BC>");
            result.append("<coordsX>" + tile.getBC().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getBC().getMidPoint().y +  "</coordsY>");
            result.append("</BC>");
            result.append("<CD>");
            result.append("<coordsX>" + tile.getCD().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getCD().getMidPoint().y +  "</coordsY>");
            result.append("</CD>");
            result.append("<DE>");
            result.append("<coordsX>" + tile.getDE().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getDE().getMidPoint().y +  "</coordsY>");
            result.append("</DE>");
            result.append("<EF>");
            result.append("<coordsX>" + tile.getEF().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getEF().getMidPoint().y +  "</coordsY>");
            result.append("</EF>");
            result.append("<FA>");
            result.append("<coordsX>" + tile.getFA().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getFA().getMidPoint().y +  "</coordsY>");
            result.append("</FA>");
            result.append("</tilesides>");
            result.append("<radius>" + tile.getRadius() + "</radius>");
            result.append("<sidetoside>" + tile.getSideToSide() + "</sidetoside>");
            result.append("<canopyheight>" + tile.getCanopyHeight() + "</canopyheight>");
            result.append("<resource>" + tile.getResource() + "</resource>");
            result.append("<value>" + tile.getValue() + "</value>");
            result.append("</hextile>");

        return result.toString();
    }


    /**
     * Generates an XML string of ALL sides in a given
     * data model
     * @param model the data model to parse
     * @return the XML string representation of this model
     */
    public static String parseSides(BoardData model,boolean independent){
        StringBuffer result = new StringBuffer();
        if(independent) {
            result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        }
        result.append("<sides>");
        for(HexSide sd : model.getSideMap().values()){
            //index 0: midpoint
            result.append("<hexside><midpoint><coordsX>" + sd.getMidPoint().x + "</coordsX><coordsY>" +
                    sd.getMidPoint().y +"</coordsY></midpoint>");
            //index 1: start
            result.append("<start>");
            result.append("<coordsX>" + sd.getStart().getX() + "</coordsX><coordsY>" +
                    sd.getStart().getY() +"</coordsY>");
            result.append("</start>");
            //index 2: end
            result.append("<end>");
            result.append("<coordsX>" + sd.getEnd().getX() + "</coordsX><coordsY>" +
                    sd.getEnd().getY() +"</coordsY>");
            result.append("</end>");
            //index 3: id
            result.append("<id>"+ sd.getId() + "</id>");
            //index 4: neighbors
            result.append("<neighbors>");
            for(HexSide ns : sd.getNeighbors()) {
                result.append("<neighbor><coordsX>" + ns.getMidPoint().x + "</coordsX><coordsY>" +
                        ns.getMidPoint().y + "</coordsY></neighbor>");
            }
            result.append("</neighbors><borders>");
            //index 5: borders
            for(HexTile bs : sd.getBorders()){
                result.append("<border><coordsX>" + bs.getCenter().x + "</coordsX><coordsY>" +
                        bs.getCenter().y + "</coordsY></border>");
            }
            result.append("</borders>");
            //index 6: build status
            result.append("<build-status>" + sd.isBuilt() + "</build-status></hexside>");

        }
        result.append("</sides>");
        return result.toString();
    }

    /**
     * Generates an XML string of a particular hexSide
     * @param model the model to operate on
     * @param sd the side to parse
     * @return the XML string representation of this side.
     */
    public static String parseSingleSide(BoardData model, HexSide sd,boolean independent){
        StringBuffer result = new StringBuffer();
        if(independent) {
            result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        }
            //index 0: midpoint
            result.append("<hexside><midpoint><coordsX>" + sd.getMidPoint().x + "</coordsX><coordsY>" +
                    sd.getMidPoint().y +"</coordsY></midpoint>");
            //index 1: start
            result.append("<start>");
            result.append("<coordsX>" + sd.getStart().getX() + "</coordsX><coordsY>" +
                    sd.getStart().getY() +"</coordsY>");
            result.append("</start>");
            //index 2: end
            result.append("<end>");
            result.append("<coordsX>" + sd.getEnd().getX() + "</coordsX><coordsY>" +
                    sd.getEnd().getY() +"</coordsY>");
            result.append("</end>");
            //index 3: id
            result.append("<id>"+ sd.getId() + "</id>");
            //index 4: neighbors
            result.append("<neighbors>");
            for(HexSide ns : sd.getNeighbors()) {
                result.append("<neighbor><coordsX>" + ns.getMidPoint().x + "</coordsX><coordsY>" +
                        ns.getMidPoint().y + "</coordsY></neighbor>");
            }
            result.append("</neighbors><borders>");
            //index 5: borders
            for(HexTile bs : sd.getBorders()){
                result.append("<border><coordsX>" + bs.getCenter().x + "</coordsX><coordsY>" +
                        bs.getCenter().y + "</coordsY></border>");
            }
            result.append("</borders>");
            //index 6: build status
            result.append("<build-status>" + sd.isBuilt() + "</build-status></hexside>");
        return result.toString();
    }

    /**
     * A helper method that converts the XML
     * string to a DOM doc if a DOM doc isn't avaliable
     * @param model
     * @param XML
     */

    public static void readSides(BoardData model,String XML) {
        Document doc = null;
        try {
            doc = stringToDom(XML);
            readSides(model,doc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    /**
     * Reads a side XML string and updates the board accordingly
      * @param model the model to update
     * @param doc the DOM to use
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void readSides(BoardData model, Document doc) {

        NodeList hexSides = doc.getElementsByTagName("hexside");
        for (int i = 0; i < hexSides.getLength(); i++) {
            Node hexSide = hexSides.item(i);
            NodeList sideData = hexSide.getChildNodes();
            //index 0: midpoint
            Point midpoint = new Point(Integer.parseInt(sideData.item(0).getFirstChild().getTextContent()),
                    Integer.parseInt(sideData.item(0).getLastChild().getTextContent()));
            HexSide update = model.getSideMap().get(midpoint);
            //index 1: start
            HexPoint start = model.getPointMap().get(new Point(Integer.parseInt(sideData.item(1).getFirstChild().getTextContent()),
                    Integer.parseInt(sideData.item(1).getLastChild().getTextContent())));
            //index 2: end
            HexPoint end = model.getPointMap().get(new Point(Integer.parseInt(sideData.item(2).getFirstChild().getTextContent()),
                    Integer.parseInt(sideData.item(2).getLastChild().getTextContent())));
            //index 3: id
            int id = Integer.parseInt(sideData.item(3).getTextContent());
            HashSet<HexSide> neighbors = new HashSet<HexSide>();
            HashSet<HexTile> borders = new HashSet<HexTile>();
            //index 4: neighbors
            NodeList ne = sideData.item(4).getChildNodes();
            //index 5: borders
            NodeList bo = sideData.item(5).getChildNodes();
            for(int j = 0; j < ne.getLength(); j++){
                neighbors.add(findHexSide(model, new Point(Integer.parseInt(ne.item(j).getFirstChild().getTextContent()),
                        Integer.parseInt(ne.item(j).getLastChild().getTextContent()))));
            }
            for(int j = 0; j < bo.getLength(); j++){
                borders.add(findHexTile(model, new Point(Integer.parseInt(ne.item(j).getFirstChild().getTextContent()),
                        Integer.parseInt(ne.item(j).getLastChild().getTextContent()))));
            }
            //index 6: built
            boolean built = Boolean.parseBoolean(sideData.item(6).getTextContent());
            update.setStart(start);
            update.setEnd(end);
            update.setId(id);
            update.setNeighbors(neighbors);
            update.setBorders(borders);
            update.setBuilt(built);
        }

    }


    /**
     * Generates an XML string of a particular hex point
     * @param model the model to operate on
     * @param pt the point to generate XML for
     * @return the XML string representation of this point
     */
    public static String parseSinglePoint(BoardData model, HexPoint pt,boolean independent){
        StringBuffer result = new StringBuffer();
        if(independent) {
            result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        }
            result.append("<hexpoint>");
            //coords - index 1 & 2
            result.append("<coordsX>" + pt.getCoords().x + "</coordsX>");
            result.append("<coordsY>" + pt.getCoords().y + "</coordsY>");
            result.append("<neighbors>");
            //neighbors index 3
            for(HexPoint n : pt.getNeigbors()){
                result.append("<neighbor>");
                result.append("<coordsX>" + n.getCoords().x + "</coordsX>");
                result.append("<coordsY>" + n.getCoords().y + "</coordsY>");
                result.append("</neighbor>");
            }
            result.append("</neighbors>");
            //roadss - index 4
            result.append("<roads>");
            for(HexSide s : pt.getRoads()){
                result.append("<road>");
                result.append("<coordsX>" + s.getMidPoint().x + "</coordsX>");
                result.append("<coordsY>" +  s.getMidPoint().y + "</coordsY>");
                result.append("</road>");
            }
            result.append("</roads>");
            //build-status - index 5
            result.append("<build-status>" + pt.getBuildStatus() + "</build-status>");
            //center-coords - index 6
            result.append("<center-coords>");
            result.append("<coordsX>"+ pt.getCenterCoords().x + "</coordsX>");
            result.append("<coordsY>"+ pt.getCenterCoords().y + "</coordsY>");
            result.append("</center-coords>");
            result.append("</hexpoint>");
        return result.toString().trim();
    }


    public static String parseMaps(BoardData model, boolean independent){
        StringBuffer result = new StringBuffer();
        if(independent) {
            result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        }
        //coords, center, id, parent
        result.append("<maps><point-map>");
        for(HexPoint pt: model.getPointMap().values()){
            result.append("<point>");
            result.append("<coordsX>" + pt.getCoords().x + "</coordsX>");
            result.append("<coordsY>" + pt.getCoords().y + "</coordsY>");
            result.append("<id>" + pt.getId() + "</id>");
            result.append("<center-coordsX>" + pt.getCenterCoords().getX() + "</center-coordsX>" +
                    "<center-coordsY>" + pt.getCenterCoords().getY() + "</center-coordsY>");
            result.append("</point>");
        }
        result.append("</point-map><side-map>");
        for(HexSide sd : model.getSideMap().values()){
            //start, end,midpoint,id,parent
            result.append("<side>");
            result.append("<start>");
            result.append("<coordsX>" + sd.getStart().getX() + "</coordsX><coordsY>" +
                    sd.getStart().getY() +"</coordsY>");
            result.append("</start>");
            result.append("<end>");
            result.append("<coordsX>" + sd.getEnd().getX() + "</coordsX><coordsY>" +
                    sd.getEnd().getY() +"</coordsY>");
            result.append("</end>");
            result.append("<midpoint><coordsX>" + sd.getMidPoint().x + "</coordsX><coordsY>" +
                    sd.getMidPoint().y +"</coordsY></midpoint>");
            result.append("<id>" + sd.getId() + "</id>");
            result.append("</side>");
        }
        result.append("</side-map>");
        result.append("<tile-map>");
        for(HexTile tile : model.getTileMap().values()){
            //parent, center x, center y, radius, model, resource value
            result.append("<tile>");
            result.append("<center-point><coordsX>" + tile.getCenter().x + "</coordsX>" +
                    "<coordsY>" + tile.getCenter().y + "</coordsY></center-point>");
            result.append("<radius>" + tile.getRadius() + "</radius>");
            result.append("<resource>" + tile.getResource() + "</resource>");
            result.append("<value>" + tile.getValue() + "</value>");
            result.append("</tile>");
        }
        result.append("</tile-map></maps>");
        return result.toString();
    }

    /**
     * Generates an XML string of ALL points in the data structure
     * @param model the model to operate on
     * @return
     */
    public static String parsePoints(BoardData model,boolean independent){
        StringBuffer result = new StringBuffer();
        if(independent) {
            result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        }
        result.append("<points>");
        int size = model.getPointMap().size();
        int current = 0;
        for(HexPoint pt : model.getPointMap().values()){
            result.append("<hexpoint>");
            //coords - index 1 & 2
            result.append("<coordsX>" + pt.getCoords().x + "</coordsX>");
            result.append("<coordsY>" + pt.getCoords().y + "</coordsY>");
            result.append("<neighbors>");
            //neighbors index 3
            for(HexPoint n : pt.getNeigbors()){
                result.append("<neighbor>");
                result.append("<coordsX>" + n.getCoords().x + "</coordsX>");
                result.append("<coordsY>" + n.getCoords().y + "</coordsY>");
                result.append("</neighbor>");
            }
            result.append("</neighbors>");
            //roadss - index 4
            result.append("<roads>");
            for(HexSide s : pt.getRoads()){
                result.append("<road>");
                result.append("<coordsX>" + s.getMidPoint().x + "</coordsX>");
                result.append("<coordsY>" +  s.getMidPoint().y + "</coordsY>");
                result.append("</road>");
            }
            result.append("</roads>");
            //build-status - index 5
            result.append("<build-status>" + pt.getBuildStatus() + "</build-status>");
            //center-coords - index 6
            result.append("<center-coords>");
            result.append("<coordsX>"+ pt.getCenterCoords().x + "</coordsX>");
            result.append("<coordsY>"+ pt.getCenterCoords().y + "</coordsY>");
            result.append("</center-coords>");
            result.append("</hexpoint>");
            current++;
            if(current < size){
                result.append("");
            }
        }
        result.append("</points>");
        return result.toString().trim();
    }

    /**
     * Helper method that makes a doc out of an
     * XML string if a DOM doc isn't avaliable
     * @param model
     * @param XML
     */
    public static void readPoints(BoardData model,String XML) {
        Document doc = null;
        try {
            doc = stringToDom(XML);
            readPoints(model,doc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reads a point XML file and updates the model accordingly
     * @param model the model to update
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void readPoints(BoardData model,Document doc)  {

            NodeList hexPoints = doc.getElementsByTagName("hexpoint");
            for (int i = 0; i < hexPoints.getLength(); i++) {
                Node hexPoint = (Node) hexPoints.item(i);
                NodeList hexData = hexPoint.getChildNodes();
                //identifier point - coords - index 0 & 1
                Point coords = new Point(Integer.parseInt(hexData.item(0).getTextContent()), Integer.parseInt(hexData.item(1).getTextContent()));
                //id - index 2
                int id = Integer.parseInt(hexData.item(2).getTextContent());
                //build status - index 5
                int buildStatus = Integer.parseInt(hexData.item(5).getTextContent());
                //center coords - index 6
                Point centerCoords = new Point(Integer.parseInt(hexData.item(6).getFirstChild().getTextContent()),
                        Integer.parseInt(hexData.item(6).getLastChild().getTextContent()));
                HashSet<HexPoint> n = new HashSet<HexPoint>();
                HashSet<HexSide> s = new HashSet<HexSide>();
                NodeList neighbors = hexData.item(3).getChildNodes();
                NodeList roads = hexData.item(4).getChildNodes();
                //neighbors index 3;
                for (int j = 0; j < neighbors.getLength(); j++) {
                    NodeList ne = neighbors.item(j).getChildNodes();
                    Point key = new Point(Integer.parseInt(ne.item(0).getTextContent()),
                            Integer.parseInt(ne.item(1).getTextContent()));

                    n.add(model.getPointMap().get(key));
                }
                //roads index 4
                for (int j = 0; j < roads.getLength(); j++) {
                    int tempX = Integer.parseInt(roads.item(j).getFirstChild().getTextContent());
                    int tempY = Integer.parseInt(roads.item(j).getLastChild().getTextContent());
                    s.add(findHexSide(model, new Point(tempX, tempY)));
                }
                HexPoint update = model.getPointMap().get(coords);
                update.setBuildStatus(buildStatus);
                update.setNeigbors(n);
                update.setRoads(s);
        }

    }


    public static void readModel(BoardData model, String XML) {
        Document doc = null;
        try {
            doc = stringToDom(XML);
            //initialize the maps
            readMaps(model,doc);
            //fix the points
            readPoints(model,doc);
            //fix sides
            readSides(model,doc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts an XML string to a DOM
     * object
     * @param XML the XML string
     * @return a dom Object
     */
    public static Document stringToDom(String XML) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = null;
        db = dbf.newDocumentBuilder();
        return db.parse(new ByteArrayInputStream(XML.getBytes()));
    }

    /**
     * If a DOM isn't constructed
     * this helper method builds one.
     * @param model the model to operate on
     * @param XML the XML string to create an object.
     */
    public static void readMaps(BoardData model, String XML){
        try {
            Document doc = stringToDom(XML);
            readMaps(model,doc);
        } catch (IOException e) {
        e.printStackTrace();
        } catch (SAXException e) {
        e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the hash map section of the XML model,
     * populates the hashmaps accordingly.
     * @param model the model that holds the hashmaps
     * @param doc the DOM to manipulate the model
     */
    public static void readMaps(BoardData model, Document doc){
            //init tile map
            NodeList tileMap = doc.getElementsByTagName("tile-map");
            NodeList mapChildren = tileMap.item(0).getChildNodes();
            for(int i = 0; i < mapChildren.getLength(); i++) {
                NodeList tile = mapChildren.item(i).getChildNodes();
                HexTile initTile = generateTileFromNode(tile, model);
                model.getTileMap().put(initTile.getCenter(), initTile);
            }
            //init point map
            NodeList pointMap = doc.getElementsByTagName("point-map");
            mapChildren = pointMap.item(0).getChildNodes();
            for(int i = 0; i < mapChildren.getLength(); i++) {
                NodeList pt = mapChildren.item(i).getChildNodes();
                HexPoint initPoint = generatePointFromNode(pt, model);
                model.getPointMap().put(initPoint.getCoords(), initPoint);
            }
            //init side map
            NodeList sideMap = doc.getElementsByTagName("side-map");
            mapChildren = sideMap.item(0).getChildNodes();
            for(int i = 0; i < mapChildren.getLength(); i++){
                NodeList sd = mapChildren.item(i).getChildNodes();
                HexSide initside = generateSideFromNode(sd,model);
                model.getSideMap().put(initside.getMidPoint(), initside);
            }
    }








    /**
     * Places the HexPoint in the models
     * pont map. If it already exists, the point
     * is updated. Returns the inserted point
     */
    public static HexPoint updateHexPoint(BoardData model, HexPoint toUpdate){
            HexPoint result = model.getPointMap().get(toUpdate.getCoords());
            result.setId(toUpdate.getId());
            result.setBuildStatus(toUpdate.getBuildStatus());
            result.setNeigbors(toUpdate.getNeigbors());
            result.setRoads(toUpdate.getRoads());
            return result;
    }


    /**
     * Finds a specific HexSide given the the point key that
     * is used in it's models hashmap
     * @param model the model to operate on
     * @param point the point key where the HexSide is located.
     * @return
     */
    public static HexSide findHexSide(BoardData model, Point point){
        return model.getSideMap().get(point);
    }

    public static HexTile findHexTile(BoardData model, Point point){
        return model.getTileMap().get(point);
    }

    /**
     * Given a point NodeList, this method generates a HexPoint from
     * the data
     * @param point the node list containing the data
     * @param model the model containing the points
     * @return a new HexPoint
     */
    private static HexPoint generatePointFromNode(NodeList point, BoardData model){
        Point tempXY = new Point(Integer.parseInt(point.item(0).getTextContent()),
                Integer.parseInt(point.item(1).getTextContent()));
        int nId = Integer.parseInt(point.item(2).getTextContent());
        Point tempCXY = new Point(Integer.parseInt(point.item(3).getTextContent()),
                Integer.parseInt(point.item(4).getTextContent()));
        return new HexPoint(tempXY, tempCXY, nId, model.getParent());
    }


    /**
     * Given a side NodeList, this method generates a HexSide from
     * the data
     * @param side the node list containing the data
     * @param model the model containing the points
     * @return a new HexPoint
     */
    private static HexSide generateSideFromNode(NodeList side, BoardData model){
        //start, end, midpoint, id
        HexPoint startXY = model.getPointMap().get(new Point(Integer.parseInt(
                side.item(0).getFirstChild().getTextContent()),
                Integer.parseInt(side.item(0).getLastChild().getTextContent())));
        HexPoint endXY = model.getPointMap().get(new Point(Integer.parseInt(side.item(1).getFirstChild().getTextContent()),
                Integer.parseInt(side.item(2).getLastChild().getTextContent())));
        Node mp = side.item(3);
        Point midPoint = new Point(Integer.parseInt(mp.getFirstChild().getTextContent()),
                Integer.parseInt(mp.getLastChild().getTextContent()));
        int id = Integer.parseInt(side.item(4).getTextContent());
        return new HexSide(startXY,endXY,midPoint,id,model.getParent());

    }

    /**
     * Given a tile NodeList, this method generates a HexTile from
     * the data
     * @param tile the node list containing the data
     * @param model the model containing the points
     * @return a new HexPoint
     */
    private static HexTile generateTileFromNode(NodeList tile, BoardData model){
        //centerpoints, radius resource, value
        Point center = new Point(Integer.parseInt(tile.item(0).getFirstChild().getTextContent()),
                Integer.parseInt(tile.item(0).getLastChild().getTextContent()));
        int radius = Integer.parseInt(tile.item(1).getTextContent());
        String resource = tile.item(2).getTextContent();
        int value = Integer.parseInt(tile.item(3).getTextContent());
        return new HexTile(model.getParent(), center.getX(), center.getY(),radius,model,resource,value);
    }



}



