package libdb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractModelRepository<T> implements Repository<T> {

    private List<T> items = null;

    public AbstractModelRepository() {
        this(new ArrayList<T>());
    }

    public AbstractModelRepository(List<T> itemsList) {
        this.items = itemsList;
    }

    public void add(T item) {
        items.add(item);
    }

    public void add(Iterable<T> items) {

    }

    public T select(Specification specification) {
        return null;
    }

    public void update(T entity) {

    }

    public void delete(T item) {
        items.remove(item);
    }

    public void delete(Specification specification) {

    }

    public Collection<T> getAll() {
        return items;
    }

    public Collection<T> getAllByUser(Integer UserId) throws SQLException {
        return items;
    }

    public T findById(int id) {
        return items.get(id);
    }

    public void removeAll() {
        items.clear();
    }
}
