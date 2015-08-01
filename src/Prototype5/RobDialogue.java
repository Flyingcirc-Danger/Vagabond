package Prototype5;



import java.util.ArrayList;

/**
 * A dialogue for the robber
 * Created by Tom_Bryant on 7/31/15.
 */
public class RobDialogue {

    private Board parent;
    private boolean robConfirm;
    private boolean toolSwitch;
    private ArrayList<Integer> targets;
    private int[] okayButton;
    private int[] cancelButton;
    private HexTile robberTile;


    public RobDialogue(Board parent){
        this.parent = parent;
        this.targets = new ArrayList<Integer>();
        robConfirm = false;
        okayButton = new int[4];
        cancelButton = new int[4];
        this.toolSwitch = false;
        this.robberTile = new HexTile();
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
        return robConfirm;
    }

    public void setRobConfirm(boolean robConfirm) {
        this.robConfirm = robConfirm;
    }

    public boolean isToolSwitch() {
        return toolSwitch;
    }

    public void setToolSwitch(boolean toolSwitch) {
        this.toolSwitch = toolSwitch;
    }

    public void display() {
        if (robConfirm) {
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
    }


    public void checkButtons(){
        //if the robber has been placed
        if(robConfirm){
            //player buttons if the target size >= 0;
            if(targets.size() > 0){
                //check cancel button.
                if(Listeners.overRect(cancelButton[0],cancelButton[1],cancelButton[2],cancelButton[3],parent)){
                    this.robConfirm = false;
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
                        parent.model.setRobberTile(robberTile);
                        parent.model.setStealManifest(ObjectParser.parseSteal(parent.model,targets.get(i),robberTile,true));
                        parent.currentTool = 0;
                        parent.model.setDisplayMode(0);
                        robConfirm = false;

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
                    robConfirm = false;
                }
                if(Listeners.overRect(cancelButton[0],cancelButton[1],cancelButton[2],cancelButton[3],parent)){
                    this.robConfirm = false;
                    this.robberTile.setRobber(false);
                }
            }
        }
        if(toolSwitch){
            parent.textSize(20);
            if(Listeners.overRect((parent.SCREEN_WIDTH/2) - (((int)parent.textWidth("Move The Robber!") + 40)/2)
                    + (((int)parent.textWidth("Move The Robber!") + 40)/2) - 50,
                    (parent.SCREEN_HEIGHT/2) - (100/2)+ ((100/4)*3) -25,100,40,parent)){
                parent.currentTool = 7;
                this.toolSwitch = false;
            }

        }

    }

    /**
     * Notifies the player to move the robber
     */
    public void displayRobberNotification(){
        int width = (int)parent.textWidth("Move The Robber!") + 40;
        int height = 100;
        parent.textSize(20);
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

    public HexTile getRobberTile() {
        return robberTile;
    }

    public void setRobberTile(HexTile robberTile) {
        this.robberTile = robberTile;
    }
}


