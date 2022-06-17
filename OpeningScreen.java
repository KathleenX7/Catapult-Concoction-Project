/**
 * OpeningScreen.java
 * Kathleen Xiong
 * June 17th 2022
 * Screens with information and buttons
 */

import java.util.ArrayList;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class OpeningScreen extends Screen{
    private ArrayList<OpeningObject> elements;
    private ArrayList<OpeningObject> staticElements;
    private BufferedImage backgroundImage;
    public OpeningScreen() throws IOException{
        super("opening");
        elements = new ArrayList<OpeningObject>();
        staticElements = new ArrayList<OpeningObject>();
        
    }

    public void setBackgroundImage(BufferedImage i){
        backgroundImage = i;
    }
    public void addStatic(OpeningObject o){
        staticElements.add(o);
    }
    public void addElement(OpeningObject o){
        elements.add(o);
    }
    public ArrayList<OpeningObject> getElements(){
        return elements;
    }
    public ArrayList<OpeningObject> getStaticElements(){
        return staticElements;
    }
    public BufferedImage getBackground(){
        return this.backgroundImage;
    }

}
