/**
 * Tool.java
 * Kathleen Xiong
 * June 17th 2022
 * InventoryItems that are used to destroy materials
 */

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tool extends InventoryItem{
    public Tool(String type) throws IOException{
        super(type);

        if(type.equals("Axe")){
            this.setImage(ImageIO.read(new File("./Images/Tools/Axe.png")));
        }else if(type.equals("Scissors")){
            this.setImage(ImageIO.read(new File("./Images/Tools/Scissors.png")));
        }
        
    }
}