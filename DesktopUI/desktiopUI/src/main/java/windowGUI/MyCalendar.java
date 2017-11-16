package windowGUI;

import java.awt.*;
import java.util.ArrayList;

import com.toedter.calendar.JDateChooser;

public class MyCalendar extends JDateChooser{
//    private static final MyStyle MY_STYLE = new MyStyle();

        public MyCalendar() {
//            MY_STYLE.setStyle(getListComponents());
            setBounds(20, 20, 200, 20);
            setDateFormatString("yyyy-MM-dd- HH:mm:ss");

            EventQueue.invokeLater(() ->{
                try {
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

//    private ArrayList<Component> getListComponents(){
//        ArrayList<Component> listComponent = new ArrayList<>();
//        listComponent.add(this);
//        return listComponent;
//    }
}
