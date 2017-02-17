package net.swiftos.common.exception;

import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;

/**
 * Created by gy939 on 2017/1/15.
 */

public interface IExceptionFactory {
    boolean isError(Throwable throwable);
    FailureEntity onFailure(Throwable throwable, Object tag);
    ErrorResponse onError(Throwable throwable, Object tag);
}
