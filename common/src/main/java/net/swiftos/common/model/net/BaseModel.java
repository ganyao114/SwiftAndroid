package net.swiftos.common.model.net;


import net.swiftos.common.application.BaseApplication;
import net.swiftos.common.exception.CommonExceptionFactory;
import net.swiftos.common.exception.ExceptionAdapter;
import net.swiftos.common.exception.HttpServiceException;
import net.swiftos.common.exception.IExceptionFactory;
import net.swiftos.common.exception.NetworkException;
import net.swiftos.common.model.bean.BaseResponse;
import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.IResponse;
import net.swiftos.common.model.entity.HttpCallback;
import net.swiftos.utils.StatusUtil;

import org.json.JSONException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ganyao on 2016/10/26.
 */

public class BaseModel {

    private final static String TAG = "Model --->";

    private IExceptionFactory exceptionFactory;

    public BaseModel() {
        BaseApplication.getAppComponent().inject(this);
        exceptionFactory = new CommonExceptionFactory();
    }

    public BaseModel(IExceptionFactory exceptionFactory) {
        this.exceptionFactory = exceptionFactory;
        BaseApplication.getAppComponent().inject(this);
    }

    public <T> Observable<T> getAsyncObservable(Observable<BaseResponse<T>> observable) {
        return observable
                .doOnSubscribe(this::checkNetwork)
                .map(new BaseHttpFunc<T>())
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
    public <T> Observable<T> getAsyncObservableWithCache(IGetFromCache<T> getFromCache, ISaveToCache<T> saveToCache, Observable<BaseResponse<T>> observable) {

        Observable httpObservable = observable
                .doOnSubscribe(this::checkNetwork)
                .map(new BaseHttpFunc<T>())
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

    protected <T> Subscriber<T> getSubscriber(HttpCallback<T> callback) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                callback.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                if (exceptionFactory.isError(e)) {
                    callback.onError(exceptionFactory.onError(e, callback.getTag()));
                } else {
                    callback.onFailure(exceptionFactory.onFailure(e, callback.getTag()));
                }
            }

            @Override
            public void onNext(T t) {
                callback.onSuccess(t);
            }
        };
    }

    protected void checkNetwork() throws NetworkException {
        if (!StatusUtil.checkNetWorkStatus(BaseApplication.getApplication())) {
            throw new NetworkException("no network");
        }
    }

    public interface IGetFromCache<T> {
        T fromCache();
    }

    public interface ISaveToCache<T> {
        void toCache(T t);
    }

    /**
     * 返回值公共字段的处理，包括业务异常的抛出
     * @param <T>
     */
    public class BaseHttpFunc<T> implements Func1<BaseResponse<T>, T> {

        @Override
        public T call(BaseResponse<T> baseResponse) {
            int status = baseResponse.getStatus();
            String msg = baseResponse.getMessage();
            T data = baseResponse.getData();
            if (status != 1) {
                throw new HttpServiceException(msg);
            }
            return data;
        }
    }

}
