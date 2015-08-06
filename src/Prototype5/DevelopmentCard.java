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
    private boolean inactive;
    private ArrayList<Button> buttons;
    private boolean justPurchased;
    private boolean background;


    public DevelopmentCard(String type, Board parent, int id) {
        this.type = type;
        this.large = true;
        this.parent = parent;
        this.id = id;
        this.inPlayerDeck = !false;
        this.buttons = new ArrayList<Button>();
        this.background = false;
        if (type.equals("Knight")) {
            this.inactive = true;
        } else {
            this.inactive = false;
        }
        justPurchased = true;
        setupButtons();

    }


    public void setupButtons() {
        // 0 - play card button:
        int playCardWidth = Button.widthEstimate("Play This Card", parent);
        Button playCard = new Button(playCardWidth, 40, "Wait One Turn", parent);
        playCard.curveSize = 5;
        buttons.add(playCard);
        // 1 - back button:
        int backWidth = Button.widthEstimate("Back", parent);
        Button back = new Button(backWidth, 40, "Back", parent);
        back.color = new int[]{198, 40, 40};
        back.curveSize = 5;
        buttons.add(back);
        // 2 - next button:
        PImage icon = parent.loadImage("assets/developmentCards/next.png");
        Button next = new Button(icon, parent);
        buttons.add(next);
        // 3 - prev button:
        PImage icon2 = parent.loadImage("assets/developmentCards/prev.png");
        Button prev = new Button(icon2, parent);
        buttons.add(prev);
        // 4 - okay button
        int okayWidth = Button.widthEstimate("Okay", parent);
        Button okay = new Button(okayWidth, 40, "Okay", parent);
        okay.color = new int[]{76, 175, 80};
        okay.curveSize = 5;
        buttons.add(okay);
        // 5 - buy a new card
        int buyWidth = Button.widthEstimate("Buy Another Card", parent);
        Button buy = new Button(buyWidth, 40, "Buy Another Card", parent);
        buy.color = new int[]{76, 175, 80};
        buttons.add(buy);


    }


    public void displayBackground(boolean left){
        int modX = 0;
        int modY = 30;
        int cardHeight = (parent.SCREEN_HEIGHT / 2);
        int cardWidth = (int) (cardHeight / (1.4));
        int startX = (parent.SCREEN_WIDTH / 2) - (cardWidth / 2);
        int startY = (parent.SCREEN_HEIGHT / 2) - (cardHeight / 2) - 15;
        if(left){
            modX = ((cardWidth / 2) + 10) * -1;
        }else{
            modX =  (cardWidth / 2) + 10;
        }
        startX = startX + modX;
        startY = startY + modY;
        //main card
        parent.fill(0, 0, 0, 30);
        parent.rect(startX + 2, startY + 2, cardWidth, cardHeight, 15, 15, 15, 15);
        parent.fill(227, 226, 213);
        parent.rect(startX, startY, cardWidth, cardHeight, 15, 15, 15, 15);

        parent.fill(62, 39, 35);
        parent.rect(startX + 7, startY + 7, cardWidth - 14, cardHeight - 14, 15, 15, 15, 15);
        //icon
        int boxHeight = ((cardHeight - 14) / 2) - 20;
        int boxStartY = startY + 17;
        int boxStartX = startX + 17;
        int boxWidth = ((cardWidth - 14)) - 20;
        parent.fill(198, 40, 40);
        parent.rect(boxStartX, boxStartY, boxWidth, boxHeight, 10, 10, 10, 10);
        parent.image(logo, startX + (cardWidth / 2) - 80, startY + 17 + (boxHeight / 2) - 80);
        //text
        parent.textAlign(parent.CENTER);
        parent.textSize(30);
        parent.fill(255);
        parent.text(type, parent.SCREEN_WIDTH / 2, startY + (cardHeight / 2) + 30);
        int textHeight = startY + cardHeight - (startY + (cardHeight / 2) + 40);
        parent.textSize(12);
        parent.textAlign(parent.CENTER, parent.CENTER);
        parent.text(ruleText, startX + 27, startY + (cardHeight / 2) + 40, cardWidth - 44, textHeight - 20);
        parent.fill(0, 0, 0, 80);
        parent.rect(startX, startY, cardWidth, cardHeight, 15, 15, 15, 15);

    }

    public void display() {
        logo = parent.model.getMenus().getDevDeck().getDevImages()[0];
        ruleText = "When you play this card, you move the robber and steal a resource from " +
                "the owner of an adjacent settlement or city. Note: cannot be played on the turn of purchase";
        if (type.equals("Monopoly")) {
            logo = parent.model.getMenus().getDevDeck().getDevImages()[1];
            ruleText = "When you play this card, " +
                    "announce one type of resource. " +
                    "All other players must give you their " +
                    "entire supply of that resource.";
        }
        if (type.equals("Year of Plenty")) {
            logo = parent.model.getMenus().getDevDeck().getDevImages()[2];
            ruleText = "When you play this card, you can select 2 " +
                    "resources of your choice from the bank";
        }
        if (type.equals("Victory Point")) {
            logo = parent.model.getMenus().getDevDeck().getDevImages()[3];
            ruleText = "You gain one Victory Point!";
        }
        if (type.equals("Road Building")) {
            logo = parent.model.getMenus().getDevDeck().getDevImages()[4];
            ruleText = "When you play this card, you can build two roads free of charge";
        }
        if (this.large) {

            parent.stroke(0, 0, 0, 0);
            int cardHeight = (parent.SCREEN_HEIGHT / 2);
            int cardWidth = (int) (cardHeight / (1.4));
            int startX = (parent.SCREEN_WIDTH / 2) - (cardWidth / 2);
            int startY = (parent.SCREEN_HEIGHT / 2) - (cardHeight / 2) - 15;
            //purchase text
            if (justPurchased) {
                parent.fill(255);
                parent.textAlign(parent.CENTER);
                parent.textSize(20);
                parent.text("You Recieve 1 x " + type, parent.SCREEN_WIDTH / 2, startY - 10);
            }
            //main card
            parent.fill(0, 0, 0, 30);
            parent.rect(startX + 2, startY + 2, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(227, 226, 213);
            parent.rect(startX, startY, cardWidth, cardHeight, 15, 15, 15, 15);
            parent.fill(62, 39, 35);
            parent.rect(startX + 7, startY + 7, cardWidth - 14, cardHeight - 14, 15, 15, 15, 15);
            //icon
            int boxHeight = ((cardHeight - 14) / 2) - 20;
            int boxStartY = startY + 17;
            int boxStartX = startX + 17;
            int boxWidth = ((cardWidth - 14)) - 20;
            parent.fill(198, 40, 40);
            parent.rect(boxStartX, boxStartY, boxWidth, boxHeight, 10, 10, 10, 10);
            parent.image(logo, startX + (cardWidth / 2) - 80, startY + 17 + (boxHeight / 2) - 80);
            //text
            parent.textAlign(parent.CENTER);
            parent.textSize(30);
            parent.fill(255);
            parent.text(type  + " " + id, parent.SCREEN_WIDTH / 2, startY + (cardHeight / 2) + 30);
            int textHeight = startY + cardHeight - (startY + (cardHeight / 2) + 40);
            parent.textSize(12);
            parent.textAlign(parent.CENTER, parent.CENTER);
            parent.text(ruleText, startX + 27, startY + (cardHeight / 2) + 40, cardWidth - 44, textHeight - 20);
            if (!inPlayerDeck) {
                parent.textSize(20);
                int buttonWidth = (int) parent.textWidth("Okay") + 40;
                parent.fill(198, 40, 40);
                parent.rect((parent.SCREEN_WIDTH / 2) - buttonWidth / 2, startY + cardHeight + 10, buttonWidth, 40, 5, 5, 5, 5);
                parent.fill(255);
                parent.textAlign(parent.CENTER, parent.CENTER);
                parent.text("Okay", (parent.SCREEN_WIDTH / 2), startY + cardHeight + 10 + 15);
            }
                //Buttons
                if (justPurchased) {
                    parent.textSize(20);
                    buttons.get(4).setStartY(startY + cardHeight + 10);
                    buttons.get(4).display();

                }
                if (inPlayerDeck && !justPurchased) {
                    parent.textSize(20);
                    int buttonWidth = 0;
                    if (!isInactive()) {
                        buttonWidth = (int) parent.textWidth("Play This Card") + 40;
                        buttons.get(0).color = new int[]{255, 167, 38};
                    } else {
                        buttonWidth = (int) parent.textWidth("Wait One Turn") + 40;
                        buttons.get(0).color = new int[]{158, 158, 158};
                    }
                    buttons.get(3).setStartX((parent.SCREEN_WIDTH / 2) - (buttonWidth / 2) - 10 - 40);
                    buttons.get(3).setStartY(startY + cardHeight + 10);
                    buttons.get(0).setStartY(startY + cardHeight + 10);
                    buttons.get(0).setStartX((parent.SCREEN_WIDTH / 2) - buttonWidth / 2);
                    buttons.get(2).setStartX((parent.SCREEN_WIDTH / 2) + (buttonWidth / 2) + 10);
                    buttons.get(2).setStartY(startY + cardHeight + 10);

                    parent.fill(255);
                    parent.textAlign(parent.CENTER, parent.CENTER);
                    if (!isInactive()) {
                        buttons.get(0).buttonText = "Play This Card";
                    } else {
                        buttons.get(0).buttonText = "Wait One Turn";
                    }
                    parent.textSize(20);
                    buttons.get(0).display();
                    buttons.get(5).setStartY(startY + cardHeight + 60);
                    buttons.get(1).setStartY(startY + cardHeight + 110);
                    buttons.get(1).display();
                    buttons.get(5).display();
                    buttons.get(2).display();
                    buttons.get(3).display();
//                StringBuffer text = new StringBuffer();
//                ArrayList<DevelopmentCard> playerDeck = parent.model.getPlayer().getPlayerDeck();
//                text.append("Index: " + parent.model.getMenus().getDeckScreen().getSelectionIndex() + "\n");
//                for(int i = 0; i < playerDeck.size(); i++){
//                    text.append( i + ": " + playerDeck.get(i).getId() + "\n");
//                }
//                parent.textSize(10);
//                parent.text(text.toString(), 100, parent.SCREEN_HEIGHT);
                }

            }
        }




    /**
     * Add this card to the players deck
     */
    public void addToPlayerDeck() {
        this.inPlayerDeck = true;
        parent.model.getPlayer().addToDeck(this);
    }


    /**
     * Plays and then discards the card.
     * The play behavior is defined by its rule text
     */
    public void playCard() {

    }


    public boolean isInPlayerDeck() {
        return inPlayerDeck;
    }

    public void setInPlayerDeck(boolean inPlayerDeck) {
        this.inPlayerDeck = inPlayerDeck;
    }

    public void displayButtons() {

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

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }


    public int checkButtons() {
        //okay button
        if(justPurchased){
        if (buttons.get(4).checkButton()) {
            this.justPurchased = false;
            return 0;
        }
        } else {
            //new card button
            if (buttons.get(5).checkButton()) {
                parent.model.getMenus().getDevDeck().getCard();
                return 0;
            }
            //next button
            if (buttons.get(2).checkButton()) {
                return 1;

            }
            //prev button
            if (buttons.get(3).checkButton()) {
                return 2;
            }
            //back button
            if (buttons.get(1).checkButton()) {
                return 3;
            }
            //play button
            if(!inactive && buttons.get(0).checkButton()){
                return 4;
            }

            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).checkButton()) {
                    System.out.println("Button " + i + " pressed");
                }
            }
        }
        return 0;
    }

    public boolean isBackground() {
        return background;
    }

    public void setBackground(boolean background) {
        this.background = background;
    }


    /**
     * Displays a medium version of the card.
     * Valid types are:
     *  - knight
     *  - monopoly
     *  - freeroad
     *  - yearofplenty
     *  - victorypoint
     * @param type the type of card to display
     * @param parent the board to draw on
     */
    public static void displayMedium(String type,Board parent){
        PImage medLogo = parent.loadImage("assets/developmentCards/" + type + "MD.png");
        parent.fill(0,0,0,30);
        parent.rect(parent.SCREEN_WIDTH - 152+2, parent.SCREEN_HEIGHT - 300+2, 142,200,5,5,5,5);
        parent.fill(227, 226, 213);
        parent.rect(parent.SCREEN_WIDTH - 152, parent.SCREEN_HEIGHT - 300, 142,200,5,5,5,5);
        parent.fill(62, 39, 35);
        parent.rect(parent.SCREEN_WIDTH - 152 +4, parent.SCREEN_HEIGHT - 300 + 4, 142 -8,200 -8,5,5,5,5);
        parent.image(medLogo,parent.SCREEN_WIDTH - 152 +(142/2) - (medLogo.width/2),  parent.SCREEN_HEIGHT - 200 - (medLogo.height/2) );


    }
}
