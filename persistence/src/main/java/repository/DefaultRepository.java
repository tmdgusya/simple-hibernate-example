package repository;

import event.persist.PersistEventProducer;

public class DefaultRepository<T, E> implements EntityRepository<T, E> {

    private final PersistEventProducer persistEventProducer;

    public DefaultRepository(PersistEventProducer persistEventProducer) {
        this.persistEventProducer = persistEventProducer;
    }

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
