package windowGUI.component.workDB.workProcessingData;

import windowGUI.component.workDB.tables.SitesTable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ProcessingSitesTable extends ProcessingData{
    private static final SitesTable TABLE_SITES = new SitesTable();
    private static final ArrayList<String> LIST_NAME_SITES = TABLE_SITES.getListName();
    private static final LinkedHashMap<Integer, String> listIDAndNameSites = TABLE_SITES.getListIDAndName();

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

    public int getIDSitesByNameSites(String namePerson){
        if (namePerson == null || namePerson.equals(getNotChosen())) return -1;
        int idPerson = -1;
        Object[] keyListIDAndAndNamePerson = listIDAndNameSites.keySet().toArray();
        for (int i = 0; i < listIDAndNameSites.size(); i++) {
            if(namePerson.equals(listIDAndNameSites.get(i+1))){
                idPerson = (int) keyListIDAndAndNamePerson[i];
            }
        }
        return idPerson;
    }

}
