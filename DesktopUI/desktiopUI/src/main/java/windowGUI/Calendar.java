package windowGUI;
import java.awt.EventQueue;
import com.toedter.calendar.JDateChooser;

public class Calendar extends JDateChooser{

        public Calendar() {
            setBounds(20, 20, 200, 20);
            setDateFormatString("dd MMMM yyyy, EEEE ");
            EventQueue.invokeLater(() ->{
                try {
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
}
