package windowGUI;

import windowGUI.options.DailyStatistic;
import windowGUI.options.GeneralStatistic;
import windowGUI.options.workSQL.PersonTable;

import javax.swing.*;
import java.awt.*;

class SelectionOfStatistics  {

    private static final int SIZE_WIDTH = 600;
    private static final int SIZE_HEIGHT = 400;
    private static final String WINDOW_TITLE = "Выбор статистики";
    private static final JFrame window = new JFrame();
    private static final JPanel listStatistic = new JPanel();
    private static final JTabbedPane listOfTabs = new JTabbedPane();

    private static final DailyStatistic DAILY_STATISTIC = new DailyStatistic();
    private static final GeneralStatistic GENERAL_STATISTIC = new GeneralStatistic();

    SelectionOfStatistics() {
        new ConfigurationsWindowGUI().setConfigWindow(window, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);
        showGeneralStatistic();
        showDailyStatistic();
        listStatistic.setLayout(new BorderLayout());
        listStatistic.add(listOfTabs);
        window.add(listStatistic);
    }

    private static void showGeneralStatistic(){
        listOfTabs.addTab(GENERAL_STATISTIC.getTabName(), GENERAL_STATISTIC.getPanelStat());
    }

    private static void showDailyStatistic(){
        listOfTabs.addTab(DAILY_STATISTIC.getTabName(), DAILY_STATISTIC.getPanelStat());
    }
}
