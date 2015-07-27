package ServerPrototype1;


import Prototype5.*;
import Prototype5.ObjectParser;

/**
 * Created by Tom_Bryant on 7/14/15.
 * A class containing all the information about
 * a player in a game. Adds the extra socket for
 * use on the server
 */
public class Player {

    private ServerToClientConnection connection;
    private PlayerInfo playerInfo;
    private BoardData model;


    public Player(ServerToClientConnection connection, int id, String userName, int score,BoardData model){
        this.connection = connection;
        this.playerInfo = new PlayerInfo(userName, score,id);
        this.model = model;
    }


    public void send(String message){
        connection.write(message);
    }

    public ServerToClientConnection getConnection() {
        return connection;
    }

    public void setConnection(ServerToClientConnection connection) {
        this.connection = connection;
    }

    public int getId() {
        return playerInfo.getId();
    }

    public void setId(int id) {
        this.playerInfo.setId(id);
    }

    public String getUserName() {
        return playerInfo.getUname();
    }

    public void setUserName(String userName) {
        this.playerInfo.setUname(userName);
    }

    public int getScore() {
        return playerInfo.getScore();
    }

    public void setScore(int score) {
        this.playerInfo.setScore(score);
    }

    public String getPlayerXML(){
        return ObjectParser.parseNewPlayer(getId(), model, true);
    }


}
