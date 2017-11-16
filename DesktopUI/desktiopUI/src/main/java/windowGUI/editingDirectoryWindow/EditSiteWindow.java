package windowGUI.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.SitesTable;
import windowGUI.component.workDirectory.SitesDirectory;

import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.REMAINDER;
import static java.awt.GridBagConstraints.WEST;

public class EditSiteWindow extends EditingDirectoryWindow{
    private static final SitesDirectory SITES_DIRECTORY = new SitesDirectory();
    private static final SitesTable TABLE_SITES = SitesTable.getInstance();
    private String sitesName;
    private String sitesURL;
    private boolean keyWordActive;
    private int sitesID;

    public EditSiteWindow(String windowTitle, String sitesName, int sitesID, String sitesURL, boolean keyWordActive) {
        this.sitesName = sitesName;
        this.sitesURL = sitesURL;
        this.keyWordActive = keyWordActive;
        this.sitesID = sitesID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        fillEditPanels();
        getWindow().add(getTextFieldPanel(), BorderLayout.CENTER);
    }

    @Override
    public void fillEditPanels() {
        getGBL().setConstraints(getHeadLineTextFieldName(), getCGBL().configGBCTest(WEST,1,false));
        getTextFieldPanel().add(getHeadLineTextFieldName());
        getValueEntryFieldName().setText(sitesName);
        getGBL().setConstraints(getValueEntryFieldName(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getValueEntryFieldName());

        getGBL().setConstraints(getHeadLineTextFieldURL(), getCGBL().configGBCTest(WEST,1,true));
        getTextFieldPanel().add(getHeadLineTextFieldURL());
        getValueEntryFieldURL().setText(sitesURL);
        getGBL().setConstraints(getValueEntryFieldURL(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getValueEntryFieldURL());

        getActive().setSelected(keyWordActive);
        getGBL().setConstraints(getActive(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getActive());

        getGBL().setConstraints(getBtnSave(), getCGBL().configGBCTest(1,true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configGBCTest(1,false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        if(getValueEntryFieldName().getText() != null &&  getValueEntryFieldURL() != null){
            TABLE_SITES.modifySite(sitesID,sitesName,sitesURL,getActive().isSelected());
        }
        SITES_DIRECTORY.getPanelDirectory().updateUI();
        getValueEntryFieldName().setText(null);
        getValueEntryFieldURL().setText(null);
        getWindow().dispose();
    }
}
