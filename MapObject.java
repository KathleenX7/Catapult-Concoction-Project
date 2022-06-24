/**
 * MapObject.java
 * Kathleen Xiong
 * June 17th 2022
 * Objects that appear on the map and the player sprite interacts with
 */

import java.awt.image.BufferedImage;

public class MapObject extends GameObject{ 
    private String objectType;
    private BufferedImage image;
    private int width, height;
    private int obstacle; //0 = not obstacle, 1 = obstacle, 2 = door
    private boolean currentlyTouching;

    public MapObject(int x, int y, BufferedImage image, String objectType){
        super(x,y);
        this.image = image;
        this.objectType = objectType;
        this.obstacle = 1;
        this.currentlyTouching = false;
        this.setWH();
    }

    //setters
    public void setImage(BufferedImage image){
        this.image = image;
    }
    private void setWH(){ //set the width and height for objects
        if(objectType.equals("Teacher Table")){
            width = 160; height = 80;
        }else if(objectType.equals("Table") || objectType.equals("Broken Table")){
            width = height= 80;
        }else if(objectType.equals("Projector")){
            width = 320;
            height = 40;
        }else if(objectType.equals("Office Table")){
            width = 160;
            height = 600;
        }else if(objectType.equals("Arduino") || objectType.equals("Scissors")){
            width = 170;
            height = 160;
        }else if(objectType.equals("Axe")){ width = height = 52; }
        else if(objectType.equals("Pool")){
            width = 615;
            height = 394;
        }else if(objectType.equals("Pool Rope")){
            width = 605;
            height = 8;
        }else if(objectType.equals("Hallway Object")){
            width = 320;
            height = 248;
        }else if(objectType.equals("Materials")){
            width = height = 74;
        }
        else if(objectType.equals("Large Door")){
            width = height = 80;
            obstacle = 2;
        }else if(objectType.equals("Small Door")){
            width = height = 32;
            obstacle = 2;
        }else{ obstacle = 0; }
    }
    public void setTouchingFalse(){
        this.currentlyTouching = false;
    }
    public void setTouchingTrue(){
        this.currentlyTouching = true;
    }

    //getters 
    public boolean getTouching(){
        return this.currentlyTouching;
    }
    public int getW(){
        return this.width;
    }
    public int getH(){
        return this.height;
    }
    public BufferedImage getImage(){
        return image;
    }
    public String getType(){
        return objectType;
    }
    public boolean isObstacle(){
        return this.obstacle == 1;
    }
    public boolean isDoor(){
        return this.obstacle == 2;
    }
}
