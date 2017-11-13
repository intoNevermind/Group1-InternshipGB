package windowGUI.component.editingDirectoryWindow;


import windowGUI.component.ConfigurationGBL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class EditingDirectoryWindow {

    private static final int SIZE_WIDTH = 600;
    private static final int SIZE_HEIGHT = 200;

    private static final int INDENT_WIDTH = 100;
    private static final int FIELD_SIZE_WIDTH = SIZE_WIDTH - INDENT_WIDTH;
    private static final int FIELD_SIZE_HEIGHT = SIZE_HEIGHT / 5;

    private final JFrame window = new JFrame();

    private static final GridBagLayout GBL = new GridBagLayout();
    private final ConfigurationGBL CGBL = new ConfigurationGBL();

    private final JPanel textFieldPanel = new JPanel(GBL);
    private final JPanel btnPanel = new JPanel(GBL);

    private final JLabel headLineTextFieldName = new JLabel("Наименование");
    private final JTextField valueEntryFieldName = new JTextField();

    private final JLabel headLineTextFieldURL = new JLabel("URL");
    private final JTextField valueEntryFieldURL = new JTextField();

    private final JLabel headLineTextFieldDel = new JLabel();

    private final JButton btnSave = new JButton("Сохранить");
    private final JButton btnCancel = new JButton("Отмена");


    private final JCheckBox active = new JCheckBox("Отображать эту запись в списке.");


    void fillWindow(){
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        valueEntryFieldName.setPreferredSize(new Dimension(FIELD_SIZE_WIDTH,FIELD_SIZE_HEIGHT));
        valueEntryFieldURL.setPreferredSize(new Dimension(FIELD_SIZE_WIDTH,FIELD_SIZE_HEIGHT));

        window.add(textFieldPanel, BorderLayout.CENTER);
        window.add(btnPanel,BorderLayout.SOUTH);
        window.setVisible(true);
    }

    public abstract void fillPanels();
    public abstract void saveEditing(ActionEvent actionEvent);

    void addBtnListener(){
        btnSave.addActionListener(this::saveEditing);
        btnCancel.addActionListener(this::cancelEditing);
    }

    private void cancelEditing(ActionEvent actionEvent){
        window.dispose();
    }


    static int getSizeWidth() {
        return SIZE_WIDTH;
    }

    static int getSizeHeight() {
        return SIZE_HEIGHT;
    }

    JFrame getWindow() {
        return window;
    }

    JLabel getHeadLineTextFieldName() {
        return headLineTextFieldName;
    }

    JTextField getValueEntryFieldName() {
        return valueEntryFieldName;
    }

    JLabel getHeadLineTextFieldURL() {
        return headLineTextFieldURL;
    }

    JTextField getValueEntryFieldURL() {
        return valueEntryFieldURL;
    }

    JPanel getBtnPanel() {
        return btnPanel;
    }

    JPanel getTextFieldPanel() {
        return textFieldPanel;
    }

    JButton getBtnSave() {
        return btnSave;
    }

    JButton getBtnCancel() {
        return btnCancel;
    }

    GridBagLayout getGBL() {
        return GBL;
    }

    ConfigurationGBL getCGBL() {
        return CGBL;
    }

    JCheckBox getActive() {
        return active;
    }

    public JLabel getHeadLineTextFieldDel() {
        return headLineTextFieldDel;
    }
}
