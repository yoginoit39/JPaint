package model.commandpattern;

import model.singletonpattern.Clipboard;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.strategypattern.PaintShape;

import java.util.ArrayList;
import java.util.List;

public class CopyShapeCommand implements ICommand, IUndoable {
    private List<PaintShape> shapes = new ArrayList<>();
    private ShapeList shapeList;
    private Clipboard clipboard;

    public CopyShapeCommand(ShapeList shapeList, List<PaintShape> shape, Clipboard clipboard) throws CloneNotSupportedException {
        this.shapeList = shapeList;
        for (PaintShape s : shape) {
            PaintShape newShape = (PaintShape) s.clone();
            newShape.setSelected(false);
            newShape.setPrimaryColor(s.getPrimaryColor());
            newShape.setSecondaryColor(s.getSecondaryColor());
            newShape.setShadingType(s.getShadingType());
            shapes.add(newShape);
        }
        this.clipboard = clipboard;
    }

    @Override
    public void run() {
        this.clipboard.setShape(shapes);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        clipboard.clear();
    }

    @Override
    public void redo() {
        clipboard.setShape(shapes);
    }

    @Override
    public void add() {
        clipboard.setShape(shapes);
    }
}
