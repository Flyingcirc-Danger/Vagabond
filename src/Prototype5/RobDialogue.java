package Prototype5;



import java.util.ArrayList;

/**
 * A dialogue for the robber
 * Created by Tom_Bryant on 7/31/15.
 */
public class RobDialogue {

    private Board parent;
    private boolean robConfirm;
    private ArrayList<Integer> targets;


    public RobDialogue(Board parent){
        this.parent = parent;
        this.targets = new ArrayList<Integer>();
        targets.add(1);
        targets.add(2);
        targets.add(3);
        robConfirm = true;
    }


    /**
     * Given a hex tile, this method
     * takes the owners and populates the "targets"
     * arraylist, for possible targets to steal from.
     * @param robberTile the tile that the robber currently sits.
     */
    public void setTargets(HexTile robberTile){
        this.targets = new ArrayList<Integer>();
        for(int value : robberTile.getOwners().keySet()){
            if(value != parent.model.getPlayer().getId()){
                targets.add(value);
            }
        }
    }


    public void display() {
        if (robConfirm) {
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
                parent.rect(startX + width - buttonWidth - 20, startY + height - 50, buttonWidth, 40,5,5,5,5);
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
                parent.fill(0, 0, 0,30);

                parent.fill(255);

                parent.text("Cancel", startX + (width/2)  - (parent.textWidth("Cancel")/2), startY + runningTotal + 27);
                parent.fill(0,0,0,30);
                parent.rect(startX + (width/2) - (selectWidth/2)+2, startY + runningTotal+2, selectWidth, 40,5,5,5,5);
            }
        }
    }
}
