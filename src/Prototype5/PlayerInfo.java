package Prototype5;

/**
 * Created by Tom_Bryant on 7/9/15.
 * A class containing the specific info of this player.
 */
public class PlayerInfo {

    private String uname;
    private int score;
    private int id;
    //[grain,ore,wool,brick,logs]
    private int[] resources;

    public PlayerInfo(String uname, int score,int id){
        this.uname = uname;
        this.score = score;
        this.id = id;
        this.resources = new int[]{4,4,4,4,4};
    }


    public PlayerInfo(int id){
        this.id = id;
        this.resources = new int[]{4,4,4,4,4};

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

    public void addGrain(int amount){
        this.resources[0] += amount;
    }

    public void subtractGrain(int amount){
        this.resources[0] -= amount;
    }

    public int getGrain(){
        return this.resources[0];
    }


    public void addOre(int amount){
        this.resources[1] += amount;
    }


    public void subtractOre(int amount){
        this.resources[1] -= amount;
    }

    public int getOre(){
        return this.resources[1];
    }


    public void addWool(int amount){
        this.resources[2] += amount;
    }

    public void subtractWool(int amount){
        this.resources[2] -= amount;
    }

    public int getWool(){
        return this.resources[2];
    }

    public void addBrick(int amount){
        this.resources[3] += amount;
    }

    public void subtractBrick(int amount){
        this.resources[3] -= amount;
    }

    public int getBrick(){
        return this.resources[3];
    }

    public void addLogs(int amount){
        this.resources[4] += amount;
    }

    public void subtractLogs(int amount){
        this.resources[4] -= amount;
    }

    public int getLogs(){
        return this.resources[4];
    }

}
