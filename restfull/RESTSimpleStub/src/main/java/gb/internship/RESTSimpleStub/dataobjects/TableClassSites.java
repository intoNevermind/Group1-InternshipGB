package gb.internship.RESTSimpleStub.dataobjects;


/**
 * Класс для рбаоты с таблицей sites.
 */
public class TableClassSites {
    Integer id;
    String name;
    String url;
    Boolean active;

    public TableClassSites(Integer id, String name, String url, Boolean active) {
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
