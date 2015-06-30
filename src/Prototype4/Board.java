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
        textSize(14);
        background(0, 188, 212);
        debugger.displayClosed();





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
        debugger.mouseDebug();

    }





    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "Prototype4.Board" });
    }
}
