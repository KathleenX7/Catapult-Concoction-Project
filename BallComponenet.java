import java.awt.Color;

class BallComponents {
    private double x;
    private double y;
    private double angle;
    private int speed;
    private int ballD;
    private Color colour;
    private ThreadX thread = new ThreadX();

    public BallComponents(){
        ballD = (int) (Math.random() * 50 + 25);
        int rand = (int)(Math.random() * 4);
        if(rand == 0 || rand == 1){
            x = (int) (Math.random() * (800));
            y = rand * 600;
        }else{
            y = (int) (Math.random() * (600));
            x = (rand-2) * 800;
        }
        if(x== 400){ this.angle = 90; }
        else{ this.angle = Math.atan((300.0 - y)/(400.0 - x)); }
        
        speed = (int) (Math.random() * 5 + 2);
        ballD = (int) (Math.random() * 10 + 30);
        colour = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
        runThreads();
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public int getSpeed(){
        return this.speed;
    }
    public int getBallD(){
        return this.ballD;
    }
    public Color getColour(){
        return this.colour;
    }
    public void endThread(){
        thread.stop();
    }
    public void runThreads(){
        Thread threadX = new Thread(thread);
        threadX.start();
    }
    public boolean collision(int x, int y){

        if(this.x <= x && this.x + this.ballD >= x && this.y < y && this.y + this.ballD > y){
            return true;
        }else{
            return false;
        }
    }
//------------------------------------------------------------------------------        
    
    public class ThreadX implements Runnable {
        private volatile boolean exit = false;
        public void run(){
            while(!exit){
                Visualizer.getWindow().repaint();
                try  {Thread.sleep(20);} catch(Exception e){} 

                if(x < 400){ x += Math.abs( (Math.cos(angle) * speed)); }
                else{ x -= Math.abs((Math.cos(angle) * speed)); }
                
                if(y < 300){ y += Math.abs((Math.sin(angle) * speed)); }
                else{ y -= Math.abs((Math.sin(angle) * speed)); }
            }
        }
        public void stop() {
            exit = true;
        }
    }
}

