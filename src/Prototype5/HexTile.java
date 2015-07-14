package Prototype5;


import processing.core.PApplet;

import java.awt.*;

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

    int id;



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
        configureNeighbors();

    }

    /**
     * Populates the neighbors of each point.
     * The neighbors are a set, so no duplicates can be added
     */
    private void configureNeighbors(){
        this.A.getNeigbors().add(F);
        this.A.getNeigbors().add(B);
        this.B.getNeigbors().add(A);
        this.B.getNeigbors().add(C);
        this.C.getNeigbors().add(B);
        this.C.getNeigbors().add(D);
        this.D.getNeigbors().add(C);
        this.D.getNeigbors().add(E);
        this.E.getNeigbors().add(D);
        this.E.getNeigbors().add(F);
        this.F.getNeigbors().add(E);
        this.F.getNeigbors().add(A);
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
        displayToken();
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
        parent.text("" + this.resource, textX,(int)center.getY() - 25);
    }


    /**
     * A method for checking the mouse position
     * relative to the points on this hex
     * @return
     */
    public boolean checkPoints(){

            //mode is for debugging purposes only.
            int mode = this.model.getDisplayMode();
            int id = model.getPlayer().getId();
            if (A.overPoint()) {
                if (mode == 1 || mode == 4) {
                    A.mapNeigbors();
                } else if (A.validBuild() && parent.currentTool == 1) {
                    A.setOwner(id);
                    A.drawTown();

                } else if(A.validUpgrade() && parent.currentTool == 3 && id == A.getOwner() ){
                    A.drawCity();
                    A.setOwner(id);
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
        int id = model.getPlayer().getId();
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
            parent.textSize(14);
            parent.text("" + value, center.x - (int) (parent.textWidth(Integer.toString(value)) / 2), center.y + 7);
        }
    }


    /**
     * Turns on the settle status of points.
     * Adds to the manifest
     */
    public void checkSettledPoints() {
            if(parent.currentTool == 1) {

                if (A.overPoint() && A.validBuild()) {
                    if (!A.isSettled()) {
                        A.setOwner(model.getPlayer().getId());
                        A.setSettled(true);
                        parent.model.settlementQuota++;
                        A.generateManifest();
                    }
                }
                if (B.overPoint() && B.validBuild()) {
                    if (!B.isSettled()) {
                        B.setOwner(model.getPlayer().getId());
                        B.setSettled(true);
                        parent.model.settlementQuota++;
                        B.generateManifest();
                    }
                    return;
                }
                if (C.overPoint() && C.validBuild()) {
                    if (!C.isSettled()) {
                        C.setOwner(model.getPlayer().getId());
                        C.setSettled(true);
                        parent.model.settlementQuota++;
                        C.generateManifest();
                    }
                    return;
                }
                if (D.overPoint() && D.validBuild()) {
                    if (!D.isSettled()) {
                        D.setOwner(model.getPlayer().getId());
                        D.setSettled(true);
                        parent.model.settlementQuota++;
                        D.generateManifest();
                    }
                    return;
                }
                if (E.overPoint() && E.validBuild()) {
                    if (!E.isSettled()) {
                        E.setOwner(model.getPlayer().getId());
                        E.setSettled(true);
                        parent.model.settlementQuota++;
                        E.generateManifest();
                    }
                    return;
                }
                if (F.overPoint() && F.validBuild()) {
                    if (!F.isSettled()) {
                        F.setOwner(model.getPlayer().getId());
                        F.setSettled(true);
                        parent.model.settlementQuota++;
                        F.generateManifest();
                    }
                }
            }
                if(parent.currentTool == 3){
                    if (A.overPoint() && A.validUpgrade()) {
                        if (!A.isCity()) {
                            A.setCity(true);
                            A.generateManifest();
                        }
                        return;
                    }
                    if (B.overPoint() && B.validUpgrade()) {
                        if (!B.isCity()) {
                            B.setCity(true);
                            B.generateManifest();
                        }
                        return;
                    }
                    if (C.overPoint() && C.validUpgrade()) {
                        if (!C.isCity()) {
                            C.setCity(true);
                            C.generateManifest();
                        }
                        return;
                    }
                    if (D.overPoint() && D.validUpgrade()) {
                        if (!D.isCity()) {
                            D.setCity(true);
                            D.generateManifest();
                        }
                        return;
                    }
                    if (E.overPoint() && E.validUpgrade()) {
                        if (!E.isCity()) {
                            E.setCity(true);
                            E.generateManifest();
                        }
                        return;
                    }
                    if (F.overPoint() && F.validUpgrade()) {
                        if (!F.isCity()) {
                            F.setCity(true);
                            F.generateManifest();
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
                AB.setOwner(model.getPlayer().getId());
                AB.setBuilt(true);
                AB.generateManifest();
            }
            return;
        }
        if (BC.overSide()  && parent.currentTool == 2) {
            if (!BC.isBuilt() && BC.validBuild()) {
                BC.setOwner(model.getPlayer().getId());
                BC.setBuilt(true);
                BC.generateManifest();
            }
            return;
        }
        if (CD.overSide()  && parent.currentTool == 2) {
            if (!CD.isBuilt() && CD.validBuild()) {
                CD.setOwner(model.getPlayer().getId());
                CD.setBuilt(true);
                CD.generateManifest();
            }
            return;
        }
        if (DE.overSide()  && parent.currentTool == 2) {
            if (!DE.isBuilt() && DE.validBuild()) {
                DE.setOwner(model.getPlayer().getId());
                DE.setBuilt(true);
                DE.generateManifest();
            }
            return;
        }
        if (EF.overSide()  && parent.currentTool == 2) {
            if (!EF.isBuilt() && EF.validBuild()) {
                EF.setOwner(model.getPlayer().getId());
                EF.setBuilt(true);
                EF.generateManifest();
            }
            return;
        }
        if (FA.overSide()  && parent.currentTool == 2) {
            if (!FA.isBuilt() && FA.validBuild()) {
                FA.setOwner(model.getPlayer().getId());
                FA.setBuilt(true);
                FA.generateManifest();
            }
            return;
        }

    }












}
