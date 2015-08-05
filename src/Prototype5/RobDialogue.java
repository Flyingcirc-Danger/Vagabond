package Prototype5;



import processing.core.PConstants;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * A dialogue for the robber
 * Created by Tom_Bryant on 7/31/15.
 */
public class RobDialogue {

    private Board parent;
    private ArrayList<Integer> targets;
    private int[] okayButton;
    private int[] cancelButton;
    private HexTile robberTile;
    private int playerTarget; //the player that the robbing was leveraged against.
    private int wait;//for elipsis animation
    private int itemRobbed; //after a sucessful robbery, the index of the item robbed is returned
    private int robSequence; //the stage of robbing this dialogue is currently on.


    public RobDialogue(Board parent){
        this.parent = parent;
        this.targets = new ArrayList<Integer>();
        okayButton = new int[4];
        cancelButton = new int[4];
        this.robberTile = new HexTile();
        this.playerTarget = 0; //default to 0
        this.wait = 0;
        this.itemRobbed = 1;
        this.robSequence = 5;
    }


    /**
     * Given a hex tile, this method
     * takes the owners and populates the "targets"
     * arraylist, for possible targets to steal from.
     * @param newTile the tile that the robber currently sits.
     */
    public void setTargets(HexTile newTile){
        if(this.robberTile != newTile) {
            this.robberTile = newTile;
            this.targets = new ArrayList<Integer>();
            for (int value : robberTile.getOwners().keySet()) {
                if (value != parent.model.getPlayer().getId()) {
                    targets.add(value);
                }
            }
        }

    }

    public boolean isRobConfirm() {
        if (this.robSequence == 1) {
            return true;
        }
        return false;
    }

    public void setRobConfirm() {
        this.robSequence = 1;
    }

    public boolean isToolSwitch() {
        if(this.robSequence == 0){
            return true;
        }
        return false;
    }

    public void setToolSwitch() {
        this.robSequence = 0;
    }

    public void display() {
        if (isRobConfirm()) {
            parent.cursor(parent.ARROW);
            parent.textSize(20);
            if (targets.size() == 0) {
                int height = 100;
                int width = (int) parent.textWidth("Steal From Nobody?") + 40;
                int startX = (parent.SCREEN_WIDTH / 2) - (width / 2);
                int startY = (parent.SCREEN_HEIGHT / 2) - (height / 2);
                parent.stroke(0,0,0,0);
                parent.fill(198, 40, 40);
                parent.rect(startX, startY, width, height,10,10,10,10);
                parent.fill(255);
                parent.text("Steal From Nobody?",startX + 20, startY + (35));
                parent.fill(0, 0, 0, 30);
                parent.rect(startX+2, startY+2, width, height,10,10,10,10);
                parent.fill(198, 40, 40);
                int buttonWidth = ((width - 40)/2) - 5;
                parent.rect(startX + 20,startY + height - 50, buttonWidth, 40,5,5,5,5);
                okayButton[0] = startX + 20;
                okayButton[1] = startY + height -50;
                okayButton[2] = buttonWidth;
                okayButton[3] = 40;
                parent.rect(startX + width - buttonWidth - 20, startY + height - 50, buttonWidth, 40,5,5,5,5);
                cancelButton[0] = startX + width - buttonWidth - 20;
                cancelButton[1] = startY + height - 50;
                cancelButton[2] = buttonWidth;
                cancelButton[3] = 40;
                parent.fill(0, 0, 0,30);
                parent.rect(startX + 20 + 2,startY + height - 50+2, buttonWidth, 40,5,5,5,5);
                parent.rect(startX + width +2- buttonWidth - 20, startY+2 + height - 50, buttonWidth, 40,5,5,5,5);
                parent.fill(255);
                parent.text("Okay", startX + 20 + (buttonWidth/2) - (parent.textWidth("Okay")/2), startY + height - 20);
                parent.text("Cancel", startX + width - 20 - (buttonWidth/2) - (parent.textWidth("Cancel")/2), startY + height - 20);
            }
            if (targets.size() > 0) {
                int width = (int) parent.textWidth("Select who to steal from: ") + 40;
                int height = 110 + (targets.size() * 40);
                int startX = (parent.SCREEN_WIDTH / 2) - (width / 2);
                int startY = (parent.SCREEN_HEIGHT / 2) - (height / 2);
                parent.stroke(0,0,0,0);
                parent.fill(198, 40, 40);
                parent.rect(startX, startY, width, height,10,10,10,10);
                parent.fill(255);
                parent.textSize(20);
                parent.text("Select who to steal from: ",startX + (width/2) - (parent.textWidth("Select who to steal from: ")/2), startY + (35));
                parent.fill(0, 0, 0, 30);
                parent.rect(startX+2, startY+2, width, height,10,10,10,10);
                parent.fill(198, 40, 40);
                int runningTotal = 40;
                parent.fill(255);
                parent.textSize(16);
                int selectWidth = 40 + (int)parent.textWidth("Player 1");
                for(int i = 0; i < targets.size(); i++){
                    parent.fill(198, 40, 40);
                    parent.rect(startX + (width/2) - (selectWidth/2), startY+runningTotal+5,selectWidth,40, 5,5,5,5);
                    parent.image(parent.images[19],startX + (width/2) - (selectWidth/2), startY+runningTotal+10);
                    parent.fill(255);
                    parent.text("Player " + targets.get(i), startX + (width/2) - (selectWidth/2) + 35, startY + runningTotal + 28);
                    parent.fill(0,0,0,30);
                    parent.rect(startX + (width/2) - (selectWidth/2)+2, startY+runningTotal+5+2,selectWidth,40, 5,5,5,5);
                    runningTotal += 45;
                }
                runningTotal += 5;
                parent.fill(198, 40, 40);
                parent.rect(startX + (width/2) - (selectWidth/2), startY + runningTotal, selectWidth, 40,5,5,5,5);
                cancelButton[0] = startX + (width/2) - (selectWidth/2);
                cancelButton[1] =  startY + runningTotal;
                cancelButton[2] = selectWidth;
                cancelButton[3] = 40;
                parent.fill(0, 0, 0,30);

                parent.fill(255);

                parent.text("Cancel", startX + (width/2)  - (parent.textWidth("Cancel")/2), startY + runningTotal + 27);
                parent.fill(0,0,0,30);
                parent.rect(startX + (width/2) - (selectWidth/2)+2, startY + runningTotal+2, selectWidth, 40,5,5,5,5);
            }

        }
        else if(robSequence == 2){
            displayRobInProgress();
        }
        else if(robSequence == 3){
            displayRobSuccess();
        }
        else if(robSequence == 4){
            displayVictimDialogue();
        }
    }


    public void checkButtons(){

        //if the robber has been placed
        if(isRobConfirm()){
            //player buttons if the target size >= 0;
            if(targets.size() > 0){
                //check cancel button.
                if(Listeners.overRect(cancelButton[0],cancelButton[1],cancelButton[2],cancelButton[3],parent)){
                    robSequence = 0;
                    this.robberTile.setRobber(false);
                }
                //check all of the possible player buttons
                int selectWidth = 40 + (int)parent.textWidth("Player 1");
                int width = ((int) parent.textWidth("Select who to steal from: ") + 40);
                int height = 110 + (targets.size() * 40);
                int startX = (parent.SCREEN_WIDTH / 2) - (width / 2);
                int startY = (parent.SCREEN_HEIGHT / 2) - (height / 2);
                int runningTotal = 40;
                int buttonHeight = 40;
                for(int i = 0; i < targets.size(); i++) {
                    int x = startX + (width / 2) - (selectWidth / 2);
                    int y = startY + runningTotal + 5;
                    if(Listeners.overRect(x,y,selectWidth,40,parent)){
                        System.out.println("Steal From Player " + targets.get(i));
                        this.playerTarget = targets.get(i);
                        parent.model.setRobberTile(robberTile);
                        parent.model.setStealManifest(ObjectParser.parseSteal(parent.model,targets.get(i),robberTile,true));
                        parent.currentTool = 0;
                        parent.model.setDisplayMode(0);
                        robSequence = 2;
                    }
                    runningTotal += 45;
                }

            }
            //okay & cancel buttons otherwise
            else{
                if(Listeners.overRect(okayButton[0],okayButton[1],okayButton[2],okayButton[3],parent)){
                   //send the robber xml
                    parent.model.setRobberTile(robberTile);
                    parent.model.setStealManifest(ObjectParser.parseSteal(parent.model,0,robberTile,true));
                    parent.currentTool = 0;
                    parent.model.setDisplayMode(0);
                    robSequence = 5;
                    parent.cursor(parent.ARROW);
                }
                if(Listeners.overRect(cancelButton[0],cancelButton[1],cancelButton[2],cancelButton[3],parent)){
                    robSequence = 0;
                    this.robberTile.setRobber(false);
                }
            }
        }
        if(isToolSwitch()){
            parent.textSize(20);
            if(Listeners.overRect((parent.SCREEN_WIDTH/2) - (((int)parent.textWidth("Move The Robber!") + 40)/2)
                    + (((int)parent.textWidth("Move The Robber!") + 40)/2) - 50,
                    (parent.SCREEN_HEIGHT/2) - (100/2)+ ((100/4)*3) -25,100,40,parent)){
                parent.currentTool = 7;
                this.robSequence = 6;
            }
        }
        if(robSequence == 3){
            int height = 150;
            int width = (int)parent.textWidth("From Player 1")  + 40;
            int startX = (parent.SCREEN_WIDTH/2) - (width/2);
            int startY = (parent.SCREEN_HEIGHT/2) - (height/2);
            int runningTotal = 96;
            if(Listeners.overRect(startX + (width/2) - 50, startY + runningTotal,100,40,parent)){
                robSequence = 5;
            }
        }

        if(robSequence == 4){
            int height = 130;
            int width = (int)parent.textWidth("From Player 1")  + 40;
            int startX = (parent.SCREEN_WIDTH/2) - (width/2);
            int startY = (parent.SCREEN_HEIGHT/2) - (height/2);
            int runningTotal = 77;
            if(Listeners.overRect(startX + (width/2) - 50, startY + runningTotal,100,40,parent)){
                robSequence = 5;
            }
        }

    }

    /**
     * Notifies the player to move the robber
     */
    public void displayRobberNotification(){
        parent.textSize(20);
        int width = (int)parent.textWidth("Move The Robber!") + 40;
        int height = 100;
        int startX = (parent.SCREEN_WIDTH/2) - (width/2);
        int startY = (parent.SCREEN_HEIGHT/2) - (height/2);
        parent.stroke(0,0,0,0);
        parent.fill(121, 85, 72);
        parent.rect(startX, startY, width,height,5,5,5,5);
        parent.fill(0,0,0,30);
        parent.rect(startX+2, startY+2, width,height,5,5,5,5);
        parent.fill(255);
        parent.text("Move The Robber!", startX + (width/2) - (parent.textWidth("Move The Robber!")/2), startY + (height/4) + 10);
        parent.fill(198, 40, 40);
        parent.rect(startX + (width/2) - 50, startY + ((height/4)*3) -25,100,40,5,5,5,5);
        parent.fill(255, 235, 59);
        parent.textFont(parent.fonts[1]);
        parent.text("OK", startX + (width/2) - (parent.textWidth("OK")/2), startY + ((height/4)*3) +2);
    }

    /**
     * Once a target has been selected, the robbing in process screen displays
     */
    public void displayRobInProgress(){
        int width = (int)parent.textWidth("Stealing From Player 1...") + 40;
        int baseText = (int)parent.textWidth("Stealing From Player 1") + 40;
        int height = 70;
        parent.textSize(20);
        int startX = (parent.SCREEN_WIDTH/2) - (width/2);
        int startY = (parent.SCREEN_HEIGHT/2) - (height/2);
        parent.stroke(0,0,0,0);
        parent.fill(121, 85, 72);
        parent.rect(startX, startY, width,height,5,5,5,5);
        parent.fill(0,0,0,30);
        parent.rect(startX+2, startY+2, width,height,5,5,5,5);
        parent.fill(255);
        parent.text("Stealing From Player " + playerTarget, (parent.SCREEN_WIDTH / 2) - (parent.textWidth("Stealing From Player 1...") / 2), (parent.SCREEN_HEIGHT / 2) + 8);
        if(wait == 30){
            wait = 0;
        }
        if(wait <= 10) {
            parent.text(".", (parent.SCREEN_WIDTH / 2) + (parent.textWidth("Stealing From Player") / 2), (parent.SCREEN_HEIGHT / 2) + 8);

        }
        else if(wait <= 20) {
            parent.text("..", (parent.SCREEN_WIDTH / 2) + (parent.textWidth("Stealing From Player") / 2), (parent.SCREEN_HEIGHT / 2) + 8);
        }
        else if(wait < 30) {
            parent.text("...", (parent.SCREEN_WIDTH / 2) + (parent.textWidth("Stealing From Player") / 2), (parent.SCREEN_HEIGHT / 2) + 8);
        }
        wait++;

    }


    /**
     * Once the target has been sucessfully robbed, the rob sucess
     * dialogue will appear
     */
    public void displayRobSuccess(){
        PImage resIMG = parent.images[itemRobbed];
        String resource = parent.mapResource(itemRobbed);

        int width = (int)parent.textWidth("From Player 1")  + 40;
        int height = 150;
        parent.textSize(15);
        int startX = (parent.SCREEN_WIDTH/2) - (width/2);
        int startY = (parent.SCREEN_HEIGHT/2) - (height/2);
        parent.stroke(0,0,0,0);
        parent.fill(121, 85, 72);
        parent.rect(startX, startY, width,height,5,5,5,5);
        parent.fill(0,0,0,30);
        parent.rect(startX+2, startY+2, width,height,5,5,5,5);
        parent.fill(255);
        int runningTotal = 30;
        parent.text("You Stole", startX + (width/2) - (parent.textWidth("You Stole")/2), startY + runningTotal);
        runningTotal += 5;
        int imageWidth = (35 + (int)parent.textWidth("1 x " + resource));
        parent.image(resIMG, (parent.SCREEN_WIDTH/2) - (imageWidth/2), startY + runningTotal);
        runningTotal += 24;
        parent.text("1 x " + resource, (parent.SCREEN_WIDTH/2) - (imageWidth/2) + 35, startY + runningTotal);
        runningTotal += 27;
        parent.text("From Player " + playerTarget, startX + (width/2) - (parent.textWidth("From Player " + playerTarget)/2), startY + runningTotal);
        parent.fill(198, 40, 40);
        runningTotal += 10;
        parent.rect(startX + (width/2) - 50, startY + runningTotal,100,40,5,5,5,5);
        parent.fill(255, 235, 59);
        parent.textFont(parent.fonts[1]);
        runningTotal += 28;
        parent.text("OK", startX + (width/2) - (parent.textWidth("OK")/2), startY + runningTotal);


    }

    /**
     * If you are the victim of a robber this dialogue
     * will inform you of what resource you have lost
     */
    public void displayVictimDialogue(){
        PImage resIMG = parent.images[itemRobbed];
        String resource = parent.mapResource(itemRobbed);

        int width = (int)parent.textWidth("From Player 1")  + 40;
        int height = 130;
        parent.textSize(15);
        int startX = (parent.SCREEN_WIDTH/2) - (width/2);
        int startY = (parent.SCREEN_HEIGHT/2) - (height/2);
        parent.stroke(0,0,0,0);
        parent.fill(121, 85, 72);
        parent.rect(startX, startY, width,height,5,5,5,5);
        parent.fill(0,0,0,30);
        parent.rect(startX+2, startY+2, width,height,5,5,5,5);
        parent.fill(255);
        int runningTotal = 30;
        parent.text("Player " + parent.model.getPlayerTurn() + " stole", startX + (width/2) - (parent.textWidth("Player " + parent.model.getPlayerTurn() + "Stole")/2), startY + runningTotal);
        runningTotal += 5;
        int imageWidth = (35 + (int)parent.textWidth("1 x " + resource));
        parent.image(resIMG, (parent.SCREEN_WIDTH/2) - (imageWidth/2), startY + runningTotal);
        runningTotal += 24;
        parent.text("1 x " + resource, (parent.SCREEN_WIDTH/2) - (imageWidth/2) + 35, startY + runningTotal);
        parent.fill(198, 40, 40);
        runningTotal += 18;
        parent.rect(startX + (width/2) - 50, startY + runningTotal,100,40,5,5,5,5);
        parent.fill(255, 235, 59);
        parent.textFont(parent.fonts[1]);
        runningTotal += 28;
        parent.text("OK", startX + (width/2) - (parent.textWidth("OK")/2), startY + runningTotal);

    }


    public int getRobSequence() {
        return robSequence;
    }

    /**
     * Decides which dialogue to show.
     * 0 = no robber placed
     * 1 = robber confirm scree
     * 2 = robbing in process
     * 3 = robbing success
     * 4 = robbing victim screen
     * 5 = currently not robbing.
     * @param robSequence
     */
    public void setRobSequence(int robSequence) {
        this.robSequence = robSequence;
    }

    public HexTile getRobberTile() {
        return robberTile;
    }

    public void setRobberTile(HexTile robberTile) {
        this.robberTile = robberTile;
    }


    public int getItemRobbed() {
        return itemRobbed;
    }

    public void setItemRobbed(int itemRobbed) {
        this.itemRobbed = itemRobbed;
    }

    public int getPlayerTarget() {
        return playerTarget;
    }

    public void setPlayerTarget(int playerTarget) {
        this.playerTarget = playerTarget;
    }

    /**
     * Advances the steal to the success dialogue
     * @param resource the id of the resource stolen
     * @param target the target of the steal
     */
    public void performSuccess(int resource, int target){
        this.itemRobbed = resource;
        this.playerTarget= target;
        this.robSequence = 3;
    }

    /**
     * Advances the steal to the victim dialogue
     * @param resource the resource stolen
     */
    public void performSteal(int resource){
        this.itemRobbed = resource;
        this.robSequence = 4;
    }
}


