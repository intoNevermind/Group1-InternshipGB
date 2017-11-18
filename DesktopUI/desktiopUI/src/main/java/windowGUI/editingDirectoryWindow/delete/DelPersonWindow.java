package windowGUI.editingDirectoryWindow.delete;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.PersonsTable;
import windowGUI.component.workDirectory.PersonsDirectory;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.event.ActionEvent;

public class DelPersonWindow extends EditingDirectoryWindow {

    private static final PersonsDirectory PERSON_DIRECTORY = new PersonsDirectory();
    private static final PersonsTable TABLE_PERSON = PersonsTable.getInstance();
    private int personID;

    public DelPersonWindow(String windowTitle,String personName, int personID) {
        this.personID = personID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        fillDelPanels(personName);
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        TABLE_PERSON.delPerson(personID);
        PERSON_DIRECTORY.getPanelDirectory().updateUI();
        getWindow().dispose();
    }
}
