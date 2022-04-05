package event.merge;

public interface MergeEventConsumer {

    /**
     * Merge 요청이 들어오면 Event 를 통해서 FindDirty 를 작동시켜야 함.
     * FindDirty 와 DB 에 update 요청을 보낸다.
     *
     * @param mergeEvent
     * @return
     */
    boolean consume(MergeEvent mergeEvent);

}
