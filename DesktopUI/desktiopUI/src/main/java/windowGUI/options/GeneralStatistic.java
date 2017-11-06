package windowGUI.options;

import windowGUI.options.workSQL.ProcessingPersonPageRankTable;
import windowGUI.options.workSQL.ProcessingSitesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GeneralStatistic extends Statistics{
    private static final String TAB_NAME = "Общая статистика";

    private static final GridBagLayout GBL = new GridBagLayout();

    private static final JLabel headlineComboBoxSite = new JLabel("Сайты: ");

    private final JButton btnConfirm = new JButton("Подтвердить");

    private static final ProcessingSitesTable PST = new ProcessingSitesTable();
    private static final JComboBox listSite = new JComboBox<>(PST.getColumnName());

    private static final ProcessingPersonPageRankTable PPersonPageRankT = new ProcessingPersonPageRankTable();

    private static String item;
    public GeneralStatistic() {

        setTabName(TAB_NAME);
        getOptionsPanel().setLayout(GBL);

        fillOptionsPanel();
        getPanelStat().add(getOptionsPanel(), BorderLayout.NORTH);

        columnNames = new String[]{"Имя", "Количество новых страниц"};

        listSite.addActionListener(this::getListenerFillDataTable);
        listSite.addActionListener(this::getListenerRemoveDataTable);
        btnConfirm.addActionListener(this::getListenerVisibleDataTable);
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

    private void getListenerVisibleDataTable(ActionEvent actionEvent){
        dataTable = new JTable(PPersonPageRankT.fillGeneralTable(item), columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        dataScrollPane.setVisible(false);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);
        if(dataScrollPane != null){
            dataScrollPane.setVisible(true);
        }
        getPanelStat().updateUI();
    }

    private void getListenerRemoveDataTable(ActionEvent actionEvent){
        for (int i = 0; i < getPanelStat().getComponents().length; i++) {
            if(getPanelStat().getComponents()[i].equals(dataScrollPane)){
                getPanelStat().remove(dataScrollPane);
            }
        }
        getPanelStat().updateUI();
    }

    private void getListenerFillDataTable(ActionEvent actionEvent){
        JComboBox box = (JComboBox)actionEvent.getSource();
        item = (String)box.getSelectedItem();
    }
}
