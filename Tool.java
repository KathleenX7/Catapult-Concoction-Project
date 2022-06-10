import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

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