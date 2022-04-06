package event.persist;

public interface PersistEventConsumer {

    void consume(PersistEvent persistEvent);

}
