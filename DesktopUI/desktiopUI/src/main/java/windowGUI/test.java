package windowGUI;

import windowGUI.component.workWithDB.tables.SitesTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class test {
    public static void main(String[] args) {
        SitesTable.infoAllSites();
        LinkedHashMap<String, String> listNameAndUrlFromSites = SitesTable.getListNameAndURL();
        ArrayList<Boolean> listActiveFromSites = SitesTable.getListActive();
//        System.out.println(Arrays.deepToString(convertingListToArray(listNameAndUrlFromSites, 3, listActiveFromSites)));
//        test();
    }

//    public static void test(){
//        SitesTable.infoAllSites();
//
//        LinkedHashMap<String, Boolean> listNameAndActiveFromSites = SitesTable.getListNameAndActive();
//        ArrayList<String> listActiveNameSites = new ArrayList<>();
//
//        Object[] keys = listNameAndActiveFromSites.keySet().toArray();
//
//        for (int i = 0; i < listNameAndActiveFromSites.size(); i++) {
//            if(listNameAndActiveFromSites.get(keys[i])){
//                listActiveNameSites.add((String) keys[i]);
//            }
//        }
//        System.out.println(listActiveNameSites);
//    }

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
