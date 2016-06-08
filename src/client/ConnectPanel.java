package client;

import util.Images;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Harmen on 8-6-2016.
 */
public class ConnectPanel extends JPanel {

    private Main main;

    public ConnectPanel(Main main){
        this.main = main;
    }

    public void update(){
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Images.connecting, 0, 0, null);
    }

    public void switchPanel(){
        main.switchScreen(Main.Screen.GAME);
    }

}
