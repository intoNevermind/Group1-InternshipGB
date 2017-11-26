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

        return super.getArrayItems(getListActiveItems(SitesTable.getListNameAndActive()));
    }

    /*
     * метод, возвращающий двумерный массив сайтов для заполнения JTable класса SitesDirectory строками
     * */
    @Override
    public Object[][] getArrayFillTable(int numberColumns){
        SitesTable.infoAllSites();
        LinkedHashMap<String, String> listNameAndUrlFromSites = SitesTable.getListNameAndURL();

        if(numberColumns < 1) return super.getArrayFillTable(numberColumns);

        return convertingListToArray(unionAllValues(listNameAndUrlFromSites, SitesTable.getListActive()), listNameAndUrlFromSites.size(), numberColumns);
    }

    /*
     * метод, объеденяющий все возможные значения таблицы в один список(по порядку зазмещения в колонках)
     * */
    private ArrayList<Object> unionAllValues(LinkedHashMap<String, String> list, ArrayList<Boolean> active){
        ArrayList<Object> listUnionAllValues = new ArrayList<>();

        listUnionAllValues.addAll(list.keySet());
        listUnionAllValues.addAll(list.values());
        for (int i = 0; i < active.size(); i++) {
            if(active.get(i)) listUnionAllValues.add("Активен");
            else listUnionAllValues.add("Не активен");
        }

        return listUnionAllValues;
    }

    /*
     * метод, возвращающий ID сайта по имени сайта
     * */
    public int getIDSiteByNameSite(String nameSites){
        SitesTable.infoAllSites();

        return getIDByName(nameSites, SitesTable.getListIDAndName());
    }

    /*
     * метод, возвращающий URL сайта по имени сайта
     * */
    public String getURLSiteByNameSite(String nameSites){
        SitesTable.infoAllSites();
        LinkedHashMap<String, String> listNameAndUrlFromSites = SitesTable.getListNameAndURL();

        if(nameSites == null || nameSites.equals(getNotChosen())) return "";

        String URL = "";
        Object[] keysListNameAndURL = listNameAndUrlFromSites.keySet().toArray();

        for (int i = 0; i < listNameAndUrlFromSites.size(); i++) {
            if(nameSites.equals(keysListNameAndURL[i])) URL = listNameAndUrlFromSites.get(nameSites);
        }
        return URL;
    }

    /*
     * метод, возвращающий активность сайта по имени сайта
     * */
    public boolean getActiveSiteByNameSite(String nameSites){
        SitesTable.infoAllSites();

        return getActiveByName(nameSites, SitesTable.getListNameAndActive());
    }
}
