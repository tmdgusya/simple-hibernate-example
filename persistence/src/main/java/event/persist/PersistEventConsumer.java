package event.persist;

public interface PersistEventConsumer {

    boolean consume(PersistEvent persistEvent);

}
