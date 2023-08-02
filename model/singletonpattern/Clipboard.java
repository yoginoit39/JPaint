package model.singletonpattern;

import model.commandpattern.PaintShape;

import java.util.ArrayList;
import java.util.List;

public class Clipboard {
    private static Clipboard instance = null;
    private List<PaintShape> shape = new ArrayList<>();


    public static Clipboard getInstance() {

        if (instance == null) {
            instance = new Clipboard();
        }
        return instance;
    }

    public void addShape(PaintShape shape) {
        this.shape.add(shape);
        System.out.println("Shape added to clipboard");
    }

    public void setShape(List<PaintShape> shape) {
        this.shape = shape;
        System.out.println("Shape set to clipboard");
    }

    public List<PaintShape> getShape() {
        System.out.println("Shape retrieved from clipboard");
        return shape;
    }

    public void clear() {
        shape = null;
    }
}
