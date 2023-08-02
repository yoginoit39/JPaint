package model.commandpattern;

import model.nullobjectpattern.Point;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class MoveShapeCommand implements ICommand, IUndoable {

    private PaintShape shape;

    private Point to;
    private Point from = new Point(0, 0);


    public MoveShapeCommand(PaintShape shape, Point to) {
        from = shape.getPoint();
        this.shape = shape;
        this.to = to;
    }

    @Override
    public void run() {
        shape.move(to);
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        System.out.println("Undo move shape");
        shape.move(from);
    }

    @Override
    public void redo() {
        System.out.println("Redo move shape");
        shape.move(to);
    }

    @Override
    public void add() {
        System.out.println("Add move shape");
        shape.move(to);
    }
}
