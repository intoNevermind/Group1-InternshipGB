package windowGUI.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.KeyWordsTable;
import windowGUI.component.workDirectory.KeyWordsDirectory;

import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.REMAINDER;
import static java.awt.GridBagConstraints.WEST;

public class EditKeyWordWindow extends EditingDirectoryWindow {
    private static final KeyWordsDirectory KEY_WORDS_DIRECTORY = new KeyWordsDirectory();
    private static final KeyWordsTable TABLE_KEY_WORDS = KeyWordsTable.getInstance();
    private String keyWordName;
    private int keyWordID;
    private int personID;

    public EditKeyWordWindow(String windowTitle, String keyWordName, int keyWordID, int personID) {
        this.keyWordName = keyWordName;
        this.keyWordID = keyWordID;
        this.personID = personID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        fillEditPanels();
        getWindow().add(getTextFieldPanel(), BorderLayout.CENTER);
    }

    @Override
    public void fillEditPanels() {
        getGBL().setConstraints(getHeadLineTextFieldName(), getCGBL().configGBCTest(WEST,1,false));
        getTextFieldPanel().add(getHeadLineTextFieldName());

        getValueEntryFieldName().setText(keyWordName);
        getGBL().setConstraints(getValueEntryFieldName(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getValueEntryFieldName());

        getGBL().setConstraints(getBtnSave(), getCGBL().configGBCTest(1,true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configGBCTest(1,false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        if(getValueEntryFieldName().getText() != null ){
            TABLE_KEY_WORDS.modifyKeyWordReal(keyWordID,keyWordName,personID);
        }
        KEY_WORDS_DIRECTORY.getPanelDirectory().updateUI();
        getValueEntryFieldName().setText(null);
        getWindow().dispose();
    }
}
