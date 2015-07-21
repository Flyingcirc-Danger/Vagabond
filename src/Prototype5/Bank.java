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


    public Bank(Board parent){
        this.parent = parent;
        this.open = false;
        this.items = new boolean[5];

    }

    /**
     * Display the closed version of the menu
     */
    public void displayClosed(){
        items = new boolean[5];
        parent.fill(121, 85, 72);
        parent.stroke(0,0,0,0);
        parent.rect(10,60,30,30);
        parent.image(parent.images[6],10,60);
        parent.fill(0, 0, 0,30);
        parent.rect(12,62,30,30);
    }

    /**
     * Display the open version of the menu
     * Icons display greyed out if you can't afford to trade
     */
    public void displayOpen(){
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
                if (Listeners.overRect(40, 170, 30, 30, parent)) {
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
                    if (Listeners.overRect(70, 170, 30, 30, parent)) {
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
                        if (Listeners.overRect(100, 170, 30, 30, parent)) {
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
                if (Listeners.overRect(130, 170, 30, 30, parent)) {
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
                if (Listeners.overRect(160, 170, 30, 30, parent)) {
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
    public void checkButtons(){
        if(Listeners.overRect(10,60,30,30,parent)) {
            if (this.open) {
                System.out.println("BANK OFF");
                this.open = false;
                return;
            } else {
                this.open = true;
                System.out.println("BANK ON");
                return;
            }
        }
        if(open){
                if(Listeners.overRect(40,60,30,30,parent)){
                    this.items = new boolean[5];
                    if(this.items[0]){
                        this.items[0] = false;
                    } else {
                        this.items[0] = true;
                    }
                }
            if(Listeners.overRect(70,60,30,30,parent)){
                this.items = new boolean[5];
                if(this.items[1]){
                    this.items[1] = false;
                } else {
                    this.items[1] = true;
                }
            }
            if(Listeners.overRect(100,60,30,30,parent)){
                this.items = new boolean[5];
                if(this.items[2]){
                    this.items[2] = false;
                } else {
                    this.items[2] = true;
                }
            }
            if(Listeners.overRect(130,60,30,30,parent)){
                this.items = new boolean[5];
                if(this.items[3]){
                    this.items[3] = false;
                } else {
                    this.items[3] = true;
                }
            }
            if(Listeners.overRect(160,60,30,30,parent)){
                this.items = new boolean[5];
                if(this.items[4]){
                    this.items[4] = false;
                } else {
                    this.items[4] = true;
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
                parent.text("Requires 4 Logs", (95 - (parent.textWidth("Requires 4 Logs")/2)),  105);
            }
        }



    }


    /**
     * Displays the menu either open or closed
     */
    public void display(){
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
}



