package windowGUI.options.workSQL;

import java.util.ArrayList;

public class KeyWordsTable {
    private static final String KEY_WORDS_NAME = "keywords";

    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "Name";
    private static final String PERSON_ID_COLUMN = "PersonID";


    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listName = new ArrayList<>();
    private static final ArrayList<Integer> listPersonID = new ArrayList<>();

    public ArrayList<Integer> getListID(){
        for (int i = 1; i <= 9; i++) {
            listID.add(i);
        }
        return listID;
    }

    public ArrayList<String> getListName(){
        listName.add("Путин");
        listName.add("Путинa");
        listName.add("Путинy");
        listName.add("Путиным");
        listName.add("Навальный");
        listName.add("Навальным");
        listName.add("Навальному");
        listName.add("Навального");
        listName.add("Собчак");
        return listName;
    }

    public ArrayList<Integer> getListPersonID(){
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(1);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(2);
        listPersonID.add(3);
        return listPersonID;
    }

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
