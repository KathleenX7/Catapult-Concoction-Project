/**
 * Room.java
 * Kathleen Xiong
 * June 17th 2022
 * Rooms containing Map Objects and allow the player to move around
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Room extends Screen{
    private BufferedImage backgroundImage;
    private ArrayList<MapObject> items;

    public Room(){
        super("1");
        items = new ArrayList<MapObject>();
    }

    public void setBackground(BufferedImage backgroundImage){
        this.backgroundImage = backgroundImage;
    }
    public void addItem(MapObject item){
        items.add(item);
    }
    public void removeIndex(int ind){
        items.remove(ind);
    }
    //getters 
    public BufferedImage getBackground(){
        return this.backgroundImage;
    }
    public ArrayList<MapObject> getItems(){
        return items;
    }
}
