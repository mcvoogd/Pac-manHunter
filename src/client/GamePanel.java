package client;

import server.Pacman;
import server.Path;
import util.Images;

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
    private boolean crazy = false;

    private Path path;
    private boolean won, finished;
    private Main main;
    private SierpinskiTriangle triangle;

    public GamePanel(Client client, Reader reader, Main main){
        this.client = client;
        this.reader = reader;
        this.main = main;
        triangle = new SierpinskiTriangle(5);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(!finished) {
            map.draw(g2d);
            if(character.compareTo(player2) > 0){
                character.render(g2d);
                player2.render(g2d);
            }else{
                player2.render(g2d);
                character.render(g2d);
            }

            pacman.render(g2d);
            g2d.drawImage(Images.scoreBoard, 0, 0, null);
            g2d.setColor(Color.WHITE);
//        g2d.drawString("Coordinates: " + character.getX() + " : " + character.getY(), 0, 20);
//        g2d.drawString("Coordinates: " + Data.getPlayer2X() + " : " + player2.getY(), 0, 40);
            g2d.drawString("Score: " + Data.getScorePlayer1(), 10, 20);
            g2d.drawString("Opponent's Score: " + Data.getScorePlayer2(), 480, 20);
//        path.paintPath(g2d);
        }else{
            if(won){
                g2d.drawImage(Images.won, 0, 0, null);
                triangle.draw(g2d);
            }else{
                g2d.drawImage(Images.lose, 0, 0, null);
                triangle.draw(g2d);
            }
        }

    }

    public void start(){
        character = new Character(32, 32, 32, 32, Data.getPlayerNumber());
        if(Data.getPlayerNumber() == 1){
            player2 = new Character(32, 32, 32, 32, 2);
        }else{
            player2 = new Character(32, 32, 32, 32, 1);
        }
        pacman = new Pacman();
        map = new Map();
        path = new Path(new Point(1, 1), map);
        character.setLevel(map.getLevel());
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
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
        client.toServer(character.getDirectionX() + 7050);
        client.toServer(character.getDirectionY() + 8050);
        pacman.setPosition(Data.getPacmanX(), Data.getPacmanY());
        player2.setLocation(Data.getPlayer2X(), Data.getPlayer2Y());
        player2.setDirectionX(Data.getPlayerDirectionX());
        player2.setDirectionY(Data.getPlayerDirectionY());
        if(Data.getWinner() != 0){
            System.out.println("Winner: " + Data.getWinner());
            finished = true;
            if(Data.getWinner() == Data.getPlayerNumber()){
                won = true;

            }else{
                won = false;
            }
            Data.setWinner(0);
            Data.setStarted(false);
            new Data();
        }
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
        if(finished){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                client.stopRead();
                main.switchScreen(Main.Screen.MENU);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
