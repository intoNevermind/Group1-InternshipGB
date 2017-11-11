package windowGUI.component.workDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PersonDirectory extends Directory{
    private static final String TAB_NAME = "Личности";

    PersonDirectory() {
        setTabName(TAB_NAME);

        fillBtnPanel();

        dataTable = new JTable(getPPersonT().getArrayFillTable(getColumnNames().length), getColumnNames());
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
}
