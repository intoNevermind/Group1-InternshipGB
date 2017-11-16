package windowGUI.component.workDB.restApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoUsers {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("admin")
    @Expose
    private Boolean admin;

    @SerializedName("active")
    @Expose
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
