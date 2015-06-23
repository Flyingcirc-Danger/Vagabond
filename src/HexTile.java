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

    public HexTile(int centerX, int centerY, int radius) {
        this.radius = radius;
        this.center = new int[]{centerX, centerY};
        this.A = new int[]{centerX, (centerY + radius)};
        this.B = new int[]{(centerX + radius), (centerY + (radius / 2))};
        this.C = new int[]{(centerX + radius), (centerY - (radius / 2))};
        this.D = new int[]{centerX, (centerY - radius)};
        this.E = new int[]{(centerX - radius), (centerY - (radius / 2))};
        this.F = new int[]{(centerX - radius), (centerY + (radius / 2))};


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

    //Hex Generators

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side A-B
     * @return Hex tile AB neighbor
     */
    public HexTile addAB(){
        return new HexTile(B[0], B[1] + radius, radius);
    }

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side B-C
     * @return Hex tile BC neighbor
     */
    public HexTile addBC(){
        return new HexTile(center[0] + (radius * 2),center[1], radius);
    }

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side C-D
     * @return Hex tile CD neighbor
     */
    public HexTile addCD(){
        return new HexTile(C[0], C[1] - radius, radius);
    }

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side D-E
     * @return Hex tile DE neighbor
     */
    public HexTile addDE(){
        return new HexTile(E[0], E[1] - radius, radius);
    }

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side E-F
     * @return Hex tile EF neighbor
     */
    public HexTile addEF(){
        return new HexTile(center[0] - (radius * 2),center[1], radius);
    }

    /**
     * Returns a Hex tile with co-ordinates
     * that place it in the position bordering
     * side F-A
     * @return Hex tile FA neighbor
     */
    public HexTile addFA(){
        return new HexTile(F[0],F[1] + radius, radius);
    }

    public String toString(){
        return new String("A coordinates: (" + getAx() + "," + getAy() +
                ")\nB coordinates: (" + getBx() + "," + getBy() +
                ")\nC coordinates: (" + getCx() + "," + getCy() +
                ")\nD coordinates: (" + getDx() + "," + getDy() +
                ")\nE coordinates: (" + getEx() + "," + getEy() +
                ")\nF coordinates: (" + getFx() + "," + getFy() +
                ")\nCenter coordinates: (" + getCenter()[0] + "," + getCenter()[1] + ")") ;
    }




}
