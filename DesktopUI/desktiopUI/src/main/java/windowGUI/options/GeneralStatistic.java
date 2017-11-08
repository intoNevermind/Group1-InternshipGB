package windowGUI.options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GeneralStatistic extends Statistics{
    private static final String TAB_NAME = "Общая статистика";

    private static String nameSite;

    public GeneralStatistic() {
        setTabName(TAB_NAME);
        getOptionsPanel().setLayout(getGBL());

        fillOptionsPanel();
        getPanelStat().add(getOptionsPanel(), BorderLayout.NORTH);

        columnNames = new String[]{"Имя", "Количество новых страниц"};

        getListSite().addActionListener(this::initNameSites);
        getListSite().addActionListener(this::listenerRemoveDataTable);
        getBtnConfirm().addActionListener(this::listenerVisibleDataTable);
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadlineSite(),configGBC(getHeadlineSite(),false));
        getOptionsPanel().add(getHeadlineSite());
        getGBL().setConstraints(getListSite(),configGBC(getListSite(),false));
        getOptionsPanel().add(getListSite());
        getGBL().setConstraints(getBtnConfirm(),configGBC(getBtnConfirm(),true));
        getOptionsPanel().add(getBtnConfirm());
    }

    @Override
    public void initNameSites(ActionEvent actionEvent){
        JComboBox box = (JComboBox)actionEvent.getSource();
        nameSite = (String)box.getSelectedItem();
    }

    private void listenerVisibleDataTable(ActionEvent actionEvent){
        dataTable = new JTable(getPPersonPageRankT().getArrayFillGeneralTable(nameSite), columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);

        dataScrollPane.setVisible(true);
        getPanelStat().updateUI();
    }

    private void listenerRemoveDataTable(ActionEvent actionEvent){
        for (int i = 0; i < getPanelStat().getComponents().length; i++) {
            if(getPanelStat().getComponents()[i].equals(dataScrollPane)){
                getPanelStat().remove(dataScrollPane);
            }
        }
        getPanelStat().updateUI();
    }


}
