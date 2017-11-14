package windowGUI.component.workDirectory;

import windowGUI.ApplicationWindow;
import windowGUI.component.ConfigurationGBL;
import windowGUI.component.workDB.workProcessingData.ProcessingKeyWordsTable;
import windowGUI.component.workDB.workProcessingData.ProcessingPersonTable;
import windowGUI.component.workDB.workProcessingData.ProcessingSitesTable;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;


public abstract class Directory {
    private String tabName ;

    private final GridBagLayout GBL = new GridBagLayout();
    private final ConfigurationGBL CGBL = new ConfigurationGBL();

    private static final int INDENT_WIDTH = 200;
    private static final int INDENT_HEIGHT = 100;
    private static final int PANEL_DIRECTORY_SIZE_WIDTH = ApplicationWindow.getSizeWidth() - INDENT_WIDTH;
    private static final int PANEL_DIRECTORY_SIZE_HEIGHT = ApplicationWindow.getSizeHeight() - INDENT_HEIGHT;

    private final JPanel panelDirectory = new JPanel();
    private final JPanel optionsPanel = new JPanel();
    private final JPanel btnPanel = new JPanel();


    private final JLabel headLinePerson = new JLabel("Личности");

    private static final ProcessingPersonTable PPersonT = new ProcessingPersonTable();
    private final JComboBox<String> listPersons = new JComboBox<>(PPersonT.getColumnName());

    private final JButton btnConfirm = new JButton("Подтвердить");
    private final JButton btnAdd = new JButton("Добавить");
    private final JButton btnDelete = new JButton("Удалить");
    private final JButton btnEdit = new JButton("Редактировать");

    private final ProcessingKeyWordsTable PKeyWordsT = new ProcessingKeyWordsTable();
    private final ProcessingSitesTable PSitesT = new ProcessingSitesTable();


    private final String[] columnNames = new String[]{"Наименование"};
    JTable dataTable;
    JScrollPane dataScrollPane;

    Directory() {
        panelDirectory.setLayout(new BorderLayout());
        panelDirectory.setPreferredSize(new Dimension(PANEL_DIRECTORY_SIZE_WIDTH, PANEL_DIRECTORY_SIZE_HEIGHT));
        optionsPanel.setLayout(GBL);
        btnPanel.setLayout(new FlowLayout());
        panelDirectory.add(optionsPanel, BorderLayout.NORTH);
        panelDirectory.add(btnPanel,BorderLayout.SOUTH);
    }

    public abstract void fillBtnPanel();
    public abstract void visibleWindowAdd(ActionEvent actionEvent);
    public void visibleWindowDel(ActionEvent actionEvent){};

    public void fillOptionsPanel(){}
    public void initNamePerson(ActionEvent actionEvent){}
    public void initSelectedRow(ListSelectionEvent selectionEvent){}
    public void visibleDataTable(ActionEvent actionEvent){}

    private void removeDataTable(ActionEvent actionEvent) {
        for (int i = 0; i < getPanelDirectory().getComponents().length; i++) {
            if(getPanelDirectory().getComponents()[i].equals(dataScrollPane)){
                getPanelDirectory().remove(dataScrollPane);
            }
        }
        getPanelDirectory().updateUI();
    }

    void addActionListenerForListPerson(){
        listPersons.addActionListener(this::initNamePerson);
        listPersons.addActionListener(this::removeDataTable);
    }

    void addActionListenerForBtnConfirm(){
        btnConfirm.addActionListener(this::visibleDataTable);
    }

    void addActionListenerForBtnAdd(){
        btnAdd.addActionListener(this::visibleWindowAdd);
    }

    void addActionListenerForBtnDel(){
        btnDelete.addActionListener(this::visibleWindowDel);
    }


    public String getTabName() {
        return tabName;
    }

    void setTabName(String tabName) {
        this.tabName = tabName;
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

    GridBagLayout getGBL() {
        return GBL;
    }

    ConfigurationGBL getCGBL() {
        return CGBL;
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

    static ProcessingPersonTable getPPersonT() {
        return PPersonT;
    }

    ProcessingKeyWordsTable getPKeyWordsT() {
        return PKeyWordsT;
    }

    ProcessingSitesTable getPSitesT() {
        return PSitesT;
    }

    String[] getColumnNames() {
        return columnNames;
    }
}
