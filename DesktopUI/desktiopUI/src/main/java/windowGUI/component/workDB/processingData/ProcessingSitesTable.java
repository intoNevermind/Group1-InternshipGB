package windowGUI.component.workDB.processingData;

import windowGUI.component.workDB.tables.SitesTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Класс-обработчик, отвечающий за обработку данных таблицы Sites
 * */
public class ProcessingSitesTable extends ProcessingData{
    private static final SitesTable SITES_TABLE = SitesTable.getInstance();
    private static final ArrayList<String> LIST_NAME_FROM_SITES = SITES_TABLE.getListName();
    private static final LinkedHashMap<Integer, String> LIST_ID_AND_NAME_FROM_SITES = SITES_TABLE.getListIDAndName();
    private static final LinkedHashMap<String, String> LIST_NAME_AND_URL_FROM_SITES = SITES_TABLE.getListNameAndURL();
    private static final LinkedHashMap<String, Boolean> LIST_NAME_AND_ACTIVE_FROM_SITES = SITES_TABLE.getListNameAndActive();

    /*
     * метод, возвращающий массив имен сайтов для выпадающего списка сайтов, в классах Statistics
     * */
    public String[] getArrayNameSites(){
        String[] nameSites = new String[LIST_NAME_FROM_SITES.size()+1];
        nameSites[0] = getNotChosen();

        for (int i = 0; i < LIST_NAME_FROM_SITES.size(); i++) {
            if(LIST_NAME_AND_ACTIVE_FROM_SITES.get(LIST_NAME_FROM_SITES.get(i)))
            nameSites[i+1] = LIST_NAME_FROM_SITES.get(i);
        }
        return nameSites;
    }

    /*
     * метод, возвращающий двумерный массив сайтов для заполнения JTable класса SitesDirectory строками
     * */
    @Override
    public Object[][] getArrayFillTable(int numberColumn){
        if(numberColumn < 1) return super.getArrayFillTable(numberColumn);

        return convertingListToArray(LIST_NAME_FROM_SITES,numberColumn);
    }

    /*
     * метод, возвращающий ID сайта по имени сайта
     * */
    public int getIDSiteByNameSite(String nameSites){
        return getIDByName(nameSites, LIST_ID_AND_NAME_FROM_SITES);
    }

    /*
     * метод, возвращающий URL сайта по имени сайта
     * */
    public String getURLSiteByNameSite(String nameSites){
        if (nameSites == null || nameSites.equals(getNotChosen())) return "";

        String URL = "";

        Object[] keysListNameAndURL = LIST_NAME_AND_URL_FROM_SITES.keySet().toArray();
        for (int i = 0; i < LIST_NAME_AND_URL_FROM_SITES.size(); i++) {
            if(nameSites.equals(keysListNameAndURL[i])){
                URL = LIST_NAME_AND_URL_FROM_SITES.get(nameSites);
            }
        }
        return URL;
    }

    /*
     * метод, возвращающий активность сайта по имени сайта
     * */
    public boolean getActiveSiteByNameSite(String nameSites){
        return getActiveByName(nameSites, LIST_NAME_AND_ACTIVE_FROM_SITES);
    }
}
