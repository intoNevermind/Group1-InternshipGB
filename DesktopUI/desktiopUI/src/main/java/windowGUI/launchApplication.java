package windowGUI;

import windowGUI.component.workDB.tables.PersonTable;
import windowGUI.component.workDB.tables.SitesTable;

import javax.swing.*;

public class launchApplication {

    public static void main(String[] args) {
        setStyleLookAndFeel();
        new ApplicationWindow();
//        PersonTable personTable = PersonTable.getInstance();
//        System.out.println(personTable.getListName());
//        personTable.getListIDAndName();
//        SitesTable sitesTable = SitesTable.getInstance();
//        sitesTable.getListIDAndName();
//        sitesTable.getListNameAndURL();
//        sitesTable.getListNameAndActive();
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
