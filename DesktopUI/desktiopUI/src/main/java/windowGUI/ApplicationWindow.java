package windowGUI;

import windowGUI.component.worcStatistics.DailyStatistic;
import windowGUI.component.worcStatistics.GeneralStatistic;
import windowGUI.component.workDirectory.ListOfTabsDirectory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ApplicationWindow {

    private static final int SIZE_WIDTH = 600;
    private static final int SIZE_HEIGHT = 500;
    private static final String WINDOW_TITLE = "Выбор статистики";

    private static final MyStyle MY_STYLE = new MyStyle();

    private static final JFrame WINDOW = new JFrame();
    private static final JTabbedPane LIST_OF_TABS = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);


    private static final DailyStatistic DAILY_STATISTIC = new DailyStatistic();
    private static final GeneralStatistic GENERAL_STATISTIC = new GeneralStatistic();
    private static final ListOfTabsDirectory LIST_OF_TABS_DIRECTORY = new ListOfTabsDirectory();

    ApplicationWindow() {
        MY_STYLE.setStyle(getListComponents());

        WINDOW.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new ConfigurationsWindowGUI().setConfigWindow(WINDOW, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);

        addTabs();

        WINDOW.add(LIST_OF_TABS, BorderLayout.CENTER);
        WINDOW.setVisible(true);

    }
    private static void addTabs(){
        LIST_OF_TABS.setVisible(true);
        LIST_OF_TABS.setPreferredSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT ));
        LIST_OF_TABS.addTab(GENERAL_STATISTIC.getTabName(), GENERAL_STATISTIC.getPanelStat());
        LIST_OF_TABS.addTab(DAILY_STATISTIC.getTabName(), DAILY_STATISTIC.getPanelStat());
        LIST_OF_TABS.addTab(LIST_OF_TABS_DIRECTORY.getNameListOfTabs(), LIST_OF_TABS_DIRECTORY.getListOfTabs());
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
