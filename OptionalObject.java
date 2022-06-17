/**
 * MapObject.java
 * Kathleen Xiong
 * June 17th 2022
 * Objects that appear on the map and the player sprite interacts with
 */

import java.awt.image.BufferedImage;

public class OptionalObject extends MapObject{
    public OptionalObject(int x, int y, BufferedImage image, String objectType){
        super(x,y, image, objectType);
    }
}
