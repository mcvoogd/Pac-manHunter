package server;

import client.Tile;
import util.Images;

import java.awt.*;
import java.awt.geom.Point2D;

public class Pacman extends Rectangle {

    private int xDirection, yDirection;
    private int x, y, lastTileX = 0, lastTileY = 0, tileValue;
    private Path path;
    private boolean reached;

    public Pacman(){
        super(32, 32, 32, 32);
        x = 32; y = 32;

    }

    public void update(){
        int[][] offsets = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 }, { 0, 0 } };
        int goodSide = 4;
        int tileX = (int) (x / 32);
        int tileY = (int) (y / 32);
//        System.out.println(lastTileX + " : " + tileX);
        if (lastTileX != tileX || lastTileY != tileY) {
            lastTileX = tileX;
            lastTileY = tileY;
//            System.out.println(tileX);
            int currentDistance = 9999999;
            if (path.getPath()[tileX][tileY] != null)
                currentDistance = path.getPath()[tileX][tileY];
            tileValue = currentDistance;
//            System.out.println(tileValue);
            System.out.println(currentDistance);
            if(currentDistance == 0){
                reached = true;
            }
            for (int i = 0; i < 4; i++) {

                if (tileX + offsets[i][0] >= 0 && tileY + offsets[i][1] >= 0 && tileX + offsets[i][0] < 80 - 1
                        && tileY + offsets[i][1] < 50 - 1
                        && path.getPath()[tileX + offsets[i][0]][tileY + offsets[i][1]] != null) {
                    if (path.getPath()[tileX + offsets[i][0]][tileY + offsets[i][1]] < currentDistance) {
                        goodSide = i;
                    }
                }
            }
            int newX = tileX + offsets[goodSide][0];
            int newY = tileY + offsets[goodSide][1];
            if(newX != tileX){
                if(newX > tileX){
                    xDirection = 1;
                }else{
                    xDirection = -1;
                }
            }else{
                xDirection = 0;
            }

            if(newY != tileY){
                if(newY > tileY){
                    yDirection = 1;
                }else{
                    yDirection = -1;
                }
            }else{
                yDirection = 0;
            }
        }
        x += xDirection;
        y += yDirection;
        setBounds(x, y, 32, 32);
    }

    public void render(Graphics2D g2){
        g2.drawImage(Images.spriteSheet.getSprite(0), (int) getX(),(int) getY(), null);
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public int getXCoord(){
        return x;
    }

    public int getYCoord(){
        return y;
    }

    public void setPath(Path path){
        this.path = path;
        reached = false;
        System.out.println(reached);
        lastTileX = 0;
        lastTileY = 0;
    }

    public boolean isReached() {
        return reached;
    }

    public void teleport(int x, int y){
        setBounds(x, y, 32, 32);
        this.x = x;
        this.y = y;
    }
}
