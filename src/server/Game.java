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
    private int scorePlayer1, scorePlayer2, winner;
    private boolean player1Hit, player2Hit, finished;
    private Path[] paths;

    public Game(){
        map = new Map();
        pacman = new Pacman();
        paths = new Path[4];
        paths[0] = new Path(new Point(17, 20), map);
        paths[1] = new Path(new Point(17, 1), map);
        paths[2] = new Path(new Point(1, 20), map);
        paths[3] = new Path(new Point(1, 1), map);
        pacman.setPath(paths[(int)(Math.random() *3)]);
        pacman.teleport((32* 10), (32 * 11));
        player1 = new Character(32, 32, 32, 32);
        player2 = new Character(32, 32, 32, 32);
        Timer timer = new Timer(1000/60, e -> { pacman.update();});
        timer.start();

    }

    public void update() {
        if(pacman.isReached()){
            pacman.setPath(paths[(int)(Math.random() * (paths.length - 1))]);
        }
        if(player1.intersects(pacman) && !player1Hit){
            System.out.println("PLAYER 1 HIT");
            scorePlayer1++;
            pacman.teleport((32* 10), (32 * 11) - 2);
            pacman.setPath(paths[(int)(Math.random() * (paths.length - 1))]);
            player1Hit = true;
        }else if(!player1.intersects(pacman)){
            player1Hit = false;
        }
        if(player2.intersects(pacman) && !player2Hit){
            System.out.println("PLAYER 2 HIT");
            scorePlayer2++;
            pacman.teleport((32* 10), (32 * 11) - 2);
            pacman.setPath(paths[(int)(Math.random() * (paths.length - 1))]);
            player2Hit = true;
        }else if(!player2.intersects(pacman)){
            player2Hit = false;
        }

        if(scorePlayer1 >= 10 || scorePlayer2 >= 10){
            finished = true;
            System.out.println("FINISHED");
            if(scorePlayer1 >= 10){
                winner = 1;
            }else{
                winner = 2;
            }
        }
    }

    public Pacman getPacman(){
        return pacman;
    }

    public void setPlayer1Location(int x, int y){ player1.setLocation(x, y); }

    public void setPlayer2Location(int x, int y){
        player2.setLocation(x, y);
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getWinner() {
        return winner;
    }
}
