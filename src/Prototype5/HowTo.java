package Prototype5;

import processing.core.PImage;

import java.util.ArrayList;

/**
 * Created by Tom_Bryant on 8/15/15.
 * Displays the how to guide
 */
public class HowTo {

    private Board parent;
    private PImage[] slides;
    private int index;
    private ArrayList<Button> buttons;
    private boolean active;

    public HowTo(Board parent){
        this.parent = parent;
        this.slides = new PImage[18];
        for(int i = 1; i <19; i++) {
            slides[i-1] = parent.loadImage("assets/howto/howto" + i + ".png");
        }
        index = 0;
        setupButtons();
        this.active = false;
    }

    public void setupButtons(){
        buttons = new ArrayList<Button>();
        //0 = back
        Button backButton = new Button(200,40,"Back",parent);
        backButton.setStartX(parent.SCREEN_WIDTH / 2 - 100);
        backButton.setStartY(parent.SCREEN_HEIGHT - 80);
        backButton.curveSize = 5;
        buttons.add(backButton);
        //1 = next
        Button nextButton = new Button(200,40,"Next",parent);
        nextButton .setStartX(parent.SCREEN_WIDTH / 2 + 200);
        nextButton.setStartY(parent.SCREEN_HEIGHT - 80);
        buttons.add(nextButton);
        backButton.curveSize = 5;
        //2 = previous
        Button previousButton = new Button(200,40,"Previous",parent);
        previousButton .setStartX(parent.SCREEN_WIDTH / 2 -400);
        previousButton.setStartY(parent.SCREEN_HEIGHT - 80);
        buttons.add(previousButton);
        backButton.curveSize = 5;
    }

    public void display(){
        if(active){
            parent.background(121, 85, 72);
            parent.image(slides[index],parent.SCREEN_WIDTH/2 - (slides[index].width/2), parent.SCREEN_HEIGHT/2 - 400);
            buttons.get(0).display();
            buttons.get(1).display();
            buttons.get(2).display();
        }
    }

    public void checkButtons(){
        if(active){
            if(buttons.get(0).checkButton()){
                this.active = false;
                index = 0;
                parent.model.setDisplayMode(10);
            }
            if(buttons.get(1).checkButton()){
                if(this.index == 17){
                    this.index = 0;
                } else{
                    this.index++;
                }
            }
            if(buttons.get(2).checkButton()){
                if(this.index == 0){
                    this.index = 17;
                } else{
                    this.index--;
                }
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
