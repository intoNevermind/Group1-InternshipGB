package windowGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SelectionOfStatistics  {

    private static final int SIZE_WIDTH = 600;
    private static final int SIZE_HEIGHT = 400;
    private static final String WINDOW_TITLE = "Выбор статистики";
    private static final JFrame window = new JFrame();
    private static final JPanel listStatistic = new JPanel();
    private static final JButton generalStat = new JButton("Общая статистика");
    private static final JButton dailyStat = new JButton("Ежедневная статистика");

    public SelectionOfStatistics()  {
        new ConfigurationsWindowGUI().setConfigWindow(window, WINDOW_TITLE,SIZE_WIDTH, SIZE_HEIGHT);
        generalStat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showGeneralStatistic();
            }
        });
        listStatistic.setLayout(new GridLayout(2,1));
        listStatistic.add(generalStat);
        listStatistic.add(dailyStat);
        window.add(listStatistic, BorderLayout.WEST);

    }

    public void showGeneralStatistic(){
        listStatistic.setVisible(false);
    }
}
