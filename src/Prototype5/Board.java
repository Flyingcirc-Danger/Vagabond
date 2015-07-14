package Prototype5;


import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import ServerPrototype1.*;

import java.io.IOException;

/**
 * The main board class. Builds the datastructure
 * displays & read/writesGIG the board
 * Created by Tom_Bryant on 6/25/15.
 */
public class Board extends PApplet {

    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public HexTile center;
    public Debug debugger;
    public BoardData model;


    public int currentTool;


    public PImage [] images;

    public Client client;

    public PFont[] fonts;






    public void setup() {

        SCREEN_HEIGHT = 768 ;
        SCREEN_WIDTH = 1024;

        size(SCREEN_WIDTH,SCREEN_HEIGHT);
        model = new BoardData(this);

        textSize(14);
        background(0, 188, 212);
        this.currentTool = 0;
        this.images = new PImage[6];
        this.images[0] = loadImage("assets/logoSM.png");
        this.images[1] = loadImage("assets/grainSM.png");
        this.images[2] = loadImage("assets/oreSM.png");
        this.images[3] = loadImage("assets/woolSM.png");
        this.images[4] = loadImage("assets/brickSM.png");
        this.images[5] = loadImage("assets/logsSM.png");




        this.center=new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, model,model.getResourceTiles()[0],model.getTokens()[0]);
        this.debugger = new Debug(this,center);
        debugger.displayClosed();
        this.model.setDisplayMode(10);
        fonts = new PFont[]{createFont("assets/Verdana.ttf",20),createFont("assets/merit4.ttf", 20)};
        textFont(fonts[0]);




    }





    public void draw() {


        if (model.getDisplayMode() <= 5) {
            fill(255, 0, 0, 0);
            fill(0);
            model.displayBoard();
            if (debugger.open) {
                debugger.displayOpen();
            }
            if (!debugger.open) {
                background(0, 188, 212);

                debugger.displayClosed();
                center.getModel().displayBoard();
            }
        }
            model.displayMenus();

            fill(0);



    }


        @Override
        public void mousePressed () {
            model.checkMenus();
            //regular board with debugging
            if (model.getDisplayMode() <= 5) {
                model.checkMenus();
                debugger.mouseDebug();
                model.checkSelected(this.currentTool);
            }
            //dice roll
            if (model.getDisplayMode() == 6) {

            }
        }


    public void keyPressed(){
        if(model.getDisplayMode() ==10){
            if (model.getMenus().getConnect().isTyping()){
                model.getMenus().getConnect().addText(key);
            }
        }
    }


    /**
     * Initiates the connection of this board and sets up
     * the game mode and debugging. If the connection cannot be found
     * an error message appears.
     * @param ip
     */
    public void initiateConnection(String ip){
        try {
            this.client = new Client(4001,model,ip);
            model.setToggle();
            model.setDisplayMode(0);
        } catch (IOException e) {
            this.model.getMenus().setConnect(new ConnectMenu(this,300,200,true));
        }
    }

    public static void main(String args[]) {
        PApplet.main(new String[] { "--present","Prototype5.Board" });
    }
    //"--present",
}
