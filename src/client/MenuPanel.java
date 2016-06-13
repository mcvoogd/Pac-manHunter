package client;

import util.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Martijn on 8-6-2016.
 */
public class MenuPanel extends JPanel implements KeyListener{

    private Main main;

    public MenuPanel(Main main){
        this.main = main;
        setFocusable(true);
        addKeyListener(this);
    }

    public void update(){
        setFocusable(true);
        requestFocus();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Images.start, 0, 0, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            main.switchScreen(Main.Screen.CONNECT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
