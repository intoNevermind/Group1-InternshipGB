package windowGUI.component.workDirectory;

import windowGUI.component.editingDirectoryWindow.AddPersonWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PersonDirectory extends Directory{
    private static final String TAB_NAME = "Личности";

    public PersonDirectory() {
        setTabName(TAB_NAME);

        fillBtnPanel();
        addActionListenerForBtnAdd();

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
    public void visibleWindowAdd(ActionEvent actionEvent) {
        new AddPersonWindow(getBtnAdd().getText() + " новую личность");
    }
}
