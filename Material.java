public class Material{
    private String toolUsed; 
    private String type;
    public Material(String type, String toolUsed){
        this.type = type;
        this.toolUsed = toolUsed;
    }

    public String toolUsed(){
        return this.toolUsed;
    }
}