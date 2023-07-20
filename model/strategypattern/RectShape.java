package model.strategypattern;

import model.Point;
import model.commandpattern.PaintShape;
import model.persistence.ApplicationState;

import java.awt.*;

public class RectShape extends PaintShape {
    public RectShape(Point point, Dimension dimension, ApplicationState applicationState) {
        super(point, dimension, applicationState);
        super.shape = new Rectangle(point.getX(),point.getY(), dimension.getWidth(), dimension.getHeight());
    }
}
