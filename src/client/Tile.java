package client;

import util.Images;

import java.awt.*;

/**
 * Created by Harmen on 24-5-2016.
 */
public class Tile extends Rectangle {

    private int x, y, tileSize;

    public Tile(int x, int y, int tileSize){
        super(x, y, tileSize, tileSize);
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(Images.wall, x, y, null);
    }
}
