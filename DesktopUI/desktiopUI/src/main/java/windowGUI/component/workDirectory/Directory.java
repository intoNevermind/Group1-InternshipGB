package windowGUI.component;

import windowGUI.component.workDB.ProcessingData.ProcessingPersonTable;
import javax.swing.*;
import java.awt.*;

public abstract class Directory {
    private String tabName ;

    private final JPanel panelDirectory = new JPanel();
    private final JPanel optionsPanel = new JPanel();
    private final JPanel btnPanel = new JPanel();

    private final GridBagLayout GBL = new GridBagLayout();
    private final ConfigurationGBL CGBL = new ConfigurationGBL();
    private final JLabel headLinePerson = new JLabel("Личности");

    private static final ProcessingPersonTable PPersonT = new ProcessingPersonTable();
    private final JComboBox<String> listPerson = new JComboBox<>(PPersonT.getColumnName());

    private final JButton btnConfirm = new JButton("Подтвердить");
    private final JButton btnAdd = new JButton("Добавить");
    private final JButton btnDelete = new JButton("Удалить");
    private final JButton btnEdit = new JButton("Редактировать");

    String[] columnNames;
    JTable dataTable;
    JScrollPane dataScrollPane;

    public Directory() {
        panelDirectory.setLayout(new BorderLayout());
        panelDirectory.setPreferredSize(new Dimension(400, 300));
        optionsPanel.setLayout(GBL);
        btnPanel.setLayout(new FlowLayout());
        panelDirectory.add(optionsPanel, BorderLayout.NORTH);
        panelDirectory.add(btnPanel,BorderLayout.SOUTH);
    }

    public abstract void fillBtnPanel();

    public void fillOptionsPanel(){}


    public String getTabName() {
        return tabName;
    }

    void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public JPanel getPanelDirectory() {
        return panelDirectory;
    }

    public JPanel getOptionsPanel() {
        return optionsPanel;
    }

    public JPanel getBtnPanel() {
        return btnPanel;
    }

    public GridBagLayout getGBL() {
        return GBL;
    }

    public ConfigurationGBL getCGBL() {
        return CGBL;
    }

    public JLabel getHeadLinePerson() {
        return headLinePerson;
    }

    public JComboBox<String> getListPerson() {
        return listPerson;
    }

    public JButton getBtnConfirm() {
        return btnConfirm;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }
}
