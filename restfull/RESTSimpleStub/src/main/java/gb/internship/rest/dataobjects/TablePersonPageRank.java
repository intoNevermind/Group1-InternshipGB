package gb.internship.rest.dataobjects;

/**
 * Created by agcheb on 31.10.17.
 */
public class TablePersonPageRank {
    private Integer personID;
    private Integer pageID;
    private Integer rank;

    public TablePersonPageRank(Integer personID, Integer pageID, Integer rank) {
        this.personID = personID;
        this.pageID = pageID;
        this.rank = rank;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
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
