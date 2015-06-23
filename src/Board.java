import processing.core.*;

/**
 * Created by Tom_Bryant on 6/23/15.
 */
public class Board extends PApplet {

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public CatanDeck deck;

    public void setup() {
        this.SCREEN_HEIGHT = 600;
        this.SCREEN_WIDTH = 800;
        size(SCREEN_WIDTH,SCREEN_HEIGHT);
        background(255);
        deck = new CatanDeck(true);
    }

    public void draw() {
        HexTile center = new HexTile(SCREEN_WIDTH/2,SCREEN_HEIGHT/2,50,0,11);
        buildBoard(deck,center);




        if (mousePressed) {
            line(mouseX,mouseY,pmouseX,pmouseY);
        }
    }

    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "MyProcessingSketch" });
    }

    public void hex(HexTile drawTile){
        fill(255);
        beginShape();
        vertex(drawTile.getAx(), drawTile.getAy());
        vertex(drawTile.getBx(), drawTile.getBy());
        vertex(drawTile.getCx(), drawTile.getCy());
        vertex(drawTile.getDx(), drawTile.getDy());
        vertex(drawTile.getEx(), drawTile.getEy());
        vertex(drawTile.getFx(), drawTile.getFy());
        endShape(CLOSE);
        ellipse(drawTile.getCenter()[0], drawTile.getCenter()[1],30,30);
        textSize(10);
        fill(0);
        text(""+drawTile.getValue(),drawTile.getCenter()[0],drawTile.getCenter()[1]);
    }

    public void buildBoard(CatanDeck deck, HexTile center){
        deck.getTiles()[0] = center;
        hex(center);
        for(int i =0; i < 18; i++){
            deck.getTiles()[i+1] = deck.getTiles()[i].expand(deck.getBuildOrder()[i], deck.getTokenValues()[i]);
            hex(deck.getTiles()[i+1]);
        }
    }
}

