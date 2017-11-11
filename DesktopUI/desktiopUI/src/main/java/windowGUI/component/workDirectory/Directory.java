package windowGUI.component.workDirectory;

import windowGUI.component.ConfigurationGBL;
import windowGUI.component.workDB.processingData.ProcessingPersonTable;
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

    Directory() {
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

    JComboBox<String> getListPerson() {
        return listPerson;
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
}
