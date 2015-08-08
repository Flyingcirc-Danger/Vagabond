package Prototype5;



import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;


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
    private boolean monopoly;
    private boolean monopolySuccess;
    private String monopolyResource;
    private int resourceIndex;
    private HashMap<Integer,Integer> monopolyResults; //the results of playing a monopoly card.


    public PlayerDeckScreen(Board parent, PlayerInfo player) {
        this.parent = parent;
        this.buttons = new ArrayList<Button>();
        this.playerDeck = player.getPlayerDeck();
        this.selectionIndex = 0;
        this.resourceIndex = 0;
        this.playerCard = new String();
        monopoly = false;
        monopolySuccess = false;
        monopolyResource = new String();
        monopolyResults = new HashMap<Integer, Integer>();
        setupButtons();

    }

    /**
     * Builds all of the buttons for this screen
     */
    public void setupButtons() {
        this.buttons = new ArrayList<Button>();
        // 0 - purchase button
        int purchaseWidth = Button.widthEstimate("Buy A Card", parent);
        Button purchase = new Button(purchaseWidth, 40, parent);
        purchase.buttonText = "Buy A Card";
        purchase.curveSize = 5;
        buttons.add(purchase);
        // 1 - back button
        int backWidth = Button.widthEstimate("Back", parent);
        Button back = new Button(backWidth, 40, "Back", parent);
        back.curveSize = 5;
        back.setStartX(20);
        back.setStartY(20);
        back.color = new int[]{198, 40, 40};
        buttons.add(back);
        // 2 - DeckButton
        Button deckButton = new Button(parent.loadImage("assets/developmentCards/cardBack.png"), parent);
        deckButton.setStartX((parent.SCREEN_WIDTH) - 10 - deckButton.width);
        deckButton.setStartY(60);
        buttons.add(deckButton);
        //monopoly resource buttons
        // 3 - grain
        Button monGrainButton = new Button(parent.loadImage("assets/discard/discardGrain.png"), parent);
        buttons.add(monGrainButton);
        // 4 - ore
        Button monOreButton = new Button(parent.loadImage("assets/discard/discardOre.png"), parent);
        buttons.add(monOreButton);
        // 5 - wool
        Button monWoolButton = new Button(parent.loadImage("assets/discard/discardWool.png"), parent);
        buttons.add(monWoolButton);
        // 6 - brick
        Button monBrickButton = new Button(parent.loadImage("assets/discard/discardBrick.png"), parent);
        buttons.add(monBrickButton);
        // 7 - logs
        Button monLogsButton = new Button(parent.loadImage("assets/discard/discardLogs.png"), parent);
        buttons.add(monLogsButton);
        //setup the X coords of all of the resource buttons
        int runningTotal = (parent.SCREEN_WIDTH/2) - (490/2);
        for(int i = 3; i < 8; i++){
            buttons.get(i).setStartX(runningTotal);
            runningTotal+= 105;
        }
        //8 - Monopoly Okay
        int okayWidth = Button.widthEstimate("Okay", parent);
        Button monOkay = new Button(okayWidth,40,parent);
        monOkay.buttonText = "Okay";
        monOkay.curveSize = 5;
        monOkay.font = parent.fonts[1];
        monOkay.fontColor = new int[] {255,193,7};
        monOkay.color = new int[] {40, 53, 147};
        monOkay.textSize = 30;
        monOkay.start.x +=5;
        buttons.add(monOkay);
        //9 - monopoly accept okay
        Button monAcceptOkay = new Button(okayWidth,40,parent);
        monAcceptOkay.buttonText = "Okay";
        monAcceptOkay.curveSize = 5;
        monAcceptOkay.font = parent.fonts[1];
        monAcceptOkay.fontColor = new int[] {255,193,7};
        monAcceptOkay.color = new int[] {40, 53, 147};
        monAcceptOkay.textSize = 30;
        monAcceptOkay.start.x +=5;
        buttons.add(monAcceptOkay);


    }

    public void display() {

        if (isOpen()) {
            //if a monopoly card has been played
            if (monopoly) {
                displayMonopolyDialogue();
                return;
            }
            if(monopolySuccess){
                displayMonopolySuccess();
                return;
            } else {
                parent.background(0, 188, 212);
                int cardHeight = (parent.SCREEN_HEIGHT / 2);
                int cardWidth = (int) (cardHeight / (1.4));
                int startX = (parent.SCREEN_WIDTH / 2) - (cardWidth / 2);
                int startY = (parent.SCREEN_HEIGHT / 2) - (cardHeight / 2);
                if (selectionIndex - 1 > -1) {
                    playerDeck.get(selectionIndex - 1).displayBackground(true);
                } else if (selectionIndex - 1 == -1 && playerDeck.size() > 2) {
                    if (playerDeck.size() > 2) {
                        playerDeck.get(playerDeck.size() - 1).displayBackground(true);
                    }
                } else {
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
                if (selectionIndex + 1 < playerDeck.size()) {
                    playerDeck.get(selectionIndex + 1).displayBackground(false);
                } else if (selectionIndex + 1 == playerDeck.size() && playerDeck.size() > 2) {
                    if (playerDeck.size() > 2) {
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
            }
        }else {
            if(playerCard.length() > 0){
                DevelopmentCard.displayMedium(playerCard, parent);
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
            //if a monopoly card has been played
            if(monopolySuccess){
                if(buttons.get(9).checkButton()){
                    resetMonopoly();
                    monopolySuccess = false;
                }
            }
            if (monopoly) {
                if(monopolyResource.length() > 0){
                    //check monopoly okay button
                    if(buttons.get(8).checkButton()){
                        monopolyResults = new HashMap<Integer, Integer>();
                        parent.model.setCardManifest(ObjectParser.parseMonopolyCard(parent.model,resourceIndex,0,false));
                        //resetMonopoly();
                        this.monopoly = false;
                        this.monopolySuccess = true;
                    }
                }
                for(int i = 3; i < 8; i++){
                    if(buttons.get(i).checkButton()){
                        if(i == 3){
                            monopolyResource = "Grain";
                            resourceIndex = 1;
                        }
                        if(i == 4){
                            monopolyResource = "Ore";
                            resourceIndex = 2;
                        }
                        if(i == 5){
                            monopolyResource = "Wool";
                            resourceIndex = 3;
                        }
                        if(i == 6){
                            monopolyResource = "Brick";
                            resourceIndex = 4;
                        }
                        if(i == 7){
                            monopolyResource = "Logs";
                            resourceIndex = 5;
                        }
                        break;
                    }
                }
            }

            else{
                //current card button
                if (playerDeck.size() > 0) {
                    int buttonPress = playerDeck.get(selectionIndex).checkButtons();
                    //back button
                    if (buttonPress == 3) {
                        open = false;
                    }
                    //play card button
                    if (buttonPress == 4) {
                        //send the card manifest info, so that the other players know what's happening
                        parent.model.setCardManifest(ObjectParser.parseCard(parent.model, playerDeck.get(selectionIndex)));
                        if (playerDeck.get(selectionIndex).getType().equals("Monopoly")) {
                            monopoly = true;
                            monopolySuccess = false;
                        } else {
                            monopoly = false;
                        }
                    }
                    if (playerDeck.size() > 1) {
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
            }
        }else {
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

    public void displayMonopolyDialogue(){
        //red and yellow background
        parent.textAlign(parent.CENTER);
        parent.background(255,193,7);
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
        PImage monopolyImg = parent.loadImage("assets/developmentCards/monopolyMD.png");
        parent.image(monopolyImg, parent.SCREEN_WIDTH/2 - (monopolyImg.width/2), (parent.SCREEN_HEIGHT/2) - 150 - monopolyImg.height);
        parent.text("Choose a Monopoly Resource:", (parent.SCREEN_WIDTH / 2) + 2, (parent.SCREEN_HEIGHT / 2) - 100 + 2);
        parent.fill(255,193,7);
        parent.text("Choose a Monopoly Resource:", (parent.SCREEN_WIDTH/2), (parent.SCREEN_HEIGHT/2) - 100);
        parent.textSize(25);
        //resource buttons.
        buttons.get(3).display();
        buttons.get(4).display();
        buttons.get(5).display();
        buttons.get(6).display();
        buttons.get(7).display();
        //monopoly on resource text
        parent.fill(0,0,0,30);
        parent.textFont(parent.fonts[1]);
        parent.textSize(30);
        parent.text(monopolyResource, (parent.SCREEN_WIDTH / 2) + 2, (parent.SCREEN_HEIGHT / 2) + 70 + 2);
        parent.fill(255, 193, 7);
        parent.text(monopolyResource, (parent.SCREEN_WIDTH/2), (parent.SCREEN_HEIGHT/2) + 70);
        buttons.get(8).setStartY((parent.SCREEN_HEIGHT/2) + 120);
        if(monopolyResource.length() > 0) {
            buttons.get(8).display();
        }


    }

    public void displayMonopolySuccess(){
        //red and yellow background
        parent.textAlign(parent.CENTER);
        parent.background(255,193,7);
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
        PImage monopolyImg = parent.loadImage("assets/developmentCards/monopolyMD.png");
        parent.image(monopolyImg, parent.SCREEN_WIDTH/2 - (monopolyImg.width/2), (parent.SCREEN_HEIGHT/2) - 150 - monopolyImg.height);
        parent.text("You receive:", (parent.SCREEN_WIDTH / 2) + 2, (parent.SCREEN_HEIGHT / 2) - 100 + 2);
        parent.fill(255,193,7);
        parent.text("You receive:", (parent.SCREEN_WIDTH/2), (parent.SCREEN_HEIGHT/2) - 100);
        int runningTotal = parent.SCREEN_HEIGHT /2;
        parent.textSize(20);
        for(int id : monopolyResults.keySet()){
            parent.text(monopolyResults.get(id) + " x " + monopolyResource + " from Player " + id, parent.SCREEN_WIDTH/2, runningTotal );
            runningTotal += 30;
        }
        buttons.get(9).setStartY(runningTotal);
        //if everyone has given up the monopoly resource
        if(monopolyResults.size() == parent.model.getPlayerList().size()){
            buttons.get(9).display();

        }

    }

    public HashMap<Integer, Integer> getMonopolyResults() {
        return monopolyResults;
    }

    public void setMonopolyResults(HashMap<Integer, Integer> monopolyResults) {
        this.monopolyResults = monopolyResults;
    }

    public void resetMonopoly(){
        this.monopoly = false;
        this.monopolyResource = new String();
        this.resourceIndex = 0;
    }
}


