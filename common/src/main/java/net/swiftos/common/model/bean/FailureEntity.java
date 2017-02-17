package net.swiftos.common.model.bean;

/**
 * Created by gy939 on 2017/1/15.
 */

public class FailureEntity<T> {

    private Object tag;
    private T data;

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
