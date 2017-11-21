package windowGUI.component.workDB.processingData;

import windowGUI.component.workDB.tables.PersonsTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Класс-обработчик, отвечающий за обработку данных таблицы Person
 * */
public class ProcessingPersonTable extends ProcessingData{

    private static ArrayList<String> listNameFromPersons;
    private static LinkedHashMap<Integer, String> listIdAndNameFromPersons;
    private static LinkedHashMap<String, Boolean> listNameAndActiveFromPersons;

    public ProcessingPersonTable() {
        System.out.println("конструктор ProcessingPersonTable");
        PersonsTable.infoAllPersons();
        listNameFromPersons = PersonsTable.getListName();
        listIdAndNameFromPersons = PersonsTable.getListIDAndName();
        listNameAndActiveFromPersons = PersonsTable.getListNameAndActive();
    }

    /*
     * метод, возвращающий массив имен личностей для выпадающего списка личностей, в классах Directory и Statistics
     * */
    public String[] getArrayNamePersons(){
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
        if(numberColumn < 1) return super.getArrayFillTable(numberColumn);

        return convertingListToArray(listNameFromPersons,numberColumn);
    }

    /*
     * метод, возвращающий ID личности по имени личности
     * */
    public int getIDPersonByNamePerson(String namePerson){
       return getIDByName(namePerson, listIdAndNameFromPersons);
    }

    /*
     * метод, возвращающий активность личности по имени личности
     * */
    public boolean getActivePersonByNamePerson(String namePerson){
        return getActiveByName(namePerson, listNameAndActiveFromPersons);
    }
}
