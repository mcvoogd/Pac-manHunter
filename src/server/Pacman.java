package server;

import java.awt.*;

/**
 * Created by Martijn on 30-5-2016.
 */
public class Pacman extends Rectangle {

    private int xDirection, yDirection;


    public Pacman(){

    }

    public void update(){

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
}
