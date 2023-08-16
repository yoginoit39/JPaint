package model.commandpattern;

import model.ShapeList;
import model.strategypattern.PaintShape;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.compositePattern.ShapeGroup;

public class UngroupCommand implements ICommand, IUndoable {

    private ShapeList shapeList;
    private ShapeGroup group;
    public UngroupCommand(ShapeList shapeList, ShapeGroup group) {
        this.shapeList = shapeList;
        this.group = group;
    }



    @Override
    public void run() {
        shapeList.removeShape(group);
        for (PaintShape shape : group.getGroupedShaped()) {
            shapeList.addShape(shape);
        }
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        shapeList.addShape(group);
        for (PaintShape shape : group.getGroupedShaped()) {
            shapeList.removeShape(shape);
        }
    }

    @Override
    public void redo() {
        shapeList.removeShape(group);
        for (PaintShape shape : group.getGroupedShaped()) {
            shapeList.addShape(shape);
        }
    }

    @Override
    public void add() {
        shapeList.removeShape(group);
        for (PaintShape shape : group.getGroupedShaped()) {
            shapeList.addShape(shape);
        }
    }
}
