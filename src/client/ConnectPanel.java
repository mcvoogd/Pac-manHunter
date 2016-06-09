package client;

import util.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Harmen on 8-6-2016.
 */
public class ConnectPanel extends JPanel {

    private Main main;
    private int spriteNumber;
    private int count;

    public ConnectPanel(Main main){
        this.main = main;
    }

    public void update(){
        count++;
        if(count > 2){
            spriteNumber++;
            if(spriteNumber > 11){
                spriteNumber = 0;
            }
            repaint();
            count = 0;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Images.connecting, 0, 0, null);
        g2.drawImage(Images.loading_sprite.getSprite(spriteNumber), 60, 550, null);
    }

    public void switchPanel(){
        main.switchScreen(Main.Screen.GAME);
    }

}
