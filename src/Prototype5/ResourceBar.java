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

    public void container(int x, int y){
        parent.fill(78,52,46);
        parent.stroke(0,0,0,30);
        parent.rect(x,y+2, 30,25,5,5,5,5);
        parent.fill(255);
        parent.text("0", x + 15 - (parent.textWidth("0")/2), y + 15 + 7 );
    }


    public void populateResources(){
        this.resources.add(new ResourceIcon(parent,"grain"));
        this.resources.add(new ResourceIcon(parent, "ore"));
        this.resources.add(new ResourceIcon(parent, "wool"));
        this.resources.add(new ResourceIcon(parent, "brick"));
        this.resources.add(new ResourceIcon(parent, "logs"));
        spaceResources();
    }

    public void spaceResources(){
        int tempCounter = (parent.SCREEN_WIDTH/2) - (350/2);
        for(ResourceIcon icon : resources){
            Point start = new Point(tempCounter, 5);
            icon.setStartPos(start);
            tempCounter += 70;
        }
    }
}
