package windowGUI.registrationOrEntry;

import windowGUI.MyStyle;
import windowGUI.component.ConfigurationGBL;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Authorization {
    private static final MyStyle MY_STYLE = new MyStyle();

    private String tabTitle;

    private static final int PANEL_TABS_SIZE_WIDTH = AuthorizationWindow.getSizeWidth();
    private static final int PANEL_TABS_SIZE_HEIGHT = AuthorizationWindow.getSizeHeight();

    private static final GridBagLayout GBL = new GridBagLayout();
    private static final ConfigurationGBL CGBL = new ConfigurationGBL();

    private final JPanel panelTabs = new JPanel(GBL);

    private final JLabel headLineLogin = new JLabel("Логин");
    private final JLabel headLinePassword = new JLabel("Пароль");
    private final JLabel headLineEmail = new JLabel("e-mail");
    private final JLabel headLinePasswordRepeat = new JLabel("Повтор");

    private final JTextField loginField = new JTextField();
    private final JTextField passwordField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JTextField passwordRepeatField = new JTextField();

    private final JCheckBox remember = new JCheckBox();

    private final JButton btnContinue = new JButton();

    Authorization() {
        MY_STYLE.setStyle(getListComponents());

        panelTabs.setPreferredSize(new Dimension(PANEL_TABS_SIZE_WIDTH, PANEL_TABS_SIZE_HEIGHT));

        fillTabPanel();
    }

    public abstract void fillTabPanel();

    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(headLineLogin);
        listComponent.add(headLinePassword);
        listComponent.add(headLineEmail);
        listComponent.add(headLinePasswordRepeat);

        listComponent.add(loginField);
        listComponent.add(passwordField);
        listComponent.add(emailField);
        listComponent.add(passwordRepeatField);

        listComponent.add(remember);
        return listComponent;
    }


    public static GridBagLayout getGBL() {
        return GBL;
    }

    public static ConfigurationGBL getCGBL() {
        return CGBL;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public JPanel getPanelTabs() {
        return panelTabs;
    }

    public JLabel getHeadLineLogin() {
        return headLineLogin;
    }
    public JLabel getHeadLinePassword() {
        return headLinePassword;
    }
    public JLabel getHeadLineEmail() {
        return headLineEmail;
    }
    public JLabel getHeadLinePasswordRepeat() {
        return headLinePasswordRepeat;
    }



    public JTextField getLoginField() {
        return loginField;
    }
    public JTextField getPasswordField() {
        return passwordField;
    }
    public JTextField getEmailField() {
        return emailField;
    }
    public JTextField getPasswordRepeatField() {
        return passwordRepeatField;
    }

    public JCheckBox getRemember() {
        return remember;
    }

    public JButton getBtnContinue() {
        return btnContinue;
    }
}
