package net.swiftos.common.model.net;

import net.swiftos.common.model.entity.HttpCallback;
import net.swiftos.common.presenter.IAsyncSubject;

/**
 * @O = 观察者实体类型
 * @A = API 接口返回类型
 * Created by ganyao on 2017/3/9.
 */

public interface IBaseHttpModel<O,A> {

    <M> IAsyncSubject<O> getAsyncSubject(A api, HttpCallback<M> callback);

    <M> IAsyncSubject<O> getAsyncSUbjectWithCache(A api, IGetFromCache<M> getFromCache, ISaveToCache<M> saveToCache, HttpCallback<M> callback);

    interface IGetFromCache<T> {
        T fromCache();
    }

    interface ISaveToCache<T> {
        void toCache(T t);
    }
}
