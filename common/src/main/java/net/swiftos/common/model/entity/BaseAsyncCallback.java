package net.swiftos.common.model.entity;

import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;

/**
 * Created by ganyao on 2017/5/16.
 */

public abstract class BaseAsyncCallback<T> implements AsyncCallback<T> {

    @Override
    public void onFailure(FailureEntity failure) {

    }

    @Override
    public Object getTag() {
        return null;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(ErrorResponse error) {

    }

    @Override
    public void onDone(Object tag) {

    }
}
