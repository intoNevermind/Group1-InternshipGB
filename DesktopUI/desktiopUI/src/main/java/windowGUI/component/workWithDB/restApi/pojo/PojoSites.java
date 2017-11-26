package windowGUI.component.workWithDB.restApi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoSites {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("active")
    @Expose
    private Boolean active;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
    public Boolean getActive() {
        return active;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

}
