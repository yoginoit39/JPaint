package model.strategypattern;

public class Dimension {

    private int width;
    private int height;

    public DimensionVerifyStrategy getDimensionVerifyStrategy() {
        return dimensionVerifyStrategy;
    }

    public void setDimensionVerifyStrategy(DimensionVerifyStrategy dimensionVerifyStrategy) {
        this.dimensionVerifyStrategy = dimensionVerifyStrategy;
    }

    private DimensionVerifyStrategy dimensionVerifyStrategy;

    public Dimension(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public Dimension() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
