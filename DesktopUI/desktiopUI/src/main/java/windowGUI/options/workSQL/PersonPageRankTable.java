package windowGUI.options.workSQL;

import java.util.ArrayList;

public class PersonPageRankTable {
    private static final String PERSON_PAGE_RANK_NAME = "personpagerank";

    private static final String PERSON_ID_COLUMN = "PersonID";
    private static final String PAGE_ID_COLUMN = "PageID";
    private static final String RANK_COLUMN = "Rank";

    private static final ArrayList<Integer> listPersonID = new ArrayList<>();
    private static final ArrayList<Integer> listPageID = new ArrayList<>();
    private static final ArrayList<Integer> listRank = new ArrayList<>();

    public ArrayList<Integer> getListPersonID() {
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);

        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);

        listPersonID.add(3);
        listPersonID.add(3);
        listPersonID.add(3);
        listPersonID.add(3);
        listPersonID.add(3);
        listPersonID.add(3);

        return listPersonID;
    }

    public ArrayList<Integer> getListPageID() {
        listPageID.add(1);
        listPageID.add(2);
        listPageID.add(3);
        listPageID.add(4);
        listPageID.add(5);
        listPageID.add(6);
        listPageID.add(1);
        listPageID.add(2);
        listPageID.add(3);
        listPageID.add(4);
        listPageID.add(5);
        listPageID.add(6);
        listPageID.add(1);
        listPageID.add(2);
        listPageID.add(3);
        listPageID.add(4);
        listPageID.add(5);
        listPageID.add(6);
        return listPageID;
    }

    public ArrayList<Integer> getListRank() {
        listRank.add(9);
        listRank.add(7);
        listRank.add(6);
        listRank.add(7);
        listRank.add(4);
        listRank.add(18);
        listRank.add(0);
        listRank.add(5);
        listRank.add(8);
        listRank.add(3);
        listRank.add(1);
        listRank.add(0);
        listRank.add(0);
        listRank.add(7);
        listRank.add(8);
        listRank.add(0);
        listRank.add(20);
        listRank.add(19);
        return listRank;
    }

    public static String getPersonPageRankName() {
        return PERSON_PAGE_RANK_NAME;
    }

    public static String getPersonIdColumn() {
        return PERSON_ID_COLUMN;
    }

    public static String getPageIdColumn() {
        return PAGE_ID_COLUMN;
    }

    public static String getRankColumn() {
        return RANK_COLUMN;
    }
}
