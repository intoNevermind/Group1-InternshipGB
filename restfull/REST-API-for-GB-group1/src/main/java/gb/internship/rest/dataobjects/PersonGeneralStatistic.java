package gb.internship.rest.dataobjects;

/**
 * Created by agcheb on 07.11.17.
 */
public class PersonGeneralStatistic {
    private Integer id;
    private String name;
    private Integer generalRank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGeneralRank() {
        return generalRank;
    }

    public void setGeneralRank(Integer generalRank) {
        this.generalRank = generalRank;
    }

    public PersonGeneralStatistic(Integer id, String name, Integer generalRank) {

        this.id = id;
        this.name = name;
        this.generalRank = generalRank;
    }
}
