package client;

import util.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Harmen on 24-5-2016.
 */
public class Map {

    private BufferedImage map;
    private int tileX, tileY;
    private int tileSize = 32;
    private Tile[][] level;

    public Map(){
        map = new BufferedImage(Images.map.getWidth() * tileSize, Images.map.getHeight() * tileSize, BufferedImage.TYPE_INT_ARGB);
        tileX = Images.map.getWidth();
        tileY = Images.map.getHeight();
        level = new Tile[tileY][tileX];
        buildLevel();
        paintLevel();
    }

    public void buildLevel(){
        for(int y = 0; y < tileY; y++){
            for(int x = 0; x < tileX; x++){
                Color colorPixel = new Color(Images.map.getRGB(x,y), true);
                if(colorPixel.getRGB() == Color.black.getRGB()){
                    level[y][x] = new Tile(x * tileSize, y * tileSize, tileSize);
                }
            }
        }
    }

    public void paintLevel(){
        Graphics2D g2 = map.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Images.map.getWidth() * tileSize, Images.map.getHeight() * tileSize);
        for(int y = 0; y < tileY; y++) {
            for (int x = 0; x < tileX; x++) {
                if(level[y][x] != null){
                    level[y][x].draw(g2);
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        paintLevel();
        g2.drawImage(map, 0, 0, null);
    }

    public void update(){
        for(int y = 0; y < tileY; y++) {
            for (int x = 0; x < tileX; x++) {
                if(level[y][x] != null){
                    level[y][x].update();
                }
            }
        }
    }

    public Tile[][] getLevel(){
        return level;
    }
}
