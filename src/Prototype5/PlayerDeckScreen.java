package Prototype5;


import processing.core.PImage;

import java.util.ArrayList;

/**
 * Created by Tom_Bryant on 8/5/15.
 * A class for displaying the player Deck
 */
public class PlayerDeckScreen {

    private Board parent;
    private BoardData model;
    private boolean emptyDeck;
    private ArrayList<Button> buttons;


    public PlayerDeckScreen(Board parent){
        this.parent = parent;
        this.model = parent.model;
        this.emptyDeck = true;
        this.buttons = new ArrayList<Button>();
        setupButtons();

    }

    /**
     * Builds all of the buttons for this screen
     */
    public void setupButtons(){
        this.buttons = new ArrayList<Button>();
        //purchase button
        int purchaseWidth = Button.widthEstimate("Buy A Card",parent);
        Button purchase = new Button(purchaseWidth, 40,parent);
        purchase.buttonText = "Buy A Card";
        purchase.curveSize = 5;
        buttons.add(purchase);
        //back button
        int backWidth = Button.widthEstimate("Back", parent);
        Button back = new Button(backWidth,40,"Back",parent);
        back.curveSize = 5;
        back.setStartX(20);
        back.setStartY(20);
        back.color = new int[]{198,40,40};
        buttons.add(back);

    }

    public void display() {
        if(emptyDeck) {
            parent.background(0, 188, 212);
            //if(model.getPlayer().getPlayerDeck().size() == 0){
            int cardHeight = (parent.SCREEN_HEIGHT / 2);
            int cardWidth = (int) (cardHeight / (1.4));
            int startX = (parent.SCREEN_WIDTH / 2) - (cardWidth / 2);
            int startY = (parent.SCREEN_HEIGHT / 2) - (cardHeight / 2);
            //no cards text
            parent.fill(255);
            parent.textAlign(parent.CENTER);
            parent.textSize(30);
            parent.text("You Have No Cards", parent.SCREEN_WIDTH / 2, startY - 30);
            //left card
            parent.fill(0, 0, 0, 30);
            parent.rect(startX - (cardWidth / 2) - 10 + 2, startY + 15 + 2, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(26, 35, 126);
            parent.rect(startX - (cardWidth / 2) - 10, startY + 15, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(40, 53, 147);
            parent.rect(startX - (cardWidth / 2) - 3, startY + 22, cardWidth - 14, cardHeight - 14, 15, 15, 15, 15);
            parent.image(parent.model.getMenus().getDevDeck().getDevImages()[5],
                    startX - 10 - 100, startY + 22 + (cardHeight / 2) - 100);
            //right card
            parent.fill(0, 0, 0, 30);
            parent.rect(startX + (cardWidth / 2) + 10 + 2, startY + 15 + 2, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(26, 35, 126);
            parent.rect(startX + (cardWidth / 2) + 10, startY + 15, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(40, 53, 147);
            parent.rect(startX + (cardWidth / 2) + 17, startY + 22, cardWidth - 14, cardHeight - 14, 15, 15, 15, 15);
            parent.image(parent.model.getMenus().getDevDeck().getDevImages()[5],
                    startX + cardWidth - 90, startY + 22 + (cardHeight / 2) - 100);
            //center card
            parent.fill(0, 0, 0, 30);
            parent.rect(startX + 2, startY - 15 +2, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(26, 35, 126);
            parent.rect(startX , startY - 15, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(40, 53, 147);
            parent.rect(startX + 7, startY -15 +7, cardWidth - 14, cardHeight - 14, 15, 15, 15, 15);
            parent.image(parent.model.getMenus().getDevDeck().getDevImages()[5],
                    startX + cardWidth/2 - 100, startY -15 + (cardHeight / 2) - 100);
            //purchase new card text
            parent.fill(255);
            parent.textAlign(parent.CENTER);
            parent.textSize(20);
            parent.text("Development Cards cost: ", parent.SCREEN_WIDTH / 2, startY + cardHeight + 40);
            parent.textSize(15);
            parent.textAlign(parent.LEFT);
            int resourceWidth = 45 + (int)parent.textWidth("1 x Wheat");
            int resourceStartX = (parent.SCREEN_WIDTH/2) - ((resourceWidth * 3)/2);
            int resourceStartY = startY + cardHeight + 50;
            parent.image(parent.images[1],resourceStartX + 5, resourceStartY);
            parent.text("1x Wheat", resourceStartX + 35, resourceStartY + 20);
            int runningTotal = resourceStartX + resourceWidth;
            parent.image(parent.images[3],runningTotal + 5, resourceStartY);
            parent.text("1x Wool", runningTotal + 35, resourceStartY + 20);
            runningTotal = runningTotal + resourceWidth;
            parent.image(parent.images[2],runningTotal + 5, resourceStartY);
            parent.text("1x Ore", runningTotal + 35, resourceStartY + 20);
            //purchase new card button
            buttons.get(0).start.y = resourceStartY + 40;
            buttons.get(0).textStart.y = resourceStartY + 58;
            buttons.get(0).display();
            buttons.get(1).display();

//            parent.textSize(20);
//            parent.textAlign(parent.CENTER,parent.CENTER);
//            int purchaseWidth = 40 + (int)parent.textWidth("Buy A Card");
//            parent.fill(255, 167, 38);
//            parent.rect((parent.SCREEN_WIDTH/2) - (purchaseWidth/2), resourceStartY + 40,purchaseWidth,40,5,5,5,5);
//            parent.fill(0, 0, 0, 30);
//            parent.rect((parent.SCREEN_WIDTH/2) - (purchaseWidth/2)+2, resourceStartY + 42,purchaseWidth,40,5,5,5,5);
//            parent.fill(255);
//            parent.text("Buy A Card", parent.SCREEN_WIDTH/2, resourceStartY + 58);



        }


       // model.getMenus().getDeck().get(0).display();
    }

    public void checkButtons(){
        for(int i = 0; i < buttons.size(); i++){
            if(buttons.get(i).checkButton()){
                System.out.println("Button Pressed: " + i);
            }
        }
    }
}
