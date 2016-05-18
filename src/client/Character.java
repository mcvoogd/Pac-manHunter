package client;

import java.awt.*;

/**
 * Created by Harmen on 18-5-2016.
 */
public class Character{

    private int x, y, width = 10, height = 10;
    private int speed = 2;
    private Color color = new Color(136, 42, 29);

    public Character(){

    }

    public void update(){

    }

    public void render(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);
    }

    public void move(int x, int y){
        this.x += x * speed;
        this.y += y * speed;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setColor(Color color){
        this.color = color;
    }
}
