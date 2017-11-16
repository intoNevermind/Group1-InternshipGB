package windowGUI.component.worcStatistics;

import windowGUI.ApplicationWindow;
import windowGUI.MyStyle;
import windowGUI.component.ConfigurationGBL;
import windowGUI.component.workDB.workProcessingData.ProcessingPersonPageRankTable;
import windowGUI.component.workDB.workProcessingData.ProcessingPersonTable;
import windowGUI.component.workDB.workProcessingData.ProcessingSitesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public abstract class Statistics {
    private static final MyStyle MY_STYLE = new MyStyle();

    private String tabName ;

    private static final int PANEL_STAT_SIZE_WIDTH = ApplicationWindow.getSizeWidth() ;
    private static final int PANEL_STAT_SIZE_HEIGHT = ApplicationWindow.getSizeHeight() ;

    private static final GridBagLayout GBL = new GridBagLayout();
    private static final ConfigurationGBL CGBL = new ConfigurationGBL();

    private final JPanel panelStat = new JPanel(new BorderLayout());
    private final JPanel optionsPanel = new JPanel(GBL);

    private static final ProcessingPersonPageRankTable P_PERSON_PAGE_RANK_T = new ProcessingPersonPageRankTable();
    private static final ProcessingSitesTable P_SITES_T = new ProcessingSitesTable();
    private static final ProcessingPersonTable P_PERSON_T = new ProcessingPersonTable();

    private final JLabel headlineSite = new JLabel(" Сайты: ");
    private final JLabel headlinePersons = new JLabel(" Личности: ");
    private final JLabel headlineStartPeriod = new JLabel(" Период c: ");
    private final JLabel headlineFinishPeriod = new JLabel(" по: ");

    private final JComboBox<Object> listSite = new JComboBox<>(P_SITES_T.getArrayNameSites());
    private final JComboBox<Object> listPersons = new JComboBox<>(P_PERSON_T.getColumnName());

    private final MyCalendar startCalendar = new MyCalendar();
    private final MyCalendar finishCalendar = new MyCalendar();

    private final JButton btnConfirm = new JButton(" Подтвердить");

    String[] columnNames;
    JTable dataTable;
    JScrollPane dataScrollPane;


    Statistics() {
        MY_STYLE.setStyle(getListComponents());

        panelStat.setPreferredSize(new Dimension(PANEL_STAT_SIZE_WIDTH, PANEL_STAT_SIZE_HEIGHT));
        panelStat.add(optionsPanel, BorderLayout.NORTH);

        fillOptionsPanel();

        addActionListenerForListSite();
        addActionListenerForBtnConfirm();
    }

    public abstract void fillOptionsPanel();
    public abstract void visibleDataTable(ActionEvent actionEvent);

    public void initNameSites(ActionEvent actionEvent){}
    public void initNamePerson(ActionEvent actionEvent){}
    public void initStartDate(PropertyChangeEvent evt){}
    public void removeDataTable(PropertyChangeEvent evt){}
    public void initFinishDate(PropertyChangeEvent evt){}

    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(headlineSite);
        listComponent.add(headlinePersons);
        listComponent.add(headlineStartPeriod);
        listComponent.add(headlineFinishPeriod);

        listComponent.add(listSite);
        listComponent.add(listPersons);

        listComponent.add(startCalendar);
        listComponent.add(finishCalendar);

        listComponent.add(btnConfirm);

        listComponent.add(dataTable);
        return listComponent;
    }

    private void removeDataTable(ActionEvent actionEvent) {
        for (int i = 0; i < getPanelStat().getComponents().length; i++) {
            if(getPanelStat().getComponents()[i].equals(dataScrollPane)){
                getPanelStat().remove(dataScrollPane);
            }
        }
        getPanelStat().updateUI();
    }

    private void addActionListenerForBtnConfirm(){
        btnConfirm.addActionListener(this::visibleDataTable);
    }

    private void addActionListenerForListSite(){
        listSite.addActionListener(this::initNameSites);
        listSite.addActionListener(this::removeDataTable);
    }

    void addActionListenerForListPerson(){
        listPersons.addActionListener(this::initNamePerson);
        listPersons.addActionListener(this::removeDataTable);
    }

    void addActionListenerForStartCalendar(){
        startCalendar.getDateEditor().addPropertyChangeListener("date",this::initStartDate);
        startCalendar.getDateEditor().addPropertyChangeListener("date", this::removeDataTable);
    }

    void addActionListenerForFinishCalendar(){
        finishCalendar.getDateEditor().addPropertyChangeListener("date",this::initFinishDate);
        finishCalendar.getDateEditor().addPropertyChangeListener("date", this::removeDataTable);
    }

    void setTabName(String tabName) {
        this.tabName = tabName;
    }
    public String getTabName() {
        return tabName;
    }

    static GridBagLayout getGBL() {
        return GBL;
    }
    static ConfigurationGBL getCGBL() {
        return CGBL;
    }

    public JPanel getPanelStat() {
        return panelStat;
    }
    JPanel getOptionsPanel() {
        return optionsPanel;
    }

    static ProcessingPersonPageRankTable getPPersonPageRankT() {
        return P_PERSON_PAGE_RANK_T;
    }

    JLabel getHeadlineSite() {
        return headlineSite;
    }
    JLabel getHeadlinePersons() {
        return headlinePersons;
    }
    JLabel getHeadlineStartPeriod() {
        return headlineStartPeriod;
    }
    JLabel getHeadlineFinishPeriod() {
        return headlineFinishPeriod;
    }

    JComboBox<Object> getListSite() {
        return listSite;
    }
    JComboBox<Object> getListPersons() {
        return listPersons;
    }

    MyCalendar getStartCalendar() {
        return startCalendar;
    }
    MyCalendar getFinishCalendar() {
        return finishCalendar;
    }

    JButton getBtnConfirm() {
        return btnConfirm;
    }
}
