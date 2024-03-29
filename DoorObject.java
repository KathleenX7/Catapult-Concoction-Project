/**
 * DoorObjects.java
 * Kathleen Xiong
 * June 17th 2022
 * A MapObjects door that leads to a different room
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DoorObject extends MapObject{
    private int leadToIndex;
    private BufferedImage text;
    private String lr;
    public DoorObject(int x, int y, BufferedImage image, int leadToIndex, String objectType, String lr){
        super(x,y, image, objectType);
        this.leadToIndex = leadToIndex; //based on screen index

        this.lr = lr; //direction of text box
        try{
            if(lr.equals("left")){
                text = ImageIO.read(new File("./Images/Text/PressELeft.png"));
            }else{
                text = ImageIO.read(new File("./Images/Text/PressE.png"));
            }
        }catch (IOException e){}
        
    }
    //getters 
    public String getLeftRight(){
        return this.lr;
    }
    public BufferedImage getText(){
        return this.text;
    }
    public int getLeadToInd(){
        return this.leadToIndex;
    }
}
