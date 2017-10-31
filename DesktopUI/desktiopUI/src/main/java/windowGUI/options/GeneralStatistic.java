package windowGUI.options;

import javax.swing.*;
import java.awt.*;

public class GeneralStatistic extends Statistics{
    private static final GridBagLayout GBL = new GridBagLayout();

    private static final JLabel headlineComboBoxSite = new JLabel("Сайты: ");
    private static final JButton btnConfirm = new JButton("Подтвердить");
    private final JComboBox listSite;

    private static final String[] sitesList = new String[10];

    public GeneralStatistic() {
        setTabName("Общая статистика");
        optionsPanel.setLayout(GBL);

        fillList();
        listSite = new JComboBox(sitesList);

        fillOptionsPanel();
        panelStat.add(optionsPanel, BorderLayout.NORTH);

        data = new String[][]{{"Путин", "1.00.500"}, {"Медведев", "50.000"}, {"Навальный", "50.000"}};
        columnNames = new String[]{"Дата", "Количество новых страниц"};
        dataTable = new JTable(data,columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        panelStat.add(dataScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void fillList() {
        for (int i = 0; i < 10; i++) {
            sitesList[i] = "Сайт " + (i + 1);
        }
    }

    @Override
    public void fillOptionsPanel() {
        GBL.setConstraints(headlineComboBoxSite,configGBC(headlineComboBoxSite,false));
        optionsPanel.add(headlineComboBoxSite);
        GBL.setConstraints(listSite,configGBC(listSite,false));
        optionsPanel.add(listSite);
        GBL.setConstraints(btnConfirm,configGBC(btnConfirm,true));
        optionsPanel.add(btnConfirm);
    }
}
