package client;

/**
 * Created by Harmen on 8-6-2016.
 */
public class Data {

    public static volatile int player2X, player2Y, pacmanX, pacmanY;

    public static int getPlayer2X() {
        System.out.println("RETURN " + player2X);
        return player2X;
    }

    public static void setPlayer2X(int player2XNew) {
        if(player2XNew != -1000){
            player2X = player2XNew;
        }
    }

    public static int getPlayer2Y() {
        return player2Y;
    }

    public static void setPlayer2Y(int player2YNew) {
        if(player2YNew != -1000){
            player2Y = player2YNew;
        }
    }

    public static int getPacmanX() {
        return pacmanX;
    }

    public static void setPacmanX(int pacmanXNew) {
        pacmanX = pacmanXNew;
    }

    public static int getPacmanY() {
        return pacmanY;
    }

    public static void setPacmanY(int pacmanYNew) {
        pacmanY = pacmanYNew;
    }
}
