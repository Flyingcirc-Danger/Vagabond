package ServerPrototype1;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import Prototype5.*;

/**
 * Created by Tom_Bryant on 7/7/15.
 * A class that contains the input and output steams
 * for the client to connection
 */
public class ClientToServerConnection {

    private Socket con;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String message;
    private ConcurrentLinkedQueue<String> messages;
    private BoardData model;
    private boolean activeConnection;

    public ClientToServerConnection(int port, BoardData model,String ip) throws IOException {
            this.con = new Socket(ip, port);
            this.out = new ObjectOutputStream(con.getOutputStream());
            this.in = new ObjectInputStream(con.getInputStream());
            this.message = new String();
            this.model = model;
            this.messages = new ConcurrentLinkedQueue();
            this.activeConnection = true;


        /**
         * Continually reads the objects sent from the connection
         */
        Thread heartBeat = new Thread(){
            public void run(){
                while(activeConnection){
                    try {
                        String msg = (String) in.readObject();
                        messages.add(msg);
                        evaluateMessage();
                        //message = msg;

                    } catch (java.io.EOFException e){
                        System.out.println("The Server Has Closed Your Connection");
                        try {
                            in.close();
                            activeConnection = false;
                            model.clientDisconnectWarning();
                            System.out.println("LOST CONNECTION TO THE SERVER 1");
                        } catch (IOException e1) {
                            //e1.printStackTrace();
                            activeConnection = false;
                            model.clientDisconnectWarning();
                            System.out.println("LOST CONNECTION TO THE SERVER 2");
                        }
                    } catch (IOException e) {
                        try {
                            in.close();
                            activeConnection = false;
                            model.clientDisconnectWarning();
                            System.out.println("LOST CONNECTION TO THE SERVER 3");
                        } catch (IOException e1) {
                            System.out.println("LOST CONNECTION TO THE SERVER 4");
                            //e1.printStackTrace();
                        }
                       // e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                       // e.printStackTrace();
                    }
                }
            }
        };

        heartBeat.setDaemon(true);
        heartBeat.start();
    }

    /**
     * A method for writing and sending objects
     * to the connection
     * @param msg the message to write
     */
    public void write(String msg){
        try {
            out.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Evaluates the message. If it's a random char
     * it's a heartbeat. If it begins with <?xml
     * then it's an update
     */
    public void evaluateMessage() {
        if (model.isMessageToggle()) {
            return;
        } else if(messages.size() > 0) {
            String message = messages.poll();
            if(message.length() > 1) {
               // System.out.println(message);
            }
            if (message.length() < 2) {
                //System.out.println("HeartBeat message: " + message);
            } else if (message.substring(0, 5).equals("<?xml")) {
                ObjectParser.parseRequest(model, message);
                System.out.println("Read XML: " + model.getIdentityToken());

            }
        }
    }


    public Socket getCon() {
        return con;
    }

    public void setCon(Socket con) {
        this.con = con;
    }

    public boolean isActiveConnection() {
        return activeConnection;
    }

    public void setActiveConnection(boolean activeConnection) {
        this.activeConnection = activeConnection;
    }
}
