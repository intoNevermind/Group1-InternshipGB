package gb.internship.rest.dataobjects;

/**
 * Класс для работы с таблицей Keywords
 *
 * @author Баранов, Aleksandr Vvedensky
 */
public class TableKeywords {
    private Integer id;
    private Integer personId;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TableKeywords(Integer id, Integer personId, String name) {
        this.id = id;
        this.personId = personId;
        this.name = name;
    }
}
