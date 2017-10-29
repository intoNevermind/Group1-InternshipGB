package windowGUI;

import javax.swing.*;

public class launchApplication {
    public static void main(String[] args) {
        setStyleLookAndFeel();
        new SelectionOfStatistics();
    }

    private static void setStyleLookAndFeel(){
        try {
            UIManager.setLookAndFeel(MyLookAndFeel.class.getCanonicalName());
        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
