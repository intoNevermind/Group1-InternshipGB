package windowGUI.options.workSQL;

public class PagesTable {
    private static final String PAGES_NAME = "pages";

    private static final String ID_COLUMN = "ID";
    private static final String URL_COLUMN = "URL";
    private static final String SITE_ID_COLUMN = "SiteID";
    private static final String FOUND_DATE_TIME_COLUMN = "FoundDateTime";
    private static final String LAST_SCAN_DATE_COLUMN = "LastScanDate";

    public static String getPagesName() {
        return PAGES_NAME;
    }

    public static String getIdColumn() {
        return ID_COLUMN;
    }

    public static String getUrlColumn() {
        return URL_COLUMN;
    }

    public static String getSiteIdColumn() {
        return SITE_ID_COLUMN;
    }

    public static String getFoundDateTimeColumn() {
        return FOUND_DATE_TIME_COLUMN;
    }

    public static String getLastScanDateColumn() {
        return LAST_SCAN_DATE_COLUMN;
    }
}
