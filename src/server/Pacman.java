package server;

import client.Tile;

import java.awt.*;

public class Pacman extends Rectangle {

    private int xDirection, yDirection;
    private Tile[][] level;
    private int x, y;

    public Pacman(){
        super(32, 32, 32, 32);
        x = 32; y = 32;

    }

    public void update(){
        if(x < 10){
            x++;
        }
        if(y < 10){
            y++;
        }
        setBounds(x, y, 32, 32);
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

    public void setLevel(Tile[][] level){
        this.level = level;
    }

    public int getXCoord(){
        return x;
    }

    public int getYCoord(){
        return y;
    }
}
