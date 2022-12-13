package breakout.model;

public abstract class Positionable implements IPositionable {
    private double x;
    private double y;
    private double dX;
    private double dY;
    private double width;
    private double height;

    public Positionable(double x, double y, double dX, double dY, double width, double height) {
        this.x = x;
        this.y = y;
        this.dX = dX;
        this.dY = dY;
        this.width = width;
        this.height = height;
    }

    public Positionable() {

    }

    public void move(){
        this.x += dX;
        this.y += dY;
    }

    @Override
    public double getX() {
        return this.x;
    }

    public double setX(double i){
        this.x = i;
        return this.x;
    }
    public double setY(double i){
        this.y = i;
        return this.y;
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
