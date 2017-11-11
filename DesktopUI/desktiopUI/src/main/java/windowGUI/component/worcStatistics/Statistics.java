package windowGUI.component.worcStatistics;

import windowGUI.MyCalendar;
import windowGUI.component.ConfigurationGBL;
import windowGUI.component.workDB.processingData.ProcessingPersonPageRankTable;
import windowGUI.component.workDB.processingData.ProcessingPersonTable;
import windowGUI.component.workDB.processingData.ProcessingSitesTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

public abstract class Statistics {
    private String tabName ;

    private final GridBagLayout GBL = new GridBagLayout();
    private final ConfigurationGBL CGBL = new ConfigurationGBL();

    private final JPanel panelStat = new JPanel();
    private final JPanel optionsPanel = new JPanel();

    private final JButton btnConfirm = new JButton(" Подтвердить");

    private final ProcessingPersonPageRankTable PPersonPageRankT = new ProcessingPersonPageRankTable();

    private final JLabel headlineSite = new JLabel(" Сайты: ");
    private final ProcessingSitesTable PSitesT = new ProcessingSitesTable();
    private final JComboBox<Object> listSite = new JComboBox<>(PSitesT.getColumnName());

    private final JLabel headlinePersons = new JLabel(" Личности: ");
    private final ProcessingPersonTable PPersonT = new ProcessingPersonTable();
    private final JComboBox<Object> listPersons = new JComboBox<>(PPersonT.getColumnName());

    private final JLabel headlineStartPeriod = new JLabel(" Период c: ");
    private final MyCalendar startCalendar = new MyCalendar();

    private final JLabel headlineFinishPeriod = new JLabel(" по: ");
    private final MyCalendar finishCalendar = new MyCalendar();

    String[] columnNames;
    JTable dataTable;
    JScrollPane dataScrollPane;

    Statistics() {
        panelStat.setLayout(new BorderLayout());
        optionsPanel.setLayout(GBL);
        panelStat.add(optionsPanel, BorderLayout.NORTH);
    }

    public abstract void fillOptionsPanel();
    public abstract void listenerVisibleDataTable(ActionEvent actionEvent);

    public void initNameSites(ActionEvent actionEvent){}
    public void initNamePerson(ActionEvent actionEvent){}
    public void initStartDate(PropertyChangeEvent evt){}
    public void listenerRemoveDataTable(PropertyChangeEvent evt){}
    public void initFinishDate(PropertyChangeEvent evt){}

    void addActionListenerForListPerson(){
        listPersons.addActionListener(this::initNamePerson);
        listPersons.addActionListener(this::listenerRemoveDataTable);
    }

    void addActionListenerForListSite(){
        listSite.addActionListener(this::initNameSites);
        listSite.addActionListener(this::listenerRemoveDataTable);
    }

    void addActionListenerForStartCalendar(){
        startCalendar.getDateEditor().addPropertyChangeListener("date",this::initStartDate);
        startCalendar.getDateEditor().addPropertyChangeListener("date", this::listenerRemoveDataTable);
    }

    void addActionListenerForFinishCalendar(){
        finishCalendar.getDateEditor().addPropertyChangeListener("date",this::initFinishDate);
        finishCalendar.getDateEditor().addPropertyChangeListener("date", this::listenerRemoveDataTable);
    }

    void addActionListenerForBtnConfirm(){
        btnConfirm.addActionListener(this::listenerVisibleDataTable);
    }

    private void listenerRemoveDataTable(ActionEvent actionEvent) {
        for (int i = 0; i < getPanelStat().getComponents().length; i++) {
            if(getPanelStat().getComponents()[i].equals(dataScrollPane)){
                getPanelStat().remove(dataScrollPane);
            }
        }
        getPanelStat().updateUI();
    }

    void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }

    GridBagLayout getGBL() {
        return GBL;
    }

    ConfigurationGBL getCGBL() {
        return CGBL;
    }

    public JPanel getPanelStat() {
        return panelStat;
    }

    JPanel getOptionsPanel() {
        return optionsPanel;
    }

    JLabel getHeadlineSite() {
        return headlineSite;
    }

    JButton getBtnConfirm() {
        return btnConfirm;
    }

    ProcessingPersonPageRankTable getPPersonPageRankT() {
        return PPersonPageRankT;
    }

    JComboBox<Object> getListSite() {
        return listSite;
    }

    JComboBox<Object> getListPersons() {
        return listPersons;
    }

    JLabel getHeadlinePersons() {
        return headlinePersons;
    }

    JLabel getHeadlineStartPeriod() {
        return headlineStartPeriod;
    }

    MyCalendar getStartCalendar() {
        return startCalendar;
    }

    JLabel getHeadlineFinishPeriod() {
        return headlineFinishPeriod;
    }

    MyCalendar getFinishCalendar() {
        return finishCalendar;
    }
}
