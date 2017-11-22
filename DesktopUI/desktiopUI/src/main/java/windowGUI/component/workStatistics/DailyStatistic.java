package windowGUI.component.workStatistics;

import windowGUI.component.workDB.processingData.ProcessingData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import static java.awt.GridBagConstraints.*;
/*
 * Класс-статистика, отвечающий за функциональную деятельность Ежедневной статистики
 * */
public class DailyStatistic extends Statistics{
    private static final String NAME_TAB = "Ежедневная статистика";
    private static final String FORMAT = "yyyy-MM-dd";

    private static String nameSite;
    private static String namePerson;
    private static String startDate;
    private static String finishDate;

    private JScrollPane dataScrollPane;
    private String[] namesColumn;

    public DailyStatistic() {
        setTabName(NAME_TAB);

        addActionListenerForListSite();
        addActionListenerForListPerson();
        addActionListenerForCalendars();

        namesColumn = new String[]{"Дата", "Количество новых страниц"};
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadlineSite(), getCGBL().configGBC(EAST,1,false));
        getOptionsPanel().add(getHeadlineSite());
        getGBL().setConstraints(getListSite(), getCGBL().configGBC(2,false));
        getOptionsPanel().add(getListSite());

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
        JTable dataTable = new JTable(getPDailyStatisticsT().getArrayFillTable(nameSite, namePerson, startDate, finishDate, namesColumn.length), namesColumn);
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
       refreshDataTable(actionEvent);
    }

    @Override
    public void outTotalNumberPages(){
        getNumberPagesTotal().setText("Общее количество новых страниц за выбранный период: " +
                getPDailyStatisticsT().getNumberPagesTotal());
        getPanelStat().add(getNumberPagesTotal(), BorderLayout.SOUTH);
        getNumberPagesTotal().setVisible(true);
        getPanelStat().updateUI();
    }

    @Override
    public void refreshDataTable(ActionEvent actionEvent) {
        removeDataTable(dataScrollPane);
        initDataTable();
        outTotalNumberPages();
    }
}
