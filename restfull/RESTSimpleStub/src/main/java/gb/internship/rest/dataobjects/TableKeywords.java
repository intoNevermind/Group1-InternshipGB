package gb.internship.rest.dataobjects;

/**
 * Класс для работы с Keywords
 *
 * @author Aleksandr Vvedensky
 */
public class TableKeywords {
    private Integer id;
    private Integer personId;
    private String Keyword;

    public TableKeywords(Integer id, Integer personId, String keyword) {

        this.id = id;
        this.personId = personId;
        Keyword = keyword;
    }

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

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;
    }


}
