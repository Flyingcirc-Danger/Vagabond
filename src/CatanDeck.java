import java.util.Random;

/**
 *
 */
public class CatanDeck {

    private HexTile[] tiles;
    private String[] resources;
    private String[] buildOrder;
    private int[] tokenValues;

    public CatanDeck(boolean classicRules){
        if(classicRules){
            buildOrder = new String[] {"AB", "CD", "DE","EF","FA","AB","AB","BC","CD","CD","DE","DE","EF","EF","FA","FA","AB","AB"};

            tiles = new HexTile[19];
            /*
            4 Forest
            4 Grain
            4 Pastures
            3 Mines
            3 Brick
            1 Desert
             */
            resources = new String[] {
                    "forest", "forest","forest","forest",
                    "grain", "grain","grain","grain",
                    "pasture","pasture","pasture","pasture",
                    "mine","mine","mine",
                    "brick","brick","brick",
                    "desert"
                    };
            //shuffle the terrain and update the token values
            findDesert(shuffleTerrain());

        }

    }


    public String[] getBuildOrder() {
        return buildOrder;
    }

    public void setBuildOrder(String[] buildOrder) {
        this.buildOrder = buildOrder;
    }

    public int[] getTokenValues() {
        return tokenValues;
    }

    public void setTokenValues(int[] tokenValues) {
        this.tokenValues = tokenValues;
    }

    public HexTile[] getTiles() {
        return tiles;
    }

    public void setTiles(HexTile[] tiles) {
        this.tiles = tiles;
    }

    public String[] getResources() {
        return resources;
    }

    public void setResources(String[] resources) {
        this.resources = resources;
    }

    /**
     * A method for shuffling the deck of terrain
     * this helps generate the random map.
     * It returns the int index of the desert to
     * fix the tokens
     * adapted from:
     * http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
     */
    public int shuffleTerrain(){
        int desertIndex = 0;
        Random seed = new Random();
        for (int i = resources.length - 1; i > 0; i--)
        {
            int index = seed.nextInt(i + 1);
            String temp = resources[index];
            resources[index] = resources[i];
            resources[i] = temp;
            if(temp.equals("desert")){
                desertIndex = i;
            }
        }
        return desertIndex;

    }

    /**
     * A method for fixing the temp tokens array.
     * This method places the desert token (55) into
     * the list of tokens. This will not be rendered
     * by the front-end.
     * @param desertIndex the index where the desert is located.
     */
    public void findDesert(int desertIndex){
        int[] tempTokens = new int[] {11,3,6,5,4,9,10,8,4,11,12,9,10,8,3,6,2,5};
        int[] resultTokens = new int[19];
        int counter = 0;
        if(desertIndex == 0){
            resultTokens[0] = 55;
            for(int i=0; i <tempTokens.length;i++){
                resultTokens[i+1] = tempTokens[i];
            }
        } else {
            for (int i = 0; i < desertIndex; i++) {
                resultTokens[i] = tempTokens[i];
                counter = i;
            }
            resultTokens[counter + 1] = 55;
            for (int i = counter + 2; i < 19; i++) {
                resultTokens[i] = tempTokens[i - 1];
            }
        }
        this.tokenValues = resultTokens;


    }

}
