package gb.internship.rest.dataobjects;

import java.util.Date;

/**
 * Created by agcheb on 01.11.17.
 */
public class TablePages {
    private int id; // id страницы (для связи с tablepersonpagerank)
    private String url; //url страницы
    private int siteID; // id сайта (для связи с tablesites)
    private Date foundDateTime; // дата добавления страницы в БД
    private Date lastScanDate; // дата последнего сканирования краулером

    //для реста из всех полей необходима связь id и siteID чтобы связать таблицу сайтов и таблицу ранка личности


    public TablePages(int id, String url, int siteID, Date foundDateTime, Date lastScanDate) {
        this.id = id;
        this.url = url;
        this.siteID = siteID;
        this.foundDateTime = foundDateTime;
        this.lastScanDate = lastScanDate;
    }

    public TablePages(Date lastscandate, String name) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSiteID() {
        return siteID;
    }

    public void setSiteID(int siteID) {
        this.siteID = siteID;
    }

    public Date getFoundDateTime() {
        return foundDateTime;
    }

    public void setFoundDateTime(Date foundDateTime) {
        this.foundDateTime = foundDateTime;
    }

    public Date getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(Date lastScanDate) {
        this.lastScanDate = lastScanDate;
    }
}
