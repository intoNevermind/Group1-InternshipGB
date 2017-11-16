package windowGUI.component.workDirectory;

import windowGUI.ApplicationWindow;
import windowGUI.MyStyle;
import windowGUI.component.ConfigurationGBL;
import windowGUI.component.workDB.workProcessingData.ProcessingKeyWordsTable;
import windowGUI.component.workDB.workProcessingData.ProcessingPersonTable;
import windowGUI.component.workDB.workProcessingData.ProcessingSitesTable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class Directory {
    private static final MyStyle MY_STYLE = new MyStyle();

    private String tabName;

    private static final int INDENT_WIDTH = 200;
    private static final int INDENT_HEIGHT = 100;
    private static final int PANEL_DIRECTORY_SIZE_WIDTH = ApplicationWindow.getSizeWidth() - INDENT_WIDTH;
    private static final int PANEL_DIRECTORY_SIZE_HEIGHT = ApplicationWindow.getSizeHeight() - INDENT_HEIGHT;

    private static final GridBagLayout GBL = new GridBagLayout();
    private static final ConfigurationGBL CGBL = new ConfigurationGBL();

    private final JPanel panelDirectory = new JPanel();
    private final JPanel optionsPanel = new JPanel();
    private final JPanel btnPanel = new JPanel();

    private static final ProcessingPersonTable P_PERSON_T = new ProcessingPersonTable();
    private static final ProcessingKeyWordsTable P_KEY_WORDS_T = new ProcessingKeyWordsTable();
    private static final ProcessingSitesTable P_SITES_T = new ProcessingSitesTable();

    private final JLabel headLinePerson = new JLabel("Личности");

    private final JComboBox<String> listPersons = new JComboBox<>(P_PERSON_T.getColumnName());

    private final JButton btnConfirm = new JButton("Подтвердить");
    private final JButton btnAdd = new JButton("Добавить");
    private final JButton btnDelete = new JButton("Удалить");
    private final JButton btnEdit = new JButton("Редактировать");
    private final String[] columnNames = new String[]{"Наименование"};

    JTable dataTable;
    JScrollPane dataScrollPane;



    Directory() {
        MY_STYLE.setStyle(getListComponents());

        panelDirectory.setLayout(new BorderLayout());
        panelDirectory.setPreferredSize(new Dimension(PANEL_DIRECTORY_SIZE_WIDTH, PANEL_DIRECTORY_SIZE_HEIGHT));
        optionsPanel.setLayout(GBL);
        btnPanel.setLayout(new FlowLayout());
        panelDirectory.add(optionsPanel, BorderLayout.NORTH);
        panelDirectory.add(btnPanel,BorderLayout.SOUTH);

        fillBtnPanel();

        addActionListenerForBtnAdd();
        addActionListenerForBtnDel();
        addActionListenerForBtnEdit();
    }

    public abstract void visibleWindowAdd(ActionEvent actionEvent);
    public abstract void visibleWindowDel(ActionEvent actionEvent);
    public abstract void visibleWindowEdit(ActionEvent actionEvent);

    public void fillOptionsPanel(){}
    public void initNamePerson(ActionEvent actionEvent){}
    public void initSelectedRow(ListSelectionEvent selectionEvent){}
    public void visibleDataTable(ActionEvent actionEvent){}

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

    private void fillBtnPanel(){
        getBtnPanel().add(getBtnAdd());
        getBtnPanel().add(getBtnEdit());
        getBtnPanel().add(getBtnDelete());
    }

    private void removeDataTable(ActionEvent actionEvent) {
        for (int i = 0; i < getPanelDirectory().getComponents().length; i++) {
            if(getPanelDirectory().getComponents()[i].equals(dataScrollPane)){
                getPanelDirectory().remove(dataScrollPane);
            }
        }
        getPanelDirectory().updateUI();
    }

    private void addActionListenerForBtnAdd(){
        btnAdd.addActionListener(this::visibleWindowAdd);
    }

    private void addActionListenerForBtnDel(){
        btnDelete.addActionListener(this::visibleWindowDel);
    }

    private void addActionListenerForBtnEdit(){
        btnEdit.addActionListener(this::visibleWindowEdit);
    }

    void addActionListenerForListPerson(){
        listPersons.addActionListener(this::initNamePerson);
        listPersons.addActionListener(this::removeDataTable);
    }

    void addActionListenerForBtnConfirm(){
        btnConfirm.addActionListener(this::visibleDataTable);
    }

    public String getTabName() {
        return tabName;
    }
    void setTabName(String tabName) {
        this.tabName = tabName;
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
    JPanel getBtnPanel() {
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
}
