package server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server extends JFrame {
    // Text area for displaying contents
    private JTextArea jta = new JTextArea();
    private static DataOutputStream outputToClient1, outputToClient2;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
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
                jta.append("Starting thread for client " + clientNo +
                        " at " + new Date() + '\n');

                // Find the client's host name, and IP address
                InetAddress inetAddress = socket.getInetAddress();
                jta.append("Client " + clientNo + "'s host name is "
                        + inetAddress.getHostName() + "\n");
                jta.append("Client " + clientNo + "'s IP Address is "
                        + inetAddress.getHostAddress() + "\n");

                // Create a new thread for the connection
                DataInputStream inputFromClient1 = new DataInputStream(
                        socket.getInputStream());
                outputToClient1 = new DataOutputStream(
                        socket.getOutputStream());
                DataInputStream inputFromClient2 = new DataInputStream(
                        socket2.getInputStream());
                outputToClient2 = new DataOutputStream(
                        socket2.getOutputStream());
                HandleAClient task = new HandleAClient(inputFromClient1, clientNo);
                clientNo++;
                HandleAClient task2 = new HandleAClient(inputFromClient2, clientNo);

                // Start the new thread
                new Thread(task).start();
                new Thread(task2).start();

                // Increment clientNo



            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static synchronized void update(int x, int y, int clientNo)
    {
        if(clientNo == 1){
            try {
                outputToClient2.write(x);
                outputToClient2.write(y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(clientNo == 2){
            try {
                outputToClient1.write(x);
                outputToClient1.write(y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    // Inner class
    // Define the thread class for handling new connection
    class HandleAClient implements Runnable {
        private DataInputStream inputFromClient;
        private int clientNo;

        /** Construct a thread */
        public HandleAClient(DataInputStream inputFromClient, int clientNo) {

            this.inputFromClient = inputFromClient;
            this.clientNo = clientNo;
        }

        /** Run a thread */
        public void run() {
            try {
                // Create data input and output streams



                // Continuously serve the client
                while (true) {
                    // Receive radius from the client
//                    double radius = inputFromClient.readDouble();

                    int x = inputFromClient.read();
                    int y = inputFromClient.read();

                    Server.update(x, y, clientNo);
//
//                    outputToClient1.write(y2);
//                    outputToClient1.write(x2);
//
//                    outputToClient2.write(y1);
//                    outputToClient2.write(x1);


                    // Compute area
//                    double area = radius * radius * Math.PI;

                    // Send area back to the client
//                    outputToClient.writeDouble(area);

//                    jta.append("Player 1:" + "x: " + x1 + " - " + "y: " + y1 + '\n');
//                    jta.append("Player 2:" + "x: " + x2 + " - " + "y: " + y2 + '\n');
//                    jta.append("radius received from client: " +
//                            radius + '\n');
//                    jta.append("Area found: " + area + '\n');
                }
            }
            catch(IOException e) {
                System.err.println(e);
            }

        }
    }
}
