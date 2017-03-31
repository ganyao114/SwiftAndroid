package net.swiftos.common.exception;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import net.swiftos.common.model.bean.ErrorResponse;
import net.swiftos.common.model.bean.FailureEntity;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gy939 on 2017/1/15.
 */

public class CommonExceptionFactory implements IExceptionFactory {

    private List<Class<? extends Throwable>> failureTypes = new ArrayList();

    public CommonExceptionFactory() {
        failureTypes.add(HttpServiceException.class);
        failureTypes.add(JsonParseException.class);
        failureTypes.add(JsonIOException.class);
        failureTypes.add(JsonSyntaxException.class);

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
        failureEntity.setCause(throwable);
        return failureEntity;
    }

    @Override
    public ErrorResponse onError(Throwable throwable, Object tag) {
        ErrorResponse errorResponse = new ErrorResponse();
        if (throwable instanceof NetworkException) {
            errorResponse.setMsg("network is disconnect please check your phone!");
        } else if (throwable instanceof SocketTimeoutException) {
            errorResponse.setMsg("connect is out of time!");
        } else if (throwable instanceof ClassCastException) {
            errorResponse.setMsg("");
        }
        errorResponse.setTag(tag);
        errorResponse.setCause(throwable);
        return errorResponse;
    }
}
