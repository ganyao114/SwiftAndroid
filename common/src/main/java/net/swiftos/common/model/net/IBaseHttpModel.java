package net.swiftos.common.model.net;

import net.swiftos.common.model.entity.AsyncCallback;
import net.swiftos.common.presenter.IAsyncSubject;

/**
 * @O = 观察者实体类型
 * @A = API 接口返回类型
 * Created by ganyao on 2017/3/9.
 */
public interface IBaseHttpModel<O,A> {
    void setBaseResponse(IResponseAdapter baseResponse);
    <M> IAsyncSubject<O> getAsyncSubject(A api, AsyncCallback<M> callback);
    <M> IAsyncSubject<O> getAsyncSubjectWithCache(A api, IGetFromCache<M> getFromCache, ISaveToCache<M> saveToCache, AsyncCallback<M> callback);
    <M> IAsyncSubject<O> mergeSubjects(AsyncCallback<M> callback, A... subjects);
    <M> IAsyncSubject<O> concatSubjects(AsyncCallback<M> callback, A... subjects);
    @FunctionalInterface
    interface IGetFromCache<T> {
        T fromCache();
    }
    @FunctionalInterface
    interface ISaveToCache<T> {
        void toCache(T t);
    }
}
