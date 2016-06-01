package server;


import client.Map;

/**
 * Created by Martijn on 30-5-2016.
 */
public class Game {

    private Map map;
    private Pacman pacman;

    public Game(){
        map = new Map();
        pacman = new Pacman();

    }

    public void update() {
        pacman.update();
    }

    public Pacman getPacman(){
        return pacman;
    }


}
