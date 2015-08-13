package Prototype5;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tom_Bryant on 8/11/15.
 * Contains information pertaining to the two special
 * victory bonus points (largest army and longest road)
 */
public class VictoryBonus {

    private int armyID;
    private int armySize;
    private int roadID;
    private int roadSize;
    private boolean roadVisible;
    private boolean armyVisible;
    private Board parent;
    private ArrayList<Button> buttons;
    private HashMap<String, Integer> victoryPointMap;


    /**
     * Basic constructor, everything starts off at 0
     */
    public VictoryBonus(Board parent){
        this.armyID = 0;
        this.armySize = 0;
        this.roadID = 0;
        this.roadSize = 0;
        this.armyVisible = false;
        this.roadVisible = false;
        this.parent = parent;
        this.victoryPointMap = new HashMap<String,Integer>();
        victoryPointMap.put("Town", 0);
        victoryPointMap.put("City",0);
        victoryPointMap.put("Victory Point Card",0);
        victoryPointMap.put("Longest Road", 0);
        victoryPointMap.put("Largest Army",0);
    }

    private void initButtons(){
        buttons = new ArrayList<Button>();
        int okayWidth = Button.widthEstimate("Okay", parent);
        Button okay = new Button(okayWidth, 40, "Okay", parent);
        okay.color = new int[]{198, 40, 40};
        okay.curveSize = 5;
        buttons.add(okay);
    }

    public int getArmyID() {
        return armyID;
    }

    public void setArmyID(int armyID) {
        this.armyID = armyID;
    }

    public int getArmySize() {
        return armySize;
    }

    public void setArmySize(int armySize) {
        this.armySize = armySize;
    }

    public int getRoadID() {
        return roadID;
    }

    public void setRoadID(int roadID) {
        this.roadID = roadID;
    }

    public int getRoadSize() {
        return roadSize;
    }

    public void setRoadSize(int roadSize) {
        this.roadSize = roadSize;
    }

    public boolean isRoadVisible() {
        return roadVisible;
    }

    public void setRoadVisible(boolean roadVisible) {
        this.roadVisible = roadVisible;
    }

    public boolean isArmyVisible() {
        return armyVisible;
    }

    public void setArmyVisible(boolean armyVisible) {
        this.armyVisible = armyVisible;
    }

    /**
     * Checks to see if the road built is the longest road
     * @param size
     * @return
     */
    public boolean checkLongestRoad(int size){
        if(size > roadSize){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks to see if you have the largest army
     * @param size
     * @return
     */
    public boolean checkLargestArmy(int size){
        if(size > armySize){
            return true;
        }
        return false;
    }

    public void display() {
        if(buttons == null){
            initButtons();
        }
        if (roadVisible) {
            int cardWidth = 350;
            int cardHeight = (int) (1.4 * cardWidth);
            int startX = (parent.SCREEN_WIDTH / 2) - (cardWidth / 2);
            int startY = (parent.SCREEN_HEIGHT / 2) - (cardHeight / 2);
            //main card rectangle
            parent.fill(0, 0, 0, 30);
            parent.rect(startX + 2, startY + 2, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(227, 226, 213);
            parent.rect(startX, startY, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(62, 39, 35);
            parent.rect(startX + 7, startY + 7, cardWidth - 14, cardHeight - 14, 15, 15, 15, 15);
            //Icon
            int boxHeight = ((cardHeight - 14) / 2) - 20;
            int boxStartY = startY + 17;
            int boxStartX = startX + 17;
            int boxWidth = ((cardWidth - 14)) - 20;
            parent.fill(198, 40, 40);
            parent.rect(boxStartX, boxStartY, boxWidth, boxHeight, 10, 10, 10, 10);
            parent.image(parent.loadImage("assets/longestRoad.png"), startX + (cardWidth / 2) - 100, startY + 17 + (boxHeight / 2) - 100);
            //text
            parent.textAlign(parent.CENTER);
            parent.textSize(30);
            parent.fill(255);
            parent.textFont(parent.fonts[1]);
            parent.text("The Longest Road", parent.SCREEN_WIDTH / 2, startY + (cardHeight / 2) + 30);
            int textHeight = startY + cardHeight - (startY + (cardHeight / 2) + 40);
            parent.textSize(12);
            parent.textAlign(parent.CENTER, parent.CENTER);
            parent.textFont(parent.fonts[0]);
            parent.textSize(12);
            parent.text("2 Victory Points! The first player to build a road of 5 unbroken segments gets this card. Another player who builds a longer road takes control of this card", startX + 27, startY + (cardHeight / 2) + 40, cardWidth - 44, textHeight - 20);
            buttons.get(0).setStartY(startY + cardHeight + 20);
            buttons.get(0).display();
            checkButtons();
            parent.textAlign(parent.LEFT, parent.BOTTOM);
        }
        if (armyVisible) {
            int cardWidth = 350;
            int cardHeight = (int) (1.4 * cardWidth);
            int startX = (parent.SCREEN_WIDTH / 2) - (cardWidth / 2);
            int startY = (parent.SCREEN_HEIGHT / 2) - (cardHeight / 2);
            //main card rectangle
            parent.fill(0, 0, 0, 30);
            parent.rect(startX + 2, startY + 2, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(227, 226, 213);
            parent.rect(startX, startY, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(62, 39, 35);
            parent.rect(startX + 7, startY + 7, cardWidth - 14, cardHeight - 14, 15, 15, 15, 15);
            //Icon
            int boxHeight = ((cardHeight - 14) / 2) - 20;
            int boxStartY = startY + 17;
            int boxStartX = startX + 17;
            int boxWidth = ((cardWidth - 14)) - 20;
            parent.fill(198, 40, 40);
            parent.rect(boxStartX, boxStartY, boxWidth, boxHeight, 10, 10, 10, 10);
            parent.image(parent.loadImage("assets/largestArmy.png"), startX + (cardWidth / 2) - 100, startY + 17 + (boxHeight / 2) - 100);
            //text
            parent.textAlign(parent.CENTER);
            parent.textSize(30);
            parent.fill(255);
            parent.textFont(parent.fonts[1]);
            parent.text("The Largest Army", parent.SCREEN_WIDTH / 2, startY + (cardHeight / 2) + 30);
            int textHeight = startY + cardHeight - (startY + (cardHeight / 2) + 40);
            parent.textSize(12);
            parent.textAlign(parent.CENTER, parent.CENTER);
            parent.textFont(parent.fonts[0]);
            parent.textSize(12);
            parent.text("2 Victory Points! The first player to use three knights has the largest army. Another player who plays more knights takes control of this card", startX + 27, startY + (cardHeight / 2) + 40, cardWidth - 44, textHeight - 20);
            buttons.get(0).setStartY(startY + cardHeight + 20);
            buttons.get(0).display();
            checkButtons();
            parent.textAlign(parent.LEFT, parent.BOTTOM);
        }
    }

    public void checkButtons(){
        if(parent.mousePressed) {
            if (buttons.get(0).checkButton()) {
                this.roadVisible = false;
                this.armyVisible = false;
            }
        }
    }

    public String generateBonusManifest(String type){
        StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        result.append("<victorybonus>");
        if(type.equals("road")) {
            result.append("<id>" + roadID + "</id>");
            result.append("<size>" + roadSize + "</size>");
        }
        if(type.equals("knight")){
            result.append("<id>" + armyID + "</id>");
            result.append("<size>" + armySize + "</size>");
        }
        result.append("<from>" + parent.model.getPlayer().getId() + "</from>");
        result.append("<type>" + type + "</type>");
        result.append("</victorybonus>");
        return result.toString();

    }


    public HashMap<String, Integer> getVictoryPointMap() {
        return victoryPointMap;
    }

    public void setVictoryPointMap(HashMap<String, Integer> victoryPointMap) {
        this.victoryPointMap = victoryPointMap;
    }
}
