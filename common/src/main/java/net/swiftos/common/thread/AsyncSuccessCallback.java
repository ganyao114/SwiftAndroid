package net.swiftos.common.thread;

/**
 * Created by ganyao on 2017/3/23.
 */
@FunctionalInterface
public interface AsyncSuccessCallback<T> {
    void success(T t);
}
