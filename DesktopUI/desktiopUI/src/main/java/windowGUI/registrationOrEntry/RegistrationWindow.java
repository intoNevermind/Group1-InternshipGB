package windowGUI.registrationOrEntry;

import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.REMAINDER;

public class RegistrationWindow extends Authorization {
    private static final String TAB_TITLE = "Регистрация";

    RegistrationWindow() {
        setTabTitle(TAB_TITLE);
    }

    @Override
    public void fillTabPanel() {
        getGBL().setConstraints(getHeadLineEmail(),getCGBL().configGBCTest(EAST,1,false));
        getPanelTabs().add(getHeadLineEmail());
        getGBL().setConstraints(getEmailField(), getCGBL().configGBCTest(2,false));
        getPanelTabs().add(getEmailField());

        getGBL().setConstraints(getHeadLineLogin(),getCGBL().configGBCTest(EAST,1,true));
        getPanelTabs().add(getHeadLineLogin());
        getGBL().setConstraints(getLoginField(), getCGBL().configGBCTest(2,false));
        getPanelTabs().add(getLoginField());

        getGBL().setConstraints(getHeadLinePassword(), getCGBL().configGBCTest(EAST,1,true));
        getPanelTabs().add(getHeadLinePassword());
        getGBL().setConstraints(getPasswordField(), getCGBL().configGBCTest(2,false));
        getPanelTabs().add(getPasswordField());

        getGBL().setConstraints(getHeadLinePasswordRepeat(), getCGBL().configGBCTest(EAST,1,true));
        getPanelTabs().add(getHeadLinePasswordRepeat());
        getGBL().setConstraints(getPasswordRepeatField(), getCGBL().configGBCTest(2,false));
        getPanelTabs().add(getPasswordRepeatField());

        getRemember().setText("Согласен с пользовательским соглашением");
        getGBL().setConstraints(getRemember(), getCGBL().configGBCTest(REMAINDER,true));
        getPanelTabs().add(getRemember());

        getBtnContinue().setText("Зарегистрироваться");
        getGBL().setConstraints(getBtnContinue(), getCGBL().configGBCTest(REMAINDER,true));
        getPanelTabs().add(getBtnContinue());
    }

}
