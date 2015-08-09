package Prototype5;

import processing.core.PImage;

import java.util.HashMap;

/**
 * Created by Tom_Bryant on 7/30/15.
 * When a 7 is rolled this class builds the discard screen
 * for players who have more than 7 cards
 */
public class DiscardScreen {

    private Board parent;
    private PImage[] discardImages;
    private int resourceDiscard;
    private HashMap<Integer,Integer> discardPile;

    public DiscardScreen(Board parent,int resources){
        this.parent = parent;
        this.discardImages = new PImage[14];
        discardImages[0] = parent.loadImage("assets/discard/discardGrain.png");
        discardImages[1] = parent.loadImage("assets/discard/discardOre.png");
        discardImages[2] = parent.loadImage("assets/discard/discardWool.png");
        discardImages[3] = parent.loadImage("assets/discard/discardBrick.png");
        discardImages[4] = parent.loadImage("assets/discard/discardLogs.png");
        discardImages[5] = parent.loadImage("assets/discard/discardGrainBW.png");
        discardImages[6] = parent.loadImage("assets/discard/discardOreBW.png");
        discardImages[7] = parent.loadImage("assets/discard/discardWoolBW.png");
        discardImages[8] = parent.loadImage("assets/discard/discardBrickBW.png");
        discardImages[9] = parent.loadImage("assets/discard/discardLogsBW.png");
        discardImages[10] = parent.loadImage("assets/discard/discardMinus.png");
        discardImages[11] = parent.loadImage("assets/discard/discardPlus.png");
        discardImages[12] = parent.loadImage("assets/discard/discardMinusDisabled.png");
        discardImages[13] = parent.loadImage("assets/discard/discardPlusDisabled.png");
        discardPile = new HashMap<Integer,Integer>();
        discardPile.put(1,0);
        discardPile.put(2,0);
        discardPile.put(3,0);
        discardPile.put(4,0);
        discardPile.put(5,0);
        this.resourceDiscard = (resources/2);


    }

    public void display(){
        parent.textAlign(parent.LEFT);
        parent.background(255,193,7);
        //calculateDiscardAmount();
        parent.fill(198,40,40);
        parent.stroke(0,0,0,0);
        parent.rect(20,20,parent.SCREEN_WIDTH - 40, parent.SCREEN_HEIGHT - 40);
        parent.fill(255,193,7);
        parent.ellipse(0,0,100,100);
        parent.ellipse(parent.SCREEN_WIDTH,0,100,100);
        parent.ellipse(parent.SCREEN_WIDTH,parent.SCREEN_HEIGHT,100,100);
        parent.ellipse(0,parent.SCREEN_HEIGHT,100,100);
        parent.textFont(parent.fonts[1]);
        parent.textSize(40);
        parent.fill(0,0,0,30);
        parent.text("STAND AND DELIVER!", (parent.SCREEN_WIDTH/2) - (parent.textWidth("STAND AND DELIVER!")/2) + 2, (parent.SCREEN_HEIGHT/2) - 200 +2);
        parent.fill(255,193,7);
        parent.text("STAND AND DELIVER!", (parent.SCREEN_WIDTH/2) - (parent.textWidth("STAND AND DELIVER!")/2), (parent.SCREEN_HEIGHT/2) - 200);
        parent.textSize(25);
        parent.text("Discard " + resourceDiscard + " resources to continue", (parent.SCREEN_WIDTH/2) -
                (parent.textWidth("Discard 4 resources to continue")/2), (parent.SCREEN_HEIGHT/2) - 175);
        displayResourceArea();
        if(resourceDiscard == 0){
            displayContinue();
        }
    }


    public void displayResourceArea(){
        int comboWidth = 115; //10 spacing, 35 buttons, 70 icon
        int startX = (parent.SCREEN_WIDTH/2) - (575/2);
        int startY = (parent.SCREEN_HEIGHT/2) - 35;
        int runningTotal = 0;
        for(int i = 0; i < 5; i++){
            int resourceCount = parent.model.getPlayer().getGrain();
            if(i == 1){
                resourceCount = parent.model.getPlayer().getOre();
            }
            if(i == 2){
                resourceCount = parent.model.getPlayer().getWool();
            }
            if(i == 3){
                resourceCount = parent.model.getPlayer().getBrick();
            }
            if(i == 4){
                resourceCount = parent.model.getPlayer().getLogs();
            }
            parent.stroke(0,0,0,0);
            parent.fill(161,24,24);

            if(resourceCount == 0){
                //minus
                parent.image(discardImages[12],startX + 5, startY + 35);
            } else {
                //minus
                parent.image(discardImages[10],startX + 5, startY + 35);
            }
            if(discardPile.get(i + 1) == 0) {

                //plus
                parent.image(discardImages[13], startX + 5, startY);
            } else {
                //plus
                parent.image(discardImages[11], startX + 5, startY);
            }
            //resourceIcon
            parent.fill(161,24,24);
            parent.rect(startX + 40, startY, 70,70,5,5,5,5);
            if(resourceCount == 0 && discardPile.get(i+1) == 0){
                parent.image(discardImages[i+5], startX + 40, startY);
            } else {
                parent.image(discardImages[i], startX + 40, startY);
            }
            //resource count
            parent.fill(78,52,46);
            parent.stroke(0,0,0,30);
            parent.rect(startX +5 , startY + 75, 105,30,2,2,2,2);
            parent.fill(255);
            parent.textSize(18);
            parent.textFont(parent.fonts[0]);
            parent.text(resourceCount, startX + 57 - (parent.textWidth(Integer.toString(resourceCount))/2), startY + 95);
            //increment startX
            startX = startX + comboWidth;
        }

    }

    /**
     * Calculates the number of resources that a player has to discard inorder to
     * comply with the robber rules (if you have more than 7 resources, discard half
     * rounding down.
     */
    public void calculateDiscardAmount(){
        int amount = parent.model.getPlayer().getResourceCount();
        this.resourceDiscard = amount/2;
    }

    /**
     * Displays the continue button
     * only when
     */
    private void displayContinue(){
        parent.stroke(0,0,0,0);
        parent.textFont(parent.fonts[1]);
        parent.textSize(40);
        int width = (int)parent.textWidth("Continue") + 20;
        int startX = (parent.SCREEN_WIDTH/2) - (width/2);
        parent.fill(161, 24, 24);
        parent.rect(startX, ((parent.SCREEN_HEIGHT/4) * 3), width,70,4,4,4,4);
        parent.fill(0,0,0,30);
        parent.rect(startX+2, ((parent.SCREEN_HEIGHT/4) * 3)+2, width,70,4,4,4,4);
        parent.fill(255,193,7);
        parent.text("Continue", startX + 10, ((parent.SCREEN_HEIGHT/4) * 3) + 50 );
    }




    public void checkButtons(){
        int plusStartX = (parent.SCREEN_WIDTH/2) - (575/2) + 5;
        int plusStartY = (parent.SCREEN_HEIGHT/2) - 35;
        if(resourceDiscard == 0){
            //continue button.
            int width = (int)parent.textWidth("Continue") + 20;
            int startX = (parent.SCREEN_WIDTH/2) - (width/2);
            if(Listeners.overRect(startX, ((parent.SCREEN_HEIGHT/4) * 3), width,70,parent)){
                resetDiscardPile();
                parent.model.setDisplayMode(0);
                parent.model.setAlert(ObjectParser.generateAlert(parent.model,"discard"));
                parent.model.setAlertReady(true);
                //if its my turn
                if(parent.model.getPlayer().getId() == parent.model.getPlayerTurn()){
                    //give me control of the robber
                    parent.model.getMenus().getRobDialogue().setToolSwitch();
                }
                //if it's not my turn. Check to see if I've been flagged to be robbed.
                if(parent.model.getPlayer().isStealFlag()){
                    PlayerInfo player = parent.model.getPlayer();
                    player.setStealFlag(false);
                    parent.model.setStealManifest(ObjectParser.parseSteal(parent.model,
                            player.getStealFromID(), parent.model.getRobberTile(), false));
                }
            }
        }
        //plus grain
        if(Listeners.overRect(plusStartX,plusStartY,35,35,parent)){
            if(discardPile.get(1) > 0){
                parent.model.getPlayer().addGrain(1);
                discardPile.put(1,discardPile.get(1) - 1);
                resourceDiscard +=1;
            }

        }
        //minus grain
        if(Listeners.overRect(plusStartX,plusStartY+35,35,35,parent)){
            if(resourceDiscard > 0) {
                if (parent.model.getPlayer().getGrain() > 0) {
                    parent.model.getPlayer().subtractGrain(1);
                    discardPile.put(1, discardPile.get(1) + 1);
                    resourceDiscard -= 1;
                }
            }

        }
        int runningTotal = plusStartX + 115;
        //plus ore
        if(Listeners.overRect(runningTotal,plusStartY,35,35,parent)){
            if(discardPile.get(2) > 0){
                parent.model.getPlayer().addOre(1);
                discardPile.put(2,discardPile.get(2) - 1);
                resourceDiscard +=1;
            }

        }
        //minus ore
        if(Listeners.overRect(runningTotal,plusStartY+35,35,35,parent)){
            if(resourceDiscard > 0) {
                if (resourceDiscard > 0) {
                    if (parent.model.getPlayer().getOre() > 0) {
                        parent.model.getPlayer().subtractOre(1);
                        discardPile.put(2, discardPile.get(2) + 1);
                        resourceDiscard -= 1;
                    }
                }
            }

        }
        runningTotal+=115;
        //plus wool
        if(Listeners.overRect(runningTotal,plusStartY,35,35,parent)){
            if(discardPile.get(3) > 0){
                parent.model.getPlayer().addWool(1);
                discardPile.put(3,discardPile.get(3) - 1);
                resourceDiscard +=1;
            }

        }
        //minus wool
        if(Listeners.overRect(runningTotal,plusStartY+35,35,35,parent)){
            if(resourceDiscard > 0) {
                if (parent.model.getPlayer().getWool() > 0) {
                    parent.model.getPlayer().subtractWool(1);
                    discardPile.put(3, discardPile.get(3) + 1);
                    resourceDiscard -= 1;
                }
            }

        }
        runningTotal +=115;
        //plus brick
        if(Listeners.overRect(runningTotal,plusStartY,35,35,parent)){
            if(discardPile.get(4) > 0){
                parent.model.getPlayer().addBrick(1);
                discardPile.put(4,discardPile.get(4) - 1);
                resourceDiscard +=1;
            }

        }
        //minus brick
        if(Listeners.overRect(runningTotal,plusStartY+35,35,35,parent)){
            if(resourceDiscard > 0) {
                if (parent.model.getPlayer().getBrick() > 0) {
                    parent.model.getPlayer().subtractBrick(1);
                    discardPile.put(4, discardPile.get(4) + 1);
                    resourceDiscard -= 1;
                }
            }

        }
        runningTotal +=115;

        //plus logs
        if(Listeners.overRect(runningTotal,plusStartY,35,35,parent)){
            if(discardPile.get(5) > 0){
                parent.model.getPlayer().addLogs(1);
                discardPile.put(5,discardPile.get(5) - 1);
                resourceDiscard +=1;
            }

        }
        //minus logs
        if(Listeners.overRect(runningTotal,plusStartY+35,35,35,parent)){
            if(resourceDiscard > 0) {
                if (parent.model.getPlayer().getLogs() > 0) {
                    parent.model.getPlayer().subtractLogs(1);
                    discardPile.put(5, discardPile.get(5) + 1);
                    resourceDiscard -= 1;
                }
            }

        }


    }

    public int getResourceDiscard() {
        return resourceDiscard;
    }

    public void setResourceDiscard(int resourceDiscard) {
        this.resourceDiscard = resourceDiscard;
    }

    /**
     * Resets the discard pile so all resources are zero
     */
    public void resetDiscardPile(){
        discardPile.put(1,0);
        discardPile.put(2,0);
        discardPile.put(3,0);
        discardPile.put(4,0);
        discardPile.put(5,0);
    }

    public HashMap<Integer, Integer> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(HashMap<Integer, Integer> discardPile) {
        this.discardPile = discardPile;
    }
}
