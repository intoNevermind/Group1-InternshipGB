package windowGUI.component.workWithDB.restApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoDailyStatistics {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("rankDate")
    @Expose
    private String rankDate;

    @SerializedName("dailyRank")
    @Expose
    private Integer dailyRank;

    public String getName() {
        return name;
    }
    public Integer getDailyRank() {
        return dailyRank;
    }
    public String getRankDate() {
        return rankDate;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDailyRank(Integer dailyRank) {
        this.dailyRank = dailyRank;
    }
}
