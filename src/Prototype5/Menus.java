package Prototype5;


import ServerPrototype1.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Tom_Bryant on 7/14/15.
 * A class containing all of the required menus for a the game
 */

public class Menus {

    private Board parent;

    private GameMenu gameMenu;
    private ConnectMenu connect;
    private ResourceBar resourceBar;
    private BottomMenu bottomMenu;
    private Dice die;
    private StatusMenu waitScreen;
    private BuildCard card;
    private Bank bank;
    private TradeFloor tradeFloor;
    private DiscardScreen discardScreen;
    private RobDialogue robDialogue;
    private DevelopmentDeck deck;
    private PlayerDeckScreen deckScreen;

    public Menus(Board parent, PlayerInfo player){
        this.gameMenu = new GameMenu(parent, 300,400);
        this.connect = new ConnectMenu(parent, 300,200,false);
        this.resourceBar = new ResourceBar(parent);
        this.die = new Dice(6,5,parent);
        this.bottomMenu = new BottomMenu(parent);
        this.waitScreen = new StatusMenu("Waiting For More Players. Click below to ready up", parent, true,true);
        this.card = new BuildCard(parent);
        this.bank = new Bank(parent);
        this.tradeFloor = new TradeFloor(parent);
        this.discardScreen = new DiscardScreen(parent,10);
        this.robDialogue = new RobDialogue(parent);
        this.deck = new DevelopmentDeck(parent);
        this.deckScreen = new PlayerDeckScreen(parent,player);


    }

    public GameMenu getGameMenu() {
        return gameMenu;
    }

    public void setGameMenu(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
    }

    public ResourceBar getResourceBar() {
        return resourceBar;
    }

    public void setResourceBar(ResourceBar resourceBar) {
        this.resourceBar = resourceBar;
    }

    public Dice getDie() {
        return die;
    }

    public void setDie(Dice die) {
        this.die = die;
    }

    public ConnectMenu getConnect() {
        return connect;
    }

    public void setConnect(ConnectMenu connect) {
        this.connect = connect;
    }

    public BottomMenu getBottomMenu() {
        return bottomMenu;
    }

    public void setBottomMenu(BottomMenu bottomMenu) {
        this.bottomMenu = bottomMenu;
    }

    public StatusMenu getWaitScreen() {
        return waitScreen;
    }

    public void setWaitScreen(StatusMenu waitScreen) {
        this.waitScreen = waitScreen;
    }

    public BuildCard getCard() {
        return card;
    }

    public void setCard(BuildCard card) {
        this.card = card;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public TradeFloor getTradeFloor() {
        return tradeFloor;
    }

    public void setTradeFloor(TradeFloor tradeFloor) {
        this.tradeFloor = tradeFloor;
    }

    public DiscardScreen getDiscardScreen() {
        return discardScreen;
    }

    public void setDiscardScreen(DiscardScreen discardScreen) {
        this.discardScreen = discardScreen;
    }

    public RobDialogue getRobDialogue() {
        return robDialogue;
    }

    public void setRobDialogue(RobDialogue robDialogue) {
        this.robDialogue = robDialogue;
    }

    public DevelopmentDeck getDevDeck() {
        return deck;
    }

    public ArrayList<DevelopmentCard> getDeck(){
        return deck.getDeck();
    }

    public void setDevDeck(DevelopmentDeck deck) {
        this.deck = deck;
    }

    public PlayerDeckScreen getDeckScreen() {
        return deckScreen;
    }

    public void setDeckScreen(PlayerDeckScreen deckScreen) {
        this.deckScreen = deckScreen;
    }


}
