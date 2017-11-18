package windowGUI;

import javax.swing.*;
import java.awt.*;
/*
 * Класс-конфигурация, отвечающий за минимальную настройку окон(JFrame)
 * */
public class ConfigurationsWindowGUI {

    public void setConfigWindow(JFrame window, String windowTitle, int sizeWidth, int sizeHeight){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        window.setTitle(windowTitle);
        window.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        window.setResizable(false);
    }
}
