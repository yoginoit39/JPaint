package model.strategypattern;

import model.nullobjectpattern.Point;
import model.persistence.ApplicationState;

import java.awt.*;

public class RectShape extends PaintShape implements Cloneable{
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

    @Override
    public RectShape clone() {
        try {
            return (RectShape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
