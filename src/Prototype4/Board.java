package Prototype4;

import processing.core.PApplet;

/**
 * The main board class. Builds the datastructure
 * displays the board
 * Created by Tom_Bryant on 6/25/15.
 */
public class Board extends PApplet {

    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public HexTile center;
    public Debug debugger;



    public void setup() {

        SCREEN_HEIGHT = 600 ;
        SCREEN_WIDTH = 800;
        size(SCREEN_WIDTH,SCREEN_HEIGHT);
        BoardData init = new BoardData();
        this.center=new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, init,init.getResourceTiles()[0],init.getTokens()[0]);
        center.getModel().buildBoard((center));
        this.debugger = new Debug(this,center);
        background(0, 188, 212);
        debugger.displayClosed();





    }

    public void backgroundAndRandButton(){
        stroke(0,0,0,0);
        textSize(14);
        int randButtonX = (int) (SCREEN_WIDTH - 40 - textWidth("   Random   "));
        int randButtonY = 10;
        int classicButtonX = (int) (randButtonX - 10 - textWidth("   Classic   "));
        int classicButtonY = 10;
        int dPButtonX =(int) (classicButtonX - 10 - textWidth("   Debug Points   "));
        int dPButtonY = 10;
        int dSButtonX = (int) (dPButtonX - 10 - textWidth("   Debug Sides   "));
        int dSButtonY = 10 ;
        int dRButtonX = (int) (dSButtonX - 10 - textWidth("   Debug Resources   "));
        int dRButtonY = 10 ;
        int noDebugButtonX = (int) (dRButtonX - 10 - textWidth("   Debug Off   "));
        int noDebugButtonY = 10 ;


        smooth(8);
        fill(0);
        text("Prototype 3.3", 20,SCREEN_HEIGHT - 20);
        fill(244,67,54);
        rect(randButtonX,randButtonY , textWidth("   Random   "), 30);
        fill(0);
        text("   Random   ", randButtonX, 30 );
        fill(76,175,80);
        rect(classicButtonX,classicButtonY , textWidth("   Classic   "), 30);
        fill(0);
        text("   Classic   ", classicButtonX, 30 );
        fill(255,243,224);
        rect(dPButtonX,dPButtonY , textWidth("   Debug Points   "), 30);
        fill(0);
        text("   Debug Points   ", dPButtonX, 30 );
        fill(255,243,224);
        rect(dSButtonX,dSButtonY , textWidth("   Debug Sides   "), 30);
        fill(0);
        text("   Debug Sides   ", dSButtonX, 30 );
        fill(255,243,224);
        rect(dRButtonX, dRButtonY , textWidth("   Debug Resources   "), 30);
        fill(0);
        text("   Debug Resources   ", dRButtonX, 30 );
        fill(255,243,224);
        rect(noDebugButtonX,noDebugButtonY , textWidth("   Debug Off   "), 30);
        fill(0);
        text("   Debug Off   ", noDebugButtonX, 30 );
    }



    public void draw() {



        fill(255,0,0,0);

        fill(0);
        center.getModel().displayBoard();
        if(debugger.open){
            debugger.displayOpen();
        }
        if(!debugger.open){
            background(0, 188, 212);
            debugger.displayClosed();
            center.getModel().displayBoard();
        }
        //System.out.println(center.checkNeighbor("AB"));


        fill(0);



    }




    boolean overRect(int x, int y, int width, int height)  {
        if (mouseX >= x && mouseX <= x+width &&
                mouseY >= y && mouseY <= y+height) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void mousePressed() {
        if(!debugger.open) {
            if(debugger.overHead()){
                debugger.open = true;
            } else {
                debugger.open = false;
            }
        }
            if(debugger.open){
                int result = debugger.overMenu();
                if(result == 1) {
                    clear();
                    background(0, 188, 212);
                    debugger.displayClosed();
                    debugger.open = false;
                    BoardData reInit = new BoardData();
                    this.center = new HexTile(this, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 50, reInit, reInit.getResourceTiles()[0], reInit.getTokens()[0]);
                    this.center.getModel().buildRandomBoard((center));
                }
                if(result == 2){
                    clear();
                    background(0, 188, 212);
                    debugger.displayClosed();
                    debugger.open = false;
                    backgroundAndRandButton();
                    BoardData reInit = new BoardData();
                    this.center = new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, reInit,reInit.getResourceTiles()[0],reInit.getTokens()[0]);
                    this.center.getModel().buildBoard(center);
                }
                if(result == 3){
                    background(0, 188, 212);
                    debugger.displayClosed();
                    debugger.open = false;
                    center.getModel().displayBoard();
                    center.getModel().setDisplayMode(1);
                }
                if(result == 4){
                    background(0, 188, 212);
                    debugger.displayClosed();
                    debugger.open = false;
                    center.getModel().displayBoard();
                    center.getModel().setDisplayMode(2);
                }
                if(result == 5){
                    background(0, 188, 212);
                    debugger.displayClosed();
                    debugger.open = false;
                    center.getModel().displayBoard();
                    center.getModel().setDisplayMode(3);
                }
                if(result == 6){
                    background(0, 188, 212);
                    debugger.displayClosed();
                    debugger.open = false;
                    center.getModel().displayBoard();
                    center.getModel().setDisplayMode(0);
                }
            }

    }





    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "Prototype4.Board" });
    }
}
