package libdb.repository;

import java.util.Collection;

public interface Repository<T> {
    public void add(T item);
    void add(Iterable<T> items);
    public Collection<T> getAll();
    public void remove(T item);
    void remove(Specification specification);
    public void update(T item);
}
