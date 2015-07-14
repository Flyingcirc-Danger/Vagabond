package ServerPrototype1;

import Prototype5.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
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


    public Game(){
        this.currentID = 0;
        this.gameBegin = false;
        this.mainBoard = new BoardData();
        players= new ArrayList<Player>();
        messages = new LinkedBlockingQueue<String>();
        heartBeat = new HashMap<Integer, String>();
        record = new MessageRecord();
        int SCREEN_HEIGHT = 768;
        int SCREEN_WIDTH = 1024;
        HexTile center=new HexTile(SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, mainBoard,mainBoard.getResourceTiles()[0],mainBoard.getTokens()[0]);
        center.getModel().buildBoard((center));
        this.XMLboard = ObjectParser.parseModel(mainBoard);

        Thread sendAll = new Thread() {
            public void run() {
                try {
                    while (true) {
                        if (players.size() > 0) {
                            String message = Server.generateRandString();
                            if(record.isCurrent()){
                                message = record.getCurrent();
                            }
                            System.out.println("Sent message from Game: " + message);
                            for (Player con : players) {
                                con.send(message);
                            }
                            sleep(2000);
                            if(players.size() > 0) {
                                ArrayList<Player> toRemove = new ArrayList<Player>();
                                for (Player con : players) {
                                    if (!heartBeat.get(con.getId()).equals(message)) {
                                        System.out.println(con.getId() + ": message mismatch");
                                        con.getConnection().strike();
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
                if (players.size() >= 2) {
                        beginGame();
                    System.out.println("Game has begun");
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void addPlayer(Socket s){
        if(this.currentID > 3){
            this.currentID = 0;
        }
        int setID = currentID;
        this.currentID +=1;
        ServerToClientConnection temp = new ServerToClientConnection(s, this.heartBeat, setID, this.mainBoard, this.record);
        Player newPlayer = new Player(temp,setID,"Tom", 0);
        players.add(newPlayer);
        heartBeat.put(newPlayer.getId(), "START");
        System.out.println("Player " + newPlayer.getId() + " has entered the game");
        System.out.println("current id: " + this.currentID);
        //newPlayer.send(XMLboard);
        //newPlayer.send(newPlayer.getPlayerXML());
    }

    public String getGameID(){
        return mainBoard.getIdentityToken();
    }


    public void beginGame(){
        if(players.size() >= 2){
            for(Player play : players) {
                play.send(XMLboard);
                play.send(play.getPlayerXML());
            }
            this.gameBegin = true;
        }

    }
}
