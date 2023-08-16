package model.strategypattern;

import model.compositePattern.ShapeComponent;
import model.nullobjectpattern.Point;
import model.persistence.ApplicationState;

import java.awt.*;

public class RectShape extends PaintShape  implements IShapeAction, ShapeComponent {
    public RectShape(Point point, Dimension dimension, ApplicationState applicationState) {
        super(point, dimension, applicationState);
        super.shape = new Rectangle(point.getX(),point.getY(), dimension.getWidth(), dimension.getHeight());
        super.shapeBorderWhenSelected = new Rectangle(point.getX() - SELECT_PADDING,point.getY() - SELECT_PADDING, dimension.getWidth() + SELECT_PADDING * 2, dimension.getHeight() + SELECT_PADDING * 2);
    }

    @Override
    public void move(Point point) {
        super.shape = new Rectangle(point.getX(),point.getY(), super.getDimension().getWidth(), super.getDimension().getHeight());
        super.shapeBorderWhenSelected = new Rectangle(point.getX() - SELECT_PADDING,point.getY() - SELECT_PADDING, super.getDimension().getWidth() + SELECT_PADDING * 2, super.getDimension().getHeight() + SELECT_PADDING * 2);
        super.move(point);
    }


}
