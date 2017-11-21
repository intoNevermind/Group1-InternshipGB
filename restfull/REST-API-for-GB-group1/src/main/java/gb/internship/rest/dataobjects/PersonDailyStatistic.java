package gb.internship.rest.dataobjects;

import java.util.Date;

/**
 * Created by agcheb on 21.11.17.
 */
public class PersonDailyStatistic {
    private String name;
    private Date rankDate;
    private Integer dailyRank;

    public PersonDailyStatistic(String name, Date rankDate, Integer dailyRank) {
        this.name = name;
        this.rankDate = rankDate;
        this.dailyRank = dailyRank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRankDate() {
        return rankDate;
    }

    public void setRankDate(Date rankDate) {
        this.rankDate = rankDate;
    }

    public Integer getDailyRank() {
        return dailyRank;
    }

    public void setDailyRank(Integer dailyRank) {
        this.dailyRank = dailyRank;
    }
}
