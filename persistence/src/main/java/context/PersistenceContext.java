package context;

public interface PersistenceContext {
    
    boolean persist(Object Id, Object entity);

    boolean detach(Object entity);

    boolean remove(Object entity);

    boolean isDetached(Object id);

    boolean isPersist(Object entity);

}
