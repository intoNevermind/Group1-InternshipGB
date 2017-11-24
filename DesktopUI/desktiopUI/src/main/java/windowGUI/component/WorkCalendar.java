package windowGUI.component;

import java.beans.PropertyChangeEvent;

public interface WorkCalendar {
    void initStartDate(PropertyChangeEvent evt);
    void initFinishDate(PropertyChangeEvent evt);
    void addActionListenerForCalendars();
}
