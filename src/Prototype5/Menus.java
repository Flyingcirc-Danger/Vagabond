package Prototype5;

import Prototype4.*;

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


    public Menus(Board parent){
        this.gameMenu = new GameMenu(parent, 300,400);
        this.connect = new ConnectMenu(parent, 300,200,false);
        this.resourceBar = new ResourceBar(parent);
        this.die = new Dice(6,5,parent);
        this.bottomMenu = new BottomMenu(parent);
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
}
