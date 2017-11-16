package windowGUI.component.workDB.restApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoPersonPageRank {
    @SerializedName("personId")
    @Expose
    private Integer personId;
    @SerializedName("pageID")
    @Expose
    private Integer pageID;
    @SerializedName("rank")
    @Expose
    private Integer rank;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPageID() {
        return pageID;
    }

    public void setPageID(Integer pageID) {
        this.pageID = pageID;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
