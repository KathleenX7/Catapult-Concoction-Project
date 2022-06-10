import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BreakableObject extends MapObject{
    private boolean isGood;
    private Material dropped;
    private Tool toolNeeded;
    private BufferedImage missingToolText;
    private BufferedImage hasToolText;
    public BreakableObject(int x, int y, BufferedImage image, String objectType, InventoryItem dropped, InventoryItem toolNeeded, boolean isGood) throws IOException{
        super(x,y, image, objectType);
        this.isGood = isGood;
        this.dropped = (Material) dropped;
        this.toolNeeded = (Tool) toolNeeded;
        this.setText();
        
    }

    public void setText() throws IOException{
        if(dropped.getType().equals("Wood")){
            missingToolText = ImageIO.read(new File("./Images/Text/NeedAxe.png"));
            hasToolText = ImageIO.read(new File("./Images/Text/PressB.png"));
        } else if(dropped.getType().equals("Rope")){
            missingToolText = ImageIO.read(new File("./Images/Text/NeedScissors.png"));
            hasToolText = ImageIO.read(new File("./Images/Text/PressB.png"));
        }
    }
    public BufferedImage getMissingToolText(){
        return this.missingToolText;
    }
    public BufferedImage getHasToolText(){
        return this.hasToolText;
    }
    public Tool getTool(){
        return this.toolNeeded;
    }
}
