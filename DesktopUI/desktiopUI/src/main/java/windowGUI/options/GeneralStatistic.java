package windowGUI.options;

import windowGUI.options.workSQL.SQLAnfrage;
import windowGUI.options.workSQL.SitesTable;

import javax.swing.*;
import java.awt.*;

public class GeneralStatistic extends Statistics{
    private static final String TAB_NAME = "Общая статистика";

    private static final GridBagLayout GBL = new GridBagLayout();

    private static final JLabel headlineComboBoxSite = new JLabel("Сайты: ");

    private static final JButton btnConfirm = new JButton("Подтвердить");

    private static final SQLAnfrage anfrSiteName = new SQLAnfrage(SitesTable.getTableName());
    private final JComboBox<Object> listSite = new JComboBox<>(anfrSiteName.fillList());

    public GeneralStatistic() {
        setTabName(TAB_NAME);
        getOptionsPanel().setLayout(GBL);

        fillOptionsPanel();
        getPanelStat().add(getOptionsPanel(), BorderLayout.NORTH);

        data = new String[][]{{"Путин", "1.00.500"}, {"Медведев", "50.000"}, {"Навальный", "50.000"}};
        columnNames = new String[]{"Дата", "Количество новых страниц"};
        dataTable = new JTable(data,columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void fillOptionsPanel() {
        GBL.setConstraints(headlineComboBoxSite,configGBC(headlineComboBoxSite,false));
        getOptionsPanel().add(headlineComboBoxSite);
        GBL.setConstraints(listSite,configGBC(listSite,false));
        getOptionsPanel().add(listSite);
        GBL.setConstraints(btnConfirm,configGBC(btnConfirm,true));
        getOptionsPanel().add(btnConfirm);
    }
}
