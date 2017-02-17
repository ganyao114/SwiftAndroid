package net.swiftos.eventposter.template;

import net.swiftos.eventposter.exception.EventInvokeException;

/**
 * Created by gy939 on 2016/10/3.
 */
public interface IEventEntity {
    public Object invoke(Object invoker,Object... pars)throws EventInvokeException;
}
