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

    public ClientToServerConnection(int port, BoardData model,String ip) throws IOException {
            this.con = new Socket(ip, port);
            this.out = new ObjectOutputStream(con.getOutputStream());
            this.in = new ObjectInputStream(con.getInputStream());
            this.message = new String();
            this.model = model;
            this.messages = new ConcurrentLinkedQueue();


        /**
         * Continually reads the objects sent from the connection
         */
        Thread heartBeat = new Thread(){
            public void run(){
                while(true){
                    try {
                        evaluateMessage();
                        String msg = (String) in.readObject();
                        messages.add(msg);
                        evaluateMessage();
                        //message = msg;
                    } catch (java.io.EOFException e){
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
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
            System.out.println("Evaluating the message");
            String message = messages.poll();
            if (message.length() < 2) {
                //System.out.println("HeartBeat message: " + message);
            } else if (message.substring(0, 5).equals("<?xml")) {
                ObjectParser.parseRequest(model, message);
                System.out.println("Read XML: " + model.getIdentityToken());

            }
        }
    }

}
