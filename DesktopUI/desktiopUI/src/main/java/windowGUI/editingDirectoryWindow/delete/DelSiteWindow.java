package windowGUI.editingDirectoryWindow.delete;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.SitesTable;
import windowGUI.component.workStatistics.DailyStatistic;
import windowGUI.component.workStatistics.GeneralStatistic;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.event.ActionEvent;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность удаления сайтов
 * */
public class DelSiteWindow extends EditingDirectoryWindow {
    private int sitesID;
    private String nameSites;

    public DelSiteWindow(String windowTitle,String nameSites, int sitesID) {
        this.sitesID = sitesID;
        this.nameSites = nameSites;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        SitesTable.infoAllSites();

        fillDelPanels(nameSites);
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        SitesTable.delSite(sitesID);

        DailyStatistic.listDelNameSites.add(nameSites);
        GeneralStatistic.listDelNameSites.add(nameSites);

        getWindow().dispose();
    }
}
