package serverPoint;

/**
 * Created by Harmen on 30-5-2016.
 */
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
            jta.append("PointServer started at " + new Date() + '\n');

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

                HandleAClientPoint task = new HandleAClientPoint(socket, socket2);

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
    class HandleAClientPoint implements Runnable {
        private Socket socket1, socket2;

        /** Construct a thread */
        public HandleAClientPoint(Socket socket1, Socket socket2) {
            jta.append("Handeled Clients" + "\n");
            this.socket1 = socket1;
            this.socket2 = socket2;
        }

        /** Run a thread */
        public void run() {
            jta.append("Run thread" + "\n");
            try {
                // Create data input and output streams
                ObjectOutputStream outputToClient1 = new ObjectOutputStream(
                        socket1.getOutputStream());
                jta.append("Output 1" + "\n");
                ObjectInputStream inputFromClient1 = new ObjectInputStream(
                        socket1.getInputStream());
                jta.append("Input 1" + "\n");
                ObjectOutputStream outputToClient2 = new ObjectOutputStream(
                        socket2.getOutputStream());
                jta.append("Output 2" + "\n");
                ObjectInputStream inputFromClient2 = new ObjectInputStream(
                        socket2.getInputStream());
                jta.append("Input 2" + "\n");
                // Continuously serve the client
                while (true) {
//                    jta.append(true + "\n");
                    Point pointPlayer1 = null;
                    Point pointPlayer2 = null;
                    if(inputFromClient1.available() > 0) {
                        jta.append("Recieving data player 1");
                        try {
                            Object o = inputFromClient1.readObject();
                            pointPlayer1 = (Point) o;
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                    if(inputFromClient2.available() > 0) {
                        jta.append("Recieving data player 2");
                        try {
                            Object o = inputFromClient2.readObject();
                            pointPlayer2 = (Point) o;
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if(pointPlayer1 != null)
                        jta.append("Player 1:" + "x: " + pointPlayer1.getX() + " - " + "y: " + pointPlayer1.getY() + '\n');
                    if(pointPlayer2 != null)
                        jta.append("Player 2:" + "x: " + pointPlayer2.getX() + " - " + "y: " + pointPlayer2.getY() + '\n');

                }
            }
            catch(IOException e) {
                System.out.println(e.getStackTrace());
            }

        }
    }
}
