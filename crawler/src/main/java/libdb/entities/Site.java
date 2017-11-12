package libdb.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *  Класс описывает объект Сайт, соответствующий сущности БД Sites
 */
public class Site implements PersistenceEntity {
    private Long id;
    private String name;
    private String url;
    private Boolean active;
    private Long userId;
    private Map<Integer, String> pages = new HashMap<Integer, String>();

    public Site() {};

    public Site(Long id, String name, String url, Boolean active, Long userId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.active = active;
        this.userId = userId;
    }

    public Site(String name, String url, Boolean active, Long userId) {
        this(0L, name, url, active, userId);
    }

    public Site(String name, String url, Long userId) {
        this(name, url, true, userId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Site [id=" + id + ", name=" + name + ", url=" + url + ", active=" + active + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (url == null ? 0 : url.hashCode());
        result = prime * result + (userId == null ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Site other = (Site) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (userId == null) {
            if (other.getUserId() != null) {
                return false;
            }
        } else if (!userId.equals(other.getUserId())) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (url == null) {
            if (other.url != null) {
                return false;
            }
        } else if (!url.equals(other.url)) {
            return false;
        }
        return true;
    }
}
