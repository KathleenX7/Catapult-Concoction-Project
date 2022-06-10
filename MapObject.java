import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapObject extends Object{ //anything that the user cannot pass through
    private String objectType;
    private BufferedImage image;
    private int width, height;
    private boolean obstacle = true;
    private boolean currentlyTouching = false;

    public MapObject(int x, int y, BufferedImage image, String objectType){
        super(x,y);
        this.image = image;
        this.objectType = objectType;
        this.setWH();
    }

    private void setWH(){
        if(objectType.equals("Teacher Table")){
            width = 160; height = 80;
        }else if(objectType.equals("Table") || objectType.equals("Broken Table")){
            width = height= 80;
        }else if(objectType.equals("Projector")){
            width = 320;
            height = 40;
        }else{
            obstacle = false;
        }
    }
    public void setTouchingFalse(){
        this.currentlyTouching = false;
    }
    public void setTouchingTrue(){
        this.currentlyTouching = true;
    }
    public boolean getTouching(){
        return this.currentlyTouching;
    }
    public boolean isObstacle(){
        return this.obstacle;
    }
    public int getW(){
        return this.width;
    }public int getH(){
        return this.height;
    }
    public BufferedImage getImage(){
        return image;
    }
    public String getType(){
        return objectType;
    }

}
