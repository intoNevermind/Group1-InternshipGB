package windowGUI.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.PersonTable;
import windowGUI.component.workDirectory.PersonDirectory;

import java.awt.event.ActionEvent;

public class DelPersonWindow extends EditingDirectoryWindow {

    private static final PersonDirectory PERSON_DIRECTORY = new PersonDirectory();
    private static final PersonTable TABLE_PERSON = PersonTable.getInstance();
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
