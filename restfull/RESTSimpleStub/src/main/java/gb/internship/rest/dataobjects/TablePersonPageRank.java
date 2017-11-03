package gb.internship.rest.dataobjects;



/**
 * @author баранов
 * Класс для работы с таблицей PersonPageRank
 */

public class TablePersonPageRank {

    private Integer persnoID;
    private Integer pageID;
    private Integer rank;

    public Integer getPersnoID() { return persnoID; }

    public void setPersnoID(Integer persnoID) { this.persnoID = persnoID;    }

    public Integer getPageID() {        return pageID;    }

    public void setPageID(Integer pageID) {        this.pageID = pageID;    }

    public Integer getRank() {        return rank;    }

    public void setRank(Integer rank) {        this.rank = rank;    }



    public TablePersonPageRank(Integer persnoID, Integer pageID, Integer rank) {
        this.persnoID = persnoID;
        this.pageID = pageID;
        this.rank = rank;
   }
}
