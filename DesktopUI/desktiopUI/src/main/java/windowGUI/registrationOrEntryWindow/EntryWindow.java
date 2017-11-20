package windowGUI.registrationOrEntryWindow;

import windowGUI.ApplicationWindow;
import windowGUI.component.workDB.tables.UsersTable;
import static windowGUI.registrationOrEntryWindow.AuthorizationWindow.getWINDOW;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.REMAINDER;
/*
 * Класс-авторизация, отвечающий за функциональную деятельность вкладки Вход
 * */
public class EntryWindow extends Authorization {
    private static final String TAB_TITLE = "Вход";


//    private static final ArrayList<String> LIST_LOGIN = UsersTable.getListLogin();
//    private static final LinkedHashMap<String,String> LIST_LOGIN_AND_PASSWORD  = UsersTable.getListLoginAndPassword();

    private static String userLogin;
    private static String userPassword;
    private static ArrayList<String> listLogin;
    private static LinkedHashMap<String,String> listLoginAndPassword;

    EntryWindow() {
        setTabTitle(TAB_TITLE);

        System.out.println("конструктор ВХОД");
        UsersTable.infoAllUsers();
        listLogin = UsersTable.getListLogin();
        listLoginAndPassword  = UsersTable.getListLoginAndPassword();
    }

    @Override
    public void fillTabPanel() {
        getGBL().setConstraints(getHeadLineLogin(),getCGBL().configGBC(EAST,1,false));
        getPanelTabs().add(getHeadLineLogin());
        getGBL().setConstraints(getLoginField(), getCGBL().configGBC(2,false));
        getPanelTabs().add(getLoginField());

        getGBL().setConstraints(getHeadLinePassword(), getCGBL().configGBC(EAST,1,true));
        getPanelTabs().add(getHeadLinePassword());
        getGBL().setConstraints(getPasswordField(), getCGBL().configGBC(2,false));
        getPanelTabs().add(getPasswordField());

        getBtnContinue().setText("Войти");
        getGBL().setConstraints(getBtnContinue(), getCGBL().configGBC(REMAINDER,true));
        getPanelTabs().add(getBtnContinue());
    }

    @Override
    public void openApplication(ActionEvent actionEvent) {

        System.out.println(listLogin);
        String str = "";
        if(getLoginField().getText().equals("")) str += " Введите: \"" + getHeadLineLogin().getText() + "\" \n";
        if(getPasswordField().getText().equals("")) str += " Введите:  \"" + getHeadLinePassword().getText() + "\" \n";
        if(!getLoginField().getText().equals(listLoginAndPassword.get(getPasswordField().getText()))) str += "Логин и пароль не совпадают";

        if(!str.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Вход не возможен \n" + str,
                    "Ошибка при входе",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            for (int i = 0; i < listLogin.size(); i++) {
                if (getLoginField().getText().equals(listLogin.get(i)) && getLoginField().getText().equals(listLoginAndPassword.get(getPasswordField().getText()))) {
                    userLogin = listLogin.get(i);
                    userPassword = listLoginAndPassword.get(userLogin);
                    break;
                }else {
                    userLogin = null;
                    userPassword = null;
                }
            }
            if (userLogin != null && userPassword != null) {
//                if(UsersTable.authorized(userLogin, userPassword)){
                System.out.println(userLogin);
                    new ApplicationWindow(userLogin);
                    getLoginField().setText(null);
                    getWINDOW().dispose();
//                }else{
//                    JOptionPane.showMessageDialog(null,
//                            "Не удалось авторизовать пользователя",
//                            "Ошибка авторизации",
//                            JOptionPane.WARNING_MESSAGE);
//                }

            } else {
                JOptionPane.showMessageDialog(null,
                        "Такого аккаунта не найденно",
                        "Ошибка авторизации",
                        JOptionPane.WARNING_MESSAGE);

            }
        }
        getLoginField().setText(null);
        getPasswordField().setText(null);
    }
}
