package net.swiftos.usermodule.event.entity;

import net.swiftos.eventposter.exception.EventInvokeException;
import net.swiftos.eventposter.template.IEventEntity;

/**
 * Created by swift_gan on 2017/2/17.
 */

public class LoginEventEntity implements IEventEntity {
    @Override
    public Object invoke(Object invoker, Object... pars) throws EventInvokeException {
        return null;
    }
}
