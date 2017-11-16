package windowGUI.registrationOrEntry;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.MyStyle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AuthorizationWindow {
    private static final int SIZE_WIDTH = 350;
    private static final int SIZE_HEIGHT = 250;
    private static final String WINDOW_TITLE = "Авторизация";

    private static final MyStyle MY_STYLE = new MyStyle();

    private static final JFrame WINDOW = new JFrame();
    private static final JTabbedPane LIST_OF_TABS = new JTabbedPane();

    private static final EntryWindow ENTRY_WINDOW = new EntryWindow();
    private static final RegistrationWindow REGISTRATION_WINDOW = new RegistrationWindow();


    public AuthorizationWindow() {
        MY_STYLE.setStyle(getListComponents());

        WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new ConfigurationsWindowGUI().setConfigWindow(WINDOW, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);

        addTabs();

        WINDOW.add(LIST_OF_TABS, BorderLayout.CENTER);
        WINDOW.setVisible(true);
    }
    private static void addTabs(){
        LIST_OF_TABS.setVisible(true);

        LIST_OF_TABS.setPreferredSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
        LIST_OF_TABS.addTab(ENTRY_WINDOW.getTabTitle(), ENTRY_WINDOW.getPanelTabs());
        LIST_OF_TABS.addTab(REGISTRATION_WINDOW.getTabTitle(), REGISTRATION_WINDOW.getPanelTabs());
    }

    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(LIST_OF_TABS);
        return listComponent;
    }

    public static int getSizeWidth() {
        return SIZE_WIDTH;
    }

    public static int getSizeHeight() {
        return SIZE_HEIGHT;
    }
}
