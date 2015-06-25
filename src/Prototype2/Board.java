package Prototype2;

import processing.core.*;

import java.util.ArrayList;

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
        smooth(8);
        deck = new CatanDeck(true);


    }

    public void draw() {
        smooth(8);
        HexTile center = new HexTile(SCREEN_WIDTH/2,SCREEN_HEIGHT/2,50,0,deck.getTokenValues()[0],deck.getResources()[0]);
        buildBoard(deck,center);
        deck.popEdgeTiles();
        //drawCoast();
        drawBoard();





        if (mousePressed) {
            line(mouseX,mouseY,pmouseX,pmouseY);
        }
    }

    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "Prototype2.Board" });
    }

    public void hex(HexTile drawTile){
        int[] cl = drawTile.tileColor();
        fill(cl[0],cl[1],cl[2],100);
        stroke(0,0,0,30);
        beginShape();
        vertex(drawTile.getAx(), drawTile.getAy());
        vertex(drawTile.getBx(), drawTile.getBy());
        vertex(drawTile.getCx(), drawTile.getCy());
        vertex(drawTile.getDx(), drawTile.getDy());
        vertex(drawTile.getEx(), drawTile.getEy());
        vertex(drawTile.getFx(), drawTile.getFy());
        endShape(CLOSE);
        if(drawTile.getValue() < 13) {
            stroke(0,0,0,0);
            fill(0,0,0,60f);
            ellipse(drawTile.getCenter()[0]+2, drawTile.getCenter()[1]+2, 30, 30);
            stroke(0,0,0,5);
            fill(255,243,224);
            ellipse(drawTile.getCenter()[0], drawTile.getCenter()[1], 30, 30);
            textSize(15);
            PFont f = createFont("assets/merit4.ttf",15);
            textFont(f);
            fill(121,85,72);
            String token = "" + drawTile.getValue();

            text(token, (float) (drawTile.getCenter()[0] - (textWidth(token) / 2)), (float) (drawTile.getCenter()[1] + 6));
        }
    }

    /**
     * A method for building a board
     * given a specific build order
     * from the center out
     * Also assigns the tile resource type and
     * token value
     * @param deck the catan deck for your game
     * @param center the center tile
     */
    public void buildBoard(CatanDeck deck, HexTile center){
        deck.getTiles()[0] = center;
        deck.bulkAddCoord(center);
       // hex(center);
        for(int i =0; i < 18; i++){
            deck.getTiles()[i+1] = deck.getTiles()[i].expand(deck.getBuildOrder()[i], deck.getTokenValues()[i+1],deck.getResources()[i+1]);
            deck.bulkAddCoord(deck.getTiles()[i+1]);
            stroke(0,0,0,0);
           // hex(deck.getTiles()[i + 1]);
        }
    }


    /**
     * A method for drawing the board.
     * Should not be called before buildBoard()
     * If a coast is desired, it should be called after
     * drawCoast()
     */
    public void drawBoard(){
        for(int i= 0; i < deck.getTiles().length; i++){
            hex(deck.getTiles()[i]);
        }

    }

    /**
     * A method for drawing the coast.
     */
    public void drawCoast(){
        stroke(0,0,0,50);
        fill(255,243,224);
        ArrayList<EdgeCoord> edges = deck.getEdgeCoords();
        beginShape();
        for(int i =0; i < edges.size(); i++){
            EdgeCoord edge = edges.get(i);
            vertex((int)edge.coord.getX(),(int)edge.coord.getY());
        }
        endShape(CLOSE);
    }


}

