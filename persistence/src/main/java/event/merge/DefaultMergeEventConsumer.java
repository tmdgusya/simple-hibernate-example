package event.merge;

import com.google.common.eventbus.Subscribe;

public class DefaultMergeEventConsumer implements MergeEventConsumer {

    @Override
    @Subscribe
    public boolean consume(MergeEvent mergeEvent) {
        return false;
    }
}
