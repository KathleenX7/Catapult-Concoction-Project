/**
 * CollectableObject.java
 * Kathleen Xiong
 * June 17th 2022
 * Type of MapObject that can be collected (put in inventory) but does not require a specific tool
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CollectableObject extends MapObject{
    private InventoryItem dropped;
    private BufferedImage text;
    
    public CollectableObject(int x, int y, BufferedImage image, String objectType, InventoryItem dropped){
        super(x,y, image, objectType); 
        this.dropped = dropped;
        this.setText();
    }
    //setters
    public void setText(){
        try{
            text = ImageIO.read(new File("./Images/Text/PressC.png"));
        }catch(IOException e){}
    }
    //getters
    public BufferedImage getText(){
        return this.text;
    }
    public InventoryItem getDropped(){
        return this.dropped;
    }
}
