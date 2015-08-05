package Prototype5;

import processing.core.PImage;

import java.util.ArrayList;


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
    private int id;
    private boolean turnOfPurchase;
    private ArrayList<Button> buttons;



    public DevelopmentCard(String type,Board parent,int id){
        this.type = type;
        this.large = true;
        this.parent = parent;
        this.id = id;
        this.inPlayerDeck = !false;
        this.buttons = new ArrayList<Button>();
        if(type.equals("Knight")) {
            this.turnOfPurchase = true;
        } else{
            this.turnOfPurchase = false;
        }
        setupButtons();



    }


    public void setupButtons(){
        //play card button:
        int playCardWidth = Button.widthEstimate("Play This Card",parent);
        Button playCard = new Button(playCardWidth,40,"Wait One Turn", parent);
        playCard.curveSize = 5;
        buttons.add(playCard);
        //back button:
        int backWidth = Button.widthEstimate("Back",parent);
        Button back = new Button(backWidth,40,"Back",parent);
        back.color = new int[]{198, 40, 40};
        back.curveSize = 5;
        buttons.add(back);
        //next button:
        PImage icon = parent.loadImage("assets/developmentCards/next.png");
        Button next = new Button(icon,parent);
        buttons.add(next);
        //prev button:
        PImage icon2 = parent.loadImage("assets/developmentCards/prev.png");
        Button prev = new Button(icon2,parent);
        buttons.add(prev);



    }

    public void display(){
        logo = parent.model.getMenus().getDevDeck().getDevImages()[0];
        ruleText = "When you play this card, you move the robber and steal a resource from " +
                "the owner of an adjacent settlement or city. Note: cannot be played on the turn of purchase";
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
            //purchase text
            if(!isInPlayerDeck()){
                parent.fill(255);
                parent.textAlign(parent.CENTER);
                parent.text("You Recieve 1 x " + type, parent.SCREEN_WIDTH/2, startY - 10);
            }
            //left card
            parent.fill(0,0,0,30);
            parent.rect(startX - (cardWidth/2) - 10+2, startY + 30 +2,cardWidth,cardHeight,15,15,15,15);
            parent.fill(26, 35, 126);
            parent.rect(startX - (cardWidth/2) - 10, startY + 30,cardWidth,cardHeight,15,15,15,15);
            parent.fill(40, 53, 147);
            parent.rect(startX - (cardWidth/2) - 3, startY + 37,cardWidth-14,cardHeight-14,15,15,15,15);
            parent.image(parent.model.getMenus().getDevDeck().getDevImages()[5],
                    startX - 10 - 100, startY + 37 + (cardHeight / 2) - 100);
            //right card
            parent.fill(0,0,0,30);
            parent.rect(startX + (cardWidth/2) + 10+2, startY + 30 +2,cardWidth,cardHeight,15,15,15,15);
            parent.fill(26, 35, 126);
            parent.rect(startX + (cardWidth/2) + 10, startY + 30,cardWidth,cardHeight,15,15,15,15);
            parent.fill(40, 53, 147);
            parent.rect(startX + (cardWidth/2) + 17, startY + 37,cardWidth-14,cardHeight-14,15,15,15,15);
            parent.image(parent.model.getMenus().getDevDeck().getDevImages()[5],
                    startX + cardWidth -90, startY + 37 + (cardHeight/2) - 100);
            //main card
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
            parent.text(ruleText, startX + 27, startY + (cardHeight/2) + 40, cardWidth - 44, textHeight -20);
            if(!inPlayerDeck){
                parent.textSize(20);
                int buttonWidth = (int) parent.textWidth("Okay") + 40;
                parent.fill(198, 40, 40);
                parent.rect((parent.SCREEN_WIDTH/2) - buttonWidth/2, startY + cardHeight + 10, buttonWidth, 40,5,5,5,5);
                parent.fill(255);
                parent.textAlign(parent.CENTER,parent.CENTER);
                parent.text("Okay", (parent.SCREEN_WIDTH/2), startY + cardHeight + 10 + 15);

            }
            //Buttons
            if(inPlayerDeck){
                parent.textSize(20);
                int buttonWidth = 0;
                if(!isTurnOfPurchase()) {
                buttonWidth = (int) parent.textWidth("Play This Card") + 40;
                    buttons.get(0).color = new int[]{255,167,38};
                } else {
                    buttonWidth = (int) parent.textWidth("Wait One Turn") + 40;
                    buttons.get(0).color = new int[]{158,158,158};
                }
//                parent.image(parent.model.getMenus().getDevDeck().getDevImages()[6],
//                        (parent.SCREEN_WIDTH/2) - (buttonWidth/2) - 10 - 40,startY + cardHeight + 10  );
                buttons.get(3).setStartX((parent.SCREEN_WIDTH/2) - (buttonWidth/2) - 10 - 40);
                buttons.get(3).setStartY(startY + cardHeight + 10);

                buttons.get(0).setStartY(startY + cardHeight + 10);
                buttons.get(0).setStartX((parent.SCREEN_WIDTH / 2) - buttonWidth / 2);

//                parent.image(parent.model.getMenus().getDevDeck().getDevImages()[7],
//                        (parent.SCREEN_WIDTH/2) + (buttonWidth/2) + 10,startY + cardHeight + 10  );

                buttons.get(2).setStartX((parent.SCREEN_WIDTH/2) + (buttonWidth/2) + 10);
                buttons.get(2).setStartY(startY + cardHeight + 10);

                parent.fill(255);
                parent.textAlign(parent.CENTER,parent.CENTER);
                if(!isTurnOfPurchase()) {
                    buttons.get(0).buttonText = "Play This Card";
                } else{
                    buttons.get(0).buttonText = "Wait One Turn";
                }
                parent.textSize(20);
                buttons.get(0).display();
                buttons.get(1).setStartY(startY + cardHeight + 60);
                buttons.get(1).display();
                buttons.get(2).display();
                buttons.get(3).display();

            }

        }

    }


    /**
     * Add this card to the players deck
     */
    public void addToPlayerDeck(){
        this.inPlayerDeck = true;
        parent.model.getPlayer().addToDeck(this);
    }

    /**
     * Remove this card from the players deck
     * Will result in garbage collection
     */
    public void removeFromPlayerDeck(){
        parent.model.getPlayer().removeFromDeck(this);
        this.setInPlayerDeck(false);
    }


    /**
     * Plays and then discards the card.
     * The play behavior is defined by its rule text
     */
    public void playCard(){

    }



    public boolean isInPlayerDeck() {
        return inPlayerDeck;
    }

    public void setInPlayerDeck(boolean inPlayerDeck) {
        this.inPlayerDeck = inPlayerDeck;
    }

    public void displayButtons(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTurnOfPurchase() {
        return turnOfPurchase;
    }

    public void setTurnOfPurchase(boolean turnOfPurchase) {
        this.turnOfPurchase = turnOfPurchase;
    }


    public void checkButtons(){
        for(int i = 0; i < buttons.size(); i++){
            if(buttons.get(i).checkButton()){
                System.out.println("Button " + i + " pressed");
            }
        }
    }
}
