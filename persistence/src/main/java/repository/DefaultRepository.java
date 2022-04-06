package repository;

public class DefaultRepository<T, E> implements EntityRepository<T, E> {

    @Override
    public boolean save(Object entity) {
        return false;
    }

    @Override
    public boolean remove(Object entity) {
        return false;
    }

    @Override
    public Object findById(Object id) {
        return null;
    }
}
