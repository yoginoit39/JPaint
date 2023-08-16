package model.strategypattern;

import model.compositePattern.ShapeComponent;
import model.nullobjectpattern.Point;
import model.persistence.ApplicationState;

import java.awt.*;

public class TriangleShape extends PaintShape implements IShapeAction, ShapeComponent {
    public TriangleShape(Point point, Dimension dimension, ApplicationState applicationState) {
        super(point, dimension, applicationState);
        Polygon triangle = new Polygon();
        triangle.addPoint(point.getX(), point.getY());
        triangle.addPoint(point.getX() + dimension.getWidth(), point.getY() + dimension.getHeight());
        triangle.addPoint(point.getX(), point.getY() + dimension.getHeight());

        Polygon triangleBorder = new Polygon();
        triangleBorder.addPoint(point.getX() - SELECT_PADDING, point.getY() - SELECT_PADDING);
        triangleBorder.addPoint(point.getX() + dimension.getWidth() + SELECT_PADDING, point.getY() +
                dimension.getHeight() + SELECT_PADDING);
        triangleBorder.addPoint(point.getX() - SELECT_PADDING, point.getY() + dimension.getHeight()
                + SELECT_PADDING);

        super.shape = triangle;
        super.shapeBorderWhenSelected = triangleBorder;
    }

    @Override
    public void move(Point point) {
        Polygon triangle = new Polygon();
        triangle.addPoint(point.getX(), point.getY());
        triangle.addPoint(point.getX() + getDimension().getWidth(), point.getY() + getDimension().getHeight());
        triangle.addPoint(point.getX(), point.getY() + getDimension().getHeight());

        Polygon triangleBorder = new Polygon();
        triangleBorder.addPoint(point.getX() - SELECT_PADDING, point.getY() - SELECT_PADDING);
        triangleBorder.addPoint(point.getX() + getDimension().getWidth() + SELECT_PADDING, point.getY()
                + getDimension().getHeight() + SELECT_PADDING);
        triangleBorder.addPoint(point.getX() - SELECT_PADDING, point.getY() + getDimension().getHeight()
                + SELECT_PADDING);

        super.shape = triangle;
        super.shapeBorderWhenSelected = triangleBorder;
        super.move(point);
    }


}
