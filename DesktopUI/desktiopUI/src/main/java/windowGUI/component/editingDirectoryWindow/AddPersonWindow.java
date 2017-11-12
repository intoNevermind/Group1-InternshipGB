package windowGUI.component.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.PersonTable;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddPersonWindow extends EditingDirectoryWindow {

    private static final PersonTable TABLE_PERSON = new PersonTable();
    JCheckBox jCheckBox = new JCheckBox("Не отображать эту запись в списке");

    public AddPersonWindow(String windowTitle, boolean active) {

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

        getGBL().setConstraints(jCheckBox, configGBC(jCheckBox,true));
        getTextFieldPanel().add(jCheckBox);

        getGBL().setConstraints(getBtnSave(), configGBC(getBtnSave(),true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), configGBC(getBtnCancel(),false));
        getBtnPanel().add(getBtnCancel());

    }

    @Override
    public void getValueField(ActionEvent actionEvent) {
        if(getValueEntryField().getText() != null){
            TABLE_PERSON.addPerson(getValueEntryField().getText(),true);
        }

        getValueEntryField().setText(null);
        getWindow().dispose();
    }
}
