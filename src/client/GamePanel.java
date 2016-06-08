package client;

import server.Pacman;
import server.Path;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener{

    private Character character;
    private Character player2;
    private Pacman pacman;
    private Client client;
    private Map map;
    private int xPlayer2Server = 0, yPlayer2Server = 0, xPlayer2 = 0, yPlayer2 = 0, xPacman = 0, yPacman = 0;
    private boolean crazy = false;

    private Path path;

    public GamePanel(Client client){
        character = new Character(32, 32, 32, 32);
        player2 = new Character(32, 32, 32, 32);
        pacman = new Pacman();
        this.client = client;
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        client.toServer((int)character.getX());
        client.toServer((int)character.getY());
        map = new Map();
        path = new Path(new Point(12,12), map);
        character.setLevel(map.getLevel());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        map.draw(g2d);
        character.render(g2d);
        player2.render(g2d);
        pacman.render(g2d);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Coordinates: " + character.getX() + " : " + character.getY(), 0, 20);
        g2d.drawString("Coordinates: " + xPlayer2 + " : " + yPlayer2, 0, 40);
        path.paintPath(g2d);
    }

    public void update() {
        character.update();
        if(crazy)
            map.update();
        int xtoSend = (int) character.getX();
        xtoSend += 1000;
        int ytoSend = (int) character.getY();
        ytoSend += 2000;
        client.toServer(xtoSend);
        client.toServer(ytoSend);

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
        if(e.getKeyCode() == KeyEvent.VK_C){
            crazy = !crazy;
        }
        client.toServer((int)character.getX());
        client.toServer((int)character.getY());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void read(){
            int read1 = client.fromServer();
            int read2 = client.fromServer();
            if(read1 - 2000 < 0){
                xPlayer2Server = read1 - 1000;
            }else if(read1 - 3000 < 0){
                yPlayer2Server = read1 - 2000;
            }else if(read1 - 4000 < 0){
                xPacman = read1 - 3000;
            }else{
                yPacman = read1 - 4000;
            }

            if(read2 - 2000 < 0){
                xPlayer2Server = read2 - 1000;
            }else if(read2 - 3000 < 0){
                yPlayer2Server = read2 - 2000;
            }else if(read2 - 4000 < 0){
                xPacman = read2 - 3000;
            }else{
                yPacman = read2 - 4000;
            }
            if(xPlayer2Server > 0) xPlayer2 = xPlayer2Server;
            if(yPlayer2Server > 0) yPlayer2 = yPlayer2Server;
            player2.setLocation(xPlayer2, yPlayer2);
            pacman.setLocation(xPacman, yPacman);
    }

}
