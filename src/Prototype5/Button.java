package Prototype5;

import processing.core.PImage;

import java.awt.*;


/**
 * Created by Tom_Bryant on 8/5/15.
 * An object that creates a button and it's listener.
 */
public class Button {

    public Point start;
    public int width;
    public int height;
    public int curveSize;
    public int[] color;
    public int[] shadow;
    public String buttonText;
    public int textSize;
    public int[] fontColor;
    private Board parent;
    public Point textStart;
    public PImage icon;
    public boolean isIcon;


    public Button(Point start, int width, int height, int curveSize, int[] color, Board parent){
        this.start = start;
        this.width = width;
        this.height = height;
        this.curveSize = curveSize;
        this.color = color;
        this.shadow = new int[]{0,0,0,30};
        this.parent = parent;
        this.buttonText = "No Text";
        textSize = 20;
        fontColor = new int[]{255,255,255};
        textStart = new Point(start.x + (width/2),start.y + (height/2) -2);
    }

    public Button(int x, int y, int width, int height,Board parent){
        this.start= new Point(x,y);
        this.width = width ;
        this.height = height;
        curveSize = 0;
        color = new int[] {255, 167, 38};
        this.shadow = new int[]{0,0,0,30};
        this.parent = parent;
        this.buttonText = "No Text";
        textSize = 20;
        fontColor = new int[]{255,255,255};
        textStart = new Point(start.x + (width/2),start.y + (height/2)-2);
    }

    public Button(int x, int y, int width, int height, int[] color,Board parent){
        this.start= new Point(x,y);
        this.width = width ;
        this.height = height;
        this.color = color;
        this.curveSize = 0;
        this.shadow = new int[]{0,0,0,30};
        this.parent = parent;
        this.buttonText = "No Text";
        textSize = 20;
        fontColor = new int[]{255,255,255};
        textStart = new Point(start.x + (width/2),start.y + (height/2)-2);
    }

    public Button(int x, int y, int width, int height,int [] color, String buttonText, Board parent){
        this.start= new Point(x,y);
        this.width = width ;
        this.height = height;
        this.color = color;
        this.buttonText = buttonText;
        this.curveSize = 0;
        this.shadow = new int[]{0,0,0,30};
        this.parent = parent;
        textSize = 20;
        fontColor = new int[]{255,255,255};
        textStart = new Point(start.x + (width/2),start.y + (height/2)-2);
    }

    public Button(int width, int height,Board parent){
        this.start = new Point((parent.SCREEN_WIDTH/2) - (width/2), (parent.SCREEN_HEIGHT/2) - (height/2));
        this.width = width;
        this.height = height;
        curveSize = 0;
        color = new int[] {255, 167, 38};
        this.shadow = new int[]{0,0,0,30};
        this.parent = parent;
        this.buttonText = "No Text";
        textSize = 20;
        fontColor = new int[]{255,255,255};
        textStart = new Point(start.x + (width/2),start.y + (height/2)-2);
    }

    public Button(int width, int height,String buttonText, Board parent){
        this.start = new Point((parent.SCREEN_WIDTH/2) - (width/2), (parent.SCREEN_HEIGHT/2) - (height/2));
        this.width = width;
        this.height = height;
        curveSize = 0;
        color = new int[] {255, 167, 38};
        this.shadow = new int[]{0,0,0,30};
        this.parent = parent;
        this.buttonText = buttonText;
        textSize = 20;
        fontColor = new int[]{255,255,255};
        textStart = new Point(start.x + (width/2),start.y + (height/2)-2);
    }

    public Button(PImage icon, int x, int y, Board parent){
        this.icon = icon;
        this.start = new Point(x,y);
        this.width = icon.width;
        this.height = icon.height;
        this.isIcon = true;
        textStart = new Point();
        this.parent = parent;

    }

    public Button(PImage icon, Board parent){
        this.icon = icon;
        this.start = new Point(parent.SCREEN_WIDTH/2 - icon.width/2,parent.SCREEN_HEIGHT/2 - icon.height/2);
        this.width = icon.width;
        this.height = icon.height;
        this.isIcon = true;
        this.parent = parent;
        textStart = new Point();
    }

    public void display(){
        if(isIcon){
            parent.image(icon,start.x,start.y);
        }else {
            if (width < parent.textWidth(buttonText)) {
                width = 40 + (int) parent.textWidth(buttonText);
            }
            if (height < textSize) {
                height = textSize + 20;
            }
            parent.fill(color[0], color[1], color[2]);
            parent.stroke(0, 0, 0, 0);
            parent.rect(start.x, start.y, width, height, curveSize, curveSize, curveSize, curveSize);
            parent.fill(shadow[0], shadow[1], shadow[2], shadow[3]);
            parent.rect(start.x+2, start.y+2, width, height, curveSize, curveSize, curveSize, curveSize);
            parent.textAlign(parent.CENTER, parent.CENTER);
            parent.fill(fontColor[0], fontColor[1], fontColor[2]);
            parent.text(buttonText, textStart.x, textStart.y);
            parent.textAlign(parent.LEFT, parent.BOTTOM);
        }
    }

    /**
     * Estimates the button width.
     * @param text
     * @param parent
     * @return
     */
    public static int widthEstimate(String text,Board parent){
        return 40 + (int)parent.textWidth(text);
    }


    public void setStartX(int newStartX){
        start.x = newStartX;
        textStart.x = newStartX + (width/2);
    }

    public void setStartY(int newStartY){
        start.y = newStartY;
        textStart.y = newStartY + (height/2)-2;
    }

    /**
     * Handles the listening for this button.
     * @return true if pressed
     */
    public boolean checkButton(){
        return(Listeners.overRect(start.x,start.y,width,height,parent));
    }


}
