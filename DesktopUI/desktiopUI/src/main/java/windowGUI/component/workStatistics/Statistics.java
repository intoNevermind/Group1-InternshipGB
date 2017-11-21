package windowGUI.component.workStatistics;

import windowGUI.ApplicationWindow;
import windowGUI.MyStyle;
import windowGUI.component.ConfigurationGBL;
import windowGUI.component.workDB.processingData.ProcessingPersonPageRankTable;
import windowGUI.component.workDB.processingData.ProcessingPersonTable;
import windowGUI.component.workDB.processingData.ProcessingSitesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
/*
 * Родительский класс для классов-статистик
 * */
public abstract class Statistics {
    private static final MyStyle MY_STYLE = new MyStyle();

    private String tabName;
    private static final String EMPTY_FIELDS = "Не инициализированы поля";

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
    private final JLabel numberPagesTotal = new JLabel();

    private final JComboBox<Object> listSite = new JComboBox<>(P_SITES_T.getArrayNameSites());
    private final JComboBox<Object> listPersons = new JComboBox<>(P_PERSON_T.getArrayNamePersons());

    private final MyCalendar startCalendar = new MyCalendar();
    private final MyCalendar finishCalendar = new MyCalendar();

    private final JButton btnConfirm = new JButton(" Подтвердить");
    private final JButton btnRefresh = new JButton("Обновить");

    Statistics() {
        MY_STYLE.setStyle(getListComponents());

        panelStat.setPreferredSize(new Dimension(PANEL_STAT_SIZE_WIDTH, PANEL_STAT_SIZE_HEIGHT));
        panelStat.add(optionsPanel, BorderLayout.NORTH);

        fillOptionsPanel();

        addActionListenerForListSite();
        addActionListenerForBtn();
    }

    /*
     * метод, отвечающий за передачу всех элементов статистик для установки графического вида
     * */
    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(headlineSite);
        listComponent.add(headlinePersons);
        listComponent.add(headlineStartPeriod);
        listComponent.add(headlineFinishPeriod);
        listComponent.add(numberPagesTotal);

        listComponent.add(listSite);
        listComponent.add(listPersons);

        listComponent.add(startCalendar);
        listComponent.add(finishCalendar);

        listComponent.add(btnConfirm);
        listComponent.add(btnRefresh);

        return listComponent;
    }

    /*
     * <абстрактные методы>
     * */
    public abstract void fillOptionsPanel();// заполняет панэль опций
    public abstract void initDataTable();// инициализирует таблицу данных
    public abstract void refreshDataTable(ActionEvent actionEvent);//обновляет таблицу данных
    public abstract void visibleDataTable(ActionEvent actionEvent);//делает видимой таблицу с данными
    /*
     * </абстрактные методы>
     * */

    /*
     * <общие методы>
     * одинаковые и обязательные для всех статистик
     * */

    /*
     * метод, удаляющий таблицу с данными
     * */
    void removeDataTable(JScrollPane dataScrollPane) {
        for (int i = 0; i < getPanelStat().getComponents().length; i++) {
            if(getPanelStat().getComponents()[i].equals(dataScrollPane)){
                getPanelStat().remove(dataScrollPane);
            }
        }
        getPanelStat().updateUI();
    }

    /*
     * метод, добавляющий листенеры для выпадающего списка сайтов
     * */
    private void addActionListenerForListSite(){
        listSite.addActionListener(this::initNameSites);
    }

    /*
     * метод, добавляющий листенеры для кнопок
     * */
    private void addActionListenerForBtn(){
        btnConfirm.addActionListener(this::visibleDataTable);
        btnRefresh.addActionListener(this::refreshDataTable);
    }
    /*
     * </общие методы>
     * */

    /*
     * <специфичные методы>
     * специфичные методы, которые могут быть в классе-статистике
     * */
    public void initNameSites(ActionEvent actionEvent){}// инициализирует имя сайта
    public void initNamePerson(ActionEvent actionEvent){}// инициализирует имя личности
    public void initStartDate(PropertyChangeEvent evt){}// инициализирует начальную дату
    public void initFinishDate(PropertyChangeEvent evt){}// инициализирует конечную дату
    public void outTotalNumberPages(){}// выводит общее количество найденных страниц

    /*
     * метод, добавляющий листенеры для выпадающего списка личностей
     * */
    void addActionListenerForListPerson(){
        listPersons.addActionListener(this::initNamePerson);
    }

    /*
     * метод, добавляющий листенеры для календарей
     * */
    void addActionListenerForCalendars(){
        startCalendar.getDateEditor().addPropertyChangeListener("date",this::initStartDate);
        finishCalendar.getDateEditor().addPropertyChangeListener("date",this::initFinishDate);
    }
    /*
     * </специфичные методы>
     * */

    /*
     * <getters and setters>
     * */
    void setTabName(String tabName) {
        this.tabName = tabName;
    }
    public String getTabName() {
        return tabName;
    }
    static String getEmptyFields() {
        return EMPTY_FIELDS;
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
    JLabel getNumberPagesTotal() {
        return numberPagesTotal;
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
    JButton getBtnRefresh() {
        return btnRefresh;
    }
    /*
     * </getters and setters>
     * */
}
