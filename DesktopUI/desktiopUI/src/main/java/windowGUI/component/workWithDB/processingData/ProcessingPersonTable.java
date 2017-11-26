package windowGUI.component.workWithDB.processingData;

import windowGUI.component.workWithDB.tables.PersonsTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
/*
 * Класс-обработчик, отвечающий за обработку данных таблицы Person
 * */
public class ProcessingPersonTable extends ProcessingData{

    /*
     * метод, возвращающий массив имен личностей для выпадающего списка личностей, в классах Directory и Statistics
     * */
    public String[] getArrayNamePersons(){
        PersonsTable.infoAllPersons();

        return super.getArrayItems(getListActiveItems(PersonsTable.getListNameAndActive()));
    }

    /*
     * метод, возвращающий двумерный массив имен личностей для заполнения JTable класса PersonsDirectory строками
     * */
    @Override
    public Object[][] getArrayFillTable(int numberColumns){
        PersonsTable.infoAllPersons();
        LinkedHashMap<String, Boolean> listNameAndActiveFromPersons = PersonsTable.getListNameAndActive();

        if(numberColumns < 1) return super.getArrayFillTable(numberColumns);

        return convertingListToArray(unionAllValues(listNameAndActiveFromPersons), listNameAndActiveFromPersons.size(), numberColumns);
    }

    /*
     * метод, объеденяющий все возможные значения таблицы в один список(по порядку зазмещения в колонках)
     * */
    private ArrayList<Object> unionAllValues(LinkedHashMap<String, Boolean> list){
        ArrayList<Object> listUnionAllValues = new ArrayList<>();
        Object[] keys = list.keySet().toArray();

        listUnionAllValues.addAll(list.keySet());
        for (int i = 0; i <list.size(); i++) {
            if(list.get(keys[i])) listUnionAllValues.add("Активен");
            else listUnionAllValues.add("Не активен");
        }

        return listUnionAllValues;
    }

    /*
     * метод, возвращающий ID личности по имени личности
     * */
    public int getIDPersonByNamePerson(String namePerson){
        PersonsTable.infoAllPersons();

        return getIDByName(namePerson, PersonsTable.getListIDAndName());
    }

    /*
     * метод, возвращающий активность личности по имени личности
     * */
    public boolean getActivePersonByNamePerson(String namePerson){
        PersonsTable.infoAllPersons();

        return getActiveByName(namePerson, PersonsTable.getListNameAndActive());
    }
}
