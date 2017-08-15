package net.swiftos.common.exception;

/**
 * Created by ganyao on 2016/10/27.
 */

public class BaseException extends RuntimeException {

    private int flag;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, int flag) {
        super(message);
        this.flag = flag;
    }

    public BaseException(String message, Throwable cause, int flag) {
        super(message, cause);
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
