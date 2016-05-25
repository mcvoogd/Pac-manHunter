package client;

import util.Images;

import java.awt.*;

/**
 * Created by Harmen on 18-5-2016.
 */
public class Character extends  Rectangle{

    private int x, y, width, height;
    private int speed = 1;
    private Color color = new Color(136, 42, 29);
    private int directionX = 0, directionY = 0;
    private Tile[][] level;

    public Character(int x, int y, int width, int height){
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update(){
        move();
    }

    public void render(Graphics2D g2d){
        if(directionX == 1){
            g2d.drawImage(Images.ghost_red.getSprite(1), x, y, null);
        }
        if(directionX == -1){
           g2d.drawImage(Images.ghost_red.getSprite(0), x, y, null);
        }
        if(directionY == 1){
            g2d.drawImage(Images.ghost_red.getSprite(3), x, y, null);
        }
        if(directionY == -1){
            g2d.drawImage(Images.ghost_red.getSprite(2), x, y, null);
        }
        if(directionX == 0 && directionY == 0){
            g2d.drawImage(Images.ghost_red.getSprite(2), x, y, null);
        }
        g2d.setColor(Color.WHITE);
//        g2d.drawRect((int)getX(),(int) getY(),(int) getWidth(),(int) getHeight());
    }

    public void move(){
        int oldX = x;
        int oldY = y;
        int newX = x + directionX * speed;
        int newY = y +  directionY * speed;
        int tileX = (y + 16)/32;
        int tileY = (x + 16)/32;
        Rectangle rect = new Rectangle(newX, newY, width, height);
        boolean hit = false;
        System.out.println("Tile:" + tileX + " : " + tileY);
        for(int y = 0; y < level.length; y++){
            for(int x = 0; x < level[y].length; x++) {
                if(level[y][x] != null){
                    if(level[y][x].intersects(rect)){
                        System.out.println("Hit");
                        hit = true;
                    }
                }
            }
        }
        if(!hit){
            x = newX;
            y = newY;
            setBounds(x, y, width, height);
        }

    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void changeDirection(int x, int y){
        directionX = x;
        directionY = y;
    }

    public void setLevel(Tile[][] level){
        this.level = level;
    }
}
