package controller;


import model.commandpattern.*;
import model.persistence.ApplicationState;
import model.singletonpattern.Clipboard;
import model.ShapeList;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.strategypattern.PaintShape;
import model.strategypattern.ShapeGroup;
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

import java.util.ArrayList;
import java.util.List;


public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final ShapeList shapeList;
    private final CommandManager commandManager;

    private PaintCanvas paintCanvas;

    private Clipboard clipboard = Clipboard.getInstance();



    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeList shapeList, PaintCanvas paintCanvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.commandManager = new CommandManager();
        this.paintCanvas = paintCanvas;

        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, applicationState::setActiveShape);
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, applicationState::setActivePrimaryColor);
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, applicationState::setActiveSecondaryColor);
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, applicationState::setActiveShadingType);
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, this::changeMouseType);
        uiModule.addEvent(EventName.UNDO, this::undo);
        uiModule.addEvent(EventName.REDO, this::redo);
        uiModule.addEvent(EventName.COPY, this::copy);
        uiModule.addEvent(EventName.PASTE, this::paste);
        uiModule.addEvent(EventName.DELETE, this::delete);
        uiModule.addEvent(EventName.GROUP, this::group);
        uiModule.addEvent(EventName.UNGROUP, this::ungroup);
    }

    private void undo() {
        ICommand undoCommand = new UndoCommand();
        undoCommand.run();
            System.out.println("undo");
        paintCanvas.repaint();
    }

    private void redo() {
      ICommand redoCommand = new RedoCommand();
      redoCommand.run();
        System.out.println("redo");
        paintCanvas.repaint();
    }

    private void changeMouseType() {
        applicationState.setActiveStartAndEndPointMode();
        uiModule.changeCursor(applicationState.getActiveMouseMode());
    }

    private void copy() throws CloneNotSupportedException {
        List<PaintShape> selectedShapes = shapeList.getSelectedShapes();
        if (selectedShapes != null) {
            ICommand copyCommand = new CopyShapeCommand(shapeList, selectedShapes, clipboard);
            copyCommand.run();
            System.out.println("copy");
        }
    }

    private void paste() {
        ICommand pasteCommand = new PasteShapeCommand(clipboard, shapeList);
        pasteCommand.run();
        paintCanvas.repaint();
    }

    private void delete() {
        List<PaintShape> selectedShapes = shapeList.getSelectedShapes();
        if (selectedShapes.size() >= 1) {
            ICommand deleteCommand = new DeleteCommand(selectedShapes, shapeList);
            deleteCommand.run();
            System.out.println("delete");
            paintCanvas.repaint();
        }
    }

    private void group() {
        List<PaintShape> selectedShapes = new ArrayList<>();
        for (PaintShape shape : shapeList.getAllShapes()) {
            if (shape.isSelected()) {
                selectedShapes.add(shape);
            }
        }

        if (selectedShapes.size() > 1) {
            ShapeGroup group = new ShapeGroup(shapeList, (ApplicationState) applicationState);
            ICommand groupCommand = new GroupCommand(shapeList, group);
            groupCommand.run();
            paintCanvas.repaint();
        }
    }

    private void ungroup() {
        try {
            PaintShape selectedShape = applicationState.getSelectedShape();
            if (selectedShape instanceof ShapeGroup) {
                ICommand ungroupCommand = new UngroupCommand(shapeList, (ShapeGroup) selectedShape);
                ungroupCommand.run();
                System.out.println("ungroup");
                paintCanvas.repaint();
            }
        } catch (Exception e) {
            System.out.println("No group selected");
        }
    }
}
