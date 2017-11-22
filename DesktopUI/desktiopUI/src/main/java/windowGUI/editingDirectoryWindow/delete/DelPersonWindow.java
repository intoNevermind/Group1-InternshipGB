package windowGUI.editingDirectoryWindow.delete;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.PersonsTable;
import windowGUI.component.workDirectory.PersonsDirectory;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.event.ActionEvent;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность удаления личностей
 * */
public class DelPersonWindow extends EditingDirectoryWindow {

    private static final PersonsDirectory PERSON_DIRECTORY = new PersonsDirectory();

    private int personID;

    public DelPersonWindow(String windowTitle,String namePerson, int personID) {
        this.personID = personID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        PersonsTable.infoAllPersons();

        fillDelPanels(namePerson);
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        PersonsTable.delPerson(personID);
        PERSON_DIRECTORY.visibleDataTable(actionEvent);
        getWindow().dispose();
    }
}
