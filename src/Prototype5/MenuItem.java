package Prototype5;

import java.awt.*;

/**
 * Created by Tom_Bryant on 6/30/15.
 * A menu item interface. Menu items exist on the menu bar
 */
public class MenuItem {

    public int width;
    public int height;
    public Point startingPos;
    public String toolTip;
    public boolean highlighted;

    Board parent;


    public MenuItem(int width, int height, String toolTip,  Board parent){
        this.startingPos = new Point(0,0);
        this.width = width;
        this.height = height;
        this.parent = parent;
        this.toolTip = toolTip;
        this.highlighted = false;
    }

    /**
     * Checks the tooltip and displays accordingly.
     */
    public void display(){
        int tempX = startingPos.x;
        int tempY = startingPos.y;
        if(!highlighted){
            parent.stroke(0,0,0,0);
            parent.fill(62,39,35);
        } else {
            parent.stroke(0,0,0,0);
            parent.fill(245,127,23);
        }
        //check to see it it's highligted
        if(parent.currentTool == getTool()){
            parent.stroke(0,0,0,0);
            parent.fill(245,127,23);
        }
        if(toolTip.equals("town")){
            Point coords = new Point(startingPos.x + (width/2), startingPos.y + (height/2));
            parent.beginShape();
            parent.vertex(coords.x, coords.y - (height / 2));
            parent.vertex(coords.x - (width / 2), coords.y);
            parent.vertex(coords.x - ((width / 2) - (width / 8)), coords.y);
            parent.vertex(coords.x - ((width / 2) - (width / 8)), coords.y + (height / 2));
            parent.vertex(coords.x + ((width / 2) - (width / 8)), coords.y + (height / 2));
            parent.vertex(coords.x + ((width / 2) - (width / 8)), coords.y);
            parent.vertex(coords.x + (width / 2), coords.y);
            parent.endShape();
        }
        if(toolTip.equals("road")){
            Point end = new Point(startingPos.x + width - 9, startingPos.y + height -9);
            Point start = new Point(startingPos.x + 9, startingPos.y + 9);

            Point midPoint = new Point((start.x + end.x) /2, (start.y + end.y)/2);
            Point aStart = new Point((start.x + midPoint.x)/2, (start.y + midPoint.y)/2);
            Point bStart = new Point((end.x + midPoint.x)/2, (end.y + midPoint.y)/2);
            int slopeX = (end.x - start.x) * -1;
            int slopeY = (end.y - start.y) * -1;
            parent.beginShape();
            parent.vertex(bStart.x + slopeY, bStart.y + -slopeX);
            parent.vertex(aStart.x + slopeY, aStart.y + -slopeX);
            parent.vertex(aStart.x - slopeY, aStart.y + slopeX);
            parent.vertex(bStart.x - slopeY, bStart.y + slopeX);
            parent.endShape();
        }
        if(toolTip.equals("noTool")){
            Point end = new Point(startingPos.x + width - 9, startingPos.y + height -9);
            Point start = new Point(startingPos.x + 9, startingPos.y + 9);
            Point midPoint = new Point((start.x + end.x) /2, (start.y + end.y)/2);
            Point aStart = new Point((start.x + midPoint.x)/2, (start.y + midPoint.y)/2);
            Point bStart = new Point((end.x + midPoint.x)/2, (end.y + midPoint.y)/2);
            int slopeX = (end.x - start.x) * -1;
            int slopeY = (end.y - start.y) * -1;
            parent.beginShape();
            parent.vertex(bStart.x + slopeY, bStart.y + -slopeX);
            parent.vertex(aStart.x + slopeY, aStart.y + -slopeX);
            parent.vertex(aStart.x - slopeY, aStart.y + slopeX);
            parent.vertex(bStart.x - slopeY, bStart.y + slopeX);
            parent.endShape();
            end = new Point(startingPos.x +  9, startingPos.y + height -9);
            start = new Point(startingPos.x + width - 9, startingPos.y + 9);
            midPoint = new Point((start.x + end.x) /2, (start.y + end.y)/2);
            aStart = new Point((start.x + midPoint.x)/2, (start.y + midPoint.y)/2);
            bStart = new Point((end.x + midPoint.x)/2, (end.y + midPoint.y)/2);
            slopeX = (end.x - start.x);
            slopeY = (end.y - start.y);
            parent.beginShape();
            parent.vertex(bStart.x + slopeY, bStart.y + -slopeX);
            parent.vertex(aStart.x + slopeY, aStart.y + -slopeX);
            parent.vertex(aStart.x - slopeY, aStart.y + slopeX);
            parent.vertex(bStart.x - slopeY, bStart.y + slopeX);
            parent.endShape();
        }
        if(toolTip.equals("city")){
            parent.beginShape();
            parent.vertex(tempX, (tempY + (height/2)));
            parent.vertex(tempX, (tempY + height));
            parent.vertex(tempX + width, (tempY + height));
            parent.vertex(tempX + width, (tempY + (height/2)));
            parent.vertex(tempX + width, (tempY + (height/4)));
            parent.vertex(tempX + width - (width/4), tempY);
            parent.vertex(tempX + (width/2), (tempY + (height/4)));
            parent.vertex(tempX + (width/2), (tempY + (height/2)));
            parent.endShape();

        }
        if(toolTip.equals("END")){
            parent.rect(tempX, tempY, width, height);
            parent.textSize(13);
            parent.fill(121, 85, 72);
            int beginX = (int)(tempX + (((width /2)) - (parent.textWidth("END")/2)));
            int beginY = ((tempY + ((height/2)) + 5));
            parent.text("END", beginX,beginY );
        }
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point getStartingPos() {
        return startingPos;
    }

    public void setStartingPos(Point startingPos) {
        this.startingPos = startingPos;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public Board getParent() {
        return parent;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }


    /**
     * Checks to see if this the mouse has hovered over this item.
     * Checks by tooltip
     */
    public boolean checkHover(){

            if (parent.mouseX >= startingPos.x && parent.mouseX <= startingPos.x+width &&
                    parent.mouseY >= startingPos.y && parent.mouseY <= startingPos.y+height) {
                this.highlighted = true;
                return true;
            } else {
                this.highlighted = false;
                return false;
            }

    }

    /**
     * Returns the tool id of this button
     * 0 = No Tool
     * 1 = town
     * 2 = road
     * 3 = city
     * 4 = END TURN
     * @return
     */
    public int getTool(){
        if(toolTip.equals("town")){
            return 1;
        }
        if(toolTip.equals("road")){
            return 2;
        }
        if(toolTip.equals("city")){
            return 3;
        }
        if(toolTip.equals("END")){
            return 4;
        }
        else return 0;
    }


}
