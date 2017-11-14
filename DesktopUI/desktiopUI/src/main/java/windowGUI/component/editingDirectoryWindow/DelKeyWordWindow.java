package windowGUI.component.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.KeyWordsTable;
import windowGUI.component.workDirectory.KeyWordsDirectory;


import java.awt.event.ActionEvent;

public class DelKeyWordWindow extends EditingDirectoryWindow{

    private static final KeyWordsDirectory KEY_WORDS_DIRECTORY = new KeyWordsDirectory();
    private static final KeyWordsTable TABLE_KEY_WORDS = new KeyWordsTable();
    private int keyWordID;

    public DelKeyWordWindow(String windowTitle,String keyWordName, int keyWordID) {
        this.keyWordID = keyWordID;
        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());
        fillDelPanels(keyWordName);
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        TABLE_KEY_WORDS.delKeyWord(keyWordID);
        KEY_WORDS_DIRECTORY.getPanelDirectory().updateUI();
        getWindow().dispose();
    }
}
