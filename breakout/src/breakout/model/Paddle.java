package breakout.model;

/*
 * A Paddle for the Breakout game
 *
 */
public class Paddle extends Positionable {
    public static final double PADDLE_WIDTH = 60;  // Default values, use in constructors, not directly
    public static final double PADDLE_HEIGHT = 10;
    public static final double PADDLE_SPEED = 5;

    public Paddle(double x, double y){
        super(x,  y, 0 , 0 , PADDLE_WIDTH , PADDLE_HEIGHT );

    }


}
