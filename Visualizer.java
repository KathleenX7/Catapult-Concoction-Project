import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Visualizer{ 
    // Game Window properties
    private JFrame gameWindow;
    private GraphicsPanel canvas;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    // picture properties
    private Screen currentScreen;
    private int curIndex;
    private ArrayList<Screen> screens = new ArrayList<Screen>();
    private ArrayList<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
    private Player player;
    private int currentRoomCollisionElementB;
    private int currentRoomCollisionElementC;
    private int currentRoomCollisionElementE;
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
        // load the picture from a file
        
        gameWindow.setVisible(true);
        runGameLoop();
        
    } // main method end
    
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
                for(OpeningObject o: ((OpeningScreen) currentScreen).getElements()){
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
                
                int index =0 ;
                for(Material m: player.getMaterials().keySet()){
                    g.drawImage(m.getImage(), 474 + 66 * index, 8, this);
                    if(!m.getType().equals("Arduino")){
                        g.setFont(new Font("Arial", Font.BOLD, 9));
                        g.drawString(Integer.toString(player.getMaterials().get(m)), 518 + 66 * index, 60);
                    }
                    index++;
                }
                for(Tool t: player.getTools()){
                    g.drawImage(t.getImage(), 474 + 66 * index, 8, this);
                    index++;
                }

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
                
                
            }
        } // paintComponent method end
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
                        currentScreen = screens.get(curIndex);

                        //set coords as door
                        if(currentScreen instanceof Room){
                            player.setX(((Room)currentScreen).getItems().get(0).getX());
                            player.setY(((Room)currentScreen).getItems().get(0).getY());
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
            if(currentScreen instanceof OpeningScreen){
                for(OpeningObject o: ((OpeningScreen) currentScreen).getElements()){
                    if(e.getX() >= o.getX() && e.getX() <= o.getX() + o.getWidth() && 
                        e.getY() >= o.getY() && e.getY() <= o.getY() + o.getHeight()){
                        o.changeBorder();
                    }else{
                        o.resetColour();
                    }
                }
            }
            
        }
        public void mouseDragged(MouseEvent e){ }         
    }
    public class MyKeyListener implements KeyListener{   
        // method to process key pressed events (when a key goes down, i.e. immediately)
        public void keyPressed(KeyEvent e){}
        // method to process key released events (when a key goes up)
        public void keyReleased(KeyEvent e){ 
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ESCAPE){
                gameWindow.dispose();
            }
        }   
        // method to process key typed events (only typeable/printable keys)
        public void keyTyped(KeyEvent e){
            char keyChar = e.getKeyChar();
            if((keyChar == 'b' || keyChar == 'B') && currentRoomCollisionElementB != -1){
                BreakableObject m = ((BreakableObject)((Room) currentScreen).getItems().get(currentRoomCollisionElementB));
                if(m.getType().equals("Projector")){
                    player.addMaterial(m.getMaterial());
                    player.addMaterial(m.getMaterial());    
                }
                player.addMaterial(m.getMaterial());
                ((Room) currentScreen).removeIndex(currentRoomCollisionElementB);
                currentRoomCollisionElementB = -1;
                damagedProperty = damagedProperty || m.getIsGood();

            }else if((keyChar == 'c' || keyChar == 'C') && currentRoomCollisionElementC != -1){
                InventoryItem c = ((CollectableObject)((Room) currentScreen).getItems().get(currentRoomCollisionElementC)).getDropped();
                if(c instanceof Tool){
                    player.addTool((Tool)c);
                }else if(c instanceof Material){
                    player.addMaterial((Material) c);
                }
                
                ((Room) currentScreen).removeIndex(currentRoomCollisionElementC);
                currentRoomCollisionElementC = -1;
            }else if((keyChar == 'e' || keyChar == 'E') && currentRoomCollisionElementE != -1){
                DoorObject r = (DoorObject) ((Room) currentScreen).getItems().get(currentRoomCollisionElementE);
                if(r.getLeadToInd() != -1){
                    currentScreen = screens.get(r.getLeadToInd());
                    if(currentScreen instanceof Room){
                        player.setX(((Room)currentScreen).getItems().get(0).getX());
                        player.setY(((Room)currentScreen).getItems().get(0).getY());
                    }
                }else{
                    if(player.getNum((Material)inventoryItems.get(0)) == 3 && player.getNum((Material)inventoryItems.get(1)) == 1
                        && player.getNum((Material)inventoryItems.get(2)) == 3){
                            System.out.println("hi");
                    }
                }
                

            }

            if(currentScreen instanceof Room){
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
} // UsingPictures class