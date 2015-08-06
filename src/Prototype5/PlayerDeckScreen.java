package Prototype5;


import Prototype4.*;
import processing.core.PImage;

import java.util.ArrayList;


/**
 * Created by Tom_Bryant on 8/5/15.
 * A class for displaying the player Deck
 */
public class PlayerDeckScreen {

    private Board parent;
    private ArrayList<Button> buttons;
    private boolean open;
    private ArrayList<DevelopmentCard> playerDeck;
    private int selectionIndex;
    private String playerCard;


    public PlayerDeckScreen(Board parent, PlayerInfo player) {
        this.parent = parent;
        this.buttons = new ArrayList<Button>();
        this.playerDeck = player.getPlayerDeck();
        this.selectionIndex = 0;
        this.playerCard = new String();
        setupButtons();

    }

    /**
     * Builds all of the buttons for this screen
     */
    public void setupButtons() {
        this.buttons = new ArrayList<Button>();
        //purchase button
        int purchaseWidth = Button.widthEstimate("Buy A Card", parent);
        Button purchase = new Button(purchaseWidth, 40, parent);
        purchase.buttonText = "Buy A Card";
        purchase.curveSize = 5;
        buttons.add(purchase);
        //back button
        int backWidth = Button.widthEstimate("Back", parent);
        Button back = new Button(backWidth, 40, "Back", parent);
        back.curveSize = 5;
        back.setStartX(20);
        back.setStartY(20);
        back.color = new int[]{198, 40, 40};
        buttons.add(back);
        //DeckButton
        Button deckButton = new Button(parent.loadImage("assets/developmentCards/cardBack.png"), parent);
        deckButton.setStartX((parent.SCREEN_WIDTH) - 10 - deckButton.width);
        deckButton.setStartY(60);
        buttons.add(deckButton);

    }

    public void display() {

        if (isOpen()) {
            parent.background(0, 188, 212);
            int cardHeight = (parent.SCREEN_HEIGHT / 2);
            int cardWidth = (int) (cardHeight / (1.4));
            int startX = (parent.SCREEN_WIDTH / 2) - (cardWidth / 2);
            int startY = (parent.SCREEN_HEIGHT / 2) - (cardHeight / 2);
            if(selectionIndex - 1 > -1){
                playerDeck.get(selectionIndex - 1).displayBackground(true);
            } else if(selectionIndex - 1 == -1 && playerDeck.size() >2){
                if(playerDeck.size() > 2){
                    playerDeck.get(playerDeck.size() - 1).displayBackground(true);
                }
            }
           else {
                //left card
                parent.fill(0, 0, 0, 30);
                parent.rect(startX - (cardWidth / 2) - 10 + 2, startY + 15 + 2, cardWidth, cardHeight, 15, 15, 15, 15);
                parent.fill(26, 35, 126);
                parent.rect(startX - (cardWidth / 2) - 10, startY + 15, cardWidth, cardHeight, 15, 15, 15, 15);
                parent.fill(40, 53, 147);
                parent.rect(startX - (cardWidth / 2) - 3, startY + 22, cardWidth - 14, cardHeight - 14, 15, 15, 15, 15);
                parent.image(parent.model.getMenus().getDevDeck().getDevImages()[5],
                        startX - 10 - 100, startY + 22 + (cardHeight / 2) - 100);
            }
            if(selectionIndex + 1 < playerDeck.size()){
                playerDeck.get(selectionIndex + 1).displayBackground(false);
            } else if(selectionIndex + 1 == playerDeck.size() && playerDeck.size() > 2){
                if(playerDeck.size() > 2) {
                    playerDeck.get(0).displayBackground(false);
                }
            } else {
                //right card
                parent.fill(0, 0, 0, 30);
                parent.rect(startX + (cardWidth / 2) + 10 + 2, startY + 15 + 2, cardWidth, cardHeight, 15, 15, 15, 15);
                parent.fill(26, 35, 126);
                parent.rect(startX + (cardWidth / 2) + 10, startY + 15, cardWidth, cardHeight, 15, 15, 15, 15);
                parent.fill(40, 53, 147);
                parent.rect(startX + (cardWidth / 2) + 17, startY + 22, cardWidth - 14, cardHeight - 14, 15, 15, 15, 15);
                parent.image(parent.model.getMenus().getDevDeck().getDevImages()[5],
                        startX + cardWidth - 90, startY + 22 + (cardHeight / 2) - 100);
            }
            if (playerDeck.size() == 0) {
                //no cards text
                parent.fill(255);
                parent.textAlign(parent.CENTER);
                parent.textSize(30);
                parent.text("You Have No Cards", parent.SCREEN_WIDTH / 2, startY - 30);

                //center card
                parent.fill(0, 0, 0, 30);
                parent.rect(startX + 2, startY - 15 + 2, cardWidth, cardHeight, 15, 15, 15, 15);
                parent.fill(26, 35, 126);
                parent.rect(startX, startY - 15, cardWidth, cardHeight, 15, 15, 15, 15);
                parent.fill(40, 53, 147);
                parent.rect(startX + 7, startY - 15 + 7, cardWidth - 14, cardHeight - 14, 15, 15, 15, 15);
                parent.image(parent.model.getMenus().getDevDeck().getDevImages()[5],
                        startX + cardWidth / 2 - 100, startY - 15 + (cardHeight / 2) - 100);
                //purchase new card text
                parent.fill(255);
                parent.textAlign(parent.CENTER);
                parent.textSize(20);
                parent.text("Development Cards cost: ", parent.SCREEN_WIDTH / 2, startY + cardHeight + 40);
                parent.textSize(15);
                parent.textAlign(parent.LEFT);
                int resourceWidth = 45 + (int) parent.textWidth("1 x Wheat");
                int resourceStartX = (parent.SCREEN_WIDTH / 2) - ((resourceWidth * 3) / 2);
                int resourceStartY = startY + cardHeight + 50;
                parent.image(parent.images[1], resourceStartX + 5, resourceStartY);
                parent.text("1x Wheat", resourceStartX + 35, resourceStartY + 20);
                int runningTotal = resourceStartX + resourceWidth;
                parent.image(parent.images[3], runningTotal + 5, resourceStartY);
                parent.text("1x Wool", runningTotal + 35, resourceStartY + 20);
                runningTotal = runningTotal + resourceWidth;
                parent.image(parent.images[2], runningTotal + 5, resourceStartY);
                parent.text("1x Ore", runningTotal + 35, resourceStartY + 20);
                //purchase new card button
                buttons.get(0).start.y = resourceStartY + 40;
                buttons.get(0).textStart.y = resourceStartY + 58;
                buttons.get(0).display();
                buttons.get(1).display();
            } else {
                playerDeck.get(selectionIndex).display();
                StringBuffer text = new StringBuffer();
            }
        } else {
            if(playerCard.length() > 0){
                DevelopmentCard.displayMedium(playerCard,parent);
            }
            buttons.get(2).display();
        }

    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void checkButtons() {
        if (isOpen()) {
            //current card button
            if (playerDeck.size() > 0) {
                int buttonPress = playerDeck.get(selectionIndex).checkButtons();
                //back button
                if(buttonPress == 3){
                    open = false;
                }
                //play card button
                if(buttonPress == 4){
                    parent.model.setCardManifest(ObjectParser.parseCard(parent.model, playerDeck.get(selectionIndex)));
                }
                if(playerDeck.size() > 1) {
                    if (buttonPress == 1) {
                        //next
                        if (selectionIndex < playerDeck.size() - 1) {
                            selectionIndex++;
                        } else {
                            selectionIndex = 0;
                        }
                    }
                    if (buttonPress == 2) {
                        //previous
                        if (selectionIndex > 0) {
                            selectionIndex--;
                        } else {
                            selectionIndex = playerDeck.size() - 1;
                        }
                    }
                }
            } else {


                //purchase card
                if (buttons.get(0).checkButton()) {
                    parent.model.getMenus().getDevDeck().getCard();
                }
                //back button
                if (buttons.get(1).checkButton()) {
                    open = false;
                }

            }
        } else {
            //deck button
            if (buttons.get(2).checkButton()) {
                open = true;
            }
        }
    }


    /**
     * Remove this card from the players deck
     * Will result in garbage collection
     */
    public void removeFromPlayerDeck(){
        DevelopmentCard toRemove = playerDeck.get(selectionIndex);
        playerDeck.remove(selectionIndex);
        toRemove.setInPlayerDeck(false);
    }

    public int getSelectionIndex() {
        return selectionIndex;
    }

    public void incSelectionIndex(){
        selectionIndex++;
        if(selectionIndex == playerDeck.size()){
            selectionIndex = 0;
        }
    }

    public void setSelectionIndex(int selectionIndex) {
        this.selectionIndex = selectionIndex;
    }

    public String getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(String playerCard) {
        this.playerCard = playerCard;
    }
}


