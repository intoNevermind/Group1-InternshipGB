package windowGUI.component.workDirectory;

import windowGUI.component.editingDirectoryWindow.AddSiteWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SitesDirectory extends Directory{
    private static final String TAB_NAME = "Сайты";

    public SitesDirectory() {
        setTabName(TAB_NAME);

        fillBtnPanel();
        addActionListenerForBtnAdd();

        dataTable = new JTable(getPSitesT().getArrayFillTable(getColumnNames().length), getColumnNames());
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
    public void visibleDataTable(ActionEvent actionEvent) {

    }

    @Override
    public void visibleWindowAdd(ActionEvent actionEvent) {
        new AddSiteWindow(getBtnAdd().getText() + " новую личность");
    }
}
