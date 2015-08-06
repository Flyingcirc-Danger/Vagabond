package Prototype5;


import java.awt.*;
import java.util.*;

/**
 * Created by Tom_Bryant on 6/26/15.
 * A class containing the board mapping data
 */
public class BoardData {

    private HashMap<Point, HexPoint> pointMap; //the map of all hexpoints, no duplicates.
    private ArrayList<HexPoint> edges; //the arraylist of edge points
    private String[] buildOrder; //an array of the build order of
    private HexTile[] hexDeck; //the array of all tiles in the standard deck of catan (19 tiles)

    private ArrayList<HexSide> sides; //the arraylist of all sides (no duplicates)
    private HashMap<Point, HexSide> sideMap; //the map of all sides (no duplicates)
    private int displayMode; //the current map display mode (for debugging purposes)

    private HashMap<Point, HexTile> tileMap; //the map of all hexTiles (no duplicates)

    private HashMap<Integer,HashSet<HexTile>>payout; //the map of values to hexTiles

    private ArrayList<HexCoast> coast; //the arraylist of all coast pieces.

    private String[] resourceTiles; //array of standard resources in tournament catan (19 resources)

    private int[] tokens; //array of standard tokens in tournament catan

    public int settlementQuota; //the number of settlments (towns/cities) on the board

    public int roadQuota;

    private String identityToken;

    private Board parent;

    private StringBuffer manifest;

    private StringBuffer tradeManifest;

    private StringBuffer stealManifest;

    private StringBuffer cardManifest;

    private String alert;

    public boolean alertReady;

    public boolean tradeReady;

    public boolean stealReady;

    public boolean cardReady;

    private boolean displayToggle;

    public boolean manifestReady;

    private HashMap<Integer, int[]> playerColors;

    private PlayerInfo player;

    private Menus menus;

    //the ID of the player whose turn it is
    private int playerTurn;

    //the number of the die roll in this current turn
    private int turnRoll;

    private ArrayList<Integer> playerList; //an arraylist of player id's

    private HexTile robberTile; // the tile that the robber currently occupies





    public BoardData(Board parent) {
        this.displayMode = 0;
        this.playerList = new ArrayList<Integer>();
        this.player = new PlayerInfo(0);
        this.menus = new Menus(parent,player);
        pointMap = new HashMap<Point, HexPoint>();
        tileMap = new HashMap<Point, HexTile>();
        edges = new ArrayList<HexPoint>();
        sideMap = new HashMap<Point, HexSide>();
        buildOrder = new String[]{"NA", "AB", "CD", "DE", "EF", "FA", "AB", "AB", "BC", "CD", "CD", "DE", "DE", "EF", "EF", "FA", "FA", "AB", "AB"};
        hexDeck = new HexTile[19];
        this.coast = new ArrayList<HexCoast>();
        this.resourceTiles = new String[] {
                "forest", "forest","forest","forest",
                "grain", "grain","grain","grain",
                "pasture","pasture","pasture","pasture",
                "mine","mine","mine",
                "brick","brick","brick",
                "desert"
        };
        tokens = new int[19];
        findDesert(shuffleTerrain());
        settlementQuota = 0;
        roadQuota = 0;
        this.parent = parent;
        generateIdentity();
        System.out.println("Build Model: " + this.identityToken);
        displayToggle = false;
        manifestReady = false;
        this.stealReady = false;
        this.cardReady = false;
        initPlayerColors();
        this.playerTurn = 0;
        this.turnRoll = 1;
        this.payout = new HashMap<Integer, HashSet<HexTile>>();
        this.manifest = new StringBuffer();
        this.tradeManifest = new StringBuffer();
        this.stealManifest = new StringBuffer();
        this.cardManifest = new StringBuffer();
        this.robberTile = new HexTile();
        menus.getDevDeck().shuffleDeck(tokenSeed());

    }

    /**
     * Builds an OldBoard given a XMLstring.
     */
    public BoardData(){
        this.displayMode = 0;

        this.playerList = new ArrayList<Integer>();
        pointMap = new HashMap<Point, HexPoint>();
        tileMap = new HashMap<Point, HexTile>();
        edges = new ArrayList<HexPoint>();
        sideMap = new HashMap<Point, HexSide>();
        buildOrder = new String[]{"NA", "AB", "CD", "DE", "EF", "FA", "AB", "AB", "BC", "CD", "CD", "DE", "DE", "EF", "EF", "FA", "FA", "AB", "AB"};
        hexDeck = new HexTile[19];
        this.coast = new ArrayList<HexCoast>();
        this.resourceTiles = new String[] {
                "forest", "forest","forest","forest",
                "grain", "grain","grain","grain",
                "pasture","pasture","pasture","pasture",
                "mine","mine","mine",
                "brick","brick","brick",
                "desert"
        };
        tokens = new int[19];
        findDesert(shuffleTerrain());
        settlementQuota = 0;
        roadQuota = 0;
        generateIdentity();
        generateIdentity();
        System.out.println("Build Model: " + this.identityToken);
        initPlayerColors();
        this.player = new PlayerInfo(0);
        this.alert = new String();
        this.payout = new HashMap<Integer, HashSet<HexTile>>();
        this.manifest = new StringBuffer();
        this.robberTile = new HexTile();


    }

    public HashMap<Point, HexPoint> getPointMap() {
        return pointMap;
    }

    public void setPointMap(HashMap<Point, HexPoint> pointMap) {
        this.pointMap = pointMap;
    }

    public HashMap<Point, HexSide> getSideMap() {
        return sideMap;
    }

    public void setSideMap(HashMap<Point, HexSide> sideMap) {
        this.sideMap = sideMap;
    }

    public ArrayList<HexSide> getSides() {
        return sides;
    }

    public void setSides(ArrayList<HexSide> sides) {
        this.sides = sides;
    }

    public int getDisplayMode() {
        return displayMode;
    }

    public String[] getResourceTiles() {
        return resourceTiles;
    }

    public void setResourceTiles(String[] resourceTiles) {
        this.resourceTiles = resourceTiles;
    }

    public void setDisplayMode(int displayMode) {
        this.displayMode = displayMode;
    }

    public int[] getTokens() {
        return tokens;
    }

    public void setTokens(int[] tokens) {
        this.tokens = tokens;
    }

    public HashMap<Point, HexTile> getTileMap() {
        return tileMap;
    }

    public void setTileMap(HashMap<Point, HexTile> tileMap) {
        this.tileMap = tileMap;
    }

    public String getIdentityToken() {
        return identityToken;
    }

    public void setIdentityToken(String identityToken) {
        this.identityToken = identityToken;
    }

    public Board getParent() {
        return parent;
    }

    public void setParent(Board parent) {
        this.parent = parent;
    }

    public HexTile[] getHexDeck() {
        return hexDeck;
    }

    public void setHexDeck(HexTile[] hexDeck) {
        this.hexDeck = hexDeck;
    }

    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getTurnRoll() {
        return turnRoll;
    }

    public void setTurnRoll(int turnRoll) {
        this.turnRoll = turnRoll;
    }

    public HashMap<Integer, HashSet<HexTile>> getPayout() {
        return payout;
    }

    public void setPayout(HashMap<Integer, HashSet<HexTile>> payout) {
        this.payout = payout;
    }


    public void addNewPlayer(int id){
        this.playerList.add(id);
    }

    public ArrayList<Integer> getPlayerList(){
        return this.playerList;
    }

    public HexTile getRobberTile() {
        return robberTile;
    }

    public void setRobberTile(HexTile robberTile) {
        this.robberTile = robberTile;
    }

    /**
     * IDs are assigned to each point for
     * debugging purposes. They are assigned
     * based on the size of the current HashMap
     *
     * @return the ID of a new point.
     */
    public int assignPointID() {
        return this.pointMap.size();
    }

    /**
     * IDs are assigned to each side for
     * debugging purposes. They are assigned
     * based on the size of the current HashMap
     *
     * @return the ID of a new side.
     */
    public int assignSideID() {
        return this.sideMap.size();
    }





    /**
     * Reads the build order, expands accordingly
     *
     * @param center
     */
    public void buildBoard(HexTile center) {
        hexDeck[0] = center;
        if(center.getParent() != null) {
            center.display();
        }
        for (int i = 1; i < hexDeck.length; i++) {
            HexTile temp = hexDeck[i - 1].expand(buildOrder[i],resourceTiles[i],tokens[i]);
            if(resourceTiles[i].equals("desert")){
                temp.setRobber(true);
                this.robberTile = temp;
            }
            hexDeck[i] = temp;
        }
        configureEdges();
    }

    /**
     * Randomly generates a board given a center tile
     * @param center
     */
    public void buildRandomBoard(HexTile center) {
        hexDeck[0] = center;
        for(int i = 1; i < 19; i++){
            hexDeck[i] = chooseSide(i-1,-1);

        }
        configureEdges();
    }


    /**
     * Chooses a side and tile to randomly expand
     * Takes into consideration whether there is a tile
     * already present
     * @param maxIndex the max index of the tiles already built
     * @param excludeTile a tile to exclude (if it's land locked)
     * @return the newly built tile
     */
    public HexTile chooseSide(int maxIndex, int excludeTile) {
        Random index = new Random();
        //chose a random int between 0 and the current index.
        int R = 0;
        if(maxIndex != 0){
             R = index.nextInt(maxIndex);
        }
        //deal with excluded index.
        if (R == excludeTile) {if (R == 0) {R = maxIndex;} else {R--; }}
        HexTile toBuild = hexDeck[R];
        //check to see if tile is landlocked.
        if (toBuild.landLocked()) {

            return chooseSide(maxIndex, R);
        }
        //Screen bounds checking

        else {
            //choose a random side
            String[] instructions = new String[]{"AB","BC","CD","DE","EF","FA"};
            Random side = new Random();
            int rS = index.nextInt(5);
            //while a neighbor exists, rotate the instructions
            while (toBuild.checkNeighbor(instructions[rS])) {
                if (rS == 0) {
                    rS = 5;
                } else {
                    rS--;
                }
            }
            if(((toBuild.getCenter().getX() < (toBuild.getRadius() * 2)) && instructions[rS].equals("EF"))
                    || ((toBuild.getCenter().getX() > Board.SCREEN_WIDTH - (toBuild.getRadius() * 2))&& instructions[rS].equals("BC"))
                    || ((toBuild.getCenter().getY() > Prototype3.Prototype3_3.Board.SCREEN_HEIGHT - (toBuild.getRadius() * 2))&& instructions[rS].equals("CD"))
                    || ((toBuild.getCenter().getY() > Board.SCREEN_HEIGHT - (toBuild.getRadius() * 2))&& instructions[rS].equals("DE"))
                    || ((toBuild.getCenter().getY() < (toBuild.getRadius() * 2)) && instructions[rS].equals("FA"))
                    || ((toBuild.getCenter().getY() < (toBuild.getRadius() * 2)) && instructions[rS].equals("AB"))){
                return chooseSide(maxIndex, R);
            }

            return toBuild.expand(instructions[rS],resourceTiles[maxIndex+1],tokens[maxIndex+1]);
        }
    }




    /**
     * A method for redrawing the board.
     * OPTIONS:
     * 0 = no debug
     * 1 = point debug
     * 2 = display debug
     * 3 = resource debug
     * 4 = debug all
     * 6 = diceRoll
     * 7 = waitScreen
     * 8 = TradeScreen
     * 10 = connect menu
     */
    public void displayBoard(){
        if(!this.checkToggle()) {

            int option = this.displayMode;
            for (int i = 0; i < coast.size(); i++) {
                coast.get(i).shadow(2);
            }
            for (int i = 0; i < coast.size(); i++) {
                coast.get(i).display();
            }
            for (int i = 0; i < hexDeck.length; i++) {
                hexDeck[i].display();
                //hexDeck[i].checkPoints();
                //hexDeck[i].checkSides();
                if (option == 1) {
                    hexDeck[i].pointDebug();
                    hexDeck[i].checkPoints();
                }
                if (option == 2) {
                    hexDeck[i].sideDebug();
                    hexDeck[i].checkSides();
                }
                if (option == 3) {
                    hexDeck[i].resourceDebug();
                }
                if (option == 4) {
                    hexDeck[i].pointDebug();
                    hexDeck[i].sideDebug();
                    hexDeck[i].checkPoints();
                    hexDeck[i].checkSides();
                }
                if(parent.currentTool == 7){
                    hexDeck[i].highlightRobber();
                    if(menus.getRobDialogue().getRobSequence() == 6){
                        parent.cursor(parent.resourceIMG[10]);
                    } else{
                        parent.cursor(parent.ARROW);
                    }
                }

            }
        }
    }

    /**
     * Display mode 10 = connect menu
     */
    public void displayConnect(){
        int option = this.displayMode;
        if(!this.checkToggle()) {
            if (option == 10) {
                getMenus().getConnect().display();
            }
        }
    }

    /**
     * Checks which items (points/tiles/sides)
     * are selected. Only checks the items
     * that the specific tool requires
     * @param tool the tool currently selected.
     */
    public void checkSelected(int tool){
        if(menus.getRobDialogue().getRobSequence() < 5){
            menus.getRobDialogue().checkButtons();
        }
        if(tool == 1 || tool == 3) {
            for (int i = 0; i < hexDeck.length; i++) {
                hexDeck[i].checkSettledPoints();
                parent.cursor(parent.ARROW);
            }
        }
        if(tool == 2){
            for(int i = 0; i < hexDeck.length; i++){
                hexDeck[i].checkBuiltRoads();
                parent.cursor(parent.ARROW);
            }
        }
        if(tool == 4){
            System.out.println("Turn End");
            manifestReady = true;
            this.getParent().currentTool = 0;
            parent.cursor(parent.ARROW);
        }
        //robber tool.
        if(tool == 7){
            if(menus.getRobDialogue().getRobSequence() < 5 ){
                parent.cursor(parent.ARROW);
                menus.getRobDialogue().checkButtons();
            } else {
                parent.cursor(parent.resourceIMG[10]);
                for (int i = 0; i < hexDeck.length; i++) {
                    hexDeck[i].checkRobber();
                    if(hexDeck[i].isRobber()) {
                        menus.getRobDialogue().setTargets(hexDeck[i]);
                        menus.getRobDialogue().setRobConfirm();
                        break;
                    }
                    //parent.currentTool = 0;
                }
            }
        }
    }

    /**
     * Figures out which tiles are edge tiles
     * and populates the coast arrayList.
     */
    public void configureEdges(){
        for(int i = 0; i < hexDeck.length; i++){
            if(!hexDeck[i].landLocked()){
                HexTile ct = hexDeck[i];
                coast.add(new HexCoast(ct));
            }
        }
    }

    /**
     * A method for shuffling the deck of terrain
     * this helps generate the random map.
     * It returns the int index of the desert to
     * fix the tokens
     * adapted from:
     * http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
     */
    public int shuffleTerrain(){
        int desertIndex = 0;
        Random seed = new Random();
        for (int i = resourceTiles.length - 1; i > 0; i--)
        {
            int index = seed.nextInt(i + 1);
            String temp = resourceTiles[index];
            resourceTiles[index] = resourceTiles[i];
            resourceTiles[i] = temp;
            if(temp.equals("desert")){
                desertIndex = i;
            }
        }
        return desertIndex;

    }



    /**
     * A method for fixing the temp tokens array.
     * This method places the desert token (55) into
     * the list of tokens. This will not be rendered
     * by the front-end.
     * @param desertIndex the index where the desert is located.
     */
    public void findDesert(int desertIndex){
        int[] tempTokens = new int[] {11,3,6,5,4,9,10,8,4,11,12,9,10,8,3,6,2,5};
        int[] resultTokens = new int[19];
        int counter = 0;
        if(desertIndex == 0){
            resultTokens[0] = 55;
            for(int i=0; i <tempTokens.length;i++){
                resultTokens[i+1] = tempTokens[i];
            }
        } else {
            for (int i = 0; i < desertIndex; i++) {
                resultTokens[i] = tempTokens[i];
                counter = i;
            }
            resultTokens[counter + 1] = 55;
            for (int i = counter + 2; i < 19; i++) {
                resultTokens[i] = tempTokens[i - 1];
            }
        }
        this.tokens = resultTokens;

    }

    /**
     * Generates a random identity token of length 20.
     */
    private void generateIdentity(){
        StringBuffer possibleChars = new StringBuffer();
        for(int i = 48; i <= 57; i++){
            possibleChars.append((char) i);
        }
        for(int i = 65; i <= 90; i++){
            possibleChars.append((char) i);
        }
        for(int i = 97; i <= 122; i++){
            possibleChars.append((char) i);
        }
        Random select = new Random();
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < 20; i++){
            result.append(possibleChars.charAt(select.nextInt(possibleChars.length())));
        }
        this.identityToken = result.toString();
    }

    /**
     * Clears all the hashmaps (for use on update)
     */
    public void clearMaps() {
        pointMap = new HashMap<Point, HexPoint>();
        sideMap = new HashMap<Point, HexSide>();
        tileMap = new HashMap<Point, HexTile>();
        hexDeck = new HexTile[19];
        coast = new ArrayList<HexCoast>();
        payout = new HashMap<Integer, HashSet<HexTile>>();
        playerList = new ArrayList<Integer>();
    }


    /**
     * Initializes the manifest (move record) file
     * Clears the last manifest, adds the header info to the new
     * manifest.
     */
    public void initManifest(){
        manifest = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
        manifest.append("<manifest><identity>" + this.identityToken +"</identity>");
        manifest.append("<playerTurn>" + this.getPlayer().getId() + "</playerTurn>");
    }

    /**
     * Sets the trade manifest of this player.
     * @param trade the trade manifest string generated through the trade screen
     */
    public void setTradeManifest(String trade){
        this.tradeManifest = new StringBuffer();
        tradeManifest.append(trade);
        tradeReady = true;
    }

    /**
     * Sets the steal manifest of this player
     * @param steal the steal manifest string of the player generated by the steal dialogue.
     */
    public void setStealManifest(String steal){
        this.stealManifest = new StringBuffer();
        stealManifest.append(steal);
        stealReady = true;
    }


    /**
     * Sets the card manifest of this player
     * @param card  the card manifest string of the player.
     */
    public void setCardManifest(String card){
        this.cardManifest = new StringBuffer();
        cardManifest.append(card);
        cardReady = true;
    }






    public StringBuffer getTradeManifest(){
        return this.tradeManifest;
    }

    public StringBuffer getCardManifest(){
        return this.cardManifest;
    }

    public StringBuffer getStealManifest(){
        return this.stealManifest;
    }

    public StringBuffer getManifest(){
        return this.manifest;
    }


    /**
     * To be called ONLY when ready to send the manifest.
     * This method appends the closing XML to the manifest stringbuffer,
     * converts it to a string and clears the buffer.
     * @return
     */
    public String getManifestString(){
        if(manifest.length() == 0){
            initManifest();
        }
        manifest.append("<discardcards>" + menus.getDevDeck().getTurnRemovedCards() + "</discardcards>");
        menus.getDevDeck().setTurnRemovedCards(0);
        manifest.append("</manifest>");
        ObjectParser.saveOutput(manifest.toString(),"manifest.xml");
        String result = manifest.toString();
        manifest = new StringBuffer();
        manifestReady = false;
        return result;
    }


    /**
     * Fetches the trade string. Only to be used
     * when sending the trade string. This is because
     * the trade string will be overwritten upon
     * fetching.
     * @return the trade string to send.
     */
    public String getTradeManifestString(){
        String result = tradeManifest.toString();
        tradeManifest = new StringBuffer();
        tradeReady = false;
        return result;
    }


    public String getAlertString(){
        String result = this.alert;
        alert = new String();
        alertReady = false;
        return result;
    }

    /**
     * Fetches the steal string. only to be
     * used when sending the steal string.
     * This is because the steal string will be overwritten
     * upon fetching.
     * @return the steal string to send.
     */
    public String getStealManifestString(){
        String result = stealManifest.toString();
        stealManifest = new StringBuffer();
        stealReady = false;
        return result;
    }

    /**
     * Fetches the card string. only to be used
     * when sending the card string because the
     * card string will be overwritten upon fetching
     * @return the card string to send.
     */
    public String getCardManifestString(){
        String result = cardManifest.toString();
        cardManifest = new StringBuffer();
        cardReady = false;
        return result;
    }



    public void setToggle(){
        this.displayToggle = true;
    }

    public void releaseToggle(){
        this.displayToggle = false;
    }

    public boolean checkToggle(){
        return  this.displayToggle;
    }

    public PlayerInfo getPlayer() {
        return player;
    }

    public void setPlayer(PlayerInfo player) {
        this.player = player;
    }

    public HashMap<Integer, int[]> getPlayerColors() {
        return playerColors;
    }

    public void setPlayerColors(HashMap<Integer,int[]> playerColors) {
        this.playerColors = playerColors;
    }

    /**
     * Inits the playerColor map.
     * Maps colors to ids
     */
    public void initPlayerColors(){
        this.playerColors =  new HashMap<Integer, int[] >();
        //player1 = blue
        playerColors.put(0,new int[]{1,87,155});
        //player2 = red
        playerColors.put(1,new int[]{183,28,28});
        //player3 = green
        playerColors.put(2,new int[]{51,105,30});
        //player4 = yellow/Orange
        playerColors.put(3,new int[]{245,127,23});

    }

    /**
     * Returns the color related to the
     * given id of a player
     * @param id
     * @return
     */
    public int[] getColor(int id){
        return this.getPlayerColors().get(id);
    }


    /**
     * Handles what menus to display in
     */
    public void displayMenus(){
        if(displayMode == 10){
            menus.getConnect().display();
            return;
        }
        if(displayMode == 7){
            menus.getWaitScreen().display();
        }
        if(displayMode == 8){
            menus.getTradeFloor().display();
            menus.getResourceBar().displayBottom();
        }
        if(displayMode == 6){
            menus.getDie().display();
            return;
        }
        if(displayMode == 9){
            menus.getDiscardScreen().display();
        }
        if(displayMode <=5){
            menus.getBank().display();
            menus.getBottomMenu().display();
            menus.getResourceBar().display();
            parent.image(parent.images[0], parent.SCREEN_WIDTH - 220, parent.SCREEN_HEIGHT - 70);//logo
            menus.getDeckScreen().display();
            if(menus.getTradeFloor().isTradeAlert()){
                menus.getTradeFloor().displayTradeAlert();
            }
            if(menus.getRobDialogue().getRobSequence() > 0 && menus.getRobDialogue().getRobSequence() < 5){
                menus.getRobDialogue().display();
            }
            if(menus.getRobDialogue().isToolSwitch()){
                menus.getRobDialogue().displayRobberNotification();
            }
            return;
        }
    }

    /**
     * Checks the mouse coords in relation to specific
     * menus, determined by the display mode
     */
    public void checkMenus(){
        if(displayMode == 10){
            menus.getConnect().checkButtons();

           // menus.getDeck().get(0).checkButtons();
        }
        if(displayMode == 7){
            if(menus.getWaitScreen().checkButton()){
                this.alert = ObjectParser.generateAlert("ready");
                this.alertReady = true;
                parent.background(0, 188, 212);
                menus.setWaitScreen(new StatusMenu("Waiting For Other Players",parent, false));
            }
        }
        if(displayMode == 8){
            menus.getTradeFloor().checkButtons(0);
        }
        if(displayMode <= 5){
            menus.getBank().checkButtons();
            menus.getBottomMenu().checkSelected();
            if(menus.getTradeFloor().isTradeAlert()) {
                menus.getTradeFloor().checkButtons(1);
            }
            menus.getDeckScreen().checkButtons();
        }
        if(displayMode == 6){
            //check the die okay button
           if(menus.getDie().checkButton()){
               //go to the discard screen
               if(getPlayer().getResourceCount() > 7 && turnRoll == 7 ){
                   setDisplayMode(9);
               } else {
                   if (this.player.getId() == playerTurn && turnRoll == 7) {
                       menus.getRobDialogue().setToolSwitch();
                   } else {
                       menus.getRobDialogue().setRobSequence(5);
                   }
                   setDisplayMode(0);
               }

           }
        }
        if(displayMode == 9){
            menus.getDiscardScreen().checkButtons();
        }
    }

    /**
     * Checks the mouse coords for hovering over menus
     */
    public void checkHoverMenus(){
        if(displayMode == 7){
            menus.getWaitScreen().checkButton();
        }

    }

    /**
     * Contains the logic of the turn
     * @param playerID the player whose turn it is
     * @param d1 the roll on dice one
     * @param d2 the roll on dice 2
     */
    public void handleTurn(int playerID, int d1, int d2){
        menus.setDie(new Dice(d1,d2,parent));
        this.playerTurn = playerID;
        this.setDisplayMode(6);
        //activate knights
        if(player.getInactiveKnights() != null) {
            for (int i = 0; i < player.getInactiveKnights().size(); i++) {
                player.getInactiveKnights().get(i).setInactive(false);
            }
            player.setInactiveKnights(new ArrayList<DevelopmentCard>());
        }
        if(d1+d2 != 7) {
            payResources(d1 + d2);
        } else{
            menus.getDiscardScreen().setResourceDiscard(getPlayer().getResourceCount()/2);
            menus.getDiscardScreen().resetDiscardPile();
        }
    }

    /**
     * Generates a long seed
     * to be passed to any random generators
     * that you wish to be the same client wide
     * Uses the identityToken to generate a random seed
     * @return a long seed based on the identity token
     */
    public long tokenSeed(){
        long result =0;
        long result2 =1000;
            for(int i = 0; i < (identityToken.length())/2; i++){
                int temp = identityToken.charAt(i);
                if(result < Long.MAX_VALUE - temp) {
                    result += temp;}
            }
            for(int j = identityToken.length()/2; j < identityToken.length(); j++){
                int temp2 = identityToken.charAt(j);
                if(result2 < Long.MAX_VALUE && result2 > 0){
                    result2 -= temp2;
                }
            }
        return (result * result2);


    }

    public void payResources(int token){
        HashSet<HexTile>payHex = payout.get(token);
        for(HexTile tile : payHex){
            if(tile.checkOwner()){
                if(!tile.isRobber()) {
                    tile.payResource();
                }
            }
        }
    }


}


