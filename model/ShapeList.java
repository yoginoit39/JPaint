package model;


import model.strategypattern.PaintShape;

import java.util.ArrayList;
import java.util.List;

public class ShapeList {
    private final List<PaintShape> shapes;


    public ShapeList() {
        shapes = new ArrayList<>();
      
    }

    public void addShape(PaintShape shape) {
        shapes.add(shape);
        System.out.println("ShapeList: " + shapes.size());

    }

    public void removeShape(PaintShape shape) {
        shapes.remove(shape);

    }



    public List<PaintShape> getAllShapes() {
        return new ArrayList<>(shapes);
    }


    public List<PaintShape> getSelectedShapes() {
        List<PaintShape> selectedShapes = new ArrayList<>();
        for (PaintShape shape : shapes) {
            if (shape.isSelected()) {
                selectedShapes.add(shape);
            }
        }
        return selectedShapes;
    }


}


