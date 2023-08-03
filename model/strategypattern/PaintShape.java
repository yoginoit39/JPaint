package model.strategypattern;

import model.nullobjectpattern.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.persistence.ApplicationState;

import java.awt.*;
import java.util.Objects;

public class PaintShape implements IShapeAction, Cloneable{
    protected Shape shape;
    protected Shape shapeBorderWhenSelected;
    private ShapeType shapeType;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shadingType;
    private boolean isSelected = false;

    private Dimension dimension;
    private Point point;

    public static int BOUND_PADDING = 50;
    public static int SELECT_PADDING = 5;

    public PaintShape(Point point, Dimension dimension, ApplicationState applicationState) {
        this.primaryColor = applicationState.getActivePrimaryColor();
        this.secondaryColor =applicationState.getActiveSecondaryColor();
        this.shadingType = applicationState.getActiveShapeShadingType();
        this.point = point;
        this.setDimension(dimension);
    }



    public Shape getShape() {
        return shape;
    }

    public Shape getShapeBorderWhenSelected() {
        return shapeBorderWhenSelected;
    }

    public ShapeColor getPrimaryColor() {
        return primaryColor;
    }

    public ShapeColor getSecondaryColor() {
        return secondaryColor;
    }

    public ShapeShadingType getShadingType() {
        return shadingType;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean b) {
        isSelected = b;
    }

    public void setPrimaryColor(ShapeColor primaryColor) {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(ShapeColor secondaryColor) {
        this.secondaryColor = secondaryColor;
    }


    public void setShadingType(ShapeShadingType shadingType) {
        this.shadingType = shadingType;
    }


    @Override
    public void move(Point point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaintShape that = (PaintShape) o;
        return Objects.equals(shape, that.shape) && primaryColor == that.primaryColor && secondaryColor == that.secondaryColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, primaryColor, secondaryColor);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Point borderTopLeft() {
        return  new Point(point.getX() - BOUND_PADDING, point.getY() - BOUND_PADDING);
    }

    public Point borderBottomRight() {
        return new Point(point.getX() + dimension.getWidth() + BOUND_PADDING, point.getY() + dimension.getHeight() + BOUND_PADDING);
    }

    public Point borderTopRight() {
        return new Point(point.getX() + dimension.getWidth() + BOUND_PADDING, point.getY() - BOUND_PADDING);
    }

    public Point borderBottomLeft() {
        return new Point(point.getX() - BOUND_PADDING, point.getY() + dimension.getHeight() + BOUND_PADDING);
    }

}
