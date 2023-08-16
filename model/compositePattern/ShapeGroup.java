package model.compositePattern;

import model.ShapeColor;
import model.ShapeList;
import model.ShapeShadingType;
import model.strategypattern.DimensionVerifyStrategy;
import model.strategypattern.PaintShape;
import model.nullobjectpattern.Point;
import model.persistence.ApplicationState;
import model.strategypattern.Dimension;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ShapeGroup extends PaintShape implements Cloneable {


    private DimensionVerifyStrategy strategy;




    List<PaintShape> groupedShaped = new ArrayList<>();




    public ShapeGroup(ShapeList shapes, ApplicationState applicationState, DimensionVerifyStrategy strategy) {
        super(null, null, applicationState);
        this.strategy = strategy;


// Get the list of selected shapes
        List<PaintShape> selectedShapes = new ArrayList<>();
        for (PaintShape shape : shapes.getAllShapes()) {
            System.out.println(shape);
            if (shape.isSelected()) {
                shape.setSelected(false);
                groupedShaped.add(shape);
                shapes.removeShape(shape);
                selectedShapes.add(shape);
            }
        }


        Point point = new Point(0,0);
//// Dimension dimension = new Dimension(0,0);
        Dimension dimension = new Dimension(0, 0, this.strategy);


// get the top left point of the group
        if (selectedShapes.size() > 0) {
            point = new Point(selectedShapes.get(0).getPoint().getX(), selectedShapes.get(0).getPoint().getY());
// dimension = new Dimension(selectedShapes.get(0).getDimension().getWidth(), selectedShapes.get(0).getDimension().getHeight());
//
        }
        for (PaintShape shape : selectedShapes) {
            if (shape.getPoint().getX() < point.getX()) {
                point.setX(shape.getPoint().getX());
            }
            if (shape.getPoint().getY() < point.getY()) {
                point.setY(shape.getPoint().getY());
            }
        }


// get the bottom right point of the group
        int maxX = 0;
        int maxY = 0;
        for (PaintShape shape : selectedShapes) {
            if (shape.getPoint().getX() + shape.getDimension().getWidth() > maxX) {
                maxX = shape.getPoint().getX() + shape.getDimension().getWidth();
            }
            if (shape.getPoint().getY() + shape.getDimension().getHeight() > maxY) {
                maxY = shape.getPoint().getY() + shape.getDimension().getHeight();
            }
        }
        dimension = new Dimension(maxX - point.getX(), maxY - point.getY());




        dimension.setWidth(maxX - point.getX());
        dimension.setHeight(maxY - point.getY());


        super.setPoint(point);
        super.setDimension(dimension);
        super.setShadingType(ShapeShadingType.OUTLINE);
        super.setSecondaryColor(ShapeColor.DARK_GRAY);
        super.setPrimaryColor(ShapeColor.DARK_GRAY);


        super.setSelected(true);


        super.shape = new Rectangle(point.getX(),point.getY(), dimension.getWidth(), dimension.getHeight());
        super.shapeBorderWhenSelected = new Rectangle(point.getX() - SELECT_PADDING,point.getY() - SELECT_PADDING, dimension.getWidth() + SELECT_PADDING * 2, dimension.getHeight() + SELECT_PADDING * 2);
    }


    public List<PaintShape> getGroupedShaped() {
        return groupedShaped;
    }


    public void setGroupedShaped(List<PaintShape> groupedShaped) {
        this.groupedShaped = groupedShaped;
    }


    @Override
    public void setSelected(boolean b) {
        super.setSelected(b);
    }


    @Override
    public void move(Point point) {
        System.out.println("moving group");
// Recalculate the pont based on the dimensions of the group
        super.shape = new Rectangle(point.getX(),point.getY(), super.getDimension().getWidth(), super.getDimension().getHeight());
        super.shapeBorderWhenSelected = new Rectangle(point.getX() - SELECT_PADDING,point.getY() - SELECT_PADDING, super.getDimension().getWidth() + SELECT_PADDING * 2, super.getDimension().getHeight() + SELECT_PADDING * 2);
        for (PaintShape shape : groupedShaped) {
            Point newPoint = new Point(point.getX() + (shape.getPoint().getX() - super.getPoint().getX()), point.getY() + (shape.getPoint().getY() - super.getPoint().getY()));
            shape.move(newPoint);
        }
        super.setPoint(point);
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            ShapeGroup shapeGroup = (ShapeGroup) super.clone();
            shapeGroup.setGroupedShaped(new ArrayList<>());
            for (PaintShape shape : groupedShaped) {
                shapeGroup.getGroupedShaped().add((PaintShape) shape.clone());
            }
            return shapeGroup;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }




    @Override
    public String toString() {
        return "ShapeGroup{" +
                "groupedShaped=" + groupedShaped +
                '}';
    }


}
