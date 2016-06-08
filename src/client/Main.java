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
    private MenuPanel menu;
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
        menu = new MenuPanel(this);
        client = new Client(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(menu);
        frame.pack();
        frame.setSize(new Dimension(625,735));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Pac-man Hunter");
        timer = new Timer(1000/60, this);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            update();
            render();
    }

    public void update(){
        switch (screen){
            case MENU: menu.update(); break;
            case GAME:  client.update(); break;
            case CONNECT: client.update(); break;
        }
    }

    public void render(){
        switch (screen){
            case MENU: menu.repaint(); break;
            case GAME: client.render(); break;
            case CONNECT: break;
        }
    }

    public void switchScreen(Screen screen){
        this.screen = screen;
        switch (screen){
            case MENU: frame.setContentPane(menu); break;
            case GAME:
                client.start();
                frame.setContentPane(client.getGamePanel());
                frame.repaint();
                frame.invalidate();
                frame.revalidate();

                break;
            case CONNECT:
                frame.setContentPane(client.getConnectPanel());
                System.out.println("SWITCH PANEL");
                frame.repaint();
                frame.invalidate();
                frame.revalidate();
                break;
        }
    }

}
