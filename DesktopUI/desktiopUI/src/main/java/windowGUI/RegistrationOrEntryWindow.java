package windowGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RegistrationOrEntryWindow {
    private static final int SIZE_WIDTH = 400;
    private static final int SIZE_HEIGHT = 400;
    private static final String WINDOW_TITLE = "Выбор статистики";

    private static final MyStyle MY_STYLE = new MyStyle();

    private static final JFrame WINDOW = new JFrame();
    private static final JPanel REGISTRATION_OR_ENTRY = new JPanel();
    private static final JTabbedPane LIST_OF_TABS = new JTabbedPane();


    RegistrationOrEntryWindow() {
        MY_STYLE.setStyle(getListComponents());

        WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new ConfigurationsWindowGUI().setConfigWindow(WINDOW, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);

        addTabs();
        REGISTRATION_OR_ENTRY.setLayout(new BorderLayout());
        REGISTRATION_OR_ENTRY.add(LIST_OF_TABS,BorderLayout.CENTER);
        WINDOW.add(REGISTRATION_OR_ENTRY, BorderLayout.NORTH);
        WINDOW.setVisible(true);
    }
    private static void addTabs(){
        LIST_OF_TABS.setVisible(true);

        LIST_OF_TABS.addTab("Вход", new JPanel());
        LIST_OF_TABS.addTab("Регистрация", new JPanel());
    }

    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(LIST_OF_TABS);
        return listComponent;
    }
}
