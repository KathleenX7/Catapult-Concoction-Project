import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

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