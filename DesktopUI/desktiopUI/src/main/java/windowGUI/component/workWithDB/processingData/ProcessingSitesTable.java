package windowGUI.component.workWithDB.processingData;

import windowGUI.component.workWithDB.tables.SitesTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Класс-обработчик, отвечающий за обработку данных таблицы Sites
 * */
public class ProcessingSitesTable extends ProcessingData{

    /*
     * метод, возвращающий массив имен сайтов для выпадающего списка сайтов, в классах Statistics
     * */
    public String[] getArrayNameSites(){
        SitesTable.infoAllSites();
        LinkedHashMap<String, Boolean> listNameAndActiveFromSites = SitesTable.getListNameAndActive();
        ArrayList<String> listActiveNameSites = getListActiveItems(listNameAndActiveFromSites);
        String[] nameSites = new String[listActiveNameSites.size()+1];
        nameSites[0] = getNotChosen();

        for (int i = 0; i < listActiveNameSites.size(); i++) {
            nameSites[i+1] = listActiveNameSites.get(i);
        }
        return nameSites;
    }

    /*
     * метод, возвращающий двумерный массив сайтов для заполнения JTable класса SitesDirectory строками
     * */
    @Override
    public Object[][] getArrayFillTable(int numberColumn){
        SitesTable.infoAllSites();
        LinkedHashMap<String, String> listNameAndUrlFromSites = SitesTable.getListNameAndURL();
        ArrayList<Boolean> listActiveFromSites = SitesTable.getListActive();
        if(numberColumn < 1) return super.getArrayFillTable(numberColumn);

        return convertingListToArray(listNameAndUrlFromSites,listActiveFromSites, numberColumn);
    }

    /*
     * метод, возвращающий ID сайта по имени сайта
     * */
    public int getIDSiteByNameSite(String nameSites){
        SitesTable.infoAllSites();
        LinkedHashMap<Integer, String> listIdAndNameFromSites = SitesTable.getListIDAndName();

        return getIDByName(nameSites, listIdAndNameFromSites);
    }

    /*
     * метод, возвращающий URL сайта по имени сайта
     * */
    public String getURLSiteByNameSite(String nameSites){
        SitesTable.infoAllSites();
        LinkedHashMap<String, String> listNameAndUrlFromSites = SitesTable.getListNameAndURL();

        if (nameSites == null || nameSites.equals(getNotChosen())) return "";

        String URL = "";
        Object[] keysListNameAndURL = listNameAndUrlFromSites.keySet().toArray();

        for (int i = 0; i < listNameAndUrlFromSites.size(); i++) {
            if(nameSites.equals(keysListNameAndURL[i])){
                URL = listNameAndUrlFromSites.get(nameSites);
            }
        }
        return URL;
    }

    /*
     * метод, возвращающий активность сайта по имени сайта
     * */
    public boolean getActiveSiteByNameSite(String nameSites){
        SitesTable.infoAllSites();
        LinkedHashMap<String, Boolean> listNameAndActiveFromSites = SitesTable.getListNameAndActive();

        return getActiveByName(nameSites, listNameAndActiveFromSites);
    }
}
