package server;

import client.Data;
import util.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pacman extends Rectangle {

    private int xDirection, yDirection;
    private int x, y, lastTileX = 0, lastTileY = 0, tileValue;
    private Path path;
    private boolean reached;
    private BufferedImage[] images;
    private BufferedImage pacmanImage;
    private int imageNumber, animateFrame, animateCounter;

    public Pacman(){
        super(32, 32, 32, 32);
        x = 32; y = 32;
        cutImage();
        pacmanImage = images[0];
    }

    public void update(){
        int[][] offsets = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 }, { 0, 0 } };
        int goodSide = 4;
        int tileX = 0;
        int tileY = 0;
        switch (xDirection){
            case 0:
                tileX = x / 32;
                break;
            case -1:
                tileX = (x + 16) / 32;
                break;
            case 1:
                tileX = (x - 16) / 32;
                break;
        }
        switch (yDirection){
            case 0:
                tileY = y / 32;
                break;
            case -1:
                tileY = (y + 16) / 32;
                break;
            case 1:
                tileY = (y - 16) / 32;
                break;
        }

        if (lastTileX != tileX || lastTileY != tileY) {
            lastTileX = tileX;
            lastTileY = tileY;
            int currentDistance = 9999999;
            if (path.getPath()[tileX][tileY] != null)
                currentDistance = path.getPath()[tileX][tileY];
            tileValue = currentDistance;
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
        setBounds(x -16, y -16, 32, 32);

    }

    public void render(Graphics2D g2){

        switch (Data.getPacmanDirectionX()){
            case 1:
                imageNumber = 0;
                break;
            case -1:
                imageNumber = 6;
                break;
        }

        switch (Data.getPacmanDirectionY()){
            case 1:
                imageNumber = 3;
                break;
            case -1:
                imageNumber = 9;
                break;
        }
        animateCounter++;
        if(animateCounter > 5){
            animateFrame++;
            if(animateFrame > 2){
                animateFrame = 0;
            }
            animateCounter = 0;
        }

        pacmanImage = images[imageNumber + animateFrame];
        g2.drawImage(pacmanImage, (int) getX(), (int) getY(), null);


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
        lastTileX = 0;
        lastTileY = 0;
    }

    public boolean isReached() {
        return reached;
    }

    public void teleport(int x, int y){
        setBounds(x -16, y -16, 32, 32);
        this.x = x;
        this.y = y;
    }

    public void setPosition(int x, int y){
        setBounds(x -16, y -16, 32, 32);
        this.x = x;
        this.y = y;
    }

    private void cutImage(){
        images = new BufferedImage[12];
        for(int i = 0; i < 12; i++){
            if(i == 0 || i == 1 || i == 2){
                images[i] = Images.spriteSheet.getSprite(i);
            }
            if(i == 3 || i == 4 || i == 5){
                images[i] = Images.spriteSheet.rotate(i-3, 90);
            }
            if(i == 6 || i == 7 || i == 8){
                images[i] = Images.spriteSheet.getSpriteFlipped(i-6);
            }
            if(i == 9 || i == 10 || i == 11){
                images[i] = Images.spriteSheet.rotate(i-9, 270);
            }

        }
    }
}
