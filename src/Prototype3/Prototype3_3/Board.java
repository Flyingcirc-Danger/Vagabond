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
        this.center= new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, new BoardData());
        center.getModel().buildRandomBoard((center));
        //center.getModel().buildBoard((center));


    }

    public void backgroundAndRandButton(){
        background(0, 188, 212);
        smooth(8);
        fill(0);
        text("Prototype 3.3", 20,20);
        fill(244,67,54);
        int randButtonX = (int) (SCREEN_WIDTH - 40 - textWidth("   Random   "));
        int randButtonY = 10;
        rect(randButtonX,randButtonY , textWidth("   Random   "), 30);
        fill(0);
        text("   Random   ", randButtonX, 30 );

        fill(76,175,80);
        int classicButtonX = (int) (randButtonX - 10 - textWidth("   Classic   "));
        int classicButtonY = 10;
        rect(classicButtonX,classicButtonY , textWidth("   Classic   "), 30);
        fill(0);
        text("   Classic   ", classicButtonX, 30 );
    }



    public void draw() {


        fill(255,0,0,0);

        fill(0);
        center.getModel().displayBoard(1);
        //System.out.println(center.checkNeighbor("AB"));

        fill(0);



    }


    public void hex(float cX, float cY, float radius){
        smooth(8);
        float sideToSide = (float)(Math.sqrt(3) * radius) / 2f;
        float canopyBase = (float) Math.sqrt((Math.pow(radius, 2)) - Math.pow(sideToSide,2));
        stroke(0,0,0,0);
        beginShape();
        vertex(cX, cY + radius);
        vertex(cX + sideToSide, cY +  (radius -canopyBase));
        vertex(cX + sideToSide, cY - (radius -canopyBase));
        vertex(cX, cY - radius);
        vertex(cX - sideToSide, cY - (radius -canopyBase));
        vertex(cX - sideToSide, cY +  (radius -canopyBase));
        endShape();

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
        if (overRect(randButtonX, 10,(int)textWidth("   Random   ") ,30)) {
            clear();
            backgroundAndRandButton();
            center = new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, new BoardData());
            center.getModel().buildRandomBoard((center));
        }
        if (overRect(classicButtonX,10,(int)textWidth("   Classic   "),30)) {
            clear();
            backgroundAndRandButton();
            center = new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, new BoardData());
            center.getModel().buildBoard(center);
        }
    }





    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "Prototype3.Prototype3_3.Board" });
    }
}
