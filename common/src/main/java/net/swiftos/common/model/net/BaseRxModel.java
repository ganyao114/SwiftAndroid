package net.swiftos.common.model.net;


import android.Manifest;

import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.exception.CommonExceptionHandler;
import net.swiftos.common.exception.ExceptionAdapter;
import net.swiftos.common.exception.IExceptionHandler;
import net.swiftos.common.exception.NetworkException;
import net.swiftos.common.model.bean.BaseResponse;
import net.swiftos.common.model.entity.AsyncCallback;
import net.swiftos.common.ospermission.PermissionCheck;
import net.swiftos.utils.StatusUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ganyao on 2016/10/26.
 */

public class BaseRxModel {

    private final static String TAG = "Model --->";

    private IExceptionHandler exceptionFactory;

    public BaseRxModel() {
        BaseApplication.getAppComponent().inject(this);
        exceptionFactory = new CommonExceptionHandler();
    }

    public BaseRxModel(IExceptionHandler exceptionFactory) {
        this.exceptionFactory = exceptionFactory;
        BaseApplication.getAppComponent().inject(this);
    }
    public <T> Observable<T> getAsyncObservable(Observable<BaseResponse<T>> observable, IResponseAdapter baseResponse) {
        return observable
                .doOnSubscribe(this::checkNetwork)
                .map(new BaseHttpFunc<Object,T>(baseResponse))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new ExceptionAdapter<T>());
    }

    /**
     * 获取带缓存功能的 Observable
     * @param getFromCache 从 Cache 获取数据的方法实现
     * @param saveToCache 缓存到 Cache 的方法实现
     * @param observable Retrofit 代理生成的 Observable
     * @param <T>
     * @return
     */
    public <T> Observable<T> getAsyncObservableWithCache(IBaseHttpModel.IGetFromCache<T> getFromCache, IBaseHttpModel.ISaveToCache<T> saveToCache, Observable<BaseResponse<T>> observable, IResponseAdapter baseResponse) {

        Observable httpObservable = observable
                .doOnSubscribe(this::checkNetwork)
                .map(new BaseHttpFunc<Object,T>(baseResponse))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new ExceptionAdapter<T>())
                .doOnNext( t -> saveToCache.toCache(t));
        return Observable.defer(new Func0<Observable<T>>() {
            @Override
            public Observable<T> call() {
                T res = null;
                res = getFromCache.fromCache();
                if (res == null) {
                    return httpObservable;
                } else {
                    return Observable.just(res)
                            .observeOn(AndroidSchedulers.mainThread());
                }
            }
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .publish()
          .autoConnect();
    }

    public <T> Subscriber<T> getSubscriber(AsyncCallback<T> callback) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                callback.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                callback.onDone(callback.getTag());
                if (!exceptionFactory.isFailure(e)) {
                    callback.onError(exceptionFactory.onError(e, callback.getTag()));
                } else {
                    callback.onFailure(exceptionFactory.onFailure(e, callback.getTag()));
                }
            }

            @Override
            public void onNext(T t) {
                callback.onDone(callback.getTag());
                callback.onSuccess(t);
            }
        };
    }

    @PermissionCheck(Manifest.permission.ACCESS_NETWORK_STATE)
    public void checkNetwork() throws NetworkException {
        if (!StatusUtil.checkNetWorkStatus(BaseApplication.getApplication())) {
            throw new NetworkException("no network");
        }
    }

    /**
     * 返回值公共字段的处理，包括业务异常的抛出
     * @param <I,O>
     */
    public static class BaseHttpFunc<I,O> implements Func1<I,O> {

        private IResponseAdapter<I,O> responseAdapter;


        public BaseHttpFunc(IResponseAdapter<I,O> responseAdapter) {
            this.responseAdapter = responseAdapter;
        }

        @Override
        public O call(I baseResponse) {
            return responseAdapter.adapter(baseResponse);
        }
    }

    public void setExceptionHandler(IExceptionHandler exceptionFactory) {
        this.exceptionFactory = exceptionFactory;
    }
}
