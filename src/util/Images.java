package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    public static BufferedImage map;
    public static SpriteSheet wall;
    public static SpriteSheet spriteSheet;


    public Images(){
        map = readImage("/map01.png");
        wall = new SpriteSheet("wall", 32, 32);
        spriteSheet = new SpriteSheet("spritesheet", 32, 32);

    }

    public BufferedImage readImage(String file) {
        try {
            return ImageIO.read(Images.class.getResource(file));
        } catch (IOException e) {
            System.err.println("Could not load Image: " + e);
        }

        return null;
    }
}
