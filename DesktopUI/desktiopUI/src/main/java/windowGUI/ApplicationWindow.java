package windowGUI;

import windowGUI.component.worcStatistics.DailyStatistic;
import windowGUI.component.worcStatistics.GeneralStatistic;
import windowGUI.component.workDirectory.ListOfTabsDirectory;

import javax.swing.*;
import java.awt.*;

class ApplicationWindow {

    private static final int SIZE_WIDTH = 600;
    private static final int SIZE_HEIGHT = 400;
    private static final String WINDOW_TITLE = "Выбор статистики";
    private static final JFrame WINDOW = new JFrame();
    private static final JPanel LIST_STATISTIC = new JPanel();
    private static final JTabbedPane LIST_OF_TABS = new JTabbedPane();


    private static final DailyStatistic DAILY_STATISTIC = new DailyStatistic();
    private static final GeneralStatistic GENERAL_STATISTIC = new GeneralStatistic();
    private static final ListOfTabsDirectory LIST_OF_TABS_DIRECTORY = new ListOfTabsDirectory();

    ApplicationWindow() {
        new ConfigurationsWindowGUI().setConfigWindow(WINDOW, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);

        addTabs();

        LIST_STATISTIC.setLayout(new BorderLayout());
        LIST_STATISTIC.add(LIST_OF_TABS,BorderLayout.CENTER);
        WINDOW.add(LIST_STATISTIC, BorderLayout.NORTH);

    }
    private static void addTabs(){
        LIST_OF_TABS.setVisible(true);
        LIST_OF_TABS.addTab(LIST_OF_TABS_DIRECTORY.getNameListOfTabs(), LIST_OF_TABS_DIRECTORY.getListOfTabs());
        LIST_OF_TABS.addTab(GENERAL_STATISTIC.getTabName(), GENERAL_STATISTIC.getPanelStat());
        LIST_OF_TABS.addTab(DAILY_STATISTIC.getTabName(), DAILY_STATISTIC.getPanelStat());
    }

}
