package windowGUI.component.workDB.processingData;

import windowGUI.component.workDB.tables.KeyWordsTable;
import windowGUI.component.workDB.tables.PersonsTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
* Класс-обработчик, отвечающий за обработку данных таблицы KeyWords
* */
public class ProcessingKeyWordsTable extends ProcessingData{
    private static LinkedHashMap<Integer,String> listIdAndNameFromPersons;
    private static ArrayList<Integer> listPersonIdFromKeyWords;
    private static ArrayList<String> listNameFromKeyWords;
    private static LinkedHashMap<Integer, String> listIdAndNameFromKeyWords;

    public ProcessingKeyWordsTable() {
        KeyWordsTable.infoAllKeyWords();
        listPersonIdFromKeyWords = KeyWordsTable.getListPersonID();
        listNameFromKeyWords = KeyWordsTable.getListName();
        listIdAndNameFromKeyWords = KeyWordsTable.getListIDAndName();

        PersonsTable.infoAllPersons();
        listIdAndNameFromPersons = PersonsTable.getListIDAndName();
    }

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
        Object[] keysFromListIDAndNamePersons = listIdAndNameFromPersons.keySet().toArray();

        for (int i = 0; i < listPersonIdFromKeyWords.size(); i++) {
            for (int j = 0; j < listIdAndNameFromPersons.size(); j++) {
                if (namePerson.equals(listIdAndNameFromPersons.get(keysFromListIDAndNamePersons[j]))
                        && keysFromListIDAndNamePersons[j] == listPersonIdFromKeyWords.get(i))
                    listNameKeyWordsByNamePerson.add(listNameFromKeyWords.get(i));
            }
        }
        return listNameKeyWordsByNamePerson;
    }

    /*
     * метод, возвращающий список ID ключевго слова по имени ключевого слова
     * */
    public int getIDKeyWordByNameKeyWord(String nameKeyWord){
        return getIDByName(nameKeyWord, listIdAndNameFromKeyWords);
    }
}
