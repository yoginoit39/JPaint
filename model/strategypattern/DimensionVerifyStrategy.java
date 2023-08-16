package model.strategypattern;

public interface DimensionVerifyStrategy {
   boolean verify(Dimension dimension);
   Dimension adjustDimension(Dimension dimension);
}