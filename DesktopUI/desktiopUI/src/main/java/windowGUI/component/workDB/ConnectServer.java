package windowGUI.component.workDB;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ConnectServer {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://54.154.158.193")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getRetrofit() {
        return retrofit;
    }


}
