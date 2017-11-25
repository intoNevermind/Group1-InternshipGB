package windowGUI.component.workWithStatistics;

import windowGUI.component.ListSites;
import windowGUI.component.ChangeItemsJComboBox;
import windowGUI.component.workWithDB.processingData.ProcessingData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static java.awt.GridBagConstraints.*;
/*
 * Класс-статистика, отвечающий за функциональную деятельность Общей статистики
 * */
public class GeneralStatistic extends Statistics implements ListSites{
    private static final String NAME_TAB = "Общая статистика";
    private static final String[] NAME_COLUMNS = new String[]{"Имя", "Количество новых страниц"};

    private static String nameSite;

    public static final ArrayList<String> liLIST_ADD_NAME_SITES = new ArrayList<>();
    public static final ArrayList<String> LIST_DEL_NAME_SITES = new ArrayList<>();
    public static final ArrayList<String> LIST_BEFORE_NAME_SITES = new ArrayList<>();
    public static final ArrayList<String> LIST_AFTER_NAME_SITES = new ArrayList<>();

    private static final ChangeItemsJComboBox CHANGE_ITEMS_J_COMBO_BOX = new ChangeItemsJComboBox();

    public GeneralStatistic() {
        setTabName(NAME_TAB);

        addActionListenerForListSites();
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadlineSite(),getCGBL().configGBC(EAST,1,false));
        getOptionsPanel().add(getHeadlineSite());
        getGBL().setConstraints(getListSites(),getCGBL().configGBC(2,false));
        getOptionsPanel().add(getListSites());

        getGBL().setConstraints(getBtnConfirm(),getCGBL().configGBC(2,true));
        getOptionsPanel().add(getBtnConfirm());
        getGBL().setConstraints(getBtnRefresh(), getCGBL().configGBC(2,false));
        getOptionsPanel().add(getBtnRefresh());
    }

    @Override
    public void addActionListenerForListSites() {
        getListSites().addActionListener(this::initNameSites);
    }

    @Override
    public void initNameSites(ActionEvent actionEvent){
        JComboBox box = (JComboBox)actionEvent.getSource();
        nameSite = (String)box.getSelectedItem();
    }

    @Override
    public void initDataTable() {
        dataTable = new JTable(getPGeneralStatisticsT().getArrayFillTable(nameSite, NAME_COLUMNS.length), NAME_COLUMNS);
        super.initDataTable();
    }

    @Override
    public void visibleDataTable(ActionEvent actionEvent){
        if(nameSite == null || nameSite.equals(ProcessingData.getNotChosen())) {
            JOptionPane.showMessageDialog(null,
                    "Для просмотра общей статистики необходимо выбрать \""  + getHeadlineSite().getText() + "\" ",
                    getEmptyFields(),
                    JOptionPane.WARNING_MESSAGE);
        }
        refresh(actionEvent);
    }

    @Override
    public void refresh(ActionEvent actionEvent) {
        getWorkWithDataTable().removeDataTable(dataScrollPane, getPanelStat());
        initDataTable();

        CHANGE_ITEMS_J_COMBO_BOX.refreshList(liLIST_ADD_NAME_SITES, LIST_DEL_NAME_SITES, LIST_BEFORE_NAME_SITES, LIST_AFTER_NAME_SITES, getListSites());
    }
}
