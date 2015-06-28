package Prototype3.Prototype3_3;

import processing.core.PApplet;

/**
 * Created by Tom_Bryant on 6/25/15.
 */
public class Board extends PApplet {

    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public HexTile center;



    public void setup() {

        SCREEN_HEIGHT = 600 ;
        SCREEN_WIDTH = 800;
        size(SCREEN_WIDTH,SCREEN_HEIGHT);
        backgroundAndRandButton();
        BoardData init = new BoardData();
        this.center= new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, init,init.getResourceTiles()[0]);
        center.getModel().buildRandomBoard((center));
        //center.getModel().buildBoard((center));
       // fill(0);
       // text("center", center.getCenter().x, center.getCenter().y);


    }

    public void backgroundAndRandButton(){
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

        background(0, 188, 212);
        smooth(8);
        fill(0);
        text("Prototype 3.3", 20,20);
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
        rect(noDebugButtonX,noDebugButtonY , textWidth("   Debug Off   "), 30);
        fill(0);
        text("   Debug Off   ", noDebugButtonX, 30 );
        fill(255,243,224);
        rect(dRButtonX, dRButtonY , textWidth("   Debug Resources   "), 30);
        fill(0);
        text("   Debug Resources   ", dRButtonX, 30 );
    }



    public void draw() {


        fill(255,0,0,0);

        fill(0);
        center.getModel().displayBoard();
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
        int randButtonX =  (int) (SCREEN_WIDTH - 40 - textWidth("   Random   "));
        int classicButtonX = (int) (randButtonX - 10 - textWidth("   Classic   "));
        int dPButtonX =(int) (classicButtonX - 10 - textWidth("   Debug Points   "));
        int dSButtonX = (int) (dPButtonX - 10 - textWidth("   Debug Sides   "));
        int dRButtonX = (int) (dSButtonX - 10 - textWidth("   Debug Resources   "));
        int noDebugButtonX = (int) (dRButtonX - 10 - textWidth("   Debug Off   "));
        if (overRect(randButtonX, 10,(int)textWidth("   Random   ") ,30)) {
            clear();
            backgroundAndRandButton();
            BoardData reInit = new BoardData();
            this.center = new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, reInit,reInit.getResourceTiles()[0]);
            this.center.getModel().buildRandomBoard((center));
        }
        if (overRect(classicButtonX,10,(int)textWidth("   Classic   "),30)) {
            clear();
            backgroundAndRandButton();
            BoardData reInit = new BoardData();
            this.center = new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, reInit,reInit.getResourceTiles()[0]);
            this.center.getModel().buildBoard(center);
        }
        if (overRect(dPButtonX,10,(int)textWidth("   Debug Points   "),30)) {
            center.getModel().setDisplayMode(1);

        }
        if (overRect(dSButtonX,10,(int)textWidth("   Debug Sides   "),30)) {
            center.getModel().setDisplayMode(2);
        }
        if (overRect(noDebugButtonX,10,(int)textWidth("   Debug Off   "),30)) {
            center.getModel().setDisplayMode(0);
        }
        if (overRect(dRButtonX,10,(int)textWidth("   Debug Resources   "),30)) {
            center.getModel().setDisplayMode(3);
        }
    }





    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "Prototype3.Prototype3_3.Board" });
    }
}
