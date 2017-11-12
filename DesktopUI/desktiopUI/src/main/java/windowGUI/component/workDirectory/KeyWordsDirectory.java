package windowGUI.component.workDirectory;

import windowGUI.component.editingDirectoryWindow.AddKeyWordWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class KeyWordsDirectory extends Directory{
    private static final String TAB_NAME = "Ключевые слова";

    private static String namePerson;

    KeyWordsDirectory() {
        setTabName(TAB_NAME);

        fillOptionsPanel();
        fillBtnPanel();

        addActionListenerForListPerson();
        addActionListenerForBtnConfirm();
        addActionListenerForBtnAdd();
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getHeadLinePerson(),getCGBL().configGBC(getHeadLinePerson(),false));
        getOptionsPanel().add(getHeadLinePerson());
        getGBL().setConstraints(getListPersons(), getCGBL().configGBC(getListPersons(),false));
        getOptionsPanel().add(getListPersons());
        getGBL().setConstraints(getBtnConfirm(), getCGBL().configGBC(getBtnConfirm(),true));
        getOptionsPanel().add(getBtnConfirm());
    }

    @Override
    public void fillBtnPanel() {
        getBtnPanel().add(getBtnAdd());
        getBtnPanel().add(getBtnEdit());
        getBtnPanel().add(getBtnDelete());
    }

    @Override
    public void initNamePerson(ActionEvent actionEvent) {
        JComboBox box = (JComboBox)actionEvent.getSource();
        namePerson = (String)box.getSelectedItem();
    }

    @Override
    public void visibleDataTable(ActionEvent actionEvent){
        if(namePerson == null || namePerson.equals("Не выбранно")){
            JOptionPane.showMessageDialog(null, "Для просмотра ключевых слов необходимо выбрать \""  + getHeadLinePerson().getText() + "\" ");
        }

        dataTable = new JTable(getPKeyWordsT().getArrayFillTable(namePerson, getColumnNames().length), getColumnNames());
        dataScrollPane = new JScrollPane(dataTable);
        getPanelDirectory().add(dataScrollPane, BorderLayout.CENTER);
        dataScrollPane.setVisible(true);
        getPanelDirectory().updateUI();
    }

    @Override
    public void visibleWindowAdd(ActionEvent actionEvent){
        if(namePerson == null || namePerson.equals("Не выбранно")){
            JOptionPane.showMessageDialog(null, "Для добавления ключевых слов необходимо выбрать \""  + getHeadLinePerson().getText() + "\" ");
        }else {
            new AddKeyWordWindow(getBtnAdd().getText() + " новое ключевое слово для личности: " + namePerson, getPPersonT().getIDPersonByNamePerson(namePerson));
        }
    }

}
