package windowGUI.options.workSQL;

import java.util.ArrayList;
import java.util.HashMap;

public class SitesTable {
    private static final String TABLE_NAME = "sites";

    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "Name";
    private static final String URL_COLUMN = "URL";
    private static final String ACTIVE_COLUMN = "Active";


    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listName = new ArrayList<>();
    private static final ArrayList<String> listURL = new ArrayList<>();
    private static final ArrayList<Integer> listActive = new ArrayList<>();

    public ArrayList<Integer> getListID(){
        listID.add(1);
        listID.add(2);
        return listID;
    }

    public ArrayList<String> getListName(){
        listName.add("Лента.ру");
        listName.add("РБК");
        return listName;
    }

    public ArrayList<String> getListURL(){
        listURL.add("http:/lenta.ru/");
        listURL.add("http:/lenta.ru/");
        return listURL;
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

    public static String getUrlColumn() {
        return URL_COLUMN;
    }

    public static String getActiveColumn() {
        return ACTIVE_COLUMN;
    }
}
