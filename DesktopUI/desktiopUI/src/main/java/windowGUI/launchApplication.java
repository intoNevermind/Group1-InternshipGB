package windowGUI;

import windowGUI.component.workDB.workProcessingData.ProcessingSitesTable;

import javax.swing.*;

public class launchApplication {

    public static void main(String[] args) {
        setStyleLookAndFeel();
        new ApplicationWindow();
        ProcessingSitesTable processingSitesTable = new ProcessingSitesTable();
//        System.out.println(processingSitesTable.getActiveSitesByNameSites("Лента.ру"));
//        System.out.println(processingSitesTable.getURLSitesByNameSites("Лента.ру"));
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
