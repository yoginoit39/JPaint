package model.ProxyPattern;


import model.ColorUtil;
import model.strategypattern.PaintShape;


import java.awt.*;


public class OutlineShapeProxy implements IShape {
    private IShape realShape;


    public OutlineShapeProxy(IShape realShape) {
        this.realShape = realShape;
    }


    @Override
    public void render(Graphics2D graphics2d) {
        realShape.render(graphics2d);


    }


    @Override
    public void move(Point point) {
        realShape.move(point);
    }
}
