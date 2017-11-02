package windowGUI.options.workSQL;

import java.util.ArrayList;

public class PersonTable {
    private static final String TABLE_NAME = "persons";

    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "Name";
    private static final String ACTIVE_COLUMN = "Active";

    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listName = new ArrayList<>();
    private static final ArrayList<Integer> listActive = new ArrayList<>();

    public ArrayList<Integer> getListID(){
        listID.add(1);
        listID.add(2);
        listID.add(3);
        return listID;
    }

    public ArrayList<String> getListName(){
        listName.add("Путин");
        listName.add("Навальный");
        listName.add("Собчак");
        return listName;
    }

    public ArrayList<Integer> getListActive(){
        listActive.add(0);
        listActive.add(1);
        return listActive;
    }

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
