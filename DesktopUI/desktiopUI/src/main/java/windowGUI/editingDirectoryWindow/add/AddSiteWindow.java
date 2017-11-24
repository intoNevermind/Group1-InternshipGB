package windowGUI.editingDirectoryWindow.add;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.SitesTable;
import windowGUI.component.workDirectory.KeyWordsDirectory;
import windowGUI.component.workStatistics.DailyStatistic;
import windowGUI.component.workStatistics.GeneralStatistic;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.*;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность добавления сайтов
 * */
public class AddSiteWindow extends EditingDirectoryWindow {

    public AddSiteWindow(String windowTitle) {
        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        SitesTable.infoAllSites();

        fillAddPanels();
        getWindow().add(getTextFieldPanel(), BorderLayout.CENTER);
    }

    @Override
    public void fillAddPanels() {
        getGBL().setConstraints(getHeadLineName(), getCGBL().configGBC(WEST,1,false));
        getTextFieldPanel().add(getHeadLineName());
        getGBL().setConstraints(getNameField(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getNameField());

        getGBL().setConstraints(getHeadLineURL(), getCGBL().configGBC(WEST,1,true));
        getTextFieldPanel().add(getHeadLineURL());
        getGBL().setConstraints(getUrlField(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getUrlField());

        getGBL().setConstraints(getActive(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getActive());

        getGBL().setConstraints(getBtnSave(), getCGBL().configGBC(1,true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configGBC(1,false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        if(getNameField().getText() != null){
            SitesTable.addSite(getNameField().getText(), getUrlField().getText(),getActive().isSelected());
            if (getActive().isSelected()){
                DailyStatistic.listAddNameSites.add(getNameField().getText());
                GeneralStatistic.listAddNameSites.add(getNameField().getText());
            }
        }

        getNameField().setText(null);
        getWindow().dispose();
    }
}
