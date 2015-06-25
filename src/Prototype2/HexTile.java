package Prototype2;

/**
 * Created by Tom_Bryant on 6/23/15.
 */
public class HexTile {

    //corner coordinates
    private int[] A;
    private int[] B;
    private int[] C;
    private int[] D;
    private int[] E;
    private int[] F;
    private int[] center;

    //radius
    private int radius;

    //token values and build order
    private int value;
    private int order;

    //tile resource type
    private String resourceType;


    public HexTile(int centerX, int centerY, int radius, int order, int value, String resourceType) {
        this.order = order;
        this.value = value;
        this.radius = radius;
        this.center = new int[]{centerX, centerY};
        this.A = new int[]{centerX, (centerY + radius)};
        this.B = new int[]{(centerX + radius), (centerY + (radius / 2))};
        this.C = new int[]{(centerX + radius), (centerY - (radius / 2))};
        this.D = new int[]{centerX, (centerY - radius)};
        this.E = new int[]{(centerX - radius), (centerY - (radius / 2))};
        this.F = new int[]{(centerX - radius), (centerY + (radius / 2))};
        this.resourceType = resourceType;



    }



    //GETTERS AND SETTERS

    public int[] getA() {
        return A;
    }

    public void setA(int[] a) {
        A = a;
    }

    public int[] getB() {
        return B;
    }

    public void setB(int[] b) {
        B = b;
    }

    public int[] getC() {
        return C;
    }

    public void setC(int[] c) {
        C = c;
    }

    public int[] getD() {
        return D;
    }

    public void setD(int[] d) {
        D = d;
    }

    public int[] getE() {
        return E;
    }

    public void setE(int[] e) {
        E = e;
    }

    public int[] getF() {
        return F;
    }

    public void setF(int[] f) {
        F = f;
    }

    public int[] getCenter() {
        return center;
    }

    public void setCenter(int[] center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    //SPECIFIC COORDINATE GETTERS

    public int getAx(){
        return A[0];
    }

    public int getAy(){
        return A[1];
    }

    public int getBx(){
        return B[0];
    }

    public int getBy(){
        return B[1];
    }

    public int getCx(){
        return C[0];
    }

    public int getCy(){
        return C[1];
    }

    public int getDx(){
        return D[0];
    }

    public int getDy(){
        return D[1];
    }

    public int getEx(){
        return E[0];
    }

    public int getEy(){
        return E[1];
    }

    public int getFx(){
        return F[0];
    }

    public int getFy(){
        return F[1];
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    //Hex Generators

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side C-D
     * @return Hex tile CD neighbor
     */
    public HexTile addCD(int value, String newResourceType){
        int newOrder = order + 1;
        return new HexTile(B[0], B[1] + radius, radius, newOrder, value, newResourceType);
    }

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side B-C
     * @return Hex tile BC neighbor
     */
    public HexTile addBC(int value,String newResourceType){
        int newOrder = order + 1;
        return new HexTile(center[0] + (radius * 2),center[1], radius, newOrder,value, newResourceType);
    }

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side A-B
     * @return Hex tile AB neighbor
     */
    public HexTile addAB(int value, String newResourceType){
        int newOrder = order + 1;
        return new HexTile(C[0], C[1] - radius, radius, newOrder,value,newResourceType);
    }

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side F-A
     * @return Hex tile FA neighbor
     */
    public HexTile addFA(int value, String newResourceType){
        return new HexTile(E[0], E[1] - radius, radius, order + 1,value,newResourceType);
    }

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side E-F
     * @return Hex tile EF neighbor
     */
    public HexTile addEF(int value, String newResourceType){
        return new HexTile(center[0] - (radius * 2),center[1], radius,order + 1,value,newResourceType);
    }


    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side D-E
     * @return Hex tile DE neighbor
     */
    public HexTile addDE(int value,String newResourceType){
        return new HexTile(F[0],F[1] + radius, radius,order + 1,value,newResourceType);
    }

    public HexTile expand(String instruction, int value,String newResourceType) throws IllegalArgumentException{
        if(instruction.equals("AB")){
            return this.addAB(value,newResourceType);
        }
        if(instruction.equals("BC")) {
            return this.addBC(value,newResourceType);
        }
        if(instruction.equals("CD")){
            return this.addCD(value,newResourceType);
        }
        if(instruction.equals("DE")){
            return this.addDE(value,newResourceType);
        }
        if(instruction.equals("EF")){
            return this.addEF(value,newResourceType);
        }
        if(instruction.equals("FA")){
            return this.addFA(value,newResourceType);
        }
        else throw new IllegalArgumentException();
    }


    /**
     * Returns the color of this tile based on
     * it's assigned resource
     * @return the RGB Fill value
     */
    public int[] tileColor(){
        if(resourceType.equals("forest")){
           return new int[] {76,175,80};
        }
        if(resourceType.equals("pasture")){
            return new int[] {139,195,74};
        }
        if(resourceType.equals("mine")){
            return new int[] {158,158,158};
        }
        if(resourceType.equals("grain")){
            return new int[] {255,193,7};
        }
        if(resourceType.equals("brick")){
            return new int[] {255,87,34};
        //desert
        } else {
            return new int[] {255,235,59};
        }


    }

    @Override
    /**
     * A printout of the coordinates of this hex
     */
    public String toString(){
        return "A coordinates: (" + getAx() + "," + getAy() +
                ")\nB coordinates: (" + getBx() + "," + getBy() +
                ")\nC coordinates: (" + getCx() + "," + getCy() +
                ")\nD coordinates: (" + getDx() + "," + getDy() +
                ")\nE coordinates: (" + getEx() + "," + getEy() +
                ")\nF coordinates: (" + getFx() + "," + getFy() +
                ")\nCenter coordinates: (" + getCenter()[0] + "," + getCenter()[1] +
                ")\nValue: " + getValue() +
                "\nOrder: " + getOrder();
    }




}
