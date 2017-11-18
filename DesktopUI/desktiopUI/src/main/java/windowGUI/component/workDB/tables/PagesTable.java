package windowGUI.component.workDB.tables;

import windowGUI.component.workDB.restApi.RestApiForPagesTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
/*
 * Класс-таблица, отвечающий за получение данных из таблицы Pages, в REST-сервер
 * */
public class PagesTable extends ConnectServer {
    private static final RestApiForPagesTable REST_API_FOR_PAGES_TABLE = getRetrofit().create(RestApiForPagesTable.class);

    private static final ArrayList<Integer> listID = new ArrayList<>();
    private static final ArrayList<String> listURL = new ArrayList<>();
    private static final ArrayList<Integer> listSiteID = new ArrayList<>();
    private static final ArrayList<Date> listFoundDateTime = new ArrayList<>();
    private static final LinkedHashMap<Integer,Date> listIDAndFoundDateTime = new LinkedHashMap<>();

    private static PagesTable instance;

    public static PagesTable getInstance() {
        if(instance == null){
            instance = new PagesTable();
        }
        return instance;
    }

    private PagesTable() {
        infoAllPages();
    }

    /*
     * <Получение>
     * запросы с помощью которых, можно получить данные из БД
     * */

    /*
     * метод, заполняющий списки данными из БД
     * */
    private void infoAllPages(){
        //жду пока REST- сервер напишет запрос
    }
    /*
     * </Получение>
     * */

    /*
     * <getters>
     * */
    public ArrayList<Integer> getListID(){
        return listID;
    }
    public ArrayList<String> getListURL(){
        return listURL;
    }
    public ArrayList<Integer> getListSiteID(){
        return listSiteID;
    }
    public ArrayList<Date> getListFoundDateTime(){
        return listFoundDateTime;
    }
    public LinkedHashMap<Integer,Date> getListIDAndFoundDateTime() {
        return listIDAndFoundDateTime;
    }
    /*
     * </getters>
     * */
}
