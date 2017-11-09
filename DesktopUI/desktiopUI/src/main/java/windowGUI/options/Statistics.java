package windowGUI.options;

import windowGUI.MyCalendar;
import windowGUI.options.workSQL.ProcessingPersonPageRankTable;
import windowGUI.options.workSQL.ProcessingSitesTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class Statistics {
    private String tabName ;

    private final GridBagLayout GBL = new GridBagLayout();

    private JPanel panelStat = new JPanel();
    private JPanel optionsPanel = new JPanel();

    private final JLabel headlineSite = new JLabel(" Сайты: ");
    private final JButton btnConfirm = new JButton(" Подтвердить");

    private final ProcessingPersonPageRankTable PPersonPageRankT = new ProcessingPersonPageRankTable();

    private final ProcessingSitesTable PSitesT = new ProcessingSitesTable();
    private final JComboBox<Object> listSite = new JComboBox<>(PSitesT.getColumnName());
    String[] columnNames;
    JTable dataTable;
    JScrollPane dataScrollPane;

    private int numberStr = 0;

    Statistics() {
        panelStat.setLayout(new BorderLayout());
        optionsPanel.setLayout(GBL);
        panelStat.add(optionsPanel, BorderLayout.NORTH);
    }

    public abstract void fillOptionsPanel();
    public abstract void initNameSites(ActionEvent actionEvent);
    public abstract void listenerVisibleDataTable(ActionEvent actionEvent);

    void addActionListenerForListSiteAndBtnConfirm(){
       listSite.addActionListener(this::initNameSites);
       listSite.addActionListener(this::listenerRemoveDataTable);
       btnConfirm.addActionListener(this::listenerVisibleDataTable);
    }

    void listenerRemoveDataTable(ActionEvent actionEvent) {
        for (int i = 0; i < getPanelStat().getComponents().length; i++) {
            if(getPanelStat().getComponents()[i].equals(dataScrollPane)){
                getPanelStat().remove(dataScrollPane);
            }
        }
        getPanelStat().updateUI();
    }

    GridBagConstraints configGBC(Component component, boolean moveToNewLine){
        GridBagConstraints gbc =  new GridBagConstraints();
        if(component instanceof JLabel){
            if(moveToNewLine) numberStr++;
            gbc.gridy = numberStr;
            gbc.gridwidth  = 1;
            gbc.anchor = GridBagConstraints.EAST;
            return gbc;
        }
        if(component instanceof JComboBox || component instanceof MyCalendar){
            if(moveToNewLine) numberStr++;
            gbc.gridy = numberStr;
            gbc.gridwidth  = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            return gbc;
        }
        if(component instanceof JButton){
            if(moveToNewLine) numberStr++;
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

    GridBagLayout getGBL() {
        return GBL;
    }

    public JPanel getPanelStat() {
        return panelStat;
    }

    JPanel getOptionsPanel() {
        return optionsPanel;
    }

    JLabel getHeadlineSite() {
        return headlineSite;
    }

    JButton getBtnConfirm() {
        return btnConfirm;
    }

    ProcessingPersonPageRankTable getPPersonPageRankT() {
        return PPersonPageRankT;
    }

    JComboBox<Object> getListSite() {
        return listSite;
    }

}
