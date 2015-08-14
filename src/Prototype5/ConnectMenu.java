package Prototype5;

import ServerPrototype1.Server;
import processing.core.PImage;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
    private boolean error;
    private ArrayList<Button> buttons;
    private int menuIndex;
    private String errorMessage;


    public ConnectMenu(Board parent,int width, int height,boolean error){
        this.parent = parent;
        this.center = new Point (parent.SCREEN_WIDTH/2, parent.SCREEN_HEIGHT/2);
        this.width = width;
        this.height = height;
        this.img = parent.loadImage("assets/logoSM.png");
        this.ip = new StringBuffer("Enter IP to Connect");
        this.error = error;
        this.errorMessage = "Address Not Found";
        setupButtons();
        if(error){
            menuIndex = 1;
        } else {
            menuIndex = 0;
        }
    }

    public ConnectMenu(Board parent,int width, int height,boolean error, String errorMessage ){
        this.parent = parent;
        this.center = new Point (parent.SCREEN_WIDTH/2, parent.SCREEN_HEIGHT/2);
        this.width = width;
        this.height = height;
        this.img = parent.loadImage("assets/logoSM.png");
        this.ip = new StringBuffer("Enter IP to Connect");
        this.error = error;
        this.errorMessage = errorMessage;
        setupButtons();
        if(error){
            menuIndex = 1;
        } else {
            menuIndex = 0;
        }
    }

    private void setupButtons(){
        this.buttons = new ArrayList<Button>();
        // 0 - Join game button;
        Button join = new Button(200,40,"Join Game", parent);
        join.setStartX(parent.SCREEN_WIDTH / 2 - 100);
        buttons.add(join);
        // 1 - host game button;
        int hostWidth = Button.widthEstimate("Host Game", parent);
        Button host = new Button(200,40,"Host Game", parent);
        host.setStartX(parent.SCREEN_WIDTH/2 - 100);
        buttons.add(host);
        // 2 - Quit game button;
        Button quit = new Button(200,40,"Quit", parent);
        quit.setStartX(parent.SCREEN_WIDTH / 2 - 100);
        buttons.add(quit);
        // 3 - connect to server button;
        Button connect = new Button(200,40,"Connect", parent);
        connect.setStartX(parent.SCREEN_WIDTH / 2 - 100);
        buttons.add(connect);
        // 4 - back button;
        Button back = new Button(200,40,"Back", parent);
        back.setStartX(parent.SCREEN_WIDTH / 2 - 100);
        buttons.add(back);

    }

    public void display(){
        parent.background(0, 188, 212);
        parent.stroke(0, 0, 0, 0);
        parent.smooth(8);
        parent.fill(0, 0, 0, 70);
        parent.rect(0, 0, parent.SCREEN_WIDTH, parent.SCREEN_HEIGHT);
        if(menuIndex == 0){
            int height = 210;
            int width = 220;
            int textWidth = (int) (parent.textWidth(errorMessage) + 20);
            if(width <  textWidth){
                width = textWidth;
            }
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
        } else if(menuIndex == 1) {
            connectMenu();
        }else{
            //check to see if this menu is built after an error has occured
            if (error) {
                errorMessage("Server not found");
            }
            parent.fill(0, 0, 0, 70);
            parent.rect(center.x - (width / 2) + 2, center.y - (height / 2) + 2, width, height);
            parent.stroke(0, 0, 0, 0);
            parent.fill(255, 243, 224);
            parent.rect(center.x - (width / 2), center.y - (height / 2), width, height);
            buttonsAndLogo();
        }

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
        //buttons
        int textWidth = (int) parent.textWidth("CONNECT");
        parent.textSize(20);
        parent.fill(255,145,0);
        parent.textFont(parent.fonts[1]);
        parent.text("CONNECT", center.x - (textWidth / 2), center.y + ((height / 2) / 3) + ((height/2)/3) + 7);
        parent.textFont(parent.fonts[0]);
        parent.fill(0,0,0,90);
        int centerTextY = (center.y - (height/2) + (sectionHeight) + 5) + (sectionHeight /2);
        parent.text(ip.toString(), center.x - (width/2) + 10, centerTextY);
        parent.stroke(0,0,0,0);
    }


    public void connectMenu(){
        if(error){
            int textWidth = (int) (parent.textWidth(errorMessage) + 20);
            parent.fill(211, 47, 47);
            parent.rect(parent.SCREEN_WIDTH/2 - textWidth/2, parent.SCREEN_HEIGHT/2 - 145, textWidth,40);
            parent.fill(255);
            parent.textAlign(parent.CENTER);
            parent.text(errorMessage, parent.SCREEN_WIDTH/2, parent.SCREEN_HEIGHT/2 - 110);
            parent.textAlign(parent.LEFT);
        }
        int width = 220;
        int textWidth = (int) (parent.textWidth(errorMessage));
        if(width <  textWidth){
            width = textWidth;
        }
        parent.fill(0,0,0,30);
        parent.rect(parent.SCREEN_WIDTH/2 - width/2 +2, parent.SCREEN_HEIGHT/2 - 105 +2,width,240);
        int height = 210;
        parent.fill(255, 243, 224);
        parent.rect(parent.SCREEN_WIDTH/2 - width/2, parent.SCREEN_HEIGHT/2 - 105,width,240);
        parent.image(img, parent.SCREEN_WIDTH / 2 - 100, parent.SCREEN_HEIGHT / 2 - 95);
        parent.stroke(0,0,0,50);
        parent.fill(255);
        parent.rect(parent.SCREEN_WIDTH/2 - 105, parent.SCREEN_HEIGHT/2 -15, 210,40);
        parent.textSize(18);
        parent.fill(0,0,0,90);
        parent.text(ip.toString(), parent.SCREEN_WIDTH/2 - 100 ,parent.SCREEN_HEIGHT/2 + 18 );
        buttons.get(3).setStartY(parent.SCREEN_HEIGHT / 2 + 38);
        buttons.get(4).setStartY(parent.SCREEN_HEIGHT / 2 + 81);
        buttons.get(3).display();
        buttons.get(4).display();

    }

    /**
     * Displays an error message above the menu.
     * @param message the error message to display.
     */
    public void errorMessage(String message) {
        parent.stroke(0,0,0,0);
        int startX = (center.x - width / 2);
        int startY = (center.y - height / 2) - 40;
        int messageWidth = (int) parent.textWidth(message);
        parent.fill(0, 0, 0, 70);
        parent.rect(startX + 2, startY + 2, width, 40);
        parent.fill(211, 47, 47);
        parent.rect(startX, startY, width, 40);
        parent.fill(255);
        parent.textSize(20);
        parent.text(message, startX + (width / 2) - (messageWidth / 2), startY + (27));


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
        if(menuIndex == 0){
            //join button
            if(buttons.get(0).checkButton()){
                menuIndex = 1;
            }
            //host button
            if(buttons.get(1).checkButton()){
                try {
                    parent.game =  new Server(4001,parent);
                } catch (IOException e) {
                    parent.model.getMenus().setConnect(new ConnectMenu(parent,300,200,true,"Server Is Already Running"));
                    return;
                }
                String ip = "127.0.0.1";
                try {
                     ip = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                parent.initiateConnection(ip);
                parent.model.getMenus().getWaitScreen().setHost(true);

            }
            //quit button
            if(buttons.get(2).checkButton()){
               parent.exit();
            }

        } else if(menuIndex == 1){
            if(Listeners.overRect(parent.SCREEN_WIDTH/2 - 105, parent.SCREEN_HEIGHT/2 -15, 210,40, parent)){
                this.typing = true;
            } else{
                this.typing = false;
            }
            if(buttons.get(3).checkButton()){
                String connect = ip.toString();
                ip = new StringBuffer("Connecting");
                parent.initiateConnection(connect);
            }
            if(buttons.get(4).checkButton()){
                menuIndex = 0;
            }
        } else {

            Point connectButton = new Point(center.x - (width / 2), center.y + ((height / 2) / 3));
            if (checkHover(connectButton, width, (height / 3))) {
                this.typing = false;
                parent.initiateConnection(ip.toString());
            }
            Point textButton = new Point(center.x - (width / 2) + 5, center.y - (height / 2) + (height / 3) + 5);
            if (checkHover(textButton, width - 10, (height / 3) - 10)) {
                this.typing = true;
            } else {
                this.typing = false;
            }
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
