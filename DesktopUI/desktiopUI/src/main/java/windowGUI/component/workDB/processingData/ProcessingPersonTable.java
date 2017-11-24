package windowGUI.component.workDB.processingData;

import windowGUI.component.workDB.tables.PersonsTable;

import java.util.ArrayList;
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
        ArrayList<String> listNameFromPersons = PersonsTable.getListName();
        LinkedHashMap<String, Boolean> listNameAndActiveFromPersons = PersonsTable.getListNameAndActive();

        String[] namePersons = new String[listNameFromPersons.size()+1];
        namePersons[0] = getNotChosen();

        for (int i = 0; i < listNameFromPersons.size(); i++) {
            if(listNameAndActiveFromPersons.get(listNameFromPersons.get(i)))
            namePersons[i+1] = listNameFromPersons.get(i);
        }
        return namePersons;
    }

    /*
     * метод, возвращающий двумерный массив имен личностей для заполнения JTable класса PersonsDirectory строками
     * */
    @Override
    public Object[][] getArrayFillTable(int numberColumn){
        PersonsTable.infoAllPersons();
//        ArrayList<String> listNameFromPersons = PersonsTable.getListName();
        LinkedHashMap<String, Boolean> listNameAndActiveFromPersons = PersonsTable.getListNameAndActive();

        if(numberColumn < 1) return super.getArrayFillTable(numberColumn);

        return convertingListToArray(listNameAndActiveFromPersons,numberColumn);
    }

    /*
     * метод, возвращающий ID личности по имени личности
     * */
    public int getIDPersonByNamePerson(String namePerson){
        PersonsTable.infoAllPersons();
        LinkedHashMap<Integer, String> listIdAndNameFromPersons = PersonsTable.getListIDAndName();

       return getIDByName(namePerson, listIdAndNameFromPersons);
    }

    /*
     * метод, возвращающий активность личности по имени личности
     * */
    public boolean getActivePersonByNamePerson(String namePerson){
        PersonsTable.infoAllPersons();
        LinkedHashMap<String, Boolean> listNameAndActiveFromPersons = PersonsTable.getListNameAndActive();

        return getActiveByName(namePerson, listNameAndActiveFromPersons);
    }
}
