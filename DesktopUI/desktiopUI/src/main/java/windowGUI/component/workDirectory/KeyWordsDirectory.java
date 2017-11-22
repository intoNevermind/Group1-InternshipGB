package windowGUI.component.workDirectory;

import windowGUI.component.workDB.processingData.ProcessingData;
import windowGUI.editingDirectoryWindow.add.AddKeyWordWindow;
import windowGUI.editingDirectoryWindow.delete.DelKeyWordWindow;
import windowGUI.editingDirectoryWindow.edit.EditKeyWordWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.*;
/*
 * Класс-справочник, отвечающий за функциональную деятельность справочника KeyWords
 * */
public class KeyWordsDirectory extends Directory{
    private static final String NAME_TAB = "Ключевые слова";

    private static String namePerson;
    private static String nameKeyWord ;

    private JTable dataTable;
    private JScrollPane dataScrollPane;

    public KeyWordsDirectory() {
        setNameTab(NAME_TAB);
        addActionListenerForListPerson();
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadLinePerson(),getCGBL().configGBC(EAST,1,false));
        getPanelOptions().add(getHeadLinePerson());
        getGBL().setConstraints(getListPersons(), getCGBL().configGBC(2,false));
        getPanelOptions().add(getListPersons());

        getGBL().setConstraints(getBtnConfirm(), getCGBL().configGBC(2,true));
        getPanelOptions().add(getBtnConfirm());
        getGBL().setConstraints(getBtnRefresh(), getCGBL().configGBC(2,false));
        getPanelOptions().add(getBtnRefresh());
    }

    @Override
    public void initNamePerson(ActionEvent actionEvent) {
        JComboBox box = (JComboBox)actionEvent.getSource();
        namePerson = (String)box.getSelectedItem();
    }

    @Override
    public void initDataTable(){
        dataTable = new JTable(getPKeyWordsT().getArrayFillTable(namePerson, getNamesColumn().length), getNamesColumn());
        dataScrollPane = new JScrollPane(dataTable);
        getPanelDirectory().add(dataScrollPane, BorderLayout.CENTER);
        dataTable.getSelectionModel().addListSelectionListener(this::initSelectedRow);
        dataScrollPane.setVisible(true);
        getPanelDirectory().updateUI();
    }

    @Override
    public void initSelectedRow(ListSelectionEvent selectionEvent){
        TableModel model = dataTable.getModel();
        Object value = model.getValueAt(dataTable.getSelectedRow(), 0);
        nameKeyWord = (String) value;
    }

    @Override
    public void visibleDataTable(ActionEvent actionEvent){
        if(namePerson == null || namePerson.equals(ProcessingData.getNotChosen())){
            JOptionPane.showMessageDialog(null,
                    "Для просмотра ключевых слов необходимо выбрать \""  + getHeadLinePerson().getText() + "\" ",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }
        refreshDataTable(actionEvent);
    }

    @Override
    public void refreshDataTable(ActionEvent actionEvent) {
        removeDataTable(dataScrollPane);
        initDataTable();
    }

    @Override
    public void visibleWindowAdd(ActionEvent actionEvent){
        if(namePerson == null || namePerson.equals("Не выбранно")){
            JOptionPane.showMessageDialog(null,
                    "Для добавления ключевых слов необходимо выбрать \""  + getHeadLinePerson().getText() + "\" ",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else{
            new AddKeyWordWindow(getBtnAdd().getText() + " новое ключевое слово для личности: " + namePerson,
                    getPPersonT().getIDPersonByNamePerson(namePerson));
        }
    }

    @Override
    public void visibleWindowDel(ActionEvent actionEvent) {
        if(nameKeyWord == null ){
            JOptionPane.showMessageDialog(null,
                    "Для удаления ключевого слова необходимо выбрать ключевое слово из списка",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else{
            new DelKeyWordWindow(getBtnDelete().getText() + " ключевое слово ", nameKeyWord, getPKeyWordsT().getIDKeyWordByNameKeyWord(nameKeyWord));
            nameKeyWord = null;
        }
    }

    @Override
    public void visibleWindowEdit(ActionEvent actionEvent) {
        if(nameKeyWord == null){
            JOptionPane.showMessageDialog(null,
                    "Для редактирования ключевого слова необходимо выбрать ключевое слово из списка",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else{
            new EditKeyWordWindow(getBtnEdit().getText() + " ключевое слово ",
                    nameKeyWord,
                    getPKeyWordsT().getIDKeyWordByNameKeyWord(nameKeyWord),
                    getPPersonT().getIDPersonByNamePerson(namePerson));
            nameKeyWord = null;
        }
    }
}
