package net.swiftos.common.model.bean;

import java.io.Serializable;

/**
 * 返回数据最外层模型
 * Created by ganyao on 2016/10/27.
 */

public class BaseResponse<T> implements Serializable {

    private int status;
    private String message;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
