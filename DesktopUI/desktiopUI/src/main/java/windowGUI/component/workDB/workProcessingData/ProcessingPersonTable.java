package windowGUI.component.workDB.workProcessingData;

import windowGUI.component.workDB.tables.PersonTable;
import java.util.ArrayList;

public class ProcessingPersonTable extends  ProcessingData{
    private static final PersonTable TABLE_PERSON = new PersonTable();
    private static final ArrayList<String> LIST_NAME_PERSON = TABLE_PERSON.getListName();

    public String[] getColumnName(){
        String[] str = new String[LIST_NAME_PERSON.size()+1];
        str[0] = "Не выбранно";
        for (int i = 0; i < LIST_NAME_PERSON.size(); i++) {
            str[i+1] = LIST_NAME_PERSON.get(i);
        }
        return str;
    }

    @Override
    public Object[][] getArrayFillTable(int countColumn){
        if(countColumn < 1) return super.getArrayFillTable(countColumn);
        return convertingListToArray(LIST_NAME_PERSON,countColumn);
    }
}
