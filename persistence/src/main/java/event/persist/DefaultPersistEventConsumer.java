package event.persist;

import com.google.common.eventbus.Subscribe;

import context.PersistenceContext;
import scanner.EntityDirtyScanner;

public class DefaultPersistEventConsumer implements PersistEventConsumer {

    private final PersistenceContext defaultPersistenceContext;
    private final EntityDirtyScanner entityDirtyScanner;

    public DefaultPersistEventConsumer(PersistenceContext defaultPersistenceContext, EntityDirtyScanner entityDirtyScanner) {
        this.defaultPersistenceContext = defaultPersistenceContext;
        this.entityDirtyScanner = entityDirtyScanner;
    }

    @Override
    @Subscribe
    public void consume(PersistEvent persistEvent) {
        Object entityId = persistEvent.getEntityId();
        Object entity = persistEvent.getEntity();
        if (defaultPersistenceContext.isPersist(entity)) {
            Object persistenceEntity = defaultPersistenceContext.getPersistenceEntity(entityId);
            if (entityDirtyScanner.isDirty(persistenceEntity, entity)) {
                // TODO DBStore Update
            }
        }
        defaultPersistenceContext.persist(entityId, entity);
        //TODO DBStore Insert
    }
}
