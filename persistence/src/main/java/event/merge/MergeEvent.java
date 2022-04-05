package event.merge;

public class MergeEvent {

    private final Object entity;

    public MergeEvent(Object entity) {
        this.entity = entity;
    }

    public Object getEntityInfo() {
        return entity;
    }
}
