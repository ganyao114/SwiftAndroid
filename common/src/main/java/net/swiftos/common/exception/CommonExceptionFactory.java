package net.swiftos.common.exception;

import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gy939 on 2017/1/15.
 */

public class CommonExceptionFactory implements IExceptionFactory {

    private List<Class<? extends Throwable>> failureTypes = new ArrayList();

    public CommonExceptionFactory() {
        failureTypes.add(HttpServiceException.class);
    }

    @Override
    public boolean isFailure(Throwable throwable) {
        if (failureTypes.contains(throwable.getClass())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public FailureEntity onFailure(Throwable throwable, Object tag) {
        FailureEntity failureEntity = new FailureEntity();
        failureEntity.setTag(tag);
        return failureEntity;
    }

    @Override
    public ErrorResponse onError(Throwable throwable, Object tag) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTag(tag);
        return errorResponse;
    }
}
