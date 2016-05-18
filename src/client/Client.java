package client;

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


    public static void main(String[] args) {
        new Client();
    }

    public Client(){

        try {
            Socket socket = new Socket("localhost", 8000);

            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Panel(this));
        frame.pack();
        frame.setSize(new Dimension(500,500));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Pac-man Hunter");



    }

    public void toServer(int data){
        try {
            toServer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int fromServer(){
        try {
            return fromServer.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
