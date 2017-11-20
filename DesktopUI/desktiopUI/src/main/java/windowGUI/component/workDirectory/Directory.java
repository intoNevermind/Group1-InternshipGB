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
    private final JPanel optionsPanel = new JPanel(GBL);
    private final JPanel btnPanel = new JPanel(new FlowLayout());

    private static final ProcessingPersonTable P_PERSON_T = new ProcessingPersonTable();
    private static final ProcessingKeyWordsTable P_KEY_WORDS_T = new ProcessingKeyWordsTable();
    private static final ProcessingSitesTable P_SITES_T = new ProcessingSitesTable();

    private final JLabel headLinePerson = new JLabel("Личности");

    private final JComboBox<String> listPersons = new JComboBox<>(P_PERSON_T.getArrayNamePersons());

    private final JButton btnConfirm = new JButton("Подтвердить");
    private final JButton btnAdd = new JButton("Добавить");
    private final JButton btnDelete = new JButton("Удалить");
    private final JButton btnEdit = new JButton("Редактировать");
    private final String[] columnNames = new String[]{"Наименование"};

    JTable dataTable;
    JScrollPane dataScrollPane;

    Directory() {
        MY_STYLE.setStyle(getListComponents());

        panelDirectory.setPreferredSize(new Dimension(PANEL_DIRECTORY_SIZE_WIDTH, PANEL_DIRECTORY_SIZE_HEIGHT));
        panelDirectory.add(optionsPanel, BorderLayout.NORTH);
        panelDirectory.add(btnPanel,BorderLayout.SOUTH);
        panelDirectory.updateUI();

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
        listComponent.add(btnAdd);
        listComponent.add(btnDelete);
        listComponent.add(btnEdit);

        listComponent.add(dataTable);
        return listComponent;
    }

    /*
     * <абстрактные методы>
     * */
    public abstract void visibleWindowAdd(ActionEvent actionEvent);// вызывает окно добавления элемента
    public abstract void initSelectedRow(ListSelectionEvent selectionEvent);// инициализирует строку таблицы
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
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
    }



    /*
     * метод, добавляющий листенеры для кнопок
     * */
    private void addActionListenerForBtn(){
        btnAdd.addActionListener(this::visibleWindowAdd);
//        btnAdd.addActionListener(this::removeDataTable);
        btnDelete.addActionListener(this::visibleWindowDel);
//        btnDelete.addActionListener(this::removeDataTable);
        btnEdit.addActionListener(this::visibleWindowEdit);
//        btnEdit.addActionListener(this::removeDataTable);
    }

    /*
     * </общие методы>
     * */

    /*
     * <специфичные методы>
     * специфичные методы, которые могут быть в классе-справочнике
     * */
    public void fillOptionsPanel(){}// заполняет панель опций
    public void initNamePerson(ActionEvent actionEvent){}// инициализирует имя личности
    public void visibleDataTable(ActionEvent actionEvent){}// делает видимой таблицу с данными

    /*
     * метод, добавляющий листенеры для выпадающего списка личностей
     * */
    void addActionListenerForListPerson(){
        listPersons.addActionListener(this::initNamePerson);
        listPersons.addActionListener(this::removeDataTable);
    }

    /*
     * метод, удаляющий таблицу с данными
     * */
    private void removeDataTable(ActionEvent actionEvent) {
        for (int i = 0; i <  panelDirectory.getComponents().length; i++) {
            if( panelDirectory.getComponents()[i].equals(dataScrollPane)){
                panelDirectory.remove(dataScrollPane);
            }
        }
        panelDirectory.updateUI();
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
    JPanel getOptionsPanel() {
        return optionsPanel;
    }
    private JPanel getBtnPanel() {
        return btnPanel;
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
    JButton getBtnAdd() {
        return btnAdd;
    }
    JButton getBtnDelete() {
        return btnDelete;
    }
    JButton getBtnEdit() {
        return btnEdit;
    }

    String[] getColumnNames() {
        return columnNames;
    }
    /*
     * </getters and setters>
     * */
}
