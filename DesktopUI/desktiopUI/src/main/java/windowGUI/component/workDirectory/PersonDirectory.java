package windowGUI.component.workDirectory;

import javax.swing.*;
import java.awt.*;

public class PersonDirectory extends Directory{
    private static final String TAB_NAME = "Личности";

    PersonDirectory() {
        setTabName(TAB_NAME);

        fillBtnPanel();

        columnNames = new String[]{"Наименование"};
        String[][] arr = {{"Путин"},{"Навальный"},{"Собчак"}};

        dataTable = new JTable(arr, columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelDirectory().add(dataScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void fillBtnPanel() {
        getBtnPanel().add(getBtnAdd());
        getBtnPanel().add(getBtnEdit());
        getBtnPanel().add(getBtnDelete());
    }

}
