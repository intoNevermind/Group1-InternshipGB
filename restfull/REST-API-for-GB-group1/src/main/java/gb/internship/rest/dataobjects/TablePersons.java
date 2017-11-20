package gb.internship.rest.dataobjects;

/**
 * Класс для работы с таблицей persons.
 *
 * @author Aleksandr Vvedensky
 */
public class TablePersons {
    private Integer id;
    private String name;
    private Boolean active;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public TablePersons(Integer id, String name, Boolean active) {

        this.id = id;
        this.name = name;
        this.active = active;
    }


}
