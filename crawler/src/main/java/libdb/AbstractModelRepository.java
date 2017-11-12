package libdb;

import libdb.entities.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractModelRepository<T> implements Repository<T> {

    private List<T> items = null;

    public AbstractModelRepository() {
        items = new ArrayList<T>();
    }

    public void add(T item) throws SQLException {
        items.add(item);
    }

    public void update(T item) throws SQLException {

    }

    public void delete(T item) throws SQLException {
        items.remove(item);
    }

    public Collection<T> getAll(Boolean onlyActive) throws SQLException {
        return items;
    }

    public Collection<T> getAll(final Long userId, Boolean onlyActive) throws SQLException {
        return items;
    }

    @Override
    public Collection<T> getAll() throws SQLException {
        return items;
    }

    @Override
    public Collection<T> getAll(Long userId) throws SQLException {
        return items;
    }
}
