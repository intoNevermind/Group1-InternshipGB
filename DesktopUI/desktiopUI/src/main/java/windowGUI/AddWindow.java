package windowGUI;

import javax.swing.*;
import java.awt.*;

public class AddWindow {

    private static final int SIZE_WIDTH = 600;
    private static final int SIZE_HEIGHT = 120;
    private static final JFrame WINDOW = new JFrame();
    private static final JLabel label = new JLabel("Наименование");
    private static final JTextField jTextField = new JFormattedTextField();
    private static final JPanel BTN_PANEL = new JPanel(new FlowLayout());
    private static final JPanel TEXT_FIELD_PANEL = new JPanel(new BorderLayout());
    private static final JButton BTN_SAVE = new JButton("Сохранить");
    private static final JButton BTN_CANCEL = new JButton("Отмена");


    public AddWindow(String windowTitle) {
        WINDOW.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        new ConfigurationsWindowGUI().setConfigWindow(WINDOW, windowTitle, SIZE_WIDTH, SIZE_HEIGHT);

        TEXT_FIELD_PANEL.add(label, BorderLayout.CENTER);
        TEXT_FIELD_PANEL.add(jTextField, BorderLayout.SOUTH);

        BTN_PANEL.add(BTN_SAVE);
        BTN_PANEL.add(BTN_CANCEL);

        WINDOW.add(TEXT_FIELD_PANEL, BorderLayout.CENTER);
        WINDOW.add(BTN_PANEL,BorderLayout.SOUTH);
        WINDOW.setVisible(true);
    }
}
