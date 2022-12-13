package breakout.model;

import java.util.Random;

import static breakout.model.Breakout.GAME_HEIGHT;
import static breakout.model.Breakout.GAME_WIDTH;

/*
 *    A Ball for the Breakout game
 */

public class Ball extends Positionable {

    public Ball (double x, double y, double dX, double dY, double width,  double height) {
        super(x, y, dX, dY, width, height);
    }

    /*private double x;
    private double y;
    private double dX;
    private double dY;
    private double width;
    private double height;

    public Ball(double x, double y, double dX, double dY, double width,  double height) {
        this.x = x;
        this.y = y;
        this.dX = dX;
        this.dY = dY;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    public void setDx (double indx) {
        this.dX = indx;
    }

    public double getDx() {
        return this.dX;
    }

    public double getDy() {
        return this.dY;
    }

    public void setDy (double indy) {
        this.dY = indy;
    }*/

}
