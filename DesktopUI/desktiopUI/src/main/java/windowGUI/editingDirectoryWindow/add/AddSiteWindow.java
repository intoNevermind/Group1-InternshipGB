package windowGUI.editingDirectoryWindow.add;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.SitesTable;
import windowGUI.component.workDirectory.SitesDirectory;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

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
        getGBL().setConstraints(getHeadLineName(), getCGBL().configGBCTest(WEST,1,false));
        getTextFieldPanel().add(getHeadLineName());
        getGBL().setConstraints(getNameField(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getNameField());

        getGBL().setConstraints(getHeadLineURL(), getCGBL().configGBCTest(WEST,1,true));
        getTextFieldPanel().add(getHeadLineURL());
        getGBL().setConstraints(getUrlField(), getCGBL().configGBCTest(REMAINDER,true));
        getTextFieldPanel().add(getUrlField());

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
            TABLE_SITES.addSite(getNameField().getText(), getUrlField().getText(),getActive().isSelected());
        }
        SITES_DIRECTORY.getPanelDirectory().updateUI();
        getNameField().setText(null);
        getWindow().dispose();
    }
}
