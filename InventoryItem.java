/**
 * InventoryItem.java
 * Kathleen Xiong
 * June 17th 2022
 * Items that go into your inventory
 */

import java.io.IOException;
import java.awt.image.BufferedImage;

public class InventoryItem{
    
    private String type;
    private BufferedImage image;
    public InventoryItem(String type) throws IOException{
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public BufferedImage getImage(){
        return this.image;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
}