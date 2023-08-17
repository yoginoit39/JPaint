package model.nullobjectpattern;


public class Point implements PointInterface {


    private int x;
    private int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public Point() {
    }


    public static PointInterface createPoint(int x, int y) {
        if (x == 0 && y == 0) {
            return new NullPoint(); // Return NullPoint for (0, 0)
        }
        return new Point(x, y);
    }


    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }


    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
