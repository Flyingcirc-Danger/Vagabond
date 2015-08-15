package Prototype5;

import processing.core.PImage;

import java.util.ArrayList;

/**
 * Contains the buttons for the escape menu
 * Created by Tom_Bryant on 8/14/15.
 */
public class EscapeMenu {

    private ArrayList<Button> buttons;
    private Board parent;
    private boolean visible;
    private PImage img;
    private PImage costCard;

    public EscapeMenu(Board parent) {
        this.visible = false;
        this.parent = parent;
        this.img = parent.loadImage("assets/logoSM.png");
        setButtons();
        this.costCard = parent.loadImage("assets/playerCard.png");

    }

    public void setButtons() {
        this.buttons = new ArrayList<Button>();
        // 0 - mainMenu button
        Button mainMenu = new Button(200, 40, "Main Menu", parent);
        mainMenu.setStartX(parent.SCREEN_WIDTH / 2 - 100);
        buttons.add(mainMenu);
        // 1 - Quit game button;
        Button quit = new Button(200, 40, "Quit Game", parent);
        quit.setStartX(parent.SCREEN_WIDTH / 2 - 100);
        buttons.add(quit);
        // 2 - back button;
        Button back = new Button(200, 40, "Back", parent);
        back.setStartX(parent.SCREEN_WIDTH / 2 - 100);
        buttons.add(back);
    }

    public void display(){
        if(visible){
            int height = 220;
            int width = 220;
            parent.fill(0,0,0,30);
            parent.rect(parent.SCREEN_WIDTH/2 - width/2 +2, parent.SCREEN_HEIGHT/2 - 105 +2,width,220);
            parent.fill(255, 243, 224);
            parent.rect(parent.SCREEN_WIDTH/2 - width/2, parent.SCREEN_HEIGHT/2 - 105,width,220);
            parent.image(img, parent.SCREEN_WIDTH / 2 - 100, parent.SCREEN_HEIGHT / 2 - 100);
            buttons.get(0).setStartY(parent.SCREEN_HEIGHT / 2 - 20);
            buttons.get(1).setStartY(parent.SCREEN_HEIGHT/2 + 23);
            buttons.get(2).setStartY(parent.SCREEN_HEIGHT/2 + 66);
            buttons.get(0).display();
            buttons.get(1).display();
            buttons.get(2).display();
            int cardHeight = costCard.height;
            parent.image(costCard, 50,parent.SCREEN_HEIGHT/2 - cardHeight/2 );
            checkButtons();
        }
    }

    public void toggleDisplay(){
        if(this.visible){
            this.visible = false;
        } else{
            this.visible = true;
        }
    }

    public void checkButtons(){
        if(parent.mousePressed){
            if(buttons.get(2).checkButton()){
                toggleDisplay();
            }
            if(buttons.get(1).checkButton()){
                parent.exit();
            }
            if(buttons.get(0).checkButton()){
                parent.model.restartClient();
            }
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}



