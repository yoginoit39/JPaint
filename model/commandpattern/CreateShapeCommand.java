package model.commandpattern;

import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class CreateShapeCommand implements ICommand, IUndoable {

    private ShapeList shapeList;
    private PaintShape shape;

    public CreateShapeCommand(ShapeList shapeList, PaintShape shape) {
        this.shapeList = shapeList;
        this.shape = shape;
    }

    @Override
    public void run() {
        CommandHistory.add(this);
    }

    @Override
    public void add() {
        shapeList.addShape(shape);
    }

    @Override
    public void undo() {
        shapeList.removeShape(shape);
    }

    @Override
    public void redo() {
        shapeList.addShape(shape);
    }
}
