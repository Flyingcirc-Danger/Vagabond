package Prototype5;





import java.util.ArrayList;
import java.util.HashMap;


/**
 * A class containing all the information
 * for displaying the trade floor
 * Created by Tom_Bryant on 7/21/15.
 */
public class TradeFloor {

    private Board parent;
    private PlayerTradeCard[] trades;
    private PlayerTradeCard client;
    private int[][] plusButtons;
    private int[][] minusButtons;
    private int offerHighlight;
    private int wantHighlight;
    private PlayerTradeCard playerNeg;// the player who currently you're countering.
    private boolean tradeAlert;
    private int tradeAlertPlayer; //the name of the player whose trade has come in.
    private int[] acceptCoords;
    private boolean acceptAlert; //boolean to display the accept trade alert
    private boolean openTrade; //boolean to check whether this trade was initiated to all.
    private boolean rejectAlert; //boolean to display the rejection trade alert

    public TradeFloor(Board parent) {
        this.parent = parent;
        this.plusButtons = new int[10][4];
        this.minusButtons = new int[10][4];
        this.offerHighlight = 0;
        this.wantHighlight = 0;
        this.playerNeg = client;
        this.tradeAlert = false;
        this.tradeAlertPlayer = 0;
        this.acceptCoords = new int[4];
        this.openTrade = false;
        this.rejectAlert = false;


    }

    public void buildTradeFloor() {
        this.trades = new PlayerTradeCard[parent.model.getPlayerList().size()];
        initButtons();
        initCards();
        initClient();
    }

    /**
     * Retrieve the player in this trade floor
     * whos id matches the id. runs in O(n) but
     * there can only be 4 players so it's pretty fast.
     * Throws a null pointer if the player cant be found.
     * @param id the id you're searching for.
     * @return the playercard matching the provided id.
     */
    public PlayerTradeCard getPlayerForTrade(int id){
        for(int i = 0; i < trades.length; i++){
            if(trades[i].getId() == id){
                return trades[i];
            }
        }
        throw new NullPointerException("Cannot find player with id " + id);

    }

    public void display(){
        parent.background(121, 85, 72);
        for(int i = 0; i < trades.length; i++){
            trades[i].displayCard();
        }

        displayClientFloor();
        if(tradeAlert){
            displayTradeAlert();
        }
        if(acceptAlert){
            displayAccept(playerNeg);
        }
        if(rejectAlert){
           displayReject();
        }

    }

    /**
     * Initializes the other players cards (not the current client)
     */
    public void initCards(){
        int spacer = (parent.SCREEN_WIDTH - (trades.length * 150)) / (trades.length + 1);
        int runningStartX = 0 + spacer;
        ArrayList<Integer> playerList = parent.model.getPlayerList();

        for(int i = 0; i < trades.length; i++){
            trades[i] = new PlayerTradeCard(parent,playerList.get(i), runningStartX,
                    (parent.SCREEN_HEIGHT/4) - (280/2) - 40);
            runningStartX = runningStartX + (150 + spacer);
        }

    }


    /**
     * Initializes the clients cards
     */
    public void initClient(){
        this.client = new PlayerTradeCard(parent, parent.model.getPlayer().getId(), (parent.SCREEN_WIDTH/2) - (150 /2),
                ((parent.SCREEN_HEIGHT/4) * 3) - (280/2));
        this.tradeAlertPlayer = client.getId();
    }


    /**
     * Displays the client trading menu.
     * Outgoing and Incoming boxes.
     */
    public void displayClientFloor(){
        //offer


        int offerStartX = (parent.SCREEN_WIDTH /4) - 200;
        int startY = ((parent.SCREEN_HEIGHT/4) *3) - (100) ;
        parent.fill(40, 53, 157);
        parent.rect(offerStartX,startY,400,200,10,10,10,10);
        if(offerHighlight > 0){
            if(offerHighlight > 94) {
                parent.fill(0,255,34);
                offerHighlight = offerHighlight - 3;
            } else {
                parent.fill(0, 255, 34, offerHighlight);
                offerHighlight = offerHighlight - 10;
            }
            parent.rect(offerStartX, startY, 400, 200, 10, 10, 10, 10);
        }
        parent.textSize(20);
        parent.fill(255);
        parent.text("Outgoing (Selling)", offerStartX + 200 - (parent.textWidth("Outgoing (Selling)")/2), startY + 25);

        drawOffer(offerStartX, startY);

        //buttons


        //want
        parent.fill(40, 53, 157);
        int wantStartX = ((parent.SCREEN_WIDTH /4)*3) - 200;
        parent.rect(wantStartX,startY,400,200,10,10,10,10);
        if(wantHighlight > 0){
            if(wantHighlight > 94) {
                parent.fill(0,255,34);
                wantHighlight = wantHighlight - 3;
            } else {
                parent.fill(0, 255, 34, wantHighlight);
                wantHighlight = wantHighlight - 10;
            }
            parent.rect(wantStartX,startY,400,200,10,10,10,10);
        }
        parent.textSize(20);
        parent.fill(255);
        parent.text("Incoming (Buying)", wantStartX + 200 - (parent.textWidth("Incoming (Buying)")/2), startY + 25);
        drawWant(wantStartX,startY);
        clientButtons(offerStartX + 400, wantStartX, startY);
        parent.fill(255);
        parent.textSize(12);
        parent.text("Pressing \"Back\" will end all trade negotiations", (parent.SCREEN_WIDTH/2) -
                (parent.textWidth("Pressing \"Back\" will end all trade negotiations")/2), startY + 225);
        parent.line((parent.SCREEN_WIDTH/2) -
                (parent.textWidth("Pressing \"Back\" will end all trade negotiations")/2), startY + 227,
                (parent.SCREEN_WIDTH/2) + (parent.textWidth("Pressing \"Back\" will end all trade negotiations")/2),
                startY + 227);
    }

    private void clientButtons(int startX, int endX, int startY){
        parent.textSize(20);
        int width = (int)parent.textWidth(" Propose ");
        int butY = (startY + 100) - 80/2;
        int butX = startX + ((endX - startX)/2) - (width/2);

        parent.fill(255,160,0);
        parent.rect(butX, butY,width,30,5,5,5,5);
        parent.fill(255);
        parent.textSize(16);
        parent.text("Propose", butX + (width/2) - (parent.textWidth("Propose")/2), (butY + 15) + 7);
        parent.fill(0, 0, 0, 30);
        parent.rect(butX+2, butY+2,width,30,5,5,5,5);
        parent.fill(255,160,0);
        parent.rect(butX, butY + 40,width,30,5,5,5,5);
        parent.fill(255);
        parent.textSize(16);
        parent.text("Back", butX + (width/2) - (parent.textWidth("Back")/2), (butY + 55) + 7);
        parent.fill(0,0,0,30);
        parent.rect(butX +2, butY + 42,width,32,5,5,5,5);



    }

    /**
     * Constructs a counter resource icon
     * @param id the id of this resource
     * @param startX the Xstart of this icon
     * @param startY the Ystart of this icon
     * @param offer true if it's in the offer side of the board.
     */
    public void counterResource(int id,int startX, int startY,boolean offer){
        parent.fill(27,37,110);
        parent.rect(startX, startY, 110,65,5,5,5,5);
        parent.image(parent.images[17], startX+5 , startY + 5);
        parent.image(parent.images[18], startX+5, startY + 35);
        parent.image(parent.images[id+11], startX +30, startY +5 );
        parent.fill(0);
        parent.rect(startX + 80, startY + 20, 20, 20,5,5,5,5);
        parent.fill(255);
        parent.textSize(12);
        if(offer) {
            if(client.getOffers().containsKey(id)) {
                parent.text(client.getOffers().get(id), startX + 80 + 7, startY + 30 + 6);
            } else{
                parent.text("0", startX + 80 + 7, startY + 30 + 6);
            }
        } else{
            if(client.getWants().containsKey(id)) {
                parent.text(client.getWants().get(id), startX + 80 + 7, startY + 30 + 6);
            } else{
                parent.text("0", startX + 80 + 7, startY + 30 + 6);
            }
        }

    }

    /**
     * Draws all of the offer icons.
     * @param offerStartX
     * @param offerStartY
     */
    public void drawOffer(int offerStartX,int offerStartY){
        int startY = offerStartY + 100 - (130/2);
        int startX = offerStartX + 200 - (350/2);
        counterResource(1, startX, startY + 10,true);
        counterResource(2,startX + 120, startY + 10,true);
        counterResource(3,startX + 240, startY + 10,true);
        int startXR2 = offerStartX + 200 - (240/2);
        counterResource(4,startXR2, startY + 80,true);
        counterResource(5,startXR2 + 120, startY + 80,true);

    }

    /**
     * Draws all of the want icons
     * @param wantStartX
     * @param wantStartY
     */
    public void drawWant(int wantStartX,int wantStartY){
        int startY = wantStartY + 100 - (130/2);
        int startX = wantStartX + 200 - (350/2);
        counterResource(1,startX, startY + 10,false);
        counterResource(2,startX + 120, startY + 10,false);
        counterResource(3,startX + 240, startY + 10,false);
        int startXR2 = wantStartX + 200 - (240/2);
        counterResource(4,startXR2, startY + 80,false);
        counterResource(5,startXR2 + 120, startY + 80,false);

    }

    public void initButtons(){
        int startX = ((parent.SCREEN_WIDTH /4) - 200) +(200 - (350/2)) + 5;
        int startY = (((parent.SCREEN_HEIGHT/4) *3) - (100)) + (100 - (130/2)) + 10;
        plusButtons[0] = new int[]{startX,startY+5,25,25};
        minusButtons[0] = new int[]{startX,startY + 40,25,25};
        startX = startX + 120;
        plusButtons[1] = new int[]{startX,startY+5,25,25};
        minusButtons[1] = new int[]{startX,startY + 40,25,25};
        startX = startX + 120;
        plusButtons[2] = new int[]{startX,startY+5,25,25};
        minusButtons[2] = new int[]{startX,startY + 40,25,25};

        startX =  ((parent.SCREEN_WIDTH /4) - 200) + (200 - (240/2)) + 5;

        startY = startY + 75;
        plusButtons[3] = new int[]{startX,startY,25,25};
        minusButtons[3] = new int[]{startX,startY + 30,25,25};
        startX =  startX + 120;
        plusButtons[4] = new int[]{startX,startY,25,25};
        minusButtons[4] = new int[]{startX,startY + 30,25,25};

        startX =  (((parent.SCREEN_WIDTH /4)*3) - 200) + 200 - (350/2);
        startY = (((parent.SCREEN_HEIGHT/4) *3) - (100)) + (100 - (130/2)) + 10;
        plusButtons[5] = new int[]{startX,startY+5,25,25};
        minusButtons[5] = new int[]{startX,startY + 40,25,25};
        startX = startX + 120;
        plusButtons[6] = new int[]{startX,startY+5,25,25};
        minusButtons[6] = new int[]{startX,startY + 40,25,25};
        startX = startX + 120;
        plusButtons[7] = new int[]{startX,startY+5,25,25};
        minusButtons[7] = new int[]{startX,startY + 40,25,25};

        startX =  (((parent.SCREEN_WIDTH /4)*3) - 200)+ 200 - (240/2) + 5;



        startY = startY + 75;
        plusButtons[8] = new int[]{startX,startY,25,25};
        minusButtons[8] = new int[]{startX,startY + 30,25,25};
        startX =  startX + 120;
        plusButtons[9] = new int[]{startX,startY,25,25};
        minusButtons[9] = new int[]{startX,startY + 30,25,25};

    }


    /**
     * Checks the buttons based on the display mode
     * 0 = check buttons when displaying the trade menu
     * 1 = check buttons when not displaying the trade menu
     * @param mode
     */
    public void checkButtons(int mode){
        if(mode == 0) {
            for (PlayerTradeCard trade : trades) {
                int tradeButton = trade.checkButtons();
                if (tradeButton > -1) {
                    if (tradeButton == 0) {
                        playerNeg.setActiveOffer(false);
                        trade.setActiveOffer(true);
                        playerNeg = trade;
                        //move offer to client side.
                        if (trade.getOffers().size() > 0) {
                            client.setWants(trade.getOffers());
                            offerHighlight = 100;
                        }
                        if (trade.getWants().size() > 0) {
                            client.setOffers(trade.getWants());
                            wantHighlight = 100;
                        }

                    } else {
                        playerNeg = trade;//accepted trader becomes the playerNeg (playerNegotiatedWith).
                        if(!isOpenTrade()) {
                            //perform the trade
                            parent.model.setTradeManifest(ObjectParser.parseAccept(this, true));
                        } else{
                            playerNeg.setActiveOffer(false);
                            trade.setActiveOffer(true);
                            if (trade.getOffers().size() > 0) {
                                client.setWants(trade.getOffers());
                                offerHighlight = 100;
                            }
                            if (trade.getWants().size() > 0) {
                                client.setOffers(trade.getWants());
                                wantHighlight = 100;
                            }
                            //if you wish to accept an open trade, return the offer exactly
                            //but first check that you have all the resources
                            int matchesWant = 0;
                            int wants = trade.getWants().size();
                            for(int id : trade.getWants().keySet()){
                                if(parent.model.getPlayer().getAllResource(id) >= trade.getWants().get(id)){
                                    matchesWant++;
                                }
                            }
                            if(matchesWant == trade.getOffers().size()){
                                parent.model.setTradeManifest(ObjectParser.parseTrade(client, Integer.toString(playerNeg.getId()), false,false));
                            } else{
                                System.out.println("You do not have enough resources for this trade"); //TODO: Add actual trade warning 1
                            }
                        }
                    }
                    return;
                }
            }

            int startX = ((parent.SCREEN_WIDTH / 4) - 200) + 400;
            int endX = ((parent.SCREEN_WIDTH / 4) * 3) - 200;
            ;
            int startY = ((parent.SCREEN_HEIGHT / 4) * 3) - (100);
            parent.textSize(20);
            int width = (int) parent.textWidth(" Propose ");
            int butY = (startY + 100) - 80 / 2;
            int butX = startX + ((endX - startX) / 2) - (width / 2);


            parent.fill(0);
            if (Listeners.overRect(butX, butY, ((int) parent.textWidth(" Propose ")), 30, parent)) {
                if (playerNeg == client) {
                    //if not countering a trade
                    if(affordTradeWants(client)) {
                        parent.model.setTradeManifest(ObjectParser.parseTrade(client, "all", false, false));
                    } else{
                        System.out.println("You Can't Afford That Trade"); //TODO: Add actual trade warning 2
                    }
                } else {
                    if(affordTradeWants(client)) {
                        //if countering a trade the playerNeg will be set.
                        parent.model.setTradeManifest(ObjectParser.parseTrade(client, Integer.toString(playerNeg.getId()), false, false));
                    } //TODO: Add actual trade warning 3
                }
            }
            //back button
            if (Listeners.overRect(butX, butY + 40, ((int) parent.textWidth(" Propose ")), 30, parent)) {
                parent.model.setTradeManifest(ObjectParser.parseTrade(client,Integer.toString(playerNeg.getId()),false,true));
                parent.model.setDisplayMode(0);
                client.setOfferRejected(true);
            }
            PlayerInfo player = parent.model.getPlayer();
            for (int i = 0; i < plusButtons.length; i++) {
                int[] coords = plusButtons[i];
                if (Listeners.overRect(coords[0], coords[1], coords[2], coords[3], parent)) {

                    if (i == 0) {
                            client.addGrain(true);
                    }
                    if (i == 1) {
                            client.addOre(true);
                        }
                    if (i == 2) {
                            client.addWool(true);

                    }
                    if (i == 3) {
                            client.addBrick(true);

                    }
                    if (i == 4) {
                            client.addLog(true);
                    }
                    if (i == 5) {
                        client.addGrain(false);
                    }
                    if (i == 6) {
                        client.addOre(false);
                    }
                    if (i == 7) {
                        client.addWool(false);
                    }
                    if (i == 8) {
                        client.addBrick(false);
                    }
                    if (i == 9) {
                        client.addLog(false);
                    }
                }
            }
            for (int i = 0; i < minusButtons.length; i++) {
                int[] coords = minusButtons[i];
                if (Listeners.overRect(coords[0], coords[1], coords[2], coords[3], parent)) {
                    if (i == 0) {
                            client.subGrain(true);
                    }
                    if (i == 1) {
                            client.subOre(true);
                    }
                    if (i == 2) {
                            client.subWool(true);
                    }
                    if (i == 3) {
                            client.subBrick(true);
                    }
                    if (i == 4) {
                            client.subLog(true);
                    }
                    if (i == 5) {
                        client.subGrain(false);
                    }
                    if (i == 6) {
                            client.subOre(false);
                    }
                    if (i == 7) {
                            client.subWool(false);
                    }
                    if (i == 8) {
                            client.subBrick(false);
                    }
                    if (i == 9) {
                            client.subLog(false);
                    }
                }
            }

            if(tradeAlert) {
                if (Listeners.overRect((parent.SCREEN_WIDTH / 2) - 30, (parent.SCREEN_HEIGHT / 2) + 10, 60, 30, parent)) {
                    toggleTradeAlert(false);
                    parent.model.setDisplayMode(8);
                }
            }
            if(rejectAlert){
                if (Listeners.overRect((parent.SCREEN_WIDTH / 2) - 30, (parent.SCREEN_HEIGHT / 2) + 10, 60, 30, parent)) {
                    setRejectAlert(false);
                    parent.model.setDisplayMode(0);
                    resetTrades();
                }


            }
            if(acceptAlert){
                if(Listeners.overRect(acceptCoords[0], acceptCoords[1], acceptCoords[2], acceptCoords[3], parent)){
                    acceptAlert = false;
                    parent.model.setDisplayMode(0);
                    resetTrades();
                }
            }
        }
        if(mode == 1){
            if (Listeners.overRect((parent.SCREEN_WIDTH / 2) - 30, (parent.SCREEN_HEIGHT / 2) + 10, 60, 30, parent)) {
                toggleTradeAlert(false);
                parent.model.setDisplayMode(8);
            }
            if(acceptAlert){
                if(Listeners.overRect(acceptCoords[0], acceptCoords[1], acceptCoords[2], acceptCoords[3], parent)){
                    acceptAlert = false;
                    parent.model.setDisplayMode(0);
                }
            }
        }

    }

    /**
     * Performs a trade between the current player
     * and the trade partner.
     * @param partner
     */
    public void performTrade(PlayerTradeCard partner){
        playerNeg = partner;

        PlayerInfo player = parent.model.getPlayer();
        HashMap<Integer,Integer> offers = partner.getOffers();
        HashMap<Integer,Integer> wants = partner.getWants();
        for(int val : offers.keySet()){
            if(val == 1){
                player.addGrain(offers.get(val));
                System.out.println("add " + offers.get(val) + " grain");
            }
            if(val == 2){
                player.addOre(offers.get(val));
            }
            if(val == 3){
                player.addWool(offers.get(val));
            }
            if(val == 4){
                player.addBrick(offers.get(val));
            }
            if(val == 5){
                player.addLogs(offers.get(val));
            }
        }
        for(int val : wants.keySet()){
            if(val == 1){
                player.subtractGrain(wants.get(val));
            }
            if(val == 2){
                player.subtractOre(wants.get(val));
            }
            if(val == 3){
                player.subtractWool(wants.get(val));
            }
            if(val == 4){
                player.subtractBrick(wants.get(val));
            }
            if(val == 5){
                player.subtractLogs(wants.get(val));
            }
        }
        this.acceptAlert = true;

    }

    /**
     * Resets the trade board for a new round of trades
     */
    public void newTurn(){
        playerNeg = client;
        for(PlayerTradeCard trade : trades){
            trade.setOffers(new HashMap<Integer, Integer>());
            trade.setWants(new HashMap<Integer, Integer>());
        }

    }

    public PlayerTradeCard getPlayerNeg() {
        return playerNeg;
    }

    public void setPlayerNeg(PlayerTradeCard playerNeg) {
        this.playerNeg = playerNeg;
    }

    public PlayerTradeCard getClient() {
        return client;
    }

    public void setClient(PlayerTradeCard client) {
        this.client = client;
    }

    public int getTradeAlertPlayer() {
        return tradeAlertPlayer;
    }

    public void setTradeAlertPlayer(int tradeAlertPlayer) {
        this.tradeAlertPlayer = tradeAlertPlayer;
    }

    public boolean isOpenTrade() {
        return openTrade;
    }

    public void setOpenTrade(boolean openTrade) {
        this.openTrade = openTrade;
    }

    public boolean isRejectAlert() {
        return rejectAlert;
    }

    public void setRejectAlert(boolean rejectAlert) {
        this.rejectAlert = rejectAlert;
    }

    public void displayTradeAlert(){
        parent.textSize(20);
        parent.fill(198,40,40);
        parent.stroke(0,0,0,0);
        int width = (int) parent.textWidth("Trade offer received from player " + tradeAlertPlayer);

        int height = 80;
        parent.rect((parent.SCREEN_WIDTH/2) - ((20+width)/2), (parent.SCREEN_HEIGHT/2) - 30,width+40,height,10,10,10,10);
        parent.fill(0,0,0,30);
        parent.rect((parent.SCREEN_WIDTH/2) - ((20+width)/2)+2, (parent.SCREEN_HEIGHT/2) - 30 + 2,width+40,height,10,10,10,10);
        parent.fill(255);
        parent.text("Trade offer received from player " + tradeAlertPlayer, (parent.SCREEN_WIDTH/2) - ((width/2) - 10), (parent.SCREEN_HEIGHT/2) - 5 );
        parent.fill(198,40,40);
        parent.rect((parent.SCREEN_WIDTH/2) - 30,(parent.SCREEN_HEIGHT/2) + 10, 60,30,5,5,5,5 );
        parent.fill(255);
        parent.text("OK", (parent.SCREEN_WIDTH/2) - (parent.textWidth("OK")/2),(parent.SCREEN_HEIGHT/2) + 30);
    }

    /**
     * Toggles the display of the trade alert.
     */
    public void toggleTradeAlert(boolean toggle){
        tradeAlert = toggle;
    }

    /**
     * Checks to see if the trade alert is toggled
     * @return
     */
    public boolean isTradeAlert(){
        return this.tradeAlert;
    }

    /**
     * Displays the outcome of a succesful trade;
     * @param trader
     */
    public void displayAccept(PlayerTradeCard trader){
        parent.fill(255);
        parent.text("TRADED WITH: " + trader.getId() + " ",20,20);
        int receiveNo = trader.getOffers().size() * 34;
        int loseNo = trader.getWants().size() * 34;
        parent.textSize(20);
        int width =  (int)(120 + parent.textWidth("Trade Successful"));
        int height = 104 + receiveNo + loseNo ;
        int startX = (parent.SCREEN_WIDTH/2) - (width/2);
        int startY = (parent.SCREEN_HEIGHT/2) - (height/2);
        int runningTotal = startY;
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect(startX,startY, width, height,5,5,5,5);
        parent.fill(0,0,0,30);
        parent.rect(startX+2,startY+2, width, height,5,5,5,5);
        parent.image(parent.images[19],startX + 10,startY +5);
        parent.fill(255);
        parent.text("Trade Successful", startX + ((width/2) - parent.textWidth("Trade Successful")/2), startY + 25);
        parent.image(parent.images[19],startX + (width - 40),startY+5);
        parent.textSize(12);
        parent.text("You receive:", startX + ((width/2) - parent.textWidth("You receive:")/2), startY + 45);
        parent.stroke(255);
        parent.line ((startX + ((width/2) - parent.textWidth("You receive:")/2)), startY + 46,
                (startX + ((width/2) + parent.textWidth("You receive:")/2)), startY + 46);

        runningTotal += 50;
        for(int offer : trader.getOffers().keySet()){
            parent.image(parent.images[offer],startX + ((width/4)),runningTotal);
            runningTotal += 16;
            String resource = "Wheat";
            if(offer == 2){
                resource = "Ore";
            }
            if(offer == 3){
                resource = "Wool";
            }
            if(offer == 4){
                resource = "Brick";
            }
            if(offer == 5){
                resource = "Logs";
            }
            parent.text(trader.getOffers().get(offer) +" x " + resource, startX + (width/4 ) + 40,runningTotal);
            runningTotal += 14;
            }
            runningTotal += 14;
        parent.text("You lose:", startX + ((width/2) - parent.textWidth("You lose:")/2),runningTotal);
        parent.stroke(255);
        parent.line ((startX + ((width/2) - parent.textWidth("You lose:")/2)), runningTotal + 1,
                (startX + ((width/2) + parent.textWidth("You lose:")/2)),runningTotal + 1);
        runningTotal+= 5;
        for (int want : trader.getWants().keySet()){
            parent.image(parent.images[want],startX + ((width/4)),runningTotal);
            runningTotal += 16;
            String resource = "Wheat";
            if(want == 2){
                resource = "Ore";
            }
            if(want == 3){
                resource = "Wool";
            }
            if(want == 4){
                resource = "Brick";
            }
            if(want == 5){
                resource = "Logs";
            }
            parent.text(trader.getWants().get(want) + " x " + resource, startX + (width/4 ) + 40,runningTotal);
            runningTotal += 14;
        }

        parent.fill(244, 67, 54);
        parent.stroke(0, 0, 0, 10);
        int buttonWidth = (int) (parent.textWidth("Okay") + 50);
        parent.rect(startX + (width /2) - (buttonWidth/2), startY + height - 47, buttonWidth,40,5,5,5,5);

        parent.stroke(0, 0, 0, 0);
        parent.fill(0,0,0,30);
        parent.rect(startX + (width /2) - (buttonWidth/2) +2, startY + height - 47 + 2, buttonWidth,40,5,5,5,5);
        parent.textFont(parent.fonts[1]);


        parent.textSize(15);
        parent.fill(255, 235, 59);
        parent.text("Okay",startX + (width/2) - (buttonWidth/2)  + (parent.textWidth("Okay")/2),startY + height -22);
        //populate the acceptCoords array with the location of the okay button.
        acceptCoords[0] = startX + (width /2) - (buttonWidth/2);
        acceptCoords[1] = startY + height - 47;
        acceptCoords[2] = buttonWidth;
        acceptCoords[3] = 40;

        parent.textFont(parent.fonts[0]);
        parent.fill(0,0,0,30);
        parent.rect(startX+2,startY+2, width, height,5,5,5,5);
    }


    /**
     * Resets the offer/want negotiations
     */
    public void resetTrades(){
        this.openTrade = false;
        this.rejectAlert = false;
        client.setOfferRejected(false);
        playerNeg = client;
        client.setOffers(new HashMap<Integer, Integer>());
        client.setWants(new HashMap<Integer, Integer>());
        for(PlayerTradeCard trade : trades){
            trade.setOffers(new HashMap<Integer,Integer>());
            trade.setWants(new HashMap<Integer,Integer>());
            if(trade.isActiveOffer()){
                trade.setActiveOffer(false);
            }
            if(trade.isOfferRejected()){
                trade.setOfferRejected(false);
            }
        }
    }



    /**
     * Once the trading floor is closed.
     * Display the reject.
     */
    public void displayReject(){
        parent.textSize(20);
        parent.fill(198,40,40);
        parent.stroke(0,0,0,0);
        int width = (int) parent.textWidth("Trading Closed");
        int height = 80;
        parent.rect((parent.SCREEN_WIDTH/2) - ((20+width)/2), (parent.SCREEN_HEIGHT/2) - 30,width+20,height,10,10,10,10);
        parent.fill(0,0,0,30);
        parent.rect((parent.SCREEN_WIDTH/2) - ((20+width)/2)+2, (parent.SCREEN_HEIGHT/2) - 30 + 2,width+20,height,10,10,10,10);
        parent.fill(255);
        parent.text("Trading Closed", (parent.SCREEN_WIDTH/2) - ((width/2)), (parent.SCREEN_HEIGHT/2) - 5 );
        parent.fill(198, 40, 40);
        parent.rect((parent.SCREEN_WIDTH/2) - 30,(parent.SCREEN_HEIGHT/2) + 10, 60,30,5,5,5,5 );
        parent.fill(255);
        parent.text("OK", (parent.SCREEN_WIDTH/2) - (parent.textWidth("OK")/2),(parent.SCREEN_HEIGHT/2) + 30);


    }


    /**
     * Checks to see if a player can afford a trade from the perspective of
     * what the other player wants
     * @param trade
     * @return
     */
    public boolean affordTradeWants(PlayerTradeCard trade){
        int matchesWant = 0;
        for(int id : trade.getWants().keySet()){
            if(parent.model.getPlayer().getAllResource(id) >= trade.getWants().get(id)){
                matchesWant++;
            }
        }
        if( matchesWant >= trade.getWants().size()){
            return true;
        } else{
            return false;
        }
    }

}
