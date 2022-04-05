package event.persist;

import com.google.common.eventbus.Subscribe;

public class DefaultPersistEventConsumer implements PersistEventConsumer {
    
    @Override
    @Subscribe
    public boolean consume(PersistEvent persistEvent) {
        return false;
    }
}
