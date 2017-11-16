package windowGUI.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.PersonTable;
import windowGUI.component.workDirectory.PersonDirectory;

import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.*;

public class AddPersonWindow extends EditingDirectoryWindow {
    private static final PersonDirectory PERSON_DIRECTORY = new PersonDirectory();
    private static final PersonTable TABLE_PERSON = PersonTable.getInstance();

    public AddPersonWindow(String windowTitle) {
        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        fillAddPanels();
        getWindow().add(getTextFieldPanel(), BorderLayout.CENTER);
    }

    @Override
    public void fillAddPanels() {
        getGBL().setConstraints(getHeadLineTextFieldName(), getCGBL().configGBCTest(WEST,1,false));
        getTextFieldPanel().add(getHeadLineTextFieldName());
        getGBL().setConstraints(getValueEntryFieldName(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getValueEntryFieldName());

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
            TABLE_PERSON.addPerson(getValueEntryFieldName().getText(),getActive().isSelected());
            System.out.println(getActive().isSelected());
        }
        PERSON_DIRECTORY.getPanelDirectory().updateUI();
        getValueEntryFieldName().setText(null);
        getWindow().dispose();
    }
}
