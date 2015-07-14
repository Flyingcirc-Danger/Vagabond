package Prototype5;

/**
 * Created by Tom_Bryant on 7/9/15.
 * A class containing the specific info of this player.
 */
public class PlayerInfo {

    private String uname;
    private int score;
    private int id;

    public PlayerInfo(String uname, int score,int id){
        this.uname = uname;
        this.score = score;
        this.id = id;
    }


    public PlayerInfo(int id){
        this.id = id;

    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
