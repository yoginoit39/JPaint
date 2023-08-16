package model;






import model.commandpattern.CommandManager;
import model.commandpattern.CreateShapeCommand;
import model.commandpattern.MoveShapeCommand;
import model.nullobjectpattern.NullPoint;
import model.nullobjectpattern.PointInterface;
import model.strategypattern.*;
import model.MouseMode;
import model.nullobjectpattern.Point;
import model.ShapeList;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;






public class ClickHandler extends MouseAdapter {
    private PointInterface startPoint = new NullPoint();
    private PointInterface endPoint = new NullPoint();
    // private final Point startPoint = new Point();
// private final Point endPoint = new Point();
    private final PaintCanvas canvas; // reference to paintCanvas object
    private final ShapeList shapeList;


    private ApplicationState appState;




    public ClickHandler(PaintCanvas canvas, ShapeList shapeList, ApplicationState appState) {
        this.canvas = canvas;
        this.shapeList = shapeList;
        this.appState = appState;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = Point.createPoint(e.getX(), e.getY());
// startPoint.setX(e.getX());
// startPoint.setY(e.getY());
    }


    @Override
    public void mouseReleased(MouseEvent e) {


        endPoint = Point.createPoint(e.getX(), e.getY());
// endPoint.setX(e.getX());
// endPoint.setY(e.getY());


        int x = Math.min(startPoint.getX(), endPoint.getX());
        int y = Math.min(startPoint.getY(), endPoint.getY());
        int width = Math.abs(startPoint.getX() - endPoint.getX());
        int height = Math.abs(startPoint.getY() - endPoint.getY());




        Point point = new Point(x, y);


        DimensionVerifyStrategy strategy = new SimpleDimensionVerifyStrategy();
        Dimension dim = new Dimension(width, height, strategy);
// Point from = new Point(startPoint.getX(), startPoint.getY());


        int distance = (int) Math.sqrt(Math.pow((startPoint.getX() - endPoint.getX()), 2) +
                Math.pow((startPoint.getY() - endPoint.getY()), 2));




// check mouse mode
        if (appState.getActiveMouseMode() == MouseMode.DRAW && distance > 0) {
            PaintShape newShape;
            switch (appState.getActiveShapeType()) {
                case ELLIPSE:
                    drawEllipse(point, dim);
                    break;
                case RECTANGLE:
                    drawRectangle(point, dim);
                    break;
                case TRIANGLE:
                    drawTriangle(point, dim);
                    break;
            }
        } else if (appState.getActiveMouseMode() == MouseMode.SELECT) {
            selectShape();
        } else if (appState.getActiveMouseMode() == MouseMode.MOVE) {
            moveShape();
        }
    }


    void drawTriangle(Point point, Dimension dim) {
        TriangleShape shape = new TriangleShape(point, dim, appState);
        shapeList.addShape(shape);
        CommandManager.addCommand(new CreateShapeCommand(shapeList, shape));
        canvas.repaint();
    }


    void drawRectangle(Point point, Dimension dim) {
        RectShape shape = new RectShape(point, dim, appState);
        shapeList.addShape(shape);
        CommandManager.addCommand(new CreateShapeCommand(shapeList, shape));
        canvas.repaint();
    }


    void drawEllipse(Point point, Dimension dim) {
        EllipseShape shape = new EllipseShape(point, dim, appState);
        shapeList.addShape(shape);
        CommandManager.addCommand(new CreateShapeCommand(shapeList, shape));
        canvas.repaint();
    }


    void selectShape() {
        Point selectBoundTopLeft = new Point(
                Math.min(startPoint.getX(), endPoint.getX()),
                Math.min(startPoint.getY(), endPoint.getY())
        );
        Point selectBoundBottomRight = new Point(Math.max(startPoint.getX(), endPoint.getX()),
                Math.max(startPoint.getY(), endPoint.getY())
        );
        Point selectBoundTopRight = new Point(Math.max(startPoint.getX(), endPoint.getX()),
                Math.min(startPoint.getY(), endPoint.getY())
        );
        Point selectBoundBottomLeft = new Point(
                Math.min(startPoint.getX(), endPoint.getX()),
                Math.max(startPoint.getY(), endPoint.getY())
        );


        boolean selected = false;
        for (PaintShape shape : shapeList.getAllShapes()) {
            if(shape.getPoint().getX() < selectBoundTopRight.getX() &&
                    shape.getPoint().getX() + shape.getDimension().getWidth() > selectBoundTopLeft.getX() &&
                    shape.getPoint().getY() < selectBoundBottomLeft.getY() &&
                    shape.getPoint().getY() + shape.getDimension().getHeight() > selectBoundTopLeft.getY()) {
                shape.setSelected(true);
                selected = true;
                appState.setSelectedShape(shape);
                canvas.repaint();
            }


        }


        if(!selected) {
            for(PaintShape shape: shapeList.getAllShapes()) {
                if (shape.isSelected()) {
                    appState.setSelectedShape(null);
                    shape.setSelected(false);
                }
            }
            canvas.repaint();
        }
    }




    void moveShape() {
        int dx = endPoint.getX() - startPoint.getX();
        int dy = endPoint.getY() - startPoint.getY();


        for (PaintShape shape : shapeList.getAllShapes()) {
            if (shape.isSelected()) {
                int newX = shape.getPoint().getX() + dx;
                int newY = shape.getPoint().getY() + dy;
                Point newTo = new Point(newX, newY);
                MoveShapeCommand moveShapeCommand = new MoveShapeCommand(shape, newTo);
                CommandManager.addCommand(moveShapeCommand);
                moveShapeCommand.run();
            }
        }
        canvas.repaint();
    }
}






