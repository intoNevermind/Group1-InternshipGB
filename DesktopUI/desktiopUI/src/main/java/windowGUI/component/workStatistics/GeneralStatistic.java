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

    public GeneralStatistic() {
        setTabName(NAME_TAB);

        columnNames = new String[]{"Имя", "Количество новых страниц"};
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadlineSite(),getCGBL().configGBCTest(EAST,1,false));
        getOptionsPanel().add(getHeadlineSite());
        getGBL().setConstraints(getListSite(),getCGBL().configGBCTest(2,false));
        getOptionsPanel().add(getListSite());

        getGBL().setConstraints(getBtnConfirm(),getCGBL().configGBCTest(REMAINDER,true));
        getOptionsPanel().add(getBtnConfirm());
    }

    @Override
    public void initNameSites(ActionEvent actionEvent){
        JComboBox box = (JComboBox)actionEvent.getSource();
        nameSite = (String)box.getSelectedItem();
    }

    @Override
    public void visibleDataTable(ActionEvent actionEvent){
        if(nameSite == null || nameSite.equals(ProcessingData.getNotChosen())) {
            JOptionPane.showMessageDialog(null,
                    "Для просмотра общей статистики необходимо выбрать \""  + getHeadlineSite().getText() + "\" ",
                    getEmptyFields(),
                    JOptionPane.WARNING_MESSAGE);
        }
        dataTable = new JTable(getPPersonPageRankT().getArrayFillTable(nameSite, columnNames.length), columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);
        dataScrollPane.setVisible(true);
        getPanelStat().updateUI();
    }
}
