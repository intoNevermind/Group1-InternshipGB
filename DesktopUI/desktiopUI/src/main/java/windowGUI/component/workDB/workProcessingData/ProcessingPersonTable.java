package windowGUI.component.workDB.workProcessingData;

import windowGUI.component.workDB.tables.PersonTable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ProcessingPersonTable extends  ProcessingData{


    private static final PersonTable TABLE_PERSON = PersonTable.getInstance();
    private static final ArrayList<String> LIST_NAME_PERSON = TABLE_PERSON.getListName();
    private static final LinkedHashMap<Integer, String> listIDAndNamePerson = TABLE_PERSON.getListIDAndName();
    private static final LinkedHashMap<String, Boolean> listNameAndActive = TABLE_PERSON.getListNameAndActive();

    public String[] getColumnName(){
        String[] str = new String[LIST_NAME_PERSON.size()+1];
        str[0] = getNotChosen();
        for (int i = 0; i < LIST_NAME_PERSON.size(); i++) {
            if(listNameAndActive.get(LIST_NAME_PERSON.get(i)))
            str[i+1] = LIST_NAME_PERSON.get(i);
        }
        return str;
    }

    @Override
    public Object[][] getArrayFillTable(int countColumn){
        if(countColumn < 1) return super.getArrayFillTable(countColumn);
        return convertingListToArray(LIST_NAME_PERSON,countColumn);
    }

    public int getIDPersonByNamePerson(String namePerson){
       return getIDByName(namePerson,listIDAndNamePerson);
    }

    public boolean getActivePersonByNamePerson(String namePerson){
        return getActiveByName(namePerson,listNameAndActive);
    }
}
