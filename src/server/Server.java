package server;

import util.Images;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server extends JFrame {
    // Text area for displaying contents
    private JTextArea jta = new JTextArea();
    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        new Images();
        // Place text area on the frame
        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);

        setTitle("MultiThreadServer");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // It is necessary to show the frame here!
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("MultiThreadServer started at " + new Date() + '\n');

            // Number a client
            int clientNo = 1;

            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();

                Socket socket2 = serverSocket.accept();

                // Display the client number
                jta.append("Starting thread for client " + clientNo + " and " + (clientNo + 1) +
                        " at " + new Date() + '\n');

                // Find the client's host name, and IP address
                InetAddress inetAddress = socket.getInetAddress();
                jta.append("Client " + clientNo + "'s host name is "
                        + inetAddress.getHostName() + "\n");
                jta.append("Client " + clientNo + "'s IP Address is "
                        + inetAddress.getHostAddress() + "\n");
                clientNo++;
                InetAddress inetAddress2 = socket2.getInetAddress();
                jta.append("Client " + clientNo + "'s host name is "
                        + inetAddress2.getHostName() + "\n");
                jta.append("Client " + clientNo + "'s IP Address is "
                        + inetAddress2.getHostAddress() + "\n");


                // Create a new thread for the connection

                HandleAClient task = new HandleAClient(socket, socket2, new Game());

                // Start the new thread
                new Thread(task).start();

                // Increment clientNo
                clientNo++;


            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    class HandleAClient implements Runnable {
        private Socket socket1, socket2;
        private Game game;

        /** Construct a thread */
        public HandleAClient(Socket socket1, Socket socket2, Game game) {

            this.socket1 = socket1;
            this.socket2 = socket2;
            this.game = game;
        }

        /** Run a thread */
        public void run() {
            try {
                // Create data input and output streams
                DataInputStream inputFromClient1 = new DataInputStream(
                        socket1.getInputStream());
                DataOutputStream outputToClient1 = new DataOutputStream(
                        socket1.getOutputStream());
                DataInputStream inputFromClient2 = new DataInputStream(
                        socket2.getInputStream());
                DataOutputStream outputToClient2 = new DataOutputStream(
                        socket2.getOutputStream());

                int oldx1 = 0;
                int oldy1 = 0;
                int oldx2 = 0;
                int oldy2 = 0;

                int oldDirectionX1 = 0;
                int oldDirectionY1 = 0;
                int oldDirectionX2 = 0;
                int oldDirectionY2 = 0;

                outputToClient1.writeBoolean(true);
                outputToClient2.writeBoolean(true);
                outputToClient1.writeInt(1);
                outputToClient2.writeInt(2);

                // Continuously serve the client
                while (true) {
                    game.update();

                    if(inputFromClient1.available() > 0) {
                        int readInt = inputFromClient1.readInt();
                        int readInt2 = inputFromClient1.readInt();
                        int x1 = oldx1;
                        int y1 = oldy1;
                        int directionX = oldDirectionX1;
                        int directionY = oldDirectionY1;
                        if(readInt - 8000 >= 0){
                            directionY = readInt;
                        }else if(readInt - 7000 >= 0){
                            directionX = readInt;
                        }else if((readInt - 2000) > 0){
                            y1 = readInt;
                        }else{
                            x1 = readInt;
                        }
                        if((readInt2 - 2000) > 0){
                            y1 = readInt2;
                        }else{
                            x1 = readInt2;
                        }
                        if((x1!=oldx1) || (y1 != oldy1)){
                            outputToClient2.writeInt(x1);
                            outputToClient2.writeInt(y1);
                            game.setPlayer1Location(x1 - 1000, y1 - 2000);
                        }
                        if(directionX != oldDirectionX1 || directionY != oldDirectionY1){
                            outputToClient2.writeInt(directionX);
                            outputToClient2.writeInt(directionY);
                        }
                        oldx1=x1;
                        oldy1=y1;
                        oldDirectionX1 = directionX;
                        oldDirectionY1 = directionY;
                    }

                    if(inputFromClient2.available() > 0) {
                        int readInt = inputFromClient2.readInt();
                        int readInt2 = inputFromClient2.readInt();
                        int x2 = oldx2;
                        int y2 = oldy2;
                        int directionX = oldDirectionX2;
                        int directionY = oldDirectionY2;
                        if(readInt - 8000 >= 0){
                            directionY = readInt;
                        }else if(readInt - 7000 >= 0){
                            directionX = readInt;
                        }else if((readInt - 2000) > 0){
                            y2 = readInt;
                        }else{
                            x2 = readInt;
                        }
                        if((readInt2 - 2000) > 0){
                            y2 = readInt2;
                        }else{
                            x2 = readInt2;
                        }
                        if((x2!=oldx2) || (y2 != oldy2)){
                            outputToClient1.writeInt(x2);
                            outputToClient1.writeInt(y2);
                            game.setPlayer2Location(x2 - 1000, y2 - 2000);
                        }
                        if(directionX != oldDirectionX2|| directionY != oldDirectionY2){
                            outputToClient1.writeInt(directionX);
                            outputToClient1.writeInt(directionY);
                        }
                        oldx2=x2;
                        oldy2=y2;
                        oldDirectionX2 = directionX;
                        oldDirectionY2 = directionY;
                    }
                    int x = game.getPacman().getXCoord() + 3000;
                    int y = game.getPacman().getYCoord() + 4000;
                    outputToClient1.writeInt(x);
                    outputToClient1.writeInt(y);
                    outputToClient2.writeInt(x);
                    outputToClient2.writeInt(y);

                    int score1 = game.getScorePlayer1() + 5000;
                    int score2 = game.getScorePlayer2() + 6000;
                    outputToClient1.writeInt(score1);
                    outputToClient1.writeInt(score2);
                    outputToClient2.writeInt(score1 + 1000);
                    outputToClient2.writeInt(score2 - 1000);

                    int pacmanDirectionX = game.getPacman().getxDirection() + 10050;
                    int pacmanDirectionY = game.getPacman().getyDirection() + 11050;
                    outputToClient1.writeInt(pacmanDirectionX);
                    outputToClient1.writeInt(pacmanDirectionY);
                    outputToClient2.writeInt(pacmanDirectionX);
                    outputToClient2.writeInt(pacmanDirectionY);

                    if(game.isFinished()){
                        int winner = game.getWinner() + 13000;
                        outputToClient1.writeInt(winner);
                        outputToClient2.writeInt(winner);
                    }


                }
            }
            catch(IOException e) {
                System.err.println(e);
            }

        }
    }
}
