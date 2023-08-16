package model.strategypattern;

import model.compositePattern.ShapeComponent;
import model.nullobjectpattern.Point;
import model.persistence.ApplicationState;

import java.awt.geom.Ellipse2D;

public class EllipseShape extends PaintShape implements IShapeAction, ShapeComponent {
    public EllipseShape(Point point, Dimension dimension, ApplicationState applicationState) {
        super(point, dimension, applicationState);
        super.shape = new Ellipse2D.Double(point.getX(), point.getY(), dimension.getWidth(), dimension.getHeight());
        super.shapeBorderWhenSelected = new Ellipse2D.Double(point.getX() - SELECT_PADDING, point.getY() - SELECT_PADDING, dimension.getWidth() + SELECT_PADDING * 2, dimension.getHeight() + SELECT_PADDING * 2);
    }

    @Override
    public void move(Point point) {
        super.shape =  new Ellipse2D.Double(point.getX(), point.getY(), getDimension().getWidth(), getDimension().getHeight());
        super.shapeBorderWhenSelected = new Ellipse2D.Double(point.getX() - SELECT_PADDING, point.getY() - SELECT_PADDING, getDimension().getWidth() + SELECT_PADDING * 2, getDimension().getHeight() + SELECT_PADDING * 2);
        super.move(point);
    }

}
