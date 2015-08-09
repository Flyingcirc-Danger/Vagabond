package Prototype5;

import ServerPrototype1.Game;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
        saveOutput(result.toString(), "model.xml");
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
            result.append("<coordsX>" + tile.getA().getCoords().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getA().getCoords().y + "</coordsY>");
            result.append("</A>");
            result.append("<B>");
            result.append("<coordsX>" + tile.getB().getCoords().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getB().getCoords().y + "</coordsY>");
            result.append("</B>");
            result.append("<C>");
            result.append("<coordsX>" + tile.getC().getCoords().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getC().getCoords().y + "</coordsY>");
            result.append("</C>");
            result.append("<D>");
            result.append("<coordsX>" + tile.getD().getCoords().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getD().getCoords().y + "</coordsY>");
            result.append("</D>");
            result.append("<E>");
            result.append("<coordsX>" + tile.getE().getCoords().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getE().getCoords().y + "</coordsY>");
            result.append("</E>");
            result.append("<F>");
            result.append("<coordsX>" + tile.getF().getCoords().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getF().getCoords().y + "</coordsY>");
            result.append("</F>");
            result.append("</tilepoints>");
            //sides
            result.append("<tilesides>");
            result.append("<AB>");
            result.append("<coordsX>" + tile.getAB().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getAB().getMidPoint().y + "</coordsY>");
            result.append("</AB>");
            result.append("<BC>");
            result.append("<coordsX>" + tile.getBC().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getBC().getMidPoint().y + "</coordsY>");
            result.append("</BC>");
            result.append("<CD>");
            result.append("<coordsX>" + tile.getCD().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getCD().getMidPoint().y + "</coordsY>");
            result.append("</CD>");
            result.append("<DE>");
            result.append("<coordsX>" + tile.getDE().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getDE().getMidPoint().y + "</coordsY>");
            result.append("</DE>");
            result.append("<EF>");
            result.append("<coordsX>" + tile.getEF().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getEF().getMidPoint().y + "</coordsY>");
            result.append("</EF>");
            result.append("<FA>");
            result.append("<coordsX>" + tile.getFA().getMidPoint().x +  "</coordsX>");
            result.append("<coordsY>" + tile.getFA().getMidPoint().y + "</coordsY>");
            result.append("</FA>");
            result.append("</tilesides>");
            result.append("<radius>" + tile.getRadius() + "</radius>");
            result.append("<sidetoside>" + tile.getSideToSide() + "</sidetoside>");
            result.append("<canopyheight>" + tile.getCanopyHeight() + "</canopyheight>");
            result.append("<resource>" + tile.getResource() + "</resource>");
            result.append("<value>" + tile.getValue() + "</value>");
            result.append("<robber>" + tile.isRobber() + "</robber>");
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
            result.append("<robber>" + tile.isRobber() + "</robber>");
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
            result.append("<build-status>" + sd.isBuilt() + "</build-status>");
            //index 7: owner
            result.append("<owner>" + sd.getOwner() + "</owner></hexside>");

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
            result.append("<build-status>" + sd.isBuilt() + "</build-status>");
            //index 7: owner
            result.append("<owner>" + sd.getOwner() + "</owner></hexside>");
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
                borders.add(findHexTile(model, new Point(Integer.parseInt(bo.item(j).getFirstChild().getTextContent()),
                        Integer.parseInt(bo.item(j).getLastChild().getTextContent()))));
            }
            //index 6: built
            boolean built = Boolean.parseBoolean(sideData.item(6).getTextContent());
            //index 7: owner
            int owner = Integer.parseInt(sideData.item(7).getTextContent());
            update.setStart(start);
            update.setEnd(end);
            update.setId(id);
            update.setNeighbors(neighbors);
            update.setBorders(borders);
            update.setBuilt(built);
            update.setOwner(owner);
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
            //coords - index 0 & 1
            result.append("<coordsX>" + pt.getCoords().x + "</coordsX>");
            result.append("<coordsY>" + pt.getCoords().y + "</coordsY>");
            result.append("<id>" + pt.getId() + "</id>");
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
            result.append("<owner>"+ pt.getOwner() + "</owner>");
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
            result.append("<center-coordsX>" + pt.getCenterCoords().x + "</center-coordsX>" +
                    "<center-coordsY>" + pt.getCenterCoords().y + "</center-coordsY>");
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
            //coords - index 0 & 1
            result.append("<coordsX>" + pt.getCoords().x + "</coordsX>");
            result.append("<coordsY>" + pt.getCoords().y + "</coordsY>");
            result.append("<id>" + pt.getId() + "</id>");
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
            result.append("<owner>"+ pt.getOwner() + "</owner>");
            result.append("</hexpoint>");
            current++;
            if(current < size){
                result.append("");
            }
        }
        result.append("</points>");
        return result.toString().trim();
    }



    public static void readTiles(BoardData model, Document doc){
        NodeList hexTiles = doc.getElementsByTagName("hextile");
        for(int i = 0; i < hexTiles.getLength(); i++){
            NodeList tile = hexTiles.item(i).getChildNodes();
            //points
            NodeList tilePoints = tile.item(0).getChildNodes();
            Point center = new Point(Integer.parseInt(tilePoints.item(0).getFirstChild().getTextContent())
                    , Integer.parseInt(tilePoints.item(0).getLastChild().getTextContent()));
            HexPoint a = model.getPointMap().get(new Point(Integer.parseInt(tilePoints.item(1).getFirstChild().getTextContent())
                    , Integer.parseInt(tilePoints.item(1).getLastChild().getTextContent())));
            HexPoint b = model.getPointMap().get(new Point(Integer.parseInt(tilePoints.item(2).getFirstChild().getTextContent())
                    , Integer.parseInt(tilePoints.item(2).getLastChild().getTextContent())));
            HexPoint c = model.getPointMap().get(new Point(Integer.parseInt(tilePoints.item(3).getFirstChild().getTextContent())
                    , Integer.parseInt(tilePoints.item(3).getLastChild().getTextContent())));
            HexPoint d = model.getPointMap().get(new Point(Integer.parseInt(tilePoints.item(4).getFirstChild().getTextContent())
                    , Integer.parseInt(tilePoints.item(4).getLastChild().getTextContent())));
            HexPoint e = model.getPointMap().get(new Point(Integer.parseInt(tilePoints.item(5).getFirstChild().getTextContent())
                    , Integer.parseInt(tilePoints.item(5).getLastChild().getTextContent())));
            HexPoint f = model.getPointMap().get(new Point(Integer.parseInt(tilePoints.item(6).getFirstChild().getTextContent())
                    , Integer.parseInt(tilePoints.item(6).getLastChild().getTextContent())));
            //sides
            NodeList sidePoints = tile.item(1).getChildNodes();
            HexSide mAB = model.getSideMap().get(new Point(Integer.parseInt(sidePoints.item(0).getFirstChild().getTextContent())
                    , Integer.parseInt(sidePoints.item(0).getLastChild().getTextContent())));
            HexSide mBC = model.getSideMap().get(new Point(Integer.parseInt(sidePoints.item(1).getFirstChild().getTextContent())
                    , Integer.parseInt(sidePoints.item(1).getLastChild().getTextContent())));
            HexSide mCD = model.getSideMap().get(new Point(Integer.parseInt(sidePoints.item(2).getFirstChild().getTextContent())
                    , Integer.parseInt(sidePoints.item(2).getLastChild().getTextContent())));
            HexSide mDE = model.getSideMap().get(new Point(Integer.parseInt(sidePoints.item(3).getFirstChild().getTextContent())
                    , Integer.parseInt(sidePoints.item(3).getLastChild().getTextContent())));
            HexSide mEF = model.getSideMap().get(new Point(Integer.parseInt(sidePoints.item(4).getFirstChild().getTextContent())
                    , Integer.parseInt(sidePoints.item(4).getLastChild().getTextContent())));
            HexSide mFA = model.getSideMap().get(new Point(Integer.parseInt(sidePoints.item(5).getFirstChild().getTextContent())
                    , Integer.parseInt(sidePoints.item(5).getLastChild().getTextContent())));
            //draw values
            int radius = Integer.parseInt(tile.item(2).getTextContent());
            int sideToSide = Integer.parseInt(tile.item(3).getTextContent());
            int canopyHeight = Integer.parseInt(tile.item(4).getTextContent());
            String resource = tile.item(5).getTextContent();
            int value = Integer.parseInt(tile.item(6).getTextContent());
            boolean robber = Boolean.parseBoolean(tile.item(7).getTextContent());
            HexTile update = model.getTileMap().get(center);
            update.setA(a);
            update.setB(b);
            update.setC(c);
            update.setD(d);
            update.setE(e);
            update.setF(f);
            update.setAB(mAB);
            update.setBC(mBC);
            update.setCD(mCD);
            update.setDE(mDE);
            update.setEF(mEF);
            update.setFA(mFA);
            update.setRobber(robber);
            update.setRadius(radius);
            update.setSideToSide(sideToSide);
            update.setCanopyHeight(canopyHeight);
            update.setResource(resource);
            update.setValue(value);
            update.addToPayout();

        }
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
                int owner = Integer.parseInt(hexData.item(7).getTextContent());
                HexPoint update = model.getPointMap().get(coords);
                update.setId(id);
                update.setBuildStatus(buildStatus);
                update.setNeigbors(n);
                update.setRoads(s);
                update.setOwner(owner);
        }

    }


    public static void readModel(BoardData model, String XML) {
        model.setToggle();
        Document doc = null;
        try {
            doc = stringToDom(XML);
            //initialize the maps
            readMaps(model, doc);
            //fix the tiles
            readTiles(model,doc);
            //fix the points
            readPoints(model,doc);
            //fix sides
            readSides(model,doc);
            //fix players (for trading

            model.setIdentityToken(doc.getElementsByTagName("identity").item(0).getTextContent());
            HexTile[] temp = model.getTileMap().values().toArray(new HexTile[19]);

            model.setHexDeck(temp);
            model.configureEdges();
            for(HexTile tile : model.getTileMap().values()){
                tile.configureNeighborsAndBorders();
            }
            //shuffle decks
            model.getMenus().getDevDeck().shuffleDeck(model.tokenSeed());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        model.releaseToggle();
        model.setDisplayMode(0);
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
            model.clearMaps();
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
        return new HexPoint(tempXY, tempCXY, nId, model.getParent(),model);
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
                Integer.parseInt(side.item(1).getLastChild().getTextContent())));
        Node mp = side.item(2);
        Point midPoint = new Point(Integer.parseInt(mp.getFirstChild().getTextContent()),
                Integer.parseInt(mp.getLastChild().getTextContent()));
        int id = Integer.parseInt(side.item(3).getTextContent());
        return new HexSide(startXY,endXY,midPoint,id,model.getParent(),model);

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


    /**
     * Reads a manifest and updates the board accordingly
     * @param model the model to update
     * @param XML the manifest string
     */
    public static void readManifest(BoardData model, String XML){
        model.setToggle();
        try {
            Document doc = stringToDom(XML);
            NodeList updatePoints = doc.getElementsByTagName("hexpoint");
            NodeList updateSides = doc.getElementsByTagName("hexside");
            NodeList playerTurn = doc.getElementsByTagName("playerTurn");
            NodeList discardCards = doc.getElementsByTagName("discardcards");
            if(model.getPlayer().getId() == Integer.parseInt(playerTurn.item(0).getFirstChild().getTextContent())){
                System.out.println(model.getPlayer().getId() + "my own turn");
                return;
            }
            if(updatePoints.getLength() > 0){
                for(int i = 0; i < updatePoints.getLength(); i++){
                    NodeList point = updatePoints.item(i).getChildNodes();
                    Point key = new Point (Integer.parseInt(point.item(0).getTextContent()),
                    Integer.parseInt(point.item(1).getTextContent()));
                    HexPoint temp = model.getPointMap().get(key);
                    temp.setBuildStatus(Integer.parseInt(point.item(5).getTextContent()));
                    temp.setOwner(Integer.parseInt(point.item(7).getTextContent()));
                }
            }
            if(updateSides.getLength() > 0){
                for(int i = 0; i < updateSides.getLength(); i++){
                    NodeList side = updateSides.item(i).getChildNodes();
                    Point key = new Point(Integer.parseInt(side.item(0).getFirstChild().getTextContent()),
                            Integer.parseInt(side.item(0).getLastChild().getTextContent()));
                    HexSide temp = findHexSide(model,key);
                    boolean indigo = Boolean.parseBoolean(side.item(6).getTextContent());
                    temp.setOwner(Integer.parseInt(side.item(7).getTextContent()));
                    temp.setBuilt(indigo);
                }
            }
            int discardNo = Integer.parseInt(discardCards.item(0).getTextContent());
            System.out.println("Discarding " + discardNo + " cards" );
            for(int i = 0; i < discardNo; i++){
                if(model.getMenus() != null) {
                    model.getMenus().getDevDeck().removeCard();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        model.releaseToggle();

    }

    /**
     * Decides how to parse an XML request
     * Could be a complete model update, or
     * just a manifest.
     * @param model the model to perform on
     * @param XML the XML containing instructions.
     */
    public static void parseRequest(BoardData model, String XML){
        try{
            model.setMessageToggle(true);
            Document doc = stringToDom(XML);
            if(doc.getElementsByTagName("model").getLength() > 0){
                System.out.println("request recieved model");
                readModel(model,XML);
            } else  if(doc.getElementsByTagName("manifest").getLength() > 0){
                System.out.println("request recieved manifest");
                readManifest(model,XML);
            }
            else if(doc.getElementsByTagName("playerinfo").getLength() > 0){
                System.out.println("request recieved player");
                readPlayer(model,XML);
            } else if(doc.getElementsByTagName("turn-begin").getLength() > 0){
                System.out.println("request recieved turn");
                readTurnBegin(model,XML);
            }
            else if(doc.getElementsByTagName("trade").getLength() > 0){
                System.out.println("request recieved trade");
                readTrade(model, XML);
            }
            else if(doc.getElementsByTagName("robber").getLength() > 0){
                System.out.println("request received steal");
                readSteal(model, XML);
            }
            else if(doc.getElementsByTagName("card").getLength() > 0){
                System.out.println("request received card");
                readCard(model, XML);
            }
            model.setMessageToggle(false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    public static void readAlert(Game currentGame, String XML){
        try {
            Document doc = stringToDom(XML);
            NodeList alerts = doc.getElementsByTagName("alert");
            String alert = alerts.item(0).getFirstChild().getTextContent();
            if(alert.equals("ready")){
                currentGame.readyPlayers++;
                System.out.println("Players ready = " + currentGame.readyPlayers);
            }
            if(alert.equals("discard")){
                NodeList discard = alerts.item(0).getChildNodes();
                int id = Integer.parseInt(discard.item(1).getTextContent());
                currentGame.discarded.add(id);
                System.out.println("Discard recieved from " + id );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }



    /**
     * Decides how to parse an XML request
     * Could be a complete model update,trade, or
     * just a manifest.
     * Returns an integer based on the type of XML
     * Return 1 = manifest
     * Return 2 = alert
     * Return 3 = trade
     * Return 4 = steal
     * @param currentGame the game that the player is currently in.
     * @param XML the XML containing instructions.
     */
    public static int serverParseRequest(Game currentGame, String XML){
        //ANY LAST MESSAGE TYPE OTHER THAN 2 WILL ADVANCE THE TURN
        try{
            Document doc = stringToDom(XML);
            if(doc.getElementsByTagName("manifest").getLength() > 0){
                readManifest(currentGame.mainBoard, XML);
                currentGame.lastMessageType = 0;
                currentGame.turnToggle = true;
                return 1;
            }
            if(doc.getElementsByTagName("alert").getLength() > 0){
                currentGame.lastMessageType = 1;
                readAlert(currentGame, XML);
                return 2;
            }
            if(doc.getElementsByTagName("trade").getLength() > 0){
                currentGame.lastMessageType = 2;
                return 3;
            }
            if(doc.getElementsByTagName("robber").getLength() > 0){
                currentGame.lastMessageType = 2;
                return 4;
            }
            if(doc.getElementsByTagName("card").getLength() > 0){
                currentGame.lastMessageType = 2;
                return 5;
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * Parses a player Object.
     * @param model
     * @param independent
     * @return
     */
    public static String parsePlayer(BoardData model, boolean independent){
        PlayerInfo player = model.getPlayer();
        StringBuffer result = new StringBuffer();
        if(independent) {
            result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        }
        result.append("<playerinfo>");
        result.append("<username>" + player.getUname() + "</username>");
        result.append("<score>" + player.getScore() + "</score>");
        result.append("<id>" + player.getId() + "</id>");
        result.append("<playerIDs>");
        for(int play : model.getPlayerList()){
            result.append("<player>" + play + "</player>");
        }
        result.append("</playerIDs>");
        result.append("</playerinfo>");
        saveOutput(result.toString(), "player.xml");
        return result.toString();

    }

    /**
     * Creates a new player XML string
     * @param id
     * @param independent
     * @return
     */
    public static String parseNewPlayer(int id,BoardData model, boolean independent){
        StringBuffer result = new StringBuffer();
        if(independent){
            result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        }
        result.append("<playerinfo>");
        result.append("<username>" + "tom" +  "</username>");
        result.append("<score>" + 0 + "</score>");
        result.append("<id>" + id + "</id>");
        result.append("<playerIDs>");
        for(int play : model.getPlayerList()){
            result.append("<player>" + play + "</player>");
        }
        result.append("</playerIDs>");
        result.append("</playerinfo>");
        saveOutput(result.toString(), "player.xml");
        return result.toString();

    }

    /**
     * Reads player info.
     * @param model
     * @param XML
     */
    public static void readPlayer(BoardData model, String XML){
        try {
            Document doc = stringToDom(XML);
            PlayerInfo old = model.getPlayer();
            NodeList info = doc.getElementsByTagName("playerinfo");
            NodeList infoChild = info.item(0).getChildNodes();
            old.setUname(infoChild.item(0).getTextContent());
            old.setScore(Integer.parseInt(infoChild.item(1).getTextContent()));
            old.setId(Integer.parseInt(infoChild.item(2).getTextContent()));
            model.getParent().frame.setTitle("Player " + model.getPlayer().getId());
            NodeList playerList = doc.getElementsByTagName("playerIDs");
            NodeList players = playerList.item(0).getChildNodes();
            for(int i = 0; i < players.getLength(); i ++){
                int id = Integer.parseInt(players.item(i).getTextContent());
                if(id != model.getPlayer().getId()) {
                    model.addNewPlayer(Integer.parseInt(players.item(i).getTextContent()));
                }
            }
            //initiate the trade floor
            model.getMenus().getTradeFloor().buildTradeFloor();




        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Saves the XML output string to XML file
     * for debuggin
     * @param XML the XML output string
     * @param filename the filename to save it to.
     */
    public static void saveOutput(String XML,String filename){
        try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
            writer.println(XML);
            writer.close();
        }catch(IOException e){
            System.out.println("Could not save");
        }

    }

    /**
     * One time generation of an alert message
     * @return the XML alert.
     */
    public static String generateAlert(BoardData model, String alert){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        if(alert.equals("discard")){
            result.append("<alert><type>" + alert + "</type>");
            result.append("<id>" + model.getPlayer().getId() + "</id>");
            result.append("</alert>");
        } else {
            result.append("<alert><type>" + alert + "</type></alert>");
        }
        return result.toString();
    }

    /**
     * Generates the beginning of a turn phase
     * @param d1 the first dice roll
     * @param d2 the second dice roll
     * @param currentGame the current game
     * @return the XML turn begin string
     */
    public static String generateTurnBegin(int d1, int d2, Game currentGame){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        result.append("<turn-begin>");
        result.append("<player-id>" + currentGame.advanceTurn() + "</player-id>");
        result.append("<d1>" + d1 + "</d1>");
        result.append("<d2>" + d2 + "</d2>");
        result.append("</turn-begin>");
        return result.toString();
    }

    /**
     * A method for reading the begin turn message and
     * applying it to the game model
     * @param model the model to apply it to
     * @param XML the XML string to apply
     */
    public static void readTurnBegin(BoardData model, String XML){
        try {
            Document dom = stringToDom(XML);
            NodeList data = dom.getElementsByTagName("turn-begin");
            NodeList turn =  data.item(0).getChildNodes();
            int playerId = Integer.parseInt(turn.item(0).getTextContent());
            int d1 = Integer.parseInt(turn.item(1).getTextContent());
            int d2 = Integer.parseInt(turn.item(2).getTextContent());
            model.setToggle();
            model.handleTurn(playerId,d1,d2);
            model.getMenus().getTradeFloor().newTurn(); //reset the trade floor for a new turn.
            model.releaseToggle();
            System.out.println("Player Turn: " + model.getPlayerTurn());
            System.out.println("My Player: " + model.getPlayer().getId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    public static String parseAccept(TradeFloor trade,boolean accept){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        result.append("<trade>");
        result.append("<from>" + trade.getClient().getId() + "</from>");
        result.append("<to>" + trade.getPlayerNeg().getId() + "</to>");
        result.append("<fromOffers>");
        if(trade.getPlayerNeg().getWants().containsKey(1)){
            result.append("<grain>" + trade.getPlayerNeg().getWants().get(1) + "</grain>");
        } else {
            result.append("<grain>0</grain>");
        }
        if(trade.getPlayerNeg().getWants().containsKey(2)){
            result.append("<ore>" + trade.getPlayerNeg().getWants().get(2) + "</ore>");
        } else {
            result.append("<ore>0</ore>");
        }
        if(trade.getPlayerNeg().getWants().containsKey(3)){
            result.append("<wool>" + trade.getPlayerNeg().getWants().get(3) + "</wool>");
        } else {
            result.append("<wool>0</wool>");
        }
        if(trade.getPlayerNeg().getWants().containsKey(4)){
            result.append("<brick>" + trade.getPlayerNeg().getWants().get(4) + "</brick>");
        } else {
            result.append("<brick>0</brick>");
        }
        if(trade.getPlayerNeg().getWants().containsKey(5)){
            result.append("<logs>" + trade.getPlayerNeg().getWants().get(5) + "</logs>");
        } else {
            result.append("<logs>0</logs>");
        }
        result.append("</fromOffers>");
        result.append("<fromWants>");
        if(trade.getPlayerNeg().getOffers().containsKey(1)){
            result.append("<grain>" +trade.getPlayerNeg().getOffers().get(1) + "</grain>");
        } else {
            result.append("<grain>0</grain>");
        }
        if(trade.getPlayerNeg().getOffers().containsKey(2)){
            result.append("<ore>" + trade.getPlayerNeg().getOffers().get(2) + "</ore>");
        } else {
            result.append("<ore>0</ore>");
        }
        if(trade.getPlayerNeg().getOffers().containsKey(3)){
            result.append("<wool>" + trade.getPlayerNeg().getOffers().get(3) + "</wool>");
        } else {
            result.append("<wool>0</wool>");
        }
        if(trade.getPlayerNeg().getOffers().containsKey(4)){
            result.append("<brick>" + trade.getPlayerNeg().getOffers().get(4) + "</brick>");
        } else {
            result.append("<brick>0</brick>");
        }
        if(trade.getPlayerNeg().getOffers().containsKey(5)){
            result.append("<logs>" + trade.getPlayerNeg().getOffers().get(5) + "</logs>");
        } else {
            result.append("<logs>0</logs>");
        }
        result.append("</fromWants>");
        result.append("<accept>" + Boolean.toString(accept) + "</accept>");
        result.append("<reject>false</reject>");
        result.append("</trade>");
        saveOutput(result.toString(), "acceptTrade.xml");
        return result.toString();
    }




    public static String parseTrade(PlayerTradeCard trade, String to,boolean accept,boolean reject){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        result.append("<trade>");
        result.append("<from>" + trade.getId() + "</from>");
        result.append("<to>" + to + "</to>");
        result.append("<fromOffers>");
        if(trade.getOffers().containsKey(1)){
            result.append("<grain>" + trade.getOffers().get(1) + "</grain>");
        } else {
            result.append("<grain>0</grain>");
        }
        if(trade.getOffers().containsKey(2)){
            result.append("<ore>" + trade.getOffers().get(2) + "</ore>");
        } else {
            result.append("<ore>0</ore>");
        }
        if(trade.getOffers().containsKey(3)){
            result.append("<wool>" + trade.getOffers().get(3) + "</wool>");
        } else {
            result.append("<wool>0</wool>");
        }
        if(trade.getOffers().containsKey(4)){
            result.append("<brick>" + trade.getOffers().get(4) + "</brick>");
        } else {
            result.append("<brick>0</brick>");
        }
        if(trade.getOffers().containsKey(5)){
            result.append("<logs>" + trade.getOffers().get(5) + "</logs>");
        } else {
            result.append("<logs>0</logs>");
        }
        result.append("</fromOffers>");
        result.append("<fromWants>");
        if(trade.getWants().containsKey(1)){
            result.append("<grain>" + trade.getWants().get(1) + "</grain>");
        } else {
            result.append("<grain>0</grain>");
        }
        if(trade.getWants().containsKey(2)){
            result.append("<ore>" + trade.getWants().get(2) + "</ore>");
        } else {
            result.append("<ore>0</ore>");
        }
        if(trade.getWants().containsKey(3)){
            result.append("<wool>" + trade.getWants().get(3) + "</wool>");
        } else {
            result.append("<wool>0</wool>");
        }
        if(trade.getWants().containsKey(4)){
            result.append("<brick>" + trade.getWants().get(4) + "</brick>");
        } else {
            result.append("<brick>0</brick>");
        }
        if(trade.getWants().containsKey(5)){
            result.append("<logs>" + trade.getWants().get(5) + "</logs>");
        } else {
            result.append("<logs>0</logs>");
        }
        result.append("</fromWants>");
        result.append("<accept>" + Boolean.toString(accept) + "</accept>");
        result.append("<reject>" + Boolean.toString(reject) + "</reject>");
        result.append("</trade>");
        saveOutput(result.toString(), "trade.xml");
        return result.toString();
    }


    /**
     * Reads a trade message sent from the server
     * @param model the model to alter
     * @param XML the XML trade message
     */
    public static void readTrade(BoardData model, String XML){
        System.out.println("Reading Trade");

        try {
            Document dom = stringToDom(XML);
            NodeList tradeMaster = dom.getElementsByTagName("trade");
            NodeList trade =tradeMaster.item(0).getChildNodes();
            //player who it was from.
            int fromID = Integer.parseInt(trade.item(0).getTextContent());
            //player who it was to.
            String to = trade.item(1).getTextContent();

            boolean accept = Boolean.parseBoolean(trade.item(4).getTextContent());
            boolean reject = Boolean.parseBoolean(trade.item(5).getTextContent());

            if(accept || to.equals("all")) {
                model.getMenus().getTradeFloor().resetTrades();
            }
            if(!model.getMenus().getTradeFloor().getClient().isOfferRejected()) {

                //if the recieved trade was initiated by myself and it's not the acceptance resolution, there is no need to continue
                if (fromID == model.getPlayer().getId() && !accept) {
                    System.out.println("Trade Was From Me");
                    return;
                }
                //if the received trade was not for me (not addressed to all or my id).
                if (!to.equals("all") && Integer.parseInt(to) != model.getPlayer().getId()) {
                    //if I'm also not the 'from' player (I'm not in the negotiations). no need to continue
                    if (fromID != model.getPlayer().getId()) {
                        if (!reject) {
                            System.out.println("Trade Not Addressed To Me");
                            model.getMenus().getTradeFloor().setRejectAlert(true);
                        } else {
                            model.getMenus().getTradeFloor().getPlayerForTrade(fromID).
                                    setOfferRejected(Boolean.parseBoolean(trade.item(5).
                                            getTextContent()));
                        }
                        return;
                    }
                }
                //if the trade was addressed to all, it's an open trade
                if (to.equals("all") && fromID != model.getPlayer().getId()) {
                    model.getMenus().getTradeFloor().setOpenTrade(true);
                }
                //once we're past the returns, we can parse the trades

                if (fromID != model.getPlayer().getId()) {
                    //get the from player
                    PlayerTradeCard tradeInitiator = model.getMenus().getTradeFloor().getPlayerForTrade(fromID);

                    //if the trade offer is a rejection
                    tradeInitiator.setOfferRejected(Boolean.parseBoolean(trade.item(5).getTextContent()));

                    //reset the from players offer and want
                    tradeInitiator.setOffers(new HashMap<Integer, Integer>());
                    tradeInitiator.setWants(new HashMap<Integer, Integer>());
                    //fix offers.
                    NodeList fromOffers = trade.item(2).getChildNodes();

                    //grain offer
                    if (Integer.parseInt(fromOffers.item(0).getTextContent()) > 0) {
                        tradeInitiator.getOffers().put(1, Integer.parseInt(fromOffers.item(0).getTextContent()));
                    }
                    //ore offer
                    if (Integer.parseInt(fromOffers.item(1).getTextContent()) > 0) {
                        tradeInitiator.getOffers().put(2, Integer.parseInt(fromOffers.item(1).getTextContent()));
                    }
                    //wool offer
                    if (Integer.parseInt(fromOffers.item(2).getTextContent()) > 0) {
                        tradeInitiator.getOffers().put(3, Integer.parseInt(fromOffers.item(2).getTextContent()));
                    }
                    //bricks offer
                    if (Integer.parseInt(fromOffers.item(3).getTextContent()) > 0) {
                        tradeInitiator.getOffers().put(4, Integer.parseInt(fromOffers.item(3).getTextContent()));
                    }
                    //logs offer
                    if (Integer.parseInt(fromOffers.item(4).getTextContent()) > 0) {
                        tradeInitiator.getOffers().put(5, Integer.parseInt(fromOffers.item(4).getTextContent()));
                    }

                    //fix wants.
                    NodeList fromWants = trade.item(3).getChildNodes();
                    //grain want
                    if (Integer.parseInt(fromWants.item(0).getTextContent()) > 0) {
                        tradeInitiator.getWants().put(1, Integer.parseInt(fromWants.item(0).getTextContent()));
                    }
                    //ore want
                    if (Integer.parseInt(fromWants.item(1).getTextContent()) > 0) {
                        tradeInitiator.getWants().put(2, Integer.parseInt(fromWants.item(1).getTextContent()));
                    }
                    //wool want
                    if (Integer.parseInt(fromWants.item(2).getTextContent()) > 0) {
                        tradeInitiator.getWants().put(3, Integer.parseInt(fromWants.item(2).getTextContent()));
                    }
                    //brick want
                    if (Integer.parseInt(fromWants.item(3).getTextContent()) > 0) {
                        tradeInitiator.getWants().put(4, Integer.parseInt(fromWants.item(3).getTextContent()));
                    }
                    //logs want
                    if (Integer.parseInt(fromWants.item(4).getTextContent()) > 0) {
                        tradeInitiator.getWants().put(5, Integer.parseInt(fromWants.item(4).getTextContent()));
                    }
                    if (accept) {
                        System.out.println("Resolving trade where I wasn't the last sender");
                        model.getMenus().getTradeFloor().setPlayerNeg(tradeInitiator);
                        model.getMenus().getTradeFloor().performTrade(tradeInitiator);
                    }
                    //fromID is me
                } else {
                    //get the from player
                    PlayerTradeCard tradeInitiator = model.getMenus().getTradeFloor().getPlayerForTrade(Integer.parseInt(to));
                    //if the trade is a rejection
                    tradeInitiator.setOfferRejected(Boolean.parseBoolean(trade.item(5).getTextContent()));
                    //reset the from players offer and want
                    tradeInitiator.setOffers(new HashMap<Integer, Integer>());
                    tradeInitiator.setWants(new HashMap<Integer, Integer>());
                    //fix offers.
                    NodeList fromOffers = trade.item(3).getChildNodes();

                    //grain offer
                    if (Integer.parseInt(fromOffers.item(0).getTextContent()) > 0) {
                        tradeInitiator.getOffers().put(1, Integer.parseInt(fromOffers.item(0).getTextContent()));
                    }
                    //ore offer
                    if (Integer.parseInt(fromOffers.item(1).getTextContent()) > 0) {
                        tradeInitiator.getOffers().put(2, Integer.parseInt(fromOffers.item(1).getTextContent()));
                    }
                    //wool offer
                    if (Integer.parseInt(fromOffers.item(2).getTextContent()) > 0) {
                        tradeInitiator.getOffers().put(3, Integer.parseInt(fromOffers.item(2).getTextContent()));
                    }
                    //bricks offer
                    if (Integer.parseInt(fromOffers.item(3).getTextContent()) > 0) {
                        tradeInitiator.getOffers().put(4, Integer.parseInt(fromOffers.item(3).getTextContent()));
                    }
                    //logs offer
                    if (Integer.parseInt(fromOffers.item(4).getTextContent()) > 0) {
                        tradeInitiator.getOffers().put(5, Integer.parseInt(fromOffers.item(4).getTextContent()));
                    }

                    //fix wants.
                    NodeList fromWants = trade.item(2).getChildNodes();
                    //grain want
                    if (Integer.parseInt(fromWants.item(0).getTextContent()) > 0) {
                        tradeInitiator.getWants().put(1, Integer.parseInt(fromWants.item(0).getTextContent()));
                    }
                    //ore want
                    if (Integer.parseInt(fromWants.item(1).getTextContent()) > 0) {
                        tradeInitiator.getWants().put(2, Integer.parseInt(fromWants.item(1).getTextContent()));
                    }
                    //wool want
                    if (Integer.parseInt(fromWants.item(2).getTextContent()) > 0) {
                        tradeInitiator.getWants().put(3, Integer.parseInt(fromWants.item(2).getTextContent()));
                    }
                    //brick want
                    if (Integer.parseInt(fromWants.item(3).getTextContent()) > 0) {
                        tradeInitiator.getWants().put(4, Integer.parseInt(fromWants.item(3).getTextContent()));
                    }
                    //logs want
                    if (Integer.parseInt(fromWants.item(4).getTextContent()) > 0) {
                        tradeInitiator.getWants().put(5, Integer.parseInt(fromWants.item(4).getTextContent()));
                    }
                    if (accept) {
                        System.out.println("Resolving trade where I was the last sender.");
                        model.getMenus().getTradeFloor().setPlayerNeg(tradeInitiator);
                        model.getMenus().getTradeFloor().performTrade(tradeInitiator);

                    }
                }
                if (!accept) {
                    PlayerTradeCard tradeInitiator = model.getMenus().getTradeFloor().getPlayerForTrade(fromID);
                    if (!tradeInitiator.isOfferRejected()) {
                        model.getMenus().getTradeFloor().setTradeAlertPlayer(tradeInitiator.getId());
                        model.getMenus().getTradeFloor().toggleTradeAlert(true);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }


    /**
     * Parses a robber move.
     * @param model the model to operate on
     * @param toID the of the player to steal from
     * @param robberLoc the hextile location of the robber
     * @param send the send status (true = send, false = recieve)
     * @return
     */
    public static String parseSteal(BoardData model, int toID, HexTile robberLoc, boolean send){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        result.append("<robber>");
        result.append("<hextile>");
        result.append("<x>" + robberLoc.getCenter().x + "</x>");
        result.append("<y>" + robberLoc.getCenter().y + "</y>");
        result.append("</hextile>");
        result.append("<from>" + model.getPlayer().getId() + "</from>");
        result.append("<to>" + toID + "</to>");
        int resource = 0;
        if(send){
            resource = 0;
        } else{
            //perform a random steal.
            resource = model.getPlayer().stealResource();
            model.getMenus().getRobDialogue().performSteal(resource);
        }
        result.append("<resource>" + resource + "</resource>" );
        result.append("<send>" + send + "</send>");
        result.append("</robber>");
        saveOutput(result.toString(),"steal.xml");
        return result.toString();
    }

    /**
     * Reads and computes the result
     * of a steal message.
     * @param model the model to operate on
     * @param XML the steal message.
     */
    public static void readSteal(BoardData model, String XML){
        try {
            Document dom = stringToDom(XML);
            NodeList stealItems = dom.getElementsByTagName("robber");
            NodeList steal = stealItems.item(0).getChildNodes();
            NodeList centerPt = steal.item(0).getChildNodes();
            Point center = new Point(Integer.parseInt(centerPt.item(0).getTextContent()),
                    Integer.parseInt(centerPt.item(1).getTextContent()));
            //remove previous robber
            for(HexTile tile : model.getHexDeck()){
                tile.setRobber(false);
            }
            model.getRobberTile().setRobber(false);
            //place new robber
            model.getTileMap().get(center).setRobber(true);
            model.setRobberTile(model.getTileMap().get(center));
            //get send status
            boolean send = Boolean.parseBoolean(steal.item(4).getTextContent());
            int fromID = Integer.parseInt(steal.item(1).getTextContent());
            int toID = Integer.parseInt(steal.item(2).getTextContent());
            int resource = Integer.parseInt(steal.item(3).getTextContent());
            //if addressed to me
            if(toID == model.getPlayer().getId()){
                //if this was a send offer not a recieve
                if(send){
                    //do steal
                    //if we're out of the discard screen
                    if(model.getDisplayMode() != 9 ) {
                        model.setGameStatusNotifier("Player " + fromID + " is stealing from you!");
                        model.setDisplayMode(0);
                        model.setStealManifest(ObjectParser.parseSteal(model, fromID, model.getRobberTile(), false));
                    } else{
                        //queue up the steal mechanic
                        model.getPlayer().setStealFromID(fromID);
                        model.getPlayer().setStealFlag(true);
                    }
                } else{
                    //give stolen resource
                    model.getPlayer().giveResource(resource);
                    model.getMenus().getRobDialogue().performSuccess(resource,fromID);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }


    /**
     * Parses a card message
     * @param model the model from which this is sent
     * @param card the card being sent
     * @return the XML card string.
     */
    public static String parseCard(BoardData model, DevelopmentCard card){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        result.append("<card>");
            result.append("<from>" + model.getPlayer().getId() + "</from>");
            result.append("<type>" + card.getType() + "</type>");
            result.append("</card>");
            saveOutput(result.toString(), "card.xml");
            return result.toString();
        }



    /**
     * Parses a monopoly card message
     * @param model the model from which this is sent
     * @param resource the requested monopoly resource
     * @param response whether this message is a response or not.
     * @return the XML card string.
     */
    public static String parseMonopolyCard(BoardData model,int resource, int to, boolean response){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        result.append("<card>");
            result.append("<from>" + model.getPlayer().getId() + "</from>");
            result.append("<type>Monopoly</type>");
            result.append("<to>" + to + "</to>");
            result.append("<resource>" + resource + "</resource>");
            result.append("<response>" + response + "</response>");
            if(response){
                result.append("<amount>" + model.getPlayer().getAllResource(resource) + "</amount>");
            }
            result.append("</card>");
            saveOutput(result.toString(), "monopoly.xml");
            return result.toString();
        }


    /**
     * Reads a monopoly message
     * @param model
     * @param XML
     */
    public static void readMonopoly(BoardData model, String XML){
        try {
            Document dom = stringToDom(XML);
            NodeList card = dom.getElementsByTagName("card");
            int from = Integer.parseInt(dom.getElementsByTagName("from").item(0).getTextContent());
            if(dom.getElementsByTagName("to").getLength() > 0) {
                int to = Integer.parseInt(dom.getElementsByTagName("to").item(0).getTextContent());
                int resource = Integer.parseInt(dom.getElementsByTagName("resource").item(0).getTextContent());
                boolean response = Boolean.parseBoolean(dom.getElementsByTagName("response").item(0).getTextContent());
                //if it's from me ignore it
                if (from == model.getPlayer().getId()) {
                    if(to != 0){
                        model.getPlayer().cleanseResource(resource);
                    }
                    return;
                }
                // a value of 0 represents a send to all
                if (to == 0) {
                    //if its from someone else and to all then I am being robbed.
                    String resourceName = "Grain";
                    if(resource == 2){
                        resourceName = "Ore";
                    }
                    if(resource == 3){
                        resourceName = "Wool";
                    }
                    if(resource == 4){
                        resourceName = "Brick";
                    }
                    if(resource == 5){
                        resourceName = "Logs";
                    }
                    model.setGameStatusNotifier("Player " + from + " steals  your " + resourceName );
                    model.setCardManifest(ObjectParser.parseMonopolyCard(model, resource, from, true));
                }
                // if it's to me it will be a response.
                if (to == model.getPlayer().getId()) {
                    PlayerInfo player = model.getPlayer();
                    int amount = Integer.parseInt(dom.getElementsByTagName("amount").item(0).getTextContent());
                    if(!model.getMenus().getDeckScreen().getMonopolyResults().containsKey(from)) {
                    if (resource == 1) {
                        player.addGrain(amount);
                    }
                    if (resource == 2) {
                        player.addOre(amount);
                    }
                    if (resource == 3) {
                        player.addWool(amount);
                    }
                    if (resource == 4) {
                        player.addBrick(amount);
                    }
                    if (resource == 5) {
                        player.addLogs(amount);
                    }
                        model.getMenus().getDeckScreen().getMonopolyResults().put(from, amount);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    /**
     * Reads a card message
     * @param model
     * @param XML
     */
    public static void readCard(BoardData model, String XML){
        try {
            Document dom = stringToDom(XML);
            NodeList card = dom.getElementsByTagName("card");
            NodeList cardItems = card.item(0).getChildNodes();
            int from = Integer.parseInt(cardItems.item(0).getTextContent());
            //if it's from me, do nothing
            if(from == model.getPlayer().getId()){
                NodeList toTemp =  dom.getElementsByTagName("to");
                if(toTemp.getLength() > 0 ) {
                    if (Integer.parseInt(toTemp.item(0).getTextContent()) != 0){
                        int resource = Integer.parseInt(dom.getElementsByTagName("resource").item(0).getTextContent());
                        model.getPlayer().cleanseResource(resource);
                    }
                }

                System.out.println("It's From Me... No Action");
                return;
            }
            String type = cardItems.item(1).getTextContent();
            if(type.equals("Knight")){
                model.getMenus().getDeckScreen().setPlayerCard("knight");
                model.setGameStatusNotifier("Player " + model.getPlayerTurn() + " played a Knight");

            }
            if(type.equals("Monopoly")){
                if(dom.getElementsByTagName("to").getLength() == 0 ) {
                    model.getMenus().getDeckScreen().setPlayerCard("monopoly");
                    model.setGameStatusNotifier("Player " + model.getPlayerTurn() + " played Monopoly");
                } else {
                    ObjectParser.readMonopoly(model, XML);
                }
            }
            if(type.equals("Year of Plenty")){
                model.getMenus().getDeckScreen().setPlayerCard("yearofplenty");
                model.setGameStatusNotifier("Player " + model.getPlayerTurn() + " played Year of Plenty");
            }
            if(type.equals("Road Building")){
                model.getMenus().getDeckScreen().setPlayerCard("freeroad");
                model.setGameStatusNotifier("Player " + model.getPlayerTurn() + " played Road Building");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }







}



