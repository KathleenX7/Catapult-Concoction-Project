import java.io.File;
import java.io.IOException;
/**
 * Material.java
 * Kathleen Xiong
 * June 17th 2022
 * InventoryItems that are materials (require a certain amount to move onto the next stage)
 */

import javax.imageio.ImageIO;

public class Material extends InventoryItem{
    
    public Material(String type) throws IOException{
        super(type);
        if(type.equals("Wood")){
            this.setImage(ImageIO.read(new File("./Images/Materials/Wood.png")));
        }else if(type.equals("Arduino")){
            this.setImage(ImageIO.read(new File("./Images/Materials/Arduino.png")));
        }else{
            this.setImage(ImageIO.read(new File("./Images/Materials/Rope.png")));
        }

    }
}