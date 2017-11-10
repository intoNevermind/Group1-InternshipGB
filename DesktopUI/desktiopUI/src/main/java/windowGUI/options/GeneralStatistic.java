package windowGUI.options;

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

    @Override
    public void listenerVisibleDataTable(ActionEvent actionEvent){
        if(nameSite == null) JOptionPane.showMessageDialog(null, "Для просмотра общей статистики необходимо выбрать \""  + getHeadlineSite().getText() + "\" ");

        dataTable = new JTable(getPPersonPageRankT().getArrayFillGeneralTable(nameSite, columnNames.length), columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);
        dataScrollPane.setVisible(true);
        getPanelStat().updateUI();
    }
}
