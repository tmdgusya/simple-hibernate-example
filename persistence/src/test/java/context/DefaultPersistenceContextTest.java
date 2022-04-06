package context;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import event.EventConfiguration;
import event.merge.DefaultMergeEventConsumer;
import event.merge.DefaultMergeEventProducer;
import event.persist.DefaultPersistEventConsumer;
import event.persist.DefaultPersistEventProducer;
import event.persist.PersistEvent;
import testEntity.Entity01;

class DefaultPersistenceContextTest {
    EventBus eventBus = new EventBus();

    SpyPersistEventConsumer defaultPersistEventConsumer = new SpyPersistEventConsumer();
    SpyPersistEventProducer spyPersistEventProducer = new SpyPersistEventProducer(eventBus);

    EventConfiguration eventConfiguration = EventConfiguration.builder()
            .eventBus(eventBus)
            .defaultPersistEventProducer(spyPersistEventProducer)
            .defaultPersistEventConsumer(defaultPersistEventConsumer)
            .defaultMergeEventProducer(new DefaultMergeEventProducer(eventBus))
            .mergeEventConsumer(new DefaultMergeEventConsumer())
            .build();

    DefaultPersistenceContext defaultPersistenceContext = DefaultPersistenceContext
            .builder()
            .mergeEventProducer(eventConfiguration.getDefaultMergeEventProducer())
            .persistEventProducer(eventConfiguration.getDefaultPersistEventProducer())
            .build();

    @Test
    void 엔티티가_Persist_될때_PersistEvent가_Proucer_된다() {
        // given
        Long entityId = 1L;
        Entity01 entity01 = new Entity01(entityId);

        // when
        defaultPersistenceContext.persist(entityId, entity01);

        // then
        Assertions.assertTrue(defaultPersistenceContext.isPersist(entity01));
        Assertions.assertTrue(spyPersistEventProducer.isExecuted());
        Assertions.assertEquals(spyPersistEventProducer.sendEntityInfo.toString(), new Entity01(entityId).toString());
        Assertions.assertTrue(defaultPersistEventConsumer.isExecuted());
    }

    class SpyPersistEventProducer extends DefaultPersistEventProducer {

        private int produceCount = 0;
        private Object sendEntityInfo = null;

        public SpyPersistEventProducer(EventBus eventBus) {
            super(eventBus);
        }

        @Override
        public boolean produce(Object persistEvent) {
            this.sendEntityInfo = persistEvent;
            produceCount++;
            super.produce(persistEvent);
            return true;
        }

        public boolean isExecuted() {
            return produceCount != 0;
        }

    }

    class SpyPersistEventConsumer extends DefaultPersistEventConsumer {

        private int count = 0;

        @Subscribe
        public boolean consume(PersistEvent persistEvent) {
            this.count++;
            return true;
        }

        public boolean isExecuted() {
            return count != 0;
        }

    }
}

