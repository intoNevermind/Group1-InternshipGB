package windowGUI.options;

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

}
