package windowGUI.options.workSQL;

import java.util.ArrayList;
import java.util.HashMap;

public class ProcessingPersonPageRankTable {

    private static final PersonTable personTable = new PersonTable();
    private static final HashMap<Integer,String> listIDAndNamePerson = personTable.getListIDAndName();

    private static final PersonPageRankTable personPageRankTable = new PersonPageRankTable();
    private static final ArrayList<Integer> listPersonIDPersonPagePank = personPageRankTable.getListPersonID();
    private static final ArrayList<Integer> listPageIDPersonPagePank = personPageRankTable.getListPageID();
    private static final ArrayList<Integer> listRankPersonPageRank = personPageRankTable.getListRank();

    private static final PagesTable pagesTable = new PagesTable();
    private static final ArrayList<Integer> listSitesIDPages = pagesTable.getListSiteID();
    private static final ArrayList<Integer> listIDPages = pagesTable.getListID();

    private static final SitesTable sitesTable = new SitesTable();
    private static final HashMap<Integer,String> listIDAndNameSites = sitesTable.getListIDAndName();

    public Object[][] fillGeneralTable(String nameSite){
        HashMap<String, Integer> listPersonNameAndRank = new HashMap<>();

        ArrayList<String> namePerson = getDistinctNamePerson();
        Object[][] arrListPersonNameAndRank = new Object[getDistinctNamePerson().size()][getDistinctNamePerson().size()];

        int count = 0;
        for (int i = 0; i < getNameSites().size(); i++) {
            for (int j = 0; j < namePerson.size(); j++) {
                if(i > 0 && getNamePerson().get(i-1) != getNamePerson().get(i)){
                  count = 0;
                }
                if(nameSite == getNameSites().get(i) && namePerson.get(j) == getNamePerson().get(i)){
                    count += listRankPersonPageRank.get(i);
                    listPersonNameAndRank.put(namePerson.get(j), count);
                }
            }
        }

        Object[] keyListPersonNameAndRank = listPersonNameAndRank.keySet().toArray();
        for (int i = 0; i < keyListPersonNameAndRank.length; i++) {
            for (int j = 0; j < listPersonNameAndRank.size(); j++) {
                if(j == 0){
                    arrListPersonNameAndRank[i][j] = keyListPersonNameAndRank[i];
                }else {
                    arrListPersonNameAndRank[i][j] = listPersonNameAndRank.get(namePerson.get(i));
                }
            }
        }
        return arrListPersonNameAndRank;
    }

    private ArrayList<String> getNamePerson(){
        ArrayList<String> listNamePerson = new ArrayList<>();

        Object[] keyNamePerson = listIDAndNamePerson.keySet().toArray();

        for (int i = 0; i < listPersonIDPersonPagePank.size(); i++) {
            for (int j = 0; j < keyNamePerson.length; j++) {
                if(keyNamePerson[j] == listPersonIDPersonPagePank.get(i)){
                    listNamePerson.add(listIDAndNamePerson.get(j+1));
                }
            }
        }
        return listNamePerson;
    }

    public ArrayList<String> getDistinctNamePerson(){
        ArrayList<String> listDistinctNamePerson = new ArrayList<>();

        for (int i = 0; i < getNamePerson().size(); i++) {
            if(!listDistinctNamePerson.contains(getNamePerson().get(i))){
                listDistinctNamePerson.add(getNamePerson().get(i));
            }
        }
        return listDistinctNamePerson;
    }

    private ArrayList<String> getNameSites(){
        ArrayList<String> listNameSites = new ArrayList<>();

        Object[] keyNameSites = listIDAndNameSites.keySet().toArray();

        for (int i = 0; i < getSitesIDPages().size(); i++) {
            for (int j = 0; j < keyNameSites.length; j++) {
                if(keyNameSites[j] == getSitesIDPages().get(i)){
                    listNameSites.add(listIDAndNameSites.get(j+1));
                }
            }
        }
        return listNameSites;
    }

    public ArrayList<String> getDistinctNameSites(){
        ArrayList<String> listDistinctNameSites = new ArrayList<>();
        for (int i = 0; i < getNameSites().size(); i++) {
            if(!listDistinctNameSites.contains(getNameSites().get(i))){
                listDistinctNameSites.add(getNameSites().get(i));
            }
        }
        return listDistinctNameSites;
    }

    private ArrayList<Integer> getSitesIDPages(){
        ArrayList<Integer> listIDSites = new ArrayList<>();

        for (int i = 0; i < listPageIDPersonPagePank.size(); i++) {
            for (int j = 0; j < listIDPages.size(); j++) {
                if(listPageIDPersonPagePank.get(i) == listIDPages.get(j)){
                    listIDSites.add(listSitesIDPages.get(j));
                }
            }
        }
        return listIDSites;
    }
}
