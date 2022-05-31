public class CollectableObject extends MapObject{
    private boolean isGood;
    private Material materialDropped;
    private String toolNeeded;
    public CollectableObject(int x, int y, int width, int height, String objectType, Material materialDropped, String toolNeeded, boolean isGood){
        super(x,y,width,height, objectType);
        this.isGood = isGood;
        this.materialDropped = materialDropped;
        this.toolNeeded = toolNeeded;
    }
}
