package server;


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

    public Game(){
        map = new Map();
        pacman = new Pacman();
        path = new Path(new Point(12, 12), map);
        pacman.setPath(path);
        Timer timer = new Timer(1000/60, e -> { pacman.update();});
        timer.start();
    }

    public void update() {

    }

    public Pacman getPacman(){
        return pacman;
    }


}
