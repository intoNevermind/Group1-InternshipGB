package windowGUI.editingDirectoryWindow.edit;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.PersonTable;
import windowGUI.component.workDirectory.PersonDirectory;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

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
        getGBL().setConstraints(getHeadLineName(), getCGBL().configGBCTest(WEST,1,false));
        getTextFieldPanel().add(getHeadLineName());
        getNameField().setText(personName);
        getGBL().setConstraints(getNameField(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getNameField());

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
        if(getNameField().getText() != null){
            TABLE_PERSON.modifyPerson(personID,personName,getActive().isSelected());
        }
        PERSON_DIRECTORY.getPanelDirectory().updateUI();
        getNameField().setText(null);
        getWindow().dispose();
    }
}
