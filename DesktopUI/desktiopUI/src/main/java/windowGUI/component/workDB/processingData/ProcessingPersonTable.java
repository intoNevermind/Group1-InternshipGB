package windowGUI.component.workDB.processingData;

import windowGUI.component.workDB.tables.PersonsTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Класс-обработчик, отвечающий за обработку данных таблицы Person
 * */
public class ProcessingPersonTable extends ProcessingData{
    private static final PersonsTable PERSONS_TABLE = PersonsTable.getInstance();
    private static final ArrayList<String> LIST_NAME_FROM_PERSONS = PERSONS_TABLE.getListName();
    private static final LinkedHashMap<Integer, String> LIST_ID_AND_NAME_FROM_PERSONS = PERSONS_TABLE.getListIDAndName();
    private static final LinkedHashMap<String, Boolean> LIST_NAME_AND_ACTIVE_FROM_PERSONS = PERSONS_TABLE.getListNameAndActive();

    /*
     * метод, возвращающий массив имен личностей для выпадающего списка личностей, в классах Directory и Statistics
     * */
    public String[] getArrayNamePersons(){
        String[] namePersons = new String[LIST_NAME_FROM_PERSONS.size()+1];
        namePersons[0] = getNotChosen();

        for (int i = 0; i < LIST_NAME_FROM_PERSONS.size(); i++) {
            if(LIST_NAME_AND_ACTIVE_FROM_PERSONS.get(LIST_NAME_FROM_PERSONS.get(i)))
            namePersons[i+1] = LIST_NAME_FROM_PERSONS.get(i);
        }
        return namePersons;
    }

    /*
     * метод, возвращающий двумерный массив имен личностей для заполнения JTable класса PersonsDirectory строками
     * */
    @Override
    public Object[][] getArrayFillTable(int numberColumn){
        if(numberColumn < 1) return super.getArrayFillTable(numberColumn);

        return convertingListToArray(LIST_NAME_FROM_PERSONS,numberColumn);
    }

    /*
     * метод, возвращающий ID личности по имени личности
     * */
    public int getIDPersonByNamePerson(String namePerson){
       return getIDByName(namePerson, LIST_ID_AND_NAME_FROM_PERSONS);
    }

    /*
     * метод, возвращающий активность личности по имени личности
     * */
    public boolean getActivePersonByNamePerson(String namePerson){
        return getActiveByName(namePerson, LIST_NAME_AND_ACTIVE_FROM_PERSONS);
    }
}
