package windowGUI.component.workWithDB.processingData;

import windowGUI.component.workWithDB.tables.DailyStatisticsTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Класс-обработчик, отвечающий за обработку данных таблицы ежедневной
 * */
public class ProcessingDailyStatisticsTable extends ProcessingData {
    private static final ProcessingSitesTable P_SITES_T = new ProcessingSitesTable();

    /*
     * метод, возвращающий список имен личностей и количество новых страниц с упоминанием о них, по имени сайта,имени личности и интервалу дат,
     * преобразованный в двойной массив, для заполнения JTable класса DailyStatistic строками
     * */
    @Override
    public Object[][] getArrayFillTable(String nameSite, String namePerson, String startDate, String finishDate, int numberColumns){
        if(nameSite == null || namePerson == null || startDate == null || finishDate == null || numberColumns < 1){
            return super.getArrayFillTable(nameSite, namePerson, startDate, finishDate, numberColumns);
        }

        DailyStatisticsTable.infoAllDailyStatistics(namePerson, P_SITES_T.getIDSiteByNameSite(nameSite),startDate,finishDate);
        LinkedHashMap<String, Integer> listDateAndDailyRank = DailyStatisticsTable.getListDateAndDailyRank();

        return convertingListToArray(unionAllValues(listDateAndDailyRank), listDateAndDailyRank.size(), numberColumns);
    }

    /*
     * метод, объеденяющий все возможные значения таблицы в один список(по порядку зазмещения в колонках)
     * */
    private ArrayList<Object> unionAllValues(LinkedHashMap<String, Integer> list){
        ArrayList<Object> listUnionAllValues = new ArrayList<>();

        listUnionAllValues.addAll(list.keySet());
        listUnionAllValues.addAll(list.values());

        return listUnionAllValues;
    }

    /*
     * Метод, возвращающий количество новых страниц в общем, из интервала дат
     * */
    public int getNumberPagesTotal(){
        LinkedHashMap<String, Integer> listDateAndNumberPages = DailyStatisticsTable.getListDateAndDailyRank();
        Object[] keysListDateAndNumberPages = listDateAndNumberPages.keySet().toArray();
        int numberPagesTotal = 0;

        for (int i = 0; i < listDateAndNumberPages.size(); i++){
            numberPagesTotal += listDateAndNumberPages.get(keysListDateAndNumberPages[i]);
        }

        return numberPagesTotal;
    }
}
