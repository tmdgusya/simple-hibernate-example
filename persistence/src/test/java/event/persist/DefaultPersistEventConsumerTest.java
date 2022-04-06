package event.persist;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import context.DefaultPersistenceContext;
import context.PersistenceContext;
import scanner.DefaultEntityDirtyScanner;
import scanner.EntityDirtyScanner;
import testEntity.Entity01;

class DefaultPersistEventConsumerTest {
    PersistenceContext defaultPersistenceContext = mock(DefaultPersistenceContext.class);
    EntityDirtyScanner entityDirtyScanner = mock(DefaultEntityDirtyScanner.class);
    PersistEventConsumer defaultPersistEventConsumer = new DefaultPersistEventConsumer(defaultPersistenceContext, entityDirtyScanner);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("PersistEvent 를 Consume 하였을때 PersistContext 에 EntityId 와 Entity 로 Persist 를 시도한다.")
    void testConsume() {
        // given
        Long entityId = 1L;
        Object entity = new Entity01(entityId);
        PersistEvent persistEvent = new PersistEvent(entityId, entity);
        when(defaultPersistenceContext.isPersist(persistEvent.getEntity())).thenReturn(false);
        when(defaultPersistenceContext.persist(persistEvent.getEntityId(), persistEvent.getEntity())).thenReturn(true);


        // when
        defaultPersistEventConsumer.consume(persistEvent);

        // then
        verify(defaultPersistenceContext).isPersist(persistEvent.getEntity());
        verify(defaultPersistenceContext).persist(persistEvent.getEntityId(), persistEvent.getEntity());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme