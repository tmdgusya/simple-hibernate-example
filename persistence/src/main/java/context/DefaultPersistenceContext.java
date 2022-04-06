package context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultPersistenceContext implements PersistenceContext {

    private final Map<Object, Object> persistenceContext = new ConcurrentHashMap<>();

    /**
     * @param entity
     * @return true (단순히 persist) / false (need merge)
     */
    @Override
    public boolean persist(Object id, Object entity) {
        /**
         * 이건 밖에서 판단하고 persist 는 저장만 하는 역할을 하는게 좋을 것 같다.
         * Repository 계층을 만들면 밖으로 빼자.
         */
//        if (isChanged(id, entity)) {
//            mergeEventProducer.produce(entity);
//            persistenceContext.put(id, entity);
//            return false;
//        }
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

    @Override
    public Object getPersistenceEntity(Object id) {
        return persistenceContext.get(id);
    }

    /**
     * 이건 밖에서 판단하고 persist 는 저장만 하는 역할을 하는게 좋을 것 같다.
     * Repository 계층을 만들면 밖으로 빼자.
     */
//    private boolean isChanged(Object id, Object entity) {
//        Object persistedEntity = persistenceContext.get(id);
//        return persistedEntity != null && persistedEntity.equals(entity);
//    }
}
