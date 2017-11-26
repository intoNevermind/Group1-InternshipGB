package windowGUI.component.workWithDirectory;

import windowGUI.component.ListPerson;
import windowGUI.component.ChangeItemsJComboBox;
import windowGUI.component.workWithDB.processingData.ProcessingData;
import windowGUI.editingDirectoryWindow.add.AddKeyWordWindow;
import windowGUI.editingDirectoryWindow.delete.DelKeyWordWindow;
import windowGUI.editingDirectoryWindow.edit.EditKeyWordWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static java.awt.GridBagConstraints.*;
/*
 * Класс-справочник, отвечающий за функциональную деятельность справочника KeyWords
 * */
public class KeyWordsDirectory extends Directory implements ListPerson{
    private static final String NAME_TAB = "Ключевые слова";
    private static final String[] NAME_COLUMNS = new String[]{"Наименование"};

    private static String namePerson;
    private static String nameKeyWord ;


    public static final ArrayList<String> LIST_ADD_NAME_PERSONS = new ArrayList<>();
    public static final ArrayList<String> LIST_DEL_NAME_PERSONS = new ArrayList<>();
    public static final ArrayList<String> LIST_BEFORE_NAME_PERSONS = new ArrayList<>();
    public static final ArrayList<String> LIST_AFTER_NAME_PERSONS = new ArrayList<>();

    private static final ChangeItemsJComboBox CHANGE_ITEMS_J_COMBO_BOX = new ChangeItemsJComboBox();

    KeyWordsDirectory() {
        setNameTab(NAME_TAB);

        addActionListenerForListPerson();
        addActionListenerForBtnConfirm();
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
    public void addActionListenerForListPerson() {
        getListPersons().addActionListener(this::initNamePerson);
    }

    /*
     * метод, добавляющий листенер на кнопку "Продолжить"
     * */
    private void addActionListenerForBtnConfirm() {
        getBtnConfirm().addActionListener(this::visibleDataTable);
    }

    @Override
    public void initNamePerson(ActionEvent actionEvent) {
        JComboBox box = (JComboBox)actionEvent.getSource();
        namePerson = (String)box.getSelectedItem();
    }

    @Override
    public void initDataTable(){
        dataTable = new JTable(getPKeyWordsT().getArrayFillTable(namePerson, NAME_COLUMNS.length), NAME_COLUMNS);
        super.addDataTable();
    }

    @Override
    public void initSelectedRow(ListSelectionEvent selectionEvent){
        TableModel model = dataTable.getModel();
        Object value = model.getValueAt(dataTable.getSelectedRow(), 0);
        nameKeyWord = (String)value;
    }

    /*
     * метод, показывающий таблицу с данными
     * */
    private void visibleDataTable(ActionEvent actionEvent){
        if(namePerson == null || namePerson.equals(ProcessingData.getNotChosen())){
            JOptionPane.showMessageDialog(null,
                    "Для просмотра ключевых слов необходимо выбрать \""  + getHeadLinePerson().getText() + "\" ",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }
        refreshAll(actionEvent);
    }

    @Override
    public void visibleWindowAdd(ActionEvent actionEvent){
        if(namePerson == null || namePerson.equals("Не выбранно")){
            JOptionPane.showMessageDialog(null,
                    "Для добавления ключевых слов необходимо выбрать \""  + getHeadLinePerson().getText() + "\" ",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
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
        }else {
            new DelKeyWordWindow(getBtnDelete().getText() + " ключевое слово ",
                    nameKeyWord,
                    getPKeyWordsT().getIDKeyWordByNameKeyWord(nameKeyWord));
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
        }else {
            new EditKeyWordWindow(getBtnEdit().getText() + " ключевое слово ",
                    nameKeyWord,
                    getPKeyWordsT().getIDKeyWordByNameKeyWord(nameKeyWord),
                    getPPersonT().getIDPersonByNamePerson(namePerson));
            nameKeyWord = null;
        }
    }

    @Override
    public void refreshAll(ActionEvent actionEvent) {
        refreshDataTable();

        CHANGE_ITEMS_J_COMBO_BOX.refreshList(LIST_ADD_NAME_PERSONS,
                LIST_DEL_NAME_PERSONS,
                LIST_BEFORE_NAME_PERSONS,
                LIST_AFTER_NAME_PERSONS,
                getListPersons());
    }
}
