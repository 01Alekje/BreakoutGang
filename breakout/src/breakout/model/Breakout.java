package breakout.model;


import java.util.ArrayList;
import java.util.List;


/*
 *  Overall all logic for the Breakout Game
 *  Model class representing the full game
 *  This class should use other objects delegate work.
 *
 *  NOTE: Nothing visual here
 *
 */
public class Breakout {

    public static final double GAME_WIDTH = 400;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.05; // Increase ball speed
    public static final long SEC = 1_000_000_000;  // Nano seconds used by JavaFX

    private int nBalls = 5;
    int playerPoints;
    double randomDouble = 8 * Math.random() - 4;
    private Ball ball;
    private List<IPositionable> positionables;
    private List<Brick> allBricks;
    private List<Wall> allWalls;
    private Paddle paddle;
    //private Brick bricks;


    // TODO Constructor that accepts all objects needed for the model
    public Breakout(List<Brick> allBricks, List<Wall> allWalls) {
        this.positionables = new ArrayList<>();
        this.ball = new Ball(200, 200, randomDouble, -5, 20, 20);
        this.positionables.add(this.ball);
        this.paddle = new Paddle(50, 360);
        this.positionables.add(paddle);
        this.allBricks = allBricks;
        this.allWalls = allWalls;
        //this.positionables.add();
    }
    // --------  Game Logic -------------

    private long timeForLastHit;         // To avoid multiple collisions

    public void update(long now) {
        // TODO  Main game loop, start functional decomposition from here
        checkCollision();
        this.ball.move();
        this.paddle.move();
        ballCollision(this.ball, paddle);
        for (Brick brick : allBricks) {
            ballCollision(ball, brick);
        }
        paddleBounds();
        ballBounds();
    }

    // ----- Helper methods--------------
    public void checkCollision () {
        for (Wall w: allWalls) {
            BallsToTheWalls(w, this.ball);
        }
    }

    public void ballBounds () {
        if (this.ball.getY() > 400) {
            newBall();
        }
    }

    public void newBall() {
        if (getnBalls() > 0) {
            this.ball.setY(200);
            this.ball.setX(200);
            this.ball.setDy(-(Math.abs(this.ball.getDy()) + 1));
            this.ball.setDx(8 * Math.random() - 4);
            nBalls -= 1;
        }
    }

    public void movePaddle (double arg) {
        this.paddle.setDx(arg);
    }

    public void paddleBounds () { // kan refaktoreras
        double DX = this.paddle.getDx();
        double GX = this.paddle.getX();
        double GW = this.paddle.getWidth();
        double WR = this.allWalls.get(2).getX(); //this.wallRight.getX();
        double WL = this.allWalls.get(0).getX();
        Paddle P  = this.paddle;
        if (DX > 0) {
            if (GX + GW + DX >=  WR) {
                P.setX(WR - GW - DX);
            }
        } else if (DX < 0) {
            if (GX + DX <=  WL) {
                P.setX(WL - DX);
            }
        }
    }

    public void ballCollision(Ball ball, Positionable p) {
        double ballCenter = ball.getX() + (ball.getWidth() / 2.0);

        if (ballCenter > p.getX() && ballCenter < (p.getX() + p.getWidth())) {
            if (ball.getY() > (p.getY() - ball.getHeight()) && ball.getY() < p.getY()) {
                if (p instanceof Brick) {
                    Brick b = (Brick) p;
                    playerPoints += b.getPoints(); // lurig lurig, vet inte att den är Brick
                    p.setX(p.getX() + 400);
                }
                ball.setDy(ball.getDy() * -1);
            }
        }
    }

    public void BallsToTheWalls(Wall wall, Ball ball) {
        if (wall.getDir() == Wall.Dir.VERTICAL){
            if (ball.getX() < wall.getX() && (ball.getX() > (wall.getX() - ball.getWidth()))) {
                ball.setDx(ball.getDx() * -1);
            }
        } else {
            if (ball.getY() < wall.getY()) {
                ball.setDy(ball.getDy() * -1);
            }
        }
    }

    // Used for functional decomposition

    // --- Used by GUI  ------------------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> bert = new ArrayList<>();
        bert.add(this.ball);
        bert.add(this.paddle);
        for (Wall wall : this.allWalls) {
            bert.add(wall);
        }
        for (Brick brick : this.allBricks) {
            bert.add(brick);
        }
        //bert.add(this.allBricks.get(0));

        return bert;
        //return this.positionables;  // TODO return all objects to be rendered by GUI
    }

/*    public List<Wall> getWalls(){
        List<Wall> allWalls = new ArrayList<>();
        allWalls.add(this.wallLeft);
        allWalls.add(this.wallRoof);
        allWalls.add(this.wallRight);
        return allWalls;
    }*/

    public int getPlayerPoints() {
        return playerPoints;
    }

    public int getnBalls() {
        return nBalls;
    }

}
