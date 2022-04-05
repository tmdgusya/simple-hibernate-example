package event;

import com.google.common.eventbus.EventBus;

import event.merge.MergeEventConsumer;
import event.merge.MergeEventProducer;
import event.persist.PersistEventConsumer;
import event.persist.PersistEventProducer;
import lombok.Builder;

public class EventConfiguration {

    private final EventBus eventBus;
    private final PersistEventProducer defaultPersistEventProducer;
    private final PersistEventConsumer defaultPersistEventConsumer;
    private final MergeEventProducer defaultMergeEventProducer;
    private final MergeEventConsumer mergeEventConsumer;

    @Builder
    public EventConfiguration(EventBus eventBus, PersistEventProducer defaultPersistEventProducer, PersistEventConsumer defaultPersistEventConsumer, MergeEventProducer defaultMergeEventProducer, MergeEventConsumer mergeEventConsumer) {
        this.eventBus = eventBus;
        this.defaultPersistEventProducer = defaultPersistEventProducer;
        this.defaultPersistEventConsumer = defaultPersistEventConsumer;
        this.defaultMergeEventProducer = defaultMergeEventProducer;
        this.mergeEventConsumer = mergeEventConsumer;

        init();
    }

    private void init() {
        eventBus.register(defaultPersistEventConsumer);
        eventBus.register(mergeEventConsumer);
    }

    public PersistEventProducer getDefaultPersistEventProducer() {
        return defaultPersistEventProducer;
    }

    public PersistEventConsumer getDefaultPersistEventConsumer() {
        return defaultPersistEventConsumer;
    }

    public MergeEventProducer getDefaultMergeEventProducer() {
        return defaultMergeEventProducer;
    }

    public MergeEventConsumer getMergeEventConsumer() {
        return mergeEventConsumer;
    }
}
