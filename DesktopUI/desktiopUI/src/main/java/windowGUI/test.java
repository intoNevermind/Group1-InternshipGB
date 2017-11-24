package windowGUI;

import windowGUI.component.workDB.tables.SitesTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class test {
    public static void main(String[] args) {
        SitesTable.infoAllSites();
        LinkedHashMap<String, String> listNameAndUrlFromSites = SitesTable.getListNameAndURL();
        ArrayList<Boolean> listActiveFromSites = SitesTable.getListActive();
//        System.out.println(Arrays.deepToString(convertingListToArray(listNameAndUrlFromSites, 3, listActiveFromSites)));
    }

//    private static  Object[][] convertingListToArray(LinkedHashMap<String, String> list, int numberColumn, ArrayList<Boolean> active){
//        Object[][] arr = new Object[list.size()][numberColumn];
//        ArrayList<String> listTest = new ArrayList<>();
//
//        listTest.addAll(list.keySet());
//        listTest.addAll(list.values());
//        for (int i = 0; i < active.size(); i++) {
//            if(active.get(i)) listTest.add("Да");
//            else listTest.add("Нет");
//        }
//
//        System.out.println(listTest);
//        int k = 0;
//        for(int j = 0; j < numberColumn; j++) {
//          for(int i = 0; i < list.size(); i++) {
//               arr[i][j] = listTest.get(k);
//               k++;
//            }
//        }
//        return arr;
//    }
}
