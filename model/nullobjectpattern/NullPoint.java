package model.nullobjectpattern;

public class NullPoint implements PointInterface {
    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}
