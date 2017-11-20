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

    private static LinkedHashMap<Integer,String> listIdAndNameFromSites;

    private static LinkedHashMap<Integer,String> listIdAndNameFromPersons;

    private static ArrayList<Integer> listPersonIdFromPersonPageRank;
    private static ArrayList<Integer> listPageIdFromPersonPageRank;
    private static ArrayList<Integer> listRankFromPersonPageRank;

    private static ArrayList<Integer> listSitesIdFromPages;
    private static ArrayList<Integer> listIdFromPages;
    private static LinkedHashMap<Integer,Date> listIDAndFoundDateTime;

    private static final String formatDate = "yyyy-MM-dd";

    public ProcessingPersonPageRankTable() {
        System.out.println("конструктор ProcessingPersonPageRankTable");
        SitesTable.infoAllSites();
        listIdAndNameFromSites = SitesTable.getListIDAndName();

        PersonsTable.infoAllPersons();
        listIdAndNameFromPersons = PersonsTable.getListIDAndName();

        PersonPageRankTable.infoAllPersonsPageRank();
        listPersonIdFromPersonPageRank = PersonPageRankTable.getListPersonID();
        listPageIdFromPersonPageRank = PersonPageRankTable.getListPageID();
        listRankFromPersonPageRank = PersonPageRankTable.getListRank();

        PagesTable.infoAllPages();
        listSitesIdFromPages = PagesTable.getListSiteID();
        listIdFromPages = PagesTable.getListID();
        listIDAndFoundDateTime = PagesTable.getListIDAndFoundDateTime();
    }

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
                    number += listRankFromPersonPageRank.get(i);
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
        Object[] keysListIDAndDate = listIDAndFoundDateTime.keySet().toArray();

        for(int i = 0; i < listPageIdFromPersonPageRank.size(); i++) {
            for(int j = 0; j < listIDAndFoundDateTime.size(); j++) {
                if(listPageIdFromPersonPageRank.get(i) == keysListIDAndDate[j]){
                    listDate.add(listIDAndFoundDateTime.get(j+1));
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
        Object[] keysListIDAndNamePersons = listIdAndNameFromPersons.keySet().toArray();

        for(int i = 0; i < listPersonIdFromPersonPageRank.size(); i++) {
            for(int j = 0; j < keysListIDAndNamePersons.length; j++) {
                if(keysListIDAndNamePersons[j] == listPersonIdFromPersonPageRank.get(i)){
                    listNamePersons.add(listIdAndNameFromPersons.get(j+1));
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
        Object[] keysListIDAndNameSites = listIdAndNameFromSites.keySet().toArray();

        for(int i = 0; i < getListSitesID().size(); i++) {
            for(int j = 0; j < keysListIDAndNameSites.length; j++) {
                if(keysListIDAndNameSites[j] == getListSitesID().get(i)){
                    listNameSites.add(listIdAndNameFromSites.get(j+1));
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

        for(int i = 0; i < listPageIdFromPersonPageRank.size(); i++) {
            for(int j = 0; j < listIdFromPages.size(); j++) {
                if(listPageIdFromPersonPageRank.get(i).equals(listIdFromPages.get(j))){
                    listSitesID.add(listSitesIdFromPages.get(j));
                }
            }
        }
        return listSitesID;
    }
}
