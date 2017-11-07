package windowGUI.options.workSQL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class ProcessingPersonPageRankTable {

    private static final PersonTable TABLE_PERSON = new PersonTable();
    private static final LinkedHashMap<Integer,String> LIST_ID_AND_NAME = TABLE_PERSON.getListIDAndName();

    private static final PersonPageRankTable TABLE_PERSON_PAGE_RANK = new PersonPageRankTable();
    private static final ArrayList<Integer> LIST_PERSON_ID_PERSON_PAGE_RANK = TABLE_PERSON_PAGE_RANK.getListPersonID();
    private static final ArrayList<Integer> LIST_PAGE_ID_PERSON_PAGE_RANK = TABLE_PERSON_PAGE_RANK.getListPageID();
    private static final ArrayList<Integer> LIST_RANK_PERSON_PAGE_RANK = TABLE_PERSON_PAGE_RANK.getListRank();

    private static final PagesTable TABLE_PAGES = new PagesTable();
    private static final ArrayList<Integer> LIST_SITES_ID_PAGES = TABLE_PAGES.getListSiteID();
    private static final ArrayList<Integer> LIST_ID_PAGES = TABLE_PAGES.getListID();
    private static final ArrayList<Date> LIST_FOUND_DATE_TIME_PAGES = TABLE_PAGES.getListFoundDateTime();
    private static final LinkedHashMap<Integer,Date> LIST_ID_AND_FOUND_DATE_TIME_PAGES = TABLE_PAGES.getListIDAndFoundDateTime();

    private static final SitesTable TABLE_SITES = new SitesTable();
    private static final LinkedHashMap<Integer,String> LIST_ID_AND_NAME_SITES = TABLE_SITES.getListIDAndName();

    /*
    * Метод, возвращающий двумерный массив для передачи в конструктор таблицы общей статистики
    * */
    public Object[][] getArrayFillGeneralTable(String strNameSite){
        LinkedHashMap<String, Integer> listPersonNameAndRank = getListPersonNameAndRank(strNameSite);
        Object[] keyListPersonNameAndRank = listPersonNameAndRank.keySet().toArray();
        Object[][] arrPersonNameAndRank = new Object[listPersonNameAndRank.size()][2];//количество столбцов таблицы Имя и Кол-во упоминаний

        for (int i = 0; i < listPersonNameAndRank.size(); i++) {
            for (int j = 0; j < 2; j++) {
                if(j == 0){
                    arrPersonNameAndRank[i][j] = keyListPersonNameAndRank[i];// имя
                }else {
                    arrPersonNameAndRank[i][j] = listPersonNameAndRank.get(keyListPersonNameAndRank[i]);//значение rank
                }
            }
        }
        return arrPersonNameAndRank;
    }
    /*
     * Метод, возвращающий двумерный массив для передачи в конструктор таблицы ежедневной статистики
     * */
    public Object[][] getArrayFillDailyTable(String strNameSite, String strNamePerson, String strStartDate, String strFinishDate){
        LinkedHashMap<String, Integer> listFoundDateTimeAndCountPages = new LinkedHashMap<>();
        Object[][] arrDateAndCountPages = new Object[100][100];

        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            Date dateStartDate= format.parse(strStartDate);
            Date dateFinishDate= format.parse(strFinishDate);

            int count = 0;
            for (int i = 0; i < getListAbbreviatedFoundDateTime().size(); i++) {
                if(dateStartDate.before(LIST_FOUND_DATE_TIME_PAGES.get(0))){
                    strStartDate = getListAbbreviatedFoundDateTime().get(0);
                }
                if(dateFinishDate.before(dateStartDate)){
                    strFinishDate = strStartDate;
                }
                if(dateFinishDate.after(LIST_FOUND_DATE_TIME_PAGES.get(LIST_FOUND_DATE_TIME_PAGES.size()-1))){
                    strFinishDate = new SimpleDateFormat("yyyy-MM-dd").format(LIST_FOUND_DATE_TIME_PAGES.get(LIST_FOUND_DATE_TIME_PAGES.size()-1));
                }
                if(strStartDate.equals(getListAbbreviatedFoundDateTime().get(i))){
                    for (int j = i; j < getListAbbreviatedFoundDateTime().size(); j++) {
                        if(!strFinishDate.equals(getListAbbreviatedFoundDateTime().get(j)) &&
                                strNamePerson.equals(getListNamePerson().get(j))&&
                                strNameSite.equals(getListNameSites().get(j))){
                            // если pageID принадлежит ID сайта, нужно сделать каунт ++
                            listFoundDateTimeAndCountPages.put(getListAbbreviatedFoundDateTime().get(j), count);
                        }else if(strNamePerson.equals(getListNamePerson().get(j))&& strNameSite.equals(getListNameSites().get(j))){
                            listFoundDateTimeAndCountPages.put(getListAbbreviatedFoundDateTime().get(j), count);
                            break;
                        }else {
                            break;
                        }
                    }
                }
            }
            System.out.println(listFoundDateTimeAndCountPages);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return arrDateAndCountPages;
    }
    /*
     * Метод, возвращающий список сокращенных Дат(String) из таблицы PersonPageRank
     * */
    public  ArrayList<String> getListAbbreviatedFoundDateTime(){
        ArrayList<String> listAbbreviatedFoundDateTime = new ArrayList<>();// с этим листом будут сравниваться даты str
        for (int i = 0; i < getListFoundDateTime().size(); i++) {
            String strAbbreviatedFoundDateTime = new SimpleDateFormat("yyyy-MM-dd").format(getListFoundDateTime().get(i));
            listAbbreviatedFoundDateTime.add(strAbbreviatedFoundDateTime.substring(0,10));
        }
        return listAbbreviatedFoundDateTime;
    }
    /*
     * Метод, возвращающий список Дат(Date) из таблицы PersonPageRank
     * */
    public ArrayList<Date> getListFoundDateTime(){
        ArrayList<Date> listFoundDateTime = new ArrayList<>();
        Object[] keyListIDAndFoundDateTime = LIST_ID_AND_FOUND_DATE_TIME_PAGES.keySet().toArray();

        for (int i = 0; i < LIST_PAGE_ID_PERSON_PAGE_RANK.size(); i++) {
            for (int j = 0; j < LIST_ID_AND_FOUND_DATE_TIME_PAGES.size(); j++) {
                if(LIST_PAGE_ID_PERSON_PAGE_RANK.get(i) == keyListIDAndFoundDateTime[j]){
                    listFoundDateTime.add(LIST_ID_AND_FOUND_DATE_TIME_PAGES.get(j+1));
                }
            }
        }
        return listFoundDateTime;
    }
    /*
     * Метод, возвращающий пару NamePerson(String) и Rank(int) из таблицы PersonPageRank
     * */
    public LinkedHashMap<String, Integer> getListPersonNameAndRank(String nameSite){
        LinkedHashMap<String, Integer> listPersonNameAndRank = new LinkedHashMap<>();

        int count = 0;
        for (int i = 0; i < getListNameSites().size(); i++) {
            if(i > 0 && (!getListNamePerson().get(i - 1).equals(getListNamePerson().get(i)))){
                count = 0;
            }
            for (int j = 0; j < getListDistinctNamePerson().size(); j++) {
                if(nameSite.equals(getListNameSites().get(i)) && getListDistinctNamePerson().get(j).equals(getListNamePerson().get(i))){
                    count += LIST_RANK_PERSON_PAGE_RANK.get(i);
                    listPersonNameAndRank.put(getListDistinctNamePerson().get(j), count);
                }
            }
        }
        return listPersonNameAndRank;
    }
    /*
     * Метод, возвращающий список Имен личностей(String) из таблицы PersonPageRank
     * */
    public ArrayList<String> getListNamePerson(){
        ArrayList<String> listNamePerson = new ArrayList<>();
        Object[] keyListNamePerson = LIST_ID_AND_NAME.keySet().toArray();

        for (int i = 0; i < LIST_PERSON_ID_PERSON_PAGE_RANK.size(); i++) {
            for (int j = 0; j < keyListNamePerson.length; j++) {
                if(keyListNamePerson[j] == LIST_PERSON_ID_PERSON_PAGE_RANK.get(i)){
                    listNamePerson.add(LIST_ID_AND_NAME.get(j+1));
                }
            }
        }
        return listNamePerson;
    }
    /*
     * Метод, возвращающий список уникальных Имен личностей(String) из таблицы PersonPageRank
     * */
    public ArrayList<String> getListDistinctNamePerson(){
        ArrayList<String> listDistinctNamePerson = new ArrayList<>();

        for (int i = 0; i < getListNamePerson().size(); i++) {
            if(!listDistinctNamePerson.contains(getListNamePerson().get(i))){
                listDistinctNamePerson.add(getListNamePerson().get(i));
            }
        }
        return listDistinctNamePerson;
    }

    /*
     * Метод, возвращающий список Имен(String) сайтов из таблицы PersonPageRank
     * */
    public ArrayList<String> getListNameSites(){
        ArrayList<String> listNameSites = new ArrayList<>();

        Object[] keyListNameSites = LIST_ID_AND_NAME_SITES.keySet().toArray();
        for (int i = 0; i < getListSitesID().size(); i++) {
            for (int j = 0; j < keyListNameSites.length; j++) {
                if(keyListNameSites[j] == getListSitesID().get(i)){
                    listNameSites.add(LIST_ID_AND_NAME_SITES.get(j+1));
                }
            }
        }
        return listNameSites;
    }
    /*
     * Метод, возвращающий список уникальных Имен сайтов(String) по SitesID(int) из таблицы PersonPageRank
     * */
    public ArrayList<String> getListDistinctNameSites(){
        ArrayList<String> listDistinctNameSites = new ArrayList<>();

        for (int i = 0; i < getListNameSites().size(); i++) {
            if(!listDistinctNameSites.contains(getListNameSites().get(i))){
                listDistinctNameSites.add(getListNameSites().get(i));
            }
        }
        return listDistinctNameSites;
    }
    /*
     * Метод, возвращающий список ID сайтов(int) сайтов из таблицы PersonPageRank
     * */
    public ArrayList<Integer> getListSitesID(){
        ArrayList<Integer> listSitesID = new ArrayList<>();

        for (int i = 0; i < LIST_PAGE_ID_PERSON_PAGE_RANK.size(); i++) {
            for (int j = 0; j < LIST_ID_PAGES.size(); j++) {
                if(LIST_PAGE_ID_PERSON_PAGE_RANK.get(i).equals(LIST_ID_PAGES.get(j))){
                    listSitesID.add(LIST_SITES_ID_PAGES.get(j));
                }
            }
        }
        return listSitesID;
    }
    /*
     * Метод, возвращающий пару FoundDateTime(date) и количество её повторений в PersonPageRank из выбранного интервала времени
     * */
//    public LinkedHashMap<String,Integer> getListIntervalDateAndCountRepetitionPersonPageRank(String strStartDate, String strFinishDate){
//        Object[] keyListFoundDateTimeAndIDPages = LIST_ID_AND_FOUND_DATE_TIME_PAGES.keySet().toArray();
//        ArrayList<Integer> listIntervalPageIDFoundDateTime = getListIntervalPageIDFoundDateTime(strStartDate,strFinishDate);
//        LinkedHashMap<String,Integer> listIntervalDateAndCountRepetitionPersonPageRank = new LinkedHashMap<>();
//
//        for (int i = 0; i < listIntervalPageIDFoundDateTime.size(); i++) {
//            int count = 0;
//            for (int j = 0; j < LIST_PAGE_ID_PERSON_PAGE_RANK.size(); j++) {
//                if(listIntervalPageIDFoundDateTime.get(i).equals(LIST_PAGE_ID_PERSON_PAGE_RANK.get(j))){
//                    count ++;
//                }
//            }
//            String str = new SimpleDateFormat("yyyy-MM-dd").format(keyListFoundDateTimeAndIDPages[i]);
//            listIntervalDateAndCountRepetitionPersonPageRank.put(str, count);
//        }
//        return  listIntervalDateAndCountRepetitionPersonPageRank;
//
//    }
}
