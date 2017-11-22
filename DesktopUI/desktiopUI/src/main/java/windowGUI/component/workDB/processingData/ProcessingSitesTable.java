package windowGUI.component.workDB.processingData;

import windowGUI.component.workDB.tables.SitesTable;

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
        ArrayList<String> listNameFromSites = SitesTable.getListName();
        LinkedHashMap<String, Boolean> listNameAndActiveFromSites = SitesTable.getListNameAndActive();

        String[] nameSites = new String[listNameFromSites.size()+1];
        nameSites[0] = getNotChosen();

        for (int i = 0; i < listNameFromSites.size(); i++) {
            if(listNameAndActiveFromSites.get(listNameFromSites.get(i)))
            nameSites[i+1] = listNameFromSites.get(i);
        }
        return nameSites;
    }

    /*
     * метод, возвращающий двумерный массив сайтов для заполнения JTable класса SitesDirectory строками
     * */
    @Override
    public Object[][] getArrayFillTable(int numberColumn){
        SitesTable.infoAllSites();
        ArrayList<String> listNameFromSites = SitesTable.getListName();

        if(numberColumn < 1) return super.getArrayFillTable(numberColumn);

        return convertingListToArray(listNameFromSites,numberColumn);
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
