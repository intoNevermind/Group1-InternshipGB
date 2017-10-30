package windowGUI;

import windowGUI.options.DailyStatistic;
import windowGUI.options.GeneralStatistic;

import javax.swing.*;
import java.awt.*;



public class SelectionOfStatistics  {

    private static final int SIZE_WIDTH = 600;
    private static final int SIZE_HEIGHT = 400;
    private static final String WINDOW_TITLE = "Выбор статистики";
    private static final JFrame window = new JFrame();
    private static final JPanel listStatistic = new JPanel();
    private static final JTabbedPane listOfTabs = new JTabbedPane();

    private static final DailyStatistic DAILY_STATISTIC = new DailyStatistic();
    private static final GeneralStatistic GENERAL_STATISTIC = new GeneralStatistic();


    public SelectionOfStatistics()  {
        new ConfigurationsWindowGUI().setConfigWindow(window, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);
        showGeneralStatistic();
        showDailyStatistic();
        listStatistic.setLayout(new BorderLayout());
        listStatistic.add(listOfTabs);
        window.add(listStatistic);
    }

    public void showGeneralStatistic(){
        listOfTabs.addTab(GENERAL_STATISTIC.tabName, GENERAL_STATISTIC.panelStat);
    }

    public void showDailyStatistic(){
        listOfTabs.addTab(DAILY_STATISTIC.tabName, DAILY_STATISTIC.panelStat);
    }
}
