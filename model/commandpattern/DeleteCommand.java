package model.commandpattern;


import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand implements ICommand, IUndoable {
    private List<PaintShape> shapes = new ArrayList<>();
    private ShapeList shapeList;
    public DeleteCommand(List<PaintShape> payload, ShapeList shapeList) {
        this.shapes.addAll(payload);
        this.shapeList = shapeList;
    }

    @Override
    public void run() {
        for (PaintShape shape : shapes) {
            shapeList.removeShape(shape);
        }
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for (PaintShape shape : shapes) {
            shapeList.addShape(shape);
        }
    }

    @Override
    public void redo() {
        for (PaintShape shape : shapes) {
            shapeList.removeShape(shape);
        }
    }

    @Override
    public void add() {
        for (PaintShape shape : shapes) {
            shapeList.addShape(shape);
        }
    }
}
