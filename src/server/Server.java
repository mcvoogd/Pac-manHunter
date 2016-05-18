package server;

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

                HandleAClient task = new HandleAClient(socket, socket2);

                // Start the new thread
                new Thread(task).start();

                // Increment clientNo
                clientNo++;


            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }


    // Inner class
    // Define the thread class for handling new connection
    class HandleAClient implements Runnable {
        private Socket socket1, socket2;

        /** Construct a thread */
        public HandleAClient(Socket socket1, Socket socket2) {

            this.socket1 = socket1;
            this.socket2 = socket2;
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
                // Continuously serve the client
                while (true) {
                    // Receive radius from the client
//                    double radius = inputFromClient.readDouble();

                    if(inputFromClient1.available() > 0) {
                        int x1 = inputFromClient1.read();
                        int y1 = inputFromClient1.read();
                        if((x1!=oldx1) || (y1 != oldy1)){
                            outputToClient2.write(x1);
                            outputToClient2.write(y1);
                        }
                        oldx1=x1;
                        oldy1=y1;
                    }

                    if(inputFromClient2.available() > 0) {
                        int x2 = inputFromClient2.read();
                        int y2 = inputFromClient2.read();
                        if((x2!=oldx2) || (y2 != oldy2)){
                            outputToClient1.write(x2);
                            outputToClient1.write(y2);
                        }
                        oldx2=x2;
                        oldy2=y2;
                    }




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