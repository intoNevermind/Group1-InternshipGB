package windowGUI.registrationOrEntryWindow;

import windowGUI.ApplicationWindow;
import windowGUI.component.workDB.tables.UsersTable;
import static windowGUI.registrationOrEntryWindow.AuthorizationWindow.getWINDOW;

import javax.swing.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.REMAINDER;
/*
 * Класс-авторизация, отвечающий за функциональную деятельность вкладки Вход
 * */
public class EntryWindow extends Authorization {
    private static final String TAB_TITLE = "Вход";

    EntryWindow() {
        setTabTitle(TAB_TITLE);

        UsersTable.infoAllUsers();
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
        String str = "";
        if(getLoginField().getText().equals("")) str += " Введите: \"" + getHeadLineLogin().getText() + "\" \n";
        if(getPasswordField().getText().equals("")) str += " Введите:  \"" + getHeadLinePassword().getText() + "\" \n";

        if(!str.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Вход не возможен \n" + str,
                    "Ошибка при входе",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            if(UsersTable.isAuthorized(getLoginField().getText(), getPasswordField().getText())){
                new ApplicationWindow(getLoginField().getText());
                getLoginField().setText(null);
                getWINDOW().dispose();
            }else{
                JOptionPane.showMessageDialog(null,
                        "Логин и пароль не совпадают",
                        "Ошибка авторизации",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        getLoginField().setText(null);
        getPasswordField().setText(null);
    }
}
