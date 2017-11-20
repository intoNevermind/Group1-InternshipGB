package libdb;

import java.util.Collection;

public interface Repository<V> {
    public void add(final V entity);
    public Collection<V> getAll();
    public void remove(final V entity);
    public void update(final V entity);
}
