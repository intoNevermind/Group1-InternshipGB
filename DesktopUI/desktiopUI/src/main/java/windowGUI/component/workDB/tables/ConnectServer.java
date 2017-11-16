package windowGUI.component.workDB.tables;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ConnectServer {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://54.154.158.193/unauthorized/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    static Retrofit getRetrofit() {
        return retrofit;
    }
}
