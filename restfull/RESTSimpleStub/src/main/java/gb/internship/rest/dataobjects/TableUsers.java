package gb.internship.rest.dataobjects;

/**
 * Класс для работы с таблицей Users
 *
 * @author баранов
 * @author Aleksandr Vvedensky
 */
public class TableUsers {
    private Integer id;
    private String login;
    private boolean admin;
    private String password;
    private boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TableUsers(Integer id, String login, boolean admin, String password, boolean active) {
        this.id = id;
        this.login = login;
        this.admin = admin;
        this.password = password;
        this.active = active;
    }
}
