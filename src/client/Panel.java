package client;

import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class Panel extends JPanel implements ActionListener, KeyListener{

    Character character;
    Character player2;
    Timer timer;
    Client client;

    public Panel(Client client){
        character = new Character();
        player2 = new Character();
        timer = new Timer(1000/60, this);
        this.client = client;
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        client.toServer(character.getX());
        client.toServer(character.getY());
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        character.render(g2d);
        player2.render(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        character.update();
//        client.toServer(character.getX());
//        client.toServer(character.getY());
        repaint();
        int xPlayer2 = client.fromServer();
        int yPlayer2 = client.fromServer();
        player2.setLocation(xPlayer2, yPlayer2);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            character.move(0, -1);
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            character.move(-1, 0);
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            character.move(0, 1);
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            character.move(1, 0);
        }
        client.toServer(character.getX());
        client.toServer(character.getY());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
