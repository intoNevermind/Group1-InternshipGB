package windowGUI.options.workSQL;

public class PersonPageRankTable {
    private static final String PERSON_PAGE_RANK_NAME = "personpagerank";

    private static final String PERSON_ID_COLUMN = "PersonID";
    private static final String PAGE_ID_COLUMN = "PageID";
    private static final String RANK_COLUMN = "Rank";

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
