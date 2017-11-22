package windowGUI.editingDirectoryWindow.delete;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.SitesTable;
import windowGUI.component.workDirectory.SitesDirectory;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.event.ActionEvent;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность удаления сайтов
 * */
public class DelSiteWindow extends EditingDirectoryWindow {
    private static final SitesDirectory SITES_DIRECTORY = new SitesDirectory();
    private int sitesID;

    public DelSiteWindow(String windowTitle,String nameSites, int sitesID) {
        this.sitesID = sitesID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());
        System.out.println("конструктор Удаления сайта");
        SitesTable.infoAllSites();

        fillDelPanels(nameSites);
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        SitesTable.delSite(sitesID);
        SITES_DIRECTORY.visibleDataTable(actionEvent);
        getWindow().dispose();
    }
}
