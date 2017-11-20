package windowGUI.editingDirectoryWindow.delete;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.KeyWordsTable;
import windowGUI.component.workDirectory.KeyWordsDirectory;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.event.ActionEvent;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность удаления ключевых слов
 * */
public class DelKeyWordWindow extends EditingDirectoryWindow {
    private static final KeyWordsDirectory KEY_WORDS_DIRECTORY = new KeyWordsDirectory();

    private int keyWordID;

    public DelKeyWordWindow(String windowTitle,String nameKeyWord, int keyWordID) {
        this.keyWordID = keyWordID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());
        System.out.println("Конструктор Удаления ключевого слова");
        KeyWordsTable.infoAllKeyWords();

        fillDelPanels(nameKeyWord);
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        KeyWordsTable.delKeyWord(keyWordID);
        KEY_WORDS_DIRECTORY.getPanelDirectory().updateUI();
        getWindow().dispose();
    }
}
