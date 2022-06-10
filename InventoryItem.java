import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class InventoryItem{
    
    private String type;
    private BufferedImage image;
    public InventoryItem(String type) throws IOException{
        this.type = type;

        if(type.equals("Axe")){
            image = ImageIO.read(new File("./Images/Tools/Axe.png"));
        }else if(type.equals("Scissors")){
            image = ImageIO.read(new File("./Images/Tools/Scissors.png"));
        }
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