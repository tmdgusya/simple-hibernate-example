package validator;

public interface EntityPropertyValidator {
    /**
     * 필수적으로 Entity 에는 ID 가 포함되어야 한다.
     *
     * @return true / false
     */
    boolean isContainsId();

    /**
     * 한번 Persist 된 Entity 인지 확인하는 API
     *
     * @return true / false
     */
    boolean isDetachedEntity();
}
