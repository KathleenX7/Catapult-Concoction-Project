import java.util.ArrayList;

public class Player extends Object{
    private ArrayList<Object> inventory;

    public Player(int x, int y, int width, int height){
        super(x,y,width,height);
        inventory = new ArrayList<Object>();
    }
}