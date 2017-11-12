package libdb.entities;

import java.util.Date;

/**
 *  Класс описывает объект Ключевое слово, соответствующий сущности БД Keywords
 */
public class Keyword implements PersistenceEntity {
    private Long id;
    private String name;
    private Long personId;

    public Keyword() {};

    public Keyword(Long id, String name, Long personId) {
        this.id = id;
        this.name = name;
        this.personId = personId;
    }

    public Keyword(String name, Long personId) {
        this.name = name;
        this.personId = personId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Keyword [id=" + id + ", name=" + name + ", person=" + personId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (personId == null ? 0 : personId.hashCode());
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
        Keyword other = (Keyword) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }
}
