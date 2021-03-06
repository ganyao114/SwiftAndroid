package net.swiftos.common.event;

import net.swiftos.eventposter.core.EventPoster;
import net.swiftos.eventposter.modules.customevent.handler.CustomEventHandler;

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
        EventPoster.with(CustomEventHandler.class).broadcastSticky(event);
    }

    @Override
    public void register(Object object) {
        EventPoster.registerDeep(object);
    }

    @Override
    public void unRegister(Object object) {
        EventPoster.unRegisterDeep(object);
    }

    @Override
    public void preLoad(Class[] classes) {
        EventPoster.preLoadDeep(classes);
    }
}
