package net.swiftos.common.workerqueue;

/**
 * Created by ganyao on 2017/3/30.
 */
@FunctionalInterface
public interface CallbackVoid<T> {
    void callback(T t);
}
