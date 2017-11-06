package windowGUI.options.workSQL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class ProcessingPersonPageRankTable {

    private static final PersonTable personTable = new PersonTable();
    private static final LinkedHashMap<Integer,String> listIDAndNamePerson = personTable.getListIDAndName();

    private static final PersonPageRankTable personPageRankTable = new PersonPageRankTable();
    private static final ArrayList<Integer> listPersonIDPersonPagePank = personPageRankTable.getListPersonID();
    private static final ArrayList<Integer> listPageIDPersonPagePank = personPageRankTable.getListPageID();
    private static final ArrayList<Integer> listRankPersonPageRank = personPageRankTable.getListRank();

    private static final PagesTable pagesTable = new PagesTable();
    private static final ArrayList<Integer> listSitesIDPages = pagesTable.getListSiteID();
    private static final ArrayList<Integer> listIDPages = pagesTable.getListID();
    private static final ArrayList<Date> listFoundDateTimePages = pagesTable.getListFoundDateTime();
    private static final LinkedHashMap<Date,Integer> listIDAndFoundDateTimePages = pagesTable.getListFoundDateTimeAndID();

    private static final SitesTable sitesTable = new SitesTable();
    private static final LinkedHashMap<Integer,String> listIDAndNameSites = sitesTable.getListIDAndName();

    /*
    * Метод, возвращающий двумерный массив для передачи в конструктор таблицы общей статистики
    * */
    public Object[][] fillGeneralTable(String nameSite){
        LinkedHashMap<String, Integer> listPersonNameAndRank = getListPersonNameAndRank(nameSite);
        Object[] keyListPersonNameAndRank = listPersonNameAndRank.keySet().toArray();
        Object[][] arrListPersonNameAndRank = new Object[listPersonNameAndRank.size()][2];//количество столбцов таблицы Имя и Кол-во упоминаний

        for (int i = 0; i < listPersonNameAndRank.size(); i++) {
            for (int j = 0; j < 2; j++) {
                if(j == 0){
                    arrListPersonNameAndRank[i][j] = keyListPersonNameAndRank[i];// имя
                }else {
                    arrListPersonNameAndRank[i][j] = listPersonNameAndRank.get(keyListPersonNameAndRank[i]);//значение rank
                }
            }
        }
        return arrListPersonNameAndRank;
    }
    /*
     * Метод, возвращающий двумерный массив для передачи в конструктор таблицы ежедневной статистики
     * */
    public LinkedHashMap<String, Integer> fillDailyTable(String nameSite, String namePerson, String strStartDate, String strFinishDate){
        LinkedHashMap<String, Integer> listDateAndCountNewPages = new LinkedHashMap<>();
        ArrayList<String> listIntervalNameSites = getListIntervalNameSites(strStartDate, strFinishDate);

        ArrayList<String> listIntervalFoundDateTime = getListIntervalFoundDateTime(strStartDate, strFinishDate);
        Object[][] arrDateAndCountNewPages = new Object[100][100];

        int count = 0;
        for (int i = 0; i < listIntervalNameSites.size(); i++) {
            for (int j = 0; j < getDistinctNamePerson().size() ; j++) {
                if(nameSite.equals(listIntervalNameSites.get(i)) && namePerson.equals(getDistinctNamePerson().get(j))){
                    if(i > 0 && listIntervalFoundDateTime.get(i-1) != listIntervalFoundDateTime.get(i)){
                        count = 0;
                    }
                    count++;
                    listDateAndCountNewPages.put(listIntervalFoundDateTime.get(i), count);
                }
            }

        }
        return listDateAndCountNewPages;
    }
    /*
     * Метод, возвращающий Имя сайта(String) по PageID(int) из выбранного интервала FoundDateTime
     * */
    public ArrayList<String> getListIntervalNameSites(String strStartDate, String strFinishDate){
        ArrayList<Integer> listIntervalPagesID = getListIntervalPagesID(strStartDate, strFinishDate);
        ArrayList<String> listIntervalNameSites = new ArrayList<>();

        for (int i = 0; i < listIntervalPagesID.size(); i++) {
            for (int j = 0; j < listIDPages.size(); j++) {
                if( listIntervalPagesID.get(i) == listIDPages.get(j)){
                    listIntervalNameSites.add(getNameSites().get(j));
                }
            }
        }
        return listIntervalNameSites;
    }
    /*
     * Метод, возвращающий пару NamePerson(String) и Rank(int) из таблицы PersonPageRank
     * */
    public LinkedHashMap<String, Integer> getListPersonNameAndRank(String nameSite){
        LinkedHashMap<String, Integer> listPersonNameAndRank = new LinkedHashMap<>();

        int count = 0;
        for (int i = 0; i < getNameSites().size(); i++) {
            if(i > 0 && getNamePerson().get(i-1) != getNamePerson().get(i)){
                count = 0;
            }
            for (int j = 0; j <getDistinctNamePerson().size(); j++) {
                if(nameSite == getNameSites().get(i) && getDistinctNamePerson().get(j) == getNamePerson().get(i)){
                    count += listRankPersonPageRank.get(i);
                    listPersonNameAndRank.put(getDistinctNamePerson().get(j), count);
                }
            }
        }
        return listPersonNameAndRank;
    }

    /*
     * Метод, возвращающий список Имен личностей(String) по PersonID(int) из таблицы PersonPageRank
     * */
    public ArrayList<String> getNamePerson(){
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
    /*
     * Метод, возвращающий список уникальных Имен личностей(String) по PersonID(int) из таблицы PersonPageRank
     * */
    public ArrayList<String> getDistinctNamePerson(){
        ArrayList<String> listDistinctNamePerson = new ArrayList<>();

        for (int i = 0; i < getNamePerson().size(); i++) {
            if(!listDistinctNamePerson.contains(getNamePerson().get(i))){
                listDistinctNamePerson.add(getNamePerson().get(i));
            }
        }
        return listDistinctNamePerson;
    }
    /*
     * Метод, возвращающий пару FoundDateTime(Date) и ID(int) из таблицы Pages
     * */
    public LinkedHashMap<Date,Integer> getFoundDateTimeAndPagesID(){
        LinkedHashMap<Date,Integer> listFoundDateTimeAndPagesID = new LinkedHashMap<>();

        for (int i = 0; i < listFoundDateTimePages.size(); i++) {
            listFoundDateTimeAndPagesID.put(listFoundDateTimePages.get(i), listIDPages.get(i));
        }
        return listFoundDateTimeAndPagesID;
    }

    /*
     * Метод, возвращающий PageID(int) из выбранного интервала FoundDateTime(String)
     * */
    public ArrayList<Integer> getListIntervalPagesID(String strStartDate, String strFinishDate){
        ArrayList<Integer> listIntervalPagesID = new ArrayList<>();
        ArrayList<String> listIntervalFoundDateTime = getListIntervalFoundDateTime(strStartDate,strFinishDate);
        Object[] keyListFoundDateTimeAndPagesID = getFoundDateTimeAndPagesID().keySet().toArray();

        for (int i = 0; i < listIntervalFoundDateTime.size(); i++) {
            for (int j = 0; j < keyListFoundDateTimeAndPagesID.length; j++) {
                if(listIntervalFoundDateTime.get(i).equals(new SimpleDateFormat("yyyy-MM-dd").format(keyListFoundDateTimeAndPagesID[j]))){
                    listIntervalPagesID.add(getFoundDateTimeAndPagesID().get(keyListFoundDateTimeAndPagesID[j]));

                }
            }
        }
        return  listIntervalPagesID;
    }
    /*
     * Метод, возвращающий список FoundDateTime(String) из таблицы Pages, в формате "yyyy-MM-dd" (отрезано время)
     * */
    public ArrayList<String> getListAbbreviatedFoundDateTime() {
        ArrayList<String> listAbbreviatedFoundDateTime = new ArrayList<>();

        for (int i = 0; i < listFoundDateTimePages.size(); i++) {
            String str = new SimpleDateFormat("yyyy-MM-dd").format(listFoundDateTimePages.get(i));
            listAbbreviatedFoundDateTime.add(str.substring(0,10));
        }

        return listAbbreviatedFoundDateTime;
    }

    public void test(){
        int count =0;
        for (int i = 0; i < listPageIDPersonPagePank.size(); i++) {
            if(listPageIDPersonPagePank.get(i) == listIDAndFoundDateTimePages.get(listFoundDateTimePages.get(0))){
                count++;
            }
        }

        System.out.println("count " + count);
    }
    /*
     * Метод, возвращающий FoundDateTime(String) из выбранного интервала
     * */
    public ArrayList<String> getListIntervalFoundDateTime(String strStartDate, String strFinishDate){//нужно приявязать к rank
        ArrayList<String> listIntervalFoundDateTime = new ArrayList<>();
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
                            listIntervalFoundDateTime.add(getListAbbreviatedFoundDateTime().get(j));
                        }else {
                            listIntervalFoundDateTime.add(getListAbbreviatedFoundDateTime().get(j));
                            break;
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listIntervalFoundDateTime;
    }
    /*
     * Метод, возвращающий список Имен(String) сайтов............
     * */
    public ArrayList<String> getNameSites(){
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

    public ArrayList<Integer> getSitesIDPages(){
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
