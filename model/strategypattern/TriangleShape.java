package model.strategypattern;

import model.Point;
import model.commandpattern.PaintShape;
import model.interfaces.IShapeAction;
import model.persistence.ApplicationState;

import java.awt.*;

public class TriangleShape extends PaintShape {
    public TriangleShape(Point point, Dimension dimension, ApplicationState applicationState) {
        super(point, dimension, applicationState);
        Polygon triangle = new Polygon();
        triangle.addPoint(point.getX(), point.getY());
        triangle.addPoint(point.getX() + dimension.getWidth(), point.getY() + dimension.getHeight());
        triangle.addPoint(point.getX(), point.getY() + dimension.getHeight());
        super.shape = triangle;
    }
}
