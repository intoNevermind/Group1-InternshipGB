package windowGUI.options;

import javax.swing.*;
import java.awt.*;

public class GeneralStatistic extends Statistics{

    private static final JLabel headlineComboBoxSite = new JLabel("Сайты: ");
    private static final JButton btnConfirm = new JButton("Подтвердить");
    private final JComboBox listSite;

    private static final String[] sitesList = new String[10];

    private static int countRows = 0;
    private static int countCols = 0;

    public GeneralStatistic() {
        setTabName("Общая статистика");

        fillList();
        listSite = new JComboBox(sitesList);

        fillOptionsPanel();
        optionsPanel.setLayout(new GridLayout(countRows,countCols));

        panelStat.add(optionsPanel, BorderLayout.NORTH);
    }

    @Override
    public void fillList() {
        for (int i = 0; i < 10; i++) {
            sitesList[i] = "Сайт " + (i + 1);
        }

    }

    @Override
    public void fillOptionsPanel() {
        optionsPanel.add(headlineComboBoxSite);
        countRows++;
        optionsPanel.add(listSite);
        countCols++;
        optionsPanel.add(btnConfirm);
        countCols++;
    }
}
