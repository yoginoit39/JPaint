package model.compositePattern;

import model.nullobjectpattern.Point;
import model.strategypattern.IShapeAction;

public interface ShapeComponent extends IShapeAction {
    void move(Point point);

}
