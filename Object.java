abstract class Object {
    private int x, y, width, height;
    
    public Object(int x, int y, int width, int height){
        this.x = x;
        this.y = y; 
        this.width = width;
        this.height = height;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y; 
    }
}
