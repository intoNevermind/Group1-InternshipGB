package windowGUI.component.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.SitesTable;
import windowGUI.component.workDirectory.SitesDirectory;

import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.*;

public class AddSiteWindow extends EditingDirectoryWindow {
    private static final SitesDirectory SITES_DIRECTORY = new SitesDirectory();

    private static final SitesTable TABLE_SITES = SitesTable.getInstance();

    public AddSiteWindow(String windowTitle) {
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

        getGBL().setConstraints(getHeadLineTextFieldURL(), getCGBL().configGBCTest(WEST,1,true));
        getTextFieldPanel().add(getHeadLineTextFieldURL());
        getGBL().setConstraints(getValueEntryFieldURL(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getValueEntryFieldURL());

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
            TABLE_SITES.addSite(getValueEntryFieldName().getText(), getValueEntryFieldURL().getText(),getActive().isSelected());
        }
        SITES_DIRECTORY.getPanelDirectory().updateUI();
        getValueEntryFieldName().setText(null);
        getWindow().dispose();
    }
}
