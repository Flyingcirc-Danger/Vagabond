package Prototype5;

import processing.core.PImage;

/**
 * Created by Tom_Bryant on 8/3/15.
 * A development card object. Can be a monopoly,
 * year of plenty,road building, victory points
 */
public class DevelopmentCard {

    private String type;
    private boolean large;
    private Board parent;
    private PImage logo;
    private String ruleText;
    private boolean inPlayerDeck;



    public DevelopmentCard(String type,Board parent){
        this.type = type;
        this.large = true;
        this.parent = parent;
        this.inPlayerDeck = false;



    }

    public void display(){
        logo = parent.model.getMenus().getDevDeck().getDevImages()[0];
        ruleText = "When you play this card, you move the robber and steal a resource from " +
                "the owner of an adjacent settlement or city";
        if(type.equals("Monopoly")){
            logo = parent.model.getMenus().getDevDeck().getDevImages()[1];
            ruleText = "When you play this card, " +
                    "announce one type of resource. " +
                    "All other players must give you their " +
                    "entire supply of that resource.";
        }
        if(type.equals("Year of Plenty")){
            logo = parent.model.getMenus().getDevDeck().getDevImages()[2];
            ruleText = "When you play this card, you can select 2 " +
                    "resources of your choice from the bank";
        }
        if(type.equals("Victory Point")){
            logo = parent.model.getMenus().getDevDeck().getDevImages()[3];
            ruleText = "You gain one Victory Point!";
        }
        if(type.equals("Road Building")){
            logo = parent.model.getMenus().getDevDeck().getDevImages()[4];
            ruleText = "When you play this card, you can build two roads free of charge";
        }
        if(this.large){
            parent.stroke(0,0,0,0);
            int cardHeight = (parent.SCREEN_HEIGHT/2);
            int cardWidth = (int)(cardHeight / (1.4));
            int startX = (parent.SCREEN_WIDTH/2) - (cardWidth/2);
            int startY = (parent.SCREEN_HEIGHT/2) - (cardHeight/2);
            parent.fill(0,0,0,30);
            parent.rect(startX+2, startY+2,cardWidth,cardHeight,15,15,15,15);
            parent.fill(227, 226, 213);
            parent.rect(startX, startY,cardWidth,cardHeight,15,15,15,15);
            parent.fill(62, 39, 35);
            parent.rect(startX+7, startY+7,cardWidth-14,cardHeight-14,15,15,15,15);
            //icon
            int boxHeight = ((cardHeight - 14)/2) -20;
            int boxStartY = startY + 17;
            int boxStartX = startX + 17;
            int boxWidth = ((cardWidth - 14)) - 20;
            parent.fill(198, 40, 40);
            parent.rect(boxStartX, boxStartY,boxWidth,boxHeight,10,10,10,10);
            parent.image(logo,startX + (cardWidth/2)- 80 , startY + 17 + (boxHeight/2) - 80);
            //text
            parent.textAlign(parent.CENTER);
            parent.textSize(30);
            parent.fill(255);
            parent.text(type, parent.SCREEN_WIDTH/2,startY + (cardHeight/2) + 30);
            int textHeight = startY + cardHeight - (startY + (cardHeight/2) + 40);
            parent.textSize(12);
            parent.textAlign(parent.CENTER,parent.CENTER);
            parent.text(ruleText, startX + 17, startY + (cardHeight/2) + 40, cardWidth - 34, textHeight -20);
            if(!inPlayerDeck){
                parent.textSize(20);
                int buttonWidth = (int) parent.textWidth("Okay") + 40;
                parent.fill(198, 40, 40);
                parent.rect((parent.SCREEN_WIDTH/2) - buttonWidth/2, startY + cardHeight + 10, buttonWidth, 40,5,5,5,5);
                parent.fill(255);
                parent.textAlign(parent.CENTER,parent.CENTER);
                parent.text("Okay", (parent.SCREEN_WIDTH/2), startY + cardHeight + 10 + 15);

            }

        }

    }


    /**
     * Add a card to this players deck
     */
    public void addToPlayerDeck(){
        this.inPlayerDeck = true;
        parent.model.getPlayer().getPlayerDeck().add(this);
    }



    public boolean isInPlayerDeck() {
        return inPlayerDeck;
    }

    public void setInPlayerDeck(boolean inPlayerDeck) {
        this.inPlayerDeck = inPlayerDeck;
    }

    public void displayButtons(){

    }
}
