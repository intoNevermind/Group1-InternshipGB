package windowGUI.component.worcStatistics;

import windowGUI.component.workDB.workProcessingData.ProcessingData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;

public class DailyStatistic extends Statistics{
    private static final String TAB_NAME = "Ежедневная статистика";

    private static final JLabel numberPagesTotal = new JLabel();

    private static String nameSite;
    private static String namePerson;
    private static String startDate;
    private static String finishDate;

    public DailyStatistic() {
        setTabName(TAB_NAME);

        fillOptionsPanel();

        addActionListenerForListSite();
        addActionListenerForBtnConfirm();
        addActionListenerForListPerson();
        addActionListenerForStartCalendar();
        addActionListenerForFinishCalendar();
        columnNames = new String[]{"Дата", "Количество новых страниц"};
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadlineSite(), getCGBL().configMainGBC(getHeadlineSite(),false));
        getOptionsPanel().add(getHeadlineSite());
        getGBL().setConstraints(getListSite(), getCGBL().configMainGBC(getListSite(),false));
        getOptionsPanel().add(getListSite());

        getGBL().setConstraints(getHeadlinePersons(), getCGBL().configMainGBC(getHeadlinePersons(),true));
        getOptionsPanel().add(getHeadlinePersons());
        getGBL().setConstraints(getListPersons(), getCGBL().configMainGBC(getListPersons(),false));
        getOptionsPanel().add(getListPersons());

        getGBL().setConstraints(getHeadlineStartPeriod(), getCGBL().configMainGBC(getHeadlineStartPeriod(), true));
        getOptionsPanel().add(getHeadlineStartPeriod());
        getGBL().setConstraints(getStartCalendar(), getCGBL().configMainGBC(getStartCalendar(),false));
        getOptionsPanel().add(getStartCalendar());

        getGBL().setConstraints(getHeadlineFinishPeriod(), getCGBL().configMainGBC(getHeadlineFinishPeriod(),true));
        getOptionsPanel().add(getHeadlineFinishPeriod());
        getGBL().setConstraints(getFinishCalendar(), getCGBL().configMainGBC(getFinishCalendar(),false));
        getOptionsPanel().add(getFinishCalendar());

        getGBL().setConstraints(getBtnConfirm(), getCGBL().configMainGBC(getBtnConfirm(), true));
        getOptionsPanel().add(getBtnConfirm());
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
        startDate = new SimpleDateFormat("yyyy-MM-dd").format(getStartCalendar().getDate());
    }

    @Override
    public void initFinishDate(PropertyChangeEvent evt){
        finishDate = new SimpleDateFormat("yyyy-MM-dd").format(getFinishCalendar().getDate());
    }

    @Override
    public void visibleDataTable(ActionEvent actionEvent){
        String str = "";
        if(nameSite == null || nameSite.equals(ProcessingData.getNotChosen())) str += " \"" + getHeadlineSite().getText() + "\" ";
        if(namePerson == null || nameSite.equals(ProcessingData.getNotChosen())) str += " \"" + getHeadlinePersons().getText() + "\" ";
        if(startDate == null) str += " \"" + getHeadlineStartPeriod().getText() + "\" ";
        if(finishDate == null) str += " \"" + getHeadlineFinishPeriod().getText() + "\" ";
        if(!str.equals("")) {
            JOptionPane.showMessageDialog(null, "Для просмотра ежедневной статистики необходимо выбрать " + str);
        }

        dataTable = new JTable(getPPersonPageRankT().getArrayFillTable(nameSite,namePerson,startDate,finishDate, columnNames.length), columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);
        dataScrollPane.setVisible(true);
        numberPagesTotal.setText("Общее количество новых страниц за выбранный период: " + getPPersonPageRankT().getIntNumberPagesTotal(nameSite,namePerson,startDate,finishDate));
        getPanelStat().add(numberPagesTotal, BorderLayout.SOUTH);
        numberPagesTotal.setVisible(true);
        getPanelStat().updateUI();
    }

    @Override
    public void removeDataTable(PropertyChangeEvent evt){
        for (int i = 0; i < getPanelStat().getComponents().length; i++) {
            if(getPanelStat().getComponents()[i].equals(dataScrollPane)){
                getPanelStat().remove(dataScrollPane);
            }
        }
        getPanelStat().updateUI();
    }
}
