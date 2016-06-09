package client;

import util.Images;

import javax.swing.*;

public class Client{

    private JFrame frame;

    private GamePanel gamePanel;
    private ConnectPanel connectPanel;
    private boolean start = false;
    private Main main;
    private Reader reader;



    public Client(Main main){
        this.main = main;
        reader = new Reader(this);
        new Thread(reader).start();

        connectPanel = new ConnectPanel(main);
        gamePanel = new GamePanel(this, reader);
    }

    public void update(){
        if(start){
            gamePanel.update();
        }else{
            boolean b = Data.isStarted();
            connectPanel.update();
            if(b){
                System.out.println("START GAME");
                connectPanel.switchPanel();
                start();
            }
        }
    }

    public void render(){
        if(start){
            gamePanel.repaint();
        }else{
            connectPanel.repaint();
        }
    }

    public void toServer(int data){
        reader.toServer(data);
    }

    public void start(){
        start = true;
        gamePanel.start();

    }

    public JPanel getGamePanel(){
        return gamePanel;
    }

    public JPanel getConnectPanel(){
        return connectPanel;
    }

}

