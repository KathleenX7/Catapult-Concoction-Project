import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Visualizer{ 
    // Game Window properties
    static JFrame gameWindow;
    static GraphicsPanel canvas;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    // picture properties
    static BufferedImage backgroundImage;
    
//------------------------------------------------------------------------------    
    public Visualizer(BufferedImage backgroundImage){
        this.backgroundImage = backgroundImage;
        gameWindow = new JFrame("Game Window");
        gameWindow.setSize(WIDTH,HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        canvas = new GraphicsPanel();
        gameWindow.add(canvas);
        
        // load the picture from a file
        // try {                
        //     picture = ImageIO.read(new File("java.png"));
        // } catch (IOException ex){} 
        
        gameWindow.setVisible(true);
        runGameLoop();
        
    } // main method end
    
    public void changeBackground(BufferImage backgroundImage){
        this.backgroundImage = backgroundImage; 
    }
//------------------------------------------------------------------------------   
    public void runGameLoop(){
        while (true) {
            gameWindow.repaint();
            try  {Thread.sleep(20);} catch(Exception e){}
        }
    } // runGameLoop method end
    
//------------------------------------------------------------------------------  
    static class GraphicsPanel extends JPanel{
        public GraphicsPanel(){
            setFocusable(true);
            requestFocusInWindow();
        }
        public void paintComponent(Graphics g){ 
            super.paintComponent(g); //required
            
        // draw the picture ("this" refers to the graphics panel)   
            g.drawImage(backgroundImage,0,0,this);

        } // paintComponent method end
    } // GraphicsPanel class end
    
} // UsingPictures class