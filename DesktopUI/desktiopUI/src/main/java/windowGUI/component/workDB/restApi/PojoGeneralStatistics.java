package windowGUI.component.workDB.restApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoGeneralStatistics {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("generalRank")
    @Expose
    private Integer generalRank;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getGeneralRank() {
        return generalRank;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGeneralRank(Integer generalRank) {
        this.generalRank = generalRank;
    }
}
