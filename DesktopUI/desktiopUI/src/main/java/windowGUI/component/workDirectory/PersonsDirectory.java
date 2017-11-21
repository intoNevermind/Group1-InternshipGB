package windowGUI.component.workDirectory;

import windowGUI.editingDirectoryWindow.add.AddPersonWindow;
import windowGUI.editingDirectoryWindow.delete.DelPersonWindow;
import windowGUI.editingDirectoryWindow.edit.EditPersonWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.REMAINDER;

/*
 * Класс-справочник, отвечающий за функциональную деятельность справочника Persons
 * */
public class PersonsDirectory extends Directory{
    private static final String NAME_TAB = "Личности";

    private static String namePerson ;
    private JTable dataTable;
    private JScrollPane dataScrollPane;

    public PersonsDirectory() {
        setNameTab(NAME_TAB);
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getBtnRefresh(), getCGBL().configGBC(REMAINDER,false));
        getOptionsPanel().add(getBtnRefresh());
    }

    @Override
    public void initDataTable(){
        dataTable = new JTable(getPPersonT().getArrayFillTable(getColumnNames().length), getColumnNames());
        dataTable.getSelectionModel().addListSelectionListener(this::initSelectedRow);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelDirectory().add(dataScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void refreshDataTable(ActionEvent actionEvent) {
        removeDataTable(dataScrollPane);
        initDataTable();
    }

    @Override
    public void initSelectedRow(ListSelectionEvent selectionEvent){
        TableModel model = dataTable.getModel();
        Object value = model.getValueAt(dataTable.getSelectedRow(), 0);
        namePerson = (String) value;
    }

    @Override
    public void visibleWindowAdd(ActionEvent actionEvent) {
        new AddPersonWindow(getBtnAdd().getText() + " новую личность");
    }

    @Override
    public void visibleWindowDel(ActionEvent actionEvent) {
        if(namePerson == null ){
            JOptionPane.showMessageDialog(null,
                    "Для удаления личности необходимо выбрать личность из списка",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            new DelPersonWindow(getBtnDelete().getText() + " личность ",
                    namePerson, getPPersonT().getIDPersonByNamePerson(namePerson));
            namePerson = null;
        }
    }

    @Override
    public void visibleWindowEdit(ActionEvent actionEvent) {
        if(namePerson == null){
            JOptionPane.showMessageDialog(null,
                    "Для редактирования личности необходимо выбрать личность из списка",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            new EditPersonWindow(getBtnEdit().getText() + " личность ",
                    namePerson,
                    getPPersonT().getIDPersonByNamePerson(namePerson),
                    getPPersonT().getActivePersonByNamePerson(namePerson));
            namePerson = null;
        }
    }
}
