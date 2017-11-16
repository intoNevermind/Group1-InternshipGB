package windowGUI.registrationOrEntryWindow;

import windowGUI.ApplicationWindow;

import java.awt.event.ActionEvent;

import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.REMAINDER;
import static windowGUI.registrationOrEntryWindow.AuthorizationWindow.getWINDOW;

public class EntryWindow extends Authorization {
    private static final String TAB_TITLE = "Вход";

    EntryWindow() {
        setTabTitle(TAB_TITLE);
    }

    @Override
    public void fillTabPanel() {
        getGBL().setConstraints(getHeadLineLogin(),getCGBL().configGBCTest(EAST,1,false));
        getPanelTabs().add(getHeadLineLogin());
        getGBL().setConstraints(getLoginField(), getCGBL().configGBCTest(2,false));
        getPanelTabs().add(getLoginField());

        getGBL().setConstraints(getHeadLinePassword(), getCGBL().configGBCTest(EAST,1,true));
        getPanelTabs().add(getHeadLinePassword());
        getGBL().setConstraints(getPasswordField(), getCGBL().configGBCTest(2,false));
        getPanelTabs().add(getPasswordField());

        getRemember().setText("Запомнить");
        getGBL().setConstraints(getRemember(), getCGBL().configGBCTest(REMAINDER,true));
        getPanelTabs().add(getRemember());

        getBtnContinue().setText("Войти");
        getGBL().setConstraints(getBtnContinue(), getCGBL().configGBCTest(REMAINDER,true));
        getPanelTabs().add(getBtnContinue());
    }

    @Override
    public void openApplication(ActionEvent actionEvent) {
        if(getLoginField() != null && getPasswordField() != null){
            new ApplicationWindow();
        }
        getWINDOW().dispose();
    }
}
