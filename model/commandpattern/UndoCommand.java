package model.commandpattern;

import model.interfaces.ICommand;

public class UndoCommand implements ICommand {
    public void run() {
        CommandHistory.undo();
    }

}
