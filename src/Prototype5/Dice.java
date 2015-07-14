package Prototype5;

import processing.core.PApplet;

import java.awt.*;
import java.util.Random;

/**
 * Created by Tom_Bryant on 7/13/15.
 * A class for displaying the dice roll
 */
public class Dice {

    private int dieOne;
    private int dieTwo;
    private int counter;
    private Board parent;
    private int lastD1;
    private int lastD2;


    public Dice(int dieOne, int dieTwo,Board parent){
        this.dieOne = dieOne;
        this.dieTwo = dieTwo;
        this.parent = parent;
        Random numb = new Random();
        counter = numb.nextInt((25 - 10 - 1) + 10) * 10;
        lastD1 = 6;
        lastD2 = 6;

    }


    public void display(){
        parent.fill(0,0,0,70);
        parent.rect(0, 0, parent.SCREEN_WIDTH, parent.SCREEN_HEIGHT);
        if(counter > 0){
            if(counter % 10  == 0) {
                Random numb = new Random();
                lastD1 = numb.nextInt((6 - 1 - 1) + 1);
                lastD2 = numb.nextInt((6 - 1 - 1) + 1);
            }
        } else {
            lastD1 = dieOne;
            lastD2 = dieTwo;
        }
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect((parent.SCREEN_WIDTH/2) - (170/2), (parent.SCREEN_HEIGHT/2) - 80/2, 170,110,20,20,20,20);
        //dice one
        Point tempOne = new Point((parent.SCREEN_WIDTH/2) - (130/2), (parent.SCREEN_HEIGHT/2) - (60/2) );
        constructDice(lastD1,tempOne);
        Point tempTwo = new Point((parent.SCREEN_WIDTH/2) - (130/2) + 70, (parent.SCREEN_HEIGHT/2) - (60/2) );
        constructDice(lastD2,tempTwo);
        parent.textFont(parent.fonts[1]);
        parent.textSize(20);
        parent.fill(255, 235, 59);
        parent.text("" + (lastD1 + lastD2), parent.SCREEN_WIDTH / 2 - ((parent.textWidth("" + (lastD1 + lastD2)) / 2)), ((parent.SCREEN_HEIGHT) / 2) + 57);
        counter--;
    }


    private void constructDice(int value, Point start){
        parent.fill(255,248,225);
        parent.stroke(0,0,0,0);
        parent.rect(start.x, start.y, 60, 60, 15, 15, 15, 15);
        parent.fill(0,0,0,30);
        parent.rect(start.x+2, start.y+2, 60, 60, 15, 15, 15, 15);
        parent.fill(38,50,56);
        if(value == 1){
           parent.ellipse(start.x + 30, start.y + 30, 10,10);
        }
        if(value == 2){
            parent.ellipse(start.x + 40, start.y + 20, 10,10);
            parent.ellipse(start.x + 20, start.y + 40, 10,10);
        }
        if(value == 3){
            parent.ellipse(start.x + 50, start.y + 10, 10,10);
            parent.ellipse(start.x + 10, start.y + 50, 10,10);
            parent.ellipse(start.x + 30, start.y + 30, 10,10);
        }
        if(value == 4){
            parent.ellipse(start.x + 15, start.y + 16, 10,10);
            parent.ellipse(start.x + 45, start.y + 15, 10,10);
            parent.ellipse(start.x + 15, start.y + 45, 10,10);
            parent.ellipse(start.x + 45, start.y + 45, 10,10);
        }
        if(value == 5){
            parent.ellipse(start.x + 15, start.y + 16, 10,10);
            parent.ellipse(start.x + 45, start.y + 15, 10,10);
            parent.ellipse(start.x + 15, start.y + 45, 10,10);
            parent.ellipse(start.x + 45, start.y + 45, 10,10);
            parent.ellipse(start.x + 30, start.y + 30, 10,10);
        }
        if(value == 6){
            parent.ellipse(start.x + 15, start.y + 10, 10,10);
            parent.ellipse(start.x + 45, start.y + 10, 10,10);
            parent.ellipse(start.x + 15, start.y + 50, 10,10);
            parent.ellipse(start.x + 45, start.y + 50, 10,10);
            parent.ellipse(start.x + 15, start.y + 30, 10,10);
            parent.ellipse(start.x + 45, start.y + 30, 10,10);
        }

    }


}
