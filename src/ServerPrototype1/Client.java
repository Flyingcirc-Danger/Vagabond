package ServerPrototype1;

import Prototype5.*;

/**
 * Created by Tom_Bryant on 7/7/15.
 * Client class for sending/recieving messages from the connection
 */
public class Client {

    public ClientToServerConnection connection;
    public BoardData model;


    public Client(int port, BoardData model,String ip){
        this.model = model;
        this.connection = new ClientToServerConnection(port,model,ip);
        /**
         * Automatically sends messages recieved from the connection
         * back to the connection. (HeartBeat).
         */
        Thread reply = new Thread(){
            public void run() {
                while (true) {
                    if(model.manifestReady){
                        connection.write(model.getManifestString());
                    } else {
                        connection.write(connection.getMessage());
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        reply.setDaemon(true);
        reply.start();
    }



}
