package windowGUI.component;

import java.beans.PropertyChangeEvent;
/*
 * Интерфейся для работы с календарем
 * */
public interface WorkWithCalendar {
    void initStartDate(PropertyChangeEvent evt);
    void initFinishDate(PropertyChangeEvent evt);
    void addActionListenerForCalendars();
}
