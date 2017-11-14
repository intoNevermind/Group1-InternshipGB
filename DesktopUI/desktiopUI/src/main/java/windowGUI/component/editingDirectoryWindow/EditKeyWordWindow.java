package windowGUI.component.editingDirectoryWindow;

import windowGUI.component.workDB.tables.KeyWordsTable;
import windowGUI.component.workDirectory.KeyWordsDirectory;

import java.awt.event.ActionEvent;

public class EditKeyWordWindow extends EditingDirectoryWindow {

    private static final KeyWordsDirectory KEY_WORDS_DIRECTORY = new KeyWordsDirectory();
    private static final KeyWordsTable TABLE_KEY_WORDS = new KeyWordsTable();

    public EditKeyWordWindow(String windowTitle, String keyWordName, int keyWordID, int personID) {
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {

    }
}
