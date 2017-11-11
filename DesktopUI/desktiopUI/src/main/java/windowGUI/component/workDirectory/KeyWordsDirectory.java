package windowGUI.component.workDirectory;

import javax.swing.*;
import java.awt.*;

public class KeyWordsDirectory extends Directory{
    private static final String TAB_NAME = "Ключевые слова";

    KeyWordsDirectory() {
        setTabName(TAB_NAME);

        fillOptionsPanel();
        fillBtnPanel();

        columnNames = new String[]{"Наименование"};
        String[][] arr = {{"Путин"},{"Путина"},{"Путину"}};

        dataTable = new JTable(arr, columnNames);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelDirectory().add(dataScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadLinePerson(),getCGBL().configGBC(getHeadLinePerson(),false));
        getOptionsPanel().add(getHeadLinePerson());
        getGBL().setConstraints(getListPerson(), getCGBL().configGBC(getListPerson(),false));
        getOptionsPanel().add(getListPerson());
        getGBL().setConstraints(getBtnConfirm(), getCGBL().configGBC(getBtnConfirm(),true));
        getOptionsPanel().add(getBtnConfirm());
    }

    @Override
    public void fillBtnPanel() {
        getBtnPanel().add(getBtnAdd());
        getBtnPanel().add(getBtnEdit());
        getBtnPanel().add(getBtnDelete());
    }
}
