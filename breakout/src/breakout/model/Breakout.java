package breakout.model;


import breakout.event.EventBus;
import breakout.event.ModelEvent;

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
    public static final long SEC = 1_000_000_000;  // Nanoseconds used by JavaFX
    private int nBalls = 5;
    int playerPoints;
    double randomDouble = 8 * Math.random() - 4;
    private final Ball ball;
    private final List<IPositionable> positionables;
    private final List<Brick> allBricks;
    private final List<Wall> allWalls;
    private final Paddle paddle;
    public Paddle getPaddle(){
        return this.paddle;
    }


    // TODO Constructor that accepts all objects needed for the model
    public Breakout(List<Brick> allBricks, List<Wall> allWalls) {
        this.positionables = new ArrayList<>();
        this.ball = new Ball(200, 200, randomDouble, -5, 20, 20);
        this.positionables.add(this.ball);
        this.paddle = new Paddle(50, 360);
        this.positionables.add(paddle);
        this.allBricks = allBricks;
        this.allWalls = allWalls;
    }
    // --------  Game Logic -------------

    private long timeForLastHit;  // To avoid multiple collisions

    public void update(long now) {
        // TODO  Main game loop, start functional decomposition from here
        checkCollision();
        this.ball.move();
        this.paddle.move();
        ballCollision(this.ball, paddle, now);
        for (Brick brick : allBricks) {
            ballCollision(ball, brick, now);
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
            EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.GAME_OVER));
        }
    }

    public void paddleBounds() {
        double dx = this.paddle.getDx();
        double x = this.paddle.getX();
        double width = this.paddle.getWidth();
        double rightWall = this.allWalls.get(2).getX();
        double leftWall = this.allWalls.get(0).getX();
        Paddle p = this.paddle;

        if (dx > 0) {
            if (x + width + dx >= rightWall) {
                p.setX(rightWall - width);
            }
        } else if (dx < 0) {
            if (x + dx <= leftWall) {
                p.setX(leftWall);
            }
        }
    }

    public void ballCollision(Ball ball, Positionable p, long now) {
        double ballX = ball.getX();
        double ballY = ball.getY();
        double ballWidth = ball.getWidth();
        double ballHeight = ball.getHeight();
        double ballRight = ballX + ballWidth;
        double ballBottom = ballY + ballHeight;

        double pX = p.getX();
        double pY = p.getY();
        double pWidth = p.getWidth();
        double pHeight = p.getHeight();
        double pRight = pX + pWidth;
        double pBottom = pY + pHeight;

        if (ballRight > pX && ballX < pRight && ballY < pBottom && ballBottom > pY) {
            if (now - timeForLastHit > SEC / 4) {
                if (p instanceof Brick) {
                    Brick b = (Brick) p;
                    playerPoints += b.getPoints();
                    p.setX(pX + 400);
                    EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.BALL_HIT_BRICK));

                } else {
                    if (now - timeForLastHit > SEC / 4){
                        EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.BALL_HIT_PADDLE));
                    }
                }
                timeForLastHit = now;
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
        bert.addAll(this.allWalls);
        bert.addAll(this.allBricks);
        return bert;
        // TODO return all objects to be rendered by GUI
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public int getnBalls() {
        return nBalls;
    }

}
