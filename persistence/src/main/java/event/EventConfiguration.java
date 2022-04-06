package event;

import com.google.common.eventbus.EventBus;

import event.persist.PersistEventConsumer;
import event.persist.PersistEventProducer;
import lombok.Builder;

public class EventConfiguration {

    private final EventBus eventBus;
    private final PersistEventProducer defaultPersistEventProducer;
    private final PersistEventConsumer defaultPersistEventConsumer;

    @Builder
    public EventConfiguration(EventBus eventBus, PersistEventProducer defaultPersistEventProducer, PersistEventConsumer defaultPersistEventConsumer) {
        this.eventBus = eventBus;
        this.defaultPersistEventProducer = defaultPersistEventProducer;
        this.defaultPersistEventConsumer = defaultPersistEventConsumer;

        init();
    }

    private void init() {
        eventBus.register(defaultPersistEventConsumer);
    }

    public PersistEventProducer getDefaultPersistEventProducer() {
        return defaultPersistEventProducer;
    }

    public PersistEventConsumer getDefaultPersistEventConsumer() {
        return defaultPersistEventConsumer;
    }
    
}
