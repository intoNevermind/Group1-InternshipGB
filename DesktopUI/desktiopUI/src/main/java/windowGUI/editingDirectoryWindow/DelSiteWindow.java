package windowGUI.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.SitesTable;
import windowGUI.component.workDirectory.SitesDirectory;

import java.awt.event.ActionEvent;

public class DelSiteWindow extends EditingDirectoryWindow{
    private static final SitesDirectory SITES_DIRECTORY = new SitesDirectory();
    private static final SitesTable TABLE_SITES = SitesTable.getInstance();
    private int sitesID;

    public DelSiteWindow(String windowTitle,String sitesName, int sitesID) {
        this.sitesID = sitesID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        fillDelPanels(sitesName);
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        TABLE_SITES.delSite(sitesID);
        SITES_DIRECTORY.getPanelDirectory().updateUI();
        getWindow().dispose();
    }
}
