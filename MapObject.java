public class MapObject extends Object{ //anything that the user cannot pass through
    private String objectType;
    public MapObject(int x, int y, int width, int height, String objectType){
        super(x,y,width,height);
        this.objectType = objectType;
    }
}
