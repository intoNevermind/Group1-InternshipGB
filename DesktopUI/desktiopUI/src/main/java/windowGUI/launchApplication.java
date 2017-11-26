package windowGUI;

import windowGUI.registrationOrEntryWindow.AuthorizationWindow;

import javax.swing.*;
/*
 * Класс запускающий приложение
 * */
public class launchApplication {

    public static void main(String[] args) {
        setStyleLookAndFeel();
        new AuthorizationWindow();
    }

    /*
     * метод, отвечающий за внешний вид приложения
     * */
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
