
/**
 * Main.java
 * Kathleen Xiong
 * June 17th 2022
 * Visualizer class
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Visualizer{ 
    // Game Window properties
    private static JFrame gameWindow;
    private GraphicsPanel canvas;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    // picture properties
    private Screen currentScreen;
    private int curIndex;
    private ArrayList<Screen> screens = new ArrayList<Screen>();
    private ArrayList<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
    private Player player;
    private int currentRoomCollisionElementB, currentRoomCollisionElementC, currentRoomCollisionElementE;
    private boolean damagedProperty = false;
    private MyMouseMotionListener mouseMotionListener = new MyMouseMotionListener(); 
    private MyMouseListener mouseListener = new MyMouseListener();
    private MyKeyListener keyListener = new MyKeyListener();

//------------------------------------------------------------------------------    
    public Visualizer(ArrayList<Screen> screens, ArrayList<InventoryItem> inventoryItems){
        player = new Player();
        this.inventoryItems = inventoryItems;
        this.screens = screens;
        this.currentRoomCollisionElementB = this.currentRoomCollisionElementC= this.currentRoomCollisionElementE = -1;
        
        curIndex = 0;
        currentScreen = this.screens.get(curIndex);
        gameWindow = new JFrame("Game Window");
        gameWindow.setSize(WIDTH,HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new GraphicsPanel();
        gameWindow.add(canvas);
        canvas.addKeyListener(keyListener);
        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseMotionListener);
        gameWindow.setVisible(true);
        runGameLoop();
        
    } 
    public static JFrame getWindow(){
        return gameWindow;
    }
    public void changeBackground(Screen currentRoom){
        this.currentScreen = currentRoom; 
    }
//------------------------------------------------------------------------------   
    public void runGameLoop(){
        while (true) {
            gameWindow.repaint();
            try  {Thread.sleep(20);} catch(Exception e){}
        }
    } 
    
//------------------------------------------------------------------------------  
    public class GraphicsPanel extends JPanel{
        public GraphicsPanel(){
            setFocusable(true);
            requestFocusInWindow();
        }
        public void paintComponent(Graphics g){ 
            super.paintComponent(g); //required
            
            if(currentScreen instanceof OpeningScreen){
                g.drawImage(((OpeningScreen) currentScreen).getBackground(),0,0, this);
                for(OpeningObject o: ((OpeningScreen) currentScreen).getElements()){ //buttons 
                    g.setColor(o.getColour());
                    
                    g.fillRect(o.getX(), o.getY(), o.getWidth(), o.getHeight());
                    g.setColor(Color.white);
                    g.fillRect(o.getX() + 10, o.getY() + 10, o.getWidth() - 20 , o.getHeight()- 20);
                    g.drawImage(o.getSprite(), o.getX() + 10 , o.getY() + 10, this);
                }

                for(OpeningObject o: ((OpeningScreen) currentScreen).getStaticElements()){
                    g.drawImage(o.getSprite(), o.getX(), o.getY(), this);
                }
            }else if(currentScreen instanceof Room){
                g.drawImage(((Room) currentScreen).getBackground(),0,0, this);
                //map items 
                for(int ind = 0; ind < ((Room) currentScreen).getItems().size(); ind++){ 
                    MapObject r = ((Room) currentScreen).getItems().get(ind);
                    if(r.getImage() != null){
                        g.drawImage(r.getImage(),r.getX(),r.getY(), this);
                    }
                }
                //Inventory
                try{
                    g.drawImage(ImageIO.read(new File("./Images/Header.png")), 0, 0, this);
                }catch(IOException e){}
                
                //inventory material items
                int index =0 ;
                for(Material m: player.getMaterials().keySet()){
                    g.drawImage(m.getImage(), 474 + 66 * index, 8, this);
                    if(!m.getType().equals("Arduino")){
                        g.setFont(new Font("Arial", Font.BOLD, 9));
                        g.drawString(Integer.toString(player.getMaterials().get(m)), 518 + 66 * index, 60);
                    }
                    index++;
                }
                //inventory tool items
                for(Tool t: player.getTools()){
                    g.drawImage(t.getImage(), 474 + 66 * index, 8, this);
                    index++;
                }
                //check collisions (door and obstacles)
                for(int ind = 0; ind < ((Room) currentScreen).getItems().size(); ind++){ 
                    MapObject r = ((Room) currentScreen).getItems().get(ind);
                    if(r.getTouching()){
                        if(r instanceof BreakableObject){
                            Tool temp = ((BreakableObject) r).getTool();
                            if(player.containsTool(temp)){
                                g.drawImage(((BreakableObject) r).getHasToolText(),player.getX() + player.getW() - 5,player.getY() - 50, this);
                                currentRoomCollisionElementB = ind;
                            }else{
                                g.drawImage(((BreakableObject) r).getMissingToolText(),player.getX() + player.getW() - 5,player.getY() - 50, this);
                            }
                        }else if(r instanceof CollectableObject){
                            g.drawImage(((CollectableObject) r).getText(), player.getX() + player.getW() - 5,player.getY() - 50, this);
                            currentRoomCollisionElementC = ind;
                        }else if(r instanceof DoorObject){
                            if(((DoorObject) r).getLeftRight().equals("right")){
                                g.drawImage(((DoorObject) r).getText(), player.getX() + player.getW() - 5,player.getY() - 50, this);
                            }else{
                                g.drawImage(((DoorObject) r).getText(), player.getX() - 120,player.getY() - 50, this);
                            }
                            currentRoomCollisionElementE = ind;
                        }
                        
                    }else if(currentRoomCollisionElementB== ind){currentRoomCollisionElementB = -1;}
                    else if(currentRoomCollisionElementC == ind){currentRoomCollisionElementC = -1;}
                    else if(currentRoomCollisionElementE == ind){currentRoomCollisionElementE = -1;}
                }
                g.drawImage(player.getSprite(), player.getX(), player.getY(), this);
            }else if(currentScreen instanceof MultitaskingScreen){
                MultitaskingScreen temp = ((MultitaskingScreen) currentScreen);
                g.drawImage(temp.getBackground(),0,0, this);

                //draw items
                for(int ind = 0; ind < temp.getItems().size(); ind++){ 
                    MapObject r = temp.getItems().get(ind);
                    if(r instanceof OptionalObject){
                        if(damagedProperty){g.drawImage(r.getImage(),r.getX(),r.getY(), this); }
                    }else if(r.getImage() != null){
                        g.drawImage(r.getImage(),r.getX(),r.getY(), this);
                    }   
                }

                g.setColor(Color.black);
                g.fillOval(400, 300,10,10);
                //check for collision
                for(int i =0; i < temp.getSize(); i++){
                    MapObject o = temp.getItems().get(0);
                    BallComponents b = temp.getBall(i);
                    if((int) b.getX() + b.getBallD() >= o.getX() && (int) b.getX() <= o.getX() + o.getW() &&
                        (int) b.getY() + b.getBallD() >= o.getY() && (int) b.getY() <= o.getY() + o.getH() ){
                            temp.removeBall(i);
                            temp.decLives();
                            temp.getItem(1).setImage(temp.getLivesImage());

                            if(temp.getLives() == 0){
                                temp.reset();
                                temp.endThread();
                                for(int letterInd=0; letterInd < temp.getLetters().size(); letterInd++){
                                    SpamLetter tempS = (SpamLetter) temp.getLetters(letterInd);
                                    tempS.setCountImages();
                                    temp.getItem(tempS.getInd()).setImage(tempS.getHundred());
                                    temp.getItem(tempS.getInd() + 1).setImage(tempS.getTen());
                                    temp.getItem(tempS.getInd() + 2).setImage(tempS.getOne());
                                    temp.getItem(1).setImage(temp.getLivesImage()); 
                                }
                                currentScreen = screens.get(7);
                                curIndex = 7;
                            }
                    }
                    g.setColor(b.getColour());
                    g.fillOval((int)b.getX(), (int) b.getY(), b.getBallD(), b.getBallD());
                }
            }
        } 
    } // GraphicsPanel class end
    public class MyMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){ 
            if(currentScreen instanceof OpeningScreen){
                for(OpeningObject o: ((OpeningScreen) currentScreen).getElements()){
                    if(o.hovered()){
                        if(curIndex == 0){
                            player.setSprite(o.getSmallSprite());
                        }
                        curIndex++;
                        if(curIndex == 8 && !damagedProperty){
                            curIndex = 9;
                        }
                        currentScreen = screens.get(curIndex);
                        //set coords as door
                        if(currentScreen instanceof Room){ 
                            player.setX(((Room)currentScreen).getItems().get(0).getX());
                            player.setY(((Room)currentScreen).getItems().get(0).getY());
                        }else if(currentScreen instanceof MultitaskingScreen){ 
                            if(damagedProperty){
                                ((MultitaskingScreen) currentScreen).overtimeInd(1);
                            } ((MultitaskingScreen) currentScreen).reset();
                        }
                        
                    }
                }
            }
        }
        public void mousePressed(MouseEvent e){ }
        public void mouseReleased(MouseEvent e){ }
        public void mouseEntered(MouseEvent e){ }
        public void mouseExited(MouseEvent e){ }
    }

    public class MyMouseMotionListener implements MouseMotionListener{
        public void mouseMoved(MouseEvent e){
            if(currentScreen instanceof OpeningScreen){ //buttons
                for(OpeningObject o: ((OpeningScreen) currentScreen).getElements()){
                    if(e.getX() >= o.getX() && e.getX() <= o.getX() + o.getWidth() && 
                        e.getY() >= o.getY() && e.getY() <= o.getY() + o.getHeight()){
                        o.changeBorder();
                    }else{
                        o.resetColour();
                    }
                }
            }else if(currentScreen instanceof MultitaskingScreen){
                MultitaskingScreen temp = (MultitaskingScreen) currentScreen;
                for(int i=0; i < temp.getSize(); i++){
                    if(temp.getBall(i).collision(e.getX(), e.getY())){
                        temp.removeBall(i);
                    }
                }
            }
            
        }
        public void mouseDragged(MouseEvent e){ }         
    }
    public class MyKeyListener implements KeyListener{   
        public void keyPressed(KeyEvent e){}
        public void keyReleased(KeyEvent e){ 
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ESCAPE){
                gameWindow.dispose();
            }
        }   
        public void keyTyped(KeyEvent e){
            char keyChar = e.getKeyChar();
            if(currentScreen instanceof MultitaskingScreen){
                if (((MultitaskingScreen) currentScreen).getLastKey() != keyChar){
                    MultitaskingScreen temp = (MultitaskingScreen) currentScreen;
                    
                    int ind = temp.indexOfLetters(new SpamLetter(Character.toString(keyChar)));
                    int otherInd = 0;
                    if(ind == 0){otherInd = 1;}
                    if(ind != -1){
                        if(temp.getCnt() == 0){
                            temp.startThread();
                        }
                        SpamLetter s = temp.getLetters(ind);
                        if(s.getCnt() != s.getRequired()){
                            s.increaseCnt();
                            temp.incCnt();
                            temp.getItem(s.getInd()).setImage(s.getHundred());
                            temp.getItem(s.getInd() + 1).setImage(s.getTen());
                            temp.getItem(s.getInd() + 2).setImage(s.getOne());
                        }else if(temp.getLetters(otherInd).getCnt() == temp.getLetters(otherInd).getRequired()){ //if both letters are complete
                            curIndex++;
                            currentScreen = screens.get(curIndex);
                        }
                    }temp.setLastKey(keyChar);
                }
            }else if((keyChar == 'b' || keyChar == 'B') && currentRoomCollisionElementB != -1){ //break
                BreakableObject m = ((BreakableObject)((Room) currentScreen).getItems().get(currentRoomCollisionElementB));
                if(m.getType().equals("Projector")){
                    player.addMaterial(m.getMaterial());
                    player.addMaterial(m.getMaterial());    
                }
                player.addMaterial(m.getMaterial());
                ((Room) currentScreen).removeIndex(currentRoomCollisionElementB);
                currentRoomCollisionElementB = -1;
                damagedProperty = damagedProperty || (!m.getIsGood());

            }else if((keyChar == 'c' || keyChar == 'C') && currentRoomCollisionElementC != -1){ //collect
                InventoryItem c = ((CollectableObject)((Room) currentScreen).getItems().get(currentRoomCollisionElementC)).getDropped();
                if(c instanceof Tool){
                    player.addTool((Tool)c);
                }else if(c instanceof Material){
                    player.addMaterial((Material) c);
                }
                
                ((Room) currentScreen).removeIndex(currentRoomCollisionElementC);
                currentRoomCollisionElementC = -1;
            }else if((keyChar == 'e' || keyChar == 'E') && currentRoomCollisionElementE != -1){ //enter
                DoorObject r = (DoorObject) ((Room) currentScreen).getItems().get(currentRoomCollisionElementE);
                if(r.getLeadToInd() != -1){
                    currentScreen = screens.get(r.getLeadToInd());
                    if(currentScreen instanceof Room){
                        player.setX(((Room)currentScreen).getItems().get(0).getX());
                        player.setY(((Room)currentScreen).getItems().get(0).getY());
                    }
                }else{
                    if(player.getMaterialSize() == 3){
                        if(player.getNum((Material)inventoryItems.get(0)) >= 3 && player.getNum((Material)inventoryItems.get(1)) >= 1
                        && player.getNum((Material)inventoryItems.get(2)) >= 3){
                            currentScreen = screens.get(7);
                            curIndex = 7;
                            currentRoomCollisionElementE = currentRoomCollisionElementC = currentRoomCollisionElementB = -1;
                        }
                    }
                }
            }

            if(currentScreen instanceof Room){ //movement
                if (keyChar == 'a' || keyChar == 'd' || keyChar == 'w' || keyChar == 's'){ player.move(keyChar); }

                if(player.getX() < 0){ player.setX(0); }
                else if(player.getX() + player.getW() > WIDTH){ player.setX(WIDTH - player.getW()); }
                else if(player.getY() < 72){ player.setY(72);}
                else if(player.getY() + player.getH() > HEIGHT){ player.setY(HEIGHT - player.getH());}
                
                for(MapObject r: ((Room) currentScreen).getItems()){
                    if(player.getX() + player.getW() > r.getX() && player.getX() < r.getX() + r.getW() && player.getY() + player.getH() > r.getY() && player.getY() < r.getY() + r.getH()){
                        if(r.isObstacle()){
                            if(keyChar == 'w'){ player.move('s'); }
                            else if(keyChar == 's'){ player.move('w'); }
                            else if(keyChar == 'a'){ player.move('d'); }
                            else if(keyChar == 'd'){player.move('a'); }
                        }
                        r.setTouchingTrue();
                    }else{
                        r.setTouchingFalse();
                    }
                }
            }

        }        
    }
} 