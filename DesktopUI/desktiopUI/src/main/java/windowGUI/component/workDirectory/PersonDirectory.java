package windowGUI.component.workDirectory;

import windowGUI.component.editingDirectoryWindow.AddPersonWindow;
import windowGUI.component.editingDirectoryWindow.DelPersonWindow;
import windowGUI.component.editingDirectoryWindow.DelSiteWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PersonDirectory extends Directory{
    private static final String TAB_NAME = "Личности";
    private static String namePerson ;

    public PersonDirectory() {
        setTabName(TAB_NAME);

        fillBtnPanel();
        addActionListenerForBtnAdd();
        addActionListenerForBtnDel();

        dataTable = new JTable(getPPersonT().getArrayFillTable(getColumnNames().length), getColumnNames());
        dataTable.getSelectionModel().addListSelectionListener(this::initSelectedRow);
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

    @Override
    public void initSelectedRow(ListSelectionEvent selectionEvent){
        TableModel model = dataTable.getModel();
        Object value = model.getValueAt(dataTable.getSelectedRow(), 0);
        namePerson = (String) value;

    }

    @Override
    public void visibleWindowDel(ActionEvent actionEvent) {
        if(namePerson == null ){
            JOptionPane.showMessageDialog(null, "Для удаления личности необходимо выбрать личность из списка");
        }else {
            new DelPersonWindow(getBtnDelete().getText() + " личность ", namePerson, getPPersonT().getIDPersonByNamePerson(namePerson));
        }
    }
}
