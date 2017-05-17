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

    @Override
    public void register(Object object) {
        EventBus.getDefault().register(object);
    }

    @Override
    public void unRegister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    @Override
    public void preLoad(Class[] classes) {

    }
}
