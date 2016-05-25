package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    public static BufferedImage map, wall;


    public Images(){
        map = readImage("/map01.png");
        wall = readImage("/wall.png");
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
