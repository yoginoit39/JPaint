package model.strategypattern;


public class SimpleDimensionVerifyStrategy implements DimensionVerifyStrategy {


    @Override
    public boolean verify(Dimension d) {
// ensuring width and height are positive.
        return d.getWidth() > 0 && d.getHeight() > 0;
    }


    @Override
    public Dimension adjustDimension(Dimension dimension) {
        int adjustedWidth = Math.abs(dimension.getWidth());
        int adjustedHeight = Math.abs(dimension.getHeight());


// Use the method that doesnt trigger strategy based adjustment.
        return Dimension.createWithoutStrategy(adjustedWidth, adjustedHeight);
    }


}
