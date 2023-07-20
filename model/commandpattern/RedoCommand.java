package model.commandpattern;


import model.interfaces.ICommand;

public class RedoCommand implements ICommand {
    public void run() {
        CommandHistory.redo();
    }
}
