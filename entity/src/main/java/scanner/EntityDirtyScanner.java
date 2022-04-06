package scanner;

public interface EntityDirtyScanner {
    /**
     * Entity 의 변경점이 있는지 확인합니다.
     * 변경된 부분이 있다면 true 를 리턴합니다.
     * Entity 의 모든 변경점을 검사하므로 O(N) 의 시간이 걸립니다. N(Property의 개수)
     *
     * @return
     */
    boolean isDirty(Object older, Object newer);
}
