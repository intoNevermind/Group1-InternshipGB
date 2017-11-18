package windowGUI.component.workDB.processingData;

import windowGUI.component.workDB.tables.PagesTable;
import windowGUI.component.workDB.tables.PersonPageRankTable;
import windowGUI.component.workDB.tables.PersonsTable;
import windowGUI.component.workDB.tables.SitesTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/*
 * Класс-обработчик, отвечающий за обработку данных таблицы PersonPageRank
 * */
public class ProcessingPersonPageRankTable extends ProcessingData{
    private static final PersonsTable PERSONS_TABLE = PersonsTable.getInstance();
    private static final LinkedHashMap<Integer,String> LIST_ID_AND_NAME_FROM_PERSONS = PERSONS_TABLE.getListIDAndName();

    private static final PersonPageRankTable PERSON_PAGE_RANK_TABLE = PersonPageRankTable.getInstance();
    private static final ArrayList<Integer> LIST_PERSON_ID_FROM_PERSON_PAGE_RANK = PERSON_PAGE_RANK_TABLE.getListPersonID();
    private static final ArrayList<Integer> LIST_PAGE_ID_FROM_PERSON_PAGE_RANK = PERSON_PAGE_RANK_TABLE.getListPageID();
    private static final ArrayList<Integer> LIST_RANK_FROM_PERSON_PAGE_RANK = PERSON_PAGE_RANK_TABLE.getListRank();

    private static final PagesTable PAGES_TABLE = PagesTable.getInstance();
    private static final ArrayList<Integer> LIST_SITES_ID_FROM_PAGES = PAGES_TABLE.getListSiteID();
    private static final ArrayList<Integer> LIST_ID_FROM_PAGES = PAGES_TABLE.getListID();
    private static final LinkedHashMap<Integer,Date> LIST_ID_AND_FOUND_DATE_TIME_FROM_PAGES = PAGES_TABLE.getListIDAndFoundDateTime();

    private static final SitesTable SITES_TABLE = SitesTable.getInstance();
    private static final LinkedHashMap<Integer,String> LIST_ID_AND_NAME_FROM_SITES = SITES_TABLE.getListIDAndName();

    private static final String formatDate = "yyyy-MM-dd";

    /*
    *  метод, возвращающий список имен личностей и количество упоминаний о них по имени сайта,
    *  преобразованный в двойной массив, для заполнения JTable класса GeneralStatistic строками
    * */
    @Override
    public Object[][] getArrayFillTable(String nameSite, int numberColumn){
        if(nameSite == null || numberColumn < 1) return super.getArrayFillTable(nameSite,numberColumn);

        LinkedHashMap<String, Integer> listNamePersonAndRank = getListNamePersonAndRank(nameSite);
        return convertingListToArray(listNamePersonAndRank, numberColumn);
    }

    /*
     * метод, возвращающий список имен личностей и количество новых страниц с упоминанием о них, по имени сайта,имени личности и интервалу дат,
     * преобразованный в двойной массив, для заполнения JTable класса DailyStatistic строками
     * */
    @Override
    public Object[][] getArrayFillTable(String nameSite, String namePerson, String startDate, String finishDate, int numberColumn){
        if(nameSite == null || namePerson == null || startDate == null || finishDate == null || numberColumn < 1){
            return super.getArrayFillTable(nameSite, namePerson, startDate, finishDate, numberColumn);
        }

        LinkedHashMap<String, Integer> listDateAndNumberPages = getListDateAndNumberPages(nameSite, namePerson, startDate, finishDate);
        return convertingListToArray(listDateAndNumberPages, numberColumn);
    }

    /*
     * Метод, возвращающий связанный список - имя личности и количество упоминаний о ней, по имени сайта
     * */
    private LinkedHashMap<String, Integer> getListNamePersonAndRank(String nameSite){
        LinkedHashMap<String, Integer> listNamePersonAndRank = new LinkedHashMap<>();
        int number = 0;

        for(int i = 0; i < getListNameSites().size(); i++) {
            if(!listNamePersonAndRank.containsKey(getListNamePersons().get(i))) number = 0;

            for(int j = 0; j < getListDistinctNamePersons().size(); j++) {
                if(nameSite.equals(getListNameSites().get(i)) && getListDistinctNamePersons().get(j).equals(getListNamePersons().get(i))){
                    number += LIST_RANK_FROM_PERSON_PAGE_RANK.get(i);
                    listNamePersonAndRank.put(getListDistinctNamePersons().get(j), number);
                }
            }
        }
        return listNamePersonAndRank;
    }

    /*
     * Метод, возвращающий количество новых страниц в общем, из интервала дат
     * */
    @SuppressWarnings("SuspiciousMethodCalls")
    public int getNumberPagesTotal(String nameSite, String namePerson, String startDate, String finishDate){
        if(nameSite == null || namePerson == null || startDate == null || finishDate == null) return 0;

        LinkedHashMap<String, Integer> listDateAndNumberPages = getListDateAndNumberPages(nameSite, namePerson, startDate, finishDate);
        Object[] keysListDateAndNumberPages = listDateAndNumberPages.keySet().toArray();
        int numberPagesTotal = 0;

        for (int i = 0; i < listDateAndNumberPages.size(); i++){
            numberPagesTotal += listDateAndNumberPages.get(keysListDateAndNumberPages[i]);
        }
        return numberPagesTotal;
    }

    /*
     * Метод, возвращающий связанный список - дата(String) и количество страниц, по имени сайта,имени личности и интервалу дат
     * */
    private LinkedHashMap<String, Integer> getListDateAndNumberPages(String nameSite, String namePerson, String startDate, String finishDate){
        ArrayList<String> listIntervalDate = getListIntervalDate(nameSite, namePerson, startDate, finishDate);
        LinkedHashMap<String, Integer> listDateAndNumberPages = new LinkedHashMap<>();
        Integer numberPages;

        for(String i : listIntervalDate) {
            numberPages = listDateAndNumberPages.get(i);
            listDateAndNumberPages.put(i, numberPages == null ? 1 : numberPages + 1);
        }
        return listDateAndNumberPages;
    }

    /*
     * Метод, преобразующий строку в дату
     * */
    private Date convertingStringToDate(String date){
        try {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern(formatDate);
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Метод, возвращающий список дат(String), по имени сайта,имени личности и интервалу дат
     * */
    private ArrayList<String> getListIntervalDate(String nameSite, String namePerson, String strStartDate, String strFinishDate){
        Date dateStartDate = convertingStringToDate(strStartDate);
        Date dateFinishDate = convertingStringToDate(strFinishDate);

        assert dateStartDate != null;
        assert dateFinishDate != null;

        ArrayList<String> listIntervalDate = new ArrayList<>();

        for(int j = 0; j < getListAbbreviatedDate().size(); j++) {
            Date date = convertingStringToDate(getListAbbreviatedDate().get(j));
            assert date != null;
            if(date.after(dateStartDate) && date.before(dateFinishDate) ||
                    date.equals(dateStartDate) || date.equals(dateFinishDate)){
                if (namePerson.equals(getListNamePersons().get(j))){
                    if (nameSite.equals(getListNameSites().get(j))){
                        listIntervalDate.add(getListAbbreviatedDate().get(j));
                    }
                }
            }
        }
        return listIntervalDate;
    }

    /*
     * Метод, возвращающий список сокращенных дат(String, без учета часов, минут и секунд)
     * */
    private ArrayList<String> getListAbbreviatedDate(){
        ArrayList<String> listAbbreviatedDate = new ArrayList<>();

        for(int i = 0; i < getListDate().size(); i++) {
            String strAbbreviatedDate = new SimpleDateFormat(formatDate).format(getListDate().get(i));
            listAbbreviatedDate.add(strAbbreviatedDate.substring(0,formatDate.length()));
        }
        return listAbbreviatedDate;
    }

    /*
     * Метод, возвращающий список дат(Date)
     * */
    private ArrayList<Date> getListDate(){
        ArrayList<Date> listDate = new ArrayList<>();
        Object[] keysListIDAndDate = LIST_ID_AND_FOUND_DATE_TIME_FROM_PAGES.keySet().toArray();

        for(int i = 0; i < LIST_PAGE_ID_FROM_PERSON_PAGE_RANK.size(); i++) {
            for(int j = 0; j < LIST_ID_AND_FOUND_DATE_TIME_FROM_PAGES.size(); j++) {
                if(LIST_PAGE_ID_FROM_PERSON_PAGE_RANK.get(i) == keysListIDAndDate[j]){
                    listDate.add(LIST_ID_AND_FOUND_DATE_TIME_FROM_PAGES.get(j+1));
                }
            }
        }
        return listDate;
    }

    /*
     * Метод, возвращающий список имен личностей
     * */
    private ArrayList<String> getListNamePersons(){
        ArrayList<String> listNamePersons = new ArrayList<>();
        Object[] keysListIDAndNamePersons = LIST_ID_AND_NAME_FROM_PERSONS.keySet().toArray();

        for(int i = 0; i < LIST_PERSON_ID_FROM_PERSON_PAGE_RANK.size(); i++) {
            for(int j = 0; j < keysListIDAndNamePersons.length; j++) {
                if(keysListIDAndNamePersons[j] == LIST_PERSON_ID_FROM_PERSON_PAGE_RANK.get(i)){
                    listNamePersons.add(LIST_ID_AND_NAME_FROM_PERSONS.get(j+1));
                }
            }
        }
        return listNamePersons;
    }

    /*
     * Метод, возвращающий список уникальных имен личностей
     * */
    private ArrayList<String> getListDistinctNamePersons(){
        ArrayList<String> listDistinctNamePersons = new ArrayList<>();

        for(int i = 0; i < getListNamePersons().size(); i++) {
            if(!listDistinctNamePersons.contains(getListNamePersons().get(i))){
                listDistinctNamePersons.add(getListNamePersons().get(i));
            }
        }
        return listDistinctNamePersons;
    }

    /*
     * Метод, возвращающий список имен сайтов
     * */
    private ArrayList<String> getListNameSites(){
        ArrayList<String> listNameSites = new ArrayList<>();
        Object[] keysListIDAndNameSites = LIST_ID_AND_NAME_FROM_SITES.keySet().toArray();

        for(int i = 0; i < getListSitesID().size(); i++) {
            for(int j = 0; j < keysListIDAndNameSites.length; j++) {
                if(keysListIDAndNameSites[j] == getListSitesID().get(i)){
                    listNameSites.add(LIST_ID_AND_NAME_FROM_SITES.get(j+1));
                }
            }
        }
        return listNameSites;
    }

    /*
     * Метод, возвращающий список ID сайтов сайтов
     * */
    private ArrayList<Integer> getListSitesID(){
        ArrayList<Integer> listSitesID = new ArrayList<>();

        for(int i = 0; i < LIST_PAGE_ID_FROM_PERSON_PAGE_RANK.size(); i++) {
            for(int j = 0; j < LIST_ID_FROM_PAGES.size(); j++) {
                if(LIST_PAGE_ID_FROM_PERSON_PAGE_RANK.get(i).equals(LIST_ID_FROM_PAGES.get(j))){
                    listSitesID.add(LIST_SITES_ID_FROM_PAGES.get(j));
                }
            }
        }
        return listSitesID;
    }
}
