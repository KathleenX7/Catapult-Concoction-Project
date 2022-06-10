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
    private ArrayList<Screen> screens = new ArrayList<Screen>();
    private ArrayList<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();
    private Player player;
    private int curIndex;
    private MyMouseMotionListener mouseMotionListener = new MyMouseMotionListener(); 
    private MyMouseListener mouseListener = new MyMouseListener();
    private MyKeyListener keyListener = new MyKeyListener();    
//------------------------------------------------------------------------------    
    public Visualizer(ArrayList<Screen> screens, ArrayList<InventoryItem> inventoryItems){
        player = new Player();
        this.inventoryItems = inventoryItems;
        this.screens = screens;
        this.curIndex = 0;
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
                for(MapObject r: ((Room) currentScreen).getItems()){
                    g.drawImage(r.getImage(),r.getX(),r.getY(), this);
                    if(r.getTouching() && (r instanceof BreakableObject)){
                        Tool temp = ((BreakableObject) r).getTool();
                        if(player.containsTool(temp)){
                            g.drawImage(((BreakableObject) r).getHasToolText(),player.getX() + player.getW() - 10,player.getY() - 30, this);
                        }else{
                            g.drawImage(((BreakableObject) r).getMissingToolText(),player.getX() + player.getW() - 10,player.getY() - 30, this);
                        }
                    }
                }g.drawImage(player.getSprite(), player.getX(), player.getY(), this);
                //Inventory
                try{
                    g.drawImage(ImageIO.read(new File("./Images/Inventory.png")), 728, 152, this);
                }catch(Exception IOException){}
            }
        } // paintComponent method end
    } // GraphicsPanel class end
    public class MyMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){ 
            if(currentScreen instanceof OpeningScreen){
                for(OpeningObject o: ((OpeningScreen) currentScreen).getElements()){
                    if(o.hovered()){
                        curIndex++;
                        currentScreen = screens.get(curIndex);

                        //set coords as door
                        player.setSprite(o.getSmallSprite());
                        player.setX(((Room)currentScreen).getItems().get(0).getX());
                        player.setY(((Room)currentScreen).getItems().get(0).getY());
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
            if(currentScreen instanceof Room){
                if (keyChar == 'a' || keyChar == 'd' || keyChar == 'w' || keyChar == 's'){ player.move(keyChar); }

                if(player.getX() < 0){ player.setX(0); }
                else if(player.getX() + player.getW() > WIDTH){ player.setX(WIDTH - player.getW()); }
                else if(player.getY() < 0){ player.setY(0);}
                else if(player.getY() + player.getH() > HEIGHT){ player.setY(HEIGHT - player.getH());}
                
                for(MapObject r: ((Room) currentScreen).getItems()){
                    if(r.isObstacle()){
                        if(player.getX() + player.getW() > r.getX() && player.getX() < r.getX() + r.getW() && player.getY() + player.getH() > r.getY() && player.getY() < r.getY() + r.getH()){
                            if(keyChar == 'w'){ player.move('s'); }
                            else if(keyChar == 's'){ player.move('w'); }
                            else if(keyChar == 'a'){ player.move('d'); }
                            else if(keyChar == 'd'){player.move('a'); }
                            r.setTouchingTrue();
                        }else{
                            r.setTouchingFalse();
                        }
                    }
                }
            }

        }        
    }
} // UsingPictures class