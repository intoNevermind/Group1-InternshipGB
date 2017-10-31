package gb.internship.rest.dataobjects;


/**
 * @author баранов
 * Класс для работы с таблицей Keywords
 */

public class TableKeywords {

    Integer id;
    String name;
    Integer PersonID;



    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Integer getPersonID() {return PersonID;}

    public void setPersonID(Integer personID) {PersonID = personID;}




    public TableKeywords(Integer id, String name, Integer personID) {
        this.id = id;
        this.name = name;
        PersonID = personID;
    }
}
