package sample;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static sample.Controller.gameOver;
import static sample.Controller.gameOverPane;
import static sample.Controller.paintThread;

/**
 * Created by Andrew on 16.06.2016.
 */
public class Snake  {
    public ArrayList<Dot> snList = new ArrayList<>();
    private Dot nextStep;
    public Canvas canvas;
    static String dir = "D";
    private String lastDir;

    public Snake(Canvas canvas) {
        this.snList.add(new Dot(3, 1));
        this.snList.add(new Dot(2, 1));
        this.snList.add(new Dot(1, 1));
        this.nextStep = new Dot(4, 1);
        this.canvas = canvas;
        dir = "D";
    }


    public boolean collideItself() {
        ArrayList<Dot> dotsWithoutHead = new ArrayList<>(snList);
        dotsWithoutHead.remove(snList.get(0));
        for (Dot dot : dotsWithoutHead) {
            if ((snList.get(0).getX() == dot.getX()) & (snList.get(0).getY() == dot.getY()))
                return true;
        }
        return false;
    }


    public boolean collidePie() {

        if ((this.snList.get(0).getX() == Controller.pie.getX()) & (this.snList.get(0).getY() == Controller.pie.getY()))
        {
            return true;
        }
        return false;

    }


    public void moveLeft() {
        if (lastDir != "D") {

            nextStep.setXY(snList.get(0).getX() - 1, snList.get(0).getY());
            if (nextStep.getX() < 0) {
                nextStep.setX(29);
            }
            Dot buf = new Dot(nextStep.getX(), nextStep.getY());
            snList.add(0, buf);
            lastDir = "A";

            if (!collidePie()) {
                snList.remove(snList.size() - 1);
            } else {
                Controller.pie = new Pie();

            }
        }
        else {
                moveRight();
            }
    }

    public void moveRight() {
        if (lastDir != "A") {

            nextStep.setXY(snList.get(0).getX() + 1, snList.get(0).getY());
            if (nextStep.getX() > 29) {
                nextStep.setX(0);
            }
            Dot buf = new Dot(nextStep.getX(), nextStep.getY());
            snList.add(0, buf);
            lastDir = "D";
            if (!collidePie()) {
                snList.remove(snList.size() - 1);
            } else {
                Controller.pie = new Pie();
            }
        }
        else{
            moveLeft();
        }
    }

    public void moveUp() {
        if (lastDir != "S") {
            nextStep.setXY(snList.get(0).getX(), snList.get(0).getY() - 1);
            if (nextStep.getY() < 0) {
                nextStep.setY(19);
            }
            Dot buf = new Dot(nextStep.getX(), nextStep.getY());
            snList.add(0, buf);
            lastDir = "W";
            if (!collidePie()) {
                snList.remove(snList.size() - 1);
            } else {
                Controller.pie = new Pie();
            }
        }
        else
        {
            moveDown();
        }
    }

    public void moveDown() {
        if (lastDir != "W") {
            nextStep.setXY(snList.get(0).getX(), snList.get(0).getY() + 1);
            if (nextStep.getY() > 19) {
                nextStep.setY(0);
            }
            Dot buf = new Dot(nextStep.getX(), nextStep.getY());
            snList.add(0, buf);
            lastDir = "S";

            if (!collidePie()) {
                snList.remove(snList.size() - 1);
            }
            else {
                Controller.pie = new Pie();
            }
        }
        else {
            moveUp();
        }
    }


    public void move() {
        if (!collideItself()) {
            switch (dir) {
                case "W":
                    moveUp();
                    break;
                case "S":
                    moveDown();
                    break;
                case "A":
                    moveLeft();
                    break;
                case "D":
                    moveRight();
                    break;
                default:
                    break;
            }
        }
            else {



        }
    }



}

