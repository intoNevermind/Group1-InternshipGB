package windowGUI.registrationOrEntryWindow;

import static windowGUI.registrationOrEntryWindow.AuthorizationWindow.getWINDOW;
import windowGUI.ApplicationWindow;
import windowGUI.component.workDB.tables.UsersTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.REMAINDER;

public class RegistrationWindow extends Authorization {
    private static final String TAB_TITLE = "Регистрация";

    private static final  UsersTable USERS_TABLE = UsersTable.getInstance();
    private static String userLogin;
    private static String userPassword;

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

        getBtnContinue().setText("Зарегистрироваться");
        getGBL().setConstraints(getBtnContinue(), getCGBL().configGBCTest(REMAINDER,true));
        getPanelTabs().add(getBtnContinue());
    }

    @Override
    public void openApplication(ActionEvent actionEvent) {

        String str = "";
        if(getEmailField().getText().equals("")) str += " Введите: \"" + getHeadLineEmail().getText() + "\" \n";
        if(getLoginField().getText().equals("")) str += " Введите: \"" + getHeadLineLogin().getText() + "\" \n";
        if(getPasswordField().getText().equals("")) str += " Введите:  \"" + getHeadLinePassword().getText() + "\" \n";
        if(getPasswordRepeatField().getText().equals("")) str += " Введите:  \"" + getHeadLinePasswordRepeat().getText() + "\" \n";
        if(!getPasswordField().getText().equals(getPasswordRepeatField().getText())) str += "Пароли не совпадают";
        if(!str.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Вход не возможен \n" + str,
                    "Ошибка при входе",
                    JOptionPane.WARNING_MESSAGE);
            getEmailField().setText(getEmailField().getText());

        }else {

            userLogin = getLoginField().getText();
            userPassword = getPasswordField().getText();
                USERS_TABLE.addUser(userLogin , false, userPassword, true);
                JOptionPane.showMessageDialog(null,
                        "Добро пожаловать " + userLogin,
                        "Создан новый аккаунт",
                        JOptionPane.INFORMATION_MESSAGE);
                new ApplicationWindow(userLogin);
                getLoginField().setText(null);
                getWINDOW().dispose();
        }
        getEmailField().setText(null);
        getLoginField().setText(null);
        getPasswordField().setText(null);
        getPasswordRepeatField().setText(null);
    }

    public static String getUserLogin() {
        return userLogin;
    }
}
