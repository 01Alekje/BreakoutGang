package breakout.model;

/*
        A wall for the ball to bounce
 */
public class Wall extends Positionable{
    private final Dir dirre;
    public enum Dir {
        HORIZONTAL, VERTICAL;
    }

    public Wall(double y, double x, boolean roof) {
        super(x, y, 0, 0, 0, 0);
        if (roof) {
            this.dirre = Dir.HORIZONTAL; // Roof
        } else {
            this.dirre = Dir.VERTICAL; // Wall
        }
    }
    public Dir getDir() {
        return this.dirre;
    }

}

