package windowGUI.editingDirectoryWindow.delete;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workDB.tables.PersonsTable;
import windowGUI.component.workDirectory.KeyWordsDirectory;
import windowGUI.component.workStatistics.DailyStatistic;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.event.ActionEvent;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность удаления личностей
 * */
public class DelPersonWindow extends EditingDirectoryWindow {
    private int personID;
    private String namePerson;

    public DelPersonWindow(String windowTitle,String namePerson, int personID) {
        this.personID = personID;
        this.namePerson = namePerson;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        PersonsTable.infoAllPersons();

        fillDelPanels(namePerson);
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        PersonsTable.delPerson(personID);

        KeyWordsDirectory.LIST_DEL_NAME_PERSONS.add(namePerson);
        DailyStatistic.listDelNamePersons.add(namePerson);

        getWindow().dispose();
    }
}
