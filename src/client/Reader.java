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
    private int xPlayer2Server = 0, yPlayer2Server = 0, scorePlayer1 = 0, scorePlayer2 = 0, xPacman = 0, yPacman = 0;
    private boolean started;

    public Reader(Client client){
        this.client = client;
        System.out.println("NEW READER");
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
        while(true) {
            int read1 = 0;
            if (!started) {
                try {
                    if (fromServer.available() > 0) {
                        started = fromServer.readBoolean();
                        Data.setStarted(started);
                        int number = fromServer.readInt();
                        Data.setPlayerNumber(number);
                        client.setClientNumber(number);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {


                try {
                    if (fromServer.available() > 0) {
                        int read = fromServer.readInt();
                        read1 = read;

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int read2 = 0;
                try {
                    if (fromServer.available() > 0) {
                        int read = fromServer.readInt();
                        read2 = read;

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (read1 - 2000 < 0) {
                    xPlayer2Server = read1 - 1000;
                    Data.setPlayer2X(xPlayer2Server);
                } else if (read1 - 3000 < 0) {
                    yPlayer2Server = read1 - 2000;
                    Data.setPlayer2Y(yPlayer2Server);
                } else if (read1 - 4000 < 0) {
                    xPacman = read1 - 3000;
                    Data.setPacmanX(xPacman);
                } else if(read1 - 5000 < 0){
                    yPacman = read1 - 4000;
                    Data.setPacmanY(yPacman);
                } else if(read1 - 6000 < 0){
                    scorePlayer1 = read1 - 5000;
                    Data.setScorePlayer1(scorePlayer1);
                } else if(read1 - 7000 < 0){
                    scorePlayer2 = read1 - 6000;
                    Data.setScorePlayer2(scorePlayer2);
                } else if(read1 - 7000 < 900){
                    Data.setPlayerDirectionX(read1 - 7050);
                } else if(read1 - 8000 < 900){
                    Data.setPlayerDirectionY(read1 - 8050);
                } else if(read1 - 10050 == 1 || read1 - 10050 == -1 || read1 - 10050 == 0) {
                    Data.setPacmanDirectionX(read1 - 10050);
                } else if(read1 - 11050 == 1 || read1 - 11050 == -1 || read1 - 11050 == 0) {
                    Data.setPacmanDirectionY(read1 - 11050);
                }else if(read1 - 14000 < 0){
                Data.setWinner(read1 - 13000);
                }


                if (read2 - 2000 < 0) {
                    xPlayer2Server = read2 - 1000;
                    Data.setPlayer2X(xPlayer2Server);
                } else if (read2 - 3000 < 0) {
                    yPlayer2Server = read2 - 2000;
                    Data.setPlayer2Y(yPlayer2Server);
                } else if (read2 - 4000 < 0) {
                    xPacman = read2 - 3000;
                    Data.setPacmanX(xPacman);
                } else if(read2 - 5000 < 0){
                    yPacman = read2 - 4000;
                    Data.setPacmanY(yPacman);
                } else if(read2 - 6000 < 0){
                    scorePlayer1 = read2 - 5000;
                    Data.setScorePlayer1(scorePlayer1);
                } else if(read2 - 7000 < 0){
                    scorePlayer2 = read2 - 6000;
                    Data.setScorePlayer2(scorePlayer2);
                } else if(read2 - 7000 < 900){
                    Data.setPlayerDirectionX(read2 - 7050);
                } else if(read2 - 8000 < 900){
                    Data.setPlayerDirectionY(read2 - 8050);
                } else if(read2 - 10050 == 1 || read2 - 10050 == -1 || read2 - 10050 == 0) {
                    Data.setPacmanDirectionX(read2 - 10050);
                } else if(read2 - 11050 == 1 || read2 - 11050 == -1 || read2 - 11050 == 0) {
                    Data.setPacmanDirectionY(read2 - 11050);
//                    System.out.println(read2);
                }else if(read2 - 14000 < 0){
                    Data.setWinner(read2 - 13000);
                }
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
}
