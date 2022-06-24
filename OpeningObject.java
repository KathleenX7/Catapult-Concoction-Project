/**
 * OpeningObject.java
 * Kathleen Xiong
 * June 17th 2022
 * Objects in the Opening Screen
 */

import java.awt.image.BufferedImage;
import java.awt.Color;

public class OpeningObject extends GameObject{
    private int width, height;
    private BufferedImage sprite, smallSprite;
    private Color outside, original;

    public OpeningObject(int x, int y, int width, int height, BufferedImage sprite, Color outside){
        super(x,y);
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.outside = outside;
        this.original = this.outside;
    }

    public OpeningObject(int x, int y, int width, int height, BufferedImage sprite, BufferedImage smallSprite, Color outside){
        super(x,y);
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.smallSprite = smallSprite;
        this.outside = outside;
        this.original = this.outside;
    }
    //getters
    public BufferedImage getSprite(){
        return this.sprite;
    }
    public BufferedImage getSmallSprite(){
        return this.smallSprite;
    }
    public Color getColour(){
        return outside;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return height;
    }
    //setters
    public void setColour(Color outside){
        this.outside = outside; 
    }
    
    public void resetColour(){
        this.outside = this.original;
    }
    public void changeBorder(){
        this.outside = Color.orange;
    }
    public boolean hovered(){
        return this.outside != this.original;
    }
}
