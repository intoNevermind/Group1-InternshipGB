package windowGUI.component;

import java.beans.PropertyChangeEvent;

public interface WorkWithCalendar {
    void initStartDate(PropertyChangeEvent evt);
    void initFinishDate(PropertyChangeEvent evt);
    void addActionListenerForCalendars();
}
