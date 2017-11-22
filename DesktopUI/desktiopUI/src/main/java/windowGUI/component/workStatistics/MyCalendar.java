package windowGUI.component.workStatistics;

import java.awt.*;
import com.toedter.calendar.JDateChooser;
/*
 * Класс-календарь, отвечающий за функциональную деятельность календаря
 * */
public class MyCalendar extends JDateChooser{
    private static final int X = 20;
    private static final int Y = 20;
    private static final int SIZE_WIDTH = 200;
    private static final int SIZE_HEIGHT = 200;
    private static final String FORMAT = "yyyy-MM-dd";

        MyCalendar() {
            setBounds(X, Y, SIZE_WIDTH, SIZE_HEIGHT);
            setDateFormatString(FORMAT);

            EventQueue.invokeLater(() ->{
                try {
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
}
