package model.commandpattern;

import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.strategypattern.PaintShape;
import model.strategypattern.ShapeGroup;

public class GroupCommand implements ICommand, IUndoable {
    private ShapeList shapeList;
    private ShapeGroup group;

    public GroupCommand(ShapeList shapeList, ShapeGroup group) {
        this.shapeList = shapeList;
        this.group = group;
    }

    @Override
    public void run() {
        shapeList.addShape(group);
        System.out.println(group);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        shapeList.removeShape(group);
        for (PaintShape shape : group.getGroupedShaped()) {
            shapeList.addShape(shape);
        }
    }

    @Override
    public void redo() {
        shapeList.addShape(group);
        for (PaintShape shape : group.getGroupedShaped()) {
            shapeList.removeShape(shape);
        }
    }

    @Override
    public void add() {
        shapeList.addShape(group);
    }
}
