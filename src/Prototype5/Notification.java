package Prototype5;

import org.w3c.dom.NodeList;

/**
 * A Class for displaying a notifation.
 * Created by Tom_Bryant on 8/12/15.
 */
public class Notification {


    private Button okayButton;
    private Board parent;
    private String message;
    private int width;
    private int height;
    private int textHeight;
    private boolean visible;
    public boolean isEmpty;


    public Notification(Board parent, String message,int textHeight){
        this.parent = parent;
        this.message = message;
        parent.textSize(textHeight);
        this.width = (int)parent.textWidth(message) + 40;
        this.height = (textHeight * 2) + 70;
        this.textHeight = textHeight;
        int buttonWidth = (int)parent.textWidth("Okay") + 40;
        this.okayButton = new Button(buttonWidth, textHeight, "Okay",parent);
        okayButton.curveSize = 5;
        visible = false;
        isEmpty = false;
    }

    public Notification(){
        visible =false;
        isEmpty = true;
    }



    public void display(){
        if(visible){
            parent.stroke(0,0,0,0);
            parent.fill(121, 85, 72);
            parent.rect((parent.SCREEN_WIDTH/2) - (width/2), (parent.SCREEN_HEIGHT/2) - (height/2),width, height, 5,5,5,5);
            parent.textAlign(parent.CENTER, parent.CENTER);
            parent.fill(255);
            parent.textFont(parent.fonts[0]);
            parent.textSize(textHeight);
            parent.text(message, parent.SCREEN_WIDTH/2, parent.SCREEN_HEIGHT/2 - (height/4));
            parent.fill(0,0,0,30);
            parent.rect((parent.SCREEN_WIDTH/2) - (width/2) +2, (parent.SCREEN_HEIGHT/2) - (height/2)+2,width, height, 5,5,5,5);

            okayButton.setStartY(parent.SCREEN_HEIGHT/2);
            okayButton.display();
        }
    }

    public boolean checkButtons(){
        if(visible){
            if(okayButton.checkButton()){
                visible = false;
                return true;
            }

        }
        return false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
