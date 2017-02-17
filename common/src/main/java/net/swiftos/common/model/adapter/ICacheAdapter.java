package net.swiftos.common.model.adapter;


import net.swiftos.common.model.entity.RealmCache;

/**
 * 业务实体 -> Realm缓存实体 适配器
 * Created by ganyao on 2016/11/4.
 */

public interface ICacheAdapter<T> {
    RealmCache ModelToCache(T t);

    T CacheToModel(RealmCache cache);
}
