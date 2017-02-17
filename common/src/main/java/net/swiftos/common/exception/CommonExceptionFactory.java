package net.swiftos.common.exception;

import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;

/**
 * Created by gy939 on 2017/1/15.
 */

public class CommonExceptionFactory implements IExceptionFactory {
    @Override
    public boolean isError(Throwable throwable) {
        return false;
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
