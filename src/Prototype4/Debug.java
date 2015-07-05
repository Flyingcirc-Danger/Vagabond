package Prototype4;

import processing.core.PApplet;

/**
 * A class to contain the  debug dropdown
 * Created by Tom_Bryant on 6/29/15.
 */

public class Debug {

    public Board parent; //canvas to draw on
    public boolean open; //check to see if the menu is open/closed
    public int width; //the baseline width of this menu


    public Debug(Board parent, HexTile center) {
        this.parent = parent;
        this.width = (int) parent.textWidth("   Debug Resources   ");
    }

    /**
     * A method for displaying the dropdown menu when closed
     */
    public void displayClosed() {
        parent.stroke(0, 0, 0, 0);
        parent.fill(255, 243, 224);
        int y = 10;
        int x = 30;
        int width = (int) parent.textWidth("   Debug Resources   ");
        parent.rect(x, y, width, 30);
        parent.fill(0);
        parent.textSize(14);
        parent.text("  Debug  ", (width / 2), 30);
    }

    /**
     * A method for displaying the dropdown menu when open
     */
    public void displayOpen() {
        //menu title
        parent.stroke(0, 0, 0, 50);
        parent.fill(158, 158, 158);
        int y = 10;
        int x = 30;
        int width = (int) parent.textWidth("   Debug Resources   ");
        int height = 30;
        parent.rect(x, y, width, 30);
        parent.fill(0);
        parent.textSize(14);
        parent.text("  Debug  ", (width / 2), height);

        //option 1 = random
        parent.fill(255, 243, 224);
        int rY = y + 30;
        int rHeight = height + 30;
        parent.rect(x, rY, width, 30);
        parent.fill(0);
        parent.textSize(14);
        parent.text("  Random  ", 35, rHeight);

        //option 2 = classic
        parent.fill(255, 243, 224);
        int cY = rY + 30;
        int cHeight = rHeight + 30;
        parent.rect(x, cY, width, 30);
        parent.fill(0);
        parent.textSize(14);
        parent.text("  Classic  ", 35, cHeight);

        //option 3 = debug points
        parent.fill(255, 243, 224);
        int dPY = cY + 30;
        int dPHeight = cHeight + 30;
        parent.rect(x, dPY, width, 30);
        parent.fill(0);
        parent.textSize(14);
        parent.text("  Debug Points  ", 35, dPHeight);

        //option 4 = debug sides
        parent.fill(255, 243, 224);
        int dSY = dPY + 30;
        int dSHeight = dPHeight + 30;
        parent.rect(x, dSY, width, 30);
        parent.fill(0);
        parent.textSize(14);
        parent.text("  Debug Sides  ", 35, dSHeight);


        //option 5 = debug resources
        parent.fill(255, 243, 224);
        int dRY = dSY + 30;
        int dRHeight = dSHeight + 30;
        parent.rect(x, dRY, width, 30);
        parent.fill(0);
        parent.textSize(14);
        parent.text("  Debug Resources  ", 35, dRHeight);


        //option 6 = clear debug
        parent.fill(255, 243, 224);
        int cLY = dRY + 30;
        int cLHeight = dRHeight + 30;
        parent.rect(x, cLY, width, 30);
        parent.fill(0);
        parent.textSize(14);
        parent.text("  Clear Debug ", 35, cLHeight);

    }

    /**
     * A method for checking to see if the mouse is over the head of the menu
     *
     * @return
     */
    public boolean overHead() {
        return overRect(30, 10, width, 30);
    }

    /**
     * A method for checking which item of the menu is selected
     * returns the menu item id (starting at index 1);
     *
     * @return
     */
    public int overMenu() {
        //option1
        if (overRect(30, 40, width, 30)) {
            return 1;
        }
        //option2
        if (overRect(30, 70, width, 30)) {
            return 2;
        }
        //option3
        if (overRect(30, 100, width, 30)) {
            return 3;
        }
        //option4
        if (overRect(30, 130, width, 30)) {
            return 4;
        }
        //option5
        if (overRect(30, 160, width, 30)) {
            return 5;
        }
        //option5
        if (overRect(30, 190, width, 30)) {
            return 6;
        }
        return 0;
    }

    /**
     * A method for checking a rectangular
     * space for a mouse click
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    boolean overRect(int x, int y, int width, int height) {
        if (parent.mouseX >= x && parent.mouseX <= x + width &&
                parent.mouseY >= y && parent.mouseY <= y + height) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


    /**
     * Handles all the mouse positioning checks for this
     * menu.
     */
    public void mouseDebug() {
        //open if it's not already
        if (!isOpen()) {
            if (this.overHead()) {
                this.open = true;
            } else {
                this.open = false;
            }
        }
        if (isOpen()) {
            //check to see what option is selected
            int result = this.overMenu();
            if (result == 1) {
                parent.clear();
                parent.background(0, 188, 212);
                this.displayClosed();
                this.open = false;
                parent.model = new BoardData(this.parent);
                parent.center = new HexTile(parent, parent.SCREEN_WIDTH / 2, parent.SCREEN_HEIGHT / 2, 50, parent.model, parent.model.getResourceTiles()[0], parent.model.getTokens()[0]);
                parent.center.getModel().buildRandomBoard((parent.center));
            }
            if (result == 2) {
                parent.clear();
                parent.background(0, 188, 212);
                this.displayClosed();
                this.open = false;
                parent.model = new BoardData(this.parent);
                parent.center = new HexTile(parent, parent.SCREEN_WIDTH / 2, parent.SCREEN_HEIGHT / 2, 50, parent.model, parent.model.getResourceTiles()[0], parent.model.getTokens()[0]);
                parent.center.getModel().buildBoard(parent.center);
            }
            if (result == 3) {
                parent.background(0, 188, 212);
                this.displayClosed();
                this.open = false;
                parent.center.getModel().displayBoard();
                parent.center.getModel().setDisplayMode(1);
            }
            if (result == 4) {
                parent.background(0, 188, 212);
                this.displayClosed();
                this.open = false;
                parent.center.getModel().displayBoard();
                parent.center.getModel().setDisplayMode(2);
            }
            if (result == 5) {
                parent.background(0, 188, 212);
                this.displayClosed();
                this.open = false;
                parent.center.getModel().displayBoard();
                parent.center.getModel().setDisplayMode(3);
            }
            if (result == 6) {
                parent.background(0, 188, 212);
                this.displayClosed();
                this.open = false;
                parent.center.getModel().displayBoard();
                parent.center.getModel().setDisplayMode(0);
            }
        }
    }

}
