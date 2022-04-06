package event.persist;

import com.google.common.eventbus.EventBus;

public class DefaultPersistEventProducer implements PersistEventProducer {

    private final EventBus eventBus;

    public DefaultPersistEventProducer(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public boolean produce(Object entityId, Object entity) {
        PersistEvent persistEvent = new PersistEvent(entityId, entity);
        eventBus.post(persistEvent);
        return true;
    }
}
