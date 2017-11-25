package windowGUI.component.workWithDB.tables;

import windowGUI.component.workWithDB.restApi.PojoGeneralStatistics;
import windowGUI.component.workWithDB.restApi.RestApiForGeneralStatistics;

import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/*
 * Класс-таблица, отвечающий за получение данных из таблицы GeneralStatistics, в REST-сервер
 * */
public class GeneralStatisticsTable extends ConnectServer {
    private static final RestApiForGeneralStatistics REST_API_FOR_GENERAL_STATISTICS = getRetrofit().create(RestApiForGeneralStatistics.class);

    private static final ArrayList<String> LIST_NAME = new ArrayList<>();
    private static final ArrayList<Integer> LIST_GENERAL_RANK = new ArrayList<>();
    private static final LinkedHashMap<String,Integer>  LIST_NAME_PERSON_AND_GENERAL_RANK = new LinkedHashMap<>();

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД по ID сайта
     * */
    public static void infoAllGeneralStatistics(int siteID){
        LIST_NAME.clear();
        LIST_GENERAL_RANK.clear();
        LIST_NAME_PERSON_AND_GENERAL_RANK.clear();

        try {
            Response<ArrayList<PojoGeneralStatistics>> response = REST_API_FOR_GENERAL_STATISTICS.getListGeneralStatistics(siteID).execute();

            ArrayList<PojoGeneralStatistics> list = response.body();
            for (int i = 0; i < list.size(); i++) {
                LIST_NAME.add(list.get(i).getName());
                LIST_GENERAL_RANK.add(list.get(i).getGeneralRank());
                LIST_NAME_PERSON_AND_GENERAL_RANK.put(LIST_NAME.get(i), LIST_GENERAL_RANK.get(i));
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
    public static LinkedHashMap<String, Integer> getListNamePersonAndGeneralRank() {
        return LIST_NAME_PERSON_AND_GENERAL_RANK;
    }
    /*
     * </getters>
     * */
}
