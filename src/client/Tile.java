package client;

import java.awt.*;

/**
 * Created by Harmen on 24-5-2016.
 */
public class Tile {

    private int x, y;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLUE);
        g2.fillRect(x, y, 32, 32);
    }
}
