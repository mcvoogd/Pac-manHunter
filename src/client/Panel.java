package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements ActionListener, KeyListener{

    Character character;
    Character player2;
    Timer timer;
    Client client;
    private Map map;
    private int xPlayer2Server = 0, yPlayer2Server = 0, xPlayer2 = 0, yPlayer2 = 0;

    public Panel(Client client){
        character = new Character(32, 32, 32, 32);
        player2 = new Character(32, 32, 32, 32);
        timer = new Timer(1000/60, this);
        this.client = client;
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        client.toServer((int)character.getX());
        client.toServer((int)character.getY());
        map = new Map();
        character.setLevel(map.getLevel());
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        map.draw(g2d);
        character.render(g2d);
        player2.render(g2d);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Coordinates: " + character.getX() + " : " + character.getY(), 0, 20);
        g2d.drawString("Coordinates: " + xPlayer2 + " : " + yPlayer2, 0, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        character.update();
        map.update();
        client.toServer((int)character.getX());
        client.toServer((int)character.getY());
        repaint();
        xPlayer2Server = client.fromServer();
        yPlayer2Server = client.fromServer();
        if(xPlayer2Server > 0) xPlayer2 = xPlayer2Server;
        if(yPlayer2Server > 0) yPlayer2 = yPlayer2Server;
        player2.setLocation(xPlayer2, yPlayer2);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            character.changeDirection(0, -1);
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            character.changeDirection(-1, 0);
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            character.changeDirection(0, 1);
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            character.changeDirection(1, 0);
        }
        client.toServer((int)character.getX());
        client.toServer((int)character.getY());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
