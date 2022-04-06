package event.persist;

import com.google.common.eventbus.Subscribe;

import context.DefaultPersistenceContext;

public class DefaultPersistEventConsumer implements PersistEventConsumer {

    private final DefaultPersistenceContext defaultPersistenceContext;

    public DefaultPersistEventConsumer(DefaultPersistenceContext defaultPersistenceContext) {
        this.defaultPersistenceContext = defaultPersistenceContext;
    }

    @Override
    @Subscribe
    public void consume(PersistEvent persistEvent) {
        Object entityId = persistEvent.getEntityId();
        Object entity = persistEvent.getEntity();
        defaultPersistenceContext.persist(entityId, entity);
    }
}
