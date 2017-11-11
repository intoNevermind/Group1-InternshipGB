package windowGUI.component.workDirectory;

import javax.swing.*;
import java.awt.*;

public class SitesDirectory extends Directory{
    private static final String TAB_NAME = "Сайты";

    SitesDirectory() {
        setTabName(TAB_NAME);

        fillBtnPanel();

        columnNames = new String[]{"Наименование"};
        String[][] arr = {{"Лента.ру"},{"РБК"}};

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
