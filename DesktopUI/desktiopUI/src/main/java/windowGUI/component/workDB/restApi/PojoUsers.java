package windowGUI.component.workDB.restApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoUsers {
    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("active")
    @Expose
    private Boolean active;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
