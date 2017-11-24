package windowGUI.editingDirectoryWindow.edit;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.PersonsTable;
import windowGUI.component.workDirectory.KeyWordsDirectory;
import windowGUI.component.workStatistics.DailyStatistic;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.REMAINDER;
import static java.awt.GridBagConstraints.WEST;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность редактирования личностей
 * */
public class EditPersonWindow extends EditingDirectoryWindow {

    private String namePerson;
    private boolean activePerson;
    private int personID;

    public EditPersonWindow(String windowTitle, String namePerson, int personID, boolean activePerson) {
        this.namePerson = namePerson;
        this.personID = personID;
        this.activePerson = activePerson;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        PersonsTable.infoAllPersons();

        fillEditPanels();
        getWindow().add(getTextFieldPanel(), BorderLayout.CENTER);
    }

    @Override
    public void fillEditPanels() {
        getGBL().setConstraints(getHeadLineName(), getCGBL().configGBC(WEST,1,false));
        getTextFieldPanel().add(getHeadLineName());
        getNameField().setText(namePerson);
        getGBL().setConstraints(getNameField(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getNameField());

        getActive().setSelected(activePerson);
        getGBL().setConstraints(getActive(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getActive());

        getGBL().setConstraints(getBtnSave(), getCGBL().configGBC(1,true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configGBC(1,false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        if(getNameField().getText() != null) {
            PersonsTable.modifyPerson(personID, getNameField().getText(),getActive().isSelected());
            if (getActive().isSelected()){
                KeyWordsDirectory.LIST_BEFORE_NAME_PERSONS.add(namePerson);
                KeyWordsDirectory.LIST_AFTER_NAME_PERSONS.add(getNameField().getText());
                DailyStatistic.listBeforeNamePersons.add(namePerson);
                DailyStatistic.listAfterNamePersons.add(getNameField().getText());
            }
        }



        getNameField().setText(null);
        getWindow().dispose();
    }
}
