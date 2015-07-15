package Prototype5;

/**
 * Created by Tom_Bryant on 7/14/15.
 * A Menu for displaying status messages.
 */
public class StatusMenu {

    private String text;
    private Board parent;
    private boolean button;
    private boolean highighted;

    public StatusMenu(String text, Board parent, boolean button){
        this.text = text;
        this.parent = parent;
        this.button = button;
        this.highighted = false;
    }

    public void display(){
        int length = (int) parent.textWidth(text) + 40;
        int height = 60;
        if(button){
            height = 80;
            parent.textSize(20);
            parent.fill(121, 85, 72);
            parent.stroke(0,0,0,0);
            parent.rect(parent.SCREEN_WIDTH/2 - (length/2), parent.SCREEN_HEIGHT/2 - (height/2), length, height);
            parent.fill(0,0,0,30);
            parent.rect(parent.SCREEN_WIDTH/2 - (length/2)+2, parent.SCREEN_HEIGHT/2 - (height/2)+2, length, height);
            parent.fill(255);
            parent.text(text,parent.SCREEN_WIDTH/2 - (parent.textWidth(text)/2), parent.SCREEN_HEIGHT/2 - 10);
            DialogueButton("Ready");
        } else{
            parent.textSize(20);
            parent.fill(121, 85, 72);
            parent.stroke(0,0,0,0);
            parent.rect(parent.SCREEN_WIDTH/2 - (length/2), parent.SCREEN_HEIGHT/2 - (height/2), length, height);
            parent.fill(0,0,0,30);
            parent.rect(parent.SCREEN_WIDTH/2 - (length/2)+2, parent.SCREEN_HEIGHT/2 - (height/2)+2, length, height);
            parent.fill(255);
            parent.text(text,parent.SCREEN_WIDTH/2 - (parent.textWidth(text)/2), parent.SCREEN_HEIGHT/2 + 10);
        }

    }


    public void DialogueButton(String bText){
        int length = (int) parent.textWidth(bText) + 40;
        int height = 30;
        parent.textSize(20);
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect(parent.SCREEN_WIDTH/2 - (length/2), parent.SCREEN_HEIGHT/2 , length, height);
        parent.fill(0,0,0,30);
        if(highighted){
            parent.rect(parent.SCREEN_WIDTH/2 - (length/2)-2, parent.SCREEN_HEIGHT/2 -2, length, height);
        } else {
            parent.rect(parent.SCREEN_WIDTH / 2 - (length / 2) + 2, parent.SCREEN_HEIGHT / 2 + 2, length, height);
        }
        parent.fill(255);
        parent.text(bText,parent.SCREEN_WIDTH/2 - (parent.textWidth(bText)/2), parent.SCREEN_HEIGHT/2 + (22));
    }


    /**
     * A method for checking a rectangular
     * space for a mouse click
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    boolean overRect(int x, int y, int width, int height) {
        if (parent.mouseX >= x && parent.mouseX <= x + width &&
                parent.mouseY >= y && parent.mouseY <= y + height) {
            return true;
        } else {
            return false;
        }
    }


    public boolean checkButton(){
        int length = (int) parent.textWidth("Ready") + 40;
        int height = 30;
        if(overRect(parent.SCREEN_WIDTH/2 - (length/2), parent.SCREEN_HEIGHT/2, length, height)){
            this.highighted = true;
            return true;
        }
        else {
            this.highighted = false;
            return false;
        }
    }
}
