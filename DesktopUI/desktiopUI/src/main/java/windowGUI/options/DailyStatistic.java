package windowGUI.options;

import windowGUI.MyCalendar;
import windowGUI.options.workSQL.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;

public class DailyStatistic extends Statistics{
    private static final String TAB_NAME = "Ежедневная статистика";

    private static final JLabel headlinePersons = new JLabel(" Личности: ");
    private static final JLabel headlineStartPeriod = new JLabel(" Период c: ");
    private static final JLabel headlineFinishPeriod = new JLabel(" по: ");
    private static final JLabel totalNumberPages = new JLabel();

    private static final ProcessingPersonTable PPersonT = new ProcessingPersonTable();

    private static final JComboBox<Object> listPersons = new JComboBox<>(PPersonT.getColumnName());

    private static final MyCalendar startCalendar = new MyCalendar();
    private static final MyCalendar finishCalendar = new MyCalendar();

    private static String nameSite;
    private static String namePerson;
    private static String startDate;
    private static String finishDate;

    public DailyStatistic() {
        setTabName(TAB_NAME);

        fillOptionsPanel();

        addActionListenerForListSiteAndBtnConfirm();

        listPersons.addActionListener(this::initNamePerson);
        listPersons.addActionListener(this::listenerRemoveDataTable);

        startCalendar.getDateEditor().addPropertyChangeListener("date",this::initStartDate);
        startCalendar.getDateEditor().addPropertyChangeListener("date",this::listenerRemoveDataTable);

        finishCalendar.getDateEditor().addPropertyChangeListener("date", this::initFinishDate);
        finishCalendar.getDateEditor().addPropertyChangeListener("date",this::listenerRemoveDataTable);

        columnNames = new String[]{"Дата", "Количество новых страниц"};
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadlineSite(), configGBC(getHeadlineSite(),false));
        getOptionsPanel().add(getHeadlineSite());
        getGBL().setConstraints(getListSite(), configGBC(getListSite(),false));
        getOptionsPanel().add(getListSite());

        getGBL().setConstraints(headlinePersons, configGBC(headlinePersons,true));
        getOptionsPanel().add(headlinePersons);
        getGBL().setConstraints(listPersons, configGBC(getListSite(),false));
        getOptionsPanel().add(listPersons);

        getGBL().setConstraints(headlineStartPeriod, configGBC(headlineStartPeriod, true));
        getOptionsPanel().add(headlineStartPeriod);
        getGBL().setConstraints(startCalendar, configGBC(startCalendar,false));
        getOptionsPanel().add(startCalendar);

        getGBL().setConstraints(headlineFinishPeriod, configGBC(headlineFinishPeriod,true));
        getOptionsPanel().add(headlineFinishPeriod);
        getGBL().setConstraints(finishCalendar, configGBC(finishCalendar,false));
        getOptionsPanel().add(finishCalendar);

        getGBL().setConstraints(getBtnConfirm(), configGBC(getBtnConfirm(), true));
        getOptionsPanel().add(getBtnConfirm());
    }

    @Override
    public void initNameSites(ActionEvent actionEvent){
        JComboBox box = (JComboBox)actionEvent.getSource();
        nameSite = (String)box.getSelectedItem();
    }

    private void initNamePerson(ActionEvent actionEvent){
        JComboBox box = (JComboBox)actionEvent.getSource();
        namePerson = (String)box.getSelectedItem();
    }


    private void initStartDate(PropertyChangeEvent evt){
        startDate = new SimpleDateFormat("yyyy-MM-dd").format(startCalendar.getDate());
    }

    private void initFinishDate(PropertyChangeEvent evt){
        finishDate = new SimpleDateFormat("yyyy-MM-dd").format(finishCalendar.getDate());
    }

    @Override
    public void listenerVisibleDataTable(ActionEvent actionEvent){
        String str = "";
        if(nameSite == null) str += " \"" + getHeadlineSite().getText() + "\" ";
        if(namePerson == null) str += " \"" + headlinePersons.getText() + "\" ";
        if(startDate == null) str += " \"" + headlineStartPeriod.getText() + "\" ";
        if(finishDate == null) str += " \"" + headlineFinishPeriod.getText() + "\" ";
        if(!str.equals("")) JOptionPane.showMessageDialog(null, "Для просмотра ежедневной статистики необходимо выбрать " + str);

        dataTable = new JTable(getPPersonPageRankT().getArrayFillDailyTable(nameSite,namePerson,startDate,finishDate), columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelStat().add(dataScrollPane, BorderLayout.CENTER);
        dataScrollPane.setVisible(true);
        getPanelStat().updateUI();
    }

    private void listenerRemoveDataTable(PropertyChangeEvent evt){
        for (int i = 0; i < getPanelStat().getComponents().length; i++) {
            if(getPanelStat().getComponents()[i].equals(dataScrollPane)){
                getPanelStat().remove(dataScrollPane);
            }
        }
        getPanelStat().updateUI();
    }



//    private long countTotalNumberPages(){
//        int count = 0;
//        for (int i = 0; i < data.length; i++) {
//            count += (int)data[i][1];
//        }
//        return count;
//    }
}
