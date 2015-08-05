package Prototype5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
    private boolean stealFlag;
    private int stealFromID;
    private HashMap<String, ArrayList<DevelopmentCard>> playerDeck;

    public PlayerInfo(String uname, int score,int id){
        this.uname = uname;
        this.score = score;
        this.id = id;
        this.resources = new int[]{4,4,4,4,4};
        stealFlag = false;
        this.playerDeck = new HashMap<String,ArrayList<DevelopmentCard>>();
    }


    public PlayerInfo(int id){
        this.id = id;
        this.resources = new int[]{4,2,4,4,4};
        this.playerDeck = new HashMap<String,ArrayList<DevelopmentCard>>();
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

    public boolean isStealFlag() {
        return stealFlag;
    }

    public void setStealFlag(boolean stealFlag) {
        this.stealFlag = stealFlag;
    }

    public int getStealFromID() {
        return stealFromID;
    }

    public void setStealFromID(int stealFromID) {
        this.stealFromID = stealFromID;
    }


    /**
     * Adds a development card to the deck.
     * @param toAdd the card to add
     */
    public void addToDeck(DevelopmentCard toAdd){
        //special condition for the knights. To prevent playing on turn of purchase
        String type = toAdd.getType();
        if(toAdd.isTurnOfPurchase()){
            //knights who have just been purchased are classified as inactive.
            type = "InactiveKnight";
        }
        ArrayList<DevelopmentCard> temp = new ArrayList<DevelopmentCard>();
        if(playerDeck.containsKey(type)){
            temp = playerDeck.get(type);
        }
        temp.add(toAdd);
        playerDeck.put(type, temp);
    }


    public HashMap<String,ArrayList<DevelopmentCard>> getPlayerDeck(){
        return this.playerDeck;
    }


    /**
     * Removes a specified development card from a the player deck
     * @param toRemove
     */
    public void removeFromDeck(DevelopmentCard toRemove){
        if(toRemove.isInPlayerDeck()) {
            ArrayList<DevelopmentCard> temp = playerDeck.get(toRemove.getType());
            int i = 0;
            while (temp.get(i).getId() != toRemove.getId()){
                i++;
            }
            temp.remove(i);
            //remove all record if this is the last card.
            if(temp.size() == 0){
                playerDeck.remove(toRemove.getType());
            }
        }
    }

    /**
     * Called at the start of each turn, any knights that
     * are in the deck when the turn begins are made active
     */
    public void makeKnightsActive(){
        if(playerDeck.containsKey("InactiveKnight")){
            ArrayList<DevelopmentCard> inactiveKnights = playerDeck.get("InactiveKnight");
            for(int i = 0; i < inactiveKnights.size(); i++){
                //set turn of purchase to false
                inactiveKnights.get(i).setTurnOfPurchase(false);
                //add them to deck as regular knights
                addToDeck(inactiveKnights.get(i));
            }
            //remove inactive knights from the hashmap
            playerDeck.remove("InactiveKnight");
        }
    }


    /**
     * Returns the total resource count of this player
     * @return the total resource count of this player.
     */
    public int getResourceCount(){
        return getLogs() + getGrain() + getWool() + getBrick() + getOre();
    }


    /**
     * Gets the ids of all the resources this player has
     * @return an arraylist of the resources this player has
     */
    public ArrayList<Integer> getRemainingResources(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < resources.length;i++){
            if(resources[i] != 0){
                result.add(i);
            }
        }
        return result;

    }

    /**
     * Initates a steal. Only called if the steal flag is true.
     */
    public int stealResource() {
        Random rand = new Random();
        ArrayList<Integer> remaining = new ArrayList<Integer>();
        for(int i = 0; i < resources.length;i++){
            if(resources[i] > 0){
                remaining.add(i);
            }
        }
        //Triple random for seed randomness
        int index = rand.nextInt(remaining.size());
        index = rand.nextInt(remaining.size());
        index = rand.nextInt(remaining.size());
        int resource = remaining.get(index);

        System.out.println("I lose resource ID: " + resource);
        if(resource == 0){
            subtractGrain(1);
            return 1;
        }
        if(resource== 1){
            subtractOre(1);
            return 2;
        }
        if(resource == 2){
            subtractWool(1);
            return 3;
        }
        if(resource == 3){
            subtractBrick(1);
            return 4;
        }
        else{
            subtractLogs(1);
            return 5;
        }

    }

    /**
     * The initiators result of a steal
     * give the initator the specified resource
     * @param resource the int identifier of the resource
     * @return
     */
    public void  giveResource(int resource){
        System.out.println("I gain resource ID: " + resource);
        if(resource == 1){
            addGrain(1);
        }
        if(resource == 2){
            addOre(1);
        }
        if(resource == 3){
            addWool(1);
        }
        if(resource == 4){
            addBrick(1);
        }
        if(resource == 5){
            addLogs(1);
        }

    }
}
