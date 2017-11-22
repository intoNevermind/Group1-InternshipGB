package windowGUI.component.workStatistics;

import windowGUI.component.workDB.processingData.ProcessingData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.GridBagConstraints.*;
/*
 * Класс-статистика, отвечающий за функциональную деятельность Общей статистики
 * */
public class GeneralStatistic extends Statistics{
    private static final String NAME_TAB = "Общая статистика";

    private static String nameSite;
    private JScrollPane dataScrollPane;
    private String[] namesColumn;

    public GeneralStatistic() {
        setTabName(NAME_TAB);
        addActionListenerForListSite();
        namesColumn = new String[]{"Имя", "Количество новых страниц"};
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadlineSite(),getCGBL().configGBC(EAST,1,false));
        getOptionsPanel().add(getHeadlineSite());
        getGBL().setConstraints(getListSite(),getCGBL().configGBC(2,false));
        getOptionsPanel().add(getListSite());

        getGBL().setConstraints(getBtnConfirm(),getCGBL().configGBC(2,true));
        getOptionsPanel().add(getBtnConfirm());
        getGBL().setConstraints(getBtnRefresh(), getCGBL().configGBC(2,false));
        getOptionsPanel().add(getBtnRefresh());
    }

    @Override
    public void initNameSites(ActionEvent actionEvent){
        JComboBox box = (JComboBox)actionEvent.getSource();
        nameSite = (String)box.getSelectedItem();
    }

    @Override
    public void initDataTable() {
        JTable dataTable = new JTable(getPGeneralStatisticsT().getArrayFillTable(nameSite, namesColumn.length), namesColumn);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);
        getPanelStat().updateUI();
    }

    @Override
    public void visibleDataTable(ActionEvent actionEvent){
        if(nameSite == null || nameSite.equals(ProcessingData.getNotChosen())) {
            JOptionPane.showMessageDialog(null,
                    "Для просмотра общей статистики необходимо выбрать \""  + getHeadlineSite().getText() + "\" ",
                    getEmptyFields(),
                    JOptionPane.WARNING_MESSAGE);
        }
        refreshDataTable(actionEvent);
    }

    @Override
    public void refreshDataTable(ActionEvent actionEvent) {
        removeDataTable(dataScrollPane);
        initDataTable();
    }
}
