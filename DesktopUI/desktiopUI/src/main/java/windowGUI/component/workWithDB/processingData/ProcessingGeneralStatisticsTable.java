package windowGUI.component.workWithDB.processingData;

import windowGUI.component.workWithDB.tables.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
 * Класс-обработчик, отвечающий за обработку данных таблицы общей статистики
 * */
public class ProcessingGeneralStatisticsTable extends ProcessingData{
    private static final ProcessingSitesTable P_SITES_T = new ProcessingSitesTable();

    /*
    *  метод, возвращающий список имен личностей и количество упоминаний о них по имени сайта,
    *  преобразованный в двойной массив, для заполнения JTable класса GeneralStatistic строками
    * */
    @Override
    public Object[][] getArrayFillTable(String nameSite, int numberColumns){
        if(nameSite == null || numberColumns < 1) return super.getArrayFillTable(nameSite,numberColumns);

        GeneralStatisticsTable.infoAllGeneralStatistics(P_SITES_T.getIDSiteByNameSite(nameSite));
        LinkedHashMap<String, Integer> listNamePersonAndGeneralRank = GeneralStatisticsTable.getListNamePersonAndGeneralRank();

        return convertingListToArray(unionAllValues(listNamePersonAndGeneralRank), listNamePersonAndGeneralRank.size(), numberColumns);
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
}
