package breakout.model;

/*
 *   A brick for the rows of bricks
 */

public class Brick extends Positionable {
    public static final double BRICK_WIDTH = 20;  // Default values, use in constructors, not directly
    public static final double BRICK_HEIGHT = 10;
    private final int points;

    public Brick (double x, double y, int points) {
        super(x, y, 0, 0, BRICK_WIDTH, BRICK_HEIGHT);
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

}

