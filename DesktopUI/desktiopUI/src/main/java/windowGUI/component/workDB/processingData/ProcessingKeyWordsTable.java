package windowGUI.component.workDB.processingData;

import windowGUI.component.workDB.tables.KeyWordsTable;
import windowGUI.component.workDB.tables.PersonsTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
* Класс-обработчик, отвечающий за обработку данных таблицы KeyWords
* */
public class ProcessingKeyWordsTable extends ProcessingData{
    private static final PersonsTable PERSONS_TABLE = PersonsTable.getInstance();
    private static final LinkedHashMap<Integer,String> LIST_ID_AND_NAME_FROM_PERSONS = PERSONS_TABLE.getListIDAndName();

    private static final KeyWordsTable KEY_WORDS_TABLE = KeyWordsTable.getInstance();
    private static final ArrayList<Integer> LIST_PERSON_ID_FROM_KEY_WORDS = KEY_WORDS_TABLE.getListPersonID();
    private static final ArrayList<String> LIST_NAME_FROM_KEY_WORDS = KEY_WORDS_TABLE.getListName();
    private static final LinkedHashMap<Integer, String> LIST_ID_AND_NAME_FROM_KEY_WORDS = KEY_WORDS_TABLE.getListIDAndName();

    /*
     * метод, возвращающий список имен ключевых слов по имени личности, преобразованный в двойной массив, для заполнения JTable класса KeyWordsDirectory строками
     * */
    @Override
    public Object[][] getArrayFillTable(String namePerson, int numberColumn){
        if(namePerson == null || numberColumn < 1) return super.getArrayFillTable(namePerson,numberColumn);

        ArrayList<String> listNameKeyWordsByNamePerson = getListNameKeyWordsByNamePerson(namePerson);
        return convertingListToArray(listNameKeyWordsByNamePerson,numberColumn);
    }

    /*
     * метод, определяющий список имен ключевых слов по имени личности
     * */
    private ArrayList<String> getListNameKeyWordsByNamePerson(String namePerson){
        ArrayList<String> listNameKeyWordsByNamePerson = new ArrayList<>();
        Object[] keysFromListIDAndNamePersons = LIST_ID_AND_NAME_FROM_PERSONS.keySet().toArray();

        for (int i = 0; i < LIST_PERSON_ID_FROM_KEY_WORDS.size(); i++) {
            for (int j = 0; j < LIST_ID_AND_NAME_FROM_PERSONS.size(); j++) {
                if (namePerson.equals(LIST_ID_AND_NAME_FROM_PERSONS.get(j+1))
                        && keysFromListIDAndNamePersons[j] == LIST_PERSON_ID_FROM_KEY_WORDS.get(i))
                    listNameKeyWordsByNamePerson.add(LIST_NAME_FROM_KEY_WORDS.get(i));
            }
        }
        return listNameKeyWordsByNamePerson;
    }

    /*
     * метод, возвращающий список ID ключевго слова по имени ключевого слова
     * */
    public int getIDKeyWordByNameKeyWord(String nameKeyWord){
        return getIDByName(nameKeyWord, LIST_ID_AND_NAME_FROM_KEY_WORDS);
    }
}
