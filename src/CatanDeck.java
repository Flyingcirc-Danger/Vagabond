/**
 *
 */
public class CatanDeck {

    private HexTile[] tiles;
    private String[] buildOrder;
    private int[] tokenValues;

    public CatanDeck(boolean classicRules){
        if(classicRules){
            buildOrder = new String[] {"AB", "CD", "DE","EF","FA","AB","AB","BC","CD","CD","DE","DE","EF","EF","FA","FA","AB","AB"};
            tokenValues = new int[] {3,6,5,4,9,10,8,4,11,12,9,10,8,3,6,2,5,55};
            tiles = new HexTile[19];
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
}
