package Prototype5;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tom_Bryant on 6/30/15.
 * A class for rendering the bottom menu of the game.
 */
public class BottomMenu {

    private Board parent; //canvas to draw on
    private int width; //width of the menu
    private int height; //height of the menu
    private ArrayList<BottomMenuButton> buttons; //buttons on the menu

    public BottomMenu(Board parent){
        this.parent = parent;
        this.width = parent.SCREEN_WIDTH;
        this.height = 50;
        this.buttons = new ArrayList<BottomMenuButton>();
        initMenu();

    }

    /**
     * Displays the bottom menu bar
     */
    public void display(){
        parent.fill(255, 235, 59);
        parent.rect(0,parent.SCREEN_HEIGHT - height - 2, width, height);
        parent.fill(121, 85, 72);
        parent.rect(0,parent.SCREEN_HEIGHT - height, width, height);
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
     */
    public void checkSelected(){
        for(BottomMenuButton bt : buttons)
            if (bt.checkHover()) {
                parent.currentTool = bt.getTool();
            }

            }

}
