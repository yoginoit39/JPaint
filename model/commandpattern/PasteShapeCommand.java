package model.commandpattern;

import model.nullobjectpattern.Point;
import model.singletonpattern.Clipboard;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.strategypattern.Dimension;

import java.util.List;

public class PasteShapeCommand implements ICommand, IUndoable {
    private Clipboard clipboard;
    private List<PaintShape> shape;
    private ShapeList shapeList;

    public PasteShapeCommand(Clipboard clipboard, ShapeList shapeList) {
        this.clipboard = clipboard;
        this.shape = clipboard.getShape();
        this.shapeList = shapeList;

    }


    @Override
    public void run() {

        int nextX = 0;
        for (PaintShape s : shape) {
            Point newPoint = null;
            Dimension d = s.getDimension();
            if(nextX == 0 ) {
                newPoint = new Point(50, s.getPoint().getY());
            } else {
                newPoint = new Point(nextX, s.getPoint().getY());
            }

            nextX += d.getWidth();
            s.move(newPoint);
            shapeList.addShape(s);
            System.out.println("Shape pasted "+s);
        }
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for(PaintShape s : shape) {
            shapeList.removeShape(s);
        }
    }

    @Override
    public void redo() {
        for(PaintShape s : shape) {
            shapeList.addShape(s);
        }
    }

    @Override
    public void add() {
        for(PaintShape s : shape) {
            shapeList.addShape(s);
        }
    }
}

