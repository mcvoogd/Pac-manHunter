package client;

import util.Images;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Canvas{

    private JFrame frame;
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private GamePanel gamePanel;


//    public static void main(String[] args) {
//        new Client();
//    }

    public Client(){
        new Images();
        try {
            Socket socket = new Socket("localhost", 8000);

            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        gamePanel = new GamePanel(this);
//        frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(gamePanel);
//        frame.pack();
//        frame.setSize(new Dimension(625,735));
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//        frame.setTitle("Pac-man Hunter");
        gamePanel.read();
    }

    public void update(){
        gamePanel.update();
    }

    public void render(){
        gamePanel.repaint();
    }

    public void toServer(int data){
        try {
            toServer.writeInt(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int fromServer(){
        try {
            if(fromServer.available() > 0){
                int read = fromServer.readInt();
                return read;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public JPanel getGamePanel(){
        return gamePanel;
    }

    public void read(){
        gamePanel.read();
    }
}
