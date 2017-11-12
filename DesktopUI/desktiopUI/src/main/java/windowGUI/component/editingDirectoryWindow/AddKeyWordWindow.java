package windowGUI.component.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.KeyWordsTable;
import java.awt.event.ActionEvent;



public class AddKeyWordWindow extends EditingDirectoryWindow{
    private static final KeyWordsTable TABLE_KEY_WORDS = new KeyWordsTable();
    private int personID;

    public AddKeyWordWindow(String windowTitle, int personID) {
        this.personID = personID;
        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        fillPanels();
        fillWindow();
        addListener();
    }

    @Override
    public void fillPanels() {
        getGBL().setConstraints(getHeadLineTextField(), configGBC(getHeadLineTextField(),false));
        getTextFieldPanel().add(getHeadLineTextField());
        getGBL().setConstraints(getValueEntryField(), configGBC(getValueEntryField(),true));
        getTextFieldPanel().add(getValueEntryField());

        getGBL().setConstraints(getBtnSave(), configGBC(getBtnSave(),true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), configGBC(getBtnCancel(),false));
        getBtnPanel().add(getBtnCancel());
    }
    @Override
    public void getValueField(ActionEvent actionEvent) {
        if(getValueEntryField().getText() != null){
            TABLE_KEY_WORDS.addKeyWord(getValueEntryField().getText(), personID);
        }
        getValueEntryField().setText(null);
        getWindow().dispose();
    }


}
