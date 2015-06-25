package Prototype2;

import java.awt.*;

/**
 * Created by Tom_Bryant on 6/25/15.
 * A Basic class for linking edge coordinates
 * to their ordered tile
 */
public class EdgeCoord implements Comparable<EdgeCoord> {

    public Point coord;
    public String angle;
    public int id;


    public EdgeCoord(Point coord, String id) {
        this.coord = coord;
        splitID(id);
    }

    public void splitID(String id){
        char[] temp = id.toCharArray();
        if(temp.length > 2){
            this.id = Integer.parseInt(new String(temp[0] + "" + temp[1]));
            this.angle = new String(temp[2] + "");
        } else{
            this.id = Integer.parseInt(new String(temp[0] + ""));
            this.angle = new String(temp[1] + "");
        }
    }

    @Override
    public int compareTo(EdgeCoord o) {
        if(this.id == o.id){
            return this.angle.compareTo(o.angle);
        } else if(this.id < o.id){
            return -1;
        } else{
            return 1;
        }

    }
}


