package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    public static BufferedImage map, start, connecting, won, scoreBoard;
    public static SpriteSheet wall;
    public static SpriteSheet spriteSheet, loading_sprite;


    public Images(){
        map = readImage("/map01.png");
        wall = new SpriteSheet("wall", 32, 32);
        spriteSheet = new SpriteSheet("spritesheet", 32, 32);
        start = readImage("/start.png");
        connecting = readImage("/connecting.png");
        won = readImage("/won.png");
        loading_sprite = new SpriteSheet("loading_sprite", 509 ,64 );
        scoreBoard = readImage("/scoreBoard.png");
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
