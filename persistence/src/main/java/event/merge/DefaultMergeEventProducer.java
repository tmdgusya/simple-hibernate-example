package event.merge;

import com.google.common.eventbus.EventBus;

public class DefaultMergeEventProducer implements MergeEventProducer {

    private final EventBus eventBus;

    public DefaultMergeEventProducer(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public boolean produce(Object entity) {
        MergeEvent mergeEvent = new MergeEvent(entity);
        eventBus.post(mergeEvent);
        return true;
    }
}
