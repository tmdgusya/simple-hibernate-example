package event.merge;

public interface MergeEventConsumer {

    boolean consume(MergeEvent mergeEvent);

}
