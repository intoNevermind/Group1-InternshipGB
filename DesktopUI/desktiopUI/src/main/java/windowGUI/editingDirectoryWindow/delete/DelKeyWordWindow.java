package windowGUI.editingDirectoryWindow.delete;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.KeyWordsTable;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.event.ActionEvent;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность удаления ключевых слов
 * */
public class DelKeyWordWindow extends EditingDirectoryWindow {

    private int keyWordID;

    public DelKeyWordWindow(String windowTitle,String nameKeyWord, int keyWordID) {
        this.keyWordID = keyWordID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        KeyWordsTable.infoAllKeyWords();

        fillDelPanels(nameKeyWord);
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        KeyWordsTable.delKeyWord(keyWordID);
        getWindow().dispose();
    }
}
