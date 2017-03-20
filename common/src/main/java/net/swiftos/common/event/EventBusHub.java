package net.swiftos.common.event;

import net.swiftos.eventposter.core.EventPoster;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ganyao on 2017/3/20.
 */

public class EventBusHub implements IEventHub {
    @Override
    public void post(Object event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void postSticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }
}
