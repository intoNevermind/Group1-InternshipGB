package windowGUI.component.workDB.processingData;

import windowGUI.component.workDB.tables.SitesTable;

import java.util.ArrayList;

public class ProcessingSitesTable {
    private static final SitesTable SITES_TABLE = new SitesTable();
    private static final ArrayList<String> LIST_NAME = SITES_TABLE.getListName();

    public String[] getColumnName(){
        String[] str = new String[LIST_NAME.size()+1];
        str[0] = "Не выбранно";
        for (int i = 0; i < LIST_NAME.size(); i++) {
            str[i+1] = LIST_NAME.get(i);
        }
        return str;
    }
}
