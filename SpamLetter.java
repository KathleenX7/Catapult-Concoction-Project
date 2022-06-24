/**
 * Room.java
 * Kathleen Xiong
 * June 17th 2022
 * Counting letters in stage 2
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


class SpamLetter{
    private String letter;
    private int cnt, required, index;
    private BufferedImage hundred, ten, one;

    public SpamLetter(String l){
        letter = l;
        cnt = 0;
        required = 150;

        try{ setCountImages(); }
        catch(Exception e){};
        
        if(l.equals("s")){ index = 3; }
        else{ index = 6;}
        
    }
    //getters 
    public int getInd(){
        return index;
    }
    public BufferedImage getHundred(){
        return this.hundred;
    }
    public BufferedImage getTen(){
        return this.ten;
    }
    public BufferedImage getOne(){
        return this.one;
    }
    public int getRequired(){
        return this.required;
    }
    public String getLetter(){
        return this.letter;
    }
    public double getCnt(){
        return this.cnt;
    }

    //setters
    public void setLetter(String l){
        this.letter = l;
    }
    public void setCountImages(){
        try{
        hundred = returnImage((cnt/100)%10);
        ten = returnImage((cnt/10)%10);
        one = returnImage(cnt%10);
        } catch(Exception e){}
    }
    public void setRequired(int required){
        this.required = required;
    }
    
    public void resetCnt(){
        this.cnt = 0;
    }
    public BufferedImage returnImage(int x) throws IOException{
        if(x == 0){ return ImageIO.read(new File("./Images/Numbers/0.png")); }
        else if(x == 1){ return ImageIO.read(new File("./Images/Numbers/1.png")); }
        else if(x == 2){ return ImageIO.read(new File("./Images/Numbers/2.png")); }
        else if(x == 3){ return ImageIO.read(new File("./Images/Numbers/3.png")); }
        else if(x == 4){ return ImageIO.read(new File("./Images/Numbers/4.png")); }
        else if(x == 5){ return ImageIO.read(new File("./Images/Numbers/5.png")); }
        else if(x == 6){ return ImageIO.read(new File("./Images/Numbers/6.png")); }
        else if(x == 7){ return ImageIO.read(new File("./Images/Numbers/7.png")); }
        else if(x == 8){ return ImageIO.read(new File("./Images/Numbers/8.png")); }
        else { return ImageIO.read(new File("./Images/Numbers/9.png")); }
    }
    public void increaseCnt(){
        cnt++;
        setCountImages();
    }

    @Override
    public boolean equals(Object letter){
        if(!(letter instanceof SpamLetter)){
            return false;
        }
        SpamLetter temp = (SpamLetter) letter;
        return temp.getLetter().contains(this.getLetter());
    }
    
//------------------------------------------------------------------------------    
}