package windowGUI;

import windowGUI.options.workSQL.ProcessingPersonPageRankTable;

import javax.swing.*;

public class launchApplication {
    public static void main(String[] args) {
        setStyleLookAndFeel();
        new SelectionOfStatistics();
        ProcessingPersonPageRankTable PPPRT = new ProcessingPersonPageRankTable();
//        PPPRT.fillGeneralTable("РБК", "Путин");
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
