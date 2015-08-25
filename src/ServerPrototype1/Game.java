package ServerPrototype1;



import Prototype5.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Tom_Bryant on 7/14/15.
 * Contains all the information about the current game.
 *
 */
public class Game implements Runnable {

    public String XMLBoard;
    public BoardData mainBoard;
    public CopyOnWriteArrayList<Player> players;
    public HashMap<Integer, String> heartBeat;
    public String XMLboard;
    public String updateMessage;
    public ArrayList<MessageRecord> playerRecords;
    public MessageRecord record;
    public int currentID;
    public boolean gameBegin;
    public int readyPlayers;
    public int turnSeq;
    public int lastMessageType; //the type of message last recieved (0 = turn, 1 = alert, 2 = trade)
    public boolean allDiscard;
    public ArrayList<Integer> discarded;
    public boolean turnToggle;
    public int turnNo;
    public Board clientBoard;
    public ConcurrentLinkedQueue<String> messages;
    public String boardType;

    public Game(Board clientBoard, String boardType){
        this.boardType = boardType;
        this.currentID = 1;
        this.turnNo = 0;
        this.gameBegin = false;
        this.mainBoard = new BoardData();
        players= new CopyOnWriteArrayList<Player>();
        heartBeat = new HashMap<Integer, String>();
        discarded = new ArrayList<Integer>();
        this.lastMessageType = 0;
        record = new MessageRecord();
        int SCREEN_HEIGHT = 768;
        int SCREEN_WIDTH = 1024;
        HexTile center=new HexTile(SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, mainBoard,mainBoard.getResourceTiles()[0],mainBoard.getTokens()[0]);
        if(boardType.equals("Classic")) {
            center.getModel().buildBoard((center));
        } else{
            center.getModel().buildRandomBoard((center));
        }
        this.allDiscard = true;
        playerRecords = new ArrayList<MessageRecord>();
        this.clientBoard = clientBoard;
        messages = new ConcurrentLinkedQueue<String>();


        Thread sendAll = new Thread() {
            public void run() {
                try {
                    while (true) {
                        if (players.size() > 0) {
                            fixDiscard();

                            for(MessageRecord playerRec : playerRecords) {
                                String message = new String();
                                record = playerRec;
                                if (record.isCurrent()) {
                                    message = record.getCurrent();
                                    System.out.println("Added Message");
                                    messages.add(message);

                                } else if (record.checkTurn()) {
                                    message = record.getTurn();
                                    System.out.println("Added Turn");
                                    messages.add(message);
                                }

                            }
                            String justSent = new String();
                            if(messages.size() == 0){
                                justSent = Server.generateRandString();
                            } else {
                               justSent =messages.poll();
                            }
                                for (Player con : players) {
                                    if(justSent.length() > 5){
                                        System.out.println("Sending message type: " + lastMessageType + "to player " + con.getId() );
                                    }
                                    con.send(justSent);
                                }



                            //evaluate what kindof message has been sent
                            if(justSent.length() > 1){
                                //if the last message was anything other than a trade,
                                //and everyone has discarded, advance the turn.
                                if(lastMessageType != 2 && isAllDiscard()){
                                    turnSequence();
                                }

                            }
                            sleep(2000);
                            if(players.size() > 0) {
                                ArrayList<Player> toRemove = new ArrayList<Player>();
                                for (Player con : players) {
                                    if (!heartBeat.get(con.getId()).equals(justSent)) {
                                        //TODO: FIX THE HEARTBEAT
//                                        System.out.println(con.getId() + ": message mismatch" + "\nexpected: " +
//                                                message +
//                                                "\nrecorded: " +
                                                //heartBeat.get(con.getId()));
                                       // con.getConnection().strike();
                                    } else {
                                        con.getConnection().increaseStrength();
                                    }
                                    if (con.getConnection().isDisconnected()) {
                                        System.out.println(con.getId() + " has disconnected");
                                        toRemove.add(con);
                                    }
                                }
                                for(Player con : toRemove){
                                    players.remove(con);
                                    heartBeat.remove(con.getId());
                                    con.getConnection().closeConnection();
                                }
                            }
                        } else {
                            sleep(600);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        sendAll.setDaemon(true);
        sendAll.start();
    }

    @Override
    public void run() {
        while(true){
            if(!gameBegin) {
                if (readyPlayers == players.size() && players.size() > 1) {
                        beginGame();
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Given a socket, adds a player to this game.
     * @param s
     */
    public void addPlayer(Socket s){
        if(this.currentID > 4){
            this.currentID = 1;
        }
        int setID = currentID;
        this.currentID +=1;
        MessageRecord tempRec = new MessageRecord();
        ServerToClientConnection temp = new ServerToClientConnection(s, this.heartBeat, setID, this, tempRec);
        playerRecords.add(tempRec);
        Player newPlayer = new Player(temp,setID,"Tom", 0,mainBoard);
        newPlayer.getConnection().setParent(newPlayer);
        players.add(newPlayer);
        heartBeat.put(newPlayer.getId(), "START");
        System.out.println("Player " + newPlayer.getId() + " has entered the game");
        this.mainBoard.addNewPlayer(setID);
    }


    public void removePlayer(Player toRemove){
//        if(clientBoard.model.getDisplayMode() == 7){
//            this.mainBoard.removePlayer(toRemove.getId());
//            this.players.remove(toRemove);
//            reset the id's
//            for(int i = 0; i < players.size(); i++){
//                players.get(i).setId(i+1);
//            }

            this.mainBoard.removePlayer(toRemove.getId());
            this.players.remove(toRemove);

        if(readyPlayers > 0) {
            this.readyPlayers--;
        }
    }


    public String getGameID(){
        return mainBoard.getIdentityToken();
    }


    /**
     * Starts the game, sends players their
     * player information and the board layout.
     */
    public void beginGame(){

        if(players.size() >= 2){
            Random dice = new Random();
            this.turnSeq = dice.nextInt((players.size()));
            int d1 = dice.nextInt((6 - 1) + 1) + 1;
            int d2 = dice.nextInt((6 - 1) + 1) + 1;
            //no sevens on the first turn
            if(d1 + d2 == 7){
                if(d1 == 6){
                    d1 = 5;
                } else{
                    d1 += 1;
                }
            }
            String turn = ObjectParser.generateTurnBegin(d1, d2, this);
            for(Player play : players) {
                try {
                    this.XMLboard = ObjectParser.parseModel(mainBoard);
                    play.send(XMLboard);
                    Thread.sleep(100);
                    play.send(play.getPlayerXML());
                    Thread.sleep(1000);
                    play.send(turn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.gameBegin = true;
            this.turnNo = 1;
        }

    }

    /**
     * Returns the id of the player whose turn it currently is
     * Advances the turn sequence counter to the next player
     * so that the next call will return the next player.
     * @return the id of the player whose turn it is.
     */
    public int advanceTurn(){
        int result = players.get(turnSeq).getId();
        this.turnSeq = turnSeq + 1;
        if(turnSeq >= players.size()){
            this.turnSeq = 0;
        }
        System.out.println("PLAYER " + result + " turn");
        this.turnNo++;
        return result;
    }


    public boolean isAllDiscard(){;
        return allDiscard;
    }

    public void fixDiscard(){
        if(discarded.size() == players.size() || allDiscard == true){
            allDiscard = true;
            for(MessageRecord rec : playerRecords) {
                rec.setTurnAuth(true);
            }
        } else{
            for(MessageRecord rec : playerRecords) {
                rec.setTurnAuth(false);
            }
        }
    }

    /**
     * Initiates a turn sequence
     */
    public void turnSequence(){
        turnToggle = false;
        Random dice = new Random();
        int d1 = dice.nextInt((6 - 1) + 1) + 1;
        int d2 = dice.nextInt((6 - 1) + 1) + 1;
        //no sevens on the first round of turns
        if(turnNo < (players.size() *2) && d1 + d2 == 7){
            if(d1 == 6 ){
                d1 = 5;
            } else{
                d1 += 1;
            }
            for(MessageRecord rec : playerRecords) {
                rec.setTurnAuth(true);
            }
        }
        if(d1 + d2  == 7){
            allDiscard = false;
            discarded = new ArrayList<Integer>();
            for(MessageRecord rec : playerRecords) {
                rec.setTurnAuth(false);
            }
        } else{
            discarded = new ArrayList<Integer>();
            allDiscard = true;
            for(MessageRecord rec : playerRecords) {
                rec.setTurnAuth(true);
            }

        }
        String turn = ObjectParser.generateTurnBegin(d1,d2,this);
        //record.setCurrent(turn);
        for(Player play : players) {
                play.send(turn);
            }
    }

    public void buildNewRandomBoard(){
        this.mainBoard = new BoardData();
        int SCREEN_HEIGHT = 768;
        int SCREEN_WIDTH = 1024;
        HexTile center=new HexTile(SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, mainBoard,mainBoard.getResourceTiles()[0],mainBoard.getTokens()[0]);
        center.getModel().buildRandomBoard((center));
    }


}
