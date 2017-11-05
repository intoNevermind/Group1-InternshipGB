package windowGUI.options.workSQL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
    private static final ArrayList<Date> listFoundDateTimePages = pagesTable.getListFoundDateTime();

    private static final SitesTable sitesTable = new SitesTable();
    private static final HashMap<Integer,String> listIDAndNameSites = sitesTable.getListIDAndName();


    public LinkedHashMap<Date,Integer> getPagesIDFoundDateTime(){
        LinkedHashMap<Date,Integer> listPagesIDFoundDateTime = new LinkedHashMap<>();

        for (int i = 0; i < listFoundDateTimePages.size(); i++) {
            listPagesIDFoundDateTime.put(listFoundDateTimePages.get(i), listIDPages.get(i));
        }
//        System.out.println(listPagesIDFoundDateTime);
        return listPagesIDFoundDateTime;
    }
    public static ArrayList<String> getListAbbreviatedFoundDateTime() {
        ArrayList<String> listAbbreviatedFoundDateTime = new ArrayList<>();

        for (int i = 0; i < listFoundDateTimePages.size(); i++) {
            String str = new SimpleDateFormat("yyyy-MM-dd").format(listFoundDateTimePages.get(i));
            listAbbreviatedFoundDateTime.add(str.substring(0,10));
        }
        return listAbbreviatedFoundDateTime;
    }

    public Object[][] fillDailyTable(String nameSite, String namePerson, String strStartDate, String strFinishDate){
        HashMap<String, Integer> listDateAndCountNewPages = new HashMap<>();
        ArrayList<String> listIntervalStartAndFinishDate = getListIntervalStartAndFinishDate(strStartDate,strFinishDate);

        Object[] keyListPagesIDFoundDateTime = getPagesIDFoundDateTime().keySet().toArray();

        ArrayList<Integer> listIntervalPagesIDFoundDateTime = new ArrayList<>();
        for (int i = 0; i < listIntervalStartAndFinishDate.size(); i++) {
            for (int j = 0; j < keyListPagesIDFoundDateTime.length; j++) {
                if(listIntervalStartAndFinishDate.get(i).equals(new SimpleDateFormat("yyyy-MM-dd").format(keyListPagesIDFoundDateTime[j]))){
                    listIntervalPagesIDFoundDateTime.add(getPagesIDFoundDateTime().get(keyListPagesIDFoundDateTime[j]));

                }
            }
        }

        Object[][] arrDateAndCountNewPages = new Object[listIntervalStartAndFinishDate.size()][listIntervalStartAndFinishDate.size()];
        for (int i = 0; i < getListAbbreviatedFoundDateTime().size(); i++) {

        }
        int count = 0;
        return arrDateAndCountNewPages;
    }


    public ArrayList<String> getListIntervalStartAndFinishDate(String strStartDate, String strFinishDate){
        ArrayList<String> listIntervalStartAndFinishDate = new ArrayList<>();
        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            Date startDate= format.parse(strStartDate);
            Date finishDate= format.parse(strFinishDate);

            for (int i = 0; i < getListAbbreviatedFoundDateTime().size(); i++) {
                if(startDate.before(listFoundDateTimePages.get(0))){
                    strStartDate = getListAbbreviatedFoundDateTime().get(0);
                }
                if(finishDate.before(startDate)){
                    strFinishDate = strStartDate;
                }
                if(strStartDate.equals(getListAbbreviatedFoundDateTime().get(i))){
                    for (int j = i; j < getListAbbreviatedFoundDateTime().size(); j++) {
                        if(!strFinishDate.equals(getListAbbreviatedFoundDateTime().get(j))){
                            listIntervalStartAndFinishDate.add(getListAbbreviatedFoundDateTime().get(j));
                        }else {
                            listIntervalStartAndFinishDate.add(getListAbbreviatedFoundDateTime().get(j));
                            break;
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listIntervalStartAndFinishDate;
    }

    public Object[][] fillGeneralTable(String nameSite){
        HashMap<String, Integer> listPersonNameAndRank = new HashMap<>();

        Object[][] arrListPersonNameAndRank = new Object[getDistinctNamePerson().size()][getDistinctNamePerson().size()];

        int count = 0;
        for (int i = 0; i < getNameSites().size(); i++) {
            for (int j = 0; j <getDistinctNamePerson().size(); j++) {
                if(i > 0 && getNamePerson().get(i-1) != getNamePerson().get(i)){
                  count = 0;
                }
                if(nameSite == getNameSites().get(i) && getDistinctNamePerson().get(j) == getNamePerson().get(i)){
                    count += listRankPersonPageRank.get(i);
                    listPersonNameAndRank.put(getDistinctNamePerson().get(j), count);
                }
            }
        }

        Object[] keyListPersonNameAndRank = listPersonNameAndRank.keySet().toArray();
        for (int i = 0; i < keyListPersonNameAndRank.length; i++) {
            for (int j = 0; j < listPersonNameAndRank.size(); j++) {
                if(j == 0){
                    arrListPersonNameAndRank[i][j] = keyListPersonNameAndRank[i];
                }else {
                    arrListPersonNameAndRank[i][j] = listPersonNameAndRank.get(getDistinctNamePerson().get(i));
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

    private ArrayList<String> getDistinctNamePerson(){
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
