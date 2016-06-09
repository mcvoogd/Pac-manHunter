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
    private Reader reader;
    private Map map;
    private int xPlayer2Server = 0, yPlayer2Server = 0, xPlayer2 = 0, yPlayer2 = 0, xPacman = 0, yPacman = 0;
    private boolean crazy = false;

    private Path path;
    private boolean start = false;

    private int playerX2;

    public int getPlayerX2() {
        return playerX2;
    }

    public void setPlayerX2(int playerX2) {
        this.playerX2 = playerX2;
    }

    public GamePanel(Client client, Reader reader){
        this.client = client;
        this.reader = reader;
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
        g2d.drawString("Coordinates: " + Data.getPlayer2X() + " : " + player2.getY(), 0, 40);
        path.paintPath(g2d);
    }

    public void start(){
        character = new Character(32, 32, 32, 32);
        player2 = new Character(32, 32, 32, 32);
        pacman = new Pacman();
//        client.toServer((int)character.getX());
//        client.toServer((int)character.getY());
        map = new Map();
        path = new Path(new Point(12,12), map);
        character.setLevel(map.getLevel());
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        start = true;
    }

    public void update() {
        setFocusable(true);
        requestFocus();
        character.update();
        if(crazy)
            map.update();
        int xtoSend = (int) character.getX();
        xtoSend += 1000;
        int ytoSend = (int) character.getY();
        ytoSend += 2000;
        client.toServer(xtoSend);
        client.toServer(ytoSend);
        player2.setLocation(Data.getPlayer2X(), reader.getyPlayer2Server());
        pacman.setLocation(reader.getxPacman(), reader.getyPacman());

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KEY PRESSED");
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


}
