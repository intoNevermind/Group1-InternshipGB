package windowGUI.component.editingDirectoryWindow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class EditingDirectoryWindow {

    private static final int SIZE_WIDTH = 600;
    private static final int SIZE_HEIGHT = 140;
    private final JFrame WINDOW = new JFrame();

    private static final GridBagLayout GBL = new GridBagLayout();

    private final JPanel TEXT_FIELD_PANEL = new JPanel(GBL);
    private final JPanel BTN_PANEL = new JPanel(GBL);

    private final JLabel HEAD_LINE_TEXT_FIELD = new JLabel("Наименование");
    private final JTextField VALUE_ENTRY_FIELD = new JTextField();

    private final JButton BTN_SAVE = new JButton("Сохранить");
    private final JButton BTN_CANCEL = new JButton("Отмена");


    private int numberStr;

    void fillWindow(){
        WINDOW.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        VALUE_ENTRY_FIELD.setPreferredSize(new Dimension(500,25));

        WINDOW.add(TEXT_FIELD_PANEL, BorderLayout.CENTER);
        WINDOW.add(BTN_PANEL,BorderLayout.SOUTH);
        WINDOW.setVisible(true);
    }

    public abstract void getValueField(ActionEvent actionEvent);
    public abstract void fillPanels();

    GridBagConstraints configGBC(Component component, boolean moveToNewLine){
        GridBagConstraints gbc =  new GridBagConstraints();
        if(component instanceof JLabel){
            if(moveToNewLine) numberStr++;
            gbc.gridy = numberStr;
            gbc.gridwidth  = 1;
            gbc.anchor = GridBagConstraints.WEST;
            return gbc;
        }
        if(component instanceof JButton){
            if(moveToNewLine) numberStr++;
            gbc.gridy = numberStr;
            gbc.gridwidth  = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            return gbc;
        }
        if(component instanceof JTextField ||component instanceof JCheckBox){
            if(moveToNewLine) numberStr++;
            gbc.gridy = numberStr;
            gbc.gridwidth  = GridBagConstraints.REMAINDER ;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            return gbc;
        }
        return gbc;
    }

    void addListener(){
        BTN_SAVE.addActionListener(this::getValueField);
        BTN_CANCEL.addActionListener(this::cancelEditing);
    }

    private void cancelEditing(ActionEvent actionEvent){
        WINDOW.dispose();
    }

    static int getSizeWidth() {
        return SIZE_WIDTH;
    }

    int getSizeHeight() {
        return SIZE_HEIGHT;
    }

    JFrame getWindow() {
        return WINDOW;
    }

    JLabel getHeadLineTextField() {
        return HEAD_LINE_TEXT_FIELD;
    }

    JTextField getValueEntryField() {
        return VALUE_ENTRY_FIELD;
    }

    JPanel getBtnPanel() {
        return BTN_PANEL;
    }

    JPanel getTextFieldPanel() {
        return TEXT_FIELD_PANEL;
    }

    JButton getBtnSave() {
        return BTN_SAVE;
    }

    JButton getBtnCancel() {
        return BTN_CANCEL;
    }

    GridBagLayout getGBL() {
        return GBL;
    }
}
