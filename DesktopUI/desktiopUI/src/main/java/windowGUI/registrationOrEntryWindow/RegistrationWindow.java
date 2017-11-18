package windowGUI.registrationOrEntryWindow;

import static windowGUI.registrationOrEntryWindow.AuthorizationWindow.getWINDOW;
import windowGUI.ApplicationWindow;
import windowGUI.component.workDB.tables.UsersTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.REMAINDER;
/*
 * Класс-авторизация, отвечающий за функциональную деятельность вкладки Вход
 * */
public class RegistrationWindow extends Authorization {
    private static final String TAB_TITLE = "Регистрация";

    private static final UsersTable USERS_TABLE = UsersTable.getInstance();
    private static final ArrayList<String> LIST_LOGIN = USERS_TABLE.getListLogin();
    private static final LinkedHashMap<String,String> LIST_LOGIN_AND_PASSWORD  = USERS_TABLE.getListLoginAndPassword();

    private static String userLogin;
    private static String userPassword;

    RegistrationWindow() {
        setTabTitle(TAB_TITLE);
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
            for (int i = 0; i < LIST_LOGIN.size(); i++) {
                if (getLoginField().getText().equals(LIST_LOGIN.get(i))) {
                    userLogin = null;
                    userPassword = null;
                    break;
                }else {
                    userLogin = getLoginField().getText();
                    userPassword = getPasswordField().getText();
                }
            }
            if(userLogin != null && userPassword != null) {
//                if(USERS_TABLE.authorized(userLogin, userPassword)){
                USERS_TABLE.addUser(userLogin, false, userPassword, true);
                JOptionPane.showMessageDialog(null,
                        "Ваш аккаунт создан",
                        "Новый аккаунт",
                        JOptionPane.INFORMATION_MESSAGE);
                new ApplicationWindow(userLogin);
                getLoginField().setText(null);
                getWINDOW().dispose();
//                }else{
//                    JOptionPane.showMessageDialog(null,
//                            "Не удалось авторизовать пользователя",
//                            "Ошибка авторизации",
//                            JOptionPane.WARNING_MESSAGE);
//                }
            }else{
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
