package windowGUI.component.workDB.processingData;

import windowGUI.component.workDB.tables.PagesTable;
import windowGUI.component.workDB.tables.PersonPageRankTable;
import windowGUI.component.workDB.tables.PersonTable;
import windowGUI.component.workDB.tables.SitesTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private static final LinkedHashMap<Integer,Date> LIST_ID_AND_FOUND_DATE_TIME_PAGES = TABLE_PAGES.getListIDAndFoundDateTime();

    private static final SitesTable TABLE_SITES = new SitesTable();
    private static final LinkedHashMap<Integer,String> LIST_ID_AND_NAME_SITES = TABLE_SITES.getListIDAndName();
    /*
    * Метод, возвращающий двумерный массив для передачи в конструктор таблицы общей статистики
    * */
    public Object[][] getArrayFillGeneralTable(String strNameSite, int countColumn){
        if(strNameSite == null) return new Object[0][0];
        LinkedHashMap<String, Integer> listPersonNameAndRank = getListPersonNameAndRank(strNameSite);
        return convertingListToArray(listPersonNameAndRank, countColumn);
    }
    /*
     * Метод, возвращающий двумерный массив для передачи в конструктор таблицы ежедневной статистики
     * */
    public Object[][] getArrayFillDailyTable(String strNameSite, String strNamePerson, String strStartDate, String strFinishDate, int countColumn){
        if(strNameSite == null || strNamePerson == null || strStartDate == null || strFinishDate == null) return new Object[0][0];
        LinkedHashMap<String, Integer> listFoundDateTimeAndCountPages = getListFoundDateTimeAndCountPages(strNameSite, strNamePerson, strStartDate, strFinishDate);
        return convertingListToArray(listFoundDateTimeAndCountPages, countColumn);
    }
    /*
     * Метод, преобразующий список в двумерный массив
     * */
    private Object[][] convertingListToArray(LinkedHashMap list, int columnCount){
        Object[] keyList = list.keySet().toArray();
        Object[][] arr = new Object[list.size()][columnCount];

        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < 2; j++) {
                if(j == 0) arr[i][j] = keyList[i];
                else arr[i][j] = list.get(keyList[i]);
            }
        }
        return arr;
    }
    /*
     * Метод, возвращающий пару NamePerson(String) и Rank(int) из таблицы PersonPageRank
     * */
    private LinkedHashMap<String, Integer> getListPersonNameAndRank(String nameSite){
        LinkedHashMap<String, Integer> listPersonNameAndRank = new LinkedHashMap<>();

        int count = 0;
        for(int i = 0; i < getListNameSites().size(); i++) {
            if(!listPersonNameAndRank.containsKey(getListNamePerson().get(i))) count = 0;

            for(int j = 0; j < getListDistinctNamePerson().size(); j++) {
                if(nameSite.equals(getListNameSites().get(i)) && getListDistinctNamePerson().get(j).equals(getListNamePerson().get(i))){
                    count += LIST_RANK_PERSON_PAGE_RANK.get(i);
                    listPersonNameAndRank.put(getListDistinctNamePerson().get(j), count);
                }
            }
        }
        return listPersonNameAndRank;
    }
    /*
     * Метод, возвращающий пару Дату(String) и Кол-во страниц(int) из таблицы PersonPageRank
     * */
    private LinkedHashMap<String, Integer> getListFoundDateTimeAndCountPages(String strNameSite, String strNamePerson, String strStartDate, String strFinishDate){
        ArrayList<String> listIntervalFoundDateTime = getListIntervalFoundDateTime(strNameSite, strNamePerson, strStartDate, strFinishDate);

        LinkedHashMap<String, Integer> listFoundDateTimeAndCountPages = new LinkedHashMap<>();
        Integer countPages;
        for(String i : listIntervalFoundDateTime) {
            countPages = listFoundDateTimeAndCountPages.get(i);
            listFoundDateTimeAndCountPages.put(i, countPages == null ? 1 : countPages + 1);
        }
        return listFoundDateTimeAndCountPages;
    }
    /*
    * Метод, возвращающий Количество новых страниц в общем(int), из выбранного интервала дат
    * */
    public int getIntNumberPagesTotal(String strNameSite, String strNamePerson, String strStartDate, String strFinishDate){
        if(strNameSite == null || strNamePerson == null || strStartDate == null || strFinishDate == null) return 0;
        LinkedHashMap<String, Integer> listFoundDateTimeAndCountPages = getListFoundDateTimeAndCountPages(strNameSite, strNamePerson, strStartDate, strFinishDate);
        Object[] keyListFoundDateTimeAndCountPages = listFoundDateTimeAndCountPages.keySet().toArray();
        int numberPagesTotal = 0;
        for (int i = 0; i < listFoundDateTimeAndCountPages.size(); i++){
            numberPagesTotal += listFoundDateTimeAndCountPages.get(keyListFoundDateTimeAndCountPages[i]);
        }
        System.out.println(numberPagesTotal);
        return numberPagesTotal;
    }
    /*
     * Метод, преобразующий Дату(String) в Дату(Date)
     * */
    private Date convertingStrToDate(String strDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            return format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
     * Метод, возвращающий список Дат(String) из интервата дат
     * */
    private ArrayList<String> getListIntervalFoundDateTime(String strNameSite, String strNamePerson, String strStartDate, String strFinishDate){
        Date dateStartDate = convertingStrToDate(strStartDate);
        Date dateFinishDate = convertingStrToDate(strFinishDate);

        assert dateStartDate != null;
        assert dateFinishDate != null;

        ArrayList<String> listIntervalFoundDateTime = new ArrayList<>();
        for(int j = 0; j < getListAbbreviatedFoundDateTime().size(); j++) {
            Date date = convertingStrToDate(getListAbbreviatedFoundDateTime().get(j));
            assert date != null;
            if((date.after(dateStartDate) && date.before(dateFinishDate)) ||
                    date.equals(dateStartDate) || date.equals(dateFinishDate)) {
                if (strNamePerson.equals(getListNamePerson().get(j))) {
                    if (strNameSite.equals(getListNameSites().get(j))) {
                        listIntervalFoundDateTime.add(getListAbbreviatedFoundDateTime().get(j));
                    }
                }
            }
        }
        return listIntervalFoundDateTime;
    }
    /*
     * Метод, возвращающий список сокращенных Дат(String, без учета часов, минут и секунд) из таблицы PersonPageRank
     * */
    private ArrayList<String> getListAbbreviatedFoundDateTime(){
        ArrayList<String> listAbbreviatedFoundDateTime = new ArrayList<>();
        for(int i = 0; i < getListFoundDateTime().size(); i++) {
            String strAbbreviatedFoundDateTime = new SimpleDateFormat("yyyy-MM-dd").format(getListFoundDateTime().get(i));
            listAbbreviatedFoundDateTime.add(strAbbreviatedFoundDateTime.substring(0,10));
        }
        return listAbbreviatedFoundDateTime;
    }
    /*
     * Метод, возвращающий список Дат(Date) из таблицы PersonPageRank
     * */
    private ArrayList<Date> getListFoundDateTime(){
        ArrayList<Date> listFoundDateTime = new ArrayList<>();
        Object[] keyListIDAndFoundDateTime = LIST_ID_AND_FOUND_DATE_TIME_PAGES.keySet().toArray();

        for(int i = 0; i < LIST_PAGE_ID_PERSON_PAGE_RANK.size(); i++) {
            for(int j = 0; j < LIST_ID_AND_FOUND_DATE_TIME_PAGES.size(); j++) {
                if(LIST_PAGE_ID_PERSON_PAGE_RANK.get(i) == keyListIDAndFoundDateTime[j]){
                    listFoundDateTime.add(LIST_ID_AND_FOUND_DATE_TIME_PAGES.get(j+1));
                }
            }
        }
        return listFoundDateTime;
    }
    /*
     * Метод, возвращающий список Имен личностей(String) из таблицы PersonPageRank
     * */
    private ArrayList<String> getListNamePerson(){
        ArrayList<String> listNamePerson = new ArrayList<>();
        Object[] keyListNamePerson = LIST_ID_AND_NAME.keySet().toArray();

        for(int i = 0; i < LIST_PERSON_ID_PERSON_PAGE_RANK.size(); i++) {
            for(int j = 0; j < keyListNamePerson.length; j++) {
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
    private ArrayList<String> getListDistinctNamePerson(){
        ArrayList<String> listDistinctNamePerson = new ArrayList<>();

        for(int i = 0; i < getListNamePerson().size(); i++) {
            if(!listDistinctNamePerson.contains(getListNamePerson().get(i))){
                listDistinctNamePerson.add(getListNamePerson().get(i));
            }
        }
        return listDistinctNamePerson;
    }

    /*
     * Метод, возвращающий список Имен(String) сайтов из таблицы PersonPageRank
     * */
    private ArrayList<String> getListNameSites(){
        ArrayList<String> listNameSites = new ArrayList<>();

        Object[] keyListNameSites = LIST_ID_AND_NAME_SITES.keySet().toArray();
        for(int i = 0; i < getListSitesID().size(); i++) {
            for(int j = 0; j < keyListNameSites.length; j++) {
                if(keyListNameSites[j] == getListSitesID().get(i)){
                    listNameSites.add(LIST_ID_AND_NAME_SITES.get(j+1));
                }
            }
        }
        return listNameSites;
    }
    /*
     * Метод, возвращающий список ID сайтов(int) сайтов из таблицы PersonPageRank
     * */
    private ArrayList<Integer> getListSitesID(){
        ArrayList<Integer> listSitesID = new ArrayList<>();

        for(int i = 0; i < LIST_PAGE_ID_PERSON_PAGE_RANK.size(); i++) {
            for(int j = 0; j < LIST_ID_PAGES.size(); j++) {
                if(LIST_PAGE_ID_PERSON_PAGE_RANK.get(i).equals(LIST_ID_PAGES.get(j))){
                    listSitesID.add(LIST_SITES_ID_PAGES.get(j));
                }
            }
        }
        return listSitesID;
    }
}
