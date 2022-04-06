package event.persist;

public class PersistEvent {

    private final Object entityId;
    private final Object entity;

    public PersistEvent(Object entityId, Object entity) {
        if (entityId == null && entity == null) {
            throw new IllegalArgumentException("PersistContext need entity and entityID");
        }
        this.entity = entity;
        this.entityId = entityId;
    }


    public Object getEntityId() {
        return entityId;
    }

    public Object getEntity() {
        return entity;
    }
}
