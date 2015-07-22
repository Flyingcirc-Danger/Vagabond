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
    public PImage [] resourceIMG;

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
        this.images = new PImage[19];
        this.resourceIMG = new PImage[10];
        this.images[0] = loadImage("assets/logoSM.png");
        this.images[1] = loadImage("assets/grainSM.png");
        this.images[2] = loadImage("assets/oreSM.png");
        this.images[3] = loadImage("assets/woolSM.png");
        this.images[4] = loadImage("assets/brickSM.png");
        this.images[5] = loadImage("assets/logsSM.png");
        this.images[6] = loadImage("assets/bankSM.png");

        this.images[7] = loadImage("assets/grainBWSM.png");
        this.images[8] = loadImage("assets/oreBWSM.png");
        this.images[9] = loadImage("assets/woolBWSM.png");
        this.images[10] = loadImage("assets/brickBWSM.png");
        this.images[11] = loadImage("assets/logsBWSM.png");

        this.images[12] = loadImage("assets/grainMD.png");
        this.images[13] = loadImage("assets/oreMD.png");
        this.images[14] = loadImage("assets/woolMD.png");
        this.images[15] = loadImage("assets/brickMD.png");
        this.images[16] = loadImage("assets/logsMD.png");
        this.images[17] = loadImage("assets/plusSM.png");
        this.images[18] = loadImage("assets/minusSM.png");



        this.resourceIMG[0] = loadImage("assets/grainLG1.png");
        this.resourceIMG[1] = loadImage("assets/oreLG1.png");
        this.resourceIMG[2] = loadImage("assets/woolLG1.png");
        this.resourceIMG[3] = loadImage("assets/brickLG1.png");
        this.resourceIMG[4] = loadImage("assets/logsLG1.png");
        this.resourceIMG[5] = loadImage("assets/grainLG2.png");
        this.resourceIMG[6] = loadImage("assets/oreLG2.png");
        this.resourceIMG[7] = loadImage("assets/woolLG2.png");
        this.resourceIMG[8] = loadImage("assets/brickLG2.png");
        this.resourceIMG[9] = loadImage("assets/logsLG2.png");




        this.center=new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, model,model.getResourceTiles()[0],model.getTokens()[0]);
        this.debugger = new Debug(this,center);
        debugger.displayClosed();
        this.model.setDisplayMode(10);
        fonts = new PFont[]{createFont("assets/Verdana.ttf", 20),createFont("assets/merit4.ttf", 20)};
        textFont(fonts[0]);




    }





    public void draw() {


        if (model.getDisplayMode() <= 6) {
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
        if(model.getDisplayMode() == 7) {
            model.getMenus().getWaitScreen().checkButton();
        }


            fill(0);



    }


        @Override
        public void mousePressed () {
            model.checkMenus();
            //regular board with debugging
            if (model.getDisplayMode() <= 5) {
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
            background(0, 188, 212);
            model.setDisplayMode(7);
        } catch (IOException e) {
            this.model.getMenus().setConnect(new ConnectMenu(this,300,200,true));
        }
    }

    public static void main(String args[]) {
        PApplet.main(new String[] {"Prototype5.Board" });
    }
    //"--present",
}
