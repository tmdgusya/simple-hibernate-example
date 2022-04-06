package event.persist;

import com.google.common.eventbus.Subscribe;

import context.PersistenceContext;

public class DefaultPersistEventConsumer implements PersistEventConsumer {

    private final PersistenceContext defaultPersistenceContext;

    public DefaultPersistEventConsumer(PersistenceContext defaultPersistenceContext) {
        this.defaultPersistenceContext = defaultPersistenceContext;
    }

    @Override
    @Subscribe
    public void consume(PersistEvent persistEvent) {
        Object entityId = persistEvent.getEntityId();
        Object entity = persistEvent.getEntity();
        if (defaultPersistenceContext.isPersist(entity)) {
            // findDirty()
        }
        defaultPersistenceContext.persist(entityId, entity);
    }
}
