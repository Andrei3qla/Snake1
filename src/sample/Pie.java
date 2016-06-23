package sample;

import java.util.Iterator;
import java.util.Random;
import javafx.scene.paint.Color;
/**
 * Created by Andrew on 16.06.2016.
 */
public class Pie {
    public Dot coord = new Dot();
    Color color = new Color(0.5, 0.6, 0.7, 1);

    public Pie(int x,int y) {
        this.coord.setXY(x, y);
    }

    public Pie()

    {

        Random r = new Random(System.currentTimeMillis());
        this.coord.setXY(r.nextInt(20),r.nextInt(20));       //Надо будет сделать рандом

        color = new Color(0.1*r.nextInt(6)+0.4,0.1*r.nextInt(6)+0.4,0.1*r.nextInt(6)+0.4,1);

    }


    public int getX() {
        return coord.getX();
    }

    public int getY() {
        return coord.getY();
    }

    public void eatPie(Snake snake) {
        boolean coin=false;

        while (!coin) {
            coin = false;
            Random r = new Random(System.currentTimeMillis());
            this.coord.setXY(r.nextInt(20), r.nextInt(20));
            Iterator it = snake.snList.iterator();

            while (it.hasNext()) {
                if (coord.equals(it.next())) {
                    coin = true;
                    break;
                }
            }
        }
    }

}
