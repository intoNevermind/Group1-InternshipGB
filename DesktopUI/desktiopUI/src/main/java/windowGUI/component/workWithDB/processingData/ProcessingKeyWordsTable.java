package windowGUI.component.workWithDB.processingData;

import windowGUI.component.workWithDB.tables.KeyWordsTable;
import windowGUI.component.workWithDB.tables.PersonsTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
* Класс-обработчик, отвечающий за обработку данных таблицы KeyWords
* */
public class ProcessingKeyWordsTable extends ProcessingData{

    /*
     * метод, возвращающий список имен ключевых слов по имени личности, преобразованный в двойной массив, для заполнения JTable класса KeyWordsDirectory строками
     * */
    @Override
    public Object[][] getArrayFillTable(String namePerson, int numberColumns){
        if(namePerson == null || numberColumns < 1) return super.getArrayFillTable(namePerson,numberColumns);

        ArrayList<String> listNameKeyWordsByNamePerson = getListNameKeyWordsByNamePerson(namePerson);

        return convertingListToArray(unionAllValues(listNameKeyWordsByNamePerson), listNameKeyWordsByNamePerson.size(), numberColumns);
    }

    /*
     * метод, объеденяющий все возможные значения таблицы в один список(по порядку зазмещения в колонках)
     * */
    private ArrayList<Object> unionAllValues(ArrayList<String> list){
        ArrayList<Object> listUnionAllValues = new ArrayList<>();

        listUnionAllValues.addAll(list);

        return listUnionAllValues;
    }

    /*
     * метод, определяющий список имен ключевых слов по имени личности
     * */
    private ArrayList<String> getListNameKeyWordsByNamePerson(String namePerson){
        PersonsTable.infoAllPersons();
        KeyWordsTable.infoAllKeyWords();

        LinkedHashMap<Integer, String> listIdAndNameFromPersons = PersonsTable.getListIDAndName();
        ArrayList<Integer> listPersonIdFromKeyWords = KeyWordsTable.getListPersonID();

        ArrayList<String> listNameKeyWordsByNamePerson = new ArrayList<>();
        Object[] keysFromListIDAndNamePersons = listIdAndNameFromPersons.keySet().toArray();

        for (int i = 0; i < listPersonIdFromKeyWords.size(); i++) {
            for (int j = 0; j < listIdAndNameFromPersons.size(); j++) {
                if(namePerson.equals(listIdAndNameFromPersons.get(keysFromListIDAndNamePersons[j]))
                        && keysFromListIDAndNamePersons[j] == listPersonIdFromKeyWords.get(i))
                    listNameKeyWordsByNamePerson.add(KeyWordsTable.getListName().get(i));
            }
        }

        return listNameKeyWordsByNamePerson;
    }

    /*
     * метод, возвращающий список ID ключевго слова по имени ключевого слова
     * */
    public int getIDKeyWordByNameKeyWord(String nameKeyWord){
        KeyWordsTable.infoAllKeyWords();

        return getIDByName(nameKeyWord, KeyWordsTable.getListIDAndName());
    }
}
