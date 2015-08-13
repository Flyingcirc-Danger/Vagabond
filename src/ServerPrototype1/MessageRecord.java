package ServerPrototype1;

/**
 * Created by Tom_Bryant on 7/9/15.
 * A class for storing the message record of the current
 * and previous one message.
 */
public class MessageRecord {

    private String currentMessage;
    private String lastMessage;
    private String currentTurn;
    private String lastTurn;
    private boolean turnAuth;


    public MessageRecord(){
        this.currentMessage = new String();
        this.lastMessage = new String();
        this.lastTurn = new String();
        this.currentTurn = new String();
        this.turnAuth = false;
    }


    /**
     * Sets the current message to msg, provided
     * that msg does not equal the last message.
     * @param msg
     */
    public void setCurrent(String msg){
        System.out.println("Setting Current");
        if(msg.equals(lastMessage)){
            currentMessage = "";
            System.out.println("Current Cleared");
        } else{
            currentMessage = msg;
        }
    }

    /**
     * Returns the current message,
     * Shifts the current message into
     * the last message. Sets the current
     * message to blank.
     * @return the current message
     */
    public String getCurrent(){
        String result = currentMessage;
        lastMessage = currentMessage;
        currentMessage = "";
        System.out.println("Getting Current");
        return result;
    }

    /**
     * Checks to see if there is a current message
     * which is the case only if the length
     * of the currentMessage
     * is greater than 0;
     * @return true if currentMessage > 0
     */
    public boolean isCurrent(){
        if(currentMessage.length() > 0){
            return true;
        }
        return false;
    }

    /**
     * Checks to see it it's okay to send a turn
     * a turn is only valid for send if the turn auth is true
     * (all players discard) and last turn is > 0.
     * @return
     */
    public boolean checkTurn(){
        if(currentTurn.length() > 0 && turnAuth ){
            return true;
        } else{
            return false;
        }
    }

    public String getTurn(){
        String result = currentTurn;
        lastTurn = currentTurn;
        currentTurn = "";
        return result;
    }

    public void setTurn(String turn){
        if(turn.equals(lastTurn)){
            currentTurn = "";
        } else{
            currentTurn = turn;
        }
    }

    public void setTurnAuth(boolean auth){
        turnAuth = auth;
    }
}
