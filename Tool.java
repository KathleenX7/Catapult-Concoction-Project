public class Tool extends Object{
    private String toolType;
    private Material materialBroken;
    private boolean inInventory;
    public Tool(int x, int y, int width, int height, String toolType, Material materialBroken){
        super(x,y,width,height);
        this.materialBroken = materialBroken;
        this.toolType = toolType;
        this.inInventory = false;
    }

    public Material getMaterialBroken(){
        return this.materialBroken;
    }
}
