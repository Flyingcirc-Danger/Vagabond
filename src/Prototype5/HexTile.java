package Prototype5;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Tom_Bryant on 6/25/15.
 * A class containing the a node in the datastructure behind the board.
 * This object holds all information necessary for the hex to function
 */
public class HexTile {

    private Board parent;

    //points
    private Point center;
    private HexPoint A;
    private HexPoint B;
    private HexPoint C;
    private HexPoint D;
    private HexPoint E;
    private HexPoint F;

    //sides
    private HexSide AB;
    private HexSide BC;
    private HexSide CD;
    private HexSide DE;
    private HexSide EF;
    private HexSide FA;

    //measurements
    private int radius;
    private int sideToSide;
    private int canopyHeight;

    //board data
    private BoardData model;

    //color stats
    private int[] hexColor;
    private boolean highlighted;

    //resource & roll info
    String resource;
    int value;
    private boolean robber;

    int id;
    //map of owners to yield ( 1 town = 1, 1 city = 2)
    private HashMap<Integer, Integer> owners;

    boolean placeholder;

    public HexTile(Board parent, double centerX, double centerY, int radius, BoardData model, String resource,int value){
        this.parent = parent;
        this.value = value;
        this.center = new Point();
        this.center.setLocation(centerX, centerY);
        this.radius = radius;
        this.model = model;
        this.hexColor = new int[] {255,255,255};
        this.highlighted = false;
        this.resource = resource;
        initPoints();
        initSides();
        this.model.getTileMap().put(this.center, this);
        this.owners = new HashMap<Integer, Integer>();
        this.robber = false;
        this.placeholder = false;
        addToPayout();

    }

    /**
     * Constructor for no display
     * @param centerX
     * @param centerY
     * @param radius
     * @param model
     * @param resource
     * @param value
     */
    public HexTile(double centerX, double centerY, int radius, BoardData model, String resource,int value){
        this.value = value;
        this.center = new Point();
        this.center.setLocation(centerX, centerY);
        this.radius = radius;
        this.model = model;
        this.hexColor = new int[] {255,255,255};
        this.highlighted = false;
        this.resource = resource;
        initPoints();
        initSides();
        this.model.getTileMap().put(this.center, this);
        this.owners = new HashMap<Integer, Integer>();
        addToPayout();
    }




    /**
     * For temporary hex objects
     */
    public HexTile(){
        this.placeholder = true;
    }

    /**
     * Initializes the points based on the given
     * center coords and radius
     */
    private void initPoints(){
        this.sideToSide = decideRound((Math.sqrt(3) * this.radius) / 2);
        this.canopyHeight = decideRound(radius - Math.sqrt((Math.pow(radius, 2) - Math.pow(sideToSide, 2))));
        Point tempA = new Point();
        tempA.setLocation(center.getX(), center.getY() - radius);
        this.A = addToPointMap(tempA);
        Point tempB = new Point();
        tempB.setLocation(center.getX() + sideToSide, center.getY() - canopyHeight);
        this.B = addToPointMap(tempB);
        Point tempC = new Point();
        tempC.setLocation(center.getX() + sideToSide, center.getY() + canopyHeight);
        this.C = addToPointMap(tempC);
        Point tempD = new Point();
        tempD.setLocation(center.getX(), center.getY() + radius);
        this.D = addToPointMap(tempD);
        Point tempE = new Point();
        tempE.setLocation(center.getX() - sideToSide, center.getY() + canopyHeight);
        this.E = addToPointMap(tempE);
        Point tempF = new Point();
        tempF.setLocation(center.getX() - sideToSide, center.getY() - canopyHeight);
        this.F = addToPointMap(tempF);
        configureNeighborsAndBorders();

    }

    /**
     * Populates the neighbors & borders of each point.
     * The neighbors & borders are a set, so no duplicates can be added
     */
    public void configureNeighborsAndBorders(){
        this.A.getNeigbors().add(F);
        this.A.getNeigbors().add(B);
        this.A.getBorders().add(this);
        this.B.getNeigbors().add(A);
        this.B.getNeigbors().add(C);
        this.B.getBorders().add(this);
        this.C.getNeigbors().add(B);
        this.C.getNeigbors().add(D);
        this.C.getBorders().add(this);
        this.D.getNeigbors().add(C);
        this.D.getNeigbors().add(E);
        this.D.getBorders().add(this);
        this.E.getNeigbors().add(D);
        this.E.getNeigbors().add(F);
        this.E.getBorders().add(this);
        this.F.getNeigbors().add(E);
        this.F.getNeigbors().add(A);
        this.F.getBorders().add(this);
    }

    /**
     * Creates a new HexPoint if one doesn't already
     * Exist
     * @param toAdd the co-ordinate to create a new HexPoint
     * @return the new or shared HexPoint;
     */
    private HexPoint addToPointMap(Point toAdd){
        if(model.getPointMap().containsKey(toAdd)){
            return model.getPointMap().get(toAdd);
        } else {
            HexPoint newPoint = new HexPoint(toAdd, center, model.assignPointID(),parent,model);
            model.getPointMap().put(toAdd,newPoint);
            return newPoint;
        }
    }

    /**
     * Initializes this hexagons sides.
     */
    private void initSides(){
        this.AB = addToSideMap(this.A, this.B);
        this.BC = addToSideMap(this.B, this.C);
        this.CD = addToSideMap(this.C, this.D);
        this.DE = addToSideMap(this.D, this.E);
        this.EF = addToSideMap(this.E, this.F);
        this.FA = addToSideMap(this.F, this.A);
        configureSideBorders();
    }

    /**
     * Creates a new HexSide if one doesn't already exist
     * @param start The start of this side
     * @param end The end of this side
     * @return The new or shared HexSide
     */
    private HexSide addToSideMap( HexPoint start, HexPoint end){
        Point midPoint = findMidPoint(start, end);
        if(model.getSideMap().containsKey(midPoint)){
            model.getSideMap().get(midPoint).getBorders().add(this);
            return model.getSideMap().get(midPoint);
        } else {
            HexSide newSide = new HexSide(start,end,midPoint,model.assignSideID(),parent,model);
           model.getSideMap().put(midPoint, newSide);
            return newSide;
        }
    }

    /**
     * Populates the neigbors and borders of each side.
     */
    private void configureSideBorders(){
        //neighbors are the sides which connect to this side
        this.AB.getNeighbors().add(FA);
        this.AB.getNeighbors().add(BC);
        this.AB.populateNeighbors();
        this.BC.getNeighbors().add(AB);
        this.BC.getNeighbors().add(CD);
        this.BC.populateNeighbors();
        this.CD.getNeighbors().add(BC);
        this.CD.getNeighbors().add(DE);
        this.CD.populateNeighbors();
        this.DE.getNeighbors().add(CD);
        this.DE.getNeighbors().add(EF);
        this.DE.populateNeighbors();
        this.EF.getNeighbors().add(DE);
        this.EF.getNeighbors().add(FA);
        this.EF.populateNeighbors();
        this.FA.getNeighbors().add(EF);
        this.FA.getNeighbors().add(AB);
        this.FA.populateNeighbors();
        //borders are the hex tiles which this side borders
        this.AB.getBorders().add(this);
        this.BC.getBorders().add(this);
        this.CD.getBorders().add(this);
        this.DE.getBorders().add(this);
        this.EF.getBorders().add(this);
        this.FA.getBorders().add(this);
    }

    /**
     * Finds the midpoint between two points
     */
    private Point findMidPoint(HexPoint start, HexPoint end){
        int tempX = ((start.getX() + end.getX())/2);
        int tempY = ((start.getY() + end.getY())/2);
        return new Point((int)tempX,(int)tempY);
    }

    /**
     * Draws the hex on the parent canvas
     */
    public void display(){
        int[] color = tileColor();
        parent.textAlign(parent.LEFT,parent.BOTTOM);
        parent.smooth(8);
        if (this.isHighlighted()) {
            parent.fill(21,101,192);
        } else {
            parent.fill(color[0],color[1],color[2]);
        }
        parent.beginShape();
        parent.vertex((float) A.getX(), (float) A.getY());
        parent.vertex((float) B.getX(), (float) B.getY());
        parent.vertex((float) C.getX(), (float) C.getY());
        parent.vertex((float) D.getX(), (float) D.getY());
        parent.vertex((float) E.getX(), (float) E.getY());
        parent.vertex((float) F.getX(), (float) F.getY());
        parent.endShape();
        A.drawTown();
        B.drawTown();
        C.drawTown();
        D.drawTown();
        E.drawTown();
        F.drawTown();
        AB.drawRoad();
        BC.drawRoad();
        CD.drawRoad();
        DE.drawRoad();
        EF.drawRoad();
        FA.drawRoad();
        if((parent.model.getTurnRoll() == value) && checkOwner() && !robber){
            if(parent.model.getTurnNumber() > parent.model.getPlayerList().size() + 1) {
                diceDisplay();
            } else{
                displayToken();
            }
        } else {
            displayToken();
        }
        if(isRobber()){
            robberDisplay();
        }
        //checkCoast();
    }


    /**
     * Draws the hex on the parent canvas
     */
    public void displayStructure(){
        int[] color = {255,193,7};
        parent.textAlign(parent.LEFT,parent.BOTTOM);
        parent.smooth(8);
        if (this.isHighlighted()) {
            parent.fill(21,101,192);
        } else {
            parent.fill(color[0],color[1],color[2]);
        }
        parent.beginShape();
        parent.vertex((float) A.getX(), (float) A.getY());
        parent.vertex((float) B.getX(), (float) B.getY());
        parent.vertex((float) C.getX(), (float) C.getY());
        parent.vertex((float) D.getX(), (float) D.getY());
        parent.vertex((float) E.getX(), (float) E.getY());
        parent.vertex((float) F.getX(), (float) F.getY());
        parent.endShape();
    }


    /**
     * Displays the large resource graphic IF the diceRoll on this turn
     * matches this tiles value.
     */
    public void diceDisplay(){
        if(this.resource.equals("desert")){
            return;
        }
        int ownerStake = getOwners().get(parent.model.getPlayer().getId());
        int index = 0;
        //grain

        PImage img = parent.resourceIMG[index];

        if(this.resource.equals("grain")){
            index = calculateIndexByStake(ownerStake,0);
            img = parent.resourceIMG[index];
        }

        //ore
        if(this.resource.equals("mine")){
            index = calculateIndexByStake(ownerStake, 1);
            img = parent.resourceIMG[index];
        }
        //wool
        if(this.resource.equals("pasture")){
            index = calculateIndexByStake(ownerStake, 2);
            img = parent.resourceIMG[index];
        }
        //brick
        if(this.resource.equals("brick")){
            index = calculateIndexByStake(ownerStake, 3);
            img = parent.resourceIMG[index];
        }
        //forest
        if(this.resource.equals("forest")){
            index = calculateIndexByStake(ownerStake, 4);
            img = parent.resourceIMG[index];
        }
        parent.image(img,center.x - (img.width/2), center.y - (img.height/2));
    }

    /**
     * Returns the correct resource image index based off the
     * amount of resources the player recieves
     * @param stake
     * @param index
     * @return
     */
    private int calculateIndexByStake(int stake, int index){
        if(stake == 2){
            return index + 5;
        }
        if(stake == 3){
            return index + 11;
        }
        if(stake == 4){
            return index + 16;
        }
        if(stake == 5){
            return index + 21;
        }
        if(stake == 6){
            return index + 26;
        }
        else{
            return index;
        }

    }


    public void robberDisplay(){
        PImage img = parent.resourceIMG[10];
        parent.image(img, center.x - (img.width/2), center.y - (img.height/2));
    }


    /**
     * Adds a tile along the AB side
     * @return the new HexTile
     */
    public HexTile addAB(String resource,int value){
        return new HexTile(parent,B.getX(), A.getY() - radius - (A.getY() - B.getY() ), radius,model,resource,value);
    }

    /**
     * Adds a tile along the BC side
     * @return the new HexTile
     */
    public HexTile addBC(String resource,int value){
        return new HexTile(parent,center.getX() + (sideToSide * 2),(center.getY()),radius,model,resource,value);
    }

    /**
     * Adds a tile along the CD side
     * @return the new HexTile
     */
    public HexTile addCD(String resource, int value){
        return new HexTile(parent,C.getX(), D.getY() + radius - (D.getY() - C.getY() ), radius,model,resource,value);
    }

    /**
     * Adds a tile along the DE side
     * @return the new HexTile
     */
    public HexTile addDE(String resource,int value){
        //return new HexTile(parent,E.getX(),(center.getY() + (radius + (radius - canopyHeight))),radius);
        return new HexTile(parent,E.getX(), D.getY() + radius - (D.getY() - E.getY() ), radius,model,resource,value);
    }

    /**
     * Adds a tile along the EF side
     * @return the new HexTile
     */
    public HexTile addEF(String resource, int value){
        return new HexTile(parent,center.getX() - (sideToSide * 2),(center.getY()),radius,model,resource,value);
    }

    /**
     * Adds a tile along the FA side
     * @return the new HexTile
     */
    public HexTile addFA(String resource,int value){
       // return new HexTile(parent,F.getX(),(center.getY() - (radius + (radius - canopyHeight))),radius);
        return new HexTile(parent,F.getX(), A.getY() - radius - (A.getY() - F.getY() ), radius,model,resource,value);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public HexPoint getA() {
        return A;
    }

    public void setA(HexPoint a) {
        A = a;
    }

    public HexPoint getB() {
        return B;
    }

    public void setB(HexPoint b) {
        B = b;
    }

    public HexPoint getC() {
        return C;
    }

    public void setC(HexPoint c) {
        C = c;
    }

    public HexPoint getD() {
        return D;
    }

    public void setD(HexPoint d) {
        D = d;
    }

    public HexPoint getE() {
        return E;
    }

    public void setE(HexPoint e) {
        E = e;
    }

    public HexPoint getF() {
        return F;
    }

    public void setF(HexPoint f) {
        F = f;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getSideToSide() {
        return sideToSide;
    }

    public void setSideToSide(int sideToSide) {
        this.sideToSide = sideToSide;
    }

    public int getCanopyHeight() {
        return canopyHeight;
    }

    public void setCanopyHeight(int canopyHeight) {
        this.canopyHeight = canopyHeight;
    }

    public BoardData getModel() {
        return model;
    }

    public PApplet getParent() {
        return parent;
    }

    public HexSide getAB() {
        return AB;
    }

    public void setAB(HexSide AB) {
        this.AB = AB;
    }

    public HexSide getBC() {
        return BC;
    }

    public void setBC(HexSide BC) {
        this.BC = BC;
    }

    public HexSide getCD() {
        return CD;
    }

    public void setCD(HexSide CD) {
        this.CD = CD;
    }

    public HexSide getDE() {
        return DE;
    }

    public void setDE(HexSide DE) {
        this.DE = DE;
    }

    public HexSide getEF() {
        return EF;
    }

    public void setEF(HexSide EF) {
        this.EF = EF;
    }

    public HexSide getFA() {
        return FA;
    }

    public void setFA(HexSide FA) {
        this.FA = FA;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }


    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HashMap<Integer, Integer> getOwners() {
        return owners;
    }

    public void setOwners(HashMap<Integer, Integer> owners) {
        this.owners = owners;
    }

    public boolean isRobber() {
        return robber;
    }

    public void setRobber(boolean robber) {
        if(robber){
            highlighted = false;
        }
        this.robber = robber;
    }

    /**
     * Decides how to round a double
     * when converting to int
     * @param toRound the double to round
     * @return the rounded number
     */
    private int decideRound(double toRound){
        double result = ((toRound % (int) toRound));
        int firstDigit = Integer.parseInt(Double.toString(result).substring(0, 1));
        if(firstDigit >= 5){

            return (int) toRound + 1;
        } else {
            return (int) toRound;
        }
    }

    /**
     * A toString method for only the coordinates
     * @return
     */
    public String coordsToString(){
        return "A: " + A.toString() +
                "\nB: " + B.toString() +
                "\nC: " + C.toString() +
                "\nD: " + D.toString() +
                "\nE: " + E.toString() +
                "\nF: " + F.toString();
    }

    /**
     * A method for displaying points on
     * the hex. Calls the display method of each point.
     */
    public void pointDebug(){
        this.A.display();
        this.B.display();
        this.C.display();
        this.D.display();
        this.E.display();
        this.F.display();
    }

    /**
     * A method for displaying sides
     * on the hex. Calls the display method of each side.
     */
    public void sideDebug(){
        this.AB.display();
        this.BC.display();
        this.CD.display();
        this.DE.display();
        this.EF.display();
        this.FA.display();
    }

    /**
     * A method for displaying the string resource
     * applied to this tile
     */
    public void resourceDebug(){
        parent.fill(0);
        int textX = this.center.x - (int)(parent.textWidth("" + this.resource)/2);
        StringBuffer debug = new StringBuffer("" + this.resource +"\nOwners: ");
        for(Integer id: owners.keySet()){
            debug.append(id + ":" + owners.get(id) + "\n");
        }
        parent.textSize(8);
        parent.text( debug.toString(), textX,(int)center.getY() - 25);
        parent.text("Robber: " + isRobber(), textX, (int) center.getY() - 40);
    }


    /**
     * A method for checking the mouse position
     * relative to the points on this hex
     * @return
     */
    public boolean checkPoints(){

            //mode is for debugging purposes only.
            int mode = this.model.getDisplayMode();
            int id =parent.model.getPlayer().getId();
            if (A.overPoint()) {
                if (mode == 1 || mode == 4) {
                    A.mapNeigbors();
                } else if (A.validBuild() && parent.currentTool == 1) {
                    highlighted = false;
                    A.setOwner(id);
                    A.drawTown();



                } else if(A.validUpgrade() && parent.currentTool == 3 && id == A.getOwner() ){
                    highlighted = false;
                    A.drawCity();
                    A.setOwner(id);

                } else {
                    highlighted = false;
                }
                return true;
            }
            if (B.overPoint()) {
                if (mode == 1 || mode == 4) {
                    B.mapNeigbors();
                } else if (B.validBuild() && parent.currentTool == 1) {
                    B.drawTown();

                    B.setOwner(id);
                }else if(B.validUpgrade() && parent.currentTool == 3 && id == B.getOwner()){
                    B.drawCity();
                    B.setOwner(id);
                }
                return true;
            }
            if (C.overPoint()) {
                if (mode == 1 || mode == 4 ) {
                    C.mapNeigbors();
                } else if (C.validBuild()  && parent.currentTool == 1) {
                    C.drawTown();

                    C.setOwner(id);
                }else if(C.validUpgrade() && parent.currentTool == 3 && id == C.getOwner()){
                    C.drawCity();
                    C.setOwner(id);
                }
                return true;
            }
            if (D.overPoint()) {
                if (mode == 1 || mode == 4) {
                    D.mapNeigbors();
                } else if (D.validBuild() && parent.currentTool == 1) {
                    D.drawTown();

                    D.setOwner(id);
                } else if(D.validUpgrade() && parent.currentTool == 3 && id == D.getOwner()){
                    D.drawCity();
                    D.setOwner(id);
                }
                return true;
            }
            if (E.overPoint()) {
                if (mode == 1 || mode == 4) {
                    E.mapNeigbors();
                } else if (E.validBuild() && parent.currentTool == 1) {
                    E.drawTown();

                    E.setOwner(id);
                }else if(E.validUpgrade() && parent.currentTool == 3 && id == E.getOwner()){
                    E.drawCity();
                    E.setOwner(id);
                }
                return true;
            }
            if (F.overPoint()) {
                if (mode == 1 || mode == 4) {
                    F.mapNeigbors();
                } else if (F.validBuild() && parent.currentTool == 1 ) {
                    F.drawTown();

                    F.setOwner(id);
                }else if(F.validUpgrade() && parent.currentTool == 3 && id == F.getOwner()){
                    F.drawCity();
                    F.setOwner(id);
                }
                return true;
            } else {
                return false;
            }
        }


    /**
     * A method for checking the mouse position
     * relative to the sides on this hex
     * @return
     */
    public boolean checkSides(){
        //mode is for debugging purposes only.
        int mode = this.model.getDisplayMode();
        int id =parent.model.getPlayer().getId();
        if(AB.overSide()){
            if (mode == 2 || mode == 4) {
                this.setHighlighted(true);
                AB.mapNeighbors();
            } else if (AB.validBuild() && parent.currentTool == 2) {
                AB.drawRoad();
                AB.setOwner(id);
        }
            return true;
        }
        if(BC.overSide()){
            if (mode == 2 || mode == 4) {
                this.setHighlighted(true);
                BC.mapNeighbors();
            }else if (BC.validBuild() && parent.currentTool == 2) {
                BC.drawRoad();
                BC.setOwner(id);
            }
            return true;
        }
        if(CD.overSide()){
            if (mode == 2 || mode == 4) {
                this.setHighlighted(true);
                CD.mapNeighbors();
            }else if (CD.validBuild() && parent.currentTool == 2) {
                CD.drawRoad();
                CD.setOwner(id);
            }
            return true;
        }
        if(DE.overSide()){
            if (mode == 2 || mode == 4) {
                this.setHighlighted(true);
                DE.mapNeighbors();
            }else if (DE.validBuild() && parent.currentTool == 2) {
                DE.drawRoad();
                DE.setOwner(id);
            }
            return true;
        }
        if(EF.overSide()){
            if (mode == 2 || mode == 4) {
                this.setHighlighted(true);
                EF.mapNeighbors();
            }else if (EF.validBuild() && parent.currentTool == 2) {
                EF.drawRoad();
                EF.setOwner(id);
            }
            return true;
        }
        if(FA.overSide()){
            if (mode == 2 || mode == 4) {
                this.setHighlighted(true);
                FA.mapNeighbors();
            }
            else if (FA.validBuild() && parent.currentTool == 2) {
                FA.drawRoad();
                FA.setOwner(id);
            }
            return true;
        }
        this.setHighlighted(false);
            return false;
    }

    /**
     * A method for building the map, takes a build order and
     * an adjacent tile, along with the normal HexTile construction
     * params
     * @param instruction the string representation of the side to expand to eg "AB"
     * @param resource the resource of the new tile
     * @param value the value of the new tile
     * @return a new hextile adjacent to this tile.
     * @throws IllegalArgumentException
     */
    public HexTile expand(String instruction,String resource,int value) throws IllegalArgumentException{
        if(instruction.equals("AB")){
            return this.addAB(resource,value);
        }
        if(instruction.equals("BC")) {
            return this.addBC(resource,value);
        }
        if(instruction.equals("CD")){
            return this.addCD(resource,value);
        }
        if(instruction.equals("DE")){
            return this.addDE(resource,value);
        }
        if(instruction.equals("EF")){
            return this.addEF(resource,value);
        }
        if(instruction.equals("FA")){
            return this.addFA(resource,value);
        }
        else throw new IllegalArgumentException();
    }

    /**
     * Checks to see if this tile has a neighbor on
     * side AB
     * @return
     */
    public boolean ABNeighbor(){
        if(AB.getBorders().size() > 1){
            return true;
        } else{

            return false;
        }
    }

    /**
     * Checks to see if this tile has a neighbor on
     * side BC
     * @return
     */
    public boolean BCNeighbor(){
        if(BC.getBorders().size() > 1){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Checks to see if this tile has a neighbor on
     * side CD
     * @return
     */
    public boolean CDNeighbor(){
        if(CD.getBorders().size() > 1){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Checks to see if this tile has a neighbor on
     * side DE
     * @return
     */
    public boolean DENeighbor(){
        if(DE.getBorders().size() > 1){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Checks to see if this tile has a neighbor on
     * side EF
     * @return
     */
    public boolean EFNeighbor(){
        if(EF.getBorders().size() > 1){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Checks to see if this tile has a neighbor on
     * side FA
     * @return
     */
    public boolean FANeighbor(){
        if(FA.getBorders().size() > 1){
            return true;
        } else{
            return false;
        }
    }


    /**
     * Checks the side given
     * as a string for neigbors.
     * @param toCheck the side in string form
     * @return true = has neighbors.
     */
    public boolean checkNeighbor(String toCheck){
        if(toCheck.equals("AB")){
            return ABNeighbor();
        }
        if(toCheck.equals("BC")){
            return BCNeighbor();
        }
        if(toCheck.equals("CD")){
            return CDNeighbor();
        }
        if(toCheck.equals("DE")){
            return DENeighbor();
        }
        if(toCheck.equals("EF")){
            return EFNeighbor();
        }
        if(toCheck.equals("FA")){
            return FANeighbor();
        }
        return true;
    }

    /**
     * A method to check to see if a tile is landlocked
     * If a tile has neighbors on all sides, it's landlocked.
     * @return
     */
    public boolean landLocked(){
        if(ABNeighbor() &&
                BCNeighbor() &&
                CDNeighbor() &&
                DENeighbor() &&
                EFNeighbor() &&
                FANeighbor()){
            return true;
        }
        else return false;
    }



    /**
     * Returns the color of this tile based on
     * it's assigned resource
     * @return the RGB Fill value
     */
    public int[] tileColor(){
        if(resource.equals("forest")){
            parent.stroke(0, 0, 0, 50);
            return new int[] {76,175,80};
        }
        if(resource.equals("pasture")){
            parent.stroke(0,0,0,50);
            return new int[] {139,195,74};
        }
        if(resource.equals("mine")){
            parent.stroke(0,0,0,50);
            return new int[] {158,158,158};
        }
        if(resource.equals("grain")){
            parent.stroke(0,0,0,50);
            return new int[] {255,193,7};
        }
        if(resource.equals("brick")){
            parent.stroke(0,0,0,50);
            return new int[] {255,87,34};
            //desert
        }if((resource.equals("desert")))  {
            parent.stroke(0,0,0,50);
            return new int[] {255,235,59};
        } else {
            //coast
            parent.stroke(0,0,0,0);
            return new int[]{255,243,224};
        }

    }


    /**
     * A method for displaying the token on the tile
     */
    public void displayToken(){
        if(value < 13) {
            parent.fill(255, 243, 224);
            parent.stroke(0,0,0,0);
            parent.ellipse(center.x, center.y, 30, 30);
            parent.fill(0);
            parent.textFont(parent.fonts[0]);
            parent.textAlign(parent.LEFT,parent.BOTTOM);
            parent.textSize(14);
            parent.text("" + value, center.x - (int) (parent.textWidth(Integer.toString(value)) / 2), center.y + 7);
        }
    }

    /**
     * Checks to see if a player can afford to build
     * a given structure
     * If true, it takes the required resources from the player.
     * @param building town/road/city (string form)
     * @return
     */
    private boolean checkAffordBuild(String building){
        if(building.equals("town")){
            HashMap<String,Integer> vp = parent.model.getVictoryBonus().getVictoryPointMap();
            if(model.getFreeTown() > 0){
                vp.put("Town",vp.get("Town") + 1);
                model.setFreeTown(model.getFreeTown() -1);
                return true;
            }
            PlayerInfo toCheck =parent.model.getPlayer();
            if(toCheck.getBrick() >= 1 &&
                    toCheck.getLogs() >= 1 &&
                    toCheck.getGrain() >= 1 &&
                    toCheck.getWool() >= 1){
                toCheck.subtractBrick(1);
                toCheck.subtractLogs(1);
                toCheck.subtractGrain(1);
                toCheck.subtractWool(1);
                vp.put("Town",vp.get("Town") + 1);
                return true;
            }

        } else if(building.equals("road")){
            if(model.getFreeRoad() > 0){
                model.setFreeRoad(model.getFreeRoad() - 1);
                return true;
            }
            if(model.freeRoad > 0){
                model.freeRoad--;
                return true;
            }
            PlayerInfo toCheck =parent.model.getPlayer();
            if(toCheck.getBrick() >= 1 &&
                    toCheck.getLogs() >= 1){
                toCheck.subtractBrick(1);
                toCheck.subtractLogs(1);
                return true;
            }

        } else if(building.equals("city")){
            HashMap<String,Integer> vp = parent.model.getVictoryBonus().getVictoryPointMap();
            PlayerInfo toCheck =parent.model.getPlayer();
            if(toCheck.getGrain() >= 2 &&
                    toCheck.getOre() >= 3){
                vp.put("City", vp.get("City") + 1);
                vp.put("Town", vp.get("Town") - 1);
                toCheck.subtractGrain(2);
                toCheck.subtractOre(3);
                return true;
            }
        }
        return false;
    }



    /**
     * Turns on the settle status of points.
     * Adds to the manifest
     */
    public void checkSettledPoints() {
            if(parent.currentTool == 1) {

                if (A.overPoint() && A.validBuild()) {
                    if (!A.isSettled()) {
                        if (checkAffordBuild("town")) {
                            A.setOwner(model.getPlayer().getId());
                            A.setSettled(true);
                            parent.model.settlementQuota++;
                            parent.model.addVP(1);
                            A.generateManifest();
                        }
                    }
                }
                if (B.overPoint() && B.validBuild()) {
                    if (!B.isSettled()) {
                        if (checkAffordBuild("town")) {
                            B.setOwner(model.getPlayer().getId());
                            B.setSettled(true);
                            parent.model.settlementQuota++;
                            parent.model.addVP(1);
                            B.generateManifest();
                        }
                    }
                    return;
                }
                if (C.overPoint() && C.validBuild()) {
                    if (!C.isSettled()) {
                        if (checkAffordBuild("town")) {
                            C.setOwner(model.getPlayer().getId());
                            C.setSettled(true);
                            parent.model.settlementQuota++;
                            parent.model.addVP(1);
                            C.generateManifest();
                        }
                    }
                    return;
                }
                if (D.overPoint() && D.validBuild()) {
                    if (!D.isSettled()) {
                        if (checkAffordBuild("town")) {
                            D.setOwner(model.getPlayer().getId());
                            D.setSettled(true);
                            parent.model.settlementQuota++;
                            parent.model.addVP(1);
                            D.generateManifest();
                        }
                    }
                    return;
                }
                if (E.overPoint() && E.validBuild()) {
                    if (!E.isSettled()) {
                        if (checkAffordBuild("town")) {
                            E.setOwner(model.getPlayer().getId());
                            E.setSettled(true);
                            parent.model.settlementQuota++;
                            parent.model.addVP(1);
                            E.generateManifest();
                        }
                    }
                    return;
                }
                if (F.overPoint() && F.validBuild()) {
                    if (!F.isSettled()) {
                        if (checkAffordBuild("town")) {
                            F.setOwner(model.getPlayer().getId());
                            F.setSettled(true);
                            parent.model.settlementQuota++;
                            parent.model.addVP(1);
                            F.generateManifest();
                        }
                    }
                }
            }
                if(parent.currentTool == 3){
                    if (A.overPoint() && A.validUpgrade()) {
                        if (!A.isCity()) {
                            if(checkAffordBuild("city")) {
                                A.setCity(true);
                                A.setOwner(model.getPlayer().getId());
                                parent.model.addVP(1);
                                A.generateManifest();
                            }
                        }
                        return;
                    }
                    if (B.overPoint() && B.validUpgrade()) {
                        if (!B.isCity()) {
                            if(checkAffordBuild("city")) {
                                B.setCity(true);
                                B.setOwner(model.getPlayer().getId());
                                parent.model.addVP(1);
                                B.generateManifest();
                            }
                        }
                        return;
                    }
                    if (C.overPoint() && C.validUpgrade()) {
                        if (!C.isCity()) {
                            if(checkAffordBuild("city")) {
                                C.setCity(true);
                                C.setOwner(model.getPlayer().getId());
                                parent.model.addVP(1);
                                C.generateManifest();
                            }
                        }
                        return;
                    }
                    if (D.overPoint() && D.validUpgrade()) {
                        if (!D.isCity()) {
                            if(checkAffordBuild("city")) {
                                D.setCity(true);
                                D.setOwner(model.getPlayer().getId());
                                parent.model.addVP(1);
                                D.generateManifest();
                            }
                        }
                        return;
                    }
                    if (E.overPoint() && E.validUpgrade()) {
                        if (!E.isCity()) {
                            if(checkAffordBuild("city")) {
                                E.setCity(true);
                                E.setOwner(model.getPlayer().getId());
                                parent.model.addVP(1);
                                E.generateManifest();
                            }
                        }
                        return;
                    }
                    if (F.overPoint() && F.validUpgrade()) {
                        if (!F.isCity()) {
                            if(checkAffordBuild("city")) {
                                F.setCity(true);
                                F.setOwner(model.getPlayer().getId());
                                parent.model.addVP(1);
                                F.generateManifest();
                            }
                        }
                        return;
                    }
                }

        return;

    }

    /**
     * A method for checking all sides on this tile to test
     * if you can build a road.
     */
    public void checkBuiltRoads(){
        if (AB.overSide() && parent.currentTool == 2) {
            if (!AB.isBuilt() && AB.validBuild()) {
                if (checkAffordBuild("road")) {
                    AB.setOwner(model.getPlayer().getId());
                    AB.setBuilt(true);
                    AB.resolveLongestRoad();
                    parent.model.roadQuota++;
                    AB.generateManifest();
                }
            }
            return;
        }
        if (BC.overSide()  && parent.currentTool == 2) {
            if (!BC.isBuilt() && BC.validBuild()) {
                if (checkAffordBuild("road")) {
                    BC.setOwner(model.getPlayer().getId());
                    BC.setBuilt(true);
                    BC.resolveLongestRoad();
                    parent.model.roadQuota++;
                    BC.generateManifest();

                }
            }
            return;
        }
        if (CD.overSide()  && parent.currentTool == 2) {
            if (!CD.isBuilt() && CD.validBuild()) {
                if (checkAffordBuild("road")) {
                    CD.setOwner(model.getPlayer().getId());
                    CD.setBuilt(true);
                    CD.resolveLongestRoad();
                    parent.model.roadQuota++;
                    CD.generateManifest();
                }
            }
            return;
        }
        if (DE.overSide()  && parent.currentTool == 2) {
            if (!DE.isBuilt() && DE.validBuild()) {
                if (checkAffordBuild("road")) {
                    DE.setOwner(model.getPlayer().getId());
                    DE.setBuilt(true);
                    DE.resolveLongestRoad();
                    parent.model.roadQuota++;
                    DE.generateManifest();

                }
            }
            return;
        }
        if (EF.overSide()  && parent.currentTool == 2) {
            if (!EF.isBuilt() && EF.validBuild()) {
                if (checkAffordBuild("road")) {
                    EF.setOwner(model.getPlayer().getId());
                    EF.setBuilt(true);
                    EF.resolveLongestRoad();
                    parent.model.roadQuota++;
                    EF.generateManifest();
                }
            }
            return;
        }
        if (FA.overSide()  && parent.currentTool == 2) {
            if (!FA.isBuilt() && FA.validBuild()) {
                if (checkAffordBuild("road")) {
                    FA.setOwner(model.getPlayer().getId());
                    FA.setBuilt(true);
                    FA.resolveLongestRoad();
                    parent.model.roadQuota++;
                    FA.generateManifest();
                }
            }
            return;
        }

    }


    /**
     * Checks to see if this boards player
     * is an owner of this tile
     * @return
     */
    public boolean checkOwner(){
        return owners.containsKey(model.getPlayer().getId());
    }


    /**
     * Adds a owner to the hashmap, or if they
     * already exist, increases their stake
     * @param id the id of the owner to add
     */
    public void addOwner(int id){
        if(owners.containsKey(id)){
            owners.put(id, owners.get(id) + 1);
        }
        else{
            owners.put(id, 1);
        }
    }

    /**
     * Adds the value hextile pair to the payout
     * hashMap in the main model
     */
    public void addToPayout(){
        if(model.getPayout().containsKey(value)){
           model.getPayout().get(value).add(this);
        } else {
            HashSet<HexTile> pay = new HashSet<HexTile>();
            pay.add(this);
           model.getPayout().put(value,pay);
        }

    }

    /**
     * Pays the required resource  based on the roll
     */
    public void payResource(){
        int stake = owners.get(parent.model.getPlayer().getId());
        if(this.resource.equals("grain")){
           parent.model.getPlayer().addGrain(stake);
        }
        if(this.resource.equals("mine")){
           parent.model.getPlayer().addOre(stake);
        }
        if(this.resource.equals("pasture")){
           parent.model.getPlayer().addWool(stake);
        }
        if(this.resource.equals("brick")){
           parent.model.getPlayer().addBrick(stake);
        }
        if(this.resource.equals("forest")){
           parent.model.getPlayer().addLogs(stake);
        }
    }

    /**
     * Checks each tile for the robber.
     */
    public void checkRobber(){
        if(Listeners.overCircle(center.x, center.y,radius,parent)){
            robber = true;
        } else{
            robber = false;
        }

    }

    /**
     * Highlights the tile if the robber is over it.
     */
    public void highlightRobber(){
        if(Listeners.overCircle(center.x, center.y,radius,parent)){
            highlighted = true;
        } else{
            highlighted = false;
        }

    }

    public void setParent(Board parent) {
        this.parent = parent;
        A.setParent(parent);
        B.setParent(parent);
        C.setParent(parent);
        D.setParent(parent);
        E.setParent(parent);
        F.setParent(parent);
        AB.setParent(parent);
        BC.setParent(parent);
        CD.setParent(parent);
        DE.setParent(parent);
        EF.setParent(parent);
        FA.setParent(parent);
    }



//    public String checkCoast(){
//        if(this.landLocked()){
//            return "none";
//        } else{
//            Point newCenter = new Point;
//            //check EF
//            if(!EFNeighbor()){
//                newCenter = new Point(center.x - (sideToSide * 2), center.y);
//                return "EF";
//
//            }
//        }
//        return "none";
//    }

//    public boolean buildHarbor(){
//        //can't build a harbor on a landlocked tile
//        if(landLocked()){
//            return false;
//        }
//        //checkEF
//        //first check to see if E or F is already a harbor
//        if(!EFNeighbor()) {
//            if (E.isHarbor() || F.isHarbor()) {
//                return false;
//            }
//        }
//        //check FA
//        if(!FANeighbor()){
//            if(F.isHarbor() || A.isHarbor()){
//                return false;
//            }
//        }
//
//    }
}

