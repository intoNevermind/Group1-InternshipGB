package libdb;

import java.sql.SQLException;
import java.util.Collection;

public interface Repository<T> {
    public void add(T item);
    void add(Iterable<T> items);
    T select(Specification specification);
    public Collection<T> getAll();
    public Collection<T> getAllByUser(Integer UserId) throws SQLException;
    public void delete(T item);
    void delete(Specification specification);
    public void update(T item);
}
