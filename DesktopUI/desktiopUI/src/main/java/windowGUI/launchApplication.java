package windowGUI;

import windowGUI.options.workSQL.PagesTable;
import windowGUI.options.workSQL.PersonTable;
import windowGUI.options.workSQL.ProcessingPagesTable;
import windowGUI.options.workSQL.ProcessingPersonPageRankTable;

import javax.swing.*;
import java.util.Arrays;

public class launchApplication {
    public static void main(String[] args) {
        setStyleLookAndFeel();
        new SelectionOfStatistics();
        ProcessingPersonPageRankTable PPPRT = new ProcessingPersonPageRankTable();
//        ProcessingPagesTable PPagesT = new ProcessingPagesTable();

        System.out.println("fillGeneralTable " + Arrays.deepToString(PPPRT.fillGeneralTable("Лента.ру")));
        System.out.println("fillDailyTable return listDateAndCountNewPages :" + PPPRT.fillDailyTable("Лента.ру", "Собчак", "2017-10-01", "2017-10-06"));
        System.out.println("getListIntervalNameSites " + PPPRT.getListIntervalNameSites("2017-10-01", "2017-10-06"));
        System.out.println("getListPersonNameAndRank " + PPPRT.getListPersonNameAndRank("Лента.ру"));
        System.out.println("getNamePerson " + PPPRT.getNamePerson());
        System.out.println("getDistinctNamePerson " + PPPRT.getDistinctNamePerson());
        System.out.println("getFoundDateTimeAndPagesID " + PPPRT.getFoundDateTimeAndPagesID());
        System.out.println("getListAbbreviatedFoundDateTime " + PPPRT.getListAbbreviatedFoundDateTime());
        System.out.println("getListIntervalPagesID " + PPPRT.getListIntervalPagesID("2017-10-01", "2017-10-06"));
        System.out.println("getListIntervalFoundDateTime " + PPPRT.getListIntervalFoundDateTime("2017-10-01", "2017-10-06"));
        System.out.println("getNameSites " + PPPRT.getNameSites());
        System.out.println("getDistinctNameSites " + PPPRT.getDistinctNameSites());
        System.out.println("getSitesIDPages " + PPPRT.getSitesIDPages());
        PPPRT.test();



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
