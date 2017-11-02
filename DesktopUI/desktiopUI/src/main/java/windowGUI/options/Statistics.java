package windowGUI.options;

import windowGUI.Calendar;

import javax.swing.*;
import java.awt.*;

public abstract class Statistics {
    private String tabName ;
    private JPanel panelStat = new JPanel();
    private JPanel optionsPanel = new JPanel();
    JTable dataTable;
    JScrollPane dataScrollPane;
    Object[][] data;
    String[] columnNames;
    private int numberStr = 0;

    Statistics() {
        panelStat.setLayout(new BorderLayout());
    }

    public abstract void fillOptionsPanel();

    GridBagConstraints configGBC(Component component, boolean moveToNewLine){
        GridBagConstraints gbc =  new GridBagConstraints();
        if(component instanceof JLabel){
            if(moveToNewLine){
                numberStr++;
            }
            gbc.gridy = numberStr;
            gbc.gridwidth  = 1;
            gbc.anchor = GridBagConstraints.EAST;
            return gbc;
        }
        if(component instanceof JComboBox || component instanceof Calendar){
            if(moveToNewLine){
                numberStr++;
            }
            gbc.gridy = numberStr;
            gbc.gridwidth  = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            return gbc;
        }
        if(component instanceof JButton){
            if(moveToNewLine){
                numberStr++;
            }
            gbc.gridy = numberStr;
            gbc.gridwidth  = GridBagConstraints.REMAINDER ;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            return gbc;
        }
        return gbc;
    }

    void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }

    public JPanel getPanelStat() {
        return panelStat;
    }

    JPanel getOptionsPanel() {
        return optionsPanel;
    }
}
