package windowGUI.component.workWithDB.tables;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
 * Родительский класс для классов-таблиц, хранящий подключение к REST-серверу
 * */
class ConnectServer {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://54.154.158.193/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /*
     * <getters>
     * */
    static Retrofit getRetrofit() {
        return retrofit;
    }
    /*
     * </getters>
     * */
}
