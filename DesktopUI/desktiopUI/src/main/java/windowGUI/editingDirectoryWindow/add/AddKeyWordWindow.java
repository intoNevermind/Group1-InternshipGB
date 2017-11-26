package windowGUI.editingDirectoryWindow.add;

import windowGUI.ConfigurationsWindowGUI;
import windowGUI.component.workWithDB.tables.KeyWordsTable;
import windowGUI.editingDirectoryWindow.EditingDirectoryWindow;

import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.GridBagConstraints.*;
/*
 * Класс-редактор справочников, отвечающий за функциональную деятельность добавления ключевых слов
 * */
public class AddKeyWordWindow extends EditingDirectoryWindow {

    private int personID;

    public AddKeyWordWindow(String windowTitle, int personID) {
        this.personID = personID;

        new ConfigurationsWindowGUI().setConfigWindow(getWindow(), windowTitle, getSizeWidth(), getSizeHeight());

        KeyWordsTable.infoAllKeyWords();

        fillAddPanels();
        getWindow().add(getTextFieldPanel(), BorderLayout.CENTER);
    }

    @Override
    public void fillAddPanels() {
        getGBL().setConstraints(getHeadLineName(), getCGBL().configGBC(WEST,1,false));
        getTextFieldPanel().add(getHeadLineName());
        getGBL().setConstraints(getNameField(), getCGBL().configGBC(REMAINDER,true));
        getTextFieldPanel().add(getNameField());

        getGBL().setConstraints(getBtnSave(), getCGBL().configGBC(1,true));
        getBtnPanel().add(getBtnSave());
        getGBL().setConstraints(getBtnCancel(), getCGBL().configGBC(1,false));
        getBtnPanel().add(getBtnCancel());
    }

    @Override
    public void saveEditing(ActionEvent actionEvent) {
        if(getNameField().getText() != null ) KeyWordsTable.addKeyWord(getNameField().getText(), personID);

        getNameField().setText(null);
        getWindow().dispose();
    }

}
