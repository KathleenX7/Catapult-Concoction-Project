import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class OpeningScreen extends Screen{
    private ArrayList<OpeningObject> elements;
    private ArrayList<OpeningObject> staticElements;
    private BufferedImage backgroundImage;
    public OpeningScreen() throws IOException{
        super("opening");
        elements = new ArrayList<OpeningObject>();
        staticElements = new ArrayList<OpeningObject>();
        this.backgroundImage = ImageIO.read(new File("./Images/Floor.png"));
        staticElements.add(new OpeningObject(160, 15, 100, 9, ImageIO.read(new File("./Images/Title.png")), Color.white));
        elements.add(new OpeningObject(40, 70, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargeBlueMale.png")), ImageIO.read(new File("./Images/PlayerSprites/BlueMale.png")), new Color(132, 153, 224)));
        elements.add(new OpeningObject(40, 320, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargeBlueFemale.png")), ImageIO.read(new File("./Images/PlayerSprites/BlueFemale.png")), new Color(132, 153, 224)));
        
        elements.add(new OpeningObject(315, 70, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargeGreenMale.png")), ImageIO.read(new File("./Images/PlayerSprites/GreenMale.png")), new Color(175, 224, 110)));
        elements.add(new OpeningObject(315, 320, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargeGreenFemale.png")), ImageIO.read(new File("./Images/PlayerSprites/GreenFemale.png")), new Color(175, 224, 110)));

        elements.add(new OpeningObject(590, 70, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargePinkMale.png")), ImageIO.read(new File("./Images/PlayerSprites/PinkMale.png")), new Color(229, 164, 235)));
        elements.add(new OpeningObject(590, 320, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargePinkFemale.png")), ImageIO.read(new File("./Images/PlayerSprites/PinkFemale.png")), new Color(229, 164, 235)));
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
