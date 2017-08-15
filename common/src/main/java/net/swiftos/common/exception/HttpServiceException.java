package net.swiftos.common.exception;

/**
 * Created by ganyao on 2016/10/27.
 */

public class HttpServiceException extends BaseException {

    public HttpServiceException(String message) {
        super(message);
    }

    public HttpServiceException(String message, int flag) {
        super(message, flag);
    }

    public HttpServiceException(String message, Throwable cause, int flag) {
        super(message, cause, flag);
    }
}
