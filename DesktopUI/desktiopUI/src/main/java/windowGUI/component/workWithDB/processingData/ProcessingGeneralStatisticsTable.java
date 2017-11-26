package windowGUI.component.workWithDB.processingData;

import windowGUI.component.workWithDB.tables.*;
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
    public Object[][] getArrayFillTable(String nameSite, int numberColumn){
        if(nameSite == null || numberColumn < 1) return super.getArrayFillTable(nameSite,numberColumn);

        GeneralStatisticsTable.infoAllGeneralStatistics(P_SITES_T.getIDSiteByNameSite(nameSite));
        return convertingListToArray(GeneralStatisticsTable.getListNamePersonAndGeneralRank(), numberColumn);
    }
}
