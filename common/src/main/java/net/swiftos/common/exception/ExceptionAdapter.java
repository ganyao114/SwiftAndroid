package net.swiftos.common.exception;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ganyao on 2016/11/4.
 */

public class ExceptionAdapter<T> implements Func1<Throwable, Observable<T>> {
    @Override
    public Observable<T> call(Throwable throwable) {
        return Observable.error(ExceptionEngine.handlerException(throwable));
    }
}
