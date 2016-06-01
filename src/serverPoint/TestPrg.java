package serverPoint;

import javax.net.ServerSocketFactory;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestPrg {

    public static void main(String... args) throws IOException {
        ServerListener server = new ServerListener();
        server.start();

        Socket socketToServer = new Socket("localhost", 15000);
        ObjectOutputStream outStream = new ObjectOutputStream(socketToServer.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socketToServer.getInputStream());

        for (int i=1; i<10; i++) {
            try {
                Thread.sleep((long) (Math.random()*3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sending object to server ...");
            outStream.writeObject(new Point(8,5));
            System.out.println("Available: "+ inStream.available());
            if(inStream.available() > 0){
                try {
                    System.out.println("Read from client" + inStream.readObject());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        System.exit(0);

    }

    static class ServerListener extends Thread {

        private ServerSocket serverSocket;

        ServerListener() throws IOException {
            serverSocket = ServerSocketFactory.getDefault().createServerSocket(15000);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final Socket socketToClient = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(socketToClient);
                    clientHandler.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ClientHandler extends Thread{
        private Socket socket;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;

        ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Object o = inputStream.readObject();
                    Point point = (Point) o;
                    System.out.println("Read object: "+ point);

                } catch (IOException e) {
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.writeObject(new Point(12, 5));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}