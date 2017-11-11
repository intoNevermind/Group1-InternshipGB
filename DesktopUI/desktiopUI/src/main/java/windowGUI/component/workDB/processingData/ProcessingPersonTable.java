package windowGUI.component.workDB.ProcessingData;

import windowGUI.component.workDB.Tables.PersonTable;

import java.util.ArrayList;

public class ProcessingPersonTable {
    private static final PersonTable PERSON_TABLE = new PersonTable();
    private static final ArrayList<String> LIST_NAME = PERSON_TABLE.getListName();

    public String[] getColumnName(){
        String[] str = new String[LIST_NAME.size()+1];
        str[0] = "Не выбранно";
        for (int i = 0; i < LIST_NAME.size(); i++) {
            str[i+1] = LIST_NAME.get(i);
        }
        return str;
    }
}
