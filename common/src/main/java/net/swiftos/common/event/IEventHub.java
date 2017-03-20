package net.swiftos.common.event;

/**
 * Created by ganyao on 2017/3/20.
 */

public interface IEventHub {
    void post(Object event);
    void postSticky(Object event);
}
