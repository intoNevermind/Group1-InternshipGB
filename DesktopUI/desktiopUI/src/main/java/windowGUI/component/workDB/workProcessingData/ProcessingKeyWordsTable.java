package windowGUI.component.workDB.workProcessingData;

import windowGUI.component.workDB.tables.KeyWordsTable;
import windowGUI.component.workDB.tables.PersonTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ProcessingKeyWordsTable extends  ProcessingData{
    private static final PersonTable TABLE_PERSON = new PersonTable();
    private static final LinkedHashMap<Integer,String> LIST_ID_AND_NAME_PERSON = TABLE_PERSON.getListIDAndName();

    private static final KeyWordsTable TABLE_KEY_WORDS = new KeyWordsTable();
    private static final ArrayList<Integer> LIST_PERSON_ID_KEY_WORDS = TABLE_KEY_WORDS.getListPersonID();
    private static final ArrayList<String> LIST_NAME_KEY_WORDS = TABLE_KEY_WORDS.getListName();
    private static final LinkedHashMap<Integer, String> listIDAndNameKeyWords = TABLE_KEY_WORDS.getListIDAndName();


    @Override
    public Object[][] getArrayFillTable(String strNamePerson, int countColumn){
        if(strNamePerson == null || countColumn < 1) return super.getArrayFillTable(strNamePerson,countColumn);
        ArrayList<String> listNameKeyWordsByNamePerson = getListNameKeyWordsByNamePerson(strNamePerson);
        return convertingListToArray(listNameKeyWordsByNamePerson,countColumn);
    }

    private ArrayList<String> getListNameKeyWordsByNamePerson(String strNamePerson){
        ArrayList<String> listNameKeyWordsByNamePerson = new ArrayList<>();
        Object[] keyListIDAndNamePerson = LIST_ID_AND_NAME_PERSON.keySet().toArray();

        for (int i = 0; i < LIST_PERSON_ID_KEY_WORDS.size(); i++) {
            for (int j = 0; j < LIST_ID_AND_NAME_PERSON.size(); j++) {
                if (strNamePerson.equals(LIST_ID_AND_NAME_PERSON.get(j+1)) && keyListIDAndNamePerson[j] == LIST_PERSON_ID_KEY_WORDS.get(i))
                    listNameKeyWordsByNamePerson.add(LIST_NAME_KEY_WORDS.get(i));

            }
        }
        return listNameKeyWordsByNamePerson;
    }

    public int getIDKeyWordByNameKeyWord(String nameKeyWord){
        return getIDByName(nameKeyWord,listIDAndNameKeyWords);
    }
}
