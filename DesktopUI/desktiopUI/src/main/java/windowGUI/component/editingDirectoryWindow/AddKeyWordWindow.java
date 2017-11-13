package windowGUI.component.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.KeyWordsTable;
import windowGUI.component.workDirectory.KeyWordsDirectory;

import java.awt.event.ActionEvent;



public class AddKeyWordWindow extends EditingDirectoryWindow{

    private static final KeyWordsDirectory KEY_WORDS_DIRECTORY = new KeyWordsDirectory();

    private static final KeyWordsTable TABLE_KEY_WORDS = new KeyWordsTable();
    private int personID;

    public AddKeyWordWindow(String windowTitle, int personID) {
        this.personID = personID;
        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        fillPanels();
        fillWindow();
        addBtnListener();
    }

    @Override
    public void fillPanels() {
        getGBL().setConstraints(getHeadLineTextFieldName(), getCGBL().configSubsidiaryGBC(getHeadLineTextFieldName(),false));
        getTextFieldPanel().add(getHeadLineTextFieldName());
        getGBL().setConstraints(getValueEntryFieldName(), getCGBL().configSubsidiaryGBC(getValueEntryFieldName(),true));
        getTextFieldPanel().add(getValueEntryFieldName());

        getGBL().setConstraints(getBtnSave(), getCGBL().configSubsidiaryGBC(getBtnSave(),true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configSubsidiaryGBC(getBtnCancel(),false));
        getBtnPanel().add(getBtnCancel());
    }
    @Override
    public void saveEditing(ActionEvent actionEvent) {
        if(getValueEntryFieldName().getText() != null){
            TABLE_KEY_WORDS.addKeyWord(getValueEntryFieldName().getText(), personID);
        }
        KEY_WORDS_DIRECTORY.getPanelDirectory().updateUI();
        getValueEntryFieldName().setText(null);
        getWindow().dispose();
    }

}
