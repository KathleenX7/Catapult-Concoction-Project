/**
 * Room.java
 * Kathleen Xiong
 * June 17th 2022
 * Rooms containing Map Objects and allow the player to move around
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;

public class MultitaskingScreen extends Screen{
    private int totalCount, remainingLives;
    private BufferedImage backgroundImage;
    private ArrayList<MapObject> items;
    private ArrayList<BallComponents> balls; 
    private ArrayList<SpamLetter> letters;
    private char lastKey;
    private ThreadX thread = new ThreadX();

    public MultitaskingScreen(){
        super("2");
        items = new ArrayList<MapObject>();
        balls = new ArrayList<BallComponents>(); 
        letters = new ArrayList<SpamLetter>();
        this.remainingLives = 3;
        this.totalCount= 0;
    }
    public void reset(){
        totalCount = 0;
        remainingLives = 3;
        lastKey = ' ';
        for(int i=0; i < letters.size(); i++){
            letters.get(i).resetCnt();
        }
        balls.clear();
    }
    public void overtimeInd(int ind){
        letters.get(ind).setRequired(300);
        letters.get(ind).setLetter("we");
    }
    
    //background 
    public void setBackground(BufferedImage backgroundImage){
        this.backgroundImage = backgroundImage;
    }
    public BufferedImage getBackground(){
        return this.backgroundImage;
    }

    //edit lives 
    public BufferedImage getLivesImage(){
        try{
            if(remainingLives == 3) {return ImageIO.read(new File("./Images/Hearts/3Heart.png")); }
            else if(remainingLives == 2) {return ImageIO.read(new File("./Images/Hearts/2Heart.png")); }
            else if(remainingLives == 1) {return ImageIO.read(new File("./Images/Hearts/1Heart.png")); }
            else if(remainingLives == 0) {return ImageIO.read(new File("./Images/Hearts/Dead.png")); }
        }catch(Exception e){};
        return null;
    }
    public int getLives(){
        return this.remainingLives;
    }
    public void decLives(){
        if(remainingLives != 0){
            remainingLives--;
        }
    }

    //items
    public ArrayList<MapObject> getItems(){
        return items;
    }
    public MapObject getItem(int index){
        return items.get(index);
    }
    public void addItem(MapObject item){
        items.add(item);
    }
    public void removeIndex(int ind){
        items.remove(ind);
    }

    //count
    public void incCnt(){
        totalCount++;
    }
    public int getCnt(){
        return this.totalCount;
    }
    
    //lastKey character
    public void setLastKey(char key){
        this.lastKey = key;
    }
    public char getLastKey(){
        return this.lastKey;
    }
    
    
    //balls
    public void addBall(){
        balls.add(new BallComponents());
    }
    public void removeBall(int ind){
        balls.get(ind).endThread();
        balls.remove(ind);
    }
    public BallComponents getBall(int ind){
        return balls.get(ind);
    }
    public int getSize(){
        return balls.size();
    }

    //letters
    public ArrayList<SpamLetter> getLetters(){
        return this.letters;
    }
    public int indexOfLetters(SpamLetter s){
        return letters.indexOf(s);
    }
    public SpamLetter getLetters(int ind){
        return letters.get(ind);
    }
    public void addLetter(String l){
        letters.add(new SpamLetter(l));
    }

    //thread
    public void startThread(){
        thread.restart();
        Thread threadX = new Thread(thread);
        threadX.start(); 
    }
    public void endThread(){
        thread.stop();
    }
    public class ThreadX implements Runnable {
        private volatile boolean exit = false;
        public void run(){
            while(!exit){
                int rand = (int)(Math.random() * 2000) + 1250;
                try  {Thread.sleep(rand);} catch(Exception e){}
                addBall();
                if(getCnt() > 50){
                    rand = (int)(Math.random() *2);
                    if(rand ==0){
                        addBall();
                    }
                }
            }
        }
        public void stop() {
            exit = true;
        }
        public void restart(){
            exit =false;
        }
    }
}
