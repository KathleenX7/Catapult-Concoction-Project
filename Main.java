/**
 * Main.java
 * Kathleen Xiong
 * June 17th 2022
 * Implements visualizer and create rooms/inventory
 */

import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Screen> screens;
    public static ArrayList<InventoryItem> inventoryItems;
    public static Visualizer v;
    
    public static void addInventoryItems() throws IOException{
        //add materials
        inventoryItems.add(new Material("Wood"));
        inventoryItems.add(new Material("Arduino"));
        inventoryItems.add(new Material("Rope"));
        //addd tools
        inventoryItems.add(new Tool("Axe"));
        inventoryItems.add(new Tool("Scissors"));
    }

    public static void addClassroom() throws IOException{
        Room classroom = new Room();
        classroom.setBackground(ImageIO.read(new File("./Images/Floor.png")));
        classroom.addItem(new DoorObject(720, 80, ImageIO.read(new File("./Images/Classroom/Door.png")), 3, "Large Door", "left"));
        classroom.addItem(new MapObject(40, 72, ImageIO.read(new File("./Images/Classroom/TeacherTable.png")), "Teacher Table"));
        //add tables in a grid
        for(int x = 120; x < 700; x+=160){
            for(int y=200; y < 500; y+= 160){
                if(x!= 120 || y != 480){
                    classroom.addItem(new BreakableObject(x, y, ImageIO.read(new File("./Images/Classroom/Table.png")), "Table", inventoryItems.get(0), inventoryItems.get(3), false));
                }    
            }
        }
        classroom.addItem(new BreakableObject(240, 72, ImageIO.read(new File("./Images/Classroom/Projector.png")), "Projector", inventoryItems.get(2), inventoryItems.get(4), false));
        
        classroom.addItem(new BreakableObject(80, 440, ImageIO.read(new File("./Images/Classroom/BrokenTable.png")), "Broken Table", inventoryItems.get(0), inventoryItems.get(3), true));
        classroom.addItem(new BreakableObject(40, 480, ImageIO.read(new File("./Images/Classroom/BrokenTable.png")), "Broken Table", inventoryItems.get(0), inventoryItems.get(3), true));
        classroom.addItem(new BreakableObject(120, 460, ImageIO.read(new File("./Images/Classroom/BrokenTable.png")), "Broken Table", inventoryItems.get(0), inventoryItems.get(3), true));
        
        screens.add(classroom);
    }
    public static void addOffice() throws IOException{
        Room office = new Room();
        office.setBackground(ImageIO.read(new File("./Images/Floor.png")));
        office.addItem(new DoorObject(360, 520, ImageIO.read(new File("./Images/Office/Door.png")), 3, "Large Door", "right"));
        office.addItem(new MapObject(0, 36, ImageIO.read(new File("./Images/Office/OfficeLeft.png")), "Office Table"));
        office.addItem(new MapObject(640, 36, ImageIO.read(new File("./Images/Office/OfficeRight.png")), "Office Table"));
        
        office.addItem(new CollectableObject(0, 275, ImageIO.read(new File("./Images/Office/ArduinoMaterials.png")), "Arduino", inventoryItems.get(1)));
        office.addItem(new CollectableObject(630, 450, ImageIO.read(new File("./Images/Office/Scissors.png")), "Scissors", inventoryItems.get(4)));

        screens.add(office);
    }
    public static void addPool() throws IOException{
        Room pool = new Room();
        pool.setBackground(ImageIO.read(new File("./Images/Pool/PoolBackground.png")));
        pool.addItem(new DoorObject(720, 80, ImageIO.read(new File("./Images/Classroom/Door.png")), 3, "Large Door", "left"));
        pool.addItem(new MapObject(105, 105, ImageIO.read(new File("./Images/Pool/Pool.png")), "Pool"));
        
        pool.addItem(new CollectableObject(280, 510, ImageIO.read(new File("./Images/Tools/Axe.png")), "Axe", inventoryItems.get(3)));
        pool.addItem(new BreakableObject(95, 202, ImageIO.read(new File("./Images/Pool/PoolRope.png")), "Pool Rope", inventoryItems.get(2), inventoryItems.get(4), true));
        pool.addItem(new BreakableObject(95, 302, ImageIO.read(new File("./Images/Pool/PoolRope.png")), "Pool Rope", inventoryItems.get(2), inventoryItems.get(4), true));
        pool.addItem(new BreakableObject(95, 402, ImageIO.read(new File("./Images/Pool/PoolRope.png")), "Pool Rope", inventoryItems.get(2), inventoryItems.get(4), true));
        
        screens.add(pool);
    }public static void addHallway() throws IOException{
        Room hallway = new Room();
        hallway.setBackground(ImageIO.read(new File("./Images/Hallway/HallwayBackground.png")));
        hallway.addItem(new MapObject(370, 72, null, ""));
        //add images of the rooms 
        hallway.addItem(new MapObject(8, 80, ImageIO.read(new File("./Images/Hallway/Classroom.png")), "Hallway Object"));
        hallway.addItem(new MapObject(8, 350, ImageIO.read(new File("./Images/Hallway/Pool.png")), "Hallway Object"));
        hallway.addItem(new MapObject(472, 80, ImageIO.read(new File("./Images/Hallway/Office.png")), "Hallway Object"));
        hallway.addItem(new MapObject(472, 350, ImageIO.read(new File("./Images/Hallway/Exit.png")), "Hallway Object"));
        
        //add doors 
        hallway.addItem(new DoorObject(336, 180, ImageIO.read(new File("./Images/Hallway/DoorLeft.png")), 4, "Small Door", "right")); //classroom door
        hallway.addItem(new DoorObject(336, 452, ImageIO.read(new File("./Images/Hallway/DoorLeft.png")), 6, "Small Door", "right")); //pool door
        hallway.addItem(new DoorObject(432, 180, ImageIO.read(new File("./Images/Hallway/DoorRight.png")), 5, "Small Door", "left")); //office door
        hallway.addItem(new DoorObject(432, 452, ImageIO.read(new File("./Images/Hallway/DoorRight.png")), -1, "Small Door", "left")); //exit door
        screens.add(hallway);
    }
    public static void addOpening() throws IOException{ //screen 1
        OpeningScreen opening = new OpeningScreen();
        opening.setBackgroundImage(ImageIO.read(new File("./Images/Floor.png")));
        opening.addStatic(new OpeningObject(100, 15, 100, 9, ImageIO.read(new File("./Images/Title.png")), Color.white));

        //allow player to choose their sprite design
        opening.addElement(new OpeningObject(40, 70, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargeBlueMale.png")), ImageIO.read(new File("./Images/PlayerSprites/BlueMale.png")), new Color(132, 153, 224)));
        opening.addElement(new OpeningObject(40, 320, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargeBlueFemale.png")), ImageIO.read(new File("./Images/PlayerSprites/BlueFemale.png")), new Color(132, 153, 224)));
        opening.addElement(new OpeningObject(315, 70, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargeGreenMale.png")), ImageIO.read(new File("./Images/PlayerSprites/GreenMale.png")), new Color(175, 224, 110)));
        opening.addElement(new OpeningObject(315, 320, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargeGreenFemale.png")), ImageIO.read(new File("./Images/PlayerSprites/GreenFemale.png")), new Color(175, 224, 110)));
        opening.addElement(new OpeningObject(590, 70, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargePinkMale.png")), ImageIO.read(new File("./Images/PlayerSprites/PinkMale.png")), new Color(229, 164, 235)));
        opening.addElement(new OpeningObject(590, 320, 175, 220, ImageIO.read(new File("./Images/PlayerSprites/LargePinkFemale.png")), ImageIO.read(new File("./Images/PlayerSprites/PinkFemale.png")), new Color(229, 164, 235)));

        screens.add(opening);
    }
    public static void addOpeningInstruction() throws IOException{ //screen 2
        OpeningScreen opening = new OpeningScreen();
        opening.setBackgroundImage(ImageIO.read(new File("./Images/Instructions/InstructionBackground.png")));
        opening.addStatic(new OpeningObject(0, 0, 800, 600, ImageIO.read(new File("./Images/Instructions/OpInstructions.png")), Color.white));
        opening.addElement(new OpeningObject(300, 490, 200, 80, ImageIO.read(new File("./Images/Instructions/Next.png")), null, new Color(186, 11, 98)));
        screens.add(opening);
    }
    public static void addOneInstruction() throws IOException{ //screen 3, instructions for the first stage
        OpeningScreen opening = new OpeningScreen();
        opening.setBackgroundImage(ImageIO.read(new File("./Images/Instructions/InstructionBackground.png")));
        opening.addStatic(new OpeningObject(0, 0, 800, 600, ImageIO.read(new File("./Images/Instructions/OneInstructions.png")), Color.white));
        
        opening.addStatic(new OpeningObject(420, 320, 0,0, ImageIO.read(new File("./Images/Instructions/Rope.png")), Color.white));
        opening.addStatic(new OpeningObject(510, 300, 0,0, ImageIO.read(new File("./Images/Classroom/BrokenTable.png")), Color.white));
        opening.addStatic(new OpeningObject(60, 310, 0,0, ImageIO.read(new File("./Images/Classroom/Projector.png")), Color.white));
        opening.addStatic(new OpeningObject(180, 300, 0,0, ImageIO.read(new File("./Images/Classroom/Table.png")), Color.white));
        
        opening.addElement(new OpeningObject(300, 490, 200, 80, ImageIO.read(new File("./Images/Instructions/Next.png")), null, new Color(186, 11, 98)));

        screens.add(opening);
    }public static void addTwoInstruction() throws IOException{ //screen 2
        OpeningScreen opening = new OpeningScreen();
        opening.setBackgroundImage(ImageIO.read(new File("./Images/Instructions/InstructionBackground.png")));
        opening.addStatic(new OpeningObject(0, 0, 800, 600, ImageIO.read(new File("./Images/Instructions/TwoInstructions.png")), Color.white));
        opening.addElement(new OpeningObject(300, 490, 200, 80, ImageIO.read(new File("./Images/Instructions/Next.png")), null, new Color(186, 11, 98)));
        screens.add(opening);
    }
    public static void addDamage() throws IOException{
        OpeningScreen damage = new OpeningScreen();
        damage.setBackgroundImage(ImageIO.read(new File("./Images/Instructions/InstructionBackground.png")));
        damage.addStatic(new OpeningObject(0, 0, 800, 600, ImageIO.read(new File("./Images/Instructions/DamageInstructions.png")), Color.white));
        damage.addElement(new OpeningObject(300, 490, 200, 80, ImageIO.read(new File("./Images/Instructions/Next.png")), null, new Color(186, 11, 98)));
        screens.add(damage);
    }
    public static void addMultitasking() throws IOException{ 
        MultitaskingScreen screen = new MultitaskingScreen();
        screen.setBackground(ImageIO.read(new File("./Images/Multitasking/Background.png")));
        screen.addItem(new MapObject(372, 258, ImageIO.read(new File("./Images/Multitasking/Pile.png")), "Materials"));
        screen.addItem(new MapObject(275, 350, ImageIO.read(new File("./Images/Hearts/3Heart.png")), "Hearts"));
        screen.addItem(new OptionalObject(0, 0, ImageIO.read(new File("./Images/Multitasking/Overtime.png")), "IfBad"));
        screen.addItem(new MapObject(42, 110, ImageIO.read(new File("./Images/Numbers/0.png")), "HundredS"));
        screen.addItem(new MapObject(140, 110, ImageIO.read(new File("./Images/Numbers/0.png")), "TenS"));
        screen.addItem(new MapObject(238, 110, ImageIO.read(new File("./Images/Numbers/0.png")), "OneS"));

        screen.addItem(new MapObject(480, 110, ImageIO.read(new File("./Images/Numbers/0.png")), "HundredW"));
        screen.addItem(new MapObject(578, 110, ImageIO.read(new File("./Images/Numbers/0.png")), "TenW"));
        screen.addItem(new MapObject(676, 110, ImageIO.read(new File("./Images/Numbers/0.png")), "OneW"));
        
        

        screen.addLetter("s");
        screen.addLetter("w");
        screens.add(screen);
    }
    public static void addEnding() throws IOException{
        OpeningScreen opening = new OpeningScreen();
        opening.setBackgroundImage(ImageIO.read(new File("./Images/Instructions/Congratulations.png")));
        opening.addStatic(new OpeningObject(420, 350, 0,0, ImageIO.read(new File("./Images/Catapul.png")), Color.white));
        screens.add(opening);
    }
    public static void main(String[] args) throws IOException{
        inventoryItems = new ArrayList<InventoryItem>();
        screens = new ArrayList<Screen>();

        //add all screens 
        addInventoryItems();
        
        
        addOpening(); //0
        addOpeningInstruction(); //1
        addOneInstruction(); //2
        addHallway(); //3
        addClassroom(); //4
        addOffice(); //5
        addPool(); //6
        addTwoInstruction(); //7
        addDamage(); //8
        addMultitasking(); //9
        addEnding(); //10

        v = new Visualizer(screens, inventoryItems);
    }
}