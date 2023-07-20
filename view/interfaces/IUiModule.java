package view.interfaces;

import model.MouseMode;
import view.EventName;

public interface IUiModule {
    void addEvent(EventName eventName, IEventCallback command);
    <T> T getDialogResponse(IDialogChoice dialogChoice);

    void changeCursor(MouseMode mouseMode);
}
