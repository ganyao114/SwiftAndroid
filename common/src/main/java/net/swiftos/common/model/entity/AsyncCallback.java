package net.swiftos.common.model.entity;

import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;

/**
 * Created by gy939 on 2017/1/15.
 */

public interface AsyncCallback<T> {
    Object getTag();
    void onSuccess(T t);
    void onFailure(FailureEntity failure);
    void onError(ErrorResponse error);
    void onComplete();
    void onDone(Object tag);
}
