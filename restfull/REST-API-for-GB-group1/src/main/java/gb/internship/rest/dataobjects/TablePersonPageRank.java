package gb.internship.rest.dataobjects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author баранов, Aleksandr Vvedensky
 * Класс для работы с таблицей PersonPageRank
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TablePersonPageRank {

    private Integer personId;
    private Integer pageID;
    private Integer rank;

    public Integer getPersonId() { return personId; }

    public void setPersonId(Integer personId) { this.personId = personId;    }

    public Integer getPageID() {        return pageID;    }

    public void setPageID(Integer pageID) {        this.pageID = pageID;    }

    public Integer getRank() {        return rank;    }

    public void setRank(Integer rank) {        this.rank = rank;    }



    public TablePersonPageRank(Integer personId, Integer pageID, Integer rank) {
        this.personId = personId;
        this.pageID = pageID;
        this.rank = rank;
   }
}
