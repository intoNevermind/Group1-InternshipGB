package windowGUI.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.PersonTable;
import windowGUI.component.workDirectory.PersonDirectory;

import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.REMAINDER;
import static java.awt.GridBagConstraints.WEST;

public class EditPersonWindow extends EditingDirectoryWindow {
    private static final PersonDirectory PERSON_DIRECTORY = new PersonDirectory();
    private static final PersonTable TABLE_PERSON = PersonTable.getInstance();
    private String personName;
    private boolean personActive;
    private int personID;

    public EditPersonWindow(String windowTitle, String personName, int personID, boolean personActive) {
        this.personName = personName;
        this.personID = personID;
        this.personActive = personActive;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        fillEditPanels();
        getWindow().add(getTextFieldPanel(), BorderLayout.CENTER);
    }

    @Override
    public void fillEditPanels() {
        getGBL().setConstraints(getHeadLineTextFieldName(), getCGBL().configGBCTest(WEST,1,false));
        getTextFieldPanel().add(getHeadLineTextFieldName());
        getValueEntryFieldName().setText(personName);
        getGBL().setConstraints(getValueEntryFieldName(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getValueEntryFieldName());

        getActive().setSelected(personActive);
        getGBL().setConstraints(getActive(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getActive());

        getGBL().setConstraints(getBtnSave(), getCGBL().configGBCTest(1,true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configGBCTest(1,false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        if(getValueEntryFieldName().getText() != null){
            TABLE_PERSON.modifyPerson(personID,personName,getActive().isSelected());
        }
        PERSON_DIRECTORY.getPanelDirectory().updateUI();
        getValueEntryFieldName().setText(null);
        getWindow().dispose();
    }
}
