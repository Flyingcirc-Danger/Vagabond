package Prototype5;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tom_Bryant on 6/30/15.
 * A class for rendering the bottom menu of the game.
 */
public class BottomMenu {

    private Board parent; //canvas to draw on
    private int width; //width of the menu
    private int height; //height of the menu
    private ArrayList<BottomMenuButton> buttons; //buttons on the menu
    private boolean myTurn;

    public BottomMenu(Board parent){
        this.parent = parent;
        this.width = parent.SCREEN_WIDTH;
        this.height = 50;
        this.buttons = new ArrayList<BottomMenuButton>();
        this.myTurn = false;
        initMenu();

    }

    /**
     * Displays the bottom menu bar
     * Won't display if not myTurn
     */
    public void display(){
        parent.fill(255, 235, 59);
        parent.rect(0,parent.SCREEN_HEIGHT - height - 2, width, height);
        parent.fill(121, 85, 72);
        parent.rect(0,parent.SCREEN_HEIGHT - height, width, height);
        int playerNo = parent.model.getPlayer().getId();


        int runningTotal = 0;
        ArrayList<Integer> players = new ArrayList<Integer>();
        players.addAll(parent.model.getPlayerList());
        players.add(parent.model.getPlayer().getId());
        Collections.sort(players);
        for(int i = players.size() -1; i >= 0; i--) {
            int[] playerColor = parent.model.getColor(players.get(i));
            parent.fill(playerColor[0], playerColor[1],playerColor[2]);
            if(players.get(i) == parent.model.getPlayerTurn()){
                parent.strokeWeight(5);
                parent.stroke(255,171,64);
            } else{
                parent.strokeWeight(1);
                parent.stroke(0,0,0);
            }
            parent.rect(parent.SCREEN_WIDTH - 260 - runningTotal, parent.SCREEN_HEIGHT - 38, 25, 25);
            parent.fill(255);
            parent.textSize(15);
            parent.textAlign(parent.CENTER,parent.CENTER);
            parent.text(players.get(i), parent.SCREEN_WIDTH - 260 + 13 - runningTotal, parent.SCREEN_HEIGHT - 38 + 13);
            parent.textAlign(parent.LEFT);
            parent.strokeWeight(1);
            if(players.get(i) == parent.model.getPlayer().getId()){
                parent.image(parent.images[27],parent.SCREEN_WIDTH - 260 - runningTotal + 3, parent.SCREEN_HEIGHT - 38 - 10);
            }
            runningTotal += 35;
        }





        if(parent.model.getPlayer().getId() == parent.model.getPlayerTurn()){
            myTurn = true;
        } else {
            myTurn = false;
            return;
        }
        for(BottomMenuButton bt : buttons){
            parent.fill(0);
            bt.checkHover();
            bt.display();
        }
    }

    /**
     * Initializes the menu
     * populating all it's values
     */
    public void initMenu(){
        buttons.add(new BottomMenuButton(30,30,"noTool",this.parent));
        buttons.add(new BottomMenuButton(30,30,"town",this.parent));
        buttons.add(new BottomMenuButton(30,30,"road",this.parent));
        buttons.add(new BottomMenuButton(30,30,"city",this.parent));
        buttons.add(new BottomMenuButton(30,30,"END", this.parent));
        spaceMenu();
    }

    /**
     * Spaces the menu out
     * 2px per item
     */
    public void spaceMenu(){
        int runningTotalX = 20;
        for(BottomMenuButton bt: buttons){
            Point temp = new Point(runningTotalX,this.parent.SCREEN_HEIGHT - height + ((height - bt.height)/2) );
            bt.setStartingPos(temp);
            runningTotalX += (10 + bt.width);
        }
    }

    /**
     * Checks which part of the menu is selected.
     * Won't check if not my turn;
     */
    public void checkSelected(){
        if(!myTurn){
            return;
        }
        for(BottomMenuButton bt : buttons)
            if (bt.checkHover()) {
                parent.currentTool = bt.getTool();
            }

            }

}
