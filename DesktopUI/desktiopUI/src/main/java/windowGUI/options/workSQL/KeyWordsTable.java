package windowGUI.options.workSQL;

public class KeyWordsTable {
    private static final String KEY_WORDS_NAME = "keywords";

    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "Name";
    private static final String PERSON_ID_COLUMN = "PersonID";

    public static String getKeyWordsName() {
        return KEY_WORDS_NAME;
    }

    public static String getIdColumn() {
        return ID_COLUMN;
    }

    public static String getNameColumn() {
        return NAME_COLUMN;
    }

    public static String getPersonIdColumn() {
        return PERSON_ID_COLUMN;
    }
}
