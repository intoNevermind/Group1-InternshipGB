package windowGUI.registrationOrEntryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.MyStyle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/*
 * Класс-окно, отвечающий за окно авторизации(входа в приложение)
 * */
public class AuthorizationWindow {
    private static final MyStyle MY_STYLE = new MyStyle();

    private static final String WINDOW_TITLE = "Авторизация";

    private static final int SIZE_WIDTH = 350;
    private static final int SIZE_HEIGHT = 250;

    private static final JFrame WINDOW = new JFrame();
    private static final JTabbedPane LIST_OF_TABS = new JTabbedPane();

    private static final TabEntry ENTRY_WINDOW = new TabEntry();
    private static final TabRegistration REGISTRATION_WINDOW = new TabRegistration();

    public AuthorizationWindow() {
        MY_STYLE.setStyle(getListComponents());

        WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new ConfigurationsWindowGUI().setConfigWindow(WINDOW, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);

        fillTabs();

        WINDOW.add(LIST_OF_TABS, BorderLayout.CENTER);
        WINDOW.setVisible(true);
    }

    /*
     * метод, отвечающий за передачу всех элементов окна авторизации для установки графического вида
     * */
    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(LIST_OF_TABS);

        return listComponent;
    }

    /*
     * метод, заполняющий панель вкладок
     * */
    private static void fillTabs(){
        LIST_OF_TABS.setVisible(true);

        LIST_OF_TABS.setPreferredSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
        LIST_OF_TABS.addTab(ENTRY_WINDOW.getTabTitle(), ENTRY_WINDOW.getPanelTabs());
        LIST_OF_TABS.addTab(REGISTRATION_WINDOW.getTabTitle(), REGISTRATION_WINDOW.getPanelTabs());
    }

    /*
     * <getters>
     * */
    public static int getSizeWidth() {
        return SIZE_WIDTH;
    }
    public static int getSizeHeight() {
        return SIZE_HEIGHT;
    }

    static JFrame getWINDOW() {
        return WINDOW;
    }
    /*
     * </getters>
     * */
}
