package windowGUI.component.editingDirectoryWindow;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.SitesTable;
import windowGUI.component.workDirectory.SitesDirectory;

import java.awt.event.ActionEvent;

public class DelSiteWindow extends EditingDirectoryWindow{

    private static final SitesDirectory SITES_DIRECTORY = new SitesDirectory();
    private static final SitesTable TABLE_SITES = new SitesTable();
    private String sitesName;
    private int sitesID;

    public DelSiteWindow(String windowTitle,String sitesName, int sitesID) {
        this.sitesID = sitesID;
        this.sitesName = sitesName;
        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());
        fillPanels();
        fillWindow();
        addBtnListener();

    }


    @Override
    public void fillPanels() {
        getHeadLineTextFieldDel().setText("Вы хотите удалить елемент " + sitesName + " ?");
        getGBL().setConstraints(getHeadLineTextFieldDel(), getCGBL().configSubsidiaryGBC(getHeadLineTextFieldDel(),false));
        getBtnPanel().add(getHeadLineTextFieldDel());

        getBtnSave().setText("Да");
        getGBL().setConstraints(getBtnSave(), getCGBL().configSubsidiaryGBC(getBtnSave(),true));
        getBtnPanel().add(getBtnSave());

        getBtnCancel().setText("Нет");
        getGBL().setConstraints(getBtnCancel(), getCGBL().configSubsidiaryGBC(getBtnCancel(),false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        TABLE_SITES.delSite(sitesID);
        SITES_DIRECTORY.getPanelDirectory().updateUI();
        getWindow().dispose();
    }
}
