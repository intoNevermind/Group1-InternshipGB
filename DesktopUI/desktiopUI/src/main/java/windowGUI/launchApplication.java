package windowGUI;

import windowGUI.registrationOrEntryWindow.AuthorizationWindow;

import javax.swing.*;

public class launchApplication {

    public static void main(String[] args) {
        setStyleLookAndFeel();
//        new ApplicationWindow();
        new AuthorizationWindow();
    }

    private static void setStyleLookAndFeel(){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException  e) {
            e.printStackTrace();
        }
    }
}
