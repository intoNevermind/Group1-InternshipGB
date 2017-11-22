package windowGUI.component.workDirectory;

import windowGUI.ApplicationWindow;
import windowGUI.MyStyle;
import windowGUI.component.ConfigurationGBL;
import windowGUI.component.workDB.processingData.ProcessingKeyWordsTable;
import windowGUI.component.workDB.processingData.ProcessingPersonTable;
import windowGUI.component.workDB.processingData.ProcessingSitesTable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
/*
* Родительский класс для классов-справочников
* */
public abstract class Directory {
    private static final MyStyle MY_STYLE = new MyStyle();

    private String nameTab;

    private static final int PANEL_DIRECTORY_SIZE_WIDTH = ApplicationWindow.getSizeWidth();
    private static final int PANEL_DIRECTORY_SIZE_HEIGHT = ApplicationWindow.getSizeHeight();

    private static final GridBagLayout GBL = new GridBagLayout();
    private static final ConfigurationGBL CGBL = new ConfigurationGBL();

    private final JPanel panelDirectory = new JPanel(new BorderLayout());
    private final JPanel panelOptions = new JPanel(GBL);
    private final JPanel panelBtn = new JPanel(new FlowLayout());

    private static final ProcessingPersonTable P_PERSON_T = new ProcessingPersonTable();
    private static final ProcessingKeyWordsTable P_KEY_WORDS_T = new ProcessingKeyWordsTable();
    private static final ProcessingSitesTable P_SITES_T = new ProcessingSitesTable();

    private final JLabel headLinePerson = new JLabel("Личности");

    private final JComboBox<String> listPersons = new JComboBox<>(P_PERSON_T.getArrayNamePersons());

    private final JButton btnConfirm = new JButton("Подтвердить");
    private final JButton btnRefresh = new JButton("Обновить");
    private final JButton btnAdd = new JButton("Добавить");
    private final JButton btnDelete = new JButton("Удалить");
    private final JButton btnEdit = new JButton("Редактировать");

    private final String[] namesColumn = new String[]{"Наименование"};

    Directory() {
        MY_STYLE.setStyle(getListComponents());

        panelDirectory.setPreferredSize(new Dimension(PANEL_DIRECTORY_SIZE_WIDTH, PANEL_DIRECTORY_SIZE_HEIGHT));
        panelDirectory.add(panelOptions, BorderLayout.NORTH);
        panelDirectory.add(panelBtn,BorderLayout.SOUTH);

        fillOptionsPanel();
        fillBtnPanel();

        addActionListenerForBtn();
    }

    /*
     * метод, отвечающий за передачу всех элементов справочников для установки графического вида
     * */
    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(headLinePerson);

        listComponent.add(listPersons);

        listComponent.add(btnConfirm);
        listComponent.add(btnRefresh);
        listComponent.add(btnAdd);
        listComponent.add(btnDelete);
        listComponent.add(btnEdit);

        return listComponent;
    }

    /*
     * <абстрактные методы>
     * */
    public abstract void fillOptionsPanel();// заполняет панель опций

    public abstract void initDataTable();// инициализирует таблицу данных
    public abstract void initSelectedRow(ListSelectionEvent selectionEvent);// инициализирует строку таблицы

    public abstract void refreshDataTable(ActionEvent actionEvent);//обновляет таблицу данных

    public abstract void visibleWindowAdd(ActionEvent actionEvent);// вызывает окно добавления элемента
    public abstract void visibleWindowDel(ActionEvent actionEvent);// вызывает окно удаления элемента
    public abstract void visibleWindowEdit(ActionEvent actionEvent);// вызывает окно редактирования элемента
    /*
     * </абстрактные методы>
     * */

    /*
     * <общие методы>
     * одинаковые и обязательные для всех справочников
     * */

    /*
     * метод, заполняющий панэль кнопок, кнопками
     * */
    private void fillBtnPanel(){
        panelBtn.add(btnAdd);
        panelBtn.add(btnEdit);
        panelBtn.add(btnDelete);
    }

    /*
     * метод, добавляющий листенеры для кнопок
     * */
    private void addActionListenerForBtn(){
        btnConfirm.addActionListener(this::visibleDataTable);
        btnRefresh.addActionListener(this::refreshDataTable);
        btnAdd.addActionListener(this::visibleWindowAdd);
        btnDelete.addActionListener(this::visibleWindowDel);
        btnEdit.addActionListener(this::visibleWindowEdit);
    }

    /*
     * метод, удаляющий таблицу с данными
     * */
    void removeDataTable(JScrollPane dataScrollPane){
        for (int i = 0; i <  getPanelDirectory().getComponents().length; i++) {
            if( getPanelDirectory().getComponents()[i].equals(dataScrollPane)){
                getPanelDirectory().remove(dataScrollPane);
            }
        }
        getPanelDirectory().updateUI();
    }

    /*
     * </общие методы>
     * */

    /*
     * <специфичные методы>
     * специфичные методы, которые могут быть в классе-справочнике
     * */
    public void initNamePerson(ActionEvent actionEvent){}// инициализирует имя личности
    public void visibleDataTable(ActionEvent actionEvent){}// делает видимой таблицу с данными

    /*
     * метод, добавляющий листенеры для выпадающего списка личностей
     * */
    void addActionListenerForListPerson(){
        listPersons.addActionListener(this::initNamePerson);
    }
    /*
     * </специфичные методы>
     * */

    /*
     * <getters and setters>
     * */
    public String getNameTab() {
        return nameTab;
    }
    void setNameTab(String nameTab) {
        this.nameTab = nameTab;
    }

    static GridBagLayout getGBL() {
        return GBL;
    }
    static ConfigurationGBL getCGBL() {
        return CGBL;
    }

    public JPanel getPanelDirectory() {
        return panelDirectory;
    }
    JPanel getPanelOptions() {
        return panelOptions;
    }

    static ProcessingPersonTable getPPersonT() {
        return P_PERSON_T;
    }
    static ProcessingKeyWordsTable getPKeyWordsT() {
        return P_KEY_WORDS_T;
    }
    static ProcessingSitesTable getPSitesT() {
        return P_SITES_T;
    }

    JLabel getHeadLinePerson() {
        return headLinePerson;
    }

    JComboBox<String> getListPersons() {
        return listPersons;
    }

    JButton getBtnConfirm() {
        return btnConfirm;
    }
    JButton getBtnRefresh() {
        return btnRefresh;
    }
    JButton getBtnAdd() {
        return btnAdd;
    }
    JButton getBtnDelete() {
        return btnDelete;
    }
    JButton getBtnEdit() {
        return btnEdit;
    }

    String[] getNamesColumn() {
        return namesColumn;
    }
    /*
     * </getters and setters>
     * */
}
