package windowGUI.component.worcStatistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GeneralStatistic extends Statistics{
    private static final String TAB_NAME = "Общая статистика";
    private static String nameSite;

    public GeneralStatistic() {
        setTabName(TAB_NAME);

        fillOptionsPanel();

        addActionListenerForListSite();
        addActionListenerForBtnConfirm();

        columnNames = new String[]{"Имя", "Количество новых страниц"};
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadlineSite(),getCGBL().configGBC(getHeadlineSite(),false));
        getOptionsPanel().add(getHeadlineSite());
        getGBL().setConstraints(getListSite(),getCGBL().configGBC(getListSite(),false));
        getOptionsPanel().add(getListSite());
        getGBL().setConstraints(getBtnConfirm(),getCGBL().configGBC(getBtnConfirm(),true));
        getOptionsPanel().add(getBtnConfirm());
    }

    @Override
    public void initNameSites(ActionEvent actionEvent){
        JComboBox box = (JComboBox)actionEvent.getSource();
        nameSite = (String)box.getSelectedItem();
    }

    @Override
    public void visibleDataTable(ActionEvent actionEvent){
        if(nameSite == null || nameSite.equals("Не выбранно")) {
            JOptionPane.showMessageDialog(null, "Для просмотра общей статистики необходимо выбрать \""  + getHeadlineSite().getText() + "\" ");
        }

        dataTable = new JTable(getPPersonPageRankT().getArrayFillTable(nameSite, columnNames.length), columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);
        dataScrollPane.setVisible(true);
        getPanelStat().updateUI();
    }
}
