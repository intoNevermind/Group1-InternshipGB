package windowGUI;

import windowGUI.component.workStatistics.DailyStatistic;
import windowGUI.component.workStatistics.GeneralStatistic;
import windowGUI.component.workDirectory.ListOfTabsDirectory;
import windowGUI.registrationOrEntryWindow.AuthorizationWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
/*
 * Класс-окно, отвечающий за окно приложения
 * */
public class ApplicationWindow {
    private static final MyStyle MY_STYLE = new MyStyle();

    private static final String WINDOW_TITLE = "Статистика";

    private static final int SIZE_WIDTH = 800;
    private static final int SIZE_HEIGHT = 500;

    private static final JFrame WINDOW = new JFrame();

    private static final JTabbedPane LIST_OF_TABS = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);

    private static final JPanel RESTART_PANEL = new JPanel(new FlowLayout());

    private static final JLabel HEAD_LINE_RESTART = new JLabel();

    private static final JButton BTN_RESTART = new JButton("Сменить учетную запись");

    private static final DailyStatistic DAILY_STATISTIC = new DailyStatistic();
    private static final GeneralStatistic GENERAL_STATISTIC = new GeneralStatistic();
    private static final ListOfTabsDirectory LIST_OF_TABS_DIRECTORY = new ListOfTabsDirectory();

    public ApplicationWindow(String nameUser) {
        MY_STYLE.setStyle(getListComponents());

        WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new ConfigurationsWindowGUI().setConfigWindow(WINDOW, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);

        fillTabs();

        HEAD_LINE_RESTART.setText("Пользователь: " + nameUser + "   ");
        BTN_RESTART.addActionListener(this::openAuthorizationWindow);

        fillRestart();

        WINDOW.setVisible(true);
    }

    /*
     * метод, отвечающий за передачу всех элементов окна приложения для установки графического вида
     * */
    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(LIST_OF_TABS);
        listComponent.add(HEAD_LINE_RESTART);
        listComponent.add(BTN_RESTART);
        return listComponent;
    }

    /*
     * метод, заполняющий панель вкладок
     * */
    private static void fillTabs(){
        LIST_OF_TABS.setPreferredSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT ));
        LIST_OF_TABS.addTab(GENERAL_STATISTIC.getTabName(), GENERAL_STATISTIC.getPanelStat());
        LIST_OF_TABS.addTab(DAILY_STATISTIC.getTabName(), DAILY_STATISTIC.getPanelStat());
        LIST_OF_TABS.addTab(LIST_OF_TABS_DIRECTORY.getNameListOfTabs(), LIST_OF_TABS_DIRECTORY.getListOfTabs());

        WINDOW.add(LIST_OF_TABS, BorderLayout.CENTER);
    }

    /*
     * метод, заполняющий панель смены пользователя
     * */
    private void fillRestart(){
        RESTART_PANEL.add(HEAD_LINE_RESTART);
        RESTART_PANEL.add(BTN_RESTART);

        WINDOW.add(RESTART_PANEL, BorderLayout.NORTH);
    }

    /*
     * метод, открывающий окно авторизации
     * */
    private void openAuthorizationWindow(ActionEvent actionEvent){
        new AuthorizationWindow();
        WINDOW.dispose();
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
    /*
     * </getters>
     * */
}
