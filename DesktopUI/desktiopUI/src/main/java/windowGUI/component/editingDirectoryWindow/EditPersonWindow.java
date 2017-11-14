package windowGUI.component.editingDirectoryWindow;

import windowGUI.component.workDB.tables.PersonTable;
import windowGUI.component.workDirectory.PersonDirectory;

import java.awt.event.ActionEvent;

public class EditPersonWindow extends EditingDirectoryWindow {
    private static final PersonDirectory PERSON_DIRECTORY = new PersonDirectory();
    private static final PersonTable TABLE_PERSON = new PersonTable();

    public EditPersonWindow(String windowTitle, String personName, int personID, boolean personActive) {
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {

    }
}
