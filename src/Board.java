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
        background(0,188,212);
        smooth();
        deck = new CatanDeck(true);

    }

    public void draw() {
        smooth();
        HexTile center = new HexTile(SCREEN_WIDTH/2,SCREEN_HEIGHT/2,50,0,deck.getTokenValues()[0],deck.getResources()[0]);
        buildBoard(deck,center);




        if (mousePressed) {
            line(mouseX,mouseY,pmouseX,pmouseY);
        }
    }

    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "MyProcessingSketch" });
    }

    public void hex(HexTile drawTile){
        int[] cl = drawTile.tileColor();
        fill(cl[0],cl[1],cl[2]);
        stroke(0,0,0,7);
        beginShape();
        vertex(drawTile.getAx(), drawTile.getAy());
        vertex(drawTile.getBx(), drawTile.getBy());
        vertex(drawTile.getCx(), drawTile.getCy());
        vertex(drawTile.getDx(), drawTile.getDy());
        vertex(drawTile.getEx(), drawTile.getEy());
        vertex(drawTile.getFx(), drawTile.getFy());
        endShape(CLOSE);
        stroke(0,0,0);
        fill(255);
        if(drawTile.getValue() < 13) {
            ellipse(drawTile.getCenter()[0], drawTile.getCenter()[1], 30, 30);
            textSize(10);
            fill(0);
            text("" + drawTile.getValue(), drawTile.getCenter()[0], drawTile.getCenter()[1]);
        }
    }

    public void buildBoard(CatanDeck deck, HexTile center){
        deck.getTiles()[0] = center;
        hex(center);
        for(int i =0; i < 18; i++){
            deck.getTiles()[i+1] = deck.getTiles()[i].expand(deck.getBuildOrder()[i], deck.getTokenValues()[i+1],deck.getResources()[i+1]);
            hex(deck.getTiles()[i + 1]);
        }
    }
}

