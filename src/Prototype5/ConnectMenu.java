package Prototype5;

import java.awt.*;
import processing.core.*;

/**
 * Created by Tom_Bryant on 7/9/15.
 * A menu for connecting to a server
 */
public class ConnectMenu {


    private int width;
    private int height;
    private Board parent;
    private Point center;
    private PImage img;
    private StringBuffer ip;
    private boolean typing;


    public ConnectMenu(Board parent,int width, int height){
        this.parent = parent;
        this.center = new Point (parent.SCREEN_WIDTH/2, parent.SCREEN_HEIGHT/2);
        this.width = width;
        this.height = height;
        this.img = parent.loadImage("assets/logoSM.png");
        this.ip = new StringBuffer("Enter IP to Connect");
    }

    public void display(){
        parent.background(0, 188, 212);
        parent.stroke(0,0,0,0);
        parent.smooth(8);
        parent.fill(0,0,0,70);
        parent.rect(0, 0, parent.SCREEN_WIDTH, parent.SCREEN_HEIGHT);
        parent.rect(center.x - (width/2)+2, center.y - (height/2)+2, width, height);
        parent.stroke(0, 0, 0, 0);
        parent.fill(255, 243, 224);
        parent.rect(center.x - (width/2), center.y - (height/2), width, height);
        buttonsAndLogo();

    }

    /**
     * Builds the buttons and the logo
     */
    public void buttonsAndLogo(){
        int imageX = (width - img.width)/2;
        parent.image(img, center.x - (width/2)+ imageX, center.y - (height/2));
        int sectionHeight = height /3;
        parent.stroke(0,0,0,50);
        parent.fill(255);
        //textbox
        parent.rect(center.x - (width/2) + 5, center.y - (height/2) + (sectionHeight) + 5,width - 10, sectionHeight -10);
        parent.stroke(255,145,0);
        parent.fill(255, 243, 224);
        parent.rect(center.x - (width/2), center.y + ((height/2)/3),width, (height/3));
        int textWidth = (int) parent.textWidth("CONNECT");
        parent.textSize(20);
        parent.fill(255,145,0);
        parent.text("CONNECT", center.x - (textWidth / 2), center.y + ((height / 2) / 3) + ((height/2)/3) + 7);
        parent.fill(0,0,0,90);
        int centerTextY = (center.y - (height/2) + (sectionHeight) + 5) + (sectionHeight /2);
        parent.text(ip.toString(), center.x - (width/2) + 10, centerTextY);
    }





    /**
     * Checks to see if this the mouse has hovered over this item.
     * Checks by tooltip
     */
    public boolean checkHover(Point startingPos, int sWidth, int sHeight){

        if (parent.mouseX >= startingPos.x && parent.mouseX <= startingPos.x+sWidth &&
                parent.mouseY >= startingPos.y && parent.mouseY <= startingPos.y+sHeight) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isTyping() {
        return typing;
    }

    public void setTyping(boolean typing) {
        this.typing = typing;
    }

    public StringBuffer getIp() {
        return ip;
    }

    public void setIp(StringBuffer ip) {
        this.ip = ip;
    }

    /**
     * Checks to see if which button has been selected
     */
    public void checkButtons(){

        Point connectButton = new Point(center.x - (width/2), center.y + ((height/2)/3));
        if(checkHover(connectButton, width, (height/3))){
            this.typing = false;
            parent.initiateConnection(ip.toString());
        }
        Point textButton = new Point(center.x - (width/2) + 5, center.y - (height/2) + (height/3) + 5);
        if(checkHover(textButton, width - 10, (height / 3) - 10)){
            this.typing = true;
        }
        else {
            this.typing = false;
        }

    }

    /**
     * A method for adding text to the text box.
     * @param pressed the character representation of the key pressed
     */
    public void addText(char pressed){

        if(ip.toString().equals("Enter IP to Connect")){
            ip = new StringBuffer();
        }
        //backspace
        if((int) pressed == 8){
            if(ip.length() > 0){
                ip.deleteCharAt(ip.length() -1);
            }
        }
        if((int)pressed > 45 && (int)pressed < 58){
            ip.append(pressed);
        }
    }





}
