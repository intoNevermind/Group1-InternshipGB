package windowGUI.component.workDirectory;

import windowGUI.component.editingDirectoryWindow.AddSiteWindow;
import windowGUI.component.editingDirectoryWindow.DelSiteWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SitesDirectory extends Directory{
    private static final String TAB_NAME = "Сайты";
    private static String nameSites ;

    public SitesDirectory() {
        setTabName(TAB_NAME);

        fillBtnPanel();
        addActionListenerForBtnAdd();
        addActionListenerForBtnDel();

        dataTable = new JTable(getPSitesT().getArrayFillTable(getColumnNames().length), getColumnNames());
        dataTable.getSelectionModel().addListSelectionListener(this::initNameSites);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelDirectory().add(dataScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void fillBtnPanel() {
        getBtnPanel().add(getBtnAdd());
        getBtnPanel().add(getBtnEdit());
        getBtnPanel().add(getBtnDelete());
    }


    @Override
    public void visibleWindowAdd(ActionEvent actionEvent) {
        new AddSiteWindow(getBtnAdd().getText() + " новую личность");
    }

    @Override
    public void initNameSites(ListSelectionEvent selectionEvent){
        TableModel model = dataTable.getModel();
        Object value = model.getValueAt(dataTable.getSelectedRow(), 0);
        nameSites = (String) value;

    }

    @Override
    public void visibleWindowDel(ActionEvent actionEvent) {
        if(nameSites == null ){
            JOptionPane.showMessageDialog(null, "Для удаления сайта необходимо выбрать \""  + getHeadLinePerson().getText() + "\" ");
        }else {
            new DelSiteWindow(getBtnAdd().getText() + " личность", nameSites, getPSitesT().getIDSitesByNameSites(nameSites));
        }
    }
}
