package net.swiftos.common.presenter;

/**
 * 异步工作队列抽象接口
 * Created by ganyao on 2017/3/9.
 */

public interface IAsyncSubjectsQueue<T> {
    void addSubject(IAsyncSubject<T> observer);
    void removeSubject(IAsyncSubject<T> observer);
    void destroy();
}
