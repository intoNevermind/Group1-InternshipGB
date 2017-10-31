package windowGUI.options;

import windowGUI.Calendar;

import javax.swing.*;
import java.awt.*;

public class Statistics {
    public String tabName ;
    public JPanel panelStat = new JPanel();
    public JPanel optionsPanel = new JPanel();
    public JTable dataTable;
    public JScrollPane dataScrollPane;
    public Object[][] data;
    public String[] columnNames;
    private int numberStr = 0;


    public Statistics() {
        panelStat.setLayout(new BorderLayout());
    }

    public void fillList(){
        String[] list = new String[10];
        for (int i = 0; i < 10; i++) {
            list[i] = "Элемент: " + (i + 1);
        }
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public void fillOptionsPanel(){}

    public GridBagConstraints configGBC(Component component, boolean moveToNewLine){
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
}
