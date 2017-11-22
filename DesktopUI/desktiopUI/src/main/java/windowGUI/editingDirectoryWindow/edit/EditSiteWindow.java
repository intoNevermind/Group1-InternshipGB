package windowGUI.editingDirectoryWindow.edit;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.SitesTable;
import windowGUI.component.workDirectory.SitesDirectory;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.REMAINDER;
import static java.awt.GridBagConstraints.WEST;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность редактирования сайтов
 * */
public class EditSiteWindow extends EditingDirectoryWindow {
    private static final SitesDirectory SITES_DIRECTORY = new SitesDirectory();

    private String nameSites;
    private String urlSites;
    private boolean activeKeyWord;
    private int sitesID;

    public EditSiteWindow(String windowTitle, String nameSites, int sitesID, String urlSites, boolean activeKeyWord) {
        this.nameSites = nameSites;
        this.urlSites = urlSites;
        this.activeKeyWord = activeKeyWord;
        this.sitesID = sitesID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        SitesTable.infoAllSites();

        fillEditPanels();
        getWindow().add(getTextFieldPanel(), BorderLayout.CENTER);
    }

    @Override
    public void fillEditPanels() {
        getGBL().setConstraints(getHeadLineName(), getCGBL().configGBC(WEST,1,false));
        getTextFieldPanel().add(getHeadLineName());
        getNameField().setText(nameSites);
        getGBL().setConstraints(getNameField(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getNameField());

        getGBL().setConstraints(getHeadLineURL(), getCGBL().configGBC(WEST,1,true));
        getTextFieldPanel().add(getHeadLineURL());
        getUrlField().setText(urlSites);
        getGBL().setConstraints(getUrlField(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getUrlField());

        getActive().setSelected(activeKeyWord);
        getGBL().setConstraints(getActive(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getActive());

        getGBL().setConstraints(getBtnSave(), getCGBL().configGBC(1,true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configGBC(1,false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        if(getNameField().getText() != null &&  getUrlField().getText() != null){
            SitesTable.modifySite(sitesID, getNameField().getText(), getUrlField().getText(), getActive().isSelected());
        }

        SitesTable.infoAllSites();

        SITES_DIRECTORY.visibleDataTable(actionEvent);
        getNameField().setText(null);
        getWindow().dispose();
    }
}
