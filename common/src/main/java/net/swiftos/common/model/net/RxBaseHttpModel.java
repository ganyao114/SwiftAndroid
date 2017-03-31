package net.swiftos.common.model.net;

import net.swiftos.common.exception.ExceptionAdapter;
import net.swiftos.common.model.entity.AsyncCallback;
import net.swiftos.common.presenter.IAsyncSubject;
import net.swiftos.common.presenter.RxSubject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ganyao on 2017/3/9.
 */

public class RxBaseHttpModel implements IBaseHttpModel<Subscription, Observable> {

    private static BaseRxModel baseRxModel = new BaseRxModel();
    private IResponseAdapter baseResponse;

    @Override
    public void setBaseResponse(IResponseAdapter baseResponse) {
        this.baseResponse = baseResponse;
    }

    @Override
    public <M> IAsyncSubject<Subscription> getAsyncSubject(Observable api, AsyncCallback<M> callback) {
        Subscription subscription = baseRxModel
                .getAsyncObservable(api, baseResponse)
                .subscribe(baseRxModel.getSubscriber(callback));
        return new RxSubject(subscription);
    }

    @Override
    public <M> IAsyncSubject<Subscription> getAsyncSubjectWithCache(Observable api, IGetFromCache<M> getFromCache
            , ISaveToCache<M> saveToCache, AsyncCallback<M> callback) {
        Subscription subscription = baseRxModel
                .getAsyncObservableWithCache(getFromCache, saveToCache, api, baseResponse)
                .subscribe(baseRxModel.getSubscriber(callback));
        return new RxSubject(subscription);
    }

    /**
     * 合并 subject
     * @param callback
     * @param subjects
     * @param <M>
     * @return
     */
    @Override
    public <M> IAsyncSubject<Subscription> mergeSubjects(AsyncCallback<M> callback, Observable... subjects) {
        Subscription observable = Observable.<M>concat(Observable.<Observable<? extends M>>from(subjects))
                .first()
                .doOnSubscribe(baseRxModel::checkNetwork)
                .subscribeOn(Schedulers.io())
                .map(new BaseRxModel.BaseHttpFunc<Object,M>(baseResponse))
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new ExceptionAdapter<M>())
                .subscribe(baseRxModel.getSubscriber(callback));
        return new RxSubject(observable);
    }

    /**
     * 合并 subject
     * @param callback
     * @param subjects
     * @param <M>
     * @return
     */
    @Override
    public <M> IAsyncSubject<Subscription> concatSubjects(AsyncCallback<M> callback, Observable... subjects) {
        Subscription observable = Observable.<M>merge(Observable.<Observable<? extends M>>from(subjects))
                .doOnSubscribe(baseRxModel::checkNetwork)
                .subscribeOn(Schedulers.io())
                .map(new BaseRxModel.BaseHttpFunc<Object,M>(baseResponse))
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new ExceptionAdapter<M>())
                .subscribe(baseRxModel.getSubscriber(callback));
        return new RxSubject(observable);
    }

}
