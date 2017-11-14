package windowGUI.component.workDB.workProcessingData;

import windowGUI.component.workDB.tables.SitesTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ProcessingSitesTable extends ProcessingData{
    private static final SitesTable TABLE_SITES = SitesTable.getInstance();
    private static final ArrayList<String> LIST_NAME_SITES = TABLE_SITES.getListName();
    private static final LinkedHashMap<Integer, String> listIDAndNameSites = TABLE_SITES.getListIDAndName();
    private static final LinkedHashMap<String, String> listNameAndURL = TABLE_SITES.getListNameAndURL();
    private static final LinkedHashMap<String, Integer> listNameAndActive = TABLE_SITES.getListNameAndActive();

    public String[] getArrayNameSites(){
        String[] str = new String[LIST_NAME_SITES.size()+1];
        str[0] = getNotChosen();
        for (int i = 0; i < LIST_NAME_SITES.size(); i++) {
            str[i+1] = LIST_NAME_SITES.get(i);
        }
        return str;
    }
    @Override
    public Object[][] getArrayFillTable(int countColumn){
        if(countColumn < 1) return super.getArrayFillTable(countColumn);
        return convertingListToArray(LIST_NAME_SITES,countColumn);
    }

    public int getIDSitesByNameSites(String nameSites){
        return getIDByName(nameSites,listIDAndNameSites);
    }

    public String getURLSitesByNameSites(String nameSites){
        if (nameSites == null || nameSites.equals(getNotChosen())) return "";
        String URL = "";
        Object[] keyListNameAndURL = listNameAndURL.keySet().toArray();
        for (int i = 0; i < listNameAndURL.size(); i++) {
            if(nameSites.equals(keyListNameAndURL[i])){
                URL = listNameAndURL.get(nameSites);
            }
        }
        return URL;
    }

    public boolean getActiveSitesByNameSites(String nameSites){
        return getActiveByName(nameSites,listNameAndActive);
    }
}
