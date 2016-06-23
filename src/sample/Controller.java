package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Time;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable, Runnable {

    public Text pressSrart;
    @FXML
    public Canvas canvas;
    public Text Score;
    public Slider speedSnake = new Slider(1, 300, 100);
    public static Snake snake;
    public static Pie pie;
    public static Thread paintThread;
    public Button newGameB;
    public Button restart;
    public static Pane gameOverPane = new Pane();
    public static Text gameOver = new Text();


    public void StartPaintingThread() {
        paintThread = new Thread(this);
        paintThread.start();
    }

    private void setSlider() {
        speedSnake.setMin(1);
        speedSnake.setMax(500);
        speedSnake.setBlockIncrement(20);
        speedSnake.setValue(400);
    }

    @FXML
    private void renderField(Canvas canvas, int n) throws InterruptedException {
        int cell;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(1, 1, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.setLineWidth(1.0);
        cell = (int) (canvas.getHeight() / n);


        for (int i = 0; i < n + 1; i++) {
            canvas.getGraphicsContext2D().strokeLine(1, i * cell + 1, canvas.getWidth() - 1, i * cell + 1);
        }

        for (int i = 0; i < n * 1.5 + 1; i++) {
            canvas.getGraphicsContext2D().strokeLine(i * cell + 1, 1, i * cell + 1, canvas.getHeight() - 1);
        }

        gc.stroke();
    }

    public void newGame(Event event) throws InterruptedException {
        {
            setSlider();
            pie = new Pie();
            snake = new Snake(canvas);
            pressSrart.setText("");
            renderField(canvas, 20);
            StartPaintingThread();
            System.out.println("Игра началась");
            newGameB.setVisible(false);
            restart.setVisible(true);
            gameOverPane.setVisible(false);
        }
    }

    public void hiScore(Event event) {
    }

    public void Exit(Event event) {
        System.exit(0);
    }

    public void killBot(Event event) {
        System.out.println("Бот удален");

    }

    public void addBot(Event event) throws InterruptedException {
        snake = new Snake(canvas);
        pie = new Pie();


    }

    public void handlePressed(KeyEvent event) throws InterruptedException {

        Snake.dir = event.getCode().toString();

    }

    public void paintSnake(Snake snake) {
        int cell;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        cell = (int) (canvas.getHeight() / 20);
        Iterator iter = snake.snList.iterator();
        int i = 0;
        while (iter.hasNext()) {
            gc.setFill(Color.AQUAMARINE);
            canvas.getGraphicsContext2D().fillRect(2 + cell * (snake.snList.get(i).getX()),
                    2 + cell * (snake.snList.get(i).getY()),
                    cell - 2,
                    cell - 2);
            iter.next();
            i++;
        }
    }

    public void paintPie() {
        int cell;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        cell = (int) (canvas.getHeight() / 20);

        gc.setFill(pie.color);
        canvas.getGraphicsContext2D().fillRect(2 + cell * (pie.coord.getX()),
                2 + cell * pie.coord.getY(),
                cell - 2,
                cell - 2);


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        snake = new Snake(canvas);

    }

    @Override
    public void run() {
        while (!paintThread.isInterrupted()) {
            try {
                renderField(canvas, 20);
                paintSnake(snake);
                paintPie();
                snake.move();
                Score.setText("Score: " + (snake.snList.size() - 3) * 10);
                TimeUnit.MILLISECONDS.sleep(505 - (int) (speedSnake.getValue()));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
