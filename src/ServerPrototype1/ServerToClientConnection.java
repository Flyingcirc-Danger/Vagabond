package ServerPrototype1;


import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import Prototype5.*;

/**
 * Created by Tom_Bryant on 7/7/15.
 * A class to handle client side connections
 * Operates on a Strike/Strength policy: A connection has 5 strikes
 * for every message it misses, it looses a strike. When it has 0 strikes
 * it is disconnected. After 10 sucessful messages, it gains a strike back (up to a max of 5).
 */
public class ServerToClientConnection {

    private Socket conn;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public HashMap<Integer, String> heartBeat;
    private int id;
    private int strikes;
    private int connectionStrength;
    private Game currentGame;
    private MessageRecord record;


    public ServerToClientConnection(Socket conn, HashMap<Integer, String> heartBeat, int id, Game currentGame, MessageRecord record){
        this.conn = conn;
        this.heartBeat = heartBeat;
        this.id = id;
        this.strikes = 5;
        this.currentGame = currentGame;
        this.record = record;
        try {
            //out before in
            this.out = new ObjectOutputStream(conn.getOutputStream());
            this.in = new ObjectInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * Continually reads the messages from the client
         */
        Thread read = new Thread(){
            public void run() {
                try {
                    while(true) {
                            Object omsg = in.readObject();
                            String msg = (String) omsg;
                            evaluateMessage(msg);
                            //heartBeat.put(id, (String) msg);
                    }
                } catch(EOFException e ){

                }
                catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        };
        read.setDaemon(true);
        read.start();

    }


    /**
     * Writes a message to the client
     * @param msg
     */
    public void write(String msg){
        try {
            out.writeObject(msg);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Checks if the strikes are low enough (<0)
     * to disconnect this socket
     * @return
     */
    public boolean isDisconnected(){
        if(strikes <= 0 ){
            return true;
        }
        return false;
    }

    /**
     * Removes a strike from this connection
     * Resets the strenght rating
     */
    public void strike(){
        this.strikes--;
        this.connectionStrength = 0;
    }

    /**
     * Adds a strike to this connection
     */
    public void addStrike(){
        if (this.strikes < 5){
            strikes++;
        }
    }

    /**
     * Increases the strenght rating of this connection
     */
    public void increaseStrength(){
        if(this.connectionStrength <10){
            connectionStrength++;
        } else {
            addStrike();
        }
    }

    /**
     * Closes the connection
     */
    public void closeConnection(){
        try {
            this.conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Evaluates the message. If it's a random char
     * it's a heartbeat. If it begins with <?xml
     * then it's an update
     * @param message the message to evaluate
     */
    public void evaluateMessage(String message){
        if(message.length() > 2) {
            if (message.substring(0, 5).equals("REPLY")) {
                message = message.substring(5, message.length());
                return;
            }
            if (message.substring(0, 5).equals("<?xml")) {
                int parseVal = ObjectParser.serverParseRequest(currentGame, message);
                if( parseVal == 1) {
                    System.out.println("Read Model: " + currentGame.mainBoard.getIdentityToken());
                    record.setCurrent(message);
                    return;
                }
                //for alerts.
                if(parseVal == 2) {
                    System.out.println("Alert Recieved");
                    return;
                }
                //for trades
                if(parseVal == 3) {
                    System.out.println("Propagating trade ");
                    record.setCurrent(message);
                }
                //for steals
                if(parseVal == 4) {
                    System.out.println("Propagating steal ");
                    record.setCurrent(message);
                }
                //for cards
                if(parseVal == 5) {
                    System.out.println("Propagating card ");
                    record.setCurrent(message);
                }
            }
        }
            heartBeat.put(id, message);

    }
}
