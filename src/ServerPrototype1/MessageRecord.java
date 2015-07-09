package ServerPrototype1;

/**
 * Created by Tom_Bryant on 7/9/15.
 * A class for storing the message record of the current
 * and previous one message.
 */
public class MessageRecord {

    private String currentMessage;
    private String lastMessage;


    public MessageRecord(){
        this.currentMessage = new String();
        this.lastMessage = new String();
    }


    /**
     * Sets the current message to msg, provided
     * that msg does not equal the last message.
     * @param msg
     */
    public void setCurrent(String msg){
        if(msg.equals(lastMessage)){
            currentMessage = "";
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
}
