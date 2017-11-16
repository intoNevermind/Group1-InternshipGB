package windowGUI.editingDirectoryWindow;

import windowGUI.MyStyle;
import windowGUI.component.ConfigurationGBL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class EditingDirectoryWindow {
    private static final int SIZE_WIDTH = 600;
    private static final int SIZE_HEIGHT = 200;

    private static final int INDENT_WIDTH = 100;
    private static final int FIELD_SIZE_WIDTH = SIZE_WIDTH - INDENT_WIDTH;
    private static final int FIELD_SIZE_HEIGHT = SIZE_HEIGHT / 5;

    private static final MyStyle MY_STYLE = new MyStyle();

    private final JFrame window = new JFrame();

    private static final GridBagLayout GBL = new GridBagLayout();
    private static final ConfigurationGBL CGBL = new ConfigurationGBL();

    private final JPanel textFieldPanel = new JPanel(GBL);
    private final JPanel btnPanel = new JPanel(GBL);
    private final JPanel panelText = new JPanel(new BorderLayout());

    private final JLabel headLineName = new JLabel("Наименование");
    private final JLabel headLineURL = new JLabel("URL");
    private final JLabel headLineDel = new JLabel();

    private final JTextField nameField = new JTextField();
    private final JTextField urlField = new JTextField();

    private final JButton btnSave = new JButton("Сохранить");
    private final JButton btnCancel = new JButton("Отмена");

    private final JCheckBox active = new JCheckBox("Отображать эту запись в списке.");

    public EditingDirectoryWindow() {
        MY_STYLE.setStyle(getListComponents());

        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        nameField.setPreferredSize(new Dimension(FIELD_SIZE_WIDTH,FIELD_SIZE_HEIGHT));
        urlField.setPreferredSize(new Dimension(FIELD_SIZE_WIDTH,FIELD_SIZE_HEIGHT));

        window.add(btnPanel,BorderLayout.SOUTH);
        addBtnListener();
        window.setVisible(true);
    }

    public abstract void saveEditing(ActionEvent actionEvent);

    public void fillAddPanels(){}
    public void fillEditPanels(){}

    protected void fillDelPanels(String elementName){
        headLineDel.setText("Вы хотите удалить елемент " + elementName + " ?");
        panelText.add(headLineDel, BorderLayout.CENTER);
        window.add(panelText, BorderLayout.CENTER);
        btnSave.setText("Да");
        GBL.setConstraints(btnSave, CGBL.configGBCTest(1,true));
        btnPanel.add(btnSave);

        btnCancel.setText("Нет");
        GBL.setConstraints(btnCancel, CGBL.configGBCTest(1,false));
        btnPanel.add(btnCancel);
    }

    private void addBtnListener(){
        btnSave.addActionListener(this::saveEditing);
        btnCancel.addActionListener(this::cancelEditing);
    }

    private void cancelEditing(ActionEvent actionEvent){
        window.dispose();
    }

    private ArrayList<Component> getListComponents(){
        ArrayList<Component> listComponent = new ArrayList<>();
        listComponent.add(headLineName);
        listComponent.add(headLineURL);
        listComponent.add(headLineDel);

        listComponent.add(textFieldPanel);
        listComponent.add(urlField);

        listComponent.add(btnSave);
        listComponent.add(btnCancel);
        listComponent.add(active);
        return listComponent;
    }

    public static int getSizeWidth() {
        return SIZE_WIDTH;
    }
    public static int getSizeHeight() {
        return SIZE_HEIGHT;
    }

    public JFrame getWindow() {
        return window;
    }

    public static GridBagLayout getGBL() {
        return GBL;
    }
    public static ConfigurationGBL getCGBL() {
        return CGBL;
    }

    protected JPanel getBtnPanel() {
        return btnPanel;
    }
    protected JPanel getTextFieldPanel() {
        return textFieldPanel;
    }

    protected JLabel getHeadLineName() {
        return headLineName;
    }
    protected JLabel getHeadLineURL() {
        return headLineURL;
    }

    protected JTextField getNameField() {
        return nameField;
    }
    protected JTextField getUrlField() {
        return urlField;
    }

    protected JButton getBtnSave() {
        return btnSave;
    }
    protected JButton getBtnCancel() {
        return btnCancel;
    }

    protected JCheckBox getActive() {
        return active;
    }
}
