package windowGUI.component.workDB.restApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoKeyWords {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("personId")
    @Expose
    private Integer personId;

    @SerializedName("name")
    @Expose
    private String name;

    public Integer getId() {
        return id;
    }
    public Integer getPersonId() {
        return personId;
    }
    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
    public void setName(String name) {
        this.name = name;
    }
}
