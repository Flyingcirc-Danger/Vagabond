package Prototype5;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tom_Bryant on 7/13/15.
 * A bar displaying the resources (hugs the top of the screen)
 */
public class ResourceBar {

    private Board parent;
    private ArrayList<ResourceIcon> resources;

    public ResourceBar(Board parent){
        this.parent = parent;
        resources = new ArrayList<ResourceIcon>();
        populateResources();

    }

    public void display(){
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect(0, 0, parent.SCREEN_WIDTH, 40);
        for(ResourceIcon icon : resources){
            icon.display();
        }

    }

    /**
     * Displays the resource bar on the bottom of the screen
     */
    public void displayBottom(){
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect(0, parent.SCREEN_HEIGHT - 40, parent.SCREEN_WIDTH, 40);
        for(ResourceIcon icon : resources){
            icon.displayBottom();
        }

    }


    /**
     * Adds resource Icons and their counts to the resource bar
     */
    public void populateResources(){
        this.resources.add(new ResourceIcon(parent,"grain"));
        this.resources.add(new ResourceIcon(parent, "ore"));
        this.resources.add(new ResourceIcon(parent, "wool"));
        this.resources.add(new ResourceIcon(parent, "brick"));
        this.resources.add(new ResourceIcon(parent, "logs"));
        spaceResources();
    }

    /**
     * Spaces resources out on the bar
     */
    public void spaceResources(){
        int tempCounter = (parent.SCREEN_WIDTH/2) - (350/2);
        for(ResourceIcon icon : resources){
            Point start = new Point(tempCounter, 5);
            icon.setStartPos(start);
            tempCounter += 70;
        }
    }
}
