package windowGUI;

import windowGUI.component.workDB.workProcessingData.ProcessingKeyWordsTable;

import javax.swing.*;


public class launchApplication {

    public static void main(String[] args) {
        setStyleLookAndFeel();
        new ApplicationWindow();
        ProcessingKeyWordsTable processingKeyWordsTable = new ProcessingKeyWordsTable();
    }

    private static void setStyleLookAndFeel(){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
