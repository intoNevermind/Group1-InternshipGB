package libdb;

import libdb.entities.User;
import java.sql.SQLException;
import java.util.Collection;

public interface Repository<T> {
    void add(T item) throws SQLException;
    void delete(T item) throws SQLException;
    void update(T item) throws SQLException;
    Collection<T> getAll(Boolean onlyActive) throws SQLException;
    Collection<T> getAll(final Long userId, Boolean onlyActive) throws SQLException;
    Collection<T> getAll() throws SQLException;
    Collection<T> getAll(final Long userId) throws SQLException;
}
