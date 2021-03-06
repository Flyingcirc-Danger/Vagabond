package Prototype5;


import ServerPrototype1.Client;
import ServerPrototype1.Server;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

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
    public Server game;


    public int currentTool;


    public PImage [] images;
    public PImage [] resourceIMG;

    public Client client;

    public PFont[] fonts;

    public Notification test;






    public void setup() {

        SCREEN_HEIGHT = 768 ;
        SCREEN_WIDTH = 1024;
        fonts = new PFont[]{createFont("assets/Verdana.ttf", 20),createFont("assets/merit4.ttf", 20)};
        textFont(fonts[0]);
        size(SCREEN_WIDTH,SCREEN_HEIGHT);
        model = new BoardData(this);

        textSize(14);
        background(0, 188, 212);
        this.currentTool = 0;
        this.images = new PImage[28];
        this.resourceIMG = new PImage[31];
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
        this.images[19] = loadImage("assets/tradeSM.png");
        this.images[20] = loadImage("assets/noTradeSM.png");
        this.images[21] = loadImage("assets/tradeRejectSM.png");
        this.images[22] = loadImage("assets/developmentCards/cardBack.png");
        this.images[23] = loadImage("assets/victorypointSM.png");
        this.images[24] = loadImage("assets/armySM.png");
        this.images[25] = loadImage("assets/bankSMBW.png");
        this.images[26] = loadImage("assets/tradeSMBW.png");
        this.images[27] = loadImage("assets/crown.png");

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

        this.resourceIMG[10] = loadImage("assets/robber.png");

        this.resourceIMG[11] = loadImage("assets/grainLG3.png");
        this.resourceIMG[12] = loadImage("assets/oreLG3.png");
        this.resourceIMG[13] = loadImage("assets/woolLG3.png");
        this.resourceIMG[14] = loadImage("assets/brickLG3.png");
        this.resourceIMG[15] = loadImage("assets/logsLG3.png");

        this.resourceIMG[16] = loadImage("assets/grainLG4.png");
        this.resourceIMG[17] = loadImage("assets/oreLG4.png");
        this.resourceIMG[18] = loadImage("assets/woolLG4.png");
        this.resourceIMG[19] = loadImage("assets/brickLG4.png");
        this.resourceIMG[20] = loadImage("assets/logsLG4.png");

        this.resourceIMG[21] = loadImage("assets/grainLG5.png");
        this.resourceIMG[22] = loadImage("assets/oreLG5.png");
        this.resourceIMG[23] = loadImage("assets/woolLG5.png");
        this.resourceIMG[24] = loadImage("assets/brickLG5.png");
        this.resourceIMG[25] = loadImage("assets/logsLG5.png");

        this.resourceIMG[26] = loadImage("assets/grainLG6.png");
        this.resourceIMG[27] = loadImage("assets/oreLG6.png");
        this.resourceIMG[28] = loadImage("assets/woolLG6.png");
        this.resourceIMG[29] = loadImage("assets/brickLG6.png");
        this.resourceIMG[30] = loadImage("assets/logsLG6.png");




        this.center=new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, model,model.getResourceTiles()[0],model.getTokens()[0]);
        this.debugger = new Debug(this,center);
        //debugger.displayClosed();
        this.model.setDisplayMode(10);
        this.game = null; //when restarting we want to set the server to null


        frameRate(10.0f);

    }





    public void draw() {


        if (model.getDisplayMode() <= 6) {
            fill(255, 0, 0, 0);
            fill(0);
            background(0, 188, 212);
            model.displayBoard();

/*    UNCOMMENT THESE LINES IF YOU WISH TO USE THE BUILT IN DEBUGGER
            if (debugger.open) {
                debugger.displayOpen();
            }
            if (!debugger.open) {
                background(0, 188, 212);

                debugger.displayClosed();
                center.getModel().displayBoard();
            }
            */
        }

        model.displayMenus();
        if(model.getDisplayMode() == 7) {
            model.getMenus().getWaitScreen().checkButton();
        }

        if(model.getDisplayMode() == 10){
            model.getWarning().setVisible(false);
        }
        if(model.getDisplayMode() != 10){
            model.getMenus().getEscape().display();
        }

        model.getWarning().display();

            fill(0);
       fill(255);
       // textSize(10);
        //text("RobSeq: " + model.getMenus().getRobDialogue().getRobSequence() + "\n Tool: " + currentTool + "\nDM: " + model.getDisplayMode(), 10,10);


    }


        @Override
        public void mousePressed () {
            //block all button checks when on the escape menu
            if(model.getMenus().getEscape().isVisible()){
                return;
            }
            model.checkMenus();
            if(model.getWarning().checkButtons()){
                model.restartClient();
            }
            //regular board with debugging
            if (model.getDisplayMode() <= 5) {
                //debugger.mouseDebug();
                model.checkSelected(this.currentTool);

            }
            //dice roll
            if (model.getDisplayMode() == 6) {

            }
        }


    public void keyPressed(){
        if(key == ESC){
            key = 0;
            if(model.getDisplayMode() != 10){
                model.getMenus().getEscape().toggleDisplay();
            }
        }
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
            model.getMenus().getWaitScreen().setHostIp(ip);
            model.setDisplayMode(7);
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR");
            this.model.getMenus().setConnect(new ConnectMenu(this,300,200,true));
        }
    }


    /**
     * Given an integer index
     * returns the english string name
     * of that resource
     * @param index the index of the resource
     * @return the english name of the resource
     */
    public String mapResource(int index){
        if(index == 1){
            return "Wheat";
        }
        if(index == 2){
            return "Ore";
        }
        if(index == 3){
            return "Wool";
        }
        if(index == 4){
            return "Brick";
        } else{
            return "Logs";
        }
    }

    public static void main(String args[]) {
        PApplet.main(new String[] {"Prototype5.Board" });
    }
    //"--present",
}
