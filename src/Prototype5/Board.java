package Prototype5;


import ServerPrototype1.Client;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The main board class. Builds the datastructure
 * displays & read/writesGIG the board
 * Created by Tom_Bryant on 6/25/15.
 */
public class Board extends PApplet {

    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public HexTile center;
    public Debug debugger;
    public BoardData model;

    public BottomMenu bottom;

    public int currentTool;

    public GameMenu gMenu;

    public PImage img;

    public Client client;




    public void setup() {

        SCREEN_HEIGHT = 768 ;
        SCREEN_WIDTH = 1024;

        size(SCREEN_WIDTH,SCREEN_HEIGHT);
        model = new BoardData(this);
        this.client = new Client(4001,model);
        this.center=new HexTile(this, SCREEN_WIDTH/2, SCREEN_HEIGHT/2,50, model,model.getResourceTiles()[0],model.getTokens()[0]);
        //center.getModel().buildBoard((center));
        this.debugger = new Debug(this,center);
        textSize(14);
        background(0, 188, 212);
        debugger.displayClosed();
        this.bottom = new BottomMenu(this);
        this.currentTool = 0;
        this.gMenu = new GameMenu(this, 300,400);
        this.img = loadImage("assets/logoSM.png");


//        try {
//          ObjectParser.readSides(model,"points.xml");
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }


    }





    public void draw() {



        fill(255, 0, 0, 0);

        fill(0);
        center.getModel().displayBoard();
        if(debugger.open){
            debugger.displayOpen();
        }
        if(!debugger.open){
            background(0, 188, 212);

            debugger.displayClosed();
            center.getModel().displayBoard();
        }


        fill(0);

        bottom.display();
        image(img, SCREEN_WIDTH - 220, SCREEN_HEIGHT - 70);
    }




    @Override
    public void mousePressed() {
        debugger.mouseDebug();
        bottom.checkSelected();
        model.checkSelected(this.currentTool);
    }





    public static void main(String args[]) {
        PApplet.main(new String[] {  "Prototype5.Board" });
    }
    //"--present",
}
