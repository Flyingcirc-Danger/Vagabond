package Prototype5;

import processing.core.PImage;

import java.util.HashMap;
import java.util.IdentityHashMap;

/**
 * A class containing all the information
 * for displaying the trade floor
 * Created by Tom_Bryant on 7/21/15.
 */
public class TradeFloor {

    private Board parent;
    private PlayerTradeCard[] trades;
    private PlayerTradeCard client;

    public TradeFloor(Board parent){
        this.parent = parent;
        this.trades = new PlayerTradeCard[5];
        initCards();
        trades[1].setWants(new HashMap<Integer,Integer>());
        trades[3].setOffers(new HashMap<Integer, Integer>());
        initClient();


    }


    public void display(){
        parent.background(121, 85, 72);
        for(int i = 0; i < trades.length; i++){
            trades[i].displayCard();
        }
        //client.displayCard();
        displayButtons();
        displayClientFloor();

    }

    /**
     * Initializes the other players cards (not the current client)
     */
    public void initCards(){
        int spacer = (parent.SCREEN_WIDTH - (trades.length * 150)) / (trades.length + 1);
        int runningStartX = 0 + spacer;
        for(int i = 0; i < trades.length; i++){
            trades[i] = new PlayerTradeCard(parent,i, runningStartX,
                    (parent.SCREEN_HEIGHT/4) - (280/2) - 40);
            runningStartX = runningStartX + (150 + spacer);
        }

    }


    /**
     * Initializes the clients cards
     */
    public void initClient(){
        this.client = new PlayerTradeCard(parent, 0, (parent.SCREEN_WIDTH/2) - (150 /2),
                ((parent.SCREEN_HEIGHT/4) * 3) - (280/2));
    }

    /**
     * Displays the counter and accept buttons.
     * Accept buttons are only displayed when there are
     * resources in both the offer and want side of the deal.
     */
    public void displayButtons(){
        for(PlayerTradeCard trade : trades){
            parent.textSize(12);
                int acceptX = trade.getStartX() + (160 / 2) - (5 + (int) parent.textWidth(" Counter "));
                int counterX = trade.getStartX() + (160 / 2) + 5;
                int acceptY = trade.getStartY() + 290;
                if(trade.getWants().size() == 0 || trade.getOffers().size() == 0 ){
                    counterX = trade.getStartX() + (160/2) - (int)(parent.textWidth(" Counter ")/2);
                    parent.fill(40, 53, 157);
                    parent.rect(counterX, acceptY, parent.textWidth(" Counter "), 30, 5, 5, 5, 5);
                    parent.fill(0,0,0,30);
                    parent.rect(counterX+2, acceptY+2, parent.textWidth(" Counter "), 30, 5, 5, 5, 5);
                    parent.fill(255);
                    parent.text(" Counter ", counterX, acceptY + (30/2) + 4 );
                } else {
                    parent.fill(85, 139, 47);
                    parent.rect(acceptX, acceptY, parent.textWidth(" Counter "), 30, 5, 5, 5, 5);
                    parent.fill(0,0,0,30);
                    parent.rect(acceptX+2, acceptY+2, parent.textWidth(" Counter "), 30, 5, 5, 5, 5);
                    parent.fill(255);
                    parent.text(" Accept ", acceptX + (parent.textWidth(" Counter ")/2) - (parent.textWidth(" Accept ")/2), acceptY + (30/2) + 4 );
                    parent.fill(40, 53, 157);
                    parent.rect(counterX, acceptY, parent.textWidth(" Counter "), 30, 5, 5, 5, 5);
                    parent.fill(0,0,0,30);
                    parent.rect(counterX+2, acceptY+2, parent.textWidth(" Counter "), 30, 5, 5, 5, 5);
                    parent.fill(255);
                    parent.text(" Counter ", counterX, acceptY + (30/2) + 4 );

            }
        }
    }

    /**
     * Displays the client trading menu.
     * Outgoing and Incoming boxes.
     */
    public void displayClientFloor(){
        //offer

        parent.fill(40, 53, 157);
        int offerStartX = (parent.SCREEN_WIDTH /4) - 200;
        int startY = ((parent.SCREEN_HEIGHT/4) *3) - (100) ;
        parent.rect(offerStartX,startY,400,200,10,10,10,10);
        parent.textSize(20);
        parent.fill(255);
        parent.text("Outgoing (Selling)", offerStartX + 200 - (parent.textWidth("Outgoing (Selling)")/2), startY + 25);

        drawOffer(offerStartX, startY);


        //want
        parent.fill(40, 53, 157);
        int wantStartX = ((parent.SCREEN_WIDTH /4)*3) - 200;
        parent.rect(wantStartX,startY,400,200,10,10,10,10);
        parent.textSize(20);
        parent.fill(255);
        parent.text("Incoming (Buying)", wantStartX + 200 - (parent.textWidth("Incoming (Buying)")/2), startY + 25);
        drawWant(wantStartX,startY);


    }

    /**
     * Constructs a counter resource icon
     * @param id the id of this resource
     * @param startX the Xstart of this icon
     * @param startY the Ystart of this icon
     * @param offer true if it's in the offer side of the board.
     */
    public void counterResource(int id,int startX, int startY,boolean offer){
        parent.fill(27,37,110);
        parent.rect(startX, startY, 110,60,5,5,5,5);
        parent.image(parent.images[17], startX+5 , startY + 5);
        parent.image(parent.images[18], startX+5, startY + 35);
        parent.image(parent.images[id+11], startX +30, startY +5 );
        parent.fill(0);
        parent.rect(startX + 80, startY + 20, 20, 20,5,5,5,5);
        parent.fill(255);
        parent.textSize(12);
        if(offer) {
            parent.text(client.getOffers().get(id), startX + 80 + 7, startY + 30 + 6);
        } else{
            parent.text(client.getWants().get(id), startX + 80 + 7, startY + 30 + 6);
        }

    }

    /**
     * Draws all of the offer icons.
     * @param offerStartX
     * @param offerStartY
     */
    public void drawOffer(int offerStartX,int offerStartY){
        int startY = offerStartY + 100 - (130/2);
        int startX = offerStartX + 200 - (350/2);
        counterResource(1, startX, startY + 10,true);
        counterResource(2,startX + 120, startY + 10,true);
        counterResource(3,startX + 240, startY + 10,true);
        int startXR2 = offerStartX + 200 - (240/2);
        counterResource(4,startXR2, startY + 80,true);
        counterResource(5,startXR2 + 120, startY + 80,true);

    }

    /**
     * Draws all of the want icons
     * @param wantStartX
     * @param wantStartY
     */
    public void drawWant(int wantStartX,int wantStartY){
        int startY = wantStartY + 100 - (130/2);
        int startX = wantStartX + 200 - (350/2);
        counterResource(1,startX, startY + 10,false);
        counterResource(2,startX + 120, startY + 10,false);
        counterResource(3,startX + 240, startY + 10,false);
        int startXR2 = wantStartX + 200 - (240/2);
        counterResource(4,startXR2, startY + 80,false);
        counterResource(5,startXR2 + 120, startY + 80,false);

    }

}
