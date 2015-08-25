package Prototype5;

import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Tom_Bryant on 8/3/15.
 * The development deck
 */
public class DevelopmentDeck {


    private ArrayList<DevelopmentCard> deck;
    private Board parent;
    private PImage[] devImages;
    private int turnRemovedCards; //the number of removed cards this turn.


    public DevelopmentDeck(Board parent){
        this.parent = parent;
        this.deck = new ArrayList<DevelopmentCard>();
        devImages = new PImage[8];
        devImages[0] = parent.loadImage("assets/developmentCards/knight.png");
        devImages[1] = parent.loadImage("assets/developmentCards/monopoly.png");
        devImages[2] = parent.loadImage("assets/developmentCards/yearofplenty.png");
        devImages[3] = parent.loadImage("assets/developmentCards/victorypoint.png");
        devImages[4] = parent.loadImage("assets/developmentCards/freeroad.png");
        devImages[5] = parent.loadImage("assets/developmentCards/logoskew.png");
        devImages[6] = parent.loadImage("assets/developmentCards/prev.png");
        devImages[7] = parent.loadImage("assets/developmentCards/next.png");
        turnRemovedCards = 0;
        buildDeck();
    }


    /**
     * Builds the Development deck
     * 14 x Knight
     * 5 x Victory Points
     * 2 x Monopoly
     * 2 x Road Building
     * 2 x Year of Plenty
     */
    public void buildDeck(){
        deck = new ArrayList<DevelopmentCard>();
        int id = 0;
        for(int i = 0; i < 14; i++){
            deck.add(new DevelopmentCard("Knight",parent,id));
            id++;
        }
        for(int i = 0; i < 5; i++){
            deck.add(new DevelopmentCard("Victory Point",parent, id));
            id++;
        }
        for(int i = 0; i < 2; i++){
            deck.add(new DevelopmentCard("Monopoly",parent,id));
            id++;
        }
        for(int i = 0; i < 2; i++){
            deck.add(new DevelopmentCard("Road Building",parent,id));
            id++;
        }
        for(int i = 0; i < 2; i++){
            deck.add(new DevelopmentCard("Year of Plenty",parent,id));
            id++;
        }

    }




    /**
     * Shuffles the deck of development cards
     * by iterating backwards through the deck and
     * switching each card with another randomly chosen
     * card that is <= that cards position.
     */
    public void shuffleDeck(long seedNo){
        buildDeck();
        int desertIndex = 0;
        Random seed = new Random(seedNo);
        for (int i = deck.size() - 1; i > 0; i--) {
            int index = seed.nextInt(i + 1);
            DevelopmentCard temp = deck.get(index);
            DevelopmentCard iter = deck.get(i);
            deck.set(index, iter);
            deck.set(i, temp);
        }

    }

    public ArrayList<DevelopmentCard> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<DevelopmentCard> deck) {
        this.deck = deck;
    }

    public PImage[] getDevImages() {
        return devImages;
    }

    public void setDevImages(PImage[] devImages) {
        this.devImages = devImages;
    }

    /**
     * Pulls a card from the back of the deck and gives it to the models player
     * Also returns the card
     */
    public void getCard(){
        if(deck.size() > 0) {
            DevelopmentCard candidate = deck.get(deck.size() - 1);
            System.out.println("Getting card " + candidate.getId());
            removeCard();
            candidate.addToPlayerDeck();
            parent.model.getMenus().getDeckScreen().setSelectionIndex(
                    parent.model.getPlayer().getPlayerDeck().size() - 1);
            if (candidate.getType().equals("Victory Point")) {
                parent.model.addVP(1);
                HashMap<String, Integer> vp = parent.model.getVictoryBonus().getVictoryPointMap();
                vp.put("Victory Point Card", vp.get("Victory Point Card") + 1);
            }
            turnRemovedCards++;
        }
    }

    /**
     * Removes a card from the back of the deck.
     * This is to be called by all players except the
     * initiating player once a card has been drawn.
     */
    public void removeCard(){
        System.out.println("Removing card " + deck.get(deck.size() -1).getId());
        if(deck.size() > 0) {
            deck.remove(deck.size() - 1);
        }
    }

    public int getTurnRemovedCards() {
        return turnRemovedCards;
    }

    public void setTurnRemovedCards(int turnRemovedCards) {
        this.turnRemovedCards = turnRemovedCards;
    }

}
