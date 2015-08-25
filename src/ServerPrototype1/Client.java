package ServerPrototype1;

import Prototype5.*;

import java.io.IOException;

/**
 * Created by Tom_Bryant on 7/7/15.
 * Client class for sending/recieving messages from the connection
 */
public class Client {

    public ClientToServerConnection connection;
    public BoardData model;


    public Client(int port, BoardData model,String ip) throws IOException {
        this.model = model;
        this.connection = new ClientToServerConnection(port,model,ip);
        /**
         * Automatically sends messages received from the connection
         * back to the connection. (HeartBeat).
         */
        Thread reply = new Thread(){
            public void run() {
                while (true) {
                    if(model.manifestReady){
                        System.out.println("Player " + model.getPlayer().getId() + " sent");
                        connection.write(model.getManifestString());
                    } else if(model.alertReady){
                        System.out.println("Sent Alert");
                        connection.write(model.getAlertString());
                    } else if(model.tradeReady){
                        System.out.println("Sent Trade");
                        String trade = model.getTradeManifestString();
                        connection.write(trade);
                    } else if(model.stealReady){
                        System.out.println("Sent Steal");
                        String steal = model.getStealManifestString();
                        connection.write(steal);
                    } else if(model.cardReady){
                        System.out.println("Sent Card");
                        String card = model.getCardManifestString();
                        connection.write(card);
                    } else if(model.bonusReady){
                        System.out.println("Sent victory bonus");
                        String bonus = model.getBonusManifestString();
                        connection.write(bonus);
                    } else if(model.winReady){
                        System.out.println("Sent Win Dialogue");
                        String win = model.getWinManifestString();
                        connection.write(win);
                    } else if(connection.getMessage().length() > 0){
                        //HANDLES Replies. If the message is an XML reply
                        String sendMessage = connection.getMessage();
                        if(connection.getMessage().length() > 1){
                            StringBuffer reply = new StringBuffer("REPLY");
                            reply.append(connection.getMessage());
                            sendMessage = reply.toString();
                        }
                        connection.write(sendMessage);
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


    public ClientToServerConnection getConnection() {
        return connection;
    }

    public void setConnection(ClientToServerConnection connection) {
        this.connection = connection;
    }
}
