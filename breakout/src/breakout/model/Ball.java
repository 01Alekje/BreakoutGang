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
}
