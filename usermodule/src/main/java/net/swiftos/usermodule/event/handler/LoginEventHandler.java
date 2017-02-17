package net.swiftos.usermodule.event.handler;

import net.swiftos.eventposter.entity.EventAnnoInfo;
import net.swiftos.eventposter.template.IHandler;
import net.swiftos.usermodule.event.entity.LoginEventEntity;

/**
 * Created by swift_gan on 2017/2/17.
 */

public class LoginEventHandler implements IHandler<LoginEventEntity> {
    @Override
    public void init(Object... objects) {

    }

    @Override
    public void destory(Object... objects) {

    }

    @Override
    public LoginEventEntity parse(EventAnnoInfo annoInfo) {
        return null;
    }

    @Override
    public void load(LoginEventEntity eventEntity, Object invoker) {

    }

    @Override
    public void unload(LoginEventEntity eventEntity, Object invoker) {

    }

    @Override
    public void inject(Object object) {

    }

    @Override
    public void remove(Object object) {

    }
}
