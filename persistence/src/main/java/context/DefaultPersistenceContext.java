package context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import event.merge.MergeEventProducer;
import event.persist.PersistEventProducer;
import lombok.Builder;
import scanner.EntityPropertyScanner;
import scanner.GuavaEntityScanner;

public class DefaultPersistenceContext implements PersistenceContext {

    private final Map<Object, Object> persistenceContext = new ConcurrentHashMap<>();
    private final EntityPropertyScanner entityScanner;
    private final MergeEventProducer mergeEventProducer;
    private final PersistEventProducer persistEventProducer;

    @Builder
    public DefaultPersistenceContext(MergeEventProducer mergeEventProducer, PersistEventProducer persistEventProducer) {
        this.entityScanner = new GuavaEntityScanner();
        this.mergeEventProducer = mergeEventProducer;
        this.persistEventProducer = persistEventProducer;
    }

    /**
     * Custom EntityPropertyScanner 를 구현할 경우 DI 가 가능하다.
     *
     * @param entityScanner
     */
    public DefaultPersistenceContext(EntityPropertyScanner entityScanner, MergeEventProducer mergeEventProducer, PersistEventProducer persistEventProducer) {
        this.entityScanner = entityScanner;
        this.mergeEventProducer = mergeEventProducer;
        this.persistEventProducer = persistEventProducer;
    }

    /**
     * @param entity
     * @return true (단순히 persist) / false (need merge)
     */
    @Override
    public boolean persist(Object entity) {
        Object id = entityScanner.getId(entity.getClass());

        if (isDetached(id)) {
            mergeEventProducer.produce(entity);
            persistenceContext.put(id, entity);
            return false;
        }
        persistEventProducer.produce(entity);
        persistenceContext.put(id, entity);
        return true;
    }

    /**
     * Entity Manager 에 관리되었다가 분리 되는 상태
     *
     * @param entity
     * @return
     */
    @Override
    public boolean detach(Object entity) {
        return false;
    }

    /**
     * 실제 물리 DB 에서 삭제를 요구
     *
     * @param entity
     * @return
     */
    @Override
    public boolean remove(Object entity) {
        return false;
    }

    @Override
    public boolean isDetached(Object id) {
        return persistenceContext.containsKey(id);
    }

    @Override
    public boolean isPersist(Object entity) {
        return persistenceContext.containsValue(entity);
    }
}
