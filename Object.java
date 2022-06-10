abstract class Object {
    private int x, y;
    public Object(){
        this.x = 0;
        this.y = 0;
    }
    public Object(int x, int y){
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
