package ServerPrototype1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Tom_Bryant on 7/7/15.
 * Accepts connections, keeps heartbeat alive
 */
public class Server {

    public ArrayList<ClientConnection> clientConnections;
    public HashMap<Integer, String> heartBeat;
    public LinkedBlockingQueue<String> messages;
    public ServerSocket serverSocket;
    int startID = 0;


    public Server(int port) {
        clientConnections = new ArrayList<ClientConnection>();
        messages = new LinkedBlockingQueue<String>();
        heartBeat = new HashMap<Integer, String>();
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Accepts socket connections, makes new connection objects.
         * Populates the message map
         */
        Thread accept = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Socket s = serverSocket.accept();
                        ClientConnection temp = new ClientConnection(s, heartBeat, startID++ );
                        clientConnections.add(temp);
                        heartBeat.put(temp.getId(), "START");
                        System.out.println("Client " + temp.getId() + "  has connected" );
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
                        if (clientConnections.size() > 0) {
                            String message = generateRandString();
                            System.out.println("Sent message: " + message);
                            for (ClientConnection con : clientConnections) {
                                con.write(message);
                            }
                            sleep(2000);
                            if(clientConnections.size() > 0) {
                               ArrayList<ClientConnection> toRemove = new ArrayList<ClientConnection>();
                                for (ClientConnection con : clientConnections) {
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
                                for(ClientConnection con : toRemove){
                                    clientConnections.remove(con);
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



