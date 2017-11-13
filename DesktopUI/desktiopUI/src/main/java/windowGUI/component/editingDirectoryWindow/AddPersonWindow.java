package windowGUI.component.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.PersonTable;
import windowGUI.component.workDirectory.PersonDirectory;
import java.awt.event.ActionEvent;


public class AddPersonWindow extends EditingDirectoryWindow {
    private static final PersonDirectory PERSON_DIRECTORY = new PersonDirectory();

    private static final PersonTable TABLE_PERSON = new PersonTable();

    public AddPersonWindow(String windowTitle) {

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

        getGBL().setConstraints(getActive(), getCGBL().configSubsidiaryGBC(getActive(),true));
        getTextFieldPanel().add(getActive());

        getGBL().setConstraints(getBtnSave(), getCGBL().configSubsidiaryGBC(getBtnSave(),true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configSubsidiaryGBC(getBtnCancel(),false));
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
