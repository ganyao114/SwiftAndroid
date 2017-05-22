package net.swiftos.eventposter.template;

import net.swiftos.eventposter.entity.EventAnnoInfo;

/**
 * Created by gy939 on 2016/10/3.
 */
public interface IHandler<T extends IEventEntity> {
    void init(Object... objects);
    void destroy(Object... objects);
    T parse(EventAnnoInfo annoInfo);
    void load(T eventEntity, Object invoker);
    void unload(T eventEntity, Object invoker);
    void inject(Object object);
    void remove(Object object);
}
