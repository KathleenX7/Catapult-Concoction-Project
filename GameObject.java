/**
 * Object.java
 * Kathleen Xiong
 * June 17th 2022
 * Stores the coordinates for different objects on the screen
 */

abstract class GameObject {
    private int x, y;
    public GameObject(){
        this.x = 0;
        this.y = 0;
    }
    public GameObject(int x, int y){
        this.x = x;
        this.y = y; 
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y; 
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
}
