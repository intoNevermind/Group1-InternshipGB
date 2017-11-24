package windowGUI.component.workStatistics;

import windowGUI.component.ListPerson;
import windowGUI.component.ListSites;
import windowGUI.component.WorkCalendar;
import windowGUI.component.WorkWithItemsJComboBox;
import windowGUI.component.workDB.processingData.ProcessingData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static java.awt.GridBagConstraints.*;
/*
 * Класс-статистика, отвечающий за функциональную деятельность Ежедневной статистики
 * */
public class DailyStatistic extends Statistics implements ListSites, ListPerson, WorkCalendar{
    private static final String NAME_TAB = "Ежедневная статистика";
    private static final String[] NAME_COLUMNS = new String[]{"Дата", "Количество новых страниц"};;
    private static final String FORMAT = "yyyy-MM-dd";

    private static String nameSite;
    private static String namePerson;
    private static String startDate;
    private static String finishDate;

    private JScrollPane dataScrollPane;

    public static ArrayList<String> listAddNamePersons = new ArrayList<>();
    public static ArrayList<String> listDelNamePersons = new ArrayList<>();
    public static ArrayList<String> listBeforeNamePersons = new ArrayList<>();
    public static ArrayList<String> listAfterNamePersons = new ArrayList<>();

    public static ArrayList<String> listAddNameSites = new ArrayList<>();
    public static ArrayList<String> listDelNameSites = new ArrayList<>();
    public static ArrayList<String> listBeforeNameSites = new ArrayList<>();
    public static ArrayList<String> listAfterNameSites = new ArrayList<>();

    private static final WorkWithItemsJComboBox WORK_WITH_ITEMS_J_COMBO_BOX = new WorkWithItemsJComboBox();

    public DailyStatistic() {
        setTabName(NAME_TAB);

        addActionListenerForListSites();
        addActionListenerForListPerson();
        addActionListenerForCalendars();
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadlineSite(), getCGBL().configGBC(EAST,1,false));
        getOptionsPanel().add(getHeadlineSite());

        getGBL().setConstraints(getListSites() , getCGBL().configGBC(2,false));
        getOptionsPanel().add(getListSites());

        getGBL().setConstraints(getHeadlinePersons(), getCGBL().configGBC(EAST,1,true));
        getOptionsPanel().add(getHeadlinePersons());
        getGBL().setConstraints(getListPersons(), getCGBL().configGBC(2,false));
        getOptionsPanel().add(getListPersons());

        getGBL().setConstraints(getHeadlineStartPeriod(), getCGBL().configGBC(EAST,1,true));
        getOptionsPanel().add(getHeadlineStartPeriod());
        getGBL().setConstraints(getStartCalendar(), getCGBL().configGBC(2,false));
        getOptionsPanel().add(getStartCalendar());

        getGBL().setConstraints(getHeadlineFinishPeriod(), getCGBL().configGBC(EAST,1,true));
        getOptionsPanel().add(getHeadlineFinishPeriod());
        getGBL().setConstraints(getFinishCalendar(), getCGBL().configGBC(2,false));
        getOptionsPanel().add(getFinishCalendar());

        getGBL().setConstraints(getBtnConfirm(), getCGBL().configGBC(2,true));
        getOptionsPanel().add(getBtnConfirm());
        getGBL().setConstraints(getBtnRefresh(), getCGBL().configGBC(2,false));
        getOptionsPanel().add(getBtnRefresh());
    }

    @Override
    public void addActionListenerForListPerson() {
        getListPersons().addActionListener(this::initNamePerson);
    }

    @Override
    public void addActionListenerForListSites() {
        getListSites().addActionListener(this::initNameSites);
    }

    @Override
    public void addActionListenerForCalendars(){
        getStartCalendar().getDateEditor().addPropertyChangeListener("date",this::initStartDate);
        getFinishCalendar().getDateEditor().addPropertyChangeListener("date",this::initFinishDate);
    }

    @Override
    public void initNameSites(ActionEvent actionEvent){
        JComboBox box = (JComboBox)actionEvent.getSource();
        nameSite = (String)box.getSelectedItem();
    }

    @Override
    public void initNamePerson(ActionEvent actionEvent){
        JComboBox box = (JComboBox)actionEvent.getSource();
        namePerson = (String)box.getSelectedItem();
    }

    @Override
    public void initStartDate(PropertyChangeEvent evt){
        startDate = new SimpleDateFormat(FORMAT).format(getStartCalendar().getDate());
    }

    @Override
    public void initFinishDate(PropertyChangeEvent evt){
        finishDate = new SimpleDateFormat(FORMAT).format(getFinishCalendar().getDate());
    }

    @Override
    public void initDataTable() {
        JTable dataTable = new JTable(getPDailyStatisticsT().getArrayFillTable(nameSite, namePerson, startDate, finishDate, NAME_COLUMNS.length), NAME_COLUMNS);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void visibleDataTable(ActionEvent actionEvent){
        String str = "";
        if(nameSite == null || nameSite.equals(ProcessingData.getNotChosen())) str += " \"" + getHeadlineSite().getText() + "\" \n";
        if(namePerson == null || namePerson.equals(ProcessingData.getNotChosen())) str += " \"" + getHeadlinePersons().getText() + "\" \n";
        if(startDate == null) str += " \"" + getHeadlineStartPeriod().getText() + "\" \n";
        if(finishDate == null) str += " \"" + getHeadlineFinishPeriod().getText() + "\" \n";
        if(!str.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Для просмотра ежедневной статистики необходимо выбрать \n" + str,
                    getEmptyFields(),
                    JOptionPane.WARNING_MESSAGE);
        }
        refresh(actionEvent);
    }

    @Override
    public void refresh(ActionEvent actionEvent) {
        removeDataTable(dataScrollPane);
        initDataTable();

        WORK_WITH_ITEMS_J_COMBO_BOX.refreshList(listAddNameSites,listDelNameSites,listBeforeNameSites, listAfterNameSites, getListSites());
        WORK_WITH_ITEMS_J_COMBO_BOX.refreshList(listAddNamePersons,listDelNamePersons,listBeforeNamePersons, listAfterNamePersons, getListPersons());

        outTotalNumberPages();
    }

    /*
     * метод, показывающий количество страниц за период вообщем
     * */
    private void outTotalNumberPages(){
        getNumberPagesTotal().setText("Общее количество новых страниц за выбранный период: " +
                getPDailyStatisticsT().getNumberPagesTotal());
        getPanelStat().add(getNumberPagesTotal(), BorderLayout.SOUTH);
        getNumberPagesTotal().setVisible(true);
        getPanelStat().updateUI();
    }
}
