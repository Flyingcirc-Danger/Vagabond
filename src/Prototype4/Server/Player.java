package Prototype4.Server;

import com.sun.xml.internal.bind.v2.TODO;

import java.net.Socket;

/**
 * Created by Tom_Bryant on 7/7/15.
 * Contains the Socket & the send / recieve methods
 */
public class Player implements Runnable {

    private Socket connection;
    private boolean alive;


    public Player(Socket connection){
        this.connection = connection;
    }


    @Override
    public void run() {

    }


    //TODO implement message send


    //TODO implement message recieve
}
