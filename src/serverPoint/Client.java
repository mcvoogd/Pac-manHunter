package serverPoint;

import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by Harmen on 30-5-2016.
 */
public class Client {

    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;

    public static void main(String[] args) {
        new Client();
    }

    public Client(){
        try {
            Socket socket = new Socket("localhost", 8000);

            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            try {
                toServer.writeObject(new Point(5,5));
                toServer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
