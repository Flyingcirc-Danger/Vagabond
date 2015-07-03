package Prototype4;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Tom_Bryant on 7/3/15.
 * A class for parsing objects into XML form
 */
public class ObjectParser {


    /**
     * Generates an XML string of a particluar hex point
     * @param model the model to operate on
     * @param point the point to generate XML for
     * @return
     */
    public static String parseSinglePount(BoardData model, HexPoint point){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
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
        result.append("</hexpoint>");
        return result.toString();
    }

    /**
     * Generates an XML string of ALL points in the data structure
     * @param model the model to operate on
     * @return
     */
    public static String parsePoints(BoardData model){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        result.append("<model>");
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
            result.append("</hexpoint>");
            current++;
            if(current < size){
                result.append("");
            }
        }
        result.append("</model>");
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
        Document doc = db.parse(new File(filename));
        NodeList hexPoints = doc.getElementsByTagName("hexpoint");
        for (int i = 0; i < hexPoints.getLength(); i++) {
            Node hexPoint = (Node) hexPoints.item(i);
            NodeList hexData = hexPoint.getChildNodes();
            Point coords = new Point(Integer.parseInt(hexData.item(0).getTextContent()), Integer.parseInt(hexData.item(1).getTextContent()));
            int id = Integer.parseInt(hexData.item(2).getTextContent());
            int buildStatus = Integer.parseInt(hexData.item(5).getTextContent());
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




}



