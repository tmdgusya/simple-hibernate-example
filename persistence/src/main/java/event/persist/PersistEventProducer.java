package event.persist;

public interface PersistEventProducer {

    boolean produce(Object entityId, Object entity);

}
