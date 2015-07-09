package Prototype5;

import java.awt.*;

/**
 * The start menu for the game
 * Created by Tom_Bryant on 7/6/15.
 */
public class GameMenu {

    private int width;
    private int height;
    private Board parent;
    private Point center;


    public GameMenu(Board parent,int width, int height){
        this.parent = parent;
        this.center = new Point (parent.SCREEN_WIDTH/2, parent.SCREEN_HEIGHT/2);
        this.width = width;
        this.height = height;
    }

    public void display(){
        parent.background(0, 188, 212);
        parent.stroke(0,0,0,0);
        parent.smooth(8);
        parent.fill(0,0,0,70);
        parent.rect(0, 0, parent.SCREEN_WIDTH, parent.SCREEN_HEIGHT);
        parent.rect(center.x - (width/2)+2, center.y - (height/2)+2, width, height);
        parent.stroke(0, 0, 0, 0);
        parent.fill(255, 243, 224);
        parent.rect(center.x - (width/2), center.y - (height/2), width, height);
    }


}
