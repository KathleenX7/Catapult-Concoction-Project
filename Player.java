import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Player extends Object{
    private ArrayList <Tool> toolInventory;
    private Map <Material, Integer> materialInventory;
    private BufferedImage sprite;
    private int speed;
    private int width, height;
    public Player(){
        super();
        speed = 10;
        toolInventory = new ArrayList<Tool>();
        materialInventory = new HashMap<Material, Integer>();
        height = 80;
        width = 60;
    }
    public Player(int x, int y){
        super(x,y);
        toolInventory = new ArrayList<Tool>();
        materialInventory = new HashMap<Material, Integer>();
        height = 80;
        width = 60;
    }
    public int getH(){
        return this.height;
    }public int getW(){
        return this.width;
    }
    public boolean containsTool(Tool temp){
        return toolInventory.contains(temp);
    }
    public void addMaterial(Material o){
        if(materialInventory.containsKey(o)){
            materialInventory.put(o, 1);
        }else{
            materialInventory.put(o, materialInventory.get(o) + 1);
        }
    }
    public void move(char k){
        if(k == 'd'){ //right
            this.setX(this.getX() + this.speed);
        }else if(k == 'w'){ //up
            this.setY(this.getY() - this.speed);
        }else if(k == 'a'){ //left
            this.setX(this.getX() - this.speed);
        }else{
            this.setY(this.getY() + this.speed);
        }
    }
    public void addTool(Tool t){
        toolInventory.add(t);
    }
    public void setSprite(BufferedImage sprite){
        this.sprite = sprite;
    }
    public BufferedImage getSprite(){
        return this.sprite;
    }
}