package view.interfaces;

import model.MouseMode;
import view.EventName;

import javax.swing.*;

public interface IGuiWindow {
    JButton getButton(EventName eventName);
    void setCursor(MouseMode mouseMode);
}
