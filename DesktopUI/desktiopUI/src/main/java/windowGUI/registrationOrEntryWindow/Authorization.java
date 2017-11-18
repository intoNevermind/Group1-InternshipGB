package windowGUI.registrationOrEntryWindow;

import windowGUI.MyStyle;
import windowGUI.component.ConfigurationGBL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    private final JPasswordField passwordField = new JPasswordField();
    private final JTextField emailField = new JTextField();
    private final JPasswordField passwordRepeatField = new JPasswordField();

    private final JButton btnContinue = new JButton();

    Authorization() {
        MY_STYLE.setStyle(getListComponents());

        panelTabs.setPreferredSize(new Dimension(PANEL_TABS_SIZE_WIDTH, PANEL_TABS_SIZE_HEIGHT));

        fillTabPanel();
        addBtnListener();
    }

    public abstract void fillTabPanel();
    public abstract void openApplication(ActionEvent actionEvent);

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
        listComponent.add(btnContinue);

        return listComponent;
    }

    private void addBtnListener(){
        btnContinue.addActionListener(this::openApplication);
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

    JLabel getHeadLineLogin() {
        return headLineLogin;
    }
    JLabel getHeadLinePassword() {
        return headLinePassword;
    }
    JLabel getHeadLineEmail() {
        return headLineEmail;
    }
    JLabel getHeadLinePasswordRepeat() {
        return headLinePasswordRepeat;
    }

    JTextField getLoginField() {
        return loginField;
    }
    JTextField getPasswordField() {
        return passwordField;
    }
    JTextField getEmailField() {
        return emailField;
    }
    JTextField getPasswordRepeatField() {
        return passwordRepeatField;
    }

    JButton getBtnContinue() {
        return btnContinue;
    }

}
