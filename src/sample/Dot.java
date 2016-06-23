package sample;
/**
 * Created by Andrew on 16.06.2016.
 */
public class Dot {
    private int x;
    private int y;

    public Dot() {
        this.x = 0;
        this.y = 0;
    }

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



}
