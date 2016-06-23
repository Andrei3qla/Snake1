package sample;

/**
 * Created by Andrew on 16.06.2016.
 */
public class Player {
    private static Player instance;

    private Player() {
    }

    public static synchronized Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    static int Score;
    static volatile Snake snake;


}
