package windowGUI.component.workWithStatistics;

import windowGUI.ApplicationWindow;
import windowGUI.MyStyle;
import windowGUI.ConfigurationGBL;
import windowGUI.component.ChangeItemsJComboBox;
import windowGUI.component.MyCalendar;
import windowGUI.component.WorkWithDataTable;
import windowGUI.component.workWithDB.processingData.ProcessingDailyStatisticsTable;
import windowGUI.component.workWithDB.processingData.ProcessingGeneralStatisticsTable;
import windowGUI.component.workWithDB.processingData.ProcessingPersonTable;
import windowGUI.component.workWithDB.processingData.ProcessingSitesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

    private static final ProcessingGeneralStatisticsTable P_GENERAL_STATISTICS_T = new ProcessingGeneralStatisticsTable();
    private static final ProcessingDailyStatisticsTable P_DAILY_STATISTICS_T = new ProcessingDailyStatisticsTable();
    private static final ProcessingSitesTable P_SITES_T = new ProcessingSitesTable();
    private static final ProcessingPersonTable P_PERSON_T = new ProcessingPersonTable();

    private final JLabel headlineSite = new JLabel(" Сайты: ");
    private final JLabel headlinePersons = new JLabel(" Личности: ");
    private final JLabel headlineStartPeriod = new JLabel(" Период c: ");
    private final JLabel headlineFinishPeriod = new JLabel(" по: ");
    private final JLabel numberPagesTotal = new JLabel();

    private final JComboBox<Object> listSites = new JComboBox<>(P_SITES_T.getArrayNameSites());
    private final JComboBox<Object> listPersons = new JComboBox<>(P_PERSON_T.getArrayNamePersons());

    private final MyCalendar startCalendar = new MyCalendar();
    private final MyCalendar finishCalendar = new MyCalendar();

    private final JButton btnConfirm = new JButton(" Подтвердить");
    private final JButton btnRefresh = new JButton("Обновить");

    JTable dataTable;
    private JScrollPane dataScrollPane;

    private static final ChangeItemsJComboBox CHANGE_ITEMS_J_COMBO_BOX = new ChangeItemsJComboBox();
    private static final WorkWithDataTable WORK_WITH_DATA_TABLE = new WorkWithDataTable();


    Statistics() {
        MY_STYLE.setStyle(getListComponents());

        panelStat.setPreferredSize(new Dimension(PANEL_STAT_SIZE_WIDTH, PANEL_STAT_SIZE_HEIGHT));
        panelStat.add(optionsPanel, BorderLayout.NORTH);

        fillOptionsPanel();

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

        listComponent.add(listSites);
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
    public abstract void visibleDataTable(ActionEvent actionEvent);//показывает таблицу с данными
    public abstract void refreshAll(ActionEvent actionEvent);//обновляет статистику
    /*
     * </абстрактные методы>
     * */

    /*
     * <общие методы>
     * одинаковые и обязательные для всех статистик
     * */

    /*
     * метод, добавляющий листенеры для кнопок
     * */
    private void addActionListenerForBtn(){
        btnConfirm.addActionListener(this::visibleDataTable);
        btnRefresh.addActionListener(this::refreshAll);
    }

    /*
     * метод, добавляющий таблицу данных на панэль статистики
     * */
    void addDataTable(){
        dataScrollPane = new JScrollPane(dataTable);
        panelStat.add(dataScrollPane, BorderLayout.CENTER);
    }

    /*
     * метод, меняющий старую таблицу данных на новую
     * */
    void refreshDataTable(){
        WORK_WITH_DATA_TABLE.removeDataTable(dataScrollPane, panelStat);
        initDataTable();
    }
    /*
     * </общие методы>
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

    static ProcessingGeneralStatisticsTable getPGeneralStatisticsT() {
        return P_GENERAL_STATISTICS_T;
    }
    static ProcessingDailyStatisticsTable getPDailyStatisticsT() {
        return P_DAILY_STATISTICS_T;
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

    JComboBox<Object> getListSites() {
        return listSites;
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

    ChangeItemsJComboBox getChangeItemsJComboBox() {
        return CHANGE_ITEMS_J_COMBO_BOX;
    }
    /*
     * </getters and setters>
     * */
}
