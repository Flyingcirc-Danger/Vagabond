package Prototype4;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;

import java.awt.*;

import java.io.File;

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
            result.append("<hexside><midpoint><coordsX>" + sd.getMidPoint().x + "</coordsX><coordsY>" +
                    sd.getMidPoint().y +"</coordsY></midpoint>");
            result.append("<start><coordsX>" + sd.getStart().getX() + "</coordsX><coordsY>" +
                    sd.getStart().getY() +"</coordsY></start>");
            result.append("<end><coordsX>" + sd.getEnd().getX() + "</coordsX><coordsY>" +
                    sd.getEnd().getY() +"</coordsY></end>");
            result.append("<id>"+ sd.getId() + "</id>");
            result.append("<neighbors>");
            for(HexSide ns : sd.getNeighbors()) {
                result.append("<neighbor><coordsX>" + ns.getMidPoint().x + "</coordsX><coordsY>" +
                        ns.getMidPoint().y + "</coordsY></neighbor>");
            }
            result.append("</neighbors><borders>");
            for(HexTile bs : sd.getBorders()){
                result.append("<border><coordsX>" + bs.getCenter().x + "</coordsX><coordsY>" +
                        bs.getCenter().y+ "</coordsY></border>");
            }
            result.append("</borders>");
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
            result.append("<hexside><midpoint><coordsX>" + sd.getMidPoint().x + "</coordsX><coordsY>" +
                    sd.getMidPoint().y +"</coordsY></midpoint>");
            result.append("<start><coordsX>" + sd.getStart().getX() + "</coordsX><coordsY>" +
                    sd.getStart().getY() +"</coordsY></start>");
            result.append("<end><coordsX>" + sd.getEnd().getX() + "</coordsX><coordsY>" +
                    sd.getEnd().getY() +"</coordsY></end>");
            result.append("<id>"+ sd.getId() + "</id>");
            result.append("<neighbors>");
            for(HexSide ns : sd.getNeighbors()) {
                result.append("<neighbor><coordsX>" + ns.getMidPoint().x + "</coordsX><coordsY>" +
                        ns.getMidPoint().y + "</coordsY></neighbor>");
            }
            result.append("</neighbors><borders>");
            for(HexTile bs : sd.getBorders()){
                result.append("<border><coordsX>" + bs.getCenter().getX() + "</coordsX><coordsY>" +
                        bs.getCenter().getY() + "</coordsY></border>");
            }
            result.append("</borders>");
            result.append("<build-status>" + sd.isBuilt() + "</build-status></hexside>");

        return result.toString();
    }


    /**
     * Reads a side XML string and updates the board accordingly
      * @param model the model to update
     * @param filename the string or filename to use
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void readSides(BoardData model,String filename) throws ParserConfigurationException, IOException, SAXException {
        //setup the document reader
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //TODO change from filename
        Document doc = db.parse(new File(filename));
        NodeList hexSides = doc.getElementsByTagName("hexside");
        for (int i = 0; i < hexSides.getLength(); i++) {
            Node hexSide = hexSides.item(i);
            NodeList sideData = hexSide.getChildNodes();
            Point midpoint = new Point(Integer.parseInt(sideData.item(0).getFirstChild().getTextContent()),
                    Integer.parseInt(sideData.item(0).getLastChild().getTextContent()));
            HexSide update = model.getSideMap().get(midpoint);
            HexPoint start = findHexPoint(model, new Point(Integer.parseInt(sideData.item(1).getFirstChild().getTextContent()),
                    Integer.parseInt(sideData.item(1).getLastChild().getTextContent())));
            HexPoint end = findHexPoint(model,new Point(Integer.parseInt(sideData.item(2).getFirstChild().getTextContent()),
                    Integer.parseInt(sideData.item(2).getLastChild().getTextContent())));
            int id = Integer.parseInt(sideData.item(3).getTextContent());
            HashSet<HexSide> neighbors = new HashSet<HexSide>();
            HashSet<HexTile> borders = new HashSet<HexTile>();
            NodeList ne = sideData.item(4).getChildNodes();
            NodeList bo = sideData.item(5).getChildNodes();
            for(int j = 0; j < ne.getLength(); j++){
                neighbors.add(findHexSide(model, new Point(Integer.parseInt(ne.item(j).getFirstChild().getTextContent()),
                        Integer.parseInt(ne.item(j).getLastChild().getTextContent()))));
            }
            for(int j = 0; j < bo.getLength(); j++){
                borders.add(findHexTile(model, new Point(Integer.parseInt(ne.item(j).getFirstChild().getTextContent()),
                        Integer.parseInt(ne.item(j).getLastChild().getTextContent()))));
            }
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
     * @param point the point to generate XML for
     * @return the XML string representation of this point
     */
    public static String parseSinglePoint(BoardData model, HexPoint point,boolean independent){
        StringBuffer result = new StringBuffer();
        if(independent) {
            result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        }
        result.append("<hexpoint>");
        result.append("<coordsX>" + point.getCoords().x + "</coordsX>");
        result.append("<coordsY>" + point.getCoords().y + "</coordsY>");
        result.append("<id>" + point.getId() + "</id>");
        result.append("<neighbors>");
        for(HexPoint n : point.getNeigbors()){
            result.append("<neighbor>");
            result.append("<coordsX>" + n.getCoords().x + "</coordsX>");
            result.append("<coordsY>" + n.getCoords().y + "</coordsY>");
            result.append("</neighbor>");
        }
        result.append("</neighbors>");
        result.append("<roads>");
        for(HexSide s : point.getRoads()){
            result.append("<road>");
            result.append("<coordsX>" + s.getMidPoint().x + "</coordsX>");
            result.append("<coordsY>" +  s.getMidPoint().y + "</coordsY>");
            result.append("</road>");
        }
        result.append("</roads>");
        result.append("<build-status>" + point.getBuildStatus() + "</build-status>");
        result.append("<center-point><coordsX>" + point.getCenterCoords().getX() + "</coordsX>" +
                "<coordsY>" + point.getCenterCoords().getY() + "</coordsY></center-point>");
        result.append("</hexpoint>");
        result.append("</hexpoint>");
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
            result.append("<coordsX>" + pt.getCoords().x + "</coordsX>");
            result.append("<coordsY>" + pt.getCoords().y + "</coordsY>");
            result.append("<id>" + pt.getId() + "</id>");
            result.append("<neighbors>");
            for(HexPoint n : pt.getNeigbors()){
                result.append("<neighbor>");
                result.append("<coordsX>" + n.getCoords().x + "</coordsX>");
                result.append("<coordsY>" + n.getCoords().y + "</coordsY>");
                result.append("</neighbor>");
            }
            result.append("</neighbors>");
            result.append("<roads>");
            for(HexSide s : pt.getRoads()){
                result.append("<road>");
                result.append("<coordsX>" + s.getMidPoint().x + "</coordsX>");
                result.append("<coordsY>" +  s.getMidPoint().y + "</coordsY>");
                result.append("</road>");
            }
            result.append("</roads>");
            result.append("<build-status>" + pt.getBuildStatus() + "</build-status>");
            result.append("<center-point><coordsX>" + pt.getCenterCoords().getX() + "</coordsX>" +
                    "<coordsY>" + pt.getCenterCoords().getY() + "</coordsY></center-point>");
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
     * Reads a point XML file and updates the model accordingly
     * @param model the model to update
     * @param filename the filename location of the XML file
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void readPoints(BoardData model,String filename) throws ParserConfigurationException, IOException, SAXException {
        //setup the document reader
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //TODO change from filename
        Document doc = db.parse(new File(filename));
        NodeList hexPoints = doc.getElementsByTagName("hexpoint");
        for (int i = 0; i < hexPoints.getLength(); i++) {
            Node hexPoint = (Node) hexPoints.item(i);
            NodeList hexData = hexPoint.getChildNodes();
            Point coords = new Point(Integer.parseInt(hexData.item(0).getTextContent()), Integer.parseInt(hexData.item(1).getTextContent()));
            int id = Integer.parseInt(hexData.item(2).getTextContent());
            int buildStatus = Integer.parseInt(hexData.item(5).getTextContent());
            Point centerCoords = new Point(Integer.parseInt(hexData.item(6).getFirstChild().getTextContent()),
            Integer.parseInt(hexData.item(6).getLastChild().getTextContent()));
            HashSet<HexPoint> n = new HashSet<HexPoint>();
            HashSet<HexSide> s = new HashSet<HexSide>();
            NodeList neighbors = hexData.item(3).getChildNodes();
            NodeList roads = hexData.item(4).getChildNodes();
            for (int j = 0; j < neighbors.getLength(); j++) {
                int tempX = Integer.parseInt(neighbors.item(j).getFirstChild().getTextContent());
                int tempY = Integer.parseInt(neighbors.item(j).getLastChild().getTextContent());
                n.add(findHexPoint(model, new Point(tempX, tempY)));
            }
            for (int j = 0; j < roads.getLength(); j++) {
                int tempX = Integer.parseInt(roads.item(j).getFirstChild().getTextContent());
                int tempY = Integer.parseInt(roads.item(j).getLastChild().getTextContent());
                s.add(findHexSide(model, new Point(tempX, tempY)));
            }
            HexPoint update = findHexPoint(model,coords);
            update.setId(id);
            update.setBuildStatus(buildStatus);
            update.setNeigbors(n);
            update.setRoads(s);
            update.setCenterCoords(centerCoords);
        }
    }

    /**
     * Finds a specific HexPoint given the the point key that
     * is used in it's models hashmap
     * @param model the model to operate on
     * @param point the point key where the HexPoint is located.
     * @return
     */
    public static HexPoint findHexPoint(BoardData model, Point point){
        return model.getPointMap().get(point);
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




}



