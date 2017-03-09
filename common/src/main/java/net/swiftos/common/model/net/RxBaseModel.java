package net.swiftos.common.model.net;

import net.swiftos.common.model.entity.HttpCallback;
import net.swiftos.common.presenter.IAsyncSubject;
import net.swiftos.common.presenter.RxSubject;

import rx.Observable;
import rx.Subscription;

/**
 * Created by ganyao on 2017/3/9.
 */

public class RxBaseModel implements IBaseModel<Subscription, Observable> {

    private static BaseRxModel baseRxModel = new BaseRxModel();

    @Override
    public <M> IAsyncSubject<Subscription> getAsyncObservable(Observable api, HttpCallback<M> callback) {
        Subscription subscription = baseRxModel
                .getAsyncObservable(api)
                .subscribe(baseRxModel.getSubscriber(callback));
        return new RxSubject(subscription);
    }

    @Override
    public <M> IAsyncSubject<Subscription> getAsyncObservableWithCache(Observable api, IGetFromCache<M> getFromCache
            , ISaveToCache<M> saveToCache, HttpCallback<M> callback) {
        Subscription subscription = baseRxModel
                .getAsyncObservableWithCache(getFromCache, saveToCache, api)
                .subscribe(baseRxModel.getSubscriber(callback));
        return new RxSubject(subscription);
    }
    
}
