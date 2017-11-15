package windowGUI.component.workDB;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ConnectServer {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://54.154.158.193/unauthorized/admin/ui/?login=app&password=9b0183cb8e4b9f7912e770de79ef4501/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    protected static Retrofit getRetrofit() {
        return retrofit;
    }


}
