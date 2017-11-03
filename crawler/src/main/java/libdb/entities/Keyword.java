package libdb.entities;

import java.util.Date;

/**
 *  Класс описывает объект Ключевое слово, соответствующий сущности БД Keywords
 */
public class Keyword implements PersistenceEntity {
    private Integer id;
    private Person person;
    private String name;

    public Keyword() {};

    public Keyword(Integer id, String name, Person person) {
        this.id = id;
        this.name = name;
        this.person = person;
    }

    public Keyword(String name, Person person) {
        this.name = name;
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Keyword [id=" + id + ", name=" + name + ", person=" + person + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
//        result = prime * result + (site == null ? 0 : site.hashCode());
//        result = prime * result + (url == null ? 0 : url.hashCode());
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
