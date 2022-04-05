package event.persist;

public class PersistEvent {

    private final Object entity;

    public PersistEvent(Object entity) {
        this.entity = entity;
    }

    public Object getEntityInfo() {
        return entity;
    }
}
