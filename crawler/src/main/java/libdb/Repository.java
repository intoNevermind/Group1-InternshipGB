package libdb;

import libdb.entities.User;
import java.sql.SQLException;
import java.util.Collection;

public interface Repository<T> {
    void add(T item) throws SQLException;
    void delete(T item) throws SQLException;
    void deleteCascade(T item) throws SQLException;
    void update(T item) throws SQLException;
    Collection<T> getAll() throws SQLException;
    Collection<T> getAllByUser(final User user) throws SQLException;
}
