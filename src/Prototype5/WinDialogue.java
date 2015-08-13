package Prototype5;

import java.util.HashMap;

/**
 * Makes the dialogue that explains who has
 * won and with what points.
 * Created by Tom_Bryant on 8/13/15.
 */
public class WinDialogue {

    private Board parent;
    private Button okayButton;
    private HashMap<String,Integer> winnersScore;
    private boolean winner; //am i the winner or not
    private int id; //id of player who wins


    public WinDialogue(Board parent){
        this.parent = parent;
        setupButtons();
        winnersScore = new HashMap<String,Integer>();
        winnersScore.put("Town", 0);
        winnersScore.put("City",0);
        winnersScore.put("Victory Point Card",0);
        winnersScore.put("Longest Road", 0);
        winnersScore.put("Largest Army",0);
        id = 0;
        winner = false;
    }


    private void setupButtons(){
        int buttonWidth = (int)parent.textWidth("Okay") + 40;
        this.okayButton = new Button(buttonWidth, 15, "Okay",parent);
        okayButton.curveSize = 5;
        okayButton.color = new int[]{198,40,40};
    }




    public void display(){
        parent.stroke(0,0,0,0);
        parent.fill(121, 85, 72);
        parent.textSize(12);
        int width = 0;
        if(!winner) {
            width = ((int) (parent.textWidth("Victory Point Cards : 1")) + 20) * 2 + 50;
        } else{
            width = ((int) (parent.textWidth("Victory Point Cards : 1")) + 20) + 100;
        }
        int height = 260;
        int startX = (parent.SCREEN_WIDTH/2) - (width/2);
        int startY = (parent.SCREEN_HEIGHT/2) - (height/2);
        parent.rect(startX, startY, width,height,10,10,10,10);
        parent.fill(0, 0, 0,30);
        parent.rect(startX+2, startY+2, width,height,10,10,10,10);
        parent.textAlign(parent.CENTER);
        parent.textFont(parent.fonts[0]);
        parent.textSize(30);
        parent.fill(255);
        if(winner){
            parent.image(parent.images[23], parent.SCREEN_WIDTH/2 - parent.textWidth("You Win!")/2 - 50, startY + 10);
            parent.text("You Win!", startX + (width/2), startY + 35);
            parent.image(parent.images[23], parent.SCREEN_WIDTH/2 + parent.textWidth("You Win!")/2 + 20, startY + 10);
        } else{
            parent.image(parent.images[23], parent.SCREEN_WIDTH/2 - (parent.textWidth("Player 1 Wins!")/2) - 50, startY + 10);
            parent.text("Player " + id + " Wins!", startX + (width/2), startY + 35);
            parent.image(parent.images[23], parent.SCREEN_WIDTH/2 + parent.textWidth("Player " + id + " Wins!")/2 + 20, startY + 10);

        }
        if(!winner) {
            displayInsert("winner", startX + 10, startY + 50);
            parent.textSize(12);
            displayInsert("myself", startX + 40 + ((int) (parent.textWidth("Victory Point Cards : 1")) + 20), startY + 50);
        } else{
            parent.textSize(12);
            displayInsert("myself", parent.SCREEN_WIDTH/2 -  ((int) (parent.textWidth("Victory Point Cards : 1")) + 20) /2, startY + 50);

        }
        okayButton.setStartY(startY + 210);
        okayButton.display();
    }


    private void displayInsert(String player, int startX, int startY){
        HashMap<String,Integer> data = new HashMap<String, Integer>();
        if(player.equals("winner")){
            data = winnersScore;
        } else{
            data = parent.model.getVictoryBonus().getVictoryPointMap();
        }
        parent.textSize(12);
        int width = (int)(parent.textWidth("Victory Point Cards : 1")) + 20;
        int height = 110;
        parent.textAlign(parent.CENTER);
        parent.textFont(parent.fonts[0]);
        parent.textSize(18);
        if(player.equals("winner")){
            parent.text("Winner's Score:", startX +(width/2), startY + 25);
        } else{
            parent.text("My Score:", startX +(width/2), startY + 25);
        }
        int runningTotal = 52;
        parent.textAlign(parent.LEFT);
        parent.textFont(parent.fonts[0]);
        parent.textSize(12);
        parent.text("Towns : " + data.get("Town"), startX + 10, startY + runningTotal);
        runningTotal+=22;
        parent.text("Cities : " + data.get("City"), startX + 10, startY + runningTotal);
        runningTotal+=22;
        parent.text("Victory Point Cards : " + data.get("Victory Point Card"), startX + 10, startY + runningTotal);
        runningTotal+=22;
        parent.text("Longest Road : " + data.get("Longest Road"), startX + 10, startY + runningTotal);
        runningTotal+=22;
        parent.text("Largest Army : " + data.get("Largest Army"), startX + 10, startY + runningTotal);
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public HashMap<String, Integer> getWinnersScore() {
        return winnersScore;
    }

    public void setWinnersScore(HashMap<String, Integer> winnersScore) {
        this.winnersScore = winnersScore;
    }

    public void setWinId(int id){
        this.id = id;
    }

    public int getWinId(){
        return this.id;
    }
}
