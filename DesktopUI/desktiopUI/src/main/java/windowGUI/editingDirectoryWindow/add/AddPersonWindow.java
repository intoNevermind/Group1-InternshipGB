package windowGUI.editingDirectoryWindow.add;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workWithDB.tables.PersonsTable;
import windowGUI.component.workWithDirectory.KeyWordsDirectory;
import windowGUI.component.workWithStatistics.DailyStatistic;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.GridBagConstraints.*;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность добавления личностей
 * */
public class AddPersonWindow extends EditingDirectoryWindow {

    public AddPersonWindow(String windowTitle) {
        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        PersonsTable.infoAllPersons();

        fillAddPanels();
        getWindow().add(getTextFieldPanel(), BorderLayout.CENTER);
    }

    @Override
    public void fillAddPanels() {
        getGBL().setConstraints(getHeadLineName(), getCGBL().configGBC(WEST,1,false));
        getTextFieldPanel().add(getHeadLineName());
        getGBL().setConstraints(getNameField(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getNameField());

        getGBL().setConstraints(getActive(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getActive());

        getGBL().setConstraints(getBtnSave(), getCGBL().configGBC(1,true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configGBC(1,false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        if(getNameField().getText() != null) {
            PersonsTable.addPerson(getNameField().getText(),getActive().isSelected());
            if (getActive().isSelected()){
                KeyWordsDirectory.LIST_ADD_NAME_PERSONS.add(getNameField().getText());
                DailyStatistic.LIST_ADD_NAME_PERSONS.add(getNameField().getText());
            }
        }

        getNameField().setText(null);
        getWindow().dispose();
    }
}
