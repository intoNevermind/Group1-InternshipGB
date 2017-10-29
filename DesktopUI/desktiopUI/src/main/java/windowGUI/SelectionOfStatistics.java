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
    private DailyStatistic dailyStatistics = new DailyStatistic();
    private GeneralStatistic generalStatistics = new GeneralStatistic();


    public SelectionOfStatistics()  {
        new ConfigurationsWindowGUI().setConfigWindow(window, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);
        showGeneralStatistic();
        showDailyStatistic();
        listStatistic.setLayout(new BorderLayout());
        listStatistic.add(listOfTabs);
        window.getContentPane().add(listStatistic);
    }

    public void showGeneralStatistic(){
        listOfTabs.addTab(generalStatistics.tabName, generalStatistics.panelStat);
    }

    public void showDailyStatistic(){
        listOfTabs.addTab(dailyStatistics.tabName, dailyStatistics.panelStat);
    }
}
