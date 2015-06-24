import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tom_Bryant on 6/24/15.
 */
public class CatanDeckTest extends TestCase{

    @Test
    /**
     * Desert is the first index
     */
    public void testFindDesert() throws Exception {
        CatanDeck testDeck = new CatanDeck(true);
        testDeck.findDesert(0);
        int[] expected = {55,11,3,6,5,4,9,10,8,4,11,12,9,10,8,3,6,2,5};
        int[] result = testDeck.getTokenValues();
        Assert.assertArrayEquals(expected,result);
    }

    @Test
    /**
     * Desert is the last index
     */
    public void testFindDesert2(){
    CatanDeck testDeck = new CatanDeck(true);
    testDeck.findDesert(18);
        int[] expected = {11,3,6,5,4,9,10,8,4,11,12,9,10,8,3,6,2,5,55};
        int[] result = testDeck.getTokenValues();
        Assert.assertArrayEquals(expected,result);
    }

    @Test
    /**
     * Desert is the middle index
     */
    public void testFindDesert3(){
        CatanDeck testDeck = new CatanDeck(true);
        testDeck.findDesert(9);
        int[] expected = {11,3,6,5,4,9,10,8,4,55,11,12,9,10,8,3,6,2,5};
        int[] result = testDeck.getTokenValues();
        Assert.assertArrayEquals(expected,result);
    }

    @Test
    /**
     * Desert is the 17th index
     */
    public void testFindDesert4(){
        CatanDeck testDeck = new CatanDeck(true);
        testDeck.findDesert(17);
        int[] expected = {11,3,6,5,4,9,10,8,4,11,12,9,10,8,3,6,2,55,5};
        int[] result = testDeck.getTokenValues();
        Assert.assertArrayEquals(expected,result);
    }
}