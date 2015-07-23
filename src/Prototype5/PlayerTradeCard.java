package Prototype5;

import processing.core.PImage;

import java.util.HashMap;

/**
 * Contains the information for a player offer
 * Created by Tom_Bryant on 7/21/15.
 */
public class PlayerTradeCard {

    private int id;
    private int startX;
    private int startY;
    private Board parent;
    private HashMap<Integer,Integer> offers;
    private HashMap<Integer,Integer> wants;
    private boolean mutable;


    public PlayerTradeCard(Board parent, int id,int startX, int startY,HashMap<Integer,Integer> offers, HashMap<Integer,Integer> wants){
        this.parent = parent;
        this.id = id;
        this.offers = offers;
        this.wants = wants;
        this.startX = startX;
        this.startY = startY;

    }

    /**
     * Constructor for situation
     * where no offer or want has been supplied.
     */
    public PlayerTradeCard(Board parent,int id, int startX, int startY){
        this.parent = parent;
        this.id = id;
        this.offers = new HashMap<Integer,Integer>();
        offers.put(1,0);
        offers.put(2,0);
        offers.put(3,0);
        offers.put(4,0);
        offers.put(5,0);

        this.wants = new HashMap<Integer,Integer>();
        wants.put(1,0);
        wants.put(2,0);
        wants.put(3,0);
        wants.put(4,0);
        wants.put(5, 0);
        this.startX = startX;
        this.startY = startY;

    }


    public void displayCard(){
        parent.fill(0,0,0,30);
        parent.stroke(0,0,0,0);
        parent.rect(startX+2, startY+2,160, 280,10,10,10,10);
        parent.fill(227,226,213);
        parent.rect(startX, startY, 160, 280, 10, 10, 10, 10);
        parent.fill(62,39,35);
        parent.rect(startX+7, startY+7, 146, 266, 10, 10, 10, 10);
        int cardX = startX + 7;
        int cardHeight = 246;
        int cardWidth = 146;
        int cardY = startY + 7;
        parent.fill(255);
        parent.textSize(16);
        parent.text("Player " + this.id, cardX + (146/2) - (parent.textWidth("Player " + this.id)/2), cardY + 21);
        parent.textSize(12);
        parent.text("Offers:", cardX + (146/2) - (parent.textWidth("Offers:")/2), cardY + 35);
        parent.fill(198,40,40);
        parent.stroke(148,50,50);
        //offer
        parent.rect(cardX + 10,cardY + 40, cardWidth - 20, 100,5,5,5,5);
        parent.fill(255);
        parent.stroke(0,0,0,0);
        parent.text("Wants:", cardX + (146 / 2) - (parent.textWidth("Wants:") / 2), cardY + 152);
        parent.fill(198,40,40);
        parent.stroke(148,50,50);
        //for
        parent.rect(cardX + 10,cardY + 155, cardWidth - 20, 100,5,5,5,5);
        parent.fill(255);
        parent.stroke(0,0,0,0);
        displayResources();
    }

    /**
     * Displays the Offered and Wanted resources in their boxes
     */
    public void displayResources(){
        int cardX = startX + 7;
        int cardY = startY + 7;
        int resCounter = 1;
        int resWidth = 50 + (int)parent.textWidth("9");
        parent.textSize(10);
        parent.fill(255);
        for(int res : offers.keySet()){
            int count = offers.get(res);
            if(resCounter == 1){
                parent.image(getResourceIcon(res), cardX + (146/2) - resWidth, cardY + 45);
                parent.text("x" + count, cardX + (146/2) - resWidth + 35, cardY + 65);
                resCounter = resCounter + 1;
                continue;

            }
            if(resCounter == 2){
                parent.image(getResourceIcon(res), cardX + (146/2) + 5, cardY + 45);
                parent.text("x" + count, cardX + (146/2) + 40, cardY + 65);
                resCounter = resCounter + 1;
                continue;
            }
            if(resCounter == 3){
                parent.image(getResourceIcon(res), cardX + (146/2) - resWidth, cardY + 75);
                parent.text("x" + count, cardX + (146/2) - resWidth + 35, cardY + 95);
                resCounter = resCounter + 1;
                continue;
            }
            if(resCounter == 4){
                parent.image(getResourceIcon(res), cardX + (146/2) + 5, cardY + 75);
                parent.text("x" + count, cardX + (146/2) + 40, cardY + 95);
                resCounter = resCounter + 1;
                continue;
            }
            if(resCounter == 5){
                parent.image(getResourceIcon(res), cardX + (146/2) - (resWidth/2), cardY + 105);
                parent.text("x" + count, cardX + (146/2) + 5, cardY + 125);
                resCounter = resCounter + 1;
                continue;
            }

        }
        resCounter = 1;
        for(int res : wants.keySet()){
            int count = wants.get(res);
            if(resCounter == 1){
                parent.image(getResourceIcon(res), cardX + (146/2) - resWidth, cardY + 160);
                parent.text("x" + count, cardX + (146/2) - resWidth + 35, cardY + 180);
                resCounter = resCounter + 1;
                continue;

            }
            if(resCounter == 2){
                parent.image(getResourceIcon(res), cardX + (146/2) + 5, cardY + 160);
                parent.text("x" + count, cardX + (146/2) + 40, cardY + 180);
                resCounter = resCounter + 1;
                continue;
            }
            if(resCounter == 3){
                parent.image(getResourceIcon(res), cardX + (146/2) - resWidth, cardY + 190);
                parent.text("x" + count, cardX + (146/2) - resWidth + 35, cardY + 210);
                resCounter = resCounter + 1;
                continue;
            }
            if(resCounter == 4){
                parent.image(getResourceIcon(res), cardX + (146/2) + 5, cardY + 190);
                parent.text("x" + count, cardX + (146/2) + 40, cardY + 210);
                resCounter = resCounter + 1;
                continue;
            }
            if(resCounter == 5){
                parent.image(getResourceIcon(res), cardX + (146/2) - (resWidth/2), cardY + 220);
                parent.text("x" + count, cardX + (146/2) + 5, cardY + 240);
                resCounter = resCounter + 1;
                continue;
            }

        }
    }




    /**
     * Returns the image of the resource
     * determined by the resource id
     * @param id the id of the resource
     * 1 = wheat
     * 2 = ore
     * 3 = wool
     * 4 = brick
     * 5 = logs
     * @return
     */
    public PImage getResourceIcon(int id){
        return parent.images[id];
    }

    public boolean isMutable() {
        return mutable;
    }

    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }

    public HashMap<Integer, Integer> getOffers() {
        return offers;
    }

    public void setOffers(HashMap<Integer, Integer> offers) {
        this.offers = offers;
    }

    public HashMap<Integer, Integer> getWants() {
        return wants;
    }

    public void setWants(HashMap<Integer, Integer> wants) {
        this.wants = wants;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }


    /**
     * Adds 1 wheat resource to this
     * players offer or wants hasmap
     * @param offer true = offer, false = want
     */
    public void addGrain(boolean offer){
        if(offer){
            if(offers.containsKey(1)){
                offers.put(1, offers.get(1) + 1);
            } else{
                offers.put(1,1);
            }
        } else{
            if(wants.containsKey(1)){
                wants.put(1, wants.get(1) + 1);
            } else{
                wants.put(1,1);
            }

        }
    }


    /**
     * Adds 1 ore resource to this
     * players offer or wants hasmap
     * @param offer true = offer, false = want
     */
    public void addOre(boolean offer){
        if(offer){
            if(offers.containsKey(2)){
                offers.put(2, offers.get(2) + 1);
            } else{
                offers.put(2,1);
            }
        } else{
            if(wants.containsKey(2)){
                wants.put(2, wants.get(2) + 1);
            } else{
                wants.put(2,1);
            }

        }
    }


    /**
     * Adds 1 wool resource to this
     * players offer or wants hasmap
     * @param offer true = offer, false = want
     */
    public void addWool(boolean offer){
        if(offer){
            if(offers.containsKey(3)){
                offers.put(3, offers.get(3) + 1);
            } else{
                offers.put(3,1);
            }
        } else{
            if(wants.containsKey(3)){
                wants.put(3, wants.get(3) + 1);
            } else{
                wants.put(3,1);
            }

        }
    }

    /**
     * Adds 1 Brick to this
     * players offer or wants hasmap
     * @param offer true = offer, false = want
     */
    public void addBrick(boolean offer){
        if(offer){
            if(offers.containsKey(4)){
                offers.put(4, offers.get(4) + 1);
            } else{
                offers.put(4,1);
            }
        } else{
            if(wants.containsKey(4)){
                wants.put(4, wants.get(4) + 1);
            } else{
                wants.put(4,1);
            }

        }
    }

    /**
     * Adds 1 log resource to this
     * players offer or wants hashmap
     * @param offer true = offer, false = want
     */
    public void addLog(boolean offer){
        if(offer){
            if(offers.containsKey(5)){
                offers.put(5, offers.get(5) + 1);
            } else{
                offers.put(5,1);
            }
        } else{
            if(wants.containsKey(2)){
                wants.put(5, wants.get(5) + 1);
            } else{
                wants.put(5,1);
            }

        }
    }



    /**
     * Subtracts 1 wheat resource to this
     * players offer or wants hashmap
     * @param offer true = offer, false = want
     */
    public void subGrain(boolean offer){
        if(offer){
            if(offers.containsKey(1)){
                if(offers.get(1) > 0) {
                    offers.put(1, offers.get(1) - 1);
                }
            }
        } else{
            if(wants.containsKey(1)){
                if(wants.get(1) > 0) {
                    wants.put(1, wants.get(1) - 1);
                }
            }

        }
    }

    /**
     * Subtracts 1 wheat resource to this
     * players offer or wants hashmap
     * @param offer true = offer, false = want
     */
    public void subOre(boolean offer){
        if(offer){
            if(offers.containsKey(2)){
                if(offers.get(2) > 0) {
                    offers.put(2, offers.get(2) - 1);
                }
            }
        } else{
            if(wants.containsKey(2)){
                if(wants.get(2) > 0) {
                    wants.put(2, wants.get(2) - 1);
                }
            }

        }
    }

    /**
     * Subtracts 1 wool resource to this
     * players offer or wants hashmap
     * @param offer true = offer, false = want
     */
    public void subWool(boolean offer){
        if(offer){
            if(offers.containsKey(3)){
                if(offers.get(3) > 0) {
                    offers.put(3, offers.get(3) - 1);
                }
            }
        } else{
            if(wants.containsKey(3)){
                if(wants.get(3) > 0) {
                    wants.put(3, wants.get(3) - 1);
                }
            }

        }
    }

    /**
     * Subtracts 1 brick resource to this
     * players offer or wants hashmap
     * @param offer true = offer, false = want
     */
    public void subBrick(boolean offer){
        if(offer){
            if(offers.containsKey(4)){
                if(offers.get(4) > 0) {
                    offers.put(4, offers.get(4) - 1);
                }
            }
        } else{
            if(wants.containsKey(4)){
                if(wants.get(4) > 0) {
                    wants.put(4, wants.get(4) - 1);
                }
            }

        }
    }

    /**
     * Subtracts 1 log resource to this
     * players offer or wants hashmap
     * @param offer true = offer, false = want
     */
    public void subLog(boolean offer){
        if(offer){
            if(offers.containsKey(5)){
                if(offers.get(5) > 0) {
                    offers.put(5, offers.get(5) - 1);
                }
            }
        } else{
            if(wants.containsKey(5)){
                if(wants.get(5) > 0) {
                    wants.put(5, wants.get(5) - 1);
                }
            }

        }
    }
}
