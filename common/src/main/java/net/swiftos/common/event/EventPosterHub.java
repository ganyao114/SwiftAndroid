package net.swiftos.common.event;

import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.impls.customevent.handler.CustomEventHandler;

/**
 * Created by ganyao on 2017/3/20.
 */

public class EventPosterHub implements IEventHub {
    @Override
    public void post(Object event) {
        EventPoster.with(CustomEventHandler.class).broadcast(event);
    }

    @Override
    public void postSticky(Object event) {
        EventPoster.with(CustomEventHandler.class).broadcaststicky(event);
    }
}
