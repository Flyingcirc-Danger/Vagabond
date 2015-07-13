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

    public BottomMenu bottom;

    public int currentTool;

    public GameMenu gMenu;
    public ConnectMenu cMenu;
    public ResourceBar rBar;

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
        this.bottom = new BottomMenu(this);
        this.currentTool = 0;
        this.gMenu = new GameMenu(this, 300,400);
        this.cMenu = new ConnectMenu(this, 300,200,false);
        this.images = new PImage[6];
        this.rBar = new ResourceBar(this);
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


//        try {
//          ObjectParser.readSides(model,"points.xml");
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }


    }





    public void draw() {


        if(model.getDisplayMode() <= 5) {
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
            bottom.display();
            rBar.display();
            image(images[0], SCREEN_WIDTH - 220, SCREEN_HEIGHT - 70);
        }
        if(model.getDisplayMode() == 10) {
            model.displayConnect();
            rBar.display();
        }
            fill(0);



    }




    @Override
    public void mousePressed() {
        if(model.getDisplayMode() <= 5) {
            debugger.mouseDebug();
            bottom.checkSelected();
            model.checkSelected(this.currentTool);
        }
        if(model.getDisplayMode() == 10){
            cMenu.checkButtons();
        }
    }

    public void keyPressed(){
        if(model.getDisplayMode() ==10){
            if (cMenu.isTyping()){
                cMenu.addText(key);
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
            this.cMenu = new ConnectMenu(this,300,200,true);
        }
    }

    public static void main(String args[]) {
        PApplet.main(new String[] {  "Prototype5.Board" });
    }
    //"--present",
}
