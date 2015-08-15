package Prototype5;

/**
 * Created by Tom_Bryant on 7/14/15.
 * A Menu for displaying status messages.
 */
public class StatusMenu {

    private String text;
    private Board parent;
    private boolean button;
    private boolean highighted;
    private boolean active;
    private String hostIp;
    private boolean host;
    private Button randomButton;
    private boolean mapToggle;

    public StatusMenu(String text, Board parent, boolean button,boolean active){
        this.text = text;
        this.parent = parent;
        this.button = button;
        this.highighted = false;
        this.active = active;
        randomButton = new Button(200,40,"Randomize", parent);
        randomButton.setStartX(parent.SCREEN_WIDTH / 2 - 100);
        randomButton.setStartY(parent.SCREEN_HEIGHT - 70);
        mapToggle = true;
    }

    public StatusMenu(String text, Board parent, boolean button,boolean active, String hostIp,boolean host){
        this.text = text;
        this.parent = parent;
        this.button = button;
        this.highighted = false;
        this.active = active;
        this.hostIp = hostIp;
        this.host = host;
        randomButton = new Button(200,40,"Randomize", parent);
        randomButton.setStartX(parent.SCREEN_WIDTH / 2 - 100);
        randomButton.setStartY(parent.SCREEN_HEIGHT - 70);
        mapToggle = true;
    }

    public void display(){
        parent.background(0, 188, 212);
        if(host){
            if(mapToggle) {
                parent.game.getMainGame().mainBoard.displayBoardStructure();
            }
        }
        parent.stroke(0, 0, 0, 0);
        parent.smooth(8);
        parent.fill(0, 0, 0, 70);
        parent.rect(0, 0, parent.SCREEN_WIDTH, parent.SCREEN_HEIGHT);
        parent.textSize(20);
        int length = (int) parent.textWidth(text) + 40;
        int height = 60;
        parent.fill(255);
        parent.textAlign(parent.CENTER);
        parent.textSize(20);
        parent.text("You are connected to the game at IP: " + hostIp, parent.SCREEN_WIDTH/2, 40);
        if(host){
            parent.text("Players Ready: " + parent.game.mainGame.readyPlayers + "/" +
                    parent.game.mainGame.players.size(), parent.SCREEN_WIDTH/2, 80);
        }
        parent.textAlign(parent.LEFT);
        if(button){
            height = 80;
            parent.textSize(20);
            parent.fill(121, 85, 72);
            parent.stroke(0,0,0,0);
            parent.rect(parent.SCREEN_WIDTH/2 - (length/2), parent.SCREEN_HEIGHT/2 - (height/2), length, height);
            parent.fill(0,0,0,30);
            parent.rect(parent.SCREEN_WIDTH/2 - (length/2)+2, parent.SCREEN_HEIGHT/2 - (height/2)+2, length, height);
            parent.fill(255);
            parent.textFont(parent.fonts[0]);
            parent.textSize(20);
            parent.text(text,parent.SCREEN_WIDTH/2 - (parent.textWidth(text)/2), parent.SCREEN_HEIGHT/2 - 10);
            DialogueButton("Ready");
        } else{
            parent.textSize(20);
            parent.fill(121, 85, 72);
            parent.stroke(0,0,0,0);
            parent.rect(parent.SCREEN_WIDTH/2 - (length/2), parent.SCREEN_HEIGHT/2 - (height/2), length, height);
            parent.fill(0, 0, 0, 30);
            parent.rect(parent.SCREEN_WIDTH / 2 - (length / 2) + 2, parent.SCREEN_HEIGHT/2 - (height/2)+2, length, height);
            parent.fill(255);
            parent.text(text,parent.SCREEN_WIDTH/2 - (parent.textWidth(text)/2), parent.SCREEN_HEIGHT/2 + 10);
        }
        if(host){
            if(parent.game.getMainGame().boardType.equals("Random")){
                randomButton.display();
            }
        }

    }


    public void DialogueButton(String bText){
        int length = (int) parent.textWidth(bText) + 40;
        int height = 30;
        parent.textSize(20);
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect(parent.SCREEN_WIDTH/2 - (length/2), parent.SCREEN_HEIGHT/2 , length, height);
        parent.fill(0,0,0,30);
        if(highighted){
            parent.rect(parent.SCREEN_WIDTH/2 - (length/2)-2, parent.SCREEN_HEIGHT/2 -2, length, height);
        } else {
            parent.rect(parent.SCREEN_WIDTH / 2 - (length / 2) + 2, parent.SCREEN_HEIGHT / 2 + 2, length, height);
        }
        parent.fill(255);
        parent.text(bText,parent.SCREEN_WIDTH/2 - (parent.textWidth(bText)/2), parent.SCREEN_HEIGHT/2 + (22));
    }


    /**
     * A method for checking a rectangular
     * space for a mouse click
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    boolean overRect(int x, int y, int width, int height) {
        if (parent.mouseX >= x && parent.mouseX <= x + width &&
                parent.mouseY >= y && parent.mouseY <= y + height) {
            return true;
        } else {
            return false;
        }
    }


    public boolean checkButton(){
        if(host){
            if(parent.game.getMainGame().boardType.equals("Random")){
                if(parent.mousePressed) {
                    if (randomButton.checkButton()) {
                        mapToggle = false;
                        parent.game.getMainGame().buildNewRandomBoard();
                        parent.game.getMainGame().mainBoard.setParent(parent);
                        parent.game.getMainGame().mainBoard.hostSync();
                        mapToggle = true;
                    }
                }
            }

        }
        if(!active){
            return false;
        }
        int length = (int) parent.textWidth("Ready") + 40;
        int height = 30;
        if(overRect(parent.SCREEN_WIDTH/2 - (length/2), parent.SCREEN_HEIGHT/2, length, height)){
            this.highighted = true;
            return true;
        }
        else {
            this.highighted = false;
            return false;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }
}
