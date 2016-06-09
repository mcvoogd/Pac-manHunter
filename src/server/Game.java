package server;


import client.Character;
import client.Map;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Martijn on 30-5-2016.
 */
public class Game {

    private Map map;
    private Pacman pacman;
    private Path path;
    private Character player1, player2;
    private int scorePlayer1, scorePlayer2;
    private boolean player1Hit, player2Hit;
    private Path[] paths;

    public Game(){
        map = new Map();
        pacman = new Pacman();
        paths = new Path[4];
        paths[0] = new Path(new Point(17, 0), map);
        paths[1] = new Path(new Point(1, 0), map);
        paths[2] = new Path(new Point(1, 19), map);
        paths[3] = new Path(new Point(17, 19), map);
        pacman.setPath(path);
        player1 = new Character(32, 32, 32, 32);
        player2 = new Character(32, 32, 32, 32);
        Timer timer = new Timer(1000/60, e -> { pacman.update();});
        timer.start();
    }

    public void update() {
//        if(pacman.isReached()){
//            int pointX = (int) (Math.random() * 12);
//            path = new Path(new Point(pointX, (int) (Math.random() * 12)), map);
//            pacman.setPath(path);
//        }

        if(player1.intersects(pacman) && !player1Hit){
            scorePlayer1++;
            System.out.println("hit");
            pacman.teleport(32* 9, 32 * 10);
            pacman.setPath(paths[(int)(Math.random() * 4)]);
            player1Hit = true;
        }else if(!player1.intersects(pacman)){
            player1Hit = false;
        }
        if(player2.intersects(pacman) && !player2Hit){
            scorePlayer2++;
            pacman.teleport(32* 9, 32 * 10);
            pacman.setPath(paths[(int)(Math.random() * 4)]);
            player2Hit = true;
        }else if(!player2.intersects(pacman)){
            player2Hit = false;
        }
    }

    public Pacman getPacman(){
        return pacman;
    }

    public void setPlayer1Location(int x, int y){
        System.out.println(x);
        player1.setLocation(x, y);
        System.out.println("Player 1: " + player1.getLocation() + " : Pacman: " + pacman.getLocation());

    }

    public void setPlayer2Location(int x, int y){
        player2.setLocation(x, y);
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }
}
