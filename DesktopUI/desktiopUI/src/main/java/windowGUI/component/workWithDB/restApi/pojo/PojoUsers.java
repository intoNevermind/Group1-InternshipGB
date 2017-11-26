package windowGUI.component.workWithDB.restApi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoUsers {
    @SerializedName("login")
    @Expose
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
