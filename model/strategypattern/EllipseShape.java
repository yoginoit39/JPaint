package model.strategypattern;

import model.Point;
import model.commandpattern.PaintShape;
import model.interfaces.IShapeAction;
import model.persistence.ApplicationState;

import java.awt.geom.Ellipse2D;

public class EllipseShape extends PaintShape {
    public EllipseShape(Point point, Dimension dimension, ApplicationState applicationState) {
        super(point, dimension, applicationState);
        super.shape = new Ellipse2D.Double(point.getX(), point.getY(), dimension.getWidth(), dimension.getHeight());
    }
}
