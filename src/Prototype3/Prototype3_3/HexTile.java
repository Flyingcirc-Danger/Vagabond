package Prototype3.Prototype3_3;

import processing.core.PApplet;

import java.awt.*;

/**
 * Created by Tom_Bryant on 6/25/15.
 */
public class HexTile {

    private PApplet parent;

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

    private int[] hexColor;
    private boolean highlighted;

    public HexTile(PApplet parent, double centerX, double centerY, int radius, BoardData model){
        this.parent = parent;
        this.center = new Point();
        this.center.setLocation(centerX, centerY);
        this.radius = radius;
        this.model = model;
        this.hexColor = new int[] {255,255,255};
        this.highlighted = false;
        initPoints();
        initSides();

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
            HexPoint newPoint = new HexPoint(toAdd, center, model.assignPointID(),parent);
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
            HexSide newSide = new HexSide(start,end,midPoint,model.assignSideID(),parent);
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
        this.BC.getNeighbors().add(AB);
        this.BC.getNeighbors().add(CD);
        this.CD.getNeighbors().add(BC);
        this.CD.getNeighbors().add(DE);
        this.DE.getNeighbors().add(CD);
        this.DE.getNeighbors().add(EF);
        this.EF.getNeighbors().add(DE);
        this.EF.getNeighbors().add(FA);
        this.FA.getNeighbors().add(EF);
        this.FA.getNeighbors().add(AB);
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
        int tempX = (int)((start.getX() + end.getX())/2);
        int tempY = (int)((start.getY() + end.getY())/2);
        return new Point(tempX,tempY);
    }

    /**
     * Draws the hex on the parent canvas
     */
    public void display(){
        parent.smooth(8);
        if(this.isHighlighted()){
            parent.fill(244,67,54);
        }else {
            parent.fill(255);
        }
        parent.stroke(0,0,0,40);
        parent.beginShape();
        parent.vertex((float) A.getX(),(float) A.getY());
        parent.vertex((float) B.getX(), (float) B.getY());
        parent.vertex((float) C.getX(), (float) C.getY());
        parent.vertex((float) D.getX(), (float) D.getY());
        parent.vertex((float) E.getX(), (float) E.getY());
        parent.vertex((float) F.getX(), (float) F.getY());
        parent.endShape();
    }

    /**
     * Adds a tile along the AB side
     * @return the new HexTile
     */
    public HexTile addAB(){
        return new HexTile(parent,B.getX(), A.getY() - radius - (A.getY() - B.getY() ), radius,model);
    }

    /**
     * Adds a tile along the BC side
     * @return the new HexTile
     */
    public HexTile addBC(){
        return new HexTile(parent,center.getX() + (sideToSide * 2),(center.getY()),radius,model);
    }

    /**
     * Adds a tile along the CD side
     * @return the new HexTile
     */
    public HexTile addCD(){
        return new HexTile(parent,C.getX(), D.getY() + radius - (D.getY() - C.getY() ), radius,model);
    }

    /**
     * Adds a tile along the DE side
     * @return the new HexTile
     */
    public HexTile addDE(){
        //return new HexTile(parent,E.getX(),(center.getY() + (radius + (radius - canopyHeight))),radius);
        return new HexTile(parent,E.getX(), D.getY() + radius - (D.getY() - E.getY() ), radius,model);
    }

    /**
     * Adds a tile along the EF side
     * @return the new HexTile
     */
    public HexTile addEF(){
        return new HexTile(parent,center.getX() - (sideToSide * 2),(center.getY()),radius,model);
    }

    /**
     * Adds a tile along the FA side
     * @return the new HexTile
     */
    public HexTile addFA(){
       // return new HexTile(parent,F.getX(),(center.getY() - (radius + (radius - canopyHeight))),radius);
        return new HexTile(parent,F.getX(), A.getY() - radius - (A.getY() - F.getY() ), radius,model);
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
     * A method for checking the mouse position
     * relative to the points on this hex
     * @return
     */
    public boolean checkPoints(){
        if(A.overPoint()){
            A.mapNeigbors();
            return true;
        }
        if(B.overPoint()){
            B.mapNeigbors();
            return true;
        }
        if(C.overPoint()){
            C.mapNeigbors();
            return true;
        }
        if(D.overPoint()){
            D.mapNeigbors();
            return true;
        }
        if(E.overPoint()){
            E.mapNeigbors();
            return true;
        }
        if(F.overPoint()){
            F.mapNeigbors();
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
        if(AB.overSide()){
            this.setHighlighted(true);
            AB.mapNeighbors();
            return true;
        }
        if(BC.overSide()){
            this.setHighlighted(true);
            BC.mapNeighbors();
            return true;
        }
        if(CD.overSide()){
            this.setHighlighted(true);
            CD.mapNeighbors();
            return true;
        }
        if(DE.overSide()){
            this.setHighlighted(true);
            DE.mapNeighbors();
            return true;
        }
        if(EF.overSide()){
            this.setHighlighted(true);
            EF.mapNeighbors();
            return true;
        }
        if(FA.overSide()){
            this.setHighlighted(true);
            FA.mapNeighbors();
            return true;
        }
        this.setHighlighted(false);
            return false;
    }


    public HexTile expand(String instruction) throws IllegalArgumentException{
        if(instruction.equals("AB")){
            return this.addAB();
        }
        if(instruction.equals("BC")) {
            return this.addBC();
        }
        if(instruction.equals("CD")){
            return this.addCD();
        }
        if(instruction.equals("DE")){
            return this.addDE();
        }
        if(instruction.equals("EF")){
            return this.addEF();
        }
        if(instruction.equals("FA")){
            return this.addFA();
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




}
