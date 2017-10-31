package windowGUI.options.workSQL;

public class PersonTable {
    private static final String TABLE_NAME = "persons";

    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "Name";
    private static final String ACTIVE_COLUMN = "Active";

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getIdColumn() {
        return ID_COLUMN;
    }

    public static String getNameColumn() {
        return NAME_COLUMN;
    }

    public static String getActiveColumn() {
        return ACTIVE_COLUMN;
    }
}
