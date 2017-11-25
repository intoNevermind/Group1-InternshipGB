package windowGUI.component.workWithDB.tables;

import windowGUI.component.workWithDB.restApi.PojoDailyStatistics;
import windowGUI.component.workWithDB.restApi.RestApiForDailyStatistics;

import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
* Класс-таблица, отвечающий за получение данных из таблицы DailyStatistics, в REST-сервер
* */
public class DailyStatisticsTable extends ConnectServer{
    private static final RestApiForDailyStatistics REST_API_FOR_DAILY_STATISTICS = getRetrofit().create(RestApiForDailyStatistics.class);

    private static final ArrayList<String> LIST_NAME = new ArrayList<>();
    private static final ArrayList<String> LIST_RANK_DATE = new ArrayList<>();
    private static final ArrayList<Integer> LIST_DAILY_RANK= new ArrayList<>();
    private static final LinkedHashMap<String,Integer> LIST_DATE_AND_DAILY_RANK = new LinkedHashMap<>();

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД по имени личности, ID сайта и интервалу дат
     * */
    public static void infoAllDailyStatistics(String namePerson, int siteID, String dateFrom, String dateTo){
        LIST_NAME.clear();
        LIST_RANK_DATE.clear();
        LIST_DAILY_RANK.clear();
        LIST_DATE_AND_DAILY_RANK.clear();

        try {
            Response<ArrayList<PojoDailyStatistics>> response = REST_API_FOR_DAILY_STATISTICS.getListDailyStatistics(siteID,dateFrom,dateTo).execute();

            ArrayList<PojoDailyStatistics> list = response.body();
            for (int j = 0; j < list.size(); j++) {
                LIST_NAME.add(list.get(j).getName());
                LIST_RANK_DATE.add(list.get(j).getRankDate());
                LIST_DAILY_RANK.add(list.get(j).getDailyRank());
                if(namePerson.equals(LIST_NAME.get(j)) &&  LIST_DAILY_RANK.get(j) != 0){
                    LIST_DATE_AND_DAILY_RANK.put(LIST_RANK_DATE.get(j), LIST_DAILY_RANK.get(j));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * </Получение>
     * */

    /*
     * <getters>
     * */
    public static LinkedHashMap<String, Integer> getListDateAndDailyRank() {
        return LIST_DATE_AND_DAILY_RANK;
    }
    /*
     * </getters>
     * */
}
