package windowGUI.component.workDB.workProcessingData;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ProcessingData {

    private static final String NOT_CHOSEN = "Не выбрано";

    public Object[][] getArrayFillTable(String strName, int countColumn){
        return new Object[0][0];
    }


    public Object[][] getArrayFillTable(String strNameSite, String strNamePerson, String strStartDate, String strFinishDate, int countColumn){
        return new Object[0][0];
    }

    public Object[][] getArrayFillTable(int countColumn){
        return new Object[0][0];
    }

    Object[][] convertingListToArray(LinkedHashMap list, int columnCount){
        Object[] keyList = list.keySet().toArray();
        Object[][] arr = new Object[list.size()][columnCount];

        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < columnCount; j++) {
                if(j == 0) arr[i][j] = keyList[i];
                else arr[i][j] = list.get(keyList[i]);
            }
        }
        return arr;
    }

    Object[][] convertingListToArray(ArrayList list, int columnCount){
        Object[][] arr = new Object[list.size()][columnCount];

        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < columnCount; j++) {
                arr[i][j] = list.get(i);
            }
        }
        return arr;
    }

    public static String getNotChosen() {
        return NOT_CHOSEN;
    }

    int getIDByName(String name,LinkedHashMap<Integer,String> list ){
        if (name == null || name.equals(getNotChosen())) return -1;
        int id = -1;
        Object[] keyList = list.keySet().toArray();
        for (int i = 0; i < list.size(); i++) {
            if(name.equals(list.get(i+1))){
                id = (int) keyList[i];
            }
        }
        return id;
    }
}
