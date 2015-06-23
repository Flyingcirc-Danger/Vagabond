import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Assert.*;

import java.util.Arrays;

/**
 * Created by Tom_Bryant on 6/23/15.
 */
public class HexTileTest extends TestCase {


    /**
     * Add a hexTile on the AB Border
     * @throws Exception
     */
    public void testAddAB() throws Exception {
        HexTile master = new HexTile(0,0,50);
        HexTile slave = master.addAB();
        int[] expected = new int[] {50,75};
        int [] result = master.addAB().getCenter();
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);

    }

    /**
     * Add a hexTile on the BC Border
     * @throws Exception
     */
    public void testAddBC() throws Exception {
        HexTile master = new HexTile(0,0,50);
        int[] expected = new int[] {100,0};
        int [] result = master.addBC().getCenter();
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);


    }

    /**
     * Add a hexTile on the CD Border
     * @throws Exception
     */
    public void testAddCD() throws Exception {
        HexTile master = new HexTile(0,0,50);
        int[] expected = new int[] {50,-75};
        int [] result = master.addCD().getCenter();
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);

    }

    /**
     * Add a hexTile on the DE Border
     * @throws Exception
     */
    public void testAddDE() throws Exception {
        HexTile master = new HexTile(0,0,50);
        int[] expected = new int[] {-50,-75};
        int [] result = master.addDE().getCenter();
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);

    }

    /**
     * Add a hexTile on the EF Border
     * @throws Exception
     */
    public void testAddEF() throws Exception {
        HexTile master = new HexTile(0,0,50);
        int[] expected = new int[] {-100,0};
        int [] result = master.addEF().getCenter();
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);

    }

    /**
     * Add a hexTile on the FA Border
     * @throws Exception
     */
    public void testAddFA() throws Exception {
        HexTile master = new HexTile(0,0,50);
        int[] expected = new int[] {-50,75};
        int [] result = master.addFA().getCenter();
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);

    }
}