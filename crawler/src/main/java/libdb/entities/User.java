package libdb.entities;

/**
 * Created by zarodov on 02.11.2017.
 */
public class User {
    private Integer id;
    private String name;
    private Boolean active;

    public User(){

    };

    public User(Integer id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public User(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }

    public User(String name, Integer UserID) {
        this(name, true);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", active=" + active + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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
