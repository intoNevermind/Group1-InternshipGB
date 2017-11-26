package windowGUI.registrationOrEntryWindow;

import windowGUI.ApplicationWindow;
import windowGUI.component.workWithDB.tables.UsersTable;
import static windowGUI.registrationOrEntryWindow.AuthorizationWindow.getWINDOW;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.REMAINDER;
/*
 * Класс-авторизация, отвечающий за функциональную деятельность вкладки Вход
 * */
public class TabRegistration extends Authorization {
    private static final String TAB_TITLE = "Регистрация";

    private static String userLogin;
    private static String userPassword;
    private static ArrayList<String> listLogin;

    TabRegistration() {
        setTabTitle(TAB_TITLE);

        UsersTable.infoAllUsers();

        listLogin = UsersTable.getListLogin();
    }

    @Override
    public void fillTabPanel() {
        getGBL().setConstraints(getHeadLineEmail(),getCGBL().configGBC(EAST,1,false));
        getPanelTabs().add(getHeadLineEmail());
        getGBL().setConstraints(getEmailField(), getCGBL().configGBC(2,false));
        getPanelTabs().add(getEmailField());

        getGBL().setConstraints(getHeadLineLogin(),getCGBL().configGBC(EAST,1,true));
        getPanelTabs().add(getHeadLineLogin());
        getGBL().setConstraints(getLoginField(), getCGBL().configGBC(2,false));
        getPanelTabs().add(getLoginField());

        getGBL().setConstraints(getHeadLinePassword(), getCGBL().configGBC(EAST,1,true));
        getPanelTabs().add(getHeadLinePassword());
        getGBL().setConstraints(getPasswordField(), getCGBL().configGBC(2,false));
        getPanelTabs().add(getPasswordField());

        getGBL().setConstraints(getHeadLinePasswordRepeat(), getCGBL().configGBC(EAST,1,true));
        getPanelTabs().add(getHeadLinePasswordRepeat());
        getGBL().setConstraints(getPasswordRepeatField(), getCGBL().configGBC(2,false));
        getPanelTabs().add(getPasswordRepeatField());

        getBtnContinue().setText("Зарегистрироваться");
        getGBL().setConstraints(getBtnContinue(), getCGBL().configGBC(REMAINDER,true));
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
            for (int i = 0; i < listLogin.size(); i++) {
                if(getLoginField().getText().equals(listLogin.get(i))) {
                    userLogin = null;
                    userPassword = null;
                    break;
                }else {
                    userLogin = getLoginField().getText();
                    userPassword = getPasswordField().getText();
                }
            }
            if(userLogin != null && userPassword != null) {
                UsersTable.addUser(userLogin, false, userPassword, true);
                JOptionPane.showMessageDialog(null,
                        "Ваш аккаунт создан",
                        "Новый аккаунт",
                        JOptionPane.INFORMATION_MESSAGE);

                new ApplicationWindow(userLogin);

                getLoginField().setText(null);
                getWINDOW().dispose();
            }else {
                JOptionPane.showMessageDialog(null,
                        "Такое имя пользователя уже зарегестрированно",
                        "Такой аккаунт уже существует",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        getEmailField().setText(null);
        getLoginField().setText(null);
        getPasswordField().setText(null);
        getPasswordRepeatField().setText(null);
    }
}
