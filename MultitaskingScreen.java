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
import java.io.IOException;

public class MultitaskingScreen extends Screen{
    private int totalCount = 0;
    private BufferedImage backgroundImage;
    private ArrayList<MapObject> items;
    private ArrayList<BallComponents> balls; 
    private ArrayList<SpamLetter> letters;
    private char lastKey;
    private ThreadX thread = new ThreadX();
    private int remainingLives;

    public MultitaskingScreen(){
        super("2");
        items = new ArrayList<MapObject>();
        balls = new ArrayList<BallComponents>(); 
        letters = new ArrayList<SpamLetter>();
        this.remainingLives = 3;
    }
    public void setBackground(BufferedImage backgroundImage){
        this.backgroundImage = backgroundImage;
    }
    public void addItem(MapObject item){
        items.add(item);
    }
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
    public MapObject getItem(int index){
        return items.get(index);
    }
    public void incCnt(){
        totalCount++;
    }
    public int getCnt(){
        return this.totalCount;
    }
    public void removeIndex(int ind){
        items.remove(ind);
    }
    public void setLastKey(char key){
        this.lastKey = key;
    }
    public char getLastKey(){
        return this.lastKey;
    }
    public BufferedImage getBackground(){
        return this.backgroundImage;
    }
    public ArrayList<MapObject> getItems(){
        return items;
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
    public void overtimeInd(int ind){
        letters.get(ind).setRequired(300);
        letters.get(ind).setLetter("we");
    }

    //thread
    public void startThread(){
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
                int rand = (int)(Math.random() * 1000) + 1000;
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
    }
}
