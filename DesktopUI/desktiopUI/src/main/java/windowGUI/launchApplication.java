package windowGUI;

import windowGUI.options.workSQL.ProcessingPersonPageRankTable;

import javax.swing.*;
import java.util.Arrays;

public class launchApplication {
    public static void main(String[] args) {
        setStyleLookAndFeel();
        new SelectionOfStatistics();
        ProcessingPersonPageRankTable PPPRT = new ProcessingPersonPageRankTable();

        System.out.println("getArrayFillGeneralTable " + Arrays.deepToString(PPPRT.getArrayFillGeneralTable("Лента.ру")));
        System.out.println("getArrayFillDailyTable return listDateAndCountNewPages :" + PPPRT.getArrayFillDailyTable("Лента.ру", "Путин", "2017-10-01", "2017-10-06"));
        System.out.println("getListPersonNameAndRank " + PPPRT.getListPersonNameAndRank("Лента.ру"));
        System.out.println("getListNamePerson " + PPPRT.getListNamePerson());
        System.out.println("getListDistinctNamePerson " + PPPRT.getListDistinctNamePerson());
        System.out.println("getListNameSites " + PPPRT.getListNameSites());
        System.out.println("getListDistinctNameSites " + PPPRT.getListDistinctNameSites());
        System.out.println("getListSitesID " + PPPRT.getListSitesID());
//        System.out.println("getListDateAndCountRepetitionPersonPageRank " + PPPRT.getListIntervalDateAndCountRepetitionPersonPageRank("2017-10-02", "2017-10-04")); ;
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
