package model.commandpattern;

import model.interfaces.IUndoable;
public class CommandManager {
    private static final CommandHistory commandHistory = new CommandHistory();


    public static boolean addCommand(IUndoable command) {
        commandHistory.add(command);
        return true;

    }

    public static boolean undo() {
        commandHistory.undo();
        return true;
    }

    public static void redo() {
        commandHistory.redo();
    }
}

