package client;

import util.Images;

import javax.swing.*;

public class Client implements Comparable{

    private JFrame frame;

    private GamePanel gamePanel;
    private ConnectPanel connectPanel;
    private boolean start = false;
    private Main main;
    private Reader reader;
    private Thread read;
    private int clientNumber;


    public Client(Main main){
        this.main = main;
        reader = new Reader(this);
        read = new Thread(reader);
        read.start();

        connectPanel = new ConnectPanel(main);
        gamePanel = new GamePanel(this, reader, main);
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

    public void stopRead(){
        System.out.println("Stop read");
        read.interrupt();
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Client){
            if(clientNumber > ((Client) o).getClientNumber()){
                return 1;
            }
            if(clientNumber == ((Client) o).getClientNumber()){
                return 0;
            }
        }
        return -1;
    }
}

