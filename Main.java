import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Main {
    public static ArrayList<Screen> screens;
    public static ArrayList<InventoryItem> inventoryItems;
    public static void addInventoryItems() throws IOException{
        inventoryItems.add(new Material("Wood"));
        inventoryItems.add(new Material("Arduino"));
        inventoryItems.add(new Material("Rope"));
        inventoryItems.add(new Tool("Axe"));
        inventoryItems.add(new Tool("Scissors"));
    }
    public static void addClassroom() throws IOException{
        Room classroom = new Room("Classroom");
        classroom.setBackground(ImageIO.read(new File("./Images/Floor.png")));
        classroom.addItem(new MapObject(760, 40, ImageIO.read(new File("./Images/Classroom/Door.png")), "Door"));
        classroom.addItem(new MapObject(40, 40, ImageIO.read(new File("./Images/Classroom/TeacherTable.png")), "Teacher Table"));
        for(int x = 120; x < 700; x+=160){
            for(int y=160; y < 500; y+= 160){
                if(x!= 120 || y != 480){
                    classroom.addItem(new BreakableObject(x, y, ImageIO.read(new File("./Images/Classroom/Table.png")), "Table", inventoryItems.get(0), inventoryItems.get(3), false));
                }    
            }
        }
        classroom.addItem(new BreakableObject(240, 0, ImageIO.read(new File("./Images/Classroom/Projector.png")), "Projector", inventoryItems.get(2), inventoryItems.get(4), false));
        
        classroom.addItem(new BreakableObject(80, 440, ImageIO.read(new File("./Images/Classroom/BrokenTable.png")), "Broken Table", inventoryItems.get(0), inventoryItems.get(3), true));
        classroom.addItem(new BreakableObject(40, 480, ImageIO.read(new File("./Images/Classroom/BrokenTable.png")), "Broken Table", inventoryItems.get(0), inventoryItems.get(3), true));
        classroom.addItem(new BreakableObject(120, 460, ImageIO.read(new File("./Images/Classroom/BrokenTable.png")), "Broken Table", inventoryItems.get(0), inventoryItems.get(3), true));
        
        screens.add(classroom);
    }
    public static void addRoom() throws IOException{
        Room classroom = new Room("Classroom");
        classroom.setBackground(ImageIO.read(new File("./Images/Floor.png")));
        classroom.addItem(new MapObject(760, 40, ImageIO.read(new File("./Images/Classroom/Door.png")), "Door"));
        classroom.addItem(new MapObject(40, 40, ImageIO.read(new File("./Images/Classroom/TeacherTable.png")), "Teacher Table"));
        for(int x = 120; x < 700; x+=120){
            for(int y=160; y < 500; y+= 120){
                if(x!= 120 || y != 400)
                    classroom.addItem(new MapObject(x, y, ImageIO.read(new File("./Images/Classroom/Table.png")), "Table"));
            }
        }
        classroom.addItem(new MapObject(240, 0, ImageIO.read(new File("./Images/Classroom/Projector.png")), "Projector"));
        
        classroom.addItem(new MapObject(80, 400, ImageIO.read(new File("./Images/Classroom/BrokenTable.png")), "Broken Table"));
        classroom.addItem(new MapObject(40, 440, ImageIO.read(new File("./Images/Classroom/BrokenTable.png")), "Broken Table"));
        classroom.addItem(new MapObject(120, 480, ImageIO.read(new File("./Images/Classroom/BrokenTable.png")), "Broken Table"));
        
        screens.add(classroom);
    }
    public static void main(String[] args) throws IOException{
        BufferedImage picture = null;
        inventoryItems = new ArrayList<InventoryItem>();
        screens = new ArrayList<Screen>();
        addInventoryItems();
        screens.add(new OpeningScreen());
        addClassroom();
        
        Visualizer v = new Visualizer(screens, inventoryItems);
    }
}