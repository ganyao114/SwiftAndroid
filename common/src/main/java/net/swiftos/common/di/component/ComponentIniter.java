package net.swiftos.common.di.component;

/**
 * Created by ganyao on 2017/4/28.
 */
@FunctionalInterface
public interface ComponentIniter<T> {
    T init();
}
