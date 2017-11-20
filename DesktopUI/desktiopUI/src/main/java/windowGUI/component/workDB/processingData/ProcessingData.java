package windowGUI.component.workDB.processingData;

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
    public Object[][] getArrayFillTable(String name, int numberColumn){
        return new Object[0][0];
    }
    public Object[][] getArrayFillTable(String nameSite, String namePerson, String startDate, String finishDate, int numberColumn){
        return new Object[0][0];
    }
    public Object[][] getArrayFillTable(int numberColumn){
        return new Object[0][0];
    }

    /*
     * метод, преобразующий связанный список в двойной массив
     * */
    Object[][] convertingListToArray(LinkedHashMap list, int numberCount){
        Object[] keysList = list.keySet().toArray();
        Object[][] arr = new Object[list.size()][numberCount];

        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < numberCount; j++) {
                if(j == 0) arr[i][j] = keysList[i];
                else arr[i][j] = list.get(keysList[i]);
            }
        }
        return arr;
    }

    /*
     * метод, преобразующий список в двойной массив
     * */
    Object[][] convertingListToArray(ArrayList list, int numberColumn){
        Object[][] arr = new Object[list.size()][numberColumn];

        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < numberColumn; j++) {
                arr[i][j] = list.get(i);
            }
        }
        return arr;
    }

    /*
     * метод, возвращающий ID по имени
     * */
    int getIDByName(String name,LinkedHashMap<Integer,String> list ){
        if (name == null || name.equals(getNotChosen())) return -1;

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
    boolean getActiveByName(String name, LinkedHashMap<String,Boolean> list){
        if (name == null || name.equals(getNotChosen())) return false;

        Object[] keysList = list.keySet().toArray();
        boolean active = false;

        for (int i = 0; i < list.size(); i++) {
            if(name.equals(keysList[i]))active = list.get(name);
        }
        return active;
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
