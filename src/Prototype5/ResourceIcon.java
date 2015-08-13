package Prototype5;

import processing.core.PImage;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Tom_Bryant on 7/13/15.
 * An Icon resource pairing
 */
public class ResourceIcon {

    private Board parent;
    private String resource;
    private Point startPos;
    private int amount;

    public ResourceIcon(Board parent, String resource){
        this.parent = parent;
        this.resource = resource;

        this.startPos = new Point(0,0);
        this.amount = 0;
    }

    public void display(){
        parent.image(getImg(),startPos.x,5);
        parent.fill(78,52,46);
        parent.stroke(0,0,0,30);
        parent.rect(startPos.x + 32,7, 30,25,5,5,5,5);
        parent.fill(255);
        parent.textAlign(parent.LEFT);
        parent.textFont(parent.fonts[0]);
        parent.textSize(14);
        parent.text(getAmount(), startPos.x + 47 - (parent.textWidth("" +amount)/2), 5 + 15 + 7 );
    }

    /**
     * Displays the resource bar on the bottom of the screen
     */
    public void displayBottom(){
        parent.image(getImg(),startPos.x,parent.SCREEN_HEIGHT -35);
        parent.fill(78,52,46);
        parent.stroke(0,0,0,30);
        parent.rect(startPos.x + 32, parent.SCREEN_HEIGHT - 37, 30,25,5,5,5,5);
        parent.fill(255);
        parent.textFont(parent.fonts[0]);
        parent.textSize(14);
        parent.text(getAmount(), startPos.x + 47 - (parent.textWidth("" +amount)/2), parent.SCREEN_HEIGHT - (20) );
    }



    public Point getStartPos() {
        return startPos;
    }

    public void setStartPos(Point startPos) {
        this.startPos = startPos;
    }

    public int getAmount() {
        if(resource.equals("grain")){
            return parent.model.getPlayer().getGrain();
        }
        if(resource.equals("ore")){
            return parent.model.getPlayer().getOre();
        }
        if(resource.equals("wool")){
            return parent.model.getPlayer().getWool();
        }
        if(resource.equals("brick")){
            return parent.model.getPlayer().getBrick();
        }
        if(resource.equals("army")){
            return parent.model.getArmySize();
        }
        if(resource.equals("victorypoint")){
            return parent.model.getVictoryPoints();
        }
        return parent.model.getPlayer().getLogs();
    }


    public void displayTooltip(){
        if(resource.equals("victorypoint")){
            parent.stroke(0,0,0,0);
            parent.fill(121, 85, 72);
            parent.textSize(12);
            int height = height = 18 * 5;
            int width =(int) parent.textWidth("Largest Army : 0    ");
            HashMap<String, Integer> vp = parent.model.getVictoryBonus().getVictoryPointMap();
            parent.rect(startPos.x, 42, width,height,0,0,2,2);
            parent.fill(255);
            int runningTotal = 0;
            parent.text("Towns: " + vp.get("Town"), startPos.x + 5, 44 + runningTotal, startPos.x + width - 5, 42+ height -2);
            runningTotal += 16;
            parent.text("Cities: " + vp.get("City"), startPos.x + 5, 44 + runningTotal, startPos.x + width - 5, 42+ height -2);
            runningTotal += 16;
            parent.text("VP Cards: " + vp.get("Victory Point Card"), startPos.x + 5, 44 + runningTotal, startPos.x + width - 5, 42+ height -2);
            runningTotal += 16;
            parent.text("Longest Road: "+ vp.get("Longest Road"), startPos.x + 5, 44 + runningTotal, startPos.x + width - 5, 42+ height -2);
            runningTotal += 16;
            parent.text("Largest Army: " + vp.get("Largest Army"), startPos.x + 5, 44 + runningTotal, startPos.x + width - 5, 42+ height -2);
            //1 town, 2 city, 3 victory cards, 4, longest road, 5 largest army.
        }
        else{
            String newResource = new String();
            if(resource.equals("grain")){
                newResource = "Wheat";
            }
            if(resource.equals("ore")){
                newResource = "Ore";
            }
            if(resource.equals("wool")){
                newResource = "Wool";
            }
            if(resource.equals("logs")){
                newResource = "Logs";
            }
            if(resource.equals("brick")){
                newResource = "Bricks";
            }
            if(resource.equals("army")){
                newResource = "Soldiers";
            }
            parent.stroke(0,0,0,0);
            parent.fill(121, 85, 72);
            parent.textSize(12);
            int height = 20;
            int width = 10 + (int) parent.textWidth(newResource);
            parent.rect(startPos.x, 42,width,height,0,0,4,4);
            parent.fill(255);
            parent.text(newResource,startPos.x + 5, 58);
        }
    }



    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Returns a PImage that
     * is loaded in the parent board
     * based on the resource type
     */
    private PImage getImg(){
        if(resource.equals("grain")){
            return parent.images[1];
        }
        if(resource.equals("ore")){
            return parent.images[2];
        }
        if(resource.equals("wool")){
            return parent.images[3];
        }
        if(resource.equals("brick")){
            return parent.images[4];
        }
        if(resource.equals("army")){
            return parent.images[24];
        }
        if(resource.equals("victorypoint")){
            return parent.images[23];
        }
            return parent.images[5];
        }


}
