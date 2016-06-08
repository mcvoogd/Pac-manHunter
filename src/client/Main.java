package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Martijn on 8-6-2016.
 */
public class Main extends Canvas implements ActionListener{

    private JFrame frame = new JFrame();
    private Screen screen;
    private Client client;
    private Timer timer;
    private int counter;

    public enum Screen{
        MENU, GAME, CONNECT
    }

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        screen = Screen.MENU;
        client = new Client();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(client.getGamePanel());
        frame.pack();
        frame.setSize(new Dimension(625,735));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Pac-man Hunter");
        timer = new Timer(1, this);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        counter++;
        if(counter > 60){
            update();
            render();
            counter = 0;
        }
        if(screen == Screen.GAME){
            client.read();
        }
    }

    public void update(){
        switch (screen){
            case MENU: break;
            case GAME:  client.update(); break;
            case CONNECT: break;
        }
    }

    public void render(){
        switch (screen){
            case MENU: break;
            case GAME: client.render(); break;
            case CONNECT: break;
        }
    }

    public void switchScreen(Screen screen){
        this.screen = screen;
    }

}
