package client;

import util.Images;

import java.awt.*;
import java.util.Random;

/**
 * Created by Harmen on 24-5-2016.
 */
public class Tile extends Rectangle {

    private int x, y, tileSize, tileNumber, updateSpeed, updateCounter;
    private Random rand;

    public Tile(int x, int y, int tileSize){
        super(x, y, tileSize, tileSize);
        rand = new Random();
        updateSpeed = rand.nextInt(30) + 20;
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(Images.wall.getSprite(tileNumber), x, y, null);
    }

    public void update(){
        updateCounter++;
        if(updateCounter > updateSpeed){
            updateCounter = 0;
            tileNumber = rand.nextInt(4);
        }
    }
}
