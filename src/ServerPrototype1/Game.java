package ServerPrototype1;



import Prototype5.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by Tom_Bryant on 7/14/15.
 * Contains all the information about the current game.
 *
 */
public class Game implements Runnable {

    public String XMLBoard;
    public BoardData mainBoard;
    public ArrayList<Player> players;
    public HashMap<Integer, String> heartBeat;
    public LinkedBlockingQueue<String> messages;
    public String XMLboard;
    public String updateMessage;
    public MessageRecord record;
    public int currentID;
    public boolean gameBegin;
    public int readyPlayers;
    public int turnSeq;
    public int lastMessageType; //the type of message last recieved (0 = turn, 1 = alert, 2 = trade)


    public Game(){
        this.currentID = 1;
        this.gameBegin = false;
        this.mainBoard = new BoardData();
        players= new ArrayList<Player>();
        messages = new LinkedBlockingQueue<String>();
        heartBeat = new HashMap<Integer, String>();
        this.lastMessageType = 0;
        record = new MessageRecord();
        int SCREEN_HEIGHT = 768;
        int SCREEN_WIDTH = 1024;
        HexTile center=new HexTile(SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, mainBoard,mainBoard.getResourceTiles()[0],mainBoard.getTokens()[0]);
        center.getModel().buildBoard((center));

        Thread sendAll = new Thread() {
            public void run() {
                try {
                    while (true) {
                        if (players.size() > 0) {
                            String message = Server.generateRandString();
                            if(record.isCurrent()){
                                message = record.getCurrent();

                            }
                            for (Player con : players) {
                                con.send(message);

                            }
                            //evaluate what kindof message has been sent
                            if(message.length() > 1){
                                if(lastMessageType != 2){
                                    turnSequence();
                                }

                            }
                            sleep(2000);
                            if(players.size() > 0) {
                                ArrayList<Player> toRemove = new ArrayList<Player>();
                                for (Player con : players) {
                                    if (!heartBeat.get(con.getId()).equals(message)) {
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
        ServerToClientConnection temp = new ServerToClientConnection(s, this.heartBeat, setID, this, this.record);
        Player newPlayer = new Player(temp,setID,"Tom", 0,mainBoard);
        players.add(newPlayer);
        heartBeat.put(newPlayer.getId(), "START");
        System.out.println("Player " + newPlayer.getId() + " has entered the game");
        this.mainBoard.addNewPlayer(setID);
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
            int d1 = dice.nextInt((6 - 1) + 1) + 1;
            int d2 = dice.nextInt((6 - 1) + 1) + 1;
            String turn = ObjectParser.generateTurnBegin(d1,d2,this);
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
        return result;
    }


    public void turnSequence(){
        Random dice = new Random();
        int d1 = dice.nextInt((6 - 1) + 1) + 1;
        int d2 = dice.nextInt((6 - 1) + 1) + 1;
        String turn = ObjectParser.generateTurnBegin(d1,d2,this);
        //record.setCurrent(turn);
        for(Player play : players) {
                play.send(turn);
            }
    }
}
