package gb.internship.rest.dataobjects;


/**
 * @author баранов
 * Класс для работы с таблицей PersonPageRank
 */

public class TablePersonPageRank {
    private Integer PersnoID;
    private Integer PageID;
    private Integer Rank;


    public Integer getPersnoID() { return  PersnoID;   }

    public void setPersnoID(Integer persnoID) {   this.PersnoID = persnoID;  }

    public Integer getPageID() { return PageID; }

    public void setPageID(Integer pageID) {this.PageID = pageID;}

    public Integer getRank() {return Rank;}

    public void setRank(Integer rank) { this.Rank = rank;}

    public TablePersonPageRank (Integer PersnoID, Integer PageID, Integer Rank) {
        this.PersnoID = PersnoID;
        this.PageID = PageID;
        this.Rank = Rank;
    }
}
