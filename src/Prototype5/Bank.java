package Prototype5;


/**
 * Created by Tom_Bryant on 7/20/15.
 * A class that contains the bank menu,
 * for buying and trading with the bank.
 */
public class Bank {

    private Board parent;
    private boolean open;
    private boolean [] items;
    private boolean dialogue; //checks to see if the banking dialogue is open
    private String bankItem;
    private String playerItem;


    public Bank(Board parent){
        this.parent = parent;
        this.open = false;
        this.items = new boolean[5];
        this.dialogue = false;

    }

    /**
     * Display the closed version of the menu
     */
    public void displayClosed(){
        items = new boolean[5];
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect(10,60,30,30);
        if(parent.model.getPlayer().getId() == parent.model.getPlayerTurn()) {
            parent.image(parent.images[6], 10, 60);
        } else{
            parent.image(parent.images[25], 10, 60);
        }
        parent.fill(0, 0, 0,30);
        parent.rect(12,62,30,30);
        //player trade menu
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect(10,parent.SCREEN_HEIGHT - 100,30,30);
        if(parent.model.getPlayer().getId() == parent.model.getPlayerTurn()) {
            parent.image(parent.images[19],10,parent.SCREEN_HEIGHT - 100);
        } else{
            parent.image(parent.images[26],10,parent.SCREEN_HEIGHT - 100);
        }
        parent.fill(0, 0, 0,30);
        parent.rect(12,parent.SCREEN_HEIGHT - 100,30,30);
    }

    /**
     * Display the open version of the menu
     * Icons display greyed out if you can't afford to trade
     */
    public void displayOpen(){
        //player trade menu
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect(10,parent.SCREEN_HEIGHT - 100,30,30);
        parent.image(parent.images[19],10,parent.SCREEN_HEIGHT -100);
        parent.fill(0, 0, 0,30);
        parent.rect(12,parent.SCREEN_HEIGHT - 100,30,30);

        //bank trade menu
        parent.fill(62,39,35);
        parent.stroke(0,0,0,50);
        parent.rect(10,60,30,30);
        parent.image(parent.images[6],10,60);
        parent.stroke(0,0,0,0);
        parent.fill(0,0,0,30);
        parent.rect(12,62,30,30);


        parent.fill(121,85,72);
        parent.stroke(0,0,0,50);
        parent.rect(40,60,30,30);
        if(parent.model.getPlayer().getGrain() >= 4) {
            parent.image(parent.images[1], 40, 60);
        } else {
            parent.image(parent.images[7], 40, 60);
        }
        parent.stroke(0,0,0,0);
        parent.fill(0,0,0,30);
        parent.rect(42,62,30,30);

        parent.fill(121,85,72);
        parent.stroke(0,0,0,50);
        parent.rect(70,60,30,30);
        if(parent.model.getPlayer().getOre() >= 4) {
            parent.image(parent.images[2], 70, 60);
        } else {
            parent.image(parent.images[8], 70, 60);
        }
        parent.stroke(0,0,0,0);
        parent.fill(0,0,0,30);
        parent.rect(72,62,30,30);

        parent.fill(121,85,72);
        parent.stroke(0,0,0,50);
        parent.rect(100,60,30,30);
        if(parent.model.getPlayer().getWool() >= 4) {
            parent.image(parent.images[3], 100, 60);
        } else {
            parent.image(parent.images[9], 100, 60);
        }
        parent.stroke(0,0,0,0);
        parent.fill(0,0,0,30);
        parent.rect(102,62,30,30);

        parent.fill(121,85,72);
        parent.stroke(0,0,0,50);
        parent.rect(130,60,30,30);
        if(parent.model.getPlayer().getBrick() >= 4) {
            parent.image(parent.images[4], 130, 60);
        } else {
            parent.image(parent.images[10], 130, 60);
        }
        parent.stroke(0,0,0,0);
        parent.fill(0,0,0,30);
        parent.rect(132,62,30,30);

        parent.fill(121,85,72);
        parent.stroke(0,0,0,50);
        parent.rect(160,60,30,30);
        if(parent.model.getPlayer().getLogs() >= 4) {
            parent.image(parent.images[5], 160, 60);
        } else {
            parent.image(parent.images[11], 160, 60);
        }
        parent.stroke(0,0,0, 0);
        parent.fill(0,0,0,30);
        parent.rect(162,62,30,30);
        for(int i = 0; i < items.length; i++){
            if(items[i]){
                displayDown(i+1);
            }
        }
        tradeSelect();

    }

    /**
     * Once a trading resource is selected, this checks to see which item you wish to buy.
     */
    public void tradeSelect(){
        PlayerInfo player = parent.model.getPlayer();
        if(items[0]){
            if(player.getGrain() >= 4) {
                if (Listeners.overRect(40, 90, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(40, 90, 30, 30);
                    parent.image(parent.images[2], 40, 90);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(42, 92, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Wheat for 1 Ore", 75, 110);

                }
                if (Listeners.overRect(40, 120, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(40, 120, 30, 30);
                    parent.image(parent.images[3], 40, 120);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(42, 122, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Wheat for 1 Wool", 75, 140);

                }
                if (Listeners.overRect(40, 150, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(40, 150, 30, 30);
                    parent.image(parent.images[4], 40, 150);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(42, 152, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Wheat for 1 Brick", 75, 170);
                }
                if (Listeners.overRect(40, 180, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(40, 180, 30, 30);
                    parent.image(parent.images[5], 40, 180);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(42, 182, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Wheat for 1 Log", 75, 200);

                }
            }




        }



            if(items[1]) {
                if(player.getOre() >= 4) {
                    if (Listeners.overRect(70, 90, 30, 30, parent)) {
                        parent.fill(245, 127, 23);
                        parent.stroke(0, 0, 0, 50);
                        parent.rect(70, 90, 30, 30);
                        parent.image(parent.images[1], 70, 90);
                        parent.stroke(0, 0, 0, 0);
                        parent.fill(0, 0, 0, 30);
                        parent.rect(72, 92, 30, 30);
                        parent.fill(255);
                        parent.textSize(12);
                        parent.text("4 Ore for 1 Wheat", 105, 110);

                    }
                    if (Listeners.overRect(70, 120, 30, 30, parent)) {
                        parent.fill(245, 127, 23);
                        parent.stroke(0, 0, 0, 50);
                        parent.rect(70, 120, 30, 30);
                        parent.image(parent.images[3], 70, 120);
                        parent.stroke(0, 0, 0, 0);
                        parent.fill(0, 0, 0, 30);
                        parent.rect(72, 122, 30, 30);
                        parent.fill(255);
                        parent.textSize(12);
                        parent.text("4 Ore for 1 Wool", 105, 140);

                    }
                    if (Listeners.overRect(70, 150, 30, 30, parent)) {
                        parent.fill(245, 127, 23);
                        parent.stroke(0, 0, 0, 50);
                        parent.rect(70, 150, 30, 30);
                        parent.image(parent.images[4], 70, 150);
                        parent.stroke(0, 0, 0, 0);
                        parent.fill(0, 0, 0, 30);
                        parent.rect(72, 152, 30, 30);
                        parent.fill(255);
                        parent.textSize(12);
                        parent.text("4 Ore for 1 Brick", 105, 170);
                    }
                    if (Listeners.overRect(70, 180, 30, 30, parent)) {
                        parent.fill(245, 127, 23);
                        parent.stroke(0, 0, 0, 50);
                        parent.rect(70, 180, 30, 30);
                        parent.image(parent.images[5], 70, 180);
                        parent.stroke(0, 0, 0, 0);
                        parent.fill(0, 0, 0, 30);
                        parent.rect(72, 182, 30, 30);
                        parent.fill(255);
                        parent.textSize(12);
                        parent.text("4 Ore for 1 Log", 105, 200);

                    }
                }
            }

                if(items[2]){
                    if(player.getWool() >= 4) {
                        if (Listeners.overRect(100, 90, 30, 30, parent)) {
                            parent.fill(245, 127, 23);
                            parent.stroke(0, 0, 0, 50);
                            parent.rect(100, 90, 30, 30);
                            parent.image(parent.images[1], 100, 90);
                            parent.stroke(0, 0, 0, 0);
                            parent.fill(0, 0, 0, 30);
                            parent.rect(102, 92, 30, 30);
                            parent.fill(255);
                            parent.textSize(12);
                            parent.text("4 Wool for 1 Wheat", 135, 110);

                        }
                        if (Listeners.overRect(100, 120, 30, 30, parent)) {
                            parent.fill(245, 127, 23);
                            parent.stroke(0, 0, 0, 50);
                            parent.rect(100, 120, 30, 30);
                            parent.image(parent.images[2], 100, 120);
                            parent.stroke(0, 0, 0, 0);
                            parent.fill(0, 0, 0, 30);
                            parent.rect(102, 122, 30, 30);
                            parent.fill(255);
                            parent.textSize(12);
                            parent.text("4 Wool for 1 Ore", 135, 140);

                        }
                        if (Listeners.overRect(100, 150, 30, 30, parent)) {
                            parent.fill(245, 127, 23);
                            parent.stroke(0, 0, 0, 50);
                            parent.rect(100, 150, 30, 30);
                            parent.image(parent.images[4], 100, 150);
                            parent.stroke(0, 0, 0, 0);
                            parent.fill(0, 0, 0, 30);
                            parent.rect(102, 152, 30, 30);
                            parent.fill(255);
                            parent.textSize(12);
                            parent.text("4 Wool for 1 Brick", 135, 170);
                        }
                        if (Listeners.overRect(100, 180, 30, 30, parent)) {
                            parent.fill(245, 127, 23);
                            parent.stroke(0, 0, 0, 50);
                            parent.rect(100, 180, 30, 30);
                            parent.image(parent.images[5], 100, 180);
                            parent.stroke(0, 0, 0, 0);
                            parent.fill(0, 0, 0, 30);
                            parent.rect(102, 182, 30, 30);
                            parent.fill(255);
                            parent.textSize(12);
                            parent.text("4 Wool for 1 Log", 135, 200);

                        }
                    }

        }

        if(items[3]){
            if(player.getBrick() >= 4) {
                if (Listeners.overRect(130, 90, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(130, 90, 30, 30);
                    parent.image(parent.images[1], 130, 90);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(132, 92, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Brick for 1 Wheat", 165, 110);

                }
                if (Listeners.overRect(130, 120, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(130, 120, 30, 30);
                    parent.image(parent.images[2], 130, 120);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(132, 122, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Brick for 1 Ore", 165, 140);

                }
                if (Listeners.overRect(130, 150, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(130, 150, 30, 30);
                    parent.image(parent.images[3], 130, 150);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(132, 152, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Brick for 1 Wool", 165, 170);
                }
                if (Listeners.overRect(130, 180, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(130, 180, 30, 30);
                    parent.image(parent.images[5], 130, 180);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(132, 182, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Brick for 1 Log", 165, 200);

                }
            }

        }

        if(items[4]) {
            if (player.getLogs() >= 4) {
                if (Listeners.overRect(160, 90, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(160, 90, 30, 30);
                    parent.image(parent.images[1], 160, 90);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(162, 92, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Logs for 1 Wheat", 195, 110);

                }
                if (Listeners.overRect(160, 120, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(160, 120, 30, 30);
                    parent.image(parent.images[2], 160, 120);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(162, 122, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Logs for 1 Ore", 195, 140);

                }
                if (Listeners.overRect(160, 150, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(160, 150, 30, 30);
                    parent.image(parent.images[3], 160, 150);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(162, 152, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Logs for 1 Wool", 195, 170);
                }
                if (Listeners.overRect(160, 180, 30, 30, parent)) {
                    parent.fill(245, 127, 23);
                    parent.stroke(0, 0, 0, 50);
                    parent.rect(160, 180, 30, 30);
                    parent.image(parent.images[4], 160, 180);
                    parent.stroke(0, 0, 0, 0);
                    parent.fill(0, 0, 0, 30);
                    parent.rect(162, 182, 30, 30);
                    parent.fill(255);
                    parent.textSize(12);
                    parent.text("4 Logs for 1 Brick", 195, 200);

                }

            }
        }
    }


    /**
     * Checks to see which buttons have been pressed
     */
    public void checkButtons() {
        if (parent.model.getPlayer().getId() == parent.model.getPlayerTurn()) {
            if (dialogue) {
                int width = (parent.SCREEN_WIDTH / 2) - ((int) (120 + parent.textWidth("Bank Trade")) / 2);
                int buttonWidth = (int) (parent.textWidth("Okay") + 50);
                int startX = (parent.SCREEN_WIDTH / 2) - (width / 2);
                int startY = (parent.SCREEN_HEIGHT / 2) - (174 / 2);
                //okay
                if (Listeners.overRect(startX + (width / 2) - buttonWidth - 10, startY + 174 - 47, buttonWidth, 40, parent)) {
                    PlayerInfo player = parent.model.getPlayer();
                    if (this.bankItem.equals("wheat")) {
                        player.addGrain(1);
                    }
                    if (this.bankItem.equals("brick")) {
                        player.addBrick(1);
                    }
                    if (this.bankItem.equals("ore")) {
                        player.addOre(1);
                    }
                    if (this.bankItem.equals("wool")) {
                        player.addWool(1);
                    }
                    if (this.bankItem.equals("logs")) {
                        player.addLogs(1);
                    }
                    if (this.playerItem.equals("wheat")) {
                        player.subtractGrain(4);
                    }
                    if (this.playerItem.equals("ore")) {
                        player.subtractOre(4);
                    }
                    if (this.playerItem.equals("wool")) {
                        player.subtractWool(4);
                    }
                    if (this.playerItem.equals("brick")) {
                        player.subtractBrick(4);
                    }
                    if (this.playerItem.equals("logs")) {
                        player.subtractLogs(4);
                    }
                    this.dialogue = false;

                }
                //cancel
                if (Listeners.overRect(startX + (width / 2) + 10, startY + 172 - 47, buttonWidth, 40, parent)) {
                    this.dialogue = false;
                }
            } else {
                //trade icon
                if (Listeners.overRect(10, parent.SCREEN_HEIGHT - 100, 30, 30, parent)) {
                    this.parent.model.setDisplayMode(8);
                }
                //bank icon
                if (Listeners.overRect(10, 60, 30, 30, parent)) {
                    if (this.open) {
                        this.open = false;
                        return;
                    } else {
                        this.open = true;
                        return;
                    }
                }
                if (open) {
                    if (Listeners.overRect(40, 60, 30, 30, parent)) {
                        this.items = new boolean[5];
                        if (this.items[0]) {
                            this.items[0] = false;
                        } else {
                            this.items[0] = true;
                            playerItem = "wheat";
                        }
                    }
                    if (Listeners.overRect(70, 60, 30, 30, parent)) {
                        this.items = new boolean[5];
                        if (this.items[1]) {
                            this.items[1] = false;
                        } else {
                            this.items[1] = true;
                            playerItem = "ore";
                        }
                    }
                    if (Listeners.overRect(100, 60, 30, 30, parent)) {
                        this.items = new boolean[5];
                        if (this.items[2]) {
                            this.items[2] = false;
                        } else {
                            this.items[2] = true;
                            playerItem = "wool";
                        }
                    }
                    if (Listeners.overRect(130, 60, 30, 30, parent)) {
                        this.items = new boolean[5];
                        if (this.items[3]) {
                            this.items[3] = false;
                        } else {
                            this.items[3] = true;
                            playerItem = "brick";
                        }
                    }
                    if (Listeners.overRect(160, 60, 30, 30, parent)) {
                        this.items = new boolean[5];
                        if (this.items[4]) {
                            this.items[4] = false;
                        } else {
                            this.items[4] = true;
                            playerItem = "logs";
                        }
                    }
                    //once the menu is open and a
                    //grain
                    if (this.items[0]) {
                        //ore
                        if (Listeners.overRect(40, 90, 30, 30, parent)) {
                            this.bankItem = "ore";
                            dialogue = true;
                        }
                        //wool
                        if (Listeners.overRect(40, 120, 30, 30, parent)) {
                            this.bankItem = "wool";
                            dialogue = true;
                        }
                        //brick
                        if (Listeners.overRect(40, 150, 30, 30, parent)) {
                            this.bankItem = "brick";
                            dialogue = true;
                        }
                        //logs
                        if (Listeners.overRect(40, 180, 30, 30, parent)) {
                            this.bankItem = "logs";
                            dialogue = true;
                        }
                    }
                    //ore
                    if (this.items[1]) {
                        //grain
                        if (Listeners.overRect(70, 90, 30, 30, parent)) {
                            this.bankItem = "wheat";
                            dialogue = true;
                        }
                        //wool
                        if (Listeners.overRect(70, 120, 30, 30, parent)) {
                            this.bankItem = "wool";
                            dialogue = true;
                        }
                        //brick
                        if (Listeners.overRect(70, 150, 30, 30, parent)) {
                            this.bankItem = "brick";
                            dialogue = true;
                        }
                        //logs
                        if (Listeners.overRect(70, 180, 30, 30, parent)) {
                            this.bankItem = "logs";
                            dialogue = true;
                        }
                    }
                    //wool
                    if (this.items[2]) {
                        //grain
                        if (Listeners.overRect(100, 90, 30, 30, parent)) {
                            this.bankItem = "wheat";
                            dialogue = true;
                        }
                        //ore
                        if (Listeners.overRect(100, 120, 30, 30, parent)) {
                            this.bankItem = "ore";
                            dialogue = true;
                        }
                        //brick
                        if (Listeners.overRect(100, 150, 30, 30, parent)) {
                            this.bankItem = "brick";
                            dialogue = true;
                        }
                        //logs
                        if (Listeners.overRect(100, 180, 30, 30, parent)) {
                            this.bankItem = "logs";
                            dialogue = true;
                        }
                    }

                    //bricks
                    if (this.items[3]) {
                        //grain
                        if (Listeners.overRect(130, 90, 30, 30, parent)) {
                            this.bankItem = "wheat";
                            dialogue = true;
                        }
                        //ore
                        if (Listeners.overRect(130, 120, 30, 30, parent)) {
                            this.bankItem = "ore";
                            dialogue = true;
                        }
                        //wool
                        if (Listeners.overRect(130, 150, 30, 30, parent)) {
                            this.bankItem = "wool";
                            dialogue = true;
                        }
                        //logs
                        if (Listeners.overRect(130, 180, 30, 30, parent)) {
                            this.bankItem = "logs";
                            dialogue = true;
                        }
                    }

                    //wood
                    if (this.items[4]) {
                        //grain
                        if (Listeners.overRect(160, 90, 30, 30, parent)) {
                            this.bankItem = "wheat";
                            dialogue = true;
                        }
                        //ore
                        if (Listeners.overRect(160, 120, 30, 30, parent)) {
                            this.bankItem = "ore";
                            dialogue = true;
                        }
                        //wool
                        if (Listeners.overRect(160, 150, 30, 30, parent)) {
                            this.bankItem = "wool";
                            dialogue = true;
                        }
                        //bricks
                        if (Listeners.overRect(160, 180, 30, 30, parent)) {
                            this.bankItem = "brick";
                            dialogue = true;
                        }
                    }
                }
            }


        }
    }

    /**
     * Checks to see if the bank icon has been hovered over
     */
    public void checkHover(){
        if(!isOpen()){
            if(Listeners.overRect(10,60,30,30,parent)) {
                parent.fill(255);
                parent.text("Trade With Bank", 30,90);
            }

        }


    }

    /**
     * Displays the vertical trading menu underneath the selected resource
     * @param imgNo the resource number currently selected.
     */
    public void displayDown(int imgNo){
        if(imgNo == 1) {
            if (parent.model.getPlayer().getGrain() >= 4) {
                parent.fill(62, 39, 35);
                parent.stroke(0, 0, 0, 50);
                parent.rect(40, 60, 30, 30);
                parent.image(parent.images[1], 40, 60);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(42, 62, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(40, 90, 30, 30);
                parent.image(parent.images[2], 40, 90);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(42, 92, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(40, 120, 30, 30);
                parent.image(parent.images[3], 40, 120);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(42, 122, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(40, 150, 30, 30);
                parent.image(parent.images[4], 40, 150);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(42, 152, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(40, 180, 30, 30);
                parent.image(parent.images[5], 40, 180);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(42, 182, 30, 30);

            }
            else{
                parent.fill(255,0,0);
                parent.textSize(12);
                parent.text("Requires 4 Wheat", (95 - (parent.textWidth("Requires 4 Wheat")/2)),  105);
            }
        }

        if(imgNo == 2) {
            if (parent.model.getPlayer().getOre() >= 4) {
                parent.fill(62, 39, 35);
                parent.stroke(0, 0, 0, 50);
                parent.rect(70, 60, 30, 30);
                parent.image(parent.images[2], 70, 60);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(72, 62, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(70, 90, 30, 30);
                parent.image(parent.images[1], 70, 90);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(72, 92, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(70, 120, 30, 30);
                parent.image(parent.images[3], 70, 120);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(72, 122, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(70, 150, 30, 30);
                parent.image(parent.images[4], 70, 150);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(72, 152, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(70, 180, 30, 30);
                parent.image(parent.images[5], 70, 180);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(72, 182, 30, 30);

            }
            else{
                parent.fill(255,0,0);
                parent.textSize(12);
                parent.text("Requires 4 Ore", (95 - (parent.textWidth("Requires 4 Ore")/2)),  105);
            }
        }

        if(imgNo == 3) {
            if (parent.model.getPlayer().getWool() >= 4) {
                parent.fill(62, 39, 35);
                parent.stroke(0, 0, 0, 50);
                parent.rect(100, 60, 30, 30);
                parent.image(parent.images[3], 100, 60);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(102, 62, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(100, 90, 30, 30);
                parent.image(parent.images[1], 100, 90);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(102, 92, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(100, 120, 30, 30);
                parent.image(parent.images[2], 100, 120);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(102, 122, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(100, 150, 30, 30);
                parent.image(parent.images[4], 100, 150);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(102, 152, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(100, 180, 30, 30);
                parent.image(parent.images[5], 100, 180);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(102, 182, 30, 30);

            }
            else{
                parent.fill(255,0,0);
                parent.textSize(12);
                parent.text("Requires 4 Wool", (95 - (parent.textWidth("Requires 4 Wool")/2)),  105);
            }
        }

        if(imgNo == 4) {
            if (parent.model.getPlayer().getBrick() >= 4) {
                parent.fill(62, 39, 35);
                parent.stroke(0, 0, 0, 50);
                parent.rect(130, 60, 30, 30);
                parent.image(parent.images[4], 130, 60);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(132, 62, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(130, 90, 30, 30);
                parent.image(parent.images[1], 130, 90);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(132, 92, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(130, 120, 30, 30);
                parent.image(parent.images[2], 130, 120);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(132, 122, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(130, 150, 30, 30);
                parent.image(parent.images[3], 130, 150);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(132, 152, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(130, 180, 30, 30);
                parent.image(parent.images[5], 130, 180);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(132, 182, 30, 30);

            }
            else{
                parent.fill(255,0,0);
                parent.textSize(12);
                parent.text("Requires 4 Brick", (95 - (parent.textWidth("Requires 4 Brick")/2)),  105);
            }
        }

        if(imgNo == 5) {
            if (parent.model.getPlayer().getLogs() >= 4) {
                parent.fill(62, 39, 35);
                parent.stroke(0, 0, 0, 50);
                parent.rect(160, 60, 30, 30);
                parent.image(parent.images[5], 160, 60);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(162, 62, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(160, 90, 30, 30);
                parent.image(parent.images[1], 160, 90);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(162, 92, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(160, 120, 30, 30);
                parent.image(parent.images[2], 160, 120);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(162, 122, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(160, 150, 30, 30);
                parent.image(parent.images[3], 160, 150);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(162, 152, 30, 30);

                parent.fill(121, 85, 72);
                parent.stroke(0, 0, 0, 50);
                parent.rect(160, 180, 30, 30);
                parent.image(parent.images[4], 160, 180);
                parent.stroke(0, 0, 0, 0);
                parent.fill(0, 0, 0, 30);
                parent.rect(162, 182, 30, 30);

            } else{
                parent.fill(255,0,0);
                parent.textSize(12);
                parent.text("Requires 4 Logs", (95 - (parent.textWidth("Requires 4 Logs")/2)),  105);
            }
        }



    }


    /**
     * Displays the menu either open or closed
     */
    public void display(){
        if(this.dialogue){
            displayDialogue();
        }
        if(this.open){
            displayOpen();
        } else{
            displayClosed();
        }
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


    /**
     * Displays the bank trade dialogue. The player trades
     * 4 x Player Resources for 1 x bank resources
     */
    public void displayDialogue(){
        int bankIndex = 0;
        int playerIndex =0;
        if(bankItem.equals("wheat")) {
            bankIndex = 1;
        }
        if(playerItem.equals("wheat")) {
            playerIndex = 1;
        }
        if(bankItem.equals("ore")) {
            bankIndex = 2;
        }
        if(playerItem.equals("ore")) {
            playerIndex = 2;
        }
        if(bankItem.equals("wool")) {
            bankIndex = 3;
        }
        if(playerItem.equals("wool")) {
            playerIndex = 3;
        }
        if(bankItem.equals("brick")) {
            bankIndex = 4;
        }
        if(playerItem.equals("brick")) {
            playerIndex = 4;
        }
        if(bankItem.equals("logs")) {
            bankIndex = 5;
        }
        if(playerItem.equals("logs")) {
            playerIndex = 5;
        }

        parent.textSize(20);
        int width =  (int)(120 + parent.textWidth("Bank Trade"));
        int height = 174;
        int startX = (parent.SCREEN_WIDTH/2) - (width/2);
        int startY = (parent.SCREEN_HEIGHT/2) - (height/2);
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect(startX,startY, width, height,5,5,5,5);
        parent.image(parent.images[6],startX + 10,startY +5);
        parent.fill(255);
        parent.text("Bank Trade", startX + ((width/2) - parent.textWidth("Bank Trade")/2), startY + 25);
        parent.image(parent.images[6],startX + (width - 40),startY+5);
        parent.textSize(12);
        parent.text("You will receive:", startX + ((width/2) - parent.textWidth("You will receive:")/2), startY + 45);
        parent.image(parent.images[bankIndex],startX + ((width/4)),startY +48);
        parent.text("1 x " + bankItem, startX + (width/4 ) + 40,startY +  64);
        parent.text("You will lose:", startX + ((width/2) - parent.textWidth("You will lose:")/2),startY +  90);
        parent.image(parent.images[playerIndex],startX + ((width/4)),startY +93);
        parent.text("4 x " + playerItem, startX + (width/4) + 40 ,startY +  110);
        //buttons
        parent.fill(244, 67, 54);
        parent.stroke(0, 0, 0, 10);
        int buttonWidth = (int) (parent.textWidth("Okay") + 50);
        parent.rect(startX + (width /2) - buttonWidth - 10, startY + height - 47, buttonWidth,40,5,5,5,5);

        parent.rect(startX + (width /2) + 10, startY + height - 47, buttonWidth,40,5,5,5,5);
        parent.stroke(0, 0, 0, 0);
        parent.fill(0,0,0,30);
        parent.rect(startX + (width /2) - buttonWidth - 10 +2, startY + height - 47 + 2, buttonWidth,40,5,5,5,5);
        parent.rect(startX + (width /2) + 10 +2, startY + height - 47 + 2, buttonWidth,40,5,5,5,5);
        parent.textFont(parent.fonts[1]);


        parent.textSize(15);
        parent.fill(255, 235, 59);
        parent.text("Okay",startX + (width/2) - buttonWidth - 10 + (parent.textWidth("Okay")/2),startY + height -22);
        parent.text("Cancel",startX + (width/2) + 10 + (buttonWidth/2) - (parent.textWidth("Cancel")/2), startY + height -22);

        parent.textFont(parent.fonts[0]);
        parent.fill(0,0,0,30);
        parent.rect(startX+2,startY+2, width, height,5,5,5,5);
    }

    public void setDialogue(boolean dialogue) {
        this.dialogue = dialogue;
    }
}



