package net.swiftos.common.cache;

/**
 * Created by ganyao on 2017/4/14.
 */
@FunctionalInterface
public interface LruCacheCallback<T> {
    void onItemDestroy(T t);
}
