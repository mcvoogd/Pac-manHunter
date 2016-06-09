package client;

/**
 * Created by Harmen on 8-6-2016.
 */
public class Data {

    public static volatile int player2X;

    public static int getPlayer2X() {
        System.out.println("RETURN " + player2X);
        return player2X;
    }

    public static void setPlayer2X(int player2XNew) {
        if(player2XNew != -1000){
            System.out.println("SET " + player2XNew);
        }
      player2X = player2XNew;
        if(player2X != -1000)
            System.out.println("DATA " + player2X);
    }
}
