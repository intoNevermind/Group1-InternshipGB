package windowGUI.component.workDB.restApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoPersons {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("active")
    @Expose
    private Boolean active;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
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
    public void setActive(Boolean active) {
        this.active = active;
    }
}
