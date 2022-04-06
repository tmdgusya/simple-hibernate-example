package context;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import testEntity.Entity01;

class DefaultPersistenceContextTest {
    DefaultPersistenceContext defaultPersistenceContext = new DefaultPersistenceContext();


    @Test
    void 엔티티가_Persist_될때_PersistEvent가_Proucer_된다() {
        // given
        Long entityId = 1L;
        Entity01 entity01 = new Entity01(entityId);

        // when
        defaultPersistenceContext.persist(entityId, entity01);

        // then
        Assertions.assertTrue(defaultPersistenceContext.isPersist(entity01));
    }


}

