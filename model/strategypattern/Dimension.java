//package model.strategypattern;
//
//public class Dimension {
//
//    private int width;
//    private int height;
//
//    public DimensionVerifyStrategy getDimensionVerifyStrategy() {
//        return dimensionVerifyStrategy;
//    }
//
//    public void setDimensionVerifyStrategy(DimensionVerifyStrategy dimensionVerifyStrategy) {
//        this.dimensionVerifyStrategy = dimensionVerifyStrategy;
//    }
//
//    private DimensionVerifyStrategy dimensionVerifyStrategy;
//
//    public Dimension(int width, int height) {
//        this.height = height;
//        this.width = width;
//    }
//
//    public Dimension() {
//    }
//
//    public int getWidth() {
//        return width;
//    }
//
//    public void setWidth(int width) {
//        this.width = width;
//    }
//
//    public int getHeight() {
//        return height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }
//}


package model.strategypattern;

public class Dimension {

    private int width;
    private int height;
    private DimensionVerifyStrategy strategy;

    public Dimension(int width, int height, DimensionVerifyStrategy strategy) {
        this.strategy = strategy;
        if (strategy.verify(this)) {
            Dimension adjusted = strategy.adjustDimension(this);
            this.width = adjusted.getWidth();
            this.height = adjusted.getHeight();
        } else {
            this.width = width;
            this.height = height;
        }
    }

    // Add a private constructor that does not invoke strategy adjustment.
    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Create a static method to allow creation without strategy invocation.
    public static Dimension createWithoutStrategy(int width, int height) {
        return new Dimension(width, height);
    }
}
