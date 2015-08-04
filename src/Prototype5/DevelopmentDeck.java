package Prototype5;

import processing.core.PImage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tom_Bryant on 8/3/15.
 * The development deck
 */
public class DevelopmentDeck {


    private ArrayList<DevelopmentCard> deck;
    private Board parent;
    private PImage[] devImages;


    public DevelopmentDeck(Board parent){
        this.parent = parent;
        this.deck = new ArrayList<DevelopmentCard>();
        devImages = new PImage[5];
        devImages[0] = parent.loadImage("assets/developmentCards/knight.png");
        devImages[1] = parent.loadImage("assets/developmentCards/monopoly.png");
        devImages[2] = parent.loadImage("assets/developmentCards/yearofplenty.png");
        devImages[3] = parent.loadImage("assets/developmentCards/victoryPoint.png");
        devImages[4] = parent.loadImage("assets/developmentCards/freeroad.png");
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
        for(int i = 0; i < 14; i++){
            deck.add(new DevelopmentCard("Knight",parent));
        }
        for(int i = 0; i < 5; i++){
            deck.add(new DevelopmentCard("Victory Point",parent));
        }
        for(int i = 0; i < 2; i++){
            deck.add(new DevelopmentCard("Monopoly",parent));
        }
        for(int i = 0; i < 2; i++){
            deck.add(new DevelopmentCard("Road Building",parent));
        }
        for(int i = 0; i < 2; i++){
            deck.add(new DevelopmentCard("Year of Plenty",parent));
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
            deck.add(index, deck.get(i));
            deck.add(i, deck.get(index));
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
     */
    public void getCard(){
        DevelopmentCard candidate = deck.get(deck.size()-1);
        deck.remove(deck.size()-1);
        candidate.addToPlayerDeck();
    }

    /**
     * Removes a card from the back of the deck.
     * This is to be called by all players except the
     * initiating player once a card has been drawn.
     */
    public void removeCard(){
        deck.remove(deck.size()-1);
    }
}
