package windowGUI.editingDirectoryWindow.edit;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.KeyWordsTable;
import windowGUI.component.workDirectory.KeyWordsDirectory;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.REMAINDER;
import static java.awt.GridBagConstraints.WEST;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность редактирования ключевых слов
 * */
public class EditKeyWordWindow extends EditingDirectoryWindow {
    private static final KeyWordsDirectory KEY_WORDS_DIRECTORY = new KeyWordsDirectory();
    private static final KeyWordsTable TABLE_KEY_WORDS = KeyWordsTable.getInstance();
    private String nameKeyWords;
    private int keyWordID;
    private int personID;

    public EditKeyWordWindow(String windowTitle, String nameKeyWords, int keyWordID, int personID) {
        this.nameKeyWords = nameKeyWords;
        this.keyWordID = keyWordID;
        this.personID = personID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        fillEditPanels();
        getWindow().add(getTextFieldPanel(), BorderLayout.CENTER);
    }

    @Override
    public void fillEditPanels() {
        getGBL().setConstraints(getHeadLineName(), getCGBL().configGBC(WEST,1,false));
        getTextFieldPanel().add(getHeadLineName());

        getNameField().setText(nameKeyWords);
        getGBL().setConstraints(getNameField(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getNameField());

        getGBL().setConstraints(getBtnSave(), getCGBL().configGBC(1,true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configGBC(1,false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        if(getNameField().getText() != null ) TABLE_KEY_WORDS.modifyKeyWord(keyWordID, nameKeyWords,personID);

        KEY_WORDS_DIRECTORY.getPanelDirectory().updateUI();
        getWindow().dispose();
    }
}
