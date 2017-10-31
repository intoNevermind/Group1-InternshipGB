package gb.internship.rest.dataobjects;


/**
 * Класс для работы с таблицей sites.
 *
 * @author Aleksandr Vvedensky
 */
public class TableSites {
    private Integer id;
    private String name;
    private String url;
    private Boolean active;

    public TableSites(Integer id, String name, String url, Boolean active) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.active = active;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
