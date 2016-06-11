package client;

import util.Images;

import java.awt.*;

/**
 * Created by Harmen on 18-5-2016.
 */
public class Character extends  Rectangle{

    private int x, y, width, height;
    private int speed = 1;
    private int directionX = 0, directionY = 0, newDirectionX = 0, newDirectionY = 0;
    private Tile[][] level;
    private int imageNumber;

    public Character(int x, int y, int width, int height){
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        imageNumber = 16;
    }

    public Character(int x, int y, int width, int height, int player){
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        switch (player){
            case 1: imageNumber = 16; break;
            case 2: imageNumber = 12; break;
        }
    }

    public void update(){
        if((newDirectionX == directionX) && (newDirectionY == directionY)){
            moveChar(directionX, directionY);
        }else{
            changeDirection(newDirectionX, newDirectionY);
        }
        if(y == 320){
            if (x > 576){
                x = 0;
            }
            if( x < 0){
                x = 576;
            }
        }
    }

    public void render(Graphics2D g2d){
        if(directionX == 1){
            g2d.drawImage(Images.spriteSheet.getSprite(imageNumber + 1), x, y, null);
        }
        if(directionX == -1){
           g2d.drawImage(Images.spriteSheet.getSprite(imageNumber), x, y, null);
        }
        if(directionY == 1){
            g2d.drawImage(Images.spriteSheet.getSprite(imageNumber + 3), x, y, null);
        }
        if(directionY == -1){
            g2d.drawImage(Images.spriteSheet.getSprite(imageNumber + 2), x, y, null);
        }
        if(directionX == 0 && directionY == 0){
            g2d.drawImage(Images.spriteSheet.getSprite(imageNumber + 1), x, y, null);
        }
        g2d.setColor(Color.WHITE);
    }

    public boolean moveChar(int directionX, int directionY){
        int newX = x + directionX * speed;
        int newY = y +  directionY * speed;
        Rectangle rect = new Rectangle(newX, newY, width, height);
        boolean hit = false;
        for(int y = 0; y < level.length; y++){
            for(int x = 0; x < level[y].length; x++) {
                if(level[y][x] != null){
                    if(level[y][x].intersects(rect)){
                        hit = true;
                    }
                }
            }
        }
        if(!hit){
            x = newX;
            y = newY;
            setBounds(x, y, width, height);
            return true;
        }
        return  false;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
        setBounds(x, y, width, height);
    }

    public void changeDirection(int x, int y){
        newDirectionX = x;
        newDirectionY = y;
        if(!moveChar(newDirectionX, newDirectionY)){
            moveChar(directionX, directionY);
        }else{
            directionX = newDirectionX;
            directionY = newDirectionY;
        }

    }

    public void setLevel(Tile[][] level){
        this.level = level;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }
}
