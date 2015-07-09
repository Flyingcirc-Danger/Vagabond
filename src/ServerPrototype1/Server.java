package ServerPrototype1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import Prototype5.*;

/**
 * Created by Tom_Bryant on 7/7/15.
 * Accepts connections, keeps heartbeat alive
 */
public class Server {

    public ArrayList<ServerToClientConnection> serverToClientConnections;
    public HashMap<Integer, String> heartBeat;
    public LinkedBlockingQueue<String> messages;
    public ServerSocket serverSocket;
    int startID = 0;
    public String XMLboard;
    public BoardData mainBoard;
    public String updateMessage;
    public MessageRecord record;


    public Server(int port) {
        serverToClientConnections = new ArrayList<ServerToClientConnection>();
        messages = new LinkedBlockingQueue<String>();
        heartBeat = new HashMap<Integer, String>();
        record = new MessageRecord();
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mainBoard = new BoardData();
        int SCREEN_HEIGHT = 768;
        int SCREEN_WIDTH = 1024;
        HexTile center=new HexTile(SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, mainBoard,mainBoard.getResourceTiles()[0],mainBoard.getTokens()[0]);
        center.getModel().buildRandomBoard((center));
        this.XMLboard = ObjectParser.parseModel(mainBoard);


        /**
         * Accepts socket connections, makes new connection objects.
         * Populates the message map
         */
        Thread accept = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Socket s = serverSocket.accept();
                        ServerToClientConnection temp = new ServerToClientConnection(s, heartBeat, startID++, mainBoard, record);
                        serverToClientConnections.add(temp);
                        heartBeat.put(temp.getId(), "START");
                        System.out.println("Client " + temp.getId() + "  has connected" );
                        temp.write(XMLboard);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };

        accept.setDaemon(true);
        accept.start();

        /**
         * Sends periodic heartbeat updates to all
         * clients. Removes clients who have disconnected
         */
        Thread sendAll = new Thread() {
            public void run() {
                try {
                    while (true) {
                        if (serverToClientConnections.size() > 0) {
                                String message = generateRandString();
                                if(record.isCurrent()){
                                    message = record.getCurrent();
                                }
                            System.out.println("Sent message: " + message);
                            for (ServerToClientConnection con : serverToClientConnections) {
                                con.write(message);
                            }
                            sleep(2000);
                            if(serverToClientConnections.size() > 0) {
                               ArrayList<ServerToClientConnection> toRemove = new ArrayList<ServerToClientConnection>();
                                for (ServerToClientConnection con : serverToClientConnections) {
                                    if (!heartBeat.get(con.getId()).equals(message)) {
                                        System.out.println(con.getId() + ": message mismatch");
                                        con.strike();
                                    } else {
                                        con.increaseStrength();
                                    }
                                    if (con.isDisconnected()) {
                                        System.out.println(con.getId() + " has disconnected");
                                        toRemove.add(con);
                                    }
                                }
                                for(ServerToClientConnection con : toRemove){
                                    serverToClientConnections.remove(con);
                                    heartBeat.remove(con.getId());
                                    con.closeConnection();
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








    public LinkedBlockingQueue<String> getMessages() {
        return messages;
    }

    public void setMessages(LinkedBlockingQueue<String> messages) {
        this.messages = messages;
    }

    /**
     * Generates a random 1 character string to send as a heartbeat
     * token.
     * @return the random string
     */
    private String generateRandString(){
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
            result.append(possibleChars.charAt(select.nextInt(possibleChars.length())));
        return result.toString();
    }

    public static void main(String[] args){
        new Server(4001);
        while(true){

        }
    }
}



