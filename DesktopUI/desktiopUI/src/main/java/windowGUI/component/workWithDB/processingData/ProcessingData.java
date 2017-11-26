package windowGUI.component.workWithDB.processingData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Родительский класс для классов-обработчиков
 * */
public class ProcessingData {
    private static final String NOT_CHOSEN = "Не выбрано";

    /*
     * перегруженные методы, возвращающийе пустой двойной массив, созданы для переопередения
     * */
    public Object[][] getArrayFillTable(String name, int numberColumns){
        return new Object[0][0];
    }

    public Object[][] getArrayFillTable(String nameSite, String namePerson, String startDate, String finishDate, int numberColumns){
        return new Object[0][0];
    }

    public Object[][] getArrayFillTable(int numberColumns){
        return new Object[0][0];
    }

    /*
     * метод, преобразующий список в двойной массив
     * */
    Object[][] convertingListToArray(ArrayList<Object> list, int numberRows, int numberColumns){
        Object[][] arr = new Object[numberRows][numberColumns];
        int numberOfValues = 0;

        for(int i = 0; i < numberColumns; i++) {
            for(int j = 0; j < numberRows; j++) {
                arr[j][i] = list.get(numberOfValues);
                numberOfValues++;
            }
        }

        return arr;
    }

    /*
     * метод, возвращающий список активных элементов
     * */
    ArrayList<String> getListActiveItems(LinkedHashMap<String, Boolean> list){
        Object[] keys = list.keySet().toArray();
        ArrayList<String> listActiveItems = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if(list.get(keys[i])) listActiveItems.add((String) keys[i]);
        }

        return listActiveItems;
    }

    /*
     * метод, возвращающий ID по имени
     * */
    int getIDByName(String name, LinkedHashMap<Integer, String> list){
        if(name == null || name.equals(getNotChosen())) return -1;

        Object[] keysList = list.keySet().toArray();
        int id = -1;

        for (int i = 0; i < list.size(); i++) {
            if(name.equals(list.get(keysList[i]))) id = (int) keysList[i];
        }

        return id;
    }

    /*
     * метод, возвращающий активность по имени
     * */
    boolean getActiveByName(String name, LinkedHashMap<String, Boolean> list){
        if(name == null || name.equals(getNotChosen())) return false;

        Object[] keysList = list.keySet().toArray();
        boolean active = false;

        for (int i = 0; i < list.size(); i++) {
            if(name.equals(keysList[i])) active = list.get(name);
        }

        return active;
    }

    /*
     * метод, возвращающий массив элементов
     * */
    String[] getArrayItems(ArrayList<String> list){
        String[] items = new String[list.size()+1];
        items[0] = getNotChosen();

        for (int i = 0; i < list.size(); i++) items[i+1] = list.get(i);

        return items;
    }

    /*
     * <getters>
     * */
    public static String getNotChosen() {
        return NOT_CHOSEN;
    }
    /*
     * </getters>
     * */
}
