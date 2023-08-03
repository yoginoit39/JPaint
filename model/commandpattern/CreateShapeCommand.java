//package model.Commands;
//
//import model.ShapeList;
//import model.interfaces.ICommand;
//import model.interfaces.IUndoable;
//
//import java.awt.*;
//
//public class CreateShapeCommand implements ICommand, IUndoable {
//
//    private ShapeList shapeList;
//    private Shape shape;
//
//    public CreateShapeCommand(ShapeList shapeList, Shape shape) {
//        this.shapeList = shapeList;
//        this.shape = shape;
//    }
//
//
//
//    @Override
//    public void run() {
//        shapeList.addShape(shape);
//        CommandHistory.add(this);
//
//
//    }
//
//    public void add(){
//        shapeList.addShape(shape);
//    }
//
//    @Override
//    public void undo() {
//        shapeList.removeShape(shape);
//    }
//
//    @Override
//    public void redo() {
//        shapeList.addShape(shape);
//    }
//
//
//
//}
//
//
//


package model.commandpattern;

import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.strategypattern.PaintShape;

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
