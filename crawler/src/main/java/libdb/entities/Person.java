package libdb.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Класс описывает объект Личность, соответствующий сущности БД Persons
 */
public class Person implements PersistenceEntity {
    private Long id;
    private String name;
    private Boolean active;
    private Long userId;
    private Map<Integer, String> keywords = new HashMap<Integer, String>();
    private Map<Integer, Integer> ranks = new HashMap<Integer, Integer>();

    public Person(){

    };

    public Person(Long id, String name, Boolean active, Long userId) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.userId = userId;
    }

    public Person(Long id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Person(String name, Boolean active, Long userId) {
        this.name = name;
        this.active = active;
        this.userId = userId;
    }

    public Person(String name, Long userId) {
        this(name, true, userId);
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

    public Map<Integer, String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Map<Integer, String> keywords) {
        this.keywords = keywords;
    }

    public void deleteKeyword(String keyword) {
        this.keywords.remove(keyword);
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", active=" + active + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
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
        Person other = (Person) obj;
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
        return true;
    }
}
