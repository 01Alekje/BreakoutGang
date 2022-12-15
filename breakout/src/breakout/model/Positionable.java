package breakout.model;

public abstract class Positionable implements IPositionable {
    private double x;
    private double y;
    private double dX;
    private double dY;
    private final double width;
    private final double height;

    public Positionable(double x, double y, double dX, double dY, double width, double height) {
        this.x = x;
        this.y = y;
        this.dX = dX;
        this.dY = dY;
        this.width = width;
        this.height = height;
    }

    public void move(){
        this.x += dX;
        this.y += dY;
    }

    @Override
    public double getX() {
        return this.x;
    }

    public void setX(double i){
        this.x = i;
    }
    public void setY(double i){
        this.y = i;
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
    }

}
