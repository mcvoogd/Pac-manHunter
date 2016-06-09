package client;

import java.io.*;
import java.net.Socket;

/**
 * Created by Harmen on 8-6-2016.
 */
public class Reader implements Runnable {
    private Client client;
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    private int xPlayer2Server = 0, yPlayer2Server = 0, xPlayer2 = 0, yPlayer2 = 0, xPacman = 0, yPacman = 0;

    private int testX;
    public void setTestX(int testX){
        this.testX = testX;
        if(this.testX != -1000){
            System.out.println("TEST X " + this.testX);
        }

    }

    public int getTestX(){
        return testX;
    }



    public Reader(Client client){
        this.client = client;
        try {
            Socket socket = new Socket("localhost", 8000);

            fromServer = new DataInputStream(socket.getInputStream());
            System.out.println("INPUT MADE");
            toServer = new DataOutputStream(socket.getOutputStream());
            System.out.println("OUTPUT MADE");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            int read1 = 0;
            try {
                if(fromServer.available() > 0){
                    int read = fromServer.readInt();
                    read1 = read;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            int read2 = 0;
            try {
                if(fromServer.available() > 0){
                    int read = fromServer.readInt();
                    read2 = read;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(read1 - 2000 < 0){
                xPlayer2Server = read1 - 1000;
                Data.setPlayer2X(xPlayer2Server);
            }else if(read1 - 3000 < 0){
                yPlayer2Server = read1 - 2000;
                Data.setPlayer2Y(yPlayer2Server);
            }else if(read1 - 4000 < 0){
                xPacman = read1 - 3000;
                Data.setPacmanX(xPacman);
            }else{
                yPacman = read1 - 4000;
                Data.setPacmanY(yPacman);
            }

            if(read2 - 2000 < 0){
                xPlayer2Server = read2 - 1000;
                Data.setPlayer2X(xPlayer2Server);
            }else if(read2 - 3000 < 0){
                yPlayer2Server = read2 - 2000;
                Data.setPlayer2Y(yPlayer2Server);
            }else if(read2 - 4000 < 0){
                xPacman = read2 - 3000;
                Data.setPacmanX(xPacman);
            }else{
                yPacman = read2 - 4000;
                Data.setPacmanY(yPacman);
            }
        }
    }

    public void toServer(int data){
        try {
            toServer.writeInt(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getyPacman() {
        return yPacman;
    }

    public int getxPacman() {
        return xPacman;
    }

//    public int getyPlayer2() {
//        return yPlayer2;
//    }
//
//    public int getxPlayer2() {
//        return xPlayer2;
//    }

    public int getyPlayer2Server() {
        return yPlayer2Server;
    }

    public int getxPlayer2Server() {
        return xPlayer2Server;
    }
}
