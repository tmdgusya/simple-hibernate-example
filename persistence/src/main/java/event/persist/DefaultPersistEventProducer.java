package event.persist;

import com.google.common.eventbus.EventBus;

public class DefaultPersistEventProducer implements PersistEventProducer {

    private final EventBus eventBus;

    public DefaultPersistEventProducer(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public boolean produce(Object entity) {
        PersistEvent persistEvent = new PersistEvent(entity);
        eventBus.post(persistEvent);
        return true;
    }
}
