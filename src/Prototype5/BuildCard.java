package Prototype5;


/**
 * Created by Tom_Bryant on 7/19/15.
 * A class for constructing the build card info object.
 * Shows how many resources you need to build a certain structure
 * and how many victory points you get for having them.
 */
public class BuildCard {

    private Board parent;
    private int spacing;

    public BuildCard(Board parent){
        this.parent = parent;
        this.spacing = 10;

    }

    /**
     * Displays the information necessary for building a road
     */
    public void displayRoad(){
        int height = (2 * 30) + 45 + (spacing * 2);
        int width =  30 + 10 + (int)parent.textWidth("Victory Points = 0");
        int startY = parent.SCREEN_HEIGHT -72 - height;
        parent.stroke(0,0,0,0);
        parent.textSize(15);
        int startX = parent.SCREEN_WIDTH - 10 - (width);
        parent.fill(121, 85, 72);
        parent.rect(startX,startY,width,height);
        parent.fill(0,0,0,30);
        parent.rect(startX+2,startY+2,width,height);
        parent.fill(255);
        parent.text("Build Road", startX + ((width/2) - (parent.textWidth("Build Road")/2)), startY + 25);
        parent.textSize(12);
        parent.text("Victory Points = 0", startX + ((width / 2) - (parent.textWidth("Victory Points = 0") / 2)), startY + 45);
        parent.image(parent.images[4], startX + 10, startY + 55);
        if(parent.model.getPlayer().getBrick() >= 1){
            parent.fill(71,255,0);
        } else {
            parent.fill(255,57,0);
        }
        parent.text("Brick x 1", startX +50, startY + 70);
        parent.image(parent.images[5], startX + 10, startY + 85);
        if(parent.model.getPlayer().getLogs() >= 1){
            parent.fill(71,255,0);
        } else {
            parent.fill(255,57,0);
        }
        parent.text("Logs x 1", startX +50, startY + 102);

    }


    public void displayTown(){
        int height = (2 * 30) + 105 + (spacing * 2);
        int width =  30 + 10 + (int)parent.textWidth("Victory Points = 1");
        int startY = parent.SCREEN_HEIGHT -72 - height;
        parent.stroke(0,0,0,0);
        parent.textSize(15);
        int startX = parent.SCREEN_WIDTH - 10 - (width);
        parent.fill(121, 85, 72);
        parent.rect(startX,startY,width,height);
        parent.fill(0,0,0,30);
        parent.rect(startX+2,startY+2,width,height);
        parent.fill(255);
        parent.text("Build Town", startX + ((width/2) - (parent.textWidth("Build Town")/2)), startY + 25);
        parent.textSize(12);
        parent.text("Victory Points = 1", startX + ((width / 2) - (parent.textWidth("Victory Points = 1") / 2)), startY + 45);
        parent.image(parent.images[4], startX + 10, startY + 55);
        if(parent.model.getPlayer().getBrick() >= 1){
            parent.fill(71,255,0);
        } else {
            parent.fill(255,57,0);
        }
        parent.text("Brick x 1", startX +50, startY + 70);
        parent.image(parent.images[5], startX + 10, startY + 80);
        if(parent.model.getPlayer().getLogs() >= 1){
            parent.fill(71,255,0);
        } else {
            parent.fill(255,57,0);
        }
        parent.text("Logs x 1", startX +50, startY + 97);
        parent.image(parent.images[1], startX + 10, startY + 110);
        if(parent.model.getPlayer().getGrain() >= 1){
            parent.fill(71,255,0);
        } else {
            parent.fill(255,57,0);
        }
        parent.text("Wheat x 1", startX +50, startY + 130);

        parent.image(parent.images[3], startX + 10, startY + 145);
        if(parent.model.getPlayer().getWool() >= 1){
            parent.fill(71,255,0);
        } else {
            parent.fill(255,57,0);
        }
        parent.text("Wool x 1", startX +50, startY + 160);

    }
}
