package windowGUI.component.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.SitesTable;
import windowGUI.component.workDirectory.SitesDirectory;

import java.awt.event.ActionEvent;

public class AddSiteWindow extends EditingDirectoryWindow {
    private static final SitesDirectory SITES_DIRECTORY = new SitesDirectory();

    private static final SitesTable TABLE_SITES = new SitesTable();


    public AddSiteWindow(String windowTitle) {
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

        getGBL().setConstraints(getHeadLineTextFieldURL(), getCGBL().configSubsidiaryGBC(getHeadLineTextFieldURL(),true));
        getTextFieldPanel().add(getHeadLineTextFieldURL());
        getGBL().setConstraints(getValueEntryFieldURL(), getCGBL().configSubsidiaryGBC(getValueEntryFieldURL(),true));
        getTextFieldPanel().add(getValueEntryFieldURL());

        getGBL().setConstraints(getActive(), getCGBL().configSubsidiaryGBC(getActive(),true));
        getTextFieldPanel().add(getActive());

        getGBL().setConstraints(getBtnSave(), getCGBL().configSubsidiaryGBC(getBtnSave(),true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configSubsidiaryGBC(getBtnCancel(),false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void getValueField(ActionEvent actionEvent) {
        if(getValueEntryFieldName().getText() != null){
            TABLE_SITES.addSite(getValueEntryFieldName().getText(), getValueEntryFieldURL().getText(),getActive().isSelected());
            System.out.println(getValueEntryFieldName().getText() + " " + getValueEntryFieldURL().getText() + " " + getActive().isSelected());
        }
        SITES_DIRECTORY.getPanelDirectory().updateUI();
        getValueEntryFieldName().setText(null);
        getWindow().dispose();
    }
}
