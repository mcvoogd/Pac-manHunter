package client;

/**
 * Created by Harmen on 8-6-2016.
 */
public class Data {

    public static volatile int player2X, player2Y, pacmanX, pacmanY, scorePlayer1, scorePlayer2, playerNumber;
    public static volatile int playerDirectionX, playerDirectionY, pacmanDirectionX, pacmanDirectionY;
    public static volatile boolean started;

    public static int getPlayer2X() {
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

    public static boolean isStarted() {
        return started;
    }

    public static void setStarted(boolean started) {
        Data.started = started;
    }

    public static int getScorePlayer1() {
        return scorePlayer1;
    }

    public static void setScorePlayer1(int scorePlayer1New) { scorePlayer1 = scorePlayer1New; }

    public static int getScorePlayer2() {
        return scorePlayer2;
    }

    public static void setScorePlayer2(int scorePlayer2New) {
        scorePlayer2 = scorePlayer2New;
    }

    public static int getPlayerNumber() {
        return playerNumber;
    }

    public static void setPlayerNumber(int playerNumber) {
        Data.playerNumber = playerNumber;
    }

    public static int getPlayerDirectionX() {
        return playerDirectionX;
    }

    public static void setPlayerDirectionX(int playerDirectionX) {
        Data.playerDirectionX = playerDirectionX;
    }

    public static int getPlayerDirectionY() {
        return playerDirectionY;
    }

    public static void setPlayerDirectionY(int playerDirectionY) {
        Data.playerDirectionY = playerDirectionY;
    }

    public static int getPacmanDirectionX() { return pacmanDirectionX; }

    public static void setPacmanDirectionX(int pacmanDirectionX) { Data.pacmanDirectionX = pacmanDirectionX; }

    public static int getPacmanDirectionY() { return pacmanDirectionY; }

    public static void setPacmanDirectionY(int pacmanDirectionY) { Data.pacmanDirectionY = pacmanDirectionY;}
}
